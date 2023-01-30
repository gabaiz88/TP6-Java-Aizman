package ar.edu.ort.tp1.tp6Ej3.clases;

import ar.edu.ort.tp1.tdas.implementaciones.ListaOrdenadaNodos;

public class ListaDeIdiomas extends ListaOrdenadaNodos<String, Idioma> {

	@Override
	public int compare(Idioma dato1, Idioma dato2) {
		return dato1.getClave().compareTo(dato2.getClave());
	}

	@Override
	public int compareByKey(String clave, Idioma elemento) {
		return clave.compareTo(elemento.getClave());
	}

	
}
