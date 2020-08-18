package day15ing.framework.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    public static Connection getConnection(){
        String url="jdbc:mysql://api.lemonban.com:3306/futureloan";
        String userName="future";
        String password="123456";
        Connection conn=null;
        try {
            conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
