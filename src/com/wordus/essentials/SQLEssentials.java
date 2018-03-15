package com.wordus.essentials;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLEssentials {

    public static Connection init() {

        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":" +  e.getMessage());
            System.exit(0);
        }
        System.out.println("Base de donnee creer");
        return c;
    }

    public void createTable() {

    }

}
