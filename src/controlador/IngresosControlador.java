package controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import clases.Ingreso;
import clases.Producto;

public class IngresosControlador {
	private ArrayList<Ingreso> lista = new ArrayList<Ingreso>();

	public IngresosControlador() {
		cargaData();
	}

	public void agregar(Ingreso obj) {
		lista.add(obj);
	}

	public int tamaño() {
		return lista.size();
	}

	public Ingreso obtener(int pos) {
		return lista.get(pos);
	}

	public void eliminar(Producto Obj) {
		lista.remove(Obj);
	}
	
	public void eliminaPorCodigo(int cod) {
		for (Ingreso x : lista) {
			if (x.getCodigoSocio() == cod) {
				lista.remove(x);
				break;
			}
		}
	}
	public void eliminaPorSocio(int cod) {
		for (Ingreso x : lista) {
			if (x.getCodigoSocio() == cod) {
				lista.remove(x);
				break;
			}
		}
	}
	public Ingreso buscarPorCodigo(int cod){
		Ingreso salida = null;
		for(Ingreso x: lista){
			if(x.getCodigoIngreso()==cod){
				salida = x;
				break;
			}
		}
		return salida;
	}

	public int codigoCorrelativo() {
		if (tamaño() == 0)
			return 40001;
		else {
			int ultCodigo = obtener(tamaño() - 1).getCodigoIngreso();
			return ultCodigo + 1;
		}
	}

	private void cargaData() {
		BufferedReader br = null;
		try {
			// Se lee el archivo txt
			br = new BufferedReader(new FileReader("ingresos.txt"));
			String linea = null;
			// readLine captura una linea

			while ((linea = br.readLine()) != null) {
				// Se separa los datos

				String[] s = linea.split(";");

				Ingreso obj = new Ingreso();
				obj.setCodigoIngreso(Integer.parseInt(s[0]));
				obj.setCodigoSocio(Integer.parseInt(s[1]));
				obj.setFechaIngreso(s[2]);
				obj.setHoraIngreso(s[3]);
				obj.setNumeroInvitados(Integer.parseInt(s[4]));
				obj.setCostoIngreso(Double.parseDouble(s[5]));
				obj.setEstado(Integer.parseInt(s[6]));
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
			pw = new PrintWriter(new FileWriter("ingresos.txt"));
			for (Ingreso x : lista) {
				linea = x.getCodigoIngreso() + ";" + x.getCodigoSocio() + ";" + x.getFechaIngreso() + ";"
						+ x.getHoraIngreso() + ";" + x.getNumeroInvitados() + ";" + x.getCostoIngreso() + ";"
						+ x.getEstado();

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
