package singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBBDD {
	
	// public static final String JDBC_URL = "jdbc:mysql://localhost:3306/consultaonline";
	public static final String JDBC_URL = "jdbc:mysql://eu-cdbr-west-03.cleardb.net/heroku_40029d4180b186d?autoReconnect=true";
	public static Connection instance = null;
	
	public static Connection getConnection() throws SQLException {
		
		if(instance == null) {
			Properties props = new Properties();
			props.put("user", "ba840f90deec6a");
			props.put("password", "bfefe5fd");
//			props.put("user", "root");
//			props.put("password", "root");
			
			instance = DriverManager.getConnection(JDBC_URL, props);
		}
		System.out.println("connected");
		return instance;
	}
}
