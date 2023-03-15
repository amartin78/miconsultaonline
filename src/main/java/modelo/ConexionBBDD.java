package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBBDD {
	
//	public static final String JDBC_URL = "jdbc:mysql://localhost:3306/consultaonline";
	public static final String JDBC_URL = "jdbc:mysql://us-cdbr-east-06.cleardb.net/heroku_4d7a76f054ad1ba?autoReconnect=true";
	public static Connection instance = null;
	
	public static Connection getConnection() throws SQLException {
		
		if(instance == null) {
			Properties props = new Properties();
			props.put("user", "b6df492cd9092f");
			props.put("password", "2c0f4d11");
//			props.put("user", "root");
//			props.put("password", "root");
			
			instance = DriverManager.getConnection(JDBC_URL, props);
		}
		return instance;
	}
}
