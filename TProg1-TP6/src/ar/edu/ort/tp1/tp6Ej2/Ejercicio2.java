package ar.edu.ort.tp1.tp6Ej2;

import ar.edu.ort.tp1.tdas.interfaces.Cola;
import ar.edu.ort.tp1.tp6Ej2.clases.BarajaEspaniola;
import ar.edu.ort.tp1.tp6Ej2.clases.Comodin;
import ar.edu.ort.tp1.tp6Ej2.clases.Naipe;
import ar.edu.ort.tp1.tp6Ej2.clases.NaipeNumerado;

public class Ejercicio2 {

	public static void main(String[] args) {
		BarajaEspaniola mazo = new BarajaEspaniola(true);
		Cola<Naipe> monton = mazo.extraer(10);
		
		boolean hayComodines = revisar(monton);
		
		if (hayComodines) {
			System.out.println("Se encontraron comodines");
		} else {
			System.out.println("No se encontraron comodines");
		}
	}

	private static boolean revisar(Cola<Naipe> monton) {
		boolean hay = false;
		Naipe naipe = null;
		// creamos un naipe inexistente (con numero 0)
		NaipeNumerado extra = new NaipeNumerado(0, BarajaEspaniola.PaloEspaniol.BASTOS);
		monton.add(extra);
		naipe = monton.remove();
		while (naipe != extra) {
			if (naipe instanceof Comodin) {
				hay = true;
			}
			monton.add(naipe);
			naipe = monton.remove();
		}
		return hay;
	}

}