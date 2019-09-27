 package cn.why.utils;
 
 import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class DBUtils {
	static final String driver="com.mysql.jdbc.Driver";
	static final String url ="jdbc:mysql://localhost:3360/message?characterEncoding=utf-8";
	static final String username="root";
	static final String password="15085409416";
	static Connection conn=null;
	public static Connection getConnection() throws SecurityException{
		try {
			if(conn==null||conn.isClosed())
			try{
				Class.forName(driver);
			   	conn = DriverManager.getConnection(url,username,password);
			} catch(Exception e){
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void close(PreparedStatement ps,Connection conn) {
		
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	}
	
}

