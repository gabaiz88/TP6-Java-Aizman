package ar.edu.ort.tp1.tp6Ej4;

import ar.edu.ort.tp1.tp6Ej4.clases.Carga;
import ar.edu.ort.tp1.tp6Ej4.clases.Chofer;
import ar.edu.ort.tp1.tp6Ej4.clases.CompaniaDeTransportes;
import ar.edu.ort.tp1.tp6Ej4.clases.TipoVehiculo;

public class Ejercicio4 {
	private static CompaniaDeTransportes ct;
//COMPILABA PERO AHORA ME TIRA UN ERROR EL TOSTRING
	public static void main(String[] args) {
		// Creacion de la compania
		ct = new CompaniaDeTransportes();
		
		System.out.println("Carga de choferes...");
		altaChofer(12345678, "Aparicio Luna");
		altaChofer(23456789, "Marcos Gravevoz");
		altaChofer(34127856, "Kosher Waters");
		altaChofer(31426758, "Greta Barbol");
		altaChofer(24785236, "Esteban Quete");

		System.out.println("Carga de vehiculos...");
		altaVehiculo("AA 000 ZZ", TipoVehiculo.CAMION, 12000);
		altaVehiculo("FDE999", TipoVehiculo.CAMION_CON_TOLVA, 24000);
		altaVehiculo("CD 222 DC", TipoVehiculo.CAMIONETA, 3500);
		altaVehiculo("EF 333 FE", TipoVehiculo.CAMION, 9000);
		altaVehiculo("HG 444 GH", TipoVehiculo.CAMION_ACOPLADO, 20500);

		System.out.println("Baja de un chofer...");
		bajaChofer(24785236);
		altaChofer(91684500, "Rex Mifune");

		// ct.listarChoferes();
		// ct.listarVehiculos();

		// Procesar viajes

		System.out.println("Se asignan los vehiculos");
		asignarVehiculo(null, 12345678);
		asignarVehiculo("AA 000 ZZ", -1);
		asignarVehiculo("AA 000 ZZ", 12345678);
		asignarVehiculo("AA 000 ZZ", 12345678);
		asignarVehiculo("HG 444 GH", 31426758);
		asignarVehiculo("CD 222 DC", 91684500);
		asignarVehiculo("FDE999", 34127856);

		System.out.println("Se asignan las cargas");
		asignarCargaAVehiculo("Muebles", 3402.3);
		asignarCargaAVehiculo("Harina de trigo", 22402.98);
		asignarCargaAVehiculo("Acero", 18744);

		ct.listarChoferesLibres();
		ct.listarVehiculosListosParaPartir();
	}

	// Desarrollar todos los metodos que aparecen a continuacion teniendo en
	// cuenta la captura de excepciones.
	
	private static void bajaChofer(int dni) {
		try {
			Chofer baja = ct.bajaChofer(dni);
			System.out.println("El chofer " + baja.getNombre() + " ha sido dado de baja.");
		} catch (RuntimeException e) {
			System.out.println("Error al dar de baja un chofer.");
			System.out.println(e.getMessage());
		}
	}

	private static void asignarCargaAVehiculo(String descripcionCarga, double peso) {
		try {
			Carga carga = new Carga(descripcionCarga, peso);
			ct.asignarCarga(carga);
		} catch (RuntimeException e){
			System.out.println("Error en la asignacion de carga");
			System.out.println(e.getMessage());
		}
	}

	private static void altaVehiculo(String patente, TipoVehiculo tipoVehiculo, double cargaMaxima) {
		try{
			ct.altaVehiculo(patente, tipoVehiculo, cargaMaxima);
		} catch (RuntimeException e) {
			System.out.println("Error en el alta de un vehiculo");
			System.out.println(e.getMessage());
		}
	}

	private static void altaChofer(int dniChofer, String nombreCompleto) {
		try {
			ct.altaChofer(dniChofer, nombreCompleto);
		} catch (RuntimeException e){
			System.out.println("Error en el alta de un chofer");
			System.out.println(e.getMessage());
		}
	}

	private static void asignarVehiculo(String patente, int dniChofer) {
		try {
			ct.asignarVehiculo(patente, dniChofer);
		} catch (RuntimeException e){
			System.out.println("Error en la asinacion de un vehiculo");
			System.out.println(e.getMessage());
		}
	}
}