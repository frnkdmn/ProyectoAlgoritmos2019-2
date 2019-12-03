package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import clases.Bungalow;
import clases.Hospedaje;
import clases.Ingreso;
import clases.Socio;
import controlador.BungalowControlador;
import controlador.HospedajeControlador;
import controlador.IngresosControlador;
import controlador.SocioControlador;
import java.awt.SystemColor;

public class FrmHospedajesPendientes extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Object> cboHos;
	private JTextArea txtS;
	private HospedajeControlador hos = new HospedajeControlador();
	private SocioControlador soc = new SocioControlador();
	private IngresosControlador ing = new IngresosControlador();
	private BungalowControlador bun = new BungalowControlador();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmHospedajesPendientes frame = new FrmHospedajesPendientes();
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
	public FrmHospedajesPendientes() {
		setBackground(SystemColor.scrollbar);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setVisible(false);
		setTitle("Reporte Hospedajes Pendientes");
		setSize(650, 450);
		getContentPane().setLayout(null);
		setFrameIcon(new ImageIcon(FrmHospedajesPendientes.class.getResource("/imagenes/page_find.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 66, 614, 343);
		getContentPane().add(scrollPane);
		
		txtS = new JTextArea();
		scrollPane.setViewportView(txtS);
		
		JButton btnNewButton = new JButton("Actualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hos = new HospedajeControlador();
				cboHos.removeAllItems();
				for (int i = 0; i < ing.tamaño(); i++) {
					if(hos.obtener(i).getEstado()==0){
						cboHos.addItem(hos.obtener(i).getCodigoHospedaje());
					}		
				}
				listar();
			}
		});
		btnNewButton.setBounds(524, 21, 89, 23);
		getContentPane().add(btnNewButton);
		
		JLabel lblHospedajesPendientes = new JLabel("Hospedajes Pendientes:");
		lblHospedajesPendientes.setBounds(10, 25, 142, 14);
		getContentPane().add(lblHospedajesPendientes);
		
		cboHos = new JComboBox<Object>();
		cboHos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				listar();
			}
		});
		cboHos.setBounds(162, 22, 81, 20);
		getContentPane().add(cboHos);
		for (int i = 0; i < hos.tamaño(); i++) {
			if(hos.obtener(i).getEstado()==0){
				cboHos.addItem(hos.obtener(i).getCodigoHospedaje());
			}
		}	
	
	}
	
	void listar() {
		txtS.setText("");

		if (cboHos.getSelectedItem() == null) {

		} else {
			int dd, mm, aa;
			Calendar c = new GregorianCalendar();
			dd = c.get(Calendar.DAY_OF_MONTH);
			mm = c.get(Calendar.MONTH) + 1;
			aa = c.get(Calendar.YEAR) - 2000;
			String fecha =  ajustar(dd) + "/" + ajustar(mm) + "/" + ajustar(aa);;
			
			int codHos = (Integer) cboHos.getSelectedItem();
			Hospedaje objHos = hos.buscarPorCodigo(codHos);
			int codIng = objHos.getCodigoIngreso();
			int codBun = objHos.getNumeroBugalow();
			Bungalow objBun = bun.buscarPorCodigo(codBun);
			Ingreso objIng= ing.buscarPorCodigo(codIng);
			int codSoc = objIng.getCodigoSocio();
			Socio objSoc = soc.buscarPorCodigo(codSoc);
			
			String fecEnt = objHos.getFechaEntrada();
			String[] Ent = fecEnt.split("/");
			int diaEnt = Integer.parseInt(Ent[0]);
			int mesEnt = Integer.parseInt(Ent[1]);
			int anoEnt = Integer.parseInt(Ent[2]);
			
			imprimir("Código Hospedaje :	" + codHos);
			imprimir("Socio  :  " + objSoc.getCodigoSocio());
			imprimir("Nombres   :  " + objSoc.getNombres());
			imprimir("Apellidos :  " + objSoc.getApellidos());
			imprimir();
			imprimir("Fecha de Ingreso	:	" + objHos.getFechaEntrada());
			imprimir("Fecha de Salida		:	" + objHos.getFechaSalida());
			imprimir("Fecha actual		:	" + fecha );
			imprimir();
			imprimir("Total a pagar(Hoy)	:	" + objBun.getPrecioPorDia()* (dd - diaEnt+1 + (mm - mesEnt) * 30 + (aa - anoEnt) * 360 ));
			imprimir("Total a pagar(Salida)	:	" + objHos.getCostoHospedaje());
		}
	}

	String ajustar(int numero) {
		return String.format("%02d", numero);
	}
	void imprimir(String s) {
		txtS.append(s + "\n");
	}

	void imprimir() {
		imprimir("");
	}
}
