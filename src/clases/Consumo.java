package clases;

public class Consumo {
	private int codigoConsumo;
	private int codigoIngreso;
	private String detalleProducto;
	private double precio;
	private int cantidad;
	private int estado;

	
	public Consumo() {
		super();
	}

	public Consumo(int codigoConsumo,int codigoIngreso, String detalleProducto, double precio, int cantidad, int estado) {
		super();
		this.codigoConsumo = codigoConsumo;
		this.codigoIngreso = codigoIngreso;
		this.detalleProducto = detalleProducto;
		this.precio = precio;
		this.cantidad = cantidad;
		this.estado = estado;
	}
	
	public int getCodigoConsumo() {
		return codigoConsumo;
	}

	public void setCodigoConsumo(int codigoConsumo) {
		this.codigoConsumo = codigoConsumo;
	}

	public double totalConsumo(){
		return precio  * cantidad;
	}
	public int getCodigoIngreso() {
		return codigoIngreso;
	}

	public void setCodigoIngreso(int codigoIngreso) {
		this.codigoIngreso = codigoIngreso;
	}

	public String getDetalleProducto() {
		return detalleProducto;
	}

	public void setDetalleProducto(String detalleProducto) {
		this.detalleProducto = detalleProducto;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	
}
