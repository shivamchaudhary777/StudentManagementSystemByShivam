package com.example.studentmanagement;

import java.util.Comparator;

// class to sort student by name
public class SortingByName implements Comparator<StudentItem> {
    @Override
    public int compare(StudentItem o1, StudentItem o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
