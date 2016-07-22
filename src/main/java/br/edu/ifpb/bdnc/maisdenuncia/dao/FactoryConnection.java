/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.maisdenuncia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author kieckegard
 */
public class FactoryConnection
{
    private static final String url = "jdbc:postgresql://localhost:5432/maisdenuncia";
    private static final String user = "postgres";
    private static final String pass = "123456";
    private static Connection conn;
    private static final String driver = "org.postgresql.Driver";
    
    public static Connection createConnection() throws ClassNotFoundException, SQLException{
        if(conn == null){
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,pass);
        }
        return conn;
    }
}
