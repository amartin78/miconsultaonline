package singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import modelo.Paciente;

public class ConexionBBDD {
	
//	public static final String JDBC_URL = "jdbc:mysql://localhost:3306/consultaonline";
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
		
		return instance;
	}
	
	public static void insertarDatos(int idPacienteSesionActiva) throws SQLException {
		
		PreparedStatement ps1 = getConnection().prepareStatement(
				"INSERT INTO anomalia (nombre, sintoma, facultativo, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Esguince de tobillo', 'Dolor moderado y hematoma', 'Dr. Arturo Rodríguez', '20120929', " + idPacienteSesionActiva + ")" +  
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM anomalia WHERE nombre='Esguince de tobillo' and paciente_id=" + idPacienteSesionActiva + ") LIMIT 1;"
				);
		ps1.executeUpdate();
		ps1.close();

		PreparedStatement ps2 = getConnection().prepareStatement(
				"INSERT INTO anomalia (nombre, sintoma, facultativo, fecha, paciente_id) " +
				  "SELECT * FROM (SELECT 'Gripe', 'Estornudos y malestar general', 'Dr. Carlos Rodríguez', '20150104', " + idPacienteSesionActiva + ")" + 
				  "as tmp WHERE NOT EXISTS (SELECT nombre FROM anomalia WHERE nombre='Gripe' and paciente_id=" + idPacienteSesionActiva + ") LIMIT 1;");
		ps2.executeUpdate();
		ps2.close();
		
		PreparedStatement ps3 = getConnection().prepareStatement(
				"INSERT INTO anomalia (nombre, sintoma, facultativo, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Otitis', 'Inflamación y dolor en los oídos', 'Dr. Arturo Rodríguez', '20120921','8')" +  
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM anomalia WHERE nombre='Otitis' and paciente_id='8') LIMIT 1;"
				);
		ps3.executeUpdate();
		ps3.close();
		
		PreparedStatement ps4 = getConnection().prepareStatement(
				"INSERT INTO alergia (nombre, frecuencia, test_realizado, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Pólen', 'Primavera', 'PPC', '20080904','8')" +  
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM alergia WHERE nombre='Pólen' and paciente_id='8') LIMIT 1;"
				);
		ps4.executeUpdate();
		ps4.close();
		
		PreparedStatement ps5 = getConnection().prepareStatement(
				"INSERT INTO alergia (nombre, frecuencia, test_realizado, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Ácaros', 'Otoño', 'IgE', '20100312','9')" +  
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM alergia WHERE nombre='Ácaros' and paciente_id='9') LIMIT 1;"
				);
		ps5.executeUpdate();
		ps5.close();
		
		PreparedStatement ps6 = getConnection().prepareStatement(
				"INSERT INTO alergia (nombre, frecuencia, test_realizado, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Moho', 'Todo el año', 'PPC', '20140801','8')" +  
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM alergia WHERE nombre='Moho' and paciente_id='8') LIMIT 1;"
				);
		ps6.executeUpdate();
		ps6.close();

		PreparedStatement ps7 = getConnection().prepareStatement(
				"INSERT INTO vacuna (nombre, laboratorio, lote, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Sarampión', 'Sanofi Pasteur', 'LZ3590', '20010308','8')" +  
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM vacuna WHERE nombre='Sarampión' and paciente_id='8') LIMIT 1;"
				);
		ps7.executeUpdate();
		ps7.close();
		
		PreparedStatement ps8 = getConnection().prepareStatement(
				"INSERT INTO vacuna (nombre, laboratorio, lote, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Polio', 'GSK', 'WT9817', '20020601','8')" +  
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM vacuna WHERE nombre='Polio' and paciente_id='8') LIMIT 1;"
				);
		ps8.executeUpdate();
		ps8.close();
	
		PreparedStatement ps9 = getConnection().prepareStatement(
				"INSERT INTO vacuna (nombre, laboratorio, lote, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Covid-19', 'Pfizer', 'EC5384', '20211202','9')" +  
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM vacuna WHERE nombre='Covid-19' and paciente_id='9') LIMIT 1;"
				);
		ps9.executeUpdate();
		ps9.close();

		PreparedStatement ps10 = getConnection().prepareStatement(
				"INSERT INTO paciente (nombre, apellidos, fec_nacimiento, email, password) " + 
				"SELECT * FROM (SELECT 'Mario', 'García Martín', '19920904', 'mario@hotmail.com','Mario2023*')" +  
				"as tmp WHERE NOT EXISTS (SELECT email FROM paciente WHERE email='mario@hotmail.com') LIMIT 1;"
				);
		ps10.executeUpdate();
		ps10.close();
	}
}
