package com.rev.util;

import java.sql.Connection;

public class TestDB {
    public static void main(String[] args) {
        Connection con = DBUtil.getConnection();

        if (con != null)
            System.out.println("DATABASE CONNECTED SUCCESSFULLY");
        else
            System.out.println("DATABASE CONNECTION FAILED");
    }
}