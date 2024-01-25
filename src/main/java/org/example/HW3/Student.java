package org.example.HW3;

import java.io.Serializable;

public class Student implements Serializable {
    //region Fields
    private final String name;
    private final int age;
    transient double gpa;
    //endregion
    //region Constructor
    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }
    //endregion


    //region Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGpa() {
        return gpa;
    }
    //endregion


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gpa=" + gpa +
                '}';
    }
}
