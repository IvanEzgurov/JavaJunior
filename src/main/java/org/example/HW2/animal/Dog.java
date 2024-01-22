package org.example.HW2.animal;

public class Dog extends Animal{
    private String dogHouse;

    public Dog(String name, int age) {
        super(name, age);
        this.dogHouse = "KONURA";
    }

    @Override
    public void makeSound() {
        System.out.printf("%s says gav-gav\n", getName());
    }
}
