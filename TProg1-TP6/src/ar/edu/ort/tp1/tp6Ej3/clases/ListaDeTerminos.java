package ar.edu.ort.tp1.tp6Ej3.clases;

import ar.edu.ort.tp1.tdas.implementaciones.ListaOrdenadaNodos;

public class ListaDeTerminos extends ListaOrdenadaNodos<String, Termino> {

	@Override
	public int compare(Termino dato1, Termino dato2) {
		return dato1.getClave().compareTo(dato2.getClave());
	}

	@Override
	public int compareByKey(String clave, Termino elemento) {
		return clave.compareTo(elemento.getClave());
	}

	
}
