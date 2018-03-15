package com.wordus.essentials;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLEssentials {

    public static void init() {
        Connection c = null;
        Statement stmt;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Base de donnee connecte");

            stmt = c.createStatement();
            String sql = "CREATE TABLE COMPANY " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " AGE            INT     NOT NULL, " +
                    " ADDRESS        CHAR(50), " +
                    " SALARY         REAL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            System.exit(0);
            e.printStackTrace();
        }
        System.out.println("Table creer avec success");
    }

}
