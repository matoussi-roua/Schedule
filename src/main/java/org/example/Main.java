package org.example;

import Doctor.Doctor;
import lombok.extern.slf4j.XSlf4j;
import schedule.Schedule;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        List<Integer> sat = new ArrayList<>();
        sat.add(6);
        sat.add(13);
        sat.add(20);
        sat.add(27);
        List<Integer> sun = new ArrayList<>();
        sun.add(7);
        sun.add(14);
        sun.add(21);
        sun.add(28);
        List<Integer> nat = new ArrayList<>();
        nat.add(6);
        nat.add(12);
        nat.add(21);
        nat.add(24);
        Map<Integer, Double> map = new HashMap<>();
        Schedule schedule = new Schedule(8, 29, sat, sun, nat, map) {
        };

        System.out.println(schedule.generateSchedule());

        System.out.println(schedule.toString());
        for(int l=1; l<=30;l++){
            System.out.println("/////////////////////////////////////");
            System.out.println("toltal doctor: "+l);
            Schedule schedule2 = new Schedule(l, 31, sat, sun, nat, map);
            System.out.println(schedule2.generateSchedule());

        }
        System.out.println(
                "for 29 days almost all score=1 between 4 and 18 doctors just 1.5 for 5/10 doctors/n" +
                "for 30 days between 4 and 18 doctors just 1.5 for 11/19/20 doctors/n" +
                "for 31 days between 4 and 19 score distribution is respected (0.5/1) and 1.5 for 20 doctors");


    }


}