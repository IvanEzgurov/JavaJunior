package org.example.HW4.model;

import javax.persistence.*;
import java.util.Random;
@Entity
@Table(name = "courses")

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;

    private int duration;

    private static final String[] titles = new String[]{"Математика", "Философия", "САПР", "Основы электротехники", "Черчение", "Иностранный язык", "История", "Экономика"};
    private final static Random random = new Random();


    public Course(int id, String title, int duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }
    public Course(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }
    public Course() {
    }

    public static  Course create(){
        return new Course(titles[random.nextInt(titles.length)], random.nextInt(2, 6));
    }

    public void updateDuration(){
        duration = random.nextInt(2, 6);
    }
    public void updateTitle(){
        title = titles[random.nextInt(titles.length)];
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }


    //region Getters & Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getId() {
        return id;
    }
//endregion
}
