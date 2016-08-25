/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author win 7
 */
public class DBConnection {
    
    public static Connection con=null;
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
    
    Class.forName("com.mysql.jdbc.Driver");
    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sesame","root","root123");
    
    return con;
    }
    
}
