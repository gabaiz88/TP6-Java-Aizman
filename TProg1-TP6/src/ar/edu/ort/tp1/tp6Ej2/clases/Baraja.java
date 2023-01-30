package ar.edu.ort.tp1.tp6Ej2.clases;

import java.util.ArrayList;

import ar.edu.ort.tp1.tdas.implementaciones.ColaNodos;
import ar.edu.ort.tp1.tdas.implementaciones.PilaNodos;
import ar.edu.ort.tp1.tdas.interfaces.Cola;
import ar.edu.ort.tp1.tdas.interfaces.Pila;

public abstract class Baraja {

	protected Pila<Naipe> naipes;

	public Baraja(boolean mezclado) {
		naipes = new PilaNodos<Naipe>();
		cargarNaipes();
		if (mezclado) {
			mezclar();
		}
	}

	public void agregar(ArrayList<Naipe> nuevosNaipes) {
		while (!nuevosNaipes.isEmpty()) {
			naipes.push(nuevosNaipes.remove(0));			
		}
	}

	public void agregar(Cola<Naipe> nuevosNaipes) {
		while (!nuevosNaipes.isEmpty()) {
			naipes.push(nuevosNaipes.remove());			
		}
	}

	public void agregar(Naipe naipe) {
		naipes.push(naipe);
	}

	public void agregar(Pila<Naipe> nuevosNaipes) {
		while (!nuevosNaipes.isEmpty()) {
			naipes.push(nuevosNaipes.pop());			
		}
	}

	protected abstract void cargarNaipes();

	public Naipe extraer() {
		return naipes.pop();
	}

	
	public Cola<Naipe> extraer(int cuantos) {
		Cola <Naipe> colaAux = new ColaNodos<>();
		for (int i = 0; i < cuantos; i++) {
			if (!this.naipes.isEmpty()) {
				colaAux.add(this.naipes.pop());
			}
		}
		return colaAux;
	}

	public int getCantidadNaipes() {
		return naipes.size();
	}

	/**
	 * Explicar aca que hace y como funciona
	 */
	//Instancia una nueva Urna de tipo Naipe, que es generic, y hace un ciclo para sacar de la pila de naipes cada carta, mientras no este
	//vacia la pila, y lo agrega al arraylist de Urna. Al retornar los elementos a la pila de naipes lo hace con el remove de Urna que extrae
	// de manera "random".
	protected void mezclar() {
		Urna<Naipe> mezclador = new Urna<>();
		while (!naipes.isEmpty()) {
			mezclador.add(naipes.pop());
		}
		while (!mezclador.isEmpty()) {
			naipes.push(mezclador.remove());
		}
	}
}