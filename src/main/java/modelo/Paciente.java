package modelo;

import java.sql.Date;
import java.util.Objects;

import dao.PacienteDAO;

import java.sql.SQLException;

/**
 * Esta clase crea un objeto de tipo Paciente con sus atributos y 
 * permite además realizar operaciones de inserción, borrado, 
 * modificación y lectura de dichos objetos sobre la BBDD.
 * 
 * @author Antonio M. Martín Jimeno
 * @version 1.0
 */
public class Paciente {

	private int id;
	private String nombre;
	private String apellidos;
	private String email;
	private String password;
	private Date fecNacimiento;
	private String domicilio;
	private int codPostal;
	private String localidad;
	private String provincia;
	private int telefono;
	private String estadoCivil;
	
	/**
	 * Constructor vacío
	 */
	public Paciente() {
		super();
	}
	
	/**
	 * Este método constuye un objeto con los atributos necesarios para el registro
	 * de un nuevo paciente en el sistema.
	 * @param nombre
	 * @param apellidos
	 * @param email
	 * @param password
	 * @param fecNacimiento
	 */
	public Paciente(String nombre, String apellidos, String email, String password, Date fecNacimiento) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.password = password;
		this.fecNacimiento = fecNacimiento;
	}
	
	/**
	 * Este método constuye un objeto con los atributos necesarios para la modificación 
	 * de un paciente en el sistema.
	 * @param nombre
	 * @param apellidos
	 * @param email
	 * @param password
	 * @param fecNacimiento
	 */
	public Paciente(int id, String nombre, String apellidos, String email, Date fecNacimiento,
			String domicilio, int codPostal, String localidad, String provincia, int telefono, String estadoCivil) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.fecNacimiento = fecNacimiento;
		this.domicilio = domicilio;
		this.codPostal = codPostal;
		this.localidad = localidad;
		this.provincia = provincia;
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

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public int getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(int codPostal) {
		this.codPostal = codPostal;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
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
		return Objects.hash(apellidos, codPostal, domicilio, email, estadoCivil, fecNacimiento, id, localidad, nombre,
				password, provincia, telefono);
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
		return Objects.equals(apellidos, other.apellidos) && codPostal == other.codPostal
				&& Objects.equals(domicilio, other.domicilio) && Objects.equals(email, other.email)
				&& Objects.equals(estadoCivil, other.estadoCivil) && Objects.equals(fecNacimiento, other.fecNacimiento)
				&& id == other.id && Objects.equals(localidad, other.localidad) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(password, other.password) && Objects.equals(provincia, other.provincia)
				&& telefono == other.telefono;
	}

	@Override
	public String toString() {
		return "Paciente [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email
				+ ", fecNacimiento=" + fecNacimiento + ", domicilio=" + domicilio
				+ ", codPostal=" + codPostal + ", localidad=" + localidad + ", provincia=" + provincia + ", telefono="
				+ telefono + ", estadoCivil=" + estadoCivil + "]";
	}

	public void insertar() throws SQLException {
		
		PacienteDAO.getInstance().altaPaciente(this);
	}
	
	public void eliminar() throws SQLException {
		
		PacienteDAO.getInstance().bajaPaciente(this);
	}
	
	public void actualizar() throws SQLException {
		
		PacienteDAO.getInstance().actualizarPaciente(this);
	}
	
	public void cambiarContrasenia() throws SQLException {
		
		PacienteDAO.getInstance().cambiarContrasenia(this);
	}
	
	public String buscar(String atributo, String valor) throws SQLException {
		
		String listado = PacienteDAO.getInstance().buscarPacientePorAtributoJSON(atributo, valor);
		return listado;
	}
}

