package com.ecodeup.jdbc;

import java.sql.*;

public class ConexionBD {
    private static Connection connection;

    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=UTC";
        String username = "root";
        String password = "root";

        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

