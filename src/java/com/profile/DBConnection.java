/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.profile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author nikza
 */

public class DBConnection {
    // 1. Connection URL for Java DB (Derby)
    private static final String URL = "jdbc:derby://localhost:1527/student_profiles";
    // 2. Default username and password for NetBeans Java DB
    private static final String USER = "app";
    private static final String PASS = "app";

    public static Connection getConnection() {
        Connection con = null;
        try {
            // 3. Load the Derby Driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERROR: Could not connect to Database.");
            System.out.println("Check if Java DB Server is running in Services tab.");
            e.printStackTrace();
        }
        return con;
    }
}
