package ar.edu.ort.tp1.tp6Ej5.clases;

import ar.edu.ort.tp1.tdas.implementaciones.ListaOrdenadaNodos;

public class ListaTrabajosPendientes extends ListaOrdenadaNodos<Integer, Trabajo> implements Reportable{

	@Override
	public int compare(Trabajo dato1, Trabajo dato2) {
		return dato1.getCodigo() - dato2.getCodigo();
	}

	@Override
	public int compareByKey(Integer codigo, Trabajo dato) {
		return codigo - dato.getCodigo();
	}

	@Override
	public String reportar() {
		for (Trabajo t : this) {
			System.out.println(t.reportar());
		}
		return null;
	}

}
