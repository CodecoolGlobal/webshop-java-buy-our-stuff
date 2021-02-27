package com.codecool.shop.dao.implementation.jdbc;


import java.sql.*;

public abstract class BaseJDBC {

    private static final String DATABASE = ""; //todo: add your database name
    private static final String USERNAME = ""; //todo: add your username
    private static final String PASSWORD = ""; //todo: add your password

    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
    }
}
