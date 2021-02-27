package com.codecool.shop.dao.implementation.jdbc;


import java.sql.*;

public abstract class BaseJDBC {

    private static final String DATABASE = "codecoolshop";
    private static final String USERNAME = "hamargyuri";
    private static final String PASSWORD = "adat";

    Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
        System.out.println("connected to db");
        return connection;
    }
}
