package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.mysql.cj.jdbc.ClientPreparedStatement;

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
//			pacienteRes.add(new Paciente(rs.getString("nombre"), rs.getString("apellidos"),  rs.getString("email"), 
//										 rs.getString("password"), rs.getDate("fec_nacimiento")));
			pacienteRes.add(new Paciente(rs.getString("nombre"), rs.getString("apellidos"),  rs.getString("email"), 
					 rs.getString("password")));
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
	
	public Paciente buscarPorAtributo(String atributo, String valor) throws SQLException {
		
		Paciente p = null;
		PreparedStatement ps = con.prepareStatement("SELECT * FROM paciente WHERE " + atributo + "=?");
		ps.setString(1, valor);
		ResultSet rs = ps.executeQuery();		
		
		while(rs.next()) {
			p = new Paciente(rs.getString("nombre"), rs.getString("apellidos"),  rs.getString("email"), 
					 rs.getString("password"), rs.getDate("fec_nacimiento"));
		}			
		rs.close();
		ps.close();
		
		System.out.println("persona " + p);
		
		return p;
	}
	

	public String buscarPorAtributoJSON(String atributo, String valor) throws SQLException {
		
		Gson gson = new Gson();
		String json = gson.toJson(this.buscarPorAtributo(atributo, valor));
		return json;
	}
	
	public boolean autenticar(String email, String password) throws SQLException {
		
		boolean registrado = false;
		PreparedStatement ps = con.prepareStatement("SELECT * FROM paciente WHERE email=? AND password=?");
		ps.setString(1, email);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			registrado = true;
			System.out.println("El usuario " + rs.getString("nombre") + " " + rs.getString("apellidos") + " es válido.");
		} else {
			System.out.println("Usuario o contraseña incorrecta.");
		}
		rs.close();
		ps.close();
		return registrado;
	}
}

