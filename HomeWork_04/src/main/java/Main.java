import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Создайте базу данных (например, SchoolDB).
 * В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
 * Настройте Hibernate для работы с вашей базой данных.
 * Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
 * Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
 * Убедитесь, что каждая операция выполняется в отдельной транзакции.
 */
import java.sql.*;
public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "password";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            // Создаем базу данных
            createDatabase(connection);
            // Выбираем БД с которой будем работать
            useDatabase(connection);
            // Создаем таблицу в БД
            createTable(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        Course course1 = new Course("OOP", "8 hours");
        Course course2 = new Course("MD", "2 hours");
        Course course3 = new Course("MD", "4 hours");

//        try{
//            session.beginTransaction();
//
//            Course course1 = new Course("Nets", "8 hours");
//            System.out.println(course1);
//
//            /**
//             * Вставка данных в таблицу
//             */
//            session.save(course1);
//            System.out.println("Course save successfully");
//            /**
//             * Чтение из базы данных
//             */
//            Course retrievedCourse = session.get(Course.class, course1.getId());
//            System.out.println("Retrieved course is " + retrievedCourse);
//            System.out.println("Read course successfully");
//            /**
//             * Обновление данных в БД
//             */
//            retrievedCourse.setTitle("Java");
//            retrievedCourse.setDuration("48 hours");
//            session.update(retrievedCourse);
//            System.out.println("Update course successfully");
//            /**
//             * Удаление из БД
//             */
//            Course course2 = new Course("SQL", "16 hours");
//            Course course3 = new Course("Python", "16 hours");
//            session.save(course2);
//            session.save(course3);
//
//            session.delete(course2);
//            System.out.println("Course delete successfully");
//            session.getTransaction().commit();
//            System.out.println("Application close");
//        } finally {
//            session.close();
//        }
        /**
         * Добавление объекта в БД
         */
        try {
            session.beginTransaction();
            session.save(course1);
            session.save(course2);
            session.save(course3);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        /**
         * Чтение объекта в БД
         */
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Course retrievedCourse = session.get(Course.class, course1.getId());
            System.out.println("Retrieved course: " + retrievedCourse);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        /**
         * Обновление объекта в БД
         */
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Course retrievedCourse = session.get(Course.class, course1.getId());
            retrievedCourse.setTitle("Exception Java");
            retrievedCourse.setDuration("16 hours");
            session.update(retrievedCourse);
            System.out.println("Update course: " + retrievedCourse);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        /**
         * Чтение объекта в БД
         */
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.delete(course2);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }
    //region Методы создание БД и таблицы
    private static void createDatabase(Connection connection) throws SQLException {
        String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS schoolDB;";
        try (PreparedStatement statement = connection.prepareStatement(createDatabaseSQL)){
            statement.execute();
        }
    }
    private static void useDatabase(Connection connection) throws SQLException{
        String userDatabaseSQL = "USE schoolDB;";
        try (PreparedStatement statement = connection.prepareStatement(userDatabaseSQL)){
            statement.execute();
        }
    }
    private static void createTable(Connection connection) throws SQLException{
        String createTableSQL = "CREATE TABLE IF NOT EXISTS courses (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(45), duration VARCHAR(45));";
        try (PreparedStatement statement = connection.prepareStatement(createTableSQL)){
            statement.execute();
        }
    }
    //endregion
}
