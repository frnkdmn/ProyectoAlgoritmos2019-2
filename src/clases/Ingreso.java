package clases;

public class Ingreso {
	private int codigoIngreso;
	private int codigoSocio;
	private String fechaIngreso;
	private String horaIngreso;
	private int numeroInvitados;
	private double costoIngreso;
	private int estado;
	
	public Ingreso(int codigoIngreso, int codigoSocio, String fechaIngreso, String horaIngreso, int numeroInvitados,
			double costoIngreso, int estado) {
		super();
		this.codigoIngreso = codigoIngreso;
		this.codigoSocio = codigoSocio;
		this.fechaIngreso = fechaIngreso;
		this.horaIngreso = horaIngreso;
		this.numeroInvitados = numeroInvitados;
		this.costoIngreso = costoIngreso;
		this.estado = estado;
	}

	public int getCodigoIngreso() {
		return codigoIngreso;
	}

	public void setCodigoIngreso(int codigoIngreso) {
		this.codigoIngreso = codigoIngreso;
	}

	public int getCodigoSocio() {
		return codigoSocio;
	}

	public void setCodigoSocio(int codigoSocio) {
		this.codigoSocio = codigoSocio;
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getHoraIngreso() {
		return horaIngreso;
	}

	public void setHoraIngreso(String horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	public int getNumeroInvitados() {
		return numeroInvitados;
	}

	public void setNumeroInvitados(int numeroInvitados) {
		this.numeroInvitados = numeroInvitados;
	}

	public double getCostoIngreso() {
		return costoIngreso;
	}

	public void setCostoIngreso(double costoIngreso) {
		this.costoIngreso = costoIngreso;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
}
