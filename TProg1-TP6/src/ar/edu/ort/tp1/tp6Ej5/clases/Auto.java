package ar.edu.ort.tp1.tp6Ej5.clases;

public class Auto extends Vehiculo {

	private EstadoAirbag estadoAirbag;// si es 0 OK 1 defectuoso 2 no posee

	public Auto(String patente, String marca, EstadoAirbag estadoAirbag) {
		super(patente, marca);
		this.estadoAirbag = estadoAirbag;
	}

	private void setEstadoAirbag(EstadoAirbag airbag) {
		estadoAirbag = airbag;
	}

	public boolean tieneAirbag() {
		return estadoAirbag != EstadoAirbag.NO_POSEE;
	}

	public void colocarAirbag() {
		estadoAirbag = EstadoAirbag.OK;
	}

	public boolean quitarAirbag() {
		return estadoAirbag == EstadoAirbag.NO_POSEE;
	}

	public EstadoAirbag getEstadoAirbag() {
		return estadoAirbag;
	}

	@Override
	public boolean autoDiagnostico() {
		return tieneAirbag();
	}
}