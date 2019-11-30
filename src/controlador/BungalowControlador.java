package controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import clases.Bungalow;

public class BungalowControlador {
		private ArrayList<Bungalow> lista = new ArrayList<Bungalow>();
	// métodos básicos de la lista
		public BungalowControlador() {
			//apenas se crea el objeto , se crea la data
			//pasa los datos del archivo de texto a la lista
			cargaData();
		}
		public void agregar(Bungalow obj) {
			lista.add(obj);
		}

		public int tamaño() {
			return lista.size();
		}

		public Bungalow obtener(int pos) {
			return lista.get(pos);
		}

		// métodos adicionales
		public Bungalow buscarPorCodigo(int cod) {
			Bungalow salida = null;
			for (Bungalow x : lista) {
				if (x.getNumeroBungalow() == cod) {
					salida = x;
					break;
				}
			}
			return salida;
		}

		public void eliminaPorCodigo(int cod) {
			for (Bungalow x : lista) {
				if (x.getNumeroBungalow() == cod) {
					lista.remove(x);
					break;
				}
			}
		}

		public void actualizar(Bungalow aux) {
			for (Bungalow x : lista) {
				if (x.getNumeroBungalow() == aux.getNumeroBungalow()) {
					lista.set(lista.indexOf(x), aux);
					break;
				}
			}
		}
		public int codigoCorrelativo(){
			if(tamaño() ==0)
				return 30001;
			else{
				int ultCodigo = obtener(tamaño()-1).getNumeroBungalow();
				return ultCodigo + 1;
			}
		}
		private void cargaData(){
			BufferedReader br = null;
			try {
				//Se lee el archivo txt
				br = new BufferedReader(new FileReader("Bungalow.txt"));
				String linea = null;
				//readLine captura una linea
				while(  (linea = br.readLine()) != null){
						//Se separa los datos
						String[ ]  s = linea.split(";");
						Bungalow obj = new Bungalow();
						obj.setNumeroBungalow(Integer.parseInt(s[0]));
						obj.setEstado(Integer.parseInt(s[1]));
						obj.setCategoria(Integer.parseInt(s[2]));
						obj.setPrecioPorDia(Double.parseDouble(s[3]));
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
				pw = new PrintWriter(new FileWriter("Bungalow.txt"));
				for (Bungalow x: lista) {
					linea = x.getNumeroBungalow() + ";" +   x.getEstado() + ";" +	x.getCategoria() + ";" +	x.getPrecioPorDia();
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
