package com.example.demo1.dbSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDB {

    private String connectionString = "jdbc:mysql://localhost/persondb";
    private String user = "persondbuser";
    private String password = "passw0rd";
    private Connection con;

    public MariaDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(connectionString, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
