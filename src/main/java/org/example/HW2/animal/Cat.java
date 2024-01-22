package org.example.HW2.animal;

import org.example.HW2.interfaces.Runner;

public class Cat extends Animal implements Runner {
    private boolean hasScratchingPost;
    public Cat(String name, int age, boolean hasScratchingPost) {
        super(name, age);
        this.hasScratchingPost = hasScratchingPost;
    }

    @Override
    public void makeSound() {
        System.out.printf("%s says meow-meow\n", getName());
    }

    @Override
    public void run() {
        System.out.println("Can run");
    }
}
