package clases;

public class Hospedaje {
	private int codigoHospedaje;
	private int codigoIngreso;
	private int numeroBugalow;
	private String fechaSalida;
	private String horaSalida;
	private double costoHospedaje;
	private int estado;
	
	public Hospedaje(int codigoHospedaje, int codigoIngreso, int numeroBugalow, String fechaSalida, String horaSalida,
			double costoHospedaje, int estado) {
		super();
		this.codigoHospedaje = codigoHospedaje;
		this.codigoIngreso = codigoIngreso;
		this.numeroBugalow = numeroBugalow;
		this.fechaSalida = fechaSalida;
		this.horaSalida = horaSalida;
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

	public String getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
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
