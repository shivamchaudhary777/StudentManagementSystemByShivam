package com.example.studentmanagement;

import java.util.Comparator;


// class to sort student by roll number
public class SortingByRollNumber implements Comparator<StudentItem> {
    @Override
    public int compare(StudentItem o1, StudentItem o2) {
        return Integer.toString(o1.getRollNumber()).compareTo(Integer.toString(o2.getRollNumber()));
    }
}
