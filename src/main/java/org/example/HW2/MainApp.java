package org.example.HW2;

import org.example.HW2.animal.Animal;
import org.example.HW2.animal.Cat;
import org.example.HW2.animal.Dog;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        List<Animal> animalList = new ArrayList<>();
        Animal dog = new Dog("Tuzik", 2);
//        System.out.println(dog);
        animalList.add(new Cat("Myrka", 1, true));
        animalList.add(dog);

        for (Animal animal : animalList) {
            Class<?> clazz = Class.forName(animal.getClass().getName());
            System.out.println(animal.getClass().getSimpleName() + ":");
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                System.out.println("\tПоле: " + field.getName());
            }

            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if(method.getName().equals("makeSound")) {
                    System.out.println("\t" + method.getName());
                    System.out.print("\t");
                    method.invoke(animal);
                }
            }
        }
    }
}
