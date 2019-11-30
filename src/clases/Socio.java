package clases;

public class Socio {
	private int codigoSocio;
	private String nombres;
	private String apellidos;
	private String dni;
	private String telefono;
	
	
	public Socio() {
		super();
	}

	public Socio(int codigoSocio, String nombres, String apellidos, String dni, String telefono) {
		super();
		this.codigoSocio = codigoSocio;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.dni = dni;
		this.telefono = telefono;
	}

	public int getCodigoSocio() {
		return codigoSocio;
	}

	public void setCodigoSocio(int codigoSocio) {
		this.codigoSocio = codigoSocio;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
}
