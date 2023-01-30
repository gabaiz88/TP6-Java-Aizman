package ar.edu.ort.tp1.tp6Ej4.clases;

import ar.edu.ort.tp1.tdas.implementaciones.ListaOrdenadaNodos;

public class ListaVehiculos extends ListaOrdenadaNodos<String, Vehiculo> {

	@Override
	public int compare(Vehiculo vehiculo1, Vehiculo vehiculo2) {
		return vehiculo1.getPatente().compareTo(vehiculo2.getPatente());
	}

	@Override
	public int compareByKey(String patente, Vehiculo vehiculo) {
		return patente.compareTo(vehiculo.getPatente());
	}

}
