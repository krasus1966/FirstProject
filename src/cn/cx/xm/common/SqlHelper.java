package cn.cx.xm.common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SqlHelper {
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("请先加载数据库驱动");
        }
    }
    public static Connection getConnection() throws IOException, SQLException {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("dbconfig.properties");
        Properties properties = new Properties();
        properties.load(in);
        String url=properties.getProperty("url");
        String username = properties.getProperty("username");
        String pwd = properties.getProperty("pwd");
        Connection conn= DriverManager.getConnection(url,username,pwd);
        return conn;
    }
    public static void closeConnection(Connection conn) throws SQLException {
        if(conn!=null){
            conn.close();
        }
    }
}
