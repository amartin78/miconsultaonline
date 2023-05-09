package modelo;

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
	private float valorMinimo;
	private float valorMaximo;
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
	 * @param valorMinimo
	 * @param valorMaximo
	 * @param resultado
	 * @param analisis_id
	 */
	public Marcador(String nombre, String categoria, float valor, float valorMinimo, float valorMaximo,
			String resultado) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.valor = valor;
		this.valorMinimo = valorMinimo;
		this.valorMaximo = valorMaximo;
		this.resultado = resultado;
	}

	/**
	 * Constructor de un objeto de tipo Marcador para realizar una actualización.
	 * 
	 * @param id
	 * @param nombre
	 * @param categoria
	 * @param valor
	 * @param valorMinimo
	 * @param valorMaximo
	 * @param resultado
	 * @param analisis_id
	 */
	public Marcador(int id, String nombre, String categoria, float valor, float valorMinimo, float valorMaximo,
			String resultado, int analisis_id) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.valor = valor;
		this.valorMinimo = valorMinimo;
		this.valorMaximo = valorMaximo;
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

	public float getValorMinimo() {
		return valorMinimo;
	}

	public void setValorMinimo(float valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	public float getValorMaximo() {
		return valorMaximo;
	}

	public void getValorMaximo(float valorMaximo) {
		this.valorMaximo = valorMaximo;
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
		return Objects.hash(analisis_id, categoria, id, nombre, resultado, valor, valorMaximo, valorMinimo);
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
				&& Float.floatToIntBits(valorMaximo) == Float.floatToIntBits(other.valorMaximo)
				&& Float.floatToIntBits(valorMinimo) == Float.floatToIntBits(other.valorMinimo);
	}

	@Override
	public String toString() {
		return "Marcador [id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + ", valor=" + valor
				+ ", valorMinimo=" + valorMinimo + ", valorMaximo=" + valorMaximo + ", resultado=" + resultado
				+ ", analisis_id=" + analisis_id + "]";
	}
}

