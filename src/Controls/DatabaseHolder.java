/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Lorcha
 */
public class DatabaseHolder {
    private Connection conn;
    private Statement state;
    
    public Connection openConnection(){
        if(conn == null)
        {
            String url="jdbc:mysql://localhost/";
            String dbName="matchboxdatabase";
            String driver="com.mysql.cj.jdbc.Driver";
            String userName="root";
            String password="vbnet";
            try
            {
                Class.forName(driver);
                this.conn = DriverManager.getConnection(url+dbName,userName,password);
                System.out.println("CONNECTION SUCCESFUL!");
            }
            catch (ClassNotFoundException | SQLException sqle)
            {
                System.out.println("CONNECTION FAILED!");
            }
        }
     return conn;   
    }
}



