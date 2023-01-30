package ar.edu.ort.tp1.tp6Ej5.clases;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import ar.edu.ort.tp1.tdas.implementaciones.ColaNodos;
import ar.edu.ort.tp1.tdas.interfaces.Cola;

public class Taller {
	private static final int SERVICIOS_POSIBLES = 5;
	private static final int MAX_TRABAJOS_DIARIOS = 50;
	private static float PRECIO_HORA = 3000;
	private static Scanner input = new Scanner(System.in);
	private Servicio [] servicios;
	private ListaTrabajosPendientes trabajosPendientes;
	private ArrayList<Trabajo> trabajosRealizados;
	private Cola<Vehiculo> vehiculosEnPuerta;

	public Taller() {
		vehiculosEnPuerta = new ColaNodos<>(MAX_TRABAJOS_DIARIOS);
		trabajosRealizados = new ArrayList<>();
		trabajosPendientes = new ListaTrabajosPendientes();
		cargarServicios();
	}

	/**
	 * Procesa el ingreso de un vehiculo
	 * @param patente
	 * @param marca
	 * @param airbag
	 */
	public void agregarVehiculoEnEspera(String patente, String marca, EstadoAirbag airbag) {
		try {
			Vehiculo auto = new Auto(patente, marca, airbag);
			if (!this.vehiculosEnPuerta.isFull()) {
				agregarVehiculoEnEspera(auto);
			}
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Procesa el ingreso de un vehiculo
	 * @param patente
	 * @param marca
	 * @param cilindrada
	 * @param tieneLimitador
	 */
	public void agregarVehiculoEnEspera(String patente, String marca, int cilindrada, boolean tieneLimitador) {
		try {
			Vehiculo moto = new Moto(patente, marca, cilindrada, tieneLimitador);
			if (!this.vehiculosEnPuerta.isFull()) {
				agregarVehiculoEnEspera(moto);
			}
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * Procesa el ingreso de un vehiculo
	 * @param vehiculo
	 */
	private void agregarVehiculoEnEspera(Vehiculo vehiculo) {
		vehiculosEnPuerta.add(vehiculo);
	}

	/**
	 * Crea una estructura a nivel de la clase que guarda todos los servicios
	 * posibles, los que luego seran asignados a los vehiculos que ingresen al taller.
	 */
	public void cargarServicios() {
		String[] nombresServicios = { "Cambio de Bateria", "Cambio Aceite y Filtro", "Alineacion y Balanceo",
				"Cambio amortiguadores", "Servicio completo" };
		float[] duracionEstimadaServicio = { 0.5f, 1, 1.5f, 2, 3 };
		servicios = new Servicio[SERVICIOS_POSIBLES];
		for (int i = 0; i < SERVICIOS_POSIBLES; i++) {
			servicios[i] = new Servicio(i+1, nombresServicios[i], duracionEstimadaServicio[i]);
		}
	}

	private Trabajo crearTrabajo(Vehiculo vehiculo) {
		int numero = 0;
		numero = pedirServicio();
		Servicio servicio = servicios[numero - 1];
		Trabajo trabajo = new Trabajo(vehiculo, servicio);
		return trabajo;
	}

	public void informarImporteServicios() {
		float total = 0;
		for (Trabajo trabajo : trabajosRealizados) {
			System.out.println(trabajo);
			total += trabajo.getImporte();
		}
		System.out.println("La recaudacion del dia fue:" + total);
	}

	/**
	 * Genera e informa la cantidad de servicios realizados para cada tipo de servicio.
	 */
	public void informarResumenServicios() {
		int[] cantidadPorServicios = new int [this.servicios.length];
		for (Trabajo t : this.trabajosRealizados) {
			cantidadPorServicios[t.getCodigo()-1]++;
		}
		for (int i = 0; i < cantidadPorServicios.length; i++) {
			System.out.println(this.servicios[i].getDesc() + ": " + cantidadPorServicios[i]);
		}
	}

	private void mostrarMenuServicios() {
		for (int i = 0; i < servicios.length; i++) {
			System.out.println(servicios[i]);
		}
	}

	private int pedirServicio() {
		int numero = 0;
		boolean ok = false;
		while (!ok) {
			try {
				mostrarMenuServicios();
				System.out.println("Solicite un servicio 1-5:");
				numero = input.nextInt();
				if (numero > 0 && numero <= servicios.length) {
					ok = true;
				}
			} catch (InputMismatchException e) {
				System.out.println("Error. Ingrese un numero correcto");
			} finally {
				input.nextLine();
			}
		}
		return numero;
	}

	/**
	 * Asumiendo que ya todos los vehiculos en espera son validos agrega
	 * a cada uno a los trabajos pendientes. Al agregarlos pide tambien
	 * el servicio a realizar. (Para crear un Trabajo hacen falta un Vehiculo y un Servicio.
	 */
	public void procesarIngresoVehiculos() {
		Vehiculo v; 
		while(!this.vehiculosEnPuerta.isEmpty()) {
			v = this.vehiculosEnPuerta.remove();
			System.out.println("Ingreso el Vehiculo " + v.getPatente());
			this.trabajosPendientes.add(crearTrabajo(v));
		}
	}

	public void procesarServicios() {
		Trabajo trabajo;
		while (!trabajosPendientes.isEmpty()) {
			trabajo = trabajosPendientes.removeAt(0);
			trabajo.aplicarPrecioHora(PRECIO_HORA);
			trabajosRealizados.add(trabajo);
		}
	}

	public void reportarTrabajosPendientes() {
		trabajosPendientes.reportar();
	}

	/**
	 * Para evitar contratiempos chequea que los vehiculos que esperan
	 * en la puerta cumplan con las nuevas condiciones de atencion.
	 * Como puede procesarse mas de una vez debe asegurarse de mantener
	 * el orden de los vehiculos en puerta.
	 */
	public void revisarVehiculosEnPuerta() {
		System.out.println("Vehiculos que no pueden ingresar al taller");
		Vehiculo vCentinela = null;
		this.vehiculosEnPuerta.add(vCentinela);
		Vehiculo vActual = this.vehiculosEnPuerta.remove();
		while (vActual != vCentinela) {
			if (vActual instanceof Moto) {
				if (((Moto) vActual).tieneLimitador()) {
					this.vehiculosEnPuerta.add(vActual);
				} else {
					System.out.println(vActual.toString() + "no cumple con los requisitos y es rechazado.");
				}
			} else if (vActual instanceof Auto) {
				if (!((Auto) vActual).getEstadoAirbag().equals(EstadoAirbag.NO_POSEE)) {
					this.vehiculosEnPuerta.add(vActual);
				} else {
					System.out.println(vActual.toString() + "no cumple con los requisitos y es rechazado.");
				}
			}
			vActual = this.vehiculosEnPuerta.remove();
		}
	}
}