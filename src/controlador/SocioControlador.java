package controlador;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;


import clases.Socio;

public class SocioControlador {
	private ArrayList<Socio> lista = new ArrayList<Socio>();
	
	public SocioControlador(){
		cargaData();
	}
	
	public void agregar(Socio obj) {
		lista.add(obj);
	}

	public int tamaño() {
		return lista.size();
	}

	public Socio obtener(int pos) {
		return lista.get(pos);
	}

	// métodos adicionales
	public Socio buscarPorCodigo(int cod) {
		Socio salida = null;
		for (Socio x : lista) {
			if (x.getCodigoSocio() == cod) {
				salida = x;
				break;
			}
		}
		return salida;
	}

	public void eliminaPorCodigo(int cod) {
		for (Socio x : lista) {
			if (x.getCodigoSocio() == cod) {
				lista.remove(x);
				break;
			}
		}
	}

	public void actualizar(Socio aux) {
		for (Socio x : lista) {
			if (x.getCodigoSocio() == aux.getCodigoSocio()) {
				lista.set(lista.indexOf(x), aux);
				break;
			}
		}
	}
	public int codigoCorrelativo(){
		if(tamaño() ==0)
			return 10001;
		else{
			int ultCodigo = obtener(tamaño()-1).getCodigoSocio();
			return ultCodigo + 1;
		}
	}
	private void cargaData() {
		BufferedReader br = null;
		try {
			// Se lee el archivo txt
			br = new BufferedReader(new FileReader("Socios.txt"));
			String linea = null;
			// readLine captura una linea
			while ((linea = br.readLine()) != null) {
				// Se separa los datos
				String[] s = linea.split(";");
				Socio obj = new Socio();
				obj.setCodigoSocio(Integer.parseInt(s[0]));
				obj.setNombres(s[1]);
				obj.setApellidos(s[2]);;
				obj.setDni(s[3]);
				obj.setTelefono(s[4]);
				lista.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (Exception e) {
			}
		}
	}

	public void grabarData() {
		PrintWriter pw = null;
		try {
			String linea;
			pw = new PrintWriter(new FileWriter("Socios.txt"));
			for (Socio x : lista) {
				linea = x.getCodigoSocio() + ";" + x.getNombres() + ";" + x.getApellidos() + ";"
						+ x.getDni() + ";" + x.getTelefono();

				pw.println(linea);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pw != null)
					pw.close();
			} catch (Exception e) {
			}
		}
	}
}
