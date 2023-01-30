package ar.edu.ort.tp1.tp6Ej3.clases;

import java.util.Scanner;



/**
 * Esta clase debe:
 * - Cargar uno o mas diccionarios (uno en cada idioma, al menos dos) para que pueda:
 *    -- Mostrar errores
 *    -- Dar la bienvenida
 *    -- Solicitar un nombre (para que el Saludador lo pida por teclado).
 *    -- Decirle que tiene un lindo nombre
 *    -- Mostrar un saludo de despedida usando el nombre pedido por teclado.
 * - Elegir un idioma
 * - Ejecutar una rutima que:
 *   -- Salude
 *   -- Pida el nombre
 *   -- Lo cargue por teclado
 *   -- Diga que es un lindo nombre
 *   -- Despedir usando el nombre
 */
public class Saludador {

	private Diccionario diccionario;
	private String idioma;
	private Scanner input;
	
	public Saludador(String idioma) {
		input = new Scanner(System.in);
		diccionario = new Diccionario();
		cargarDiccionario(diccionario);
		elegirIdioma(idioma);
	}

	private void cargarDiccionario(Diccionario diccionario) {
		diccionario.agregarIdioma("ES", "Español");
		diccionario.agregarIdioma("EN", "English");
		diccionario.agregarTermino("ES", "IDIOMA_INVALIDO", "El idioma elegido es invalido.");
		diccionario.agregarTermino("EN", "IDIOMA_INVALIDO", "The selected language is invalid.");
		diccionario.agregarTermino("ES", "BIENVENIDA", "¡Hola!");
		diccionario.agregarTermino("EN", "BIENVENIDA", "Hello!");
		diccionario.agregarTermino("ES", "PEDIR_NOMBRE", "Ingresa tu nombre por favor: ");
		diccionario.agregarTermino("EN", "PEDIR_NOMBRE", "Enter your name, please: ");
		diccionario.agregarTermino("ES", "MENSAJE", "¡Que lindo nombre!");
		diccionario.agregarTermino("EN", "MENSAJE", "What a pretty name!");
		diccionario.agregarTermino("ES", "DESPEDIDA", "¡Adios %s! ¡Que tengas un buen dia!");
		diccionario.agregarTermino("EN", "DESPEDIDA", "Good bye %s! Have a nice day!");
	}

	public void elegirIdioma(String idioma) throws RuntimeException {
		if (diccionario.idiomaValido(idioma)) {
			this.idioma = idioma;
		} else {
			throw new RuntimeException(diccionario.obtenerTermino(this.idioma, "IDIOMA_INVALIDO"));
		}
	}

	public void run() {
		String nombre;
		System.out.println(diccionario.obtenerTermino(idioma, "BIENVENIDA"));
		System.out.print(diccionario.obtenerTermino(idioma, "PEDIR_NOMBRE"));
		nombre = input.nextLine();
		System.out.println(diccionario.obtenerTermino(idioma, "MENSAJE"));
		System.out.printf(diccionario.obtenerTermino(idioma, "DESPEDIDA"), nombre);
		System.out.println();
	}
}
