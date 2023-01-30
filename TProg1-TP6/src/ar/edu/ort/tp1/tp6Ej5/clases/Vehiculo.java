package ar.edu.ort.tp1.tp6Ej5.clases;

public abstract class Vehiculo {
	private static final String ERROR_CARGA_PATENTE = "Error en la carga de la patente";
	private static final int LARGO_MINIMO_PATENTE = 3;
	private String patente;
	private String marca;

	public Vehiculo(String patente, String marca) {
		setPatente(patente);
		this.marca = marca;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) throws IllegalArgumentException {
		if (!esPatenteValida((patente))) {
			throw new IllegalArgumentException(ERROR_CARGA_PATENTE);
		}
		this.patente = patente;
	}

	private boolean esPatenteValida(String patente) {
		return patente != null && patente.length() >= LARGO_MINIMO_PATENTE;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	protected abstract boolean autoDiagnostico();

	@Override
	public String toString() {
		return "Vehiculo [patente=" + patente + ", marca=" + marca + "]";
	}

}