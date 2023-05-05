package modelo;

import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

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
	 */
	public Medicacion(int id, String nombre, String dosis, String posologia, String tratamiento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.dosis = dosis;
		this.posologia = posologia;
		this.tratamiento = tratamiento;
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

	@Override
	public int hashCode() {
		return Objects.hash(dosis, id, nombre, posologia, tratamiento);
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
		return Objects.equals(dosis, other.dosis) && id == other.id && Objects.equals(nombre, other.nombre)
				&& Objects.equals(posologia, other.posologia) && Objects.equals(tratamiento, other.tratamiento);
	}

	@Override
	public String toString() {
		return "Medicacion [id=" + id + ", nombre=" + nombre + ", dosis=" + dosis + ", posologia=" + posologia
				+ ", tratamiento=" + tratamiento + "]";
	} 
}


