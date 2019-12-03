package gui;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import clases.Bungalow;
import clases.Hospedaje;
import clases.Ingreso;
import controlador.BungalowControlador;
import controlador.HospedajeControlador;
import controlador.IngresosControlador;

public class FrmHospedaje extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCod;
	private JTextField txtFecha;
	private JTable table;
	private JComboBox<Object> cboIng;
	private JComboBox<Object> cboBun;
	private BungalowControlador bun = new BungalowControlador();
	private IngresosControlador ing = new IngresosControlador();
	private HospedajeControlador hos = new HospedajeControlador();
	private ArrayList<Integer> ingVal = new ArrayList<Integer>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmHospedaje frame = new FrmHospedaje();
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
	public FrmHospedaje() {
		setBackground(SystemColor.scrollbar);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setVisible(false);
		setTitle("Registro Hospedaje");
		setSize(650, 450);
		setFrameIcon(new ImageIcon(FrmHospedaje.class.getResource("/imagenes/page_add.png")));
		getContentPane().setLayout(null);

		JLabel lblCdigo = new JLabel("C\u00F3digo: ");
		lblCdigo.setBounds(28, 54, 70, 14);
		getContentPane().add(lblCdigo);

		JLabel lblBungalow = new JLabel("Bungalow:");
		lblBungalow.setBounds(28, 79, 70, 14);
		getContentPane().add(lblBungalow);

		JLabel lblIngreso = new JLabel("Ingreso:");
		lblIngreso.setBounds(28, 104, 70, 14);
		getContentPane().add(lblIngreso);

		txtCod = new JTextField("" + hos.codigoCorrelativo());
		txtCod.setBackground(SystemColor.activeCaption);
		txtCod.setEditable(false);
		txtCod.setBounds(108, 51, 96, 20);
		getContentPane().add(txtCod);
		txtCod.setColumns(10);

		cboBun = new JComboBox<Object>();
		cboBun.setBounds(108, 76, 96, 20);
		getContentPane().add(cboBun);
		for (int i = 0; i < bun.tamaño(); i++) {
			Bungalow obj = bun.obtener(i);
			if (obj.getEstado() == 0)
				cboBun.addItem(bun.obtener(i).getNumeroBungalow());
		}

		cboIng = new JComboBox<Object>();
		cboIng.setBounds(108, 101, 96, 20);
		getContentPane().add(cboIng);
		for (int i = 0; i < ing.tamaño(); i++) {
			Ingreso obj = ing.obtener(i);
			if (obj.getEstado() == 0)
				cboIng.addItem(ing.obtener(i).getCodigoIngreso());
		}

		JButton btnHospedar = new JButton("Registrar");
		btnHospedar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					registrar();
				} catch (Exception e2) {
					mensaje("Ingrese datos válidos");
				}
			
			}
		});
		btnHospedar.setBounds(524, 50, 89, 23);
		getContentPane().add(btnHospedar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 146, 614, 263);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Hospedaje", "Ingreso", "Bungalow",
				"Fecha Entrada", "Fecha Salida", "Costo", "Estado" }));
		scrollPane.setViewportView(table);

		JLabel lblFechaDeEntrada = new JLabel("Fecha de Salida:");
		lblFechaDeEntrada.setBounds(251, 54, 102, 14);
		getContentPane().add(lblFechaDeEntrada);

		txtFecha = new JTextField();
		txtFecha.setBounds(376, 51, 96, 20);
		getContentPane().add(txtFecha);
		txtFecha.setColumns(10);
		
		JButton btnNewButton = new JButton("Actualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bun = new BungalowControlador();
				cboBun.removeAllItems();
				for(int i = 0;i<bun.tamaño();i++){	
					Bungalow obj = bun.obtener(i);
					if (obj.getEstado() == 0)
						cboBun.addItem(bun.obtener(i).getNumeroBungalow());
				}
				ing = new IngresosControlador();
				cboIng.removeAllItems();
				for(int i = 0;i<ing.tamaño();i++){	
					Ingreso obj = ing.obtener(i);
					if (obj.getEstado() == 0)
					cboIng.addItem(ing.obtener(i).getCodigoIngreso());
				}
				hos = new HospedajeControlador();
				listar();
			}
		});
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton.setBounds(524, 79, 89, 23);
		getContentPane().add(btnNewButton);
		listar();
	}

	void listar() {
		// Limpia la tabla de la GUI
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		// Se limpia la tabla
		dtm.setRowCount(0);
		// Se añade datos a la tabla
		for (int i = 0; i < hos.tamaño(); i++) {
			Hospedaje x = hos.obtener(i);
			Object[] f = { x.getCodigoHospedaje(), x.getCodigoIngreso(), x.getNumeroBugalow(), x.getFechaEntrada(),
					x.getFechaSalida(), x.getCostoHospedaje(), convierteEstado(x.getEstado()) };
			dtm.addRow(f);
		}
	}

	void registrar() {
		
		int cod = Integer.parseInt(txtCod.getText());
		int num = Integer.parseInt("" + cboBun.getSelectedItem());
		int ing = Integer.parseInt("" + cboIng.getSelectedItem());
		String fecEnt = fechaActual();
		String fecSal = txtFecha.getText();
		double cos = bun.buscarPorCodigo(num).getPrecioPorDia() * restarFechas();
		int est = 0;
		
		String[] fecVal = fecSal.split("/");
		int dia = Integer.parseInt(fecVal[0]);
		int mes = Integer.parseInt(fecVal[1]);
		int año = Integer.parseInt(fecVal[2]);

		
		if (fecEnt.equals(fecSal))
			cos = bun.buscarPorCodigo(num).getPrecioPorDia() * 1;
		if ((mes < 12 && año == 19)|| año<19|| dia>30||mes>12||año>99) {
			mensaje("Ingrese una fecha válida");
		} else if(bun.buscarPorCodigo(num).getEstado()==1){
			mensaje("Bungalow ocupado");
		} else if(ingVal.contains(ing)){
			mensaje("El socio ya tiene un hospedaje");
		}
		else {
			Hospedaje obj = new Hospedaje(cod, ing, num, fecEnt, fecSal, cos, est);
			hos.agregar(obj);
			hos.grabarData();
			bun.buscarPorCodigo(num).setEstado(1);	
			bun.grabarData();
			ingVal.add(ing);
			listar();
			limpiarCajas();
			mensaje("Se agregó correctamente");
			cboBun.removeItem(num);
		}
		
	}

	String fechaActual() {
		int dd, mm, aa;
		Calendar c = new GregorianCalendar();
		dd = c.get(Calendar.DAY_OF_MONTH);
		mm = c.get(Calendar.MONTH) + 1;
		aa = c.get(Calendar.YEAR) - 2000;
		return ajustar(dd) + "/" + ajustar(mm) + "/" + ajustar(aa);
	}

	String ajustar(int numero) {
		return String.format("%02d", numero);
	}

	int restarFechas() {
		String fecEnt = fechaActual();
		String fecSal = txtFecha.getText();
		String[] Ent = fecEnt.split("/");
		String[] Sal = fecSal.split("/");
		int diaEnt = Integer.parseInt(Ent[0]);
		int diaSal = Integer.parseInt(Sal[0]);
		int mesEnt = Integer.parseInt(Ent[1]);
		int mesSal = Integer.parseInt(Sal[1]);
		int anoEnt = Integer.parseInt(Ent[2]);
		int anoSal = Integer.parseInt(Sal[2]);
		return diaSal - diaEnt+1 + (mesSal - mesEnt) * 30 + (anoSal - anoEnt) * 360;
	}

	void limpiarCajas() {
		txtCod.setText("" + hos.codigoCorrelativo());
		txtFecha.setText("");
	}

	void mensaje(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}

	String convierteEstado(int estado) {
		if (estado == 0) {
			return "Pendiente";
		} else
			return "Pagado";
	}

	int convierteEstadoString(String estado) {
		if (estado.equals("Pendiente")) {
			return 0;
		} else
			return 1;
	}
}
