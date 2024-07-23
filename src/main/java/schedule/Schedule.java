package schedule;

import Doctor.Doctor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.*;

@Getter
@Setter
@ToString
@Component
@NoArgsConstructor
public class Schedule {
    private int numberDoctors;
    private int numberDays;
    private List<Integer> saturdays;
    private List<Integer> sundays;
    private List<Integer> nationalDays;
    private Map<Integer, Double> daysAndPoints = new HashMap<>();

    public Schedule(int numberDoctors, int numberDays, List<Integer> saturdays, List<Integer> sundays, List<Integer> nationalDays, Map<Integer, Double> daysAndPoints) {
        this.numberDoctors = numberDoctors;
        this.numberDays = numberDays;
        this.saturdays = saturdays;
        this.sundays = sundays;
        this.nationalDays = nationalDays;
        this.daysAndPoints = daysAndPoints;
        for (int n = 1; n <= numberDays; n++) {
            daysAndPoints.put(n, 1.0);
            if (saturdays.contains(n)) {
                daysAndPoints.put(n, 1.5);
            }
            if (sundays.contains(n)) {
                daysAndPoints.put(n, 2.0);
            }
            if (nationalDays.contains(n)) {
                daysAndPoints.put(n, 3.0);
            }
        }
    }

    // Helper method to check if a doctor can work on a specific day
    boolean canWork(Doctor doctor, int day) {
        return !doctor.getAssignedDays().contains(day - 1) && !doctor.getAssignedDays().contains(day - 2);
    }

    public String generateSchedule() {
        List<Doctor> doctors = new ArrayList<>();
        for (int i = 1; i <= this.numberDoctors; i++) {
            doctors.add(new Doctor(i));
        }

        // Assign days to doctors
        for (int day : this.nationalDays) {
            assignDayToDoctor(doctors, day);
        }
        for (int day : this.sundays) {
            assignDayToDoctor(doctors, day);
        }
        for (int day : this.saturdays) {
            assignDayToDoctor(doctors, day);
        }
        for (int day = 1; day <= this.numberDays; day++) {
            if (!this.nationalDays.contains(day) && !this.sundays.contains(day) && !this.saturdays.contains(day)) {
                assignDayToDoctor(doctors, day);
            }
        }
        System.out.println("total days :" + getTotalAssignedDays(doctors) +
                " diffrence workload :" + getMaxWorkloadMinusMinWorkload(doctors) +
                " diffrence score :" + getMaxScoreMinusMinScore(doctors));
        return doctors.toString();
    }

    private void assignDayToDoctor(List<Doctor> doctors, int day) {
        boolean isDayAssigned = doctors.stream().anyMatch(doctor -> doctor.getAssignedDays().contains(day));

        if (isDayAssigned) {
            return; // Skip the day if it has already been assigned
        }
        // Sort doctors by current workload and score to balance the distribution
        doctors.sort(Comparator.comparingDouble(Doctor::getScore).thenComparingInt(Doctor::getWorkload));

        for (Doctor doctor : doctors) {
            if (canWork(doctor, day)) {
                doctor.addAssignedDays(day);
                doctor.setWorkload(doctor.getWorkload() + 1);
                doctor.setScore(doctor.getScore() + this.daysAndPoints.get(day));
                break; // Exit the loop after assigning one doctor to the day
            }
        }
    }

    public int getTotalAssignedDays(List<Doctor> doctors) {
        return doctors.stream().mapToInt(Doctor::getWorkload).sum();
    }

    public int getMaxWorkloadMinusMinWorkload(List<Doctor> doctors) {
        int maxWorkload = doctors.stream().mapToInt(Doctor::getWorkload).max().orElse(0);
        int minWorkload = doctors.stream().mapToInt(Doctor::getWorkload).min().orElse(0);
        return maxWorkload - minWorkload;
    }

    public double getMaxScoreMinusMinScore(List<Doctor> doctors) {
        double maxScore = doctors.stream().mapToDouble(Doctor::getScore).max().orElse(0);
        double minScore = doctors.stream().mapToDouble(Doctor::getScore).min().orElse(0);
        return maxScore - minScore;
    }

}
