/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author tss
 */
public class App {

    private static final String DB_URL = "jdbc:mariadb://localhost:3306/classicmodels";
    private static final String DB_USR = "tss";
    private static final String DB_PWD = "ghiglieno";
    private static final String SQL_QUERY = "select customerName, city, state from customers where state like 'NY'";

    public static void main(String[] args) {

        try (
                 Connection cn = DriverManager
                        .getConnection(App.DB_URL, App.DB_USR, App.DB_PWD);  
                Statement cmd = cn.createStatement();  
                ResultSet rs = cmd.executeQuery(App.SQL_QUERY);  
                PreparedStatement insert = cn.prepareStatement("insert into productlines (productLine,textDescription ) values (?, ?)");
                PreparedStatement delete = cn.prepareStatement("delete from productlines where productLine = ?");
                ) {
                    try {
                        cn.setAutoCommit(false);
                        for (int i = 1; i <= 1000; i++) {
                            String key = "prova" + i;
                            //insert.setString(1, key);
                            //insert.setString(2, "prova insert");
                            //insert.executeUpdate();
                            
                            delete.setString(1, key);
                            delete.executeUpdate();
                            
                            //cmd.executeUpdate("insert into productlines (productLine,textDescription ) values ('" + key + "', 'prova insert')");
                            //cmd.executeUpdate("delete from productlines where productLine = '" + key + "'");
                        }
                        
                        cn.commit();
                    } catch (SQLException e) {
                        cn.rollback();
                        System.out.println(e.getMessage());
                    }
                    while (rs.next()) {
                        System.out.println(String.format("nome: %s , citta: %s, stato: %s",
                                rs.getString("customerName"), rs.getString("city"), rs.getString("state")));
                    }

                } catch (SQLException e) {

                    System.out.println(e.getMessage());
                }
    }
}
