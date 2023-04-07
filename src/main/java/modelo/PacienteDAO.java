package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.mysql.cj.jdbc.ClientPreparedStatement;

/**
 * Esta clase permite crear objetos de tipo PacienteDAO que 
 * contiene las funciones necesarias para la manipulación de
 * objetos Persona en la BBDD usando los métodos CRUD.
 *  
 * @author Antonio M. Martín Jimeno
 * @version 1.0
 */
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
	
	public Paciente obtenerPacientePorID(int id) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("SELECT * FROM paciente WHERE id=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Paciente p = null;
		
		if(rs.next()) {
			p = new Paciente(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"), rs.getDate("fec_nacimiento"), 
							 rs.getString("domicilio"), rs.getInt("cod_postal"), rs.getString("localidad"), 
							 rs.getString("provincia"), rs.getInt("telefono"), rs.getString("estado_civil"));
		}
		rs.close();
		ps.close();
		
		return p;
	}
	
	public void altaPaciente(Paciente p) throws SQLException {
		
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
	
	public void bajaPaciente(Paciente p) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("DELETE FROM paciente WHERE email=?");
		ps.setString(1, p.getEmail());
		ps.executeUpdate();
		ps.close();
	}
	
	public void modificarPaciente(Paciente p) throws SQLException {
		
		// System.out.println("persona domicilio: " + p.getDomicilio() + ", persona codpostal: " + p.getCodPostal());
		if(p.getId() != 0) {
			
			PreparedStatement ps = con.prepareStatement("UPDATE paciente SET " + 
					"nombre=?, apellidos=?, fec_nacimiento=?, domicilio=?, " + 
					"cod_postal=?, localidad=?, provincia=?, telefono=?, estado_civil=? " +  
					"WHERE id=?");
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getApellidos());
			ps.setDate(3, p.getFecNacimiento());
			ps.setString(4, p.getDomicilio());
			ps.setInt(5, p.getCodPostal());
			ps.setString(6, p.getLocalidad());
			ps.setString(7, p.getProvincia());
			ps.setInt(8, p.getTelefono());
			ps.setString(9, p.getEstadoCivil());
			ps.setInt(10, p.getId());
			int filas = ps.executeUpdate();
			ps.close();
		}
	}
	
	public void cambiarContrasenia(Paciente p) throws SQLException {
		
		if(p.getId() != 0) {
			
			PreparedStatement ps = con.prepareStatement("UPDATE paciente SET password=? WHERE id=?");
			ps.setString(1, p.getPassword());
			ps.setInt(2, p.getId());
			int filas = ps.executeUpdate();
			ps.close();
		}
	}
	
	public ArrayList<Paciente> listarPacientes() throws SQLException {
		
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
	
	public String listarPacientesJSON() throws SQLException {
		
		Gson gson = new Gson();
		String json = gson.toJson(this.listarPacientes());
		return json;
	}
	
	public Paciente buscarPacientePorAtributo(String atributo, String valor) throws SQLException {
		
		Paciente p = null;
		PreparedStatement ps = con.prepareStatement("SELECT * FROM paciente WHERE " + atributo + "=?");
		ps.setString(1, valor);
		ResultSet rs = ps.executeQuery();		
		
		if(rs.next()) {
			p = new Paciente(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"), 
					 rs.getString("email"), rs.getString("password"), rs.getDate("fec_nacimiento"), 
					 rs.getString("domicilio"), rs.getInt("cod_postal"), rs.getString("localidad"), 
					 rs.getString("provincia"), rs.getInt("telefono"), rs.getString("estado_civil"));
		}			
		rs.close();
		ps.close();
		
		System.out.println("persona " + p);
		
		return p;
	}
	

	public String buscarPorAtributoJSON(String atributo, String valor) throws SQLException {
		
		Gson gson = new Gson();
		String json = gson.toJson(this.buscarPacientePorAtributo(atributo, valor));
		return json;
	}
	
	public boolean autenticarPaciente(String email, String password) throws SQLException {
		
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

