/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Khangnn.DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author khang
 */
public class DBHelper {
    public static Connection makeConnection () throws NamingException, SQLException{
                   
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            String url = "jdbc:sqlserver://localhost:1433;databaseName=WorkShop1";
//            con = DriverManager.getConnection(url, "sa", "2582000");
//            return con;
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
            Context context = new InitialContext();
            Context end = (Context) context.lookup("java:comp/env");
            DataSource ds = (DataSource) end.lookup("DBCon");
            Connection conn = ds.getConnection();    
            return conn;
    }
}
