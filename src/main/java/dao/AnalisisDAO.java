package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.mysql.cj.jdbc.ClientPreparedStatement;

import modelo.Anomalia;
import modelo.Alergia;
import modelo.Analisis;
import modelo.Vacuna;
import modelo.Paciente;
import singleton.ConexionBBDD;

/**
 * Esta clase permite crear objetos de tipo AnomaliaDAO que contiene las funciones necesarias 
 * para la manipulación de objetos Anomalia en la BBDD usando los métodos CRUD. 
 *  
 * @author Antonio M. Martín Jimeno
 * @version 1.0
 */
public class AnalisisDAO {

	private Connection con = null;
	public static AnalisisDAO analisisInstance = null;
	
	public AnalisisDAO() throws SQLException {
		
		 con = ConexionBBDD.getConnection();
	}
	
	public static AnalisisDAO getInstance() throws SQLException {
		
		if(analisisInstance == null) {
			analisisInstance = new AnalisisDAO();
		}
		return analisisInstance;
	}
	
	public ArrayList<Analisis> listarAnalisisPorPacienteSesion(int id) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("SELECT nombre, estado, fecha FROM analisis " +
													"WHERE paciente_id=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		ArrayList<Analisis> analisisRes = null;
		
		while(rs.next()) {
			if(analisisRes == null) {
				analisisRes = new ArrayList<>();
			}
			analisisRes.add(new Analisis(rs.getString("nombre"), rs.getString("estado"), rs.getDate("fecha")));
		}
		rs.close();
		ps.close();
		
		return analisisRes;
	}
	
	public String listarAnalisisPorPacienteSesionJSON(int id) throws SQLException {
		
		Gson gson = new Gson();
		String json = gson.toJson(this.listarAnalisisPorPacienteSesion(id));
		return json;
	}
	
//	public ArrayList<Marcador> listarMarcadoresPorAnalisis(int id) throws SQLException {
//		
//		PreparedStatement ps = con.prepareStatement("SELECT nombre, estado, fecha FROM analisis " +
//													"WHERE paciente_id=?");
//		ps.setInt(1, id);
//		ResultSet rs = ps.executeQuery();
//		ArrayList<Analisis> analisisRes = null;
//		
//		while(rs.next()) {
//		if(analisisRes == null) {
//		analisisRes = new ArrayList<>();
//		}
//		analisisRes.add(new Analisis(rs.getString("nombre"), rs.getString("sintoma"),  rs.getString("facultativo"), 
//				 rs.getDate("fecha")));
//		}
//		rs.close();
//		ps.close();
//		
//		return analisisRes;
//	}
//	
//	public String listarMarcadoresPorAnalisisJSON(int id) throws SQLException {
//		
//		Gson gson = new Gson();
//		String json = gson.toJson(this.listarMarcadoresPorAnalisis(id));
//		return json;
//	}
}

