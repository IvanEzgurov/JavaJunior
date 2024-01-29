package org.example.HW4.jbdc;

import org.example.HW4.model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Main {
    private final static Random random  = new Random();
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "password";



        try {
            // Подключение к базе данных
            Connection connection = DriverManager.getConnection(url, user, password);

            // Создание базы данных
            createConnection(connection);
            System.out.println("Создание БД успешно");

            // Использование базы данных
            useDatabase(connection);
            System.out.println("Использование базы данных успешно");

            // Создание таблицы БД
            createTable(connection);
            System.out.println("Таблица создана успешно");

            // Вставка данных

            int count = random.nextInt(4, 8);

            for (int i = 0; i < count; i++) {
                insertData(connection, Course.create());
            }
            System.out.println("Вставка данных прошла успешно");


            // Чтение данных
            Collection<Course> courses = readDatabase(connection);
            for (var course : courses) {
                System.out.println(course);
            }
            System.out.println("Чтение данных выполнено успешно");

            // Обновление данных
            for (var course : courses){
                course.updateTitle();
                course.updateDuration();
                updateDatabase(connection, course);
            }
            System.out.println("Обновление данный выполнено успешно");


            // Удаление данных
//            for (var course: courses){
//                deleteData(connection, course.getId());
//            }
//            System.out.println("Delete data successfully");

            // Закрытие соединения
            connection.close();
            System.out.println("Соединение с БД закрыто успешно");


        } catch (SQLException e){e.printStackTrace();}
    }
    private static void createConnection(Connection connection) throws SQLException {
        String createDataBaseSQL = "CREATE DATABASE IF NOT EXISTS coursesDB";
        PreparedStatement statement = connection.prepareStatement(createDataBaseSQL);
        statement.execute();

    }
    private static void useDatabase(Connection connection) throws SQLException {
        String useDatabaseSQL = "USE coursesDB";
        try(PreparedStatement statement = connection.prepareStatement(useDatabaseSQL)){
            statement.execute();
        }
    }

    private static void createTable(Connection connection) throws SQLException{
        String createTableSQL = "CREATE TABLE IF NOT EXISTS courses (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(255), duration INT)";
        try(PreparedStatement statement = connection.prepareStatement(createTableSQL)){
            statement.execute();
        }
    }

    private static void insertData(Connection connection, Course course) throws SQLException{
        String inserDataSQL = "INSERT INTO courses (title, duration) VALUES (?, ?);";
        try(PreparedStatement statement = connection.prepareStatement(inserDataSQL)){
            statement.setString(1, course.getTitle());
            statement.setInt(2, course.getDuration());
            statement.executeUpdate();
        }
    }


    private static Collection<Course> readDatabase(Connection connection) throws SQLException{
        ArrayList<Course> coursesList = new ArrayList<>();
        String readDatabaseSQL = "SELECT * FROM courses;";
        try(PreparedStatement statement = connection.prepareStatement(readDatabaseSQL)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String title = resultSet.getNString("title");
                int duration = resultSet.getInt("duration");
                coursesList.add(new Course(id, title, duration));
            }
            return coursesList;
        }
    }

    private static void updateDatabase(Connection connection, Course course)throws SQLException{
        String  updatabaseSQL = "UPDATE courses SET title=?, duration=? WHERE id=?;";
        try(PreparedStatement statement = connection.prepareStatement(updatabaseSQL)){
            statement.setString(1, course.getTitle());
            statement.setInt(2, course.getDuration());
            statement.setInt(3, course.getId());
            statement.executeUpdate();
        }
    }

    private static void deleteData(Connection connection, int id) throws SQLException{
        String deleteDataSQL = "DELETE FROM courses WHERE id=?;";
        try(PreparedStatement statement = connection.prepareStatement(deleteDataSQL)){
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}
