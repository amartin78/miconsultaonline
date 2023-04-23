package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.mysql.cj.jdbc.ClientPreparedStatement;

import modelo.Anomalia;
import modelo.Paciente;
import singleton.ConexionBBDD;

/**
 * Esta clase permite crear objetos de tipo AnomaliaDAO que contiene las funciones necesarias 
 * para la manipulación de objetos Anomalia en la BBDD usando los métodos CRUD. 
 *  
 * @author Antonio M. Martín Jimeno
 * @version 1.0
 */
public class AnomaliaDAO {

	private Connection con = null;
	public static AnomaliaDAO anomaliaInstance = null;
	
	public AnomaliaDAO() throws SQLException {
		
		con = ConexionBBDD.getConnection();
	}
	
	public static AnomaliaDAO getInstance() throws SQLException {
		
		if(anomaliaInstance == null) {
			anomaliaInstance = new AnomaliaDAO();
		}
		return anomaliaInstance;
	}
	
	public ArrayList<Anomalia> listarPorPacienteSesion(int id) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("SELECT nombre, sintoma, facultativo, fecha FROM anomalia " + 
													"WHERE paciente_id=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		ArrayList<Anomalia> anomaliaRes = null;
		
		while(rs.next()) {
			if(anomaliaRes == null) {
				anomaliaRes = new ArrayList<>();
			}
			anomaliaRes.add(new Anomalia(rs.getString("nombre"), rs.getString("sintoma"),  rs.getString("facultativo"), 
					 				     rs.getDate("fecha")));
		}
		rs.close();
		ps.close();
		
		return anomaliaRes;
	}
	
	public String listarPorPacienteSesionJSON(int id) throws SQLException {
		
		Gson gson = new Gson();
		String json = gson.toJson(this.listarPorPacienteSesion(id));
		return json;
	}
}

