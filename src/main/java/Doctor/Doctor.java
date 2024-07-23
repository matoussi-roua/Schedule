package Doctor;

import lombok.*;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Doctor {
    int id;
    int workload;
    Set<Integer> assignedDays;
    double score;

    public Doctor(int id) {
        this.id = id;
        this.workload = 0;
        this.assignedDays = new HashSet<>();
        this.score = 0;
    }

    public void addAssignedDays(Integer assignedDay) {
        this.assignedDays.add(assignedDay);

    }
}