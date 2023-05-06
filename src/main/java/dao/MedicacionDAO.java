package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gson.Gson;

import modelo.Medicacion;
import singleton.ConexionBBDD;

/**
 * Esta clase permite crear objetos de tipo MedicacionDAO que contiene las funciones necesarias 
 * para la manipulación de objetos Medicacion en la BBDD usando los métodos CRUD. 
 *  
 * @author Antonio M. Martín Jimeno
 * @version 1.0
 */
public class MedicacionDAO {

	private Connection con = null;
	public static MedicacionDAO medicacionInstance = null;
	
	public MedicacionDAO() throws SQLException {
		
		 con = ConexionBBDD.getConnection();
	}
	
	public static MedicacionDAO getInstance() throws SQLException {
		
		if(medicacionInstance == null) {
			medicacionInstance = new MedicacionDAO();
		}
		return medicacionInstance;
	}
	
	public ArrayList<Medicacion> listarMedicamentosPorPacienteSesion(int id) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("SELECT nombre, dosis, posologia, tratamiento, paciente_id, fecha FROM medicacion " +
													"WHERE paciente_id=? ORDER BY fecha DESC");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		ArrayList<Medicacion> medicacionRes = null;
		
		while(rs.next()) {
			if(medicacionRes == null) {
				medicacionRes = new ArrayList<>();
			}
			medicacionRes.add(new Medicacion(rs.getString("nombre"), rs.getString("dosis"),  rs.getString("posologia"), 
											 rs.getString("tratamiento"), rs.getDate("fecha"), rs.getInt("paciente_id")));
		}
		rs.close();
		ps.close();
		
		return medicacionRes;
	}
	
	public String listarMedicamentosPorPacienteSesionJSON(int id) throws SQLException {
		
		Gson gson = new Gson();
		String json = gson.toJson(this.listarMedicamentosPorPacienteSesion(id));
		return json;
	}
}

