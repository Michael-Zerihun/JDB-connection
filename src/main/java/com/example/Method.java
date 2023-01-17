package com.example;

import java.sql.*;

public class Method {

    public static String[][] resultSetToArray(ResultSet resultSet) {
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            // resultSet.beforeFirst();
            boolean arrive = resultSet.last();
            int row = resultSet.getRow();
            int col = metaData.getColumnCount();
            String[][] arrayStrings = new String[row][col];
            resultSet.beforeFirst();
            int x = 0;
            while (resultSet.next()) {
                for (int i = 0; i < col; i++) {
                    if (x != 0) {
                        arrayStrings[x][i] = resultSet.getString(i + 1);
                    } else {
                        arrayStrings[x][i] = metaData.getColumnName(i + 1);
                    }
                }
                x++;
            }
            return arrayStrings;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static int[] longestColString(String[][] arr) {
        int[] col = new int[arr[0].length];
        for (String[] rows : arr) {
            for (int i = 0; i < rows.length; i++) {
                if (rows[i].length() > col[i]) {
                    col[i] = rows[i].length();
                }
            }
        }
        return col;
    }

    public static void displayResultInTable(ResultSet resultSet) {
        try {
            String[][] arrayStrings = resultSetToArray(resultSet);
            int[] tableSize = longestColString(arrayStrings);

            String tableFrame = "+-";
            for (int i : tableSize) {
                tableFrame += "-".repeat(i + 2) + "-+";
            }
            resultSet.beforeFirst();
            System.out.println(tableFrame);
            while (resultSet.next()) {
                String text = "| ";
                for (int index = 0; index < tableSize.length; index++) {
                    text += " " + resultSet.getString(index + 1);
                    text += " ".repeat(tableSize[index] - resultSet.getString(index + 1).length() + 1) + " |";
                }
                System.out.println(text);
                System.out.println(tableFrame);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
