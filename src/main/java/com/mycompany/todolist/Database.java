package com.mycompany.todolist;
import java.sql.*;
import java.io.*;
import java.net.URISyntaxException;

public class Database {
    private static final String DB_NAME = "tasks.db";

    public static Connection connect() {
        try {
            // Get the path where the JAR file is located
            File jarFile = new File(Database.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            String jarDir = jarFile.getParent();

            // Construct full path to the DB file
            String dbPath = jarDir + File.separator + DB_NAME;

            // Connect to SQLite using relative path
            return DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        } catch (URISyntaxException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS tasks ("
                   + "name TEXT PRIMARY KEY,"
                   + "endDate TEXT,"
                   + "endTime TEXT"
                   + ");";

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
