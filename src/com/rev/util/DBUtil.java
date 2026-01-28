package com.rev.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");

            return DriverManager.getConnection(
            	    "jdbc:oracle:thin:@localhost:1521:XE",
            	    "revuser",
            	    "rev123"
            	);
            

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}