/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author tss
 */
public class App {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/classicmodels_luca","tss","ghiglieno");
        Statement cmd = conn.createStatement();
        ResultSet rs = cmd.executeQuery("select customerName, city, country from customers where country like 'France'");
        while(rs.next())
            System.out.println(String.format("nome = %s , città: %s , paese: %s ", rs.getString("customerName")
                    , rs.getString("city"), rs.getString("country")));
        rs.close();
        cmd.close();
        conn.close();
    }
    
}
