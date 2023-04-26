package modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import modelo.Marcador;

/**
 * Esta clase crea un objeto de tipo Análisis con información útil
 * para el paciente como la fecha en que lo ordenó, el tipo de
 * test y el estado en que se encuentra la orden. Cada análisis
 * incluye una serie de marcadores (colesterol, tiroides, etc.).
 * 
 * @author Antonio M. Martín Jimeno
 * @version 1.0
 */
public class Analisis {

	private int id;
	private String nombre;	// Tipo de test (perfil tiroideo, colesterol, salud general)
	private String estado;	// Si la muestra ha llegado o no al laboratorio, si esta en proceso o listo para consultar.
	private Date fecha;		// La fecha en que se realizó la orden.
	private ArrayList<Marcador> marcadores;	// Lista de marcadores asociados a este análisis.
	private int paciente_id;	// El id del paciente que ordenó el test.
	
	/**
	 * Constructor de Analisis vacío
	 */
	public Analisis() {
		super();
	}

	/**
	 * Constructor de un objeto de tipo Analisis.
	 * 
	 * @param id
	 * @param nombre
	 * @param estado
	 * @param fecha
	 * @param paciente_id
	 */
	public Analisis(String nombre, String estado, Date fecha) {
		super();
		this.nombre = nombre;
		this.estado = estado;
		this.fecha = fecha;
		this.marcadores = new ArrayList<>();
		this.paciente_id = paciente_id;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public ArrayList<Marcador> getMarcadores() {
		return marcadores;
	}

	public void setMarcadores(ArrayList<Marcador> marcadores) {
		this.marcadores = marcadores;
	}

	public int getPaciente_id() {
		return paciente_id;
	}

	public void setPaciente_id(int paciente_id) {
		this.paciente_id = paciente_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(estado, fecha, id, marcadores, nombre, paciente_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Analisis other = (Analisis) obj;
		return Objects.equals(estado, other.estado) && Objects.equals(fecha, other.fecha) && id == other.id
				&& Objects.equals(marcadores, other.marcadores) && Objects.equals(nombre, other.nombre)
				&& paciente_id == other.paciente_id;
	}

	@Override
	public String toString() {
		return "Analisis [id=" + id + ", nombre=" + nombre + ", estado=" + estado + ", fecha=" + fecha + ", marcadores="
				+ marcadores + ", paciente_id=" + paciente_id + "]";
	}
}

