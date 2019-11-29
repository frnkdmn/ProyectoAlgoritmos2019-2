package clases;

public class Bungalow {
	private int numeroBungalow;
	private int estado;
	private int categoria;
	private double precioPorDia;
	
	
	public Bungalow() {
		super();
	}

	public Bungalow(int numeroBungalow, int categoria, double precioPorDia, int estado) {
		super();
		this.numeroBungalow = numeroBungalow;
		this.categoria = categoria;
		this.precioPorDia = precioPorDia;
		this.estado = estado;
	}

	public int getNumeroBungalow() {
		return numeroBungalow;
	}

	public void setNumeroBungalow(int numeroBungalow) {
		this.numeroBungalow = numeroBungalow;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public double getPrecioPorDia() {
		return precioPorDia;
	}

	public void setPrecioPorDia(double precioPorDia) {
		this.precioPorDia = precioPorDia;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
}
