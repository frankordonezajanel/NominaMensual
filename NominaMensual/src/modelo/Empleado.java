package modelo;

public abstract class Empleado {
	
	private String dpi;
	private String nombre;
	
	public Empleado(String dpi, String nombre) {
		this.dpi = dpi;
		this.nombre = nombre;
	}
	
	public String obtenerNombre() {
		return nombre;
	}
	
	public String obtenerDpi() {
		return dpi;
	}

	public abstract double calcularPago();
	
	public abstract String obtenerTipo();
	
}
