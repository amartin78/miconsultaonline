package singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBBDD {
	
	// public static final String JDBC_URL = "jdbc:mysql://localhost:3306/consultaonline?characterEncoding=utf8";
	public static final String JDBC_URL = "jdbc:mysql://eu-cdbr-west-03.cleardb.net/heroku_40029d4180b186d?autoReconnect=true&characterEncoding=utf8";
	public static Connection instance = null;
	
	public static Connection getConnection() throws SQLException {
		
		if(instance == null) {
			Properties props = new Properties();
			props.put("user", "ba840f90deec6a");
			props.put("password", "bfefe5fd");
//			props.put("user", "root");
//			props.put("password", "root");
			
			instance = DriverManager.getConnection(JDBC_URL, props);
			insertarDatos();
		}
		
		return instance;
	}
	
	public static void insertarDatos() throws SQLException {
		
		PreparedStatement ps1 = getConnection().prepareStatement(
				"INSERT INTO anomalia (nombre, sintoma, facultativo, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Esguince de tobillo', 'Dolor moderado y hematoma', 'Dr. Arturo Rodríguez', '20120929','8')" +  
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM anomalia WHERE nombre='Esguince de tobillo' and paciente_id='8') LIMIT 1;"
				);
		ps1.executeUpdate();
		ps1.close();
		
		PreparedStatement ps2 = getConnection().prepareStatement(
				"INSERT INTO anomalia (nombre, sintoma, facultativo, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Otitis', 'Inflamación y dolor en los oídos', 'Dr. Arturo Rodríguez', '20120921','8')" +  
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM anomalia WHERE nombre='Otitis' and paciente_id='8') LIMIT 1;"
				);
		ps2.executeUpdate();
		ps2.close();
		
//		PreparedStatement ps = getConnection().prepareStatement("INSERT INTO anomalia "
//				  + "(anomalia, sintoma, facultativo, fecha, paciente_id) "
//				  + "VALUES ('Otitis2', 'Inflamación y dolor en los oídos', 'Dr. Arturo Rodríguez', '20120921','8')");
//		ps.executeUpdate();
//		ps.close();
		
		PreparedStatement ps3 = getConnection().prepareStatement(
				"INSERT INTO alergia (nombre, frecuencia, test_realizado, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Polen', 'Primavera', 'PPC', '20080904','8')" +  
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM alergia WHERE nombre='Polen' and paciente_id='8') LIMIT 1;"
				);
		ps3.executeUpdate();
		ps3.close();
		
		PreparedStatement ps4 = getConnection().prepareStatement(
				"INSERT INTO alergia (nombre, frecuencia, test_realizado, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Ácaros', 'Otoño', 'IgE', '20100312','8')" +  
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM alergia WHERE nombre='Ácaros' and paciente_id='8') LIMIT 1;"
				);
		ps4.executeUpdate();
		ps4.close();

		PreparedStatement ps5 = getConnection().prepareStatement(
				"INSERT INTO paciente (nombre, apellidos, fec_nacimiento, email, password) " + 
				"SELECT * FROM (SELECT 'Mario', 'García Martín', '19920904', 'mario@hotmail.com','Mario2023*')" +  
				"as tmp WHERE NOT EXISTS (SELECT email FROM paciente WHERE email='mario@hotmail.com') LIMIT 1;"
				);
		ps5.executeUpdate();
		ps5.close();
		
	}
}
