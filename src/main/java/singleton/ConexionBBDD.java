package singleton;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import modelo.Analisis;

public class ConexionBBDD {
	
//	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/consultaonline";
	private static final String JDBC_URL = "jdbc:mysql://eu-cluster-west-01.k8s.cleardb.net/heroku_01d8c9df605ed4c?autoReconnect=true";
	private static Connection instance = null;
	
	private ConexionBBDD() {
		
	}
	
 	public static Connection getConnection() throws SQLException {
 		
		if(instance == null) {
			Properties props = new Properties();
			props.put("user", "bead67a2208397");
			props.put("password", "4b0fff8e");
//			props.put("user", "root");
//			props.put("password", "root");
			
			instance = DriverManager.getConnection(JDBC_URL, props);
		}
		return instance;
	}
	
	public static void insertarDatosHistoriaClinica(int idPacienteSesionActiva) throws SQLException {
		
		PreparedStatement ps1 = getConnection().prepareStatement(
				"INSERT INTO anomalia (nombre, sintoma, facultativo, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Esguince de tobillo', 'Dolor moderado y hematoma', 'Dr. Arturo Rodríguez', '20220929', " + idPacienteSesionActiva + ")" +  
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
				"SELECT * FROM (SELECT 'Otitis', 'Inflamación y dolor en los oídos', 'Dr. Arturo Rodríguez', '20120921'," + idPacienteSesionActiva + ")" +  
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM anomalia WHERE nombre='Otitis' and paciente_id=" + idPacienteSesionActiva + ") LIMIT 1;"
				);
		ps3.executeUpdate();
		ps3.close();
		
		PreparedStatement ps4 = getConnection().prepareStatement(
				"INSERT INTO alergia (nombre, frecuencia, test_realizado, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Pólen', 'Primavera', 'PPC', '20080904'," + idPacienteSesionActiva + ")" +  
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM alergia WHERE nombre='Pólen' and paciente_id=" + idPacienteSesionActiva + ") LIMIT 1;"
				);
		ps4.executeUpdate();
		ps4.close();
		
		PreparedStatement ps5 = getConnection().prepareStatement(
				"INSERT INTO alergia (nombre, frecuencia, test_realizado, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Ácaros', 'Otoño', 'IgE', '20100312'," + idPacienteSesionActiva + ")" +  
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM alergia WHERE nombre='Ácaros' and paciente_id=" + idPacienteSesionActiva + ") LIMIT 1;"
				);
		ps5.executeUpdate();
		ps5.close();
		
		PreparedStatement ps6 = getConnection().prepareStatement(
				"INSERT INTO alergia (nombre, frecuencia, test_realizado, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Moho', 'Todo el año', 'PPC', '20140801'," + idPacienteSesionActiva + ")" +  
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM alergia WHERE nombre='Moho' and paciente_id=" + idPacienteSesionActiva + ") LIMIT 1;"
				);
		ps6.executeUpdate();
		ps6.close();

		PreparedStatement ps7 = getConnection().prepareStatement(
				"INSERT INTO vacuna (nombre, laboratorio, lote, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Sarampión', 'Sanofi Pasteur', 'LZ3590', '20010308'," + idPacienteSesionActiva + ")" +  
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM vacuna WHERE nombre='Sarampión' and paciente_id=" + idPacienteSesionActiva + ") LIMIT 1;"
				);
		ps7.executeUpdate();
		ps7.close();
		
		PreparedStatement ps8 = getConnection().prepareStatement(
				"INSERT INTO vacuna (nombre, laboratorio, lote, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Polio', 'GSK', 'WT9817', '20020601'," + idPacienteSesionActiva + ")" +  
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM vacuna WHERE nombre='Polio' and paciente_id=" + idPacienteSesionActiva + ") LIMIT 1;"
				);
		ps8.executeUpdate();
		ps8.close();
	
		PreparedStatement ps9 = getConnection().prepareStatement(
				"INSERT INTO vacuna (nombre, laboratorio, lote, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Covid-19', 'Pfizer', 'EC5384', '20211202'," + idPacienteSesionActiva + ")" +  
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM vacuna WHERE nombre='Covid-19' and paciente_id=" + idPacienteSesionActiva + ") LIMIT 1;"
				);
		ps9.executeUpdate();
		ps9.close();
	}
	
	public static void insertarDatosAnalisis(int idPacienteSesionActiva) throws SQLException {
		
		PreparedStatement ps1 = getConnection().prepareStatement(
				"INSERT INTO analisis (nombre, estado, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Test Vitaminas', 'Test realizado', '19920822'," + idPacienteSesionActiva + ")" + 
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM analisis WHERE nombre='Test Vitaminas' and paciente_id=" + idPacienteSesionActiva + ") LIMIT 1;"
				);
		ps1.executeUpdate();
		ps1.close();
		
		PreparedStatement ps2 = getConnection().prepareStatement(
				"INSERT INTO analisis (nombre, estado, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Test Colesterol', 'Test realizado', '19981206'," + idPacienteSesionActiva + ")" + 
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM analisis WHERE nombre='Test Colesterol' and paciente_id=" + idPacienteSesionActiva + ") LIMIT 1;"
				);
		ps2.executeUpdate();
		ps2.close();
	
		PreparedStatement ps3 = getConnection().prepareStatement(
				"INSERT INTO analisis (nombre, estado, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Test Hepático', 'Muestra en laboratorio', '20230209'," + idPacienteSesionActiva + ")" + 
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM analisis WHERE nombre='Test Hepático' and paciente_id=" + idPacienteSesionActiva + ") LIMIT 1;"
				);
		ps3.executeUpdate();
		ps3.close();
	}
	
	public static void insertarDatosMarcador(int idPacienteSesionActiva) throws SQLException {
		
		// Para cada analítica del paciente se insertan una serie de marcadores (ej. para colesterol: bilirrubina, ALP, etc.)
		PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM analisis WHERE paciente_id=?");
		ps.setInt(1, idPacienteSesionActiva);
		ResultSet rs = ps.executeQuery();
		ArrayList<Analisis> analisisRes = null;
		
		while(rs.next()) {
			if(analisisRes == null) {
				analisisRes = new ArrayList<>();
			}
			
			String analisis = rs.getString("nombre"); // Tipo de analítica (colesterol, vitaminas, etc.)
			String analisis_id = rs.getString("analisis_id");
			
			if(analisis.equals("Test Colesterol")) {
				
				PreparedStatement ps1 = getConnection().prepareStatement(
						"INSERT INTO analisis_marcador (nombre, categoria, valor, valor_minimo, valor_maximo, resultado, analisis_id) " + 
						"SELECT * FROM (SELECT 'LDL', 'Test Colesterol', '2.85', '1', '3', 'Normal'," + analisis_id + ")" + 
						"as tmp WHERE NOT EXISTS (SELECT nombre FROM analisis_marcador WHERE nombre='LDL' and analisis_id=" + analisis_id + ") LIMIT 1;"
						);
				ps1.executeUpdate();
				ps1.close();
				
				PreparedStatement ps2 = getConnection().prepareStatement(
						"INSERT INTO analisis_marcador (nombre, categoria, valor, valor_minimo, valor_maximo, resultado, analisis_id) " + 
						"SELECT * FROM (SELECT 'HDL', 'Test Colesterol', '4.38', '4.5', '8.5', 'Anormal'," + analisis_id + ")" + 
						"as tmp WHERE NOT EXISTS (SELECT nombre FROM analisis_marcador WHERE nombre='HDL' and analisis_id=" + analisis_id + ") LIMIT 1;"
						);
				ps2.executeUpdate();
				ps2.close();
			
				PreparedStatement ps3 = getConnection().prepareStatement(
						"INSERT INTO analisis_marcador (nombre, categoria, valor, valor_minimo, valor_maximo, resultado, analisis_id) " + 
						"SELECT * FROM (SELECT 'Triglicéridos', 'Test Colesterol', '0.92', '0.25', '2.65', 'Normal'," + analisis_id + ")" + 
						"as tmp WHERE NOT EXISTS (SELECT nombre FROM analisis_marcador WHERE nombre='Triglicéridos' and analisis_id=" + analisis_id + ") LIMIT 1;"
						);
				ps3.executeUpdate();
				ps3.close();
				
			} else if(analisis.equals("Test Vitaminas")) {
				
				PreparedStatement ps4 = getConnection().prepareStatement(
						"INSERT INTO analisis_marcador (nombre, categoria, valor, valor_minimo, valor_maximo, resultado, analisis_id) " + 
						"SELECT * FROM (SELECT 'Ácido fólico (Vitamina B9)', 'Test Vitaminas', '7.24', '2.96', '32', 'Normal'," + analisis_id + ")" + 
						"as tmp WHERE NOT EXISTS (SELECT nombre FROM analisis_marcador WHERE nombre='Ácido fólico (Vitamina b9)' and analisis_id=" + analisis_id + ") LIMIT 1;"
						);
				ps4.executeUpdate();
				ps4.close();
				
				PreparedStatement ps5 = getConnection().prepareStatement(
						"INSERT INTO analisis_marcador (nombre, categoria, valor, valor_minimo, valor_maximo, resultado, analisis_id) " + 
						"SELECT * FROM (SELECT 'Vitamina B12', 'Test Vitaminas', '82.6', '32.28', '189.5', 'Normal'," + analisis_id + ")" + 
						"as tmp WHERE NOT EXISTS (SELECT nombre FROM analisis_marcador WHERE nombre='Vitamina B12' and analisis_id=" + analisis_id + ") LIMIT 1;"
						);
				ps5.executeUpdate();
				ps5.close();
			
				PreparedStatement ps6 = getConnection().prepareStatement(
						"INSERT INTO analisis_marcador (nombre, categoria, valor, valor_minimo, valor_maximo, resultado, analisis_id) " + 
						"SELECT * FROM (SELECT 'Vitamina D3', 'Test Vitaminas', '46.7', '46.8', '174.2', 'Anormal'," + analisis_id + ")" + 
						"as tmp WHERE NOT EXISTS (SELECT nombre FROM analisis_marcador WHERE nombre='Vitamina D3' and analisis_id=" + analisis_id + ") LIMIT 1;"
						);
				ps6.executeUpdate();
				ps6.close();
				
			} else if(analisis.equals("Test Hepático")) {
				
				PreparedStatement ps7 = getConnection().prepareStatement(
						"INSERT INTO analisis_marcador (nombre, categoria, valor, valor_minimo, valor_maximo, resultado, analisis_id) " + 
						"SELECT * FROM (SELECT 'Bilirrubina', 'Test Hepático', '12.36', '0', '26', 'Normal'," + analisis_id + ")" + 
						"as tmp WHERE NOT EXISTS (SELECT nombre FROM analisis_marcador WHERE nombre='Bilirrubina' and analisis_id=" + analisis_id + ") LIMIT 1;"
						);
				ps7.executeUpdate();
				ps7.close();
				
				PreparedStatement ps8 = getConnection().prepareStatement(
						"INSERT INTO analisis_marcador (nombre, categoria, valor, valor_minimo, valor_maximo, resultado, analisis_id) " + 
						"SELECT * FROM (SELECT 'ALP', 'Test Hepático', '56.7', '0', '128.5', 'Normal'," + analisis_id + ")" + 
						"as tmp WHERE NOT EXISTS (SELECT nombre FROM analisis_marcador WHERE nombre='ALP' and analisis_id=" + analisis_id + ") LIMIT 1;"
						);
				ps8.executeUpdate();
				ps8.close();
			
				PreparedStatement ps9 = getConnection().prepareStatement(
						"INSERT INTO analisis_marcador (nombre, categoria, valor, valor_minimo, valor_maximo, resultado, analisis_id) " + 
						"SELECT * FROM (SELECT 'ALT', 'Test Hepático', '52.1', '0', '52', 'Anormal'," + analisis_id + ")" + 
						"as tmp WHERE NOT EXISTS (SELECT nombre FROM analisis_marcador WHERE nombre='ALT' and analisis_id=" + analisis_id + ") LIMIT 1;"
						);
				ps9.executeUpdate();
				ps9.close();
				
				PreparedStatement ps10 = getConnection().prepareStatement(
						"INSERT INTO analisis_marcador (nombre, categoria, valor, valor_minimo, valor_maximo, resultado, analisis_id) " + 
						"SELECT * FROM (SELECT 'GGT', 'Test Hepático', '66.5', '12', '68.5', 'Normal'," + analisis_id + ")" + 
						"as tmp WHERE NOT EXISTS (SELECT nombre FROM analisis_marcador WHERE nombre='GGT' and analisis_id=" + analisis_id + ") LIMIT 1;"
						);
				ps10.executeUpdate();
				ps10.close();
				
			} else {
				
				System.out.println("Tipo de analítica no disponible.");
		    }
		}
		rs.close();
		ps.close();
	}
	
	public static void insertarDatosDiagnosticoImagen(int idPacienteSesionActiva) throws SQLException {
		
		String descripcion = "Posible indicio de artrosis, desgaste del cartílago provocando a su vez falta de protección a " +
							 "se observa también una alteración en la forma en que los huesos están alineados.<br><br>" + 
							 "Se recomienda que se realice una serie de ejercicios para el fortalecimiento de las articulaciones " + 
							 "supervisado por un fisioterapeuta. Es aconsejable la actividad física de bajo impacto por al menos 15 minutos " + 
							 "al día.";
		PreparedStatement ps1 = getConnection().prepareStatement(
				"INSERT INTO diagnostico_imagen (nombre, descripcion, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Rodilla', '" + descripcion + "', '20230128'," + idPacienteSesionActiva + ")" + 
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM diagnostico_imagen WHERE nombre='Rodilla' and paciente_id=" + idPacienteSesionActiva + ") LIMIT 1;"
				);
		ps1.executeUpdate();
		ps1.close();
		
		descripcion = "En principio no se aprecia ningún tipo de anomalía en la cadera. El paciente presenta cierta sintomatología " +
				 	  "que puede sugerir una inflamación en los músculos que rodean los huesos.<br><br>" + 
				 	  "Por el momento no se realiza diagnóstico alguno. Se recomienda que guarde reposo durante las próximas dos semanas, " + 
				 	  "que ingiera suficientes líquidos y que realice algunos ejercicios de rehabilitación con su fisioterapeuta.";
		PreparedStatement ps2 = getConnection().prepareStatement(
			"INSERT INTO diagnostico_imagen (nombre, descripcion, fecha, paciente_id) " + 
			"SELECT * FROM (SELECT 'Cadera', '" + descripcion + "', '20210304'," + idPacienteSesionActiva + ")" + 
			"as tmp WHERE NOT EXISTS (SELECT nombre FROM diagnostico_imagen WHERE nombre='Cadera' and paciente_id=" + idPacienteSesionActiva + ") LIMIT 1;"
			);
		ps2.executeUpdate();
		ps2.close();
	}
	
	public static void insertarDatosDiagnosticoImgRutas(int idPacienteSesionActiva) throws SQLException {
		
		// Para cada diagnóstico del paciente se insertan una o varias imágenes de rayos x.
		PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM diagnostico_imagen WHERE paciente_id=?");
		ps.setInt(1, idPacienteSesionActiva);
		ResultSet rs = ps.executeQuery();
		ArrayList<Analisis> analisisRes = null;
		
		while(rs.next()) {
			
			if(analisisRes == null) {
				analisisRes = new ArrayList<>();
			}
			String prueba = rs.getString("nombre"); // Tipo de prueba (rodilla, cadera, etc.)
			String diagnostico_imagen_id = rs.getString("diagnostico_imagen_id");
			
			if(prueba.equals("Rodilla")) {
				
				// Se inserta 4 imágenes para la prueba de rodilla
				for(int i = 1; i <= 4; i++) {
					
					ps = getConnection().prepareStatement(
							"INSERT INTO diagnostico_imagen_ruta (ruta, diagnostico_imagen_id) " + 
									"SELECT * FROM (SELECT 'rayos_x_rod_" + i + "', " + diagnostico_imagen_id + ")" + 
									"as tmp WHERE NOT EXISTS (SELECT ruta FROM diagnostico_imagen_ruta WHERE ruta='rayos_x_rod_" + i + "' and diagnostico_imagen_id=" + diagnostico_imagen_id + ") LIMIT 1;"
							);
					ps.executeUpdate();
				}
			} else if(prueba.equals("Cadera")) {
				
				// Se inserta 7 imágenes para la prueba de cadera
				for(int i = 1; i <= 7; i++) {
					
					ps = getConnection().prepareStatement(
							"INSERT INTO diagnostico_imagen_ruta (ruta, diagnostico_imagen_id) " + 
									"SELECT * FROM (SELECT 'rayos_x_cad_" + i + "', " + diagnostico_imagen_id + ")" + 
									"as tmp WHERE NOT EXISTS (SELECT ruta FROM diagnostico_imagen_ruta WHERE ruta='rayos_x_cad_" + i + "' and diagnostico_imagen_id=" + diagnostico_imagen_id + ") LIMIT 1;"
							);
					ps.executeUpdate();
				}
			} else {
				System.out.println("Tipo de diagnóstico por imagen no disponible.");
		    }
		}
		rs.close();
		ps.close();
	}
	
	public static void insertarDatosMedicacion(int idPacienteSesionActiva) throws SQLException {
		
		PreparedStatement ps1 = getConnection().prepareStatement(
				"INSERT INTO medicacion (nombre, dosis, posologia, tratamiento, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Ibuprofeno', '400 mg', '1 cada 8 horas', 'Artrosis', '2023-03-21', " + idPacienteSesionActiva + ") " + 
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM medicacion WHERE nombre='Ibuprofeno' and paciente_id=" + idPacienteSesionActiva + ") LIMIT 1;"
				);
		ps1.executeUpdate();
		ps1.close();
		
		PreparedStatement ps2 = getConnection().prepareStatement(
				"INSERT INTO medicacion (nombre, dosis, posologia, tratamiento, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Paracetamol pensa', '1 g', '1 cada 6 horas', 'Esguince de tobillo', '2022-09-29', " + idPacienteSesionActiva + ") " + 
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM medicacion WHERE nombre='Paracetamol pensa' and paciente_id=" + idPacienteSesionActiva + ") LIMIT 1;"
				);
		ps2.executeUpdate();
		ps2.close();
		
		PreparedStatement ps3 = getConnection().prepareStatement(
				"INSERT INTO medicacion (nombre, dosis, posologia, tratamiento, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Aspirina', '500 mg', '1 cada 6 horas', 'Dolor de cabeza', '2023-02-04', " + idPacienteSesionActiva + ") " + 
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM medicacion WHERE nombre='Aspirina' and paciente_id=" + idPacienteSesionActiva + ") LIMIT 1;"
				);
		ps3.executeUpdate();
		ps3.close();
		
		PreparedStatement ps4 = getConnection().prepareStatement(
				"INSERT INTO medicacion (nombre, dosis, posologia, tratamiento, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Champix', '1 mg', '1 cada 12 horas', 'Tabaquismo', '2022-11-14', " + idPacienteSesionActiva + ") " + 
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM medicacion WHERE nombre='Champix' and paciente_id=" + idPacienteSesionActiva + ") LIMIT 1;"
				);
		ps4.executeUpdate();
		ps4.close();
		
		PreparedStatement ps5 = getConnection().prepareStatement(
				"INSERT INTO medicacion (nombre, dosis, posologia, tratamiento, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Lovastatina Lareq', '20 g', '1 cada 24 horas', 'Colesterol alto', '2021-09-28', " + idPacienteSesionActiva + ") " + 
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM medicacion WHERE nombre='Lovastatina Lareq' and paciente_id=" + idPacienteSesionActiva + ") LIMIT 1;"
				);
		ps5.executeUpdate();
		ps5.close();
		
		PreparedStatement ps6 = getConnection().prepareStatement(
				"INSERT INTO medicacion (nombre, dosis, posologia, tratamiento, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Cetirizina Cinfa', '10 mg', '1 cada 24 horas', 'Alergia', '2021-05-06', " + idPacienteSesionActiva + ") " + 
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM medicacion WHERE nombre='Cetirizina Cinfa' and paciente_id=" + idPacienteSesionActiva + ") LIMIT 1;"
				);
		ps6.executeUpdate();
		ps6.close();
		
		PreparedStatement ps7 = getConnection().prepareStatement(
				"INSERT INTO medicacion (nombre, dosis, posologia, tratamiento, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Prednisona Cinfa', '30 mg', '1 cada 12 horas', 'Eczema', '2018-08-26', " + idPacienteSesionActiva + ") " + 
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM medicacion WHERE nombre='Prednisona Cinfa' and paciente_id=" + idPacienteSesionActiva + ") LIMIT 1;"
				);
		ps7.executeUpdate();
		ps7.close();
		
		PreparedStatement ps8 = getConnection().prepareStatement(
				"INSERT INTO medicacion (nombre, dosis, posologia, tratamiento, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Relenza', '5 mg en polvo', '1 cada 8 horas', 'Gripe', '2015-01-04', " + idPacienteSesionActiva + ") " + 
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM medicacion WHERE nombre='Relenza' and paciente_id=" + idPacienteSesionActiva + ") LIMIT 1;"
				);
		ps8.executeUpdate();
		ps8.close();
		
		PreparedStatement ps9 = getConnection().prepareStatement(
				"INSERT INTO medicacion (nombre, dosis, posologia, tratamiento, fecha, paciente_id) " + 
				"SELECT * FROM (SELECT 'Amoxicilina Normon', '500 mg', '1 cada 8 horas', 'Otitis', '2012-09-01', " + idPacienteSesionActiva + ") " + 
				"as tmp WHERE NOT EXISTS (SELECT nombre FROM medicacion WHERE nombre='Amoxicilina Normon' and paciente_id=" + idPacienteSesionActiva + ") LIMIT 1;"
				);
		ps9.executeUpdate();
		ps9.close();
	}
	
	public static void insertarDatosPacientes() throws SQLException {
		
		PreparedStatement ps = getConnection().prepareStatement(
			"INSERT INTO paciente (nombre, apellidos, fec_nacimiento, email, password) " + 
			"SELECT * FROM (SELECT 'Mario', 'García Martín', '19920904', 'mario@hotmail.com','Mario2023*')" +  
			"as tmp WHERE NOT EXISTS (SELECT email FROM paciente WHERE email='mario@hotmail.com') LIMIT 1;"
			);
		ps.executeUpdate();
		ps.close();
	}
}

