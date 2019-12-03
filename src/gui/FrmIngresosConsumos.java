package gui;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import clases.Consumo;
import clases.Ingreso;
import clases.Socio;
import controlador.ConsumoControlador;
import controlador.IngresosControlador;
import controlador.SocioControlador;
import java.awt.event.ItemListener;

import java.awt.event.ItemEvent;

public class FrmIngresosConsumos extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Object> cboIng;
	private IngresosControlador ing = new IngresosControlador();
	private SocioControlador soc = new SocioControlador();
	private ConsumoControlador con = new ConsumoControlador();
	private JTextArea txtS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmIngresosConsumos frame = new FrmIngresosConsumos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmIngresosConsumos() {
		setBackground(SystemColor.scrollbar);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setVisible(false);
		setTitle("Pago Ingresos y Cosumos");
		setSize(650, 450);
		setFrameIcon(new ImageIcon(FrmIngresosConsumos.class.getResource("/imagenes/money.png")));
		getContentPane().setLayout(null);

		JLabel lblIngreso = new JLabel("Ingreso:");
		lblIngreso.setBounds(29, 45, 85, 14);
		getContentPane().add(lblIngreso);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 113, 582, 296);
		getContentPane().add(scrollPane);

		txtS = new JTextArea();
		scrollPane.setViewportView(txtS);

		cboIng = new JComboBox<Object>();
		cboIng.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				listar();
			}
		});
		cboIng.setBounds(124, 42, 85, 20);
		getContentPane().add(cboIng);
		for (int i = 0; i < ing.tamaño(); i++) {
			if (ing.obtener(i).getEstado() == 0)
				cboIng.addItem(ing.obtener(i).getCodigoIngreso());
		}
		JButton btnPagar = new JButton("Pagar");
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int codIng = (Integer) cboIng.getSelectedItem();
				for (int i = 0; i < con.tamaño(); i++) {
					Consumo obj = con.obtener(i);
					if (obj.getCodigoIngreso() == codIng) {
						obj.setEstado(1);
					}
				}
				ing.buscarPorCodigo(codIng).setEstado(1);
				ing.grabarData();
				con.grabarData();
				cboIng.removeItem(codIng);
			}
		});
		btnPagar.setBounds(486, 41, 89, 23);
		getContentPane().add(btnPagar);

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ing = new IngresosControlador();
				con = new ConsumoControlador();
				cboIng.removeAllItems();
				for (int i = 0; i < ing.tamaño(); i++) {
					if(ing.obtener(i).getEstado()==0){
						cboIng.addItem(ing.obtener(i).getCodigoIngreso());
					}		
				}
				listar();
			}
		});
		btnActualizar.setBounds(486, 75, 89, 23);
		getContentPane().add(btnActualizar);

	}

	/*
	 * Ingreso a = aa.buscar(leerCodigoIngresos()); Socio p
	 * =ap.buscar(a.getCodigoSocio());
	 */
	void listar() {
		txtS.setText("");

		if (cboIng.getSelectedItem() == null) {

		} else {
			int codIng = (Integer) cboIng.getSelectedItem();
			Ingreso objIng = ing.buscarPorCodigo(codIng);
			int codSoc = objIng.getCodigoSocio();
			Socio objSoc = soc.buscarPorCodigo(codSoc);
			double pagoIng = objIng.getNumeroInvitados() * objIng.getCostoIngreso();
			double total = pagoIng;
			double totalCon = 0;
			imprimir("Socio  :  " + objSoc.getCodigoSocio());
			imprimir("Nombres   :  " + objSoc.getNombres());
			imprimir("Apellidos :  " + objSoc.getApellidos());
			imprimir();
			imprimir("Productos Consumidos	:	");
			imprimir();
			imprimir("Producto		Cantidad		Precio		SubTotal");
			for (int i = 0; i < con.tamaño(); i++) {
				Consumo obj = con.obtener(i);
				if (obj.getCodigoIngreso() == codIng) {
					double subtotal = obj.getCantidad() * obj.getPrecio();
					total += subtotal;
					totalCon +=subtotal;
					imprimir(obj.getDetalleProducto() + "		" + obj.getCantidad() + "			" + obj.getPrecio()
							+ "		/S." + subtotal);
				}
			}
			imprimir();
			imprimir("Pago por Ingreso		:	/S." + (pagoIng));
			imprimir("Total productos Consumidos	:	/S." + totalCon);
			imprimir("Total a Pagar			:	/S." + total);
		}
		

	}

	void imprimir(String s) {
		txtS.append(s + "\n");
	}

	void imprimir() {
		imprimir("");
	}
}
