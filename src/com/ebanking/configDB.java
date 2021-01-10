/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking;

import java.sql.*;

/**
 *
 * @author alimi
 */

public class configDB {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/banking_system";
    static final String UNAME = "root";
    static final String PASS = "";
            
    public static Connection configure(){
        
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, UNAME, PASS);
        } catch(ClassNotFoundException | SQLException e){
            System.out.println("Gagal koneksi");
        }
        return conn;
    }
}
