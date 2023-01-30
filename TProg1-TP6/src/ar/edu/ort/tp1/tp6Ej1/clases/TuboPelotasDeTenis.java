package ar.edu.ort.tp1.tp6Ej1.clases;

import ar.edu.ort.tp1.tdas.implementaciones.PilaNodos;
import ar.edu.ort.tp1.tdas.interfaces.Pila;

public class TuboPelotasDeTenis {
	private Pila<PelotaDeTenis> pelotas;
	
	public TuboPelotasDeTenis(int cantidadDeElementos) {
		pelotas = new PilaNodos<>(cantidadDeElementos);
	}

	public boolean estaLleno() {
		return pelotas.isFull();
	}

	public boolean estaVacio() {
		return pelotas.isEmpty();
	}

	public PelotaDeTenis extraer() {
		return pelotas.pop();
	}

	public void guardar(PelotaDeTenis pelota) {
		pelotas.push(pelota);
	}
}
