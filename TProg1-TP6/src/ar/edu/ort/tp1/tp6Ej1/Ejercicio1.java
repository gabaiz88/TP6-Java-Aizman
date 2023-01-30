package ar.edu.ort.tp1.tp6Ej1;

import ar.edu.ort.tp1.tdas.implementaciones.PilaNodos;
import ar.edu.ort.tp1.tdas.interfaces.Pila;
import ar.edu.ort.tp1.tp6Ej1.clases.FabricanteDePelotas;
import ar.edu.ort.tp1.tp6Ej1.clases.PelotaDeTenis;
import ar.edu.ort.tp1.tp6Ej1.clases.TuboPelotasDeTenis;

public class Ejercicio1 {

	public static void main(String[] args) {
		// Obtener un tubo de pelotas de tenis del Fabricante
		TuboPelotasDeTenis tuboDePelotas = FabricanteDePelotas.fabricarTubo();

		// Sacar una pelota del tubo, usara una vez y volverla la guardar.
		PelotaDeTenis pelota = tuboDePelotas.extraer();
		pelota.usar();
		tuboDePelotas.guardar(pelota);
		
		// Desarrollar el metodo nombrado a continuacion.
		// Debe quedar tal como estaba al recibirlo.
		listarContenidoTubo(tuboDePelotas);
	}

	private static void listarContenidoTubo(TuboPelotasDeTenis tuboDePelotas) {
		TuboPelotasDeTenis tuboAux = new TuboPelotasDeTenis(3);
		while (!tuboDePelotas.estaVacio()) {
			PelotaDeTenis pelota = tuboDePelotas.extraer();
			System.out.println(pelota);
			tuboAux.guardar(pelota);
		}
		while (!tuboAux.estaVacio()) {
			tuboDePelotas.guardar(tuboAux.extraer());
		}
	}

}
