package ar.edu.ort.tp1.tp6Ej5.clases;

public class Moto extends Vehiculo {
	private static final int CILINDRADA_MINIMA = 125;
	private int cilindrada;
	private boolean limitador;

	public Moto(String patente, String marca, int cilindrada, boolean limitador) {
		super(patente, marca);
		this.cilindrada = cilindrada;
		this.limitador = limitador;
	}

	public boolean tieneLimitador() {
		return limitador;
	}

	@Override
	public boolean autoDiagnostico() {
		return limitador;
	}

	public int getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(int cilindrada) throws IllegalArgumentException {
		if (this.cilindrada < CILINDRADA_MINIMA) {
			throw new IllegalArgumentException("La cilindrada no puede ser menor a " + CILINDRADA_MINIMA);
		}
		this.cilindrada = cilindrada;
	}
}