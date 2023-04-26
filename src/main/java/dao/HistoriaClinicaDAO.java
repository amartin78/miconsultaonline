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
public class HistoriaClinicaDAO {

	private Connection con = null;
	public static HistoriaClinicaDAO hclinicaInstance = null;
	
	public HistoriaClinicaDAO() throws SQLException {
		
		con = ConexionBBDD.getConnection();
	}
	
	public static HistoriaClinicaDAO getInstance() throws SQLException {
		
		if(hclinicaInstance == null) {
			hclinicaInstance = new HistoriaClinicaDAO();
		}
		return hclinicaInstance;
	}
	
	public ArrayList<Anomalia> listarAnomaliasPorPacienteSesion(int id) throws SQLException {
		
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
	
	public String listarAnomaliasPorPacienteSesionJSON(int id) throws SQLException {
		
		Gson gson = new Gson();
		String json = gson.toJson(this.listarAnomaliasPorPacienteSesion(id));
		return json;
	}
	
	public ArrayList<Alergia> listarAlergiasPorPacienteSesion(int id) throws SQLException {
		
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
	
	public String listarAlergiasPorPacienteSesionJSON(int id) throws SQLException {
		
		Gson gson = new Gson();
		String json = gson.toJson(this.listarAlergiasPorPacienteSesion(id));
		return json;
	}
	
	public ArrayList<Vacuna> listarVacunasPorPacienteSesion(int id) throws SQLException {
		
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
	
	public String listarVacunasPorPacienteSesionJSON(int id) throws SQLException {
		
		Gson gson = new Gson();
		String json = gson.toJson(this.listarVacunasPorPacienteSesion(id));
		return json;
	}
}

