package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.mysql.cj.jdbc.ClientPreparedStatement;

import modelo.Alergia;
import modelo.Paciente;
import singleton.ConexionBBDD;

/**
 * Esta clase permite crear objetos de tipo AnomaliaDAO que contiene las funciones necesarias 
 * para la manipulación de objetos Anomalia en la BBDD usando los métodos CRUD. 
 *  
 * @author Antonio M. Martín Jimeno
 * @version 1.0
 */
public class AlergiaDAO {

	private Connection con = null;
	public static AlergiaDAO alergiaInstance = null;
	
	public AlergiaDAO() throws SQLException {
		
		con = ConexionBBDD.getConnection();
	}
	
	public static AlergiaDAO getInstance() throws SQLException {
		
		if(alergiaInstance == null) {
			alergiaInstance = new AlergiaDAO();
		}
		return alergiaInstance;
	}
	
	public ArrayList<Alergia> listarPorPacienteSesion(int id) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("SELECT nombre, frecuencia, test_realizado, fecha FROM alergia " + 
													"WHERE paciente_id=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		ArrayList<Alergia> alergiaRes = null;
		
		while(rs.next()) {
			if(alergiaRes == null) {
				alergiaRes = new ArrayList<>();
			}
			alergiaRes.add(new Alergia(rs.getString("nombre"), rs.getString("frecuencia"),  rs.getString("test_realizado"), 
					 				     rs.getDate("fecha")));
		}
		rs.close();
		ps.close();
		
		return alergiaRes;
	}
	
	public String listarPorPacienteSesionJSON(int id) throws SQLException {
		
		Gson gson = new Gson();
		String json = gson.toJson(this.listarPorPacienteSesion(id));
		return json;
	}
}

