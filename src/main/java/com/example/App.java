package com.example;

import java.sql.*;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String pass = "";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);

            // Statement statement = connection.createStatement();

            // String query = "select * from user";
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery();

            Method.displayResultInTable(resultSet);

            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
