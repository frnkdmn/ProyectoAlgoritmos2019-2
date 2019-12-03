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

import clases.Hospedaje;
import clases.Ingreso;
import clases.Socio;
import controlador.BungalowControlador;
import controlador.HospedajeControlador;
import controlador.IngresosControlador;
import controlador.SocioControlador;

public class FrmHospedajes extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JComboBox<Object> cboIng;
	private HospedajeControlador hos =new HospedajeControlador();
	private BungalowControlador bun=new BungalowControlador();
	private SocioControlador soc=new SocioControlador();
	private IngresosControlador ing=new IngresosControlador();
	private JTextArea txtS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {	
			public void run() {
				try {
					FrmHospedajes frame = new FrmHospedajes();
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
	public FrmHospedajes() {
		setBackground(SystemColor.scrollbar);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setVisible(false);
		setTitle("Pagos Hospedajes");
		setSize(650, 450);
		setFrameIcon(new ImageIcon(FrmHospedajes.class.getResource("/imagenes/money.png")));
		getContentPane().setLayout(null);
		
		JLabel lblHospedaje = new JLabel("Hospedaje:");
		lblHospedaje.setBounds(24, 54, 91, 14);
		getContentPane().add(lblHospedaje);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 110, 587, 286);
		getContentPane().add(scrollPane);
		
		 txtS = new JTextArea();
		scrollPane.setViewportView(txtS);
		
		cboIng=new JComboBox<>();
		cboIng.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				listar();			
				}
		});
		cboIng.setBounds(114, 51, 91, 20);
		getContentPane().add(cboIng);
		for (int i = 0; i < hos.tamaño(); i++) {
			if(hos.obtener(i).getEstado()==0)
			cboIng.addItem(hos.obtener(i).getCodigoHospedaje());
		}
		JButton btnPagar = new JButton("Pagar");
		btnPagar.addActionListener(new ActionListener() {
			//evento de boton pagar
			public void actionPerformed(ActionEvent arg0) {
				int codHos = (Integer)cboIng.getSelectedItem();
				int codBun = hos.buscarPorCodigo(codHos).getNumeroBugalow();
				txtS.append(""+codBun);
				bun.buscarPorCodigo(codBun).setEstado(0);
				bun.grabarData();
				hos.buscarPorCodigo(codHos).setEstado(1);
				hos.grabarData();
				cboIng.removeItem(codHos);
			}
		});
		btnPagar.setBounds(522, 37, 89, 23);
		getContentPane().add(btnPagar);
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			//evento del boton actualizar
			public void actionPerformed(ActionEvent e) {
				hos=new HospedajeControlador();
				cboIng.removeAllItems();
				for (int i = 0; i < hos.tamaño(); i++) {
					if(hos.obtener(i).getEstado()==0){
					cboIng.addItem(hos.obtener(i).getCodigoHospedaje());
					}
				}
				listar();
			}
		});
		btnActualizar.setBounds(522, 76, 89, 23);
		getContentPane().add(btnActualizar);
		
	}
	
	void listar() {
		txtS.setText("");
		if (cboIng.getSelectedItem()==null) {
			
		} else {
			int codHos = (Integer) cboIng.getSelectedItem();
	        Hospedaje objHos = hos.buscarPorCodigo(codHos);
	        int codIng = objHos.getCodigoIngreso();
	        Ingreso objIng = ing.buscarPorCodigo(codIng);
	        int codSoc = objIng.getCodigoSocio();
	        Socio objSoc = soc.buscarPorCodigo(codSoc);
	        double total = hos.buscarPorCodigo(codHos).getCostoHospedaje();
		
			
			imprimir("Socio  :  " + objSoc.getCodigoSocio());
			imprimir("Nombres   :  " + objSoc.getNombres());
			imprimir("Apellidos :  " + objSoc.getApellidos());
			imprimir();
			imprimir("fecha de entrada : "+objHos.getFechaEntrada());
			imprimir("Fecha de Salida : "+objHos.getFechaSalida()); 
			imprimir();
			imprimir("Total a Pagar	:	"+total);

		}
		
		
	}
	void imprimir(String s) {
		txtS.append(s + "\n");
	}

	void imprimir() {
		imprimir("");
	}
}