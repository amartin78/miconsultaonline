package modelo;

import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

/**
 * Esta clase crea un objeto de tipo Marcador con información de cada
 * marcador incluido en el análisis del paciente.
 * 
 * @author Antonio M. Martín Jimeno
 * @version 1.0
 */
public class Marcador {

	private int id;
	private String nombre;	// Tipo de test (perfil tiroideo, colesterol, salud general)
	private String categoria;	// Si la muestra ha llegado o no al laboratorio, si esta en proceso o listo para consultar.
	private float valor;
	private float valor_minimo;
	private float valor_maximo;
	private String resultado;
	private int analisis_id;	// El id del paciente que ordenó el test.
	
	/**
	 * Constructor de Marcador vacío
	 */
	public Marcador() {
		super();
	}

	/**
	 * Constructor de un objeto de tipo Marcador con información relativa a 
	 * un un determinado tipo de test de la analítica. Para realizar consultas, 
	 * altas o borrado.
	 * 
	 * @param nombre
	 * @param categoria
	 * @param valor
	 * @param valor_minimo
	 * @param valor_maximo
	 * @param resultado
	 * @param analisis_id
	 */
	public Marcador(String nombre, String categoria, float valor, float valor_minimo, float valor_maximo,
			String resultado, int analisis_id) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.valor = valor;
		this.valor_minimo = valor_minimo;
		this.valor_maximo = valor_maximo;
		this.resultado = resultado;
		this.analisis_id = analisis_id;
	}

	/**
	 * Constructor de un objeto de tipo Marcador para realizar una actualización.
	 * 
	 * @param id
	 * @param nombre
	 * @param categoria
	 * @param valor
	 * @param valor_minimo
	 * @param valor_maximo
	 * @param resultado
	 * @param analisis_id
	 */
	public Marcador(int id, String nombre, String categoria, float valor, float valor_minimo, float valor_maximo,
			String resultado, int analisis_id) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.valor = valor;
		this.valor_minimo = valor_minimo;
		this.valor_maximo = valor_maximo;
		this.resultado = resultado;
		this.analisis_id = analisis_id;
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public float getValor_minimo() {
		return valor_minimo;
	}

	public void setValor_minimo(float valor_minimo) {
		this.valor_minimo = valor_minimo;
	}

	public float getValor_maximo() {
		return valor_maximo;
	}

	public void setValor_maximo(float valor_maximo) {
		this.valor_maximo = valor_maximo;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public int getAnalisis_id() {
		return analisis_id;
	}

	public void setAnalisis_id(int analisis_id) {
		this.analisis_id = analisis_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(analisis_id, categoria, id, nombre, resultado, valor, valor_maximo, valor_minimo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Marcador other = (Marcador) obj;
		return analisis_id == other.analisis_id && Objects.equals(categoria, other.categoria) && id == other.id
				&& Objects.equals(nombre, other.nombre) && Objects.equals(resultado, other.resultado)
				&& Float.floatToIntBits(valor) == Float.floatToIntBits(other.valor)
				&& Float.floatToIntBits(valor_maximo) == Float.floatToIntBits(other.valor_maximo)
				&& Float.floatToIntBits(valor_minimo) == Float.floatToIntBits(other.valor_minimo);
	}

	@Override
	public String toString() {
		return "Marcador [id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + ", valor=" + valor
				+ ", valor_minimo=" + valor_minimo + ", valor_maximo=" + valor_maximo + ", resultado=" + resultado
				+ ", analisis_id=" + analisis_id + "]";
	}
}

