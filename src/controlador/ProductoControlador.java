package controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import clases.Producto;

public class ProductoControlador {
	private ArrayList<Producto> lista = new ArrayList<Producto>();

	// métodos básicos de la lista
	public ProductoControlador() {
		//apenas se crea el objeto , se crea la data
		//pasa los datos del archivo de texto a la lista
		cargaData();
	}
	public void agregar(Producto obj) {
		lista.add(obj);
	}

	public int tamaño() {
		return lista.size();
	}

	public Producto obtener(int pos) {
		return lista.get(pos);
	}

	// métodos adicionales
	public Producto buscarPorCodigo(int aux) {
		Producto salida = null;
		for (Producto x : lista) {
			if (x.getCodigoProducto() == aux) {
				salida = x;
				break;
			}
		}
		return salida;
	}

	public void eliminaPorCodigo(int aux) {
		for (Producto x : lista) {
			if (x.getCodigoProducto() == aux) {
				lista.remove(x);
				break;
			}
		}
	}

	public void actualizar(Producto aux) {
		for (Producto x : lista) {
			if (x.getCodigoProducto() == aux.getCodigoProducto()) {
				lista.set(lista.indexOf(x), aux);
				break;
			}
		}
	}
	
	private void cargaData(){
		BufferedReader br = null;
		try {
			//Se lee el archivo txt
			br = new BufferedReader(new FileReader("Productos.txt"));
			String linea = null;
			//readLine captura una linea
			while(  (linea = br.readLine()) != null){
					//Se separa los datos
					String[ ]  s = linea.split(";");
					Producto obj = new Producto();
					obj.setCodigoProducto(Integer.parseInt(s[0]));
					obj.setDetalle(s[1]);
					obj.setPrecioUnitario(Double.parseDouble(s[2]));
					obj.setStock(Integer.parseInt(s[3]));
					lista.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
				try {
					if(br != null) br.close();
				} catch (Exception e) {}
		}
	}
	public void grabarData() {
		PrintWriter pw = null;
		try {
			String linea;
			pw = new PrintWriter(new FileWriter("Productos.txt"));
			for (Producto x: lista) {
				linea = x.getCodigoProducto() + ";" +   x.getDetalle() + ";" +	x.getPrecioUnitario() + ";" +	x.getStock();
				pw.println(linea);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(pw != null) pw.close();
			} catch (Exception e) {}
		}
	}
}
