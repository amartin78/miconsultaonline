package modelo;

import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

import dao.VacunaDAO;

/**
 * Esta clase crea la un objeto con información relativa a las vacunas que le han
 * sido administradas al paciente a lo largo de su estancia en el centro de salud asociado. 
 * Tiene como superclase a HistoriaClinica.
 * 
 * @author Antonio M. Martín Jimeno
 * @version 1.0
 */
public class Vacuna extends HistoriaClinica {

	private String laboratorio;
	private String lote; 
	
	/**
	 * Constructor de Anomalia vacío
	 */
	public Vacuna() {
		super();
	}

	/**
	 * Constructor de un objeto de tipo Vacuna que hereda atributos 
	 * de su superclase HistoriaClinica.
	 * 
	 * @param nombre
	 * @param laboratorio
	 * @param lote
	 * @param fecha
	 */
	public Vacuna( String nombre, String laboratorio, String lote, Date fecha) {
		super(nombre, fecha);
		this.laboratorio = laboratorio;
		this.lote = lote;
	}

	public String getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(laboratorio, lote);
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
		Vacuna other = (Vacuna) obj;
		return Objects.equals(laboratorio, other.laboratorio) && Objects.equals(lote, other.lote);
	}

	@Override
	public String toString() {
		return "Vacuna [laboratorio=" + laboratorio + ", lote=" + lote + "]";
	}
}

