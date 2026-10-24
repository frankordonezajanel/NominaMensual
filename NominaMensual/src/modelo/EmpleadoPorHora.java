package modelo;

public class EmpleadoPorHora extends Empleado {
	
	private double pagoPorHora;
	private double horasTrabajadas;
	
	public EmpleadoPorHora(String dpi, String nombre, double pagoPorHora, double horasTrabajadas) {
		super(dpi, nombre);
		this.pagoPorHora = pagoPorHora;
		this.horasTrabajadas = horasTrabajadas;
	}
	
	public double obtenerPagoPorHora() {
		return pagoPorHora;
	}
	
	public double obtenerHorasTrabajadas() {
		return horasTrabajadas;
	}

	@Override
	public double calcularPago() {
		return horasTrabajadas * pagoPorHora;
	}

	@Override
	public String obtenerTipo() {
		return "Por Hora";
	}

}
