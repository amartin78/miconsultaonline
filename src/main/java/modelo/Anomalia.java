package modelo;

import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

/**
 * Esta clase crea un objeto con información relativa a anomalías de un 
 * paciente a lo largo de su estancia en el centro de salud asociado. 
 * Tiene como superclase a HistoriaClinica.
 * 
 * @author Antonio M. Martín Jimeno
 * @version 1.0
 */
public class Anomalia extends HistoriaClinica {

	private String sintomas;
	private String facultativo; // El nombre del doctor asignado al paciente
	
	/**
	 * Constructor de Anomalia vacío
	 */
	public Anomalia() {
		super();
	}
	
	/**
	 * Constructor de un objeto de tipo Anomalia que hereda atributos 
	 * de su superclase HistoriaClinica.
	 * 
	 * @param nombre
	 * @param sintomas
	 * @param facultativo
	 * @param fecha
	 */
	public Anomalia(String nombre, String sintomas, String facultativo, Date fecha) {
		super(nombre, fecha);
		this.sintomas = sintomas;
		this.facultativo = facultativo;
	}

	public String getSintomas() {
		return sintomas;
	}

	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}

	public String getFacultativo() {
		return facultativo;
	}

	public void setFacultativo(String facultativo) {
		this.facultativo = facultativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(facultativo, sintomas);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Anomalia other = (Anomalia) obj;
		return Objects.equals(facultativo, other.facultativo) 
				&& Objects.equals(sintomas, other.sintomas);
	}

	@Override
	public String toString() {
		return "Anomalia [sintomas=" + sintomas + ", facultativo=" + facultativo + "]";
	}
}

