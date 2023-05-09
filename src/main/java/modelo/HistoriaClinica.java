package modelo;

import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

import dao.HistoriaClinicaDAO;

/**
 * Esta clase crea la historia clínica de un paciente con información relativa a 
 * las anomalías, alergias y vacunas que haya tenido durante el curso de su 
 * estancia en el centro de salud asociado.
 * 
 * @author Antonio M. Martín Jimeno
 * @version 1.0
 */
public class HistoriaClinica {

	private int id;
	private String nombre; // Se refiere al nombre de la anomalía, alergia o vacuna
	private Date fecha; // Fecha del diagnóstico (para anomalía o alergia) o de cuando se administró la vacuna
	
	/**
	 * Constructor de HistoriaClinica vacío
	 */
	public HistoriaClinica() {
		super();
	}
	
	/**
	 * Constructor para crear una nueva historia clínica.
	 * Para listarlos.
	 * 
	 * @param nombre
	 * @param fecha
	 */
	public HistoriaClinica(String nombre, Date fecha) {
		super();
		this.nombre = nombre;
		this.fecha = fecha;
	}
	
	/**
	 * Constructor para crear una nueva historia clínica.
	 * Para el alta o actualización.
	 * 
	 * @param id
	 * @param nombre
	 * @param fecha
	 */
	public HistoriaClinica(int id, String nombre, Date fecha) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha = fecha;
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha, id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HistoriaClinica other = (HistoriaClinica) obj;
		return Objects.equals(fecha, other.fecha) && id == other.id && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "HistoriaClinica [id=" + id + ", nombre=" + nombre + ", fecha=" + fecha + "]";
	}
	
	public String listarAnomalias(int id) throws SQLException {
		
		String listado = HistoriaClinicaDAO.getInstance().listarAnomaliasPorPacienteSesionJSON(id);
		return listado;
	}
	

	public String listarAlergias(int id) throws SQLException {
		
		String listado = HistoriaClinicaDAO.getInstance().listarAlergiasPorPacienteSesionJSON(id);
		return listado;
	}
	

	public String listarVacunas(int id) throws SQLException {
		
		String listado = HistoriaClinicaDAO.getInstance().listarVacunasPorPacienteSesionJSON(id);
		return listado;
	}
}

