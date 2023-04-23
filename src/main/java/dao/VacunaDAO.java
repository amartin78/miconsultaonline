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
import modelo.Vacuna;
import singleton.ConexionBBDD;

/**
 * Esta clase permite crear objetos de tipo AnomaliaDAO que contiene las funciones necesarias 
 * para la manipulación de objetos Anomalia en la BBDD usando los métodos CRUD. 
 *  
 * @author Antonio M. Martín Jimeno
 * @version 1.0
 */
public class VacunaDAO {

	private Connection con = null;
	public static VacunaDAO vacunaInstance = null;
	
	public VacunaDAO() throws SQLException {
		
		con = ConexionBBDD.getConnection();
	}
	
	public static VacunaDAO getInstance() throws SQLException {
		
		if(vacunaInstance == null) {
			vacunaInstance = new VacunaDAO();
		}
		return vacunaInstance;
	}
	
	public ArrayList<Vacuna> listarPorPacienteSesion(int id) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("SELECT nombre, laboratorio, lote, fecha FROM vacuna " + 
													"WHERE paciente_id=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		ArrayList<Vacuna> vacunaRes = null;
		
		while(rs.next()) {
			if(vacunaRes == null) {
				vacunaRes = new ArrayList<>();
			}
			vacunaRes.add(new Vacuna(rs.getString("nombre"), rs.getString("laboratorio"),  rs.getString("lote"), 
					 				     rs.getDate("fecha")));
		}
		rs.close();
		ps.close();
		
		return vacunaRes;
	}
	
	public String listarPorPacienteSesionJSON(int id) throws SQLException {
		
		Gson gson = new Gson();
		String json = gson.toJson(this.listarPorPacienteSesion(id));
		return json;
	}
}

