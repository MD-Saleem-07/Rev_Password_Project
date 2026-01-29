package com.rev.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    private static Connection con = null;

    public static Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                Class.forName("oracle.jdbc.driver.OracleDriver");

                con = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:xe",
                        "YOUR_DB_USERNAME",
                        "YOUR_DB_PASSWORD"
                );
            }
        } catch (Exception e) {
            System.out.println("❌ Database connection failed!");
            e.printStackTrace();
        }
        return con;
    }
}