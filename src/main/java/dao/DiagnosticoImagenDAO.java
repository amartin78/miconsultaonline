package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.DiagnosticoImagen;
import singleton.ConexionBBDD;

/**
 * Esta clase permite crear objetos de tipo AnomaliaDAO que contiene las funciones necesarias 
 * para la manipulación de objetos Anomalia en la BBDD usando los métodos CRUD. 
 *  
 * @author Antonio M. Martín Jimeno
 * @version 1.0
 */
public class DiagnosticoImagenDAO {

	private Connection con = null;
	public static DiagnosticoImagenDAO diagnosticoImgInstance = null;
	
	public DiagnosticoImagenDAO() throws SQLException {
		
		 con = ConexionBBDD.getConnection();
	}
	
	public static DiagnosticoImagenDAO getInstance() throws SQLException {
		
		if(diagnosticoImgInstance == null) {
			diagnosticoImgInstance = new DiagnosticoImagenDAO();
		}
		return diagnosticoImgInstance;
	}
	
	public ArrayList<DiagnosticoImagen> listarDiagnosticoImagenPorPacienteSesion(int id) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("SELECT diagnostico_imagen_id, nombre, descripcion, fecha FROM diagnostico_imagen " + 
													"WHERE paciente_id=? ORDER BY fecha DESC");
		ps.setInt(1, id);
		ResultSet rs1 = ps.executeQuery();
		ArrayList<DiagnosticoImagen> diagnosticoImagenRes = null;
		DiagnosticoImagen diagnosticoImagen = null;
		int diagnostico_imagen_id;
		
		while(rs1.next()) {			
			
			if(diagnosticoImagenRes == null) {
				diagnosticoImagenRes = new ArrayList<>();
			}
			
			diagnostico_imagen_id = rs1.getInt("diagnostico_imagen_id");
			diagnosticoImagen = new DiagnosticoImagen(rs1.getString("nombre"), rs1.getString("descripcion"), rs1.getDate("fecha"));
			
			ps = con.prepareStatement("SELECT ruta FROM diagnostico_imagen_ruta WHERE diagnostico_imagen_id=?");
			ps.setInt(1, diagnostico_imagen_id);
			ResultSet rs2 = ps.executeQuery();
			
			while(rs2.next()) {
				
				diagnosticoImagen.getRutas().add(rs2.getString("ruta"));
			}
			
			diagnosticoImagenRes.add(diagnosticoImagen);
			rs2.close();
		}
		rs1.close();
		ps.close();
		
		return diagnosticoImagenRes;
	}
	
	public String listarDiagnosticoImagenPorPacienteSesionJSON(int id) throws SQLException {
		
		Gson gson = new Gson();
		String json = gson.toJson(this.listarDiagnosticoImagenPorPacienteSesion(id));
		return json;
	}
}

