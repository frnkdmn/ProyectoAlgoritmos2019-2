package clases;

public class Producto {
	private int codigoProducto;
	private String detalle;
	private double precioUnitario;
	private int stock;
	
	public Producto() {
		super();
	}

	public Producto(int codigoProducto, String detalle, double precioUnitario, int stock) {
		super();
		this.codigoProducto = codigoProducto;
		this.detalle = detalle;
		this.precioUnitario = precioUnitario;
		this.stock = stock;
	}

	public int getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
