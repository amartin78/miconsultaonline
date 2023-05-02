package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * Esta clase crea un objeto de tipo DiagnosticoImagen con información útil
 * para el paciente como la fecha en que ordenó el diagnóstico (o prueba), 
 * el tipo de diagnóstico y una descripción acerca de la imagen o imágenes 
 * obtenidas. Cada prueba o diagnóstico realizada en una fecha específica 
 * contendra a su vez una o más imágenes.
 * 
 * @author Antonio M. Martín Jimeno
 * @version 1.0
 */
public class DiagnosticoImagen {

	private int diagnostico_imagen_id;
	private String nombre;	// Nombre o tipo de la prueba o diagnóstico
	private String descripcion;	// Descripción de la prueba
	private Date fecha;		// La fecha en que se realizó la prueba
	private ArrayList<String> rutas;	// Lista con las rutas donde se encuentran almacenadas las imágenes
	private int paciente_id;	// El id del paciente que ordenó la prueba.
	
	/**
	 * Constructor de Analisis vacío
	 */
	public DiagnosticoImagen() {
		super();
	}

	/**
	 * @param diagnostico_imagen_id
	 * @param nombre
	 * @param descripcion
	 * @param fecha
	 * @param paciente_id
	 */
	public DiagnosticoImagen(String nombre, String descripcion, Date fecha) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.rutas = new ArrayList<>();
	}

	public int getDiagnostico_imagen_id() {
		return diagnostico_imagen_id;
	}

	public void setDiagnostico_imagen_id(int diagnostico_imagen_id) {
		this.diagnostico_imagen_id = diagnostico_imagen_id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public ArrayList<String> getRutas() {
		return rutas;
	}

	public void setRutas(ArrayList<String> rutas) {
		this.rutas = rutas;
	}

	public int getPaciente_id() {
		return paciente_id;
	}

	public void setPaciente_id(int paciente_id) {
		this.paciente_id = paciente_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descripcion, diagnostico_imagen_id, fecha, nombre, paciente_id, rutas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DiagnosticoImagen other = (DiagnosticoImagen) obj;
		return Objects.equals(descripcion, other.descripcion) && diagnostico_imagen_id == other.diagnostico_imagen_id
				&& Objects.equals(fecha, other.fecha) && Objects.equals(nombre, other.nombre)
				&& paciente_id == other.paciente_id && Objects.equals(rutas, other.rutas);
	}

	@Override
	public String toString() {
		return "DiagnosticoImagen [diagnostico_imagen_id=" + diagnostico_imagen_id + ", nombre=" + nombre
				+ ", descripcion=" + descripcion + ", fecha=" + fecha + ", rutas=" + rutas + ", paciente_id="
				+ paciente_id + "]";
	}
}

