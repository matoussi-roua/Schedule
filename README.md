# Scheduling Algorithm for Doctors

## Overview

This project aims to develop a scheduling algorithm for doctors to automate and optimize the generation of monthly work schedules. The goal is to ensure a fair distribution of workdays and points, taking into account specific rules for different types of days. The system aims to balance the workload and score among doctors while adhering to constraints such as rest days.

## Features

- **Doctor Scheduling**: Assign workdays to doctors while considering different point values for various days.
- **Workload Balancing**: Ensure minimal differences in the number of workdays and scores among doctors.
- **Rest Day Constraints**: Implement rules to ensure doctors have appropriate rest periods.

## Rules and Constraints

1. **Point Values for Days**:
   - **Saturdays**: 1.5 points
   - **Sundays**: 2 points
   - **National Days**: 3 points
   - **Other Days**: 1 point

2. **Rest Days**:
   - If a doctor works on a specific day, they must rest for the following two days. This rule ensures doctors have sufficient recovery time between shifts.

3. **Schedule Generation**:
   - **Equal Distribution**: The algorithm aims to distribute shifts equally among doctors. The acceptable difference in workdays between doctors is at most 1 day.
   - **Score Distribution**: The algorithm ensures that the difference in scores between doctors is at most 0.5 points.

4. **Constraints**:
   - Each day should be assigned to only one doctor.
   - Ensure that no day is assigned to more than one doctor, and all days should be covered by the schedule.

## Algorithm Overview

1. **Initial Scheduling**:
   - Doctors are initially assigned days based on their availability and the point values for each day.
   - Ensure that no day is assigned to more than one doctor.

2. **Workload and Score Calculation**:
   - After initial assignment, calculate the total workload and score for each doctor.
   - Identify doctors with significant discrepancies in workload or score.

3. **Balancing**:
   - Adjust assignments to balance the workload and score among doctors.
   - Apply corrections if the score distribution exceeds the allowed maximum difference of 1 point.

4. **Correction**:
   - If the score distribution exceeds the allowed limit, iteratively adjust the schedule to bring the maximum difference within the acceptable range.

## Usage

1. **Initialize the Schedule**:
   - Define the number of doctors, the number of days, and the specific days with their point values (Saturdays, Sundays, National Days).

2. **Generate the Schedule**:
   - Use the algorithm to generate an initial schedule and ensure all constraints are met.

3. **Review and Adjust**:
   - Check the differences in workload and score.
   - Apply adjustments if necessary to ensure fairness and adherence to constraints.
# Contributing
If you have suggestions or improvements, feel free to submit a pull request or open an issue on the GitHub repository.
## Results 
**For 29 days: Among doctors numbered between 4 and 18, the score for almost all days is 1. Only 5 and 10 doctors receive a score of 1.5 on some days.**

**For 30 days: Among doctors numbered between 4 and 18, the score for most days is 1. However, 11 to 20 doctors receive a score of 1.5 on some days.**

**For 31 days: The score distribution is well-respected with a maximum difference of 0.5 to 1 point. Additionally, 20 doctors have days with a score of 1.5.**
