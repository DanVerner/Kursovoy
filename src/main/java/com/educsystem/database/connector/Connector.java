package com.educsystem.database.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Denis on 23.02.2017.
 */
public class Connector {
    private static final String USER = "root";//Логин пользователя
    private static final String PASSWORD = "";//Пароль пользователя
    private static final String URL = "jdbc:mysql://localhost:3306/proglearndb";//URL адрес
    private static final String DRIVER = "com.mysql.jdbc.Driver";//Имя драйвера

    public static Connection getDBConnection() throws SQLException {
        try {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
