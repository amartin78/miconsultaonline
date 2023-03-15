package modelo;

import java.sql.Date;
import java.util.Objects;
import java.sql.SQLException;

public class Paciente {

	private int id;
	private String nombre;
	private String apellidos;
	private String email;
	private String password;
	private Date fecNacimiento;
	private String direccion;
	private int telefono;
	private String estadoCivil;
	
	public Paciente() {
		
	}
	
	public Paciente(String nombre, String apellidos, String email, String password, Date fecNacimiento) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.password = password;
		this.fecNacimiento = fecNacimiento;
	}
	
	// Constructor de objeto cuyo atributo id ya tiene un valor en la base de datos
	public Paciente(String nombre, String apellidos, String email, String password, Date fecNacimiento,
			String direccion, int telefono, String estadoCivil) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.password = password;
		this.fecNacimiento = fecNacimiento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.estadoCivil = estadoCivil;
	}
	
	public Paciente(int id, String nombre, String apellidos, String email, String password, Date fecNacimiento,
			String direccion, int telefono, String estadoCivil) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.password = password;
		this.fecNacimiento = fecNacimiento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.estadoCivil = estadoCivil;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFecNacimiento() {
		return fecNacimiento;
	}

	public void setFecNacimiento(Date fecNacimiento) {
		this.fecNacimiento = fecNacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellidos, direccion, email, estadoCivil, fecNacimiento, id, nombre, password, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(direccion, other.direccion)
				&& Objects.equals(email, other.email) && Objects.equals(estadoCivil, other.estadoCivil)
				&& Objects.equals(fecNacimiento, other.fecNacimiento) && id == other.id
				&& Objects.equals(nombre, other.nombre) && Objects.equals(password, other.password)
				&& telefono == other.telefono;
	}

	@Override
	public String toString() {
		return "Paciente [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", emai=" + email
				+ ", password=" + password + ", fecNacimiento=" + fecNacimiento + ", direccion=" + direccion
				+ ", telefono=" + telefono + ", estadoCivil=" + estadoCivil + "]";
	}
	
	public void insertar() throws SQLException {
		
		PacienteDAO.getInstance().daoInsertar(this);
	}
}

