package com.amponsem.librarymanagementsystem.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DATABASE_NAME = "librarydb";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1/" + DATABASE_NAME;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "micky!!@@020";

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }
}
