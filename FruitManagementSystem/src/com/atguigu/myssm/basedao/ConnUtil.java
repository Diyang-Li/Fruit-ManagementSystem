package com.atguigu.myssm.basedao;

import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TreeMap;

/** Util to provice connection to database, and set, get connection from ThreadLocal
 * @author Diyang Li
 * @create 2022-04-18 9:48 AM
 */
public class ConnUtil {
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver" ;
    public static final String URL = "jdbc:mysql://localhost:3306/fruitdb";
    public static final String USER = "root";
    public static final String PWD = "lulu" ;

    /**
     * Create connection
     * @return Connecion
     */
    public static Connection createConn(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get connection from ThreadLocal
     * @return Connection
     */
    public static Connection getConn(){
        Connection conn = threadLocal.get();
        if (conn == null){
            conn = createConn();
            threadLocal.set(conn);
        }
        //如果threadLocal没有connection就createConn，如果有connection，那就直接调取
        return threadLocal.get();
    }

    /**
     * Close Connection
     * @throws SQLException
     */
    public static void closeConne() throws SQLException {
        Connection conn = threadLocal.get();
        if (conn == null){
            return;
        }
        if (!conn.isClosed()){
            conn.close();
//            threadLocal.set(null);
            threadLocal.remove();
        }
    }
}
