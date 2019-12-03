package gui;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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

public class FrmIngresosConsumosPendientes extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea txtS; 
	private JComboBox<Object> cboIng;
	private IngresosControlador ing = new IngresosControlador();
	private ConsumoControlador con = new ConsumoControlador();
	private SocioControlador soc = new SocioControlador();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmIngresosConsumosPendientes frame = new FrmIngresosConsumosPendientes();
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
	public FrmIngresosConsumosPendientes() {
		setBackground(SystemColor.scrollbar);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setVisible(false);
		setTitle("Reporte Ingreso y Consumos Pendientes");
		setSize(650, 450);
		getContentPane().setLayout(null);
		setFrameIcon(new ImageIcon(FrmIngresosConsumosPendientes.class.getResource("/imagenes/page_find.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 82, 614, 327);
		getContentPane().add(scrollPane);
		
		txtS = new JTextArea();
		scrollPane.setViewportView(txtS);
		
		JLabel lblIngresosPendientes = new JLabel("Ingresos Pendientes:");
		lblIngresosPendientes.setBounds(10, 29, 125, 14);
		getContentPane().add(lblIngresosPendientes);
		
		cboIng = new JComboBox<Object>();
		cboIng.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				listar();
			}
		});
		cboIng.setBounds(143, 26, 87, 20);
		getContentPane().add(cboIng);
		for (int i = 0; i < ing.tamaño(); i++) {
			Ingreso obj = ing.obtener(i);
			if(obj.getEstado()==0){
				cboIng.addItem(obj.getCodigoIngreso());
			}	
		}
		
		JButton btnNewButton = new JButton("Actualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ing = new IngresosControlador();
				con = new ConsumoControlador();
				cboIng.removeAllItems();
				for (int i = 0; i < ing.tamaño(); i++) {
					Ingreso obj = ing.obtener(i);
					if(obj.getEstado()==0){
						cboIng.addItem(obj.getCodigoIngreso());
					}
				}
				listar();
			}
		});
		btnNewButton.setBounds(522, 25, 89, 23);
		getContentPane().add(btnNewButton);
		
		
	}
	
	void listar() {
		txtS.setText("");

		if (cboIng.getSelectedItem() == null) {

		} else {
			int codIng = (Integer) cboIng.getSelectedItem();
			Ingreso objIng = ing.buscarPorCodigo(codIng);
			int codSoc = objIng.getCodigoSocio();
			Socio objSoc = soc.buscarPorCodigo(codSoc);
			double total = objIng.getNumeroInvitados() * objIng.getCostoIngreso();
			imprimir("Código Ingreso :	" + codIng);
			imprimir("Socio  :  " + objSoc.getCodigoSocio());
			imprimir("Nombres   :  " + objSoc.getNombres());
			imprimir("Apellidos :  " + objSoc.getApellidos());
			imprimir();
			imprimir("Pago por Ingreso	:	" + (total));
			imprimir();
			imprimir("Productos Consumidos	:	");
			imprimir();
			imprimir("Producto		Cantidad		Precio		SubTotal");
			for (int i = 0; i < con.tamaño(); i++) {
				Consumo obj = con.obtener(i);
				if (obj.getCodigoIngreso() == codIng) {
					double subtotal = obj.getCantidad() * obj.getPrecio();
					total += subtotal;
					imprimir(obj.getDetalleProducto() + "		" + obj.getCantidad() + "			" + obj.getPrecio()
							+ "		" + subtotal);
				}
			}
			imprimir();
			imprimir("Total a Pagar	:	" + total);
		}
	}

	void imprimir(String s) {
		txtS.append(s + "\n");
	}

	void imprimir() {
		imprimir("");
	}
}
