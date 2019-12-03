package controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import clases.Hospedaje;


public class HospedajeControlador {
private ArrayList<Hospedaje> lista = new ArrayList<Hospedaje>();
	
	public HospedajeControlador(){
		cargaData();
	}
	
	public void agregar(Hospedaje obj) {
		lista.add(obj);
	}

	public int tamaño() {
		return lista.size();
	}

	public Hospedaje obtener(int pos) {
		return lista.get(pos);
	}

	// métodos adicionales
	public Hospedaje buscarPorCodigo(int cod) {
		Hospedaje salida = null;
		for (Hospedaje x : lista) {
			if (x.getCodigoHospedaje() == cod) {
				salida = x;
				break;
			}
		}
		return salida;
	}

	public void eliminaPorCodigo(int cod) {
		for (Hospedaje x : lista) {
			if (x.getCodigoHospedaje() == cod) {
				lista.remove(x);
				break;
			}
		}
	}

	public void actualizar(Hospedaje aux) {
		for (Hospedaje x : lista) {
			if (x.getCodigoHospedaje() == aux.getCodigoHospedaje()) {
				lista.set(lista.indexOf(x), aux);
				break;
			}
		}
	}
	public int codigoCorrelativo(){
		if(tamaño() ==0)
			return 50001;
		else{
			int ultCodigo = obtener(tamaño()-1).getCodigoHospedaje();
			return ultCodigo + 1;
		}
	}
	private void cargaData() {
		BufferedReader br = null;
		try {
			// Se lee el archivo txt
			br = new BufferedReader(new FileReader("hospedajes.txt"));
			String linea = null;
			// readLine captura una linea
			while ((linea = br.readLine()) != null) {
				// Se separa los datos
				String[] s = linea.split(";");
				Hospedaje obj = new Hospedaje();
				obj.setCodigoHospedaje(Integer.parseInt(s[0]));
				obj.setCodigoIngreso(Integer.parseInt(s[1]));
				obj.setNumeroBugalow(Integer.parseInt(s[2]));
				obj.setFechaEntrada(s[3]);
				obj.setFechaSalida(s[4]);
				obj.setCostoHospedaje(Double.parseDouble(s[5]));
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
			pw = new PrintWriter(new FileWriter("hospedajes.txt"));
			for (Hospedaje x : lista) {
				linea = x.getCodigoHospedaje() + ";" + x.getCodigoIngreso() + ";" + x.getNumeroBugalow() + ";"
						+ x.getFechaEntrada() + ";" + x.getFechaSalida()+";"+x.getCostoHospedaje()+";"+x.getEstado();

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
