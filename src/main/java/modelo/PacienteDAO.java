package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gson.Gson;

public class PacienteDAO {

	private Connection con = null;
	public static PacienteDAO pacInstance = null;
	
	public PacienteDAO() throws SQLException {
		
		con = ConexionBBDD.getConnection();
	}
	
	public static PacienteDAO getInstance() throws SQLException {
		
		if(pacInstance == null) {
			pacInstance = new PacienteDAO();
		}
		return pacInstance;
	}
	
	public void daoInsertar(Paciente p) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("INSERT INTO paciente "
												  + "(nombre, apellidos, fec_nacimiento, email, password) "
												  + "VALUES (?,?,?,?,?)");
		ps.setString(1, p.getNombre());
		ps.setString(2, p.getApellidos());
		ps.setDate(3, p.getFecNacimiento());
		ps.setString(4, p.getEmail());
		ps.setString(5, p.getPassword());
		int filas = ps.executeUpdate();
		ps.close();
	}
	
	public ArrayList<Paciente> listar() throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("SELECT * FROM paciente");
		ResultSet rs = ps.executeQuery();
		ArrayList<Paciente> pacienteRes = null;
		
		while(rs.next()) {
			if(pacienteRes == null) {
				pacienteRes = new ArrayList<>();
			}
			pacienteRes.add(new Paciente(rs.getString("nombre"), rs.getString("apellidos"),  rs.getString("email"), 
										 rs.getString("password"), rs.getDate("fecNacimiento"), rs.getString("direccion"), 
										 rs.getInt("telefono"), rs.getString("estadoCivil")));
		}
		rs.close();
		ps.close();
		return pacienteRes;
	}
	
	public String listaJSON() throws SQLException {
		
		Gson gson = new Gson();
		String json = gson.toJson(this.listar());
		return json;
	}
	
}

