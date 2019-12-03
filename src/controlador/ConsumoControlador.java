package controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import clases.Consumo;

public class ConsumoControlador {
	private ArrayList<Consumo> lista = new ArrayList<Consumo>();

	public ConsumoControlador() {
		cargaData();
	}

	public void agregar(Consumo obj) {
		lista.add(obj);
	}

	public int tamaño() {
		return lista.size();
	}

	public Consumo obtener(int pos) {
		return lista.get(pos);
	}

	public Consumo buscarPorCodigo(int cod) {
		Consumo salida = null;
		for (Consumo x : lista) {
			if (x.getCodigoConsumo() == cod) {
				salida = x;
				break;
			}
		}
		return salida;
	}

	public Consumo buscarPorIngreso(int cod) {
		Consumo salida = null;
		for (Consumo x : lista) {
			if (x.getCodigoIngreso() == cod) {
				salida = x;
				break;
			}
		}
		return salida;
	}

	public void eliminaPorCodigo(int cod) {
		for (Consumo x : lista) {
			if (x.getCodigoConsumo() == cod) {
				lista.remove(x);
				break;
			}
		}
	}

	public void actualizar(Consumo aux) {
		for (Consumo x : lista) {
			if (x.getCodigoConsumo() == aux.getCodigoConsumo()) {
				lista.set(lista.indexOf(x), aux);
				break;
			}
		}
	}

	public int codigoCorrelativo() {
		if (tamaño() == 0)
			return 10001;
		else {
			int ultCodigo = obtener(tamaño() - 1).getCodigoConsumo();
			return ultCodigo + 1;
		}
	}

	private void cargaData() {
		BufferedReader br = null;
		try {
			// Se lee el archivo txt
			br = new BufferedReader(new FileReader("consumos.txt"));
			String linea = null;
			// readLine captura una linea
			while ((linea = br.readLine()) != null) {
				// Se separa los datos
				String[] s = linea.split(";");
				Consumo obj = new Consumo();
				obj.setCodigoConsumo(Integer.parseInt(s[0]));
				obj.setCodigoIngreso(Integer.parseInt(s[1]));
				obj.setDetalleProducto(s[2]);
				obj.setPrecio(Double.parseDouble(s[3]));
				obj.setCantidad(Integer.parseInt(s[4]));
				obj.setEstado(Integer.parseInt(s[5]));
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
			pw = new PrintWriter(new FileWriter("consumos.txt"));
			for (Consumo x : lista) {
				linea = x.getCodigoConsumo() + ";" + x.getCodigoIngreso() + ";" + x.getDetalleProducto() + ";"
						+ x.getPrecio() + ";" + x.getCantidad() + ";" + x.getEstado();

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
