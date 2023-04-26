package modelo;

import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

/**
 * Esta clase crea la un objeto con información relativa a anomalías de un 
 * paciente a lo largo de su estancia en el centro de salud asociado. 
 * Tiene como superclase a HistoriaClinica.
 * 
 * @author Antonio M. Martín Jimeno
 * @version 1.0
 */
public class Alergia extends HistoriaClinica {

	private String frecuencia;	// Época del año con más alergias
	private String testRealizado; 
	
	/**
	 * Constructor vacío
	 */
	public Alergia() {
		super();
	}

	/**
	 * Constructor de un objeto de tipo Alergía que hereda atributos 
	 * de su superclase HistoriaClinica.
	 * 
	 * @param nombre
	 * @param frecuencia
	 * @param testRealizado
	 * @param fecha
	 */
	public Alergia(String nombre, String frecuencia, String testRealizado, Date fecha) {
		super(nombre, fecha);
		this.frecuencia = frecuencia;
		this.testRealizado = testRealizado;
	}

	public String getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}

	public String getTestRealizado() {
		return testRealizado;
	}

	public void setTestRealizado(String testRealizado) {
		this.testRealizado = testRealizado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(frecuencia, testRealizado);
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
		Alergia other = (Alergia) obj;
		return Objects.equals(frecuencia, other.frecuencia) && Objects.equals(testRealizado, other.testRealizado);
	}

	@Override
	public String toString() {
		return "Alergia [frecuencia=" + frecuencia + ", testRealizado=" + testRealizado + "]";
	}
}

