package ar.edu.ort.tp1.tp6Ej4.clases;

import ar.edu.ort.tp1.tdas.implementaciones.ColaNodos;
import ar.edu.ort.tp1.tdas.interfaces.Cola;

public class CompaniaDeTransportes {
	private static int ultimoLegajo = 0;

	private ListaChoferes staff;
	private ListaVehiculos flota;
	private Cola<Vehiculo> viajesListos;

	// 1 - Crear las estructuras propuestas
	public CompaniaDeTransportes() {
		staff = new ListaChoferes();
		flota = new ListaVehiculos();
		viajesListos = new ColaNodos<>();
	}

	public void altaChofer(int dni, String nombre) {
		Chofer chofer = staff.search(dni);
		if (chofer != null) {
			throw new RuntimeException("Chofer existente!");
		}
		staff.add(new Chofer(dni, proximoLegajo(), nombre));
	}

	public Chofer bajaChofer(int dni) {
		Chofer chofer = staff.search(dni);
		if (chofer != null) {
			if (chofer.getVehiculo() == null) {
				staff.remove(chofer);
			} else {
				throw new RuntimeException("No se puede despedir a un chofer en viaje!");
			}
		}
		return chofer;
	}
	
	public void altaVehiculo(String patente, TipoVehiculo tipoVehiculo, double capacidadDeCarga) {
		Vehiculo vehiculo = flota.search(patente);
		if (vehiculo != null) {
			throw new RuntimeException("Vehiculo existente!");
		}
		flota.add(new Vehiculo(patente, tipoVehiculo, capacidadDeCarga));
	}

	private static int proximoLegajo() {
		return ++ultimoLegajo;
	}

	public void listarChoferes() {
		for (Chofer chofer : staff) {
			System.out.println(chofer);
		}
	}

	public void listarVehiculos() {
		for (Vehiculo vehiculo : flota) {
			System.out.println(vehiculo);
		}
	}

	// 2 - Asignar un vehiculo a un chofer (utilizando Excepciones)
	public void asignarVehiculo(String patenteVehiculo, int dniChofer) {
			Chofer choferABuscar = null;
			Vehiculo vehiculoABuscar = null;
			for (Vehiculo v : this.flota) {
				if (v.getPatente() == patenteVehiculo) {
					vehiculoABuscar = v;
				}
			}
			if (vehiculoABuscar == null) {
				throw new RuntimeException("Vehiculo inexistente");
			} else if (vehiculoABuscar.getChoferACargo() != null) {
				throw new RuntimeException("Vehiculo con chofer asignado");
			}
			if (dniChofer < 0 || dniChofer > 999999999) {
				throw new IllegalArgumentException("Debe Ingresar un documento Valido");
			}
			for (Chofer c : this.staff) {
				if (c.getDni() == dniChofer) {
					choferABuscar = c;
				}
			}
			if (choferABuscar == null) {
				throw new RuntimeException("Chofer inexistente");
			}
			choferABuscar.asignarVehiculo(vehiculoABuscar);
			vehiculoABuscar.asignarChofer(choferABuscar);
	}

	// 3 - Asignar una carga (utilizando Excepciones) al vehiculo que más se
	// adapte al peso de la carga.
	// a - Vehiculo con conductor asignado.
	// b - No debe tener carga asignada.
	// c - Su capacidad debe ser mayor o igual al peso de la carga.
	// De todos los vehículos posibles debe quedarse con el menor de todos
	// ellos.
	//
	// Si se logra asignar la carga el vehiculo debe agregarse a la cola de
	// vehiculos listos para partir.
	public void asignarCarga(Carga carga) {
		System.out.println("\n*** ASIGNACION DE CARGA ***");
		if (carga == null) {
			throw new IllegalArgumentException("Se debe ingresar una carga");
		} else {
			Vehiculo ve = null;
			for (Vehiculo v : this.flota) {
				if (ve == null) {
					if (v != null) {
						if (v.getCargaAsignada() == null && v.getCapacidadDeCarga() >= carga.getPeso() 
								&& v.getChoferACargo() != null) {
							v.asignarCarga(carga);
							this.viajesListos.add(v);
							System.out.println(carga.toString());
							ve = v;
						}
					} else {
						System.out.println("No existe el vehiculo");
					}
				}
			}
		}
	}
	
	// 4 - Listar choferes libres (que no estan de viaje)
	public void listarChoferesLibres() {
		System.out.println("\n--- Choferes Libres ---");
		for (Chofer chofer : this.staff) {
			if (!chofer.ListoParaViajar()) {
				System.out.println(chofer.getDni() + " - " + chofer.getNombre());
			}
		}
	}

	// 5 - Listar los vehiculos listos para salir de viaje (la estructura debe
	// quedar en el orden inicial).
	public void listarVehiculosListosParaPartir() {
		Vehiculo vCentinela = null;
		this.viajesListos.add(vCentinela);
		Vehiculo v = this.viajesListos.remove();
		while (v != vCentinela) {
			Carga carga = v.getCargaAsignada();
			System.out.println();
			System.out.println(v.toString());
			this.viajesListos.add(v);
			v = this.viajesListos.remove();
		}
	}
}