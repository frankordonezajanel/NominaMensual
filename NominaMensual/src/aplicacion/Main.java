package aplicacion;

import java.util.ArrayList;
import java.util.Scanner;


//Se importa para que pueda trabajar con estos modelos 
import modelo.Empleado;
import modelo.EmpleadoPorHora;
import modelo.EmpleadoFijo;

public class Main {
	
	private static Scanner sc = new Scanner(System.in);
	
	private static ArrayList<Empleado> empleados = new ArrayList<>();
	
	
	//Arreglo de meses y condiciones antes de ejecutarse el codigo 
	public static String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo",
			"Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	
	// Si el usuario escribe 3 debe de aparecer marzo    a tres restele uno y  el resultado es dos
	
	
	private static int numeroMes;
	
	
	//Aqui inicia la Aplicacion Empieza a leer linea por linea 
	
	public static void main(String[] args) {
		limpiarPantalla();
		System.out.println("BIENVENIDOS AL CALCULO DE NOMINAS");
		
		numeroMes = 0;
		while (numeroMes < 1 || numeroMes > 12 ) {
			System.out.print("Ingrese el mes a trabajar del 1 al 12:");
			
			try {
				// Trim "---- Frank"   --> "Frank" le quita los espacios
				numeroMes = Integer.parseInt(sc.nextLine().trim());
				
				if (numeroMes < 1 || numeroMes >  12 ) {
					System.out.println("Error, el mes debe de ser entre 1 al 12");
				}
			} catch (NumberFormatException e){
				System.out.println("Error, debe de ingresar un numero");
			}
			
		}
		limpiarPantalla();

		int opcion;
		do {
			limpiarPantalla();
			System.out.println("NOMINA CORRESPONDIENTE AL MES DE : " + meses[numeroMes - 1].toUpperCase() );
			System.out.println("1. - Ingreso de Nomina");
			System.out.println("2. - Impresión de Nomina");
			System.out.println("3. - Salir");
		
			opcion = leerEntero("Seleccione una opcion:" , 1,3);
		
			if (opcion == 1) {
				ingresarEmpleados();
			} else if (opcion == 2) {
				imprimirNomina();
			}
			
		} while (opcion != 3);
		
		System.out.println("Gracias por visitarnos");

	}
	
	//Opcion No. uno que es el ingreso de nominas
	//tendra Ingreso de trabajdores por Hora o fijos
	private static void ingresarEmpleados() {
		int otro;
		do {
			limpiarPantalla();
			System.out.println("NÓMINA DEL MES:" + meses[numeroMes -1].toUpperCase());
			System.out.println("INGRESO DE EMPLEADOS A NOMINA");
			System.out.println("1 - Empleados Fijos");
			System.out.println("2 - Empleados por hora");
			int tipo = leerEntero("Seleccione el tipo de empleado a ingresar: ", 1, 2);
			
			limpiarPantalla();
			System.out.println("NÓMINA DEL MES:" + meses[numeroMes -1].toUpperCase());
			if(tipo == 1) {
				System.out.println("INGRESO DE EMPLEADO FIJO");
			} else {
				System.out.println("INGRESO DE EMPLEADOS POR HORA");
			}
			
			String dpi;
			
			do {
				System.out.print("ingrese el NO. DPI:  ");
				dpi = sc.nextLine().trim();
				
				if (!dpi.matches("\\d{13}")) {
					System.out.println("Error, el DPI debe de tener exactamente 13 digitos");
				}
				
			} while (!dpi.matches("\\d{13}"));
			
			String nombre;
			do {
				System.out.print("Ingrese el Nombre:  ");
				nombre = sc.nextLine().trim();
	
				if(nombre.isEmpty()) {
					System.out.println("Error, debe de escribir un nombre");
				}
			} while (nombre.isEmpty());
			
			Empleado nuevo;
			
			if (tipo == 1) {
				double sueldo = leerDecimales("Sueldo Mensual Q: ");
				double bono = leerDecimales("Bonificacion para este mes Q: ");
				
				nuevo = new EmpleadoFijo(dpi, nombre, sueldo, bono);
			} else {
				double tarifa = leerDecimales("Pago por Hora Q: ");
				double horas = leerDecimales("Horas trabajadas este mes: ");
				nuevo = new EmpleadoPorHora(dpi, nombre, tarifa, horas);
			}
			
			// guardar el empleado en el arreglo que existe durante la sesion
			
			empleados.add(nuevo);
			limpiarPantalla();
			System.out.printf("Pago de %s = Q %.2f%n", nuevo.obtenerNombre(), nuevo.calcularPago());
			System.out.println("Empleado Agregado Exitosamente");
			System.out.println("Cantidad de Empleados Actuales = " + empleados.size());
			System.out.println();
			System.out.println("Desea Agregar otro empleado? ");
			System.out.println("1. Sí, ingresar otro");
			System.out.println("2. volver al menu principal");
			otro = leerEntero("Seleccione una opcion: ", 1, 2);
			
			
		} while (otro == 1);
	}
	
	private static void imprimirNomina() {
		limpiarPantalla();
		System.out.println("NOMINA DEL MES: " + meses[numeroMes -1].toUpperCase());
		
		double total = 0;
		
		if (empleados.size() == 0) {
			System.out.println("No hay empleados Ingresados para este mes");
		} else {
			//Funcion investigada para mostrar los datos de manera centrada
			System.out.printf("%-15s %-20s %-10s %10s%n", "DPI", "Nombre", "Tipo", "Pago Q");
			
			for ( Empleado e : empleados) {
				System.out.printf("%-15s %-20s %-10s %10.2f%n",
						e.obtenerDpi(), e.obtenerNombre(), e.obtenerTipo(), e.calcularPago());
				total = total + e.calcularPago();
			}
		}
		
		System.out.println("");
		System.out.println("Total de Trabajadores: " + empleados.size());
		System.out.printf("Total de nómina: Q %.2f%n", total);
		System.out.println("Presione Enter para volver al menu principal");
		sc.nextLine();
	}
	
	private static double leerDecimales(String mensaje) {
		while (true) {
			System.out.print(mensaje);
			
			try {
				double numero = Double.parseDouble(sc.nextLine().trim());
				if(Double.isFinite(numero) && numero > 0) {
					return numero;
				}
				
				System.out.println("Error, debe de ingresar un numero mayor a cero");
			} catch (NumberFormatException e) {
				System.out.println("Error, debe de ingresar un numero y no una letra");
			}
		}
		
	}
	
	private static int leerEntero(String mensaje, int min, int max) {
		while (true) {
			System.out.print(mensaje);
			
			try {
				//Estamos Validadno que sea numero y que sea mayor a cero y quitando los espacios
				int numero = Integer.parseInt(sc.nextLine().trim());
				// El numero es mayor o igual al numero minimo y si el numero es menor o igual al maximo
				if (numero >= min && numero <= max) {
					return numero;
				}
				
				System.out.println("Error, debe de ser un numero entre " + min + " y" + max );
			} catch (NumberFormatException e) {
				System.out.println("Error, debe de ingresar un numero y no una letra");
			};
		}
	}
	
	private static void limpiarPantalla() { 
		for (int i = 0; i < 40; i++) {
			System.out.println();
		}
	}

}
