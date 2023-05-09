package modelo;

import java.util.Objects;

import dao.MedicacionDAO;

import java.sql.SQLException;
import java.util.Date;

/**
 * Esta clase crea un objeto con información relativa a la medicación de un 
 * paciente a lo largo de su estancia en el centro de salud asociado. 
 * 
 * @author Antonio M. Martín Jimeno
 * @version 1.0
 */
public class Medicacion {

	private int id;
	private String nombre;
	private String dosis; 
	private String posologia;
	private String tratamiento; 
	private Date fecha;
	private int pacienteId;
	
	/**
	 * Constructor de Medicación vacío
	 */
	public Medicacion() {
		super();
	}
	
	/**
	 * Constructor de un objeto de tipo
	 * 
	 * @param id
	 * @param nombre
	 * @param dosis
	 * @param posologia
	 * @param tratamiento
	 * @param fecha
	 * @param paciente_id
	 */
	public Medicacion(String nombre, String dosis, String posologia, String tratamiento, Date fecha, int pacienteId) {
		super();
		this.nombre = nombre;
		this.dosis = dosis;
		this.posologia = posologia;
		this.tratamiento = tratamiento;
		this.fecha = fecha;
		this.pacienteId = pacienteId;
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

	public String getDosis() {
		return dosis;
	}

	public void setDosis(String dosis) {
		this.dosis = dosis;
	}

	public String getPosologia() {
		return posologia;
	}

	public void setPosologia(String posologia) {
		this.posologia = posologia;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(int pacienteId) {
		this.pacienteId = pacienteId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dosis, fecha, id, nombre, pacienteId, posologia, tratamiento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicacion other = (Medicacion) obj;
		return Objects.equals(dosis, other.dosis) && Objects.equals(fecha, other.fecha) && id == other.id
				&& Objects.equals(nombre, other.nombre) && pacienteId == other.pacienteId
				&& Objects.equals(posologia, other.posologia) && Objects.equals(tratamiento, other.tratamiento);
	}

	@Override
	public String toString() {
		return "Medicacion [id=" + id + ", nombre=" + nombre + ", dosis=" + dosis + ", posologia=" + posologia
				+ ", tratamiento=" + tratamiento + ", fecha=" + fecha + ", pacienteId=" + pacienteId + "]";
	}
	
	public String listarMedicamentos(int id) throws SQLException {
		
		String listado = MedicacionDAO.getInstance().listarMedicamentosPorPacienteSesionJSON(id);
		return listado;
	}
}

