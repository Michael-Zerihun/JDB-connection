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

            Statement statement = connection.createStatement();

            String query = "select * from user";
            ResultSet resultSet = statement.executeQuery(query);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int colCount = metaData.getColumnCount();

            for (int i = 1; i <= colCount; i++) {
                System.out.print(metaData.getColumnName(i) + "  ");
            }
            System.out.println("");
            while (resultSet.next()) {
                for (int j = 0; j < colCount; j++) {
                    System.out.print(resultSet.getString(j + 1) + "   ");
                }
                System.out.println();
            }

            System.out.println(colCount);
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
