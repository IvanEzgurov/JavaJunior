package org.example.HW4.hebirnateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.example.HW4.model.Course;

public class MainApp {
    public static void main(String[] args) {
        // Создание фабрики сессий
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // Создание сессии
        try(Session session = sessionFactory.getCurrentSession()){

            // Начало транзакции
            session.beginTransaction();

            // Создаем курс
            Course course = Course.create();

            // Сохраняем объект в базе данных
            session.save(course);
            System.out.println("Объект course сохранен успешно");

            // Чтение объекта из базы данных
            Course retrievedCourse = session.get(Course.class, course.getId());
            System.out.println("Объект course получен успешно");
            System.out.println("Полученный объект course: " + retrievedCourse);

            // Обновление объекта
            retrievedCourse.updateTitle();
            retrievedCourse.updateDuration();
            session.update(retrievedCourse);
            System.out.println("Объект course обновлен успешно");

            // Удаление объекта
//            session.delete(retrievedCourse);
//            System.out.println("Object student update successfully");

            // Коммит транзакции
            session.getTransaction().commit();
            System.out.println("Передача зафиксирована успешно");

        }
    }
}
