package com.ecodeup.jdbc;

import java.sql.*;

public class EjemploJDBC {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc?serverTimezone=UTC";
        String username= "root";
        String password= "root";

        try{
            Connection connectionv1 = DriverManager.getConnection(url,username,password);
            Statement statement = connectionv1.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM ESTUDIANTES");

            while(resultSet.next()){
                System.out.println(resultSet.getString("id_estudiante") + " | " +
                        resultSet.getString("nombre") + " | " + resultSet.getString("fechaNacimiento"));
            }
            connectionv1.close();
            statement.close();
            resultSet.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
