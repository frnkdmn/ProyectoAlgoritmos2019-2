package clases;

public class Hospedaje {
	private int codigoHospedaje;
	private int codigoIngreso;
	private int numeroBugalow;
	private String fechaEntrada;
	private String fechaSalida;
	private double costoHospedaje;
	private int estado;
	
	
	public Hospedaje() {
		super();
	}

	public Hospedaje(int codigoHospedaje, int codigoIngreso, int numeroBugalow, String fechaEntrada, String fechaSalida,
			double costoHospedaje, int estado) {
		super();
		this.codigoHospedaje = codigoHospedaje;
		this.codigoIngreso = codigoIngreso;
		this.numeroBugalow = numeroBugalow;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.costoHospedaje = costoHospedaje;
		this.estado = estado;
	}

	public int getCodigoHospedaje() {
		return codigoHospedaje;
	}

	public void setCodigoHospedaje(int codigoHospedaje) {
		this.codigoHospedaje = codigoHospedaje;
	}

	public int getCodigoIngreso() {
		return codigoIngreso;
	}

	public void setCodigoIngreso(int codigoIngreso) {
		this.codigoIngreso = codigoIngreso;
	}

	public int getNumeroBugalow() {
		return numeroBugalow;
	}

	public void setNumeroBugalow(int numeroBugalow) {
		this.numeroBugalow = numeroBugalow;
	}

	public String getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(String fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public String getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public double getCostoHospedaje() {
		return costoHospedaje;
	}

	public void setCostoHospedaje(double costoHospedaje) {
		this.costoHospedaje = costoHospedaje;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	

}
