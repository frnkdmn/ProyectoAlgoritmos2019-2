package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.DefaultTableModel;

import clases.Ingreso;
import controlador.IngresosControlador;
import controlador.SocioControlador;

import java.awt.SystemColor;

public class FrmIngreso extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCod;
	private JTextField txtInvitados;
	private JTable table;
	private IngresosControlador ing = new IngresosControlador();
	private SocioControlador soc = new SocioControlador();
	private JComboBox<Object> cboSocio;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmIngreso frame = new FrmIngreso();
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
	public FrmIngreso() {
		setBackground(SystemColor.scrollbar);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setVisible(false);
		setTitle("Registro Ingreso");
		setSize(650, 450);
		setFrameIcon(new ImageIcon(FrmIngreso.class.getResource("/imagenes/page_add.png")));
		getContentPane().setLayout(null);

		JLabel lblCdigo = new JLabel("C\u00F3digo: ");
		lblCdigo.setBounds(28, 48, 80, 14);
		getContentPane().add(lblCdigo);

		JLabel lblSocio = new JLabel("Socio:");
		lblSocio.setBounds(28, 79, 80, 14);
		getContentPane().add(lblSocio);

		JLabel lblNewLabel = new JLabel("Invitados");
		lblNewLabel.setBounds(28, 113, 80, 14);
		getContentPane().add(lblNewLabel);

		txtCod = new JTextField(""+ing.codigoCorrelativo());
		txtCod.setBackground(SystemColor.activeCaption);
		txtCod.setEditable(false);
		txtCod.setBounds(118, 45, 96, 20);
		getContentPane().add(txtCod);
		txtCod.setColumns(10);

		txtInvitados = new JTextField();
		txtInvitados.setBounds(118, 110, 96, 20);
		getContentPane().add(txtInvitados);
		txtInvitados.setColumns(10);

		cboSocio = new JComboBox<Object>();
		cboSocio.setBounds(118, 76, 96, 20);
		getContentPane().add(cboSocio);
		for(int i = 0;i<soc.tamaño();i++){
			cboSocio.addItem(soc.obtener(i).getCodigoSocio());
		}

		JButton btnIngresar = new JButton("Registrar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					registrar();
				} catch (Exception e2) {
					mensaje("Ingrese datos válidos");
				}			
			}
		});
		btnIngresar.setBounds(486, 44, 89, 23);
		getContentPane().add(btnIngresar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 153, 575, 243);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Ingreso", "Socio", "Fecha Ingreso", "Hora Ingreso", "Invitados", "Costo", "Estado" }));
		scrollPane.setViewportView(table);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				soc = new SocioControlador();
				cboSocio.removeAllItems();
				for(int i = 0;i<soc.tamaño();i++){	
					cboSocio.addItem(soc.obtener(i).getCodigoSocio());
				}
				ing = new IngresosControlador();
				listar();
			}
		});
		btnActualizar.setBounds(486, 75, 89, 23);
		getContentPane().add(btnActualizar);
		listar();
	}

	void listar() {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		// Se limpia la tabla
		dtm.setRowCount(0);
		// Se añade datos a la tabla
		for (int i = 0; i < ing.tamaño(); i++) {
			Ingreso x = ing.obtener(i);
			Object[] f = { x.getCodigoIngreso(), x.getCodigoSocio(), x.getFechaIngreso(), x.getHoraIngreso(),
					x.getNumeroInvitados(), x.getCostoIngreso(), convierteEstado(x.getEstado()) };
			dtm.addRow(f);
		}
	}

	public void registrar() {
		int codIng = Integer.parseInt(txtCod.getText().trim());
		int codSoc = Integer.parseInt(cboSocio.getSelectedItem().toString());
		String fecIng = fechaActual();
		String horIng = horaActual();
		int numInv = Integer.parseInt(txtInvitados.getText().trim());
		double costo = costo();
		int est = 0;
		
		if(numInv<0){
			mensaje("Ingrese un número de invitados correcto");
		} else{
			Ingreso obj = new Ingreso(codIng,codSoc,fecIng,horIng,numInv,costo,est);
			ing.agregar(obj);
			ing.grabarData();
			listar();
			limpiarCajas();
			mensaje("Se agregó correctamente");
		}
		
	}

	public void limpiarCajas() {
		txtCod.setText("" + ing.codigoCorrelativo());
		txtInvitados.setText("");
		txtInvitados.requestFocus();
	}

	String fechaActual() {
		int dd, mm, aa;
		Calendar c = new GregorianCalendar();
		dd = c.get(Calendar.DAY_OF_MONTH);
		mm = c.get(Calendar.MONTH) + 1;
		aa = c.get(Calendar.YEAR);
		return ajustar(dd) + "/" + ajustar(mm) + "/" + aa;
	}

	String horaActual() {
		int hh, mm, ss;
		Calendar c = new GregorianCalendar();
		hh = c.get(Calendar.HOUR_OF_DAY);
		mm = c.get(Calendar.MINUTE);
		ss = c.get(Calendar.SECOND);
		return ajustar(hh) + ":" + ajustar(mm) + ":" + ajustar(ss);
	}

	String ajustar(int numero) {
		return String.format("%02d", numero);
	}

	public double costo() {
		return invitados() * 25;
	}

	int invitados() {
		return Integer.parseInt(txtInvitados.getText());
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
