package org.example.seminar2;

public class Person {
    private int age;
    private String name;

    public Person() {
        this.age = 25;
        this.name = "Name";
    }

    public void displayInfo(){
        System.out.printf("Имя: %s; Возраст: %d\n%n", name, age);
    }
}
