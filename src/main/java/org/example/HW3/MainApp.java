package org.example.HW3;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;

public class MainApp {
    public static void main(String[] args) throws IOException {
        Student student = new Student("Igorek", 27, 4.5);
        System.out.println(student);
        String pathFile = "JavaJunior\\src\\main\\resources\\userdata.bin";

        writeObjectToFile(pathFile, student);
        System.out.println("Объект Student сериализован.");

        Student student1 = readObjectFromFile(pathFile);
        if(student1 != null){
            System.out.println("\nОбъект Student десериализован.");
            System.out.println("\tИмя: " + student1.getName());
            System.out.println("\tВозраст: " + student1.getAge());
            System.out.println("\tСредний балл (должен быть null, так как transiant): " + student1.getGpa());

        }
    }

    private static Student readObjectFromFile(String pathFile) {
        Student student1 = null;
        try(FileInputStream inFile = new FileInputStream(pathFile)){
            ObjectInputStream inObject = new ObjectInputStream(inFile);
            student1 = (Student)inObject.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        return student1;
    }

    private static void writeObjectToFile(String pathFile, Student student) {
        try(FileOutputStream outFile = new FileOutputStream(pathFile)){
            ObjectOutputStream outObject = new ObjectOutputStream(outFile);
            outObject.writeObject(student);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
