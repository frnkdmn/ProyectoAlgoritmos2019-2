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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import clases.Consumo;
import clases.Ingreso;
import controlador.ConsumoControlador;
import controlador.IngresosControlador;
import controlador.ProductoControlador;
import controlador.SocioControlador;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmConsumo extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCan;
	private JTextField txtNom;
	private IngresosControlador ing = new IngresosControlador();
	private ProductoControlador pro = new ProductoControlador();
	private ConsumoControlador con = new ConsumoControlador();
	private SocioControlador soc = new SocioControlador();
	private JComboBox<Object> cboIng;
	private JComboBox<Object> cboPro;
	private JTextField txtCodPro;
	private JTextField txtPre;
	private JTable table;
	private JTextField txtCon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmConsumo frame = new FrmConsumo();
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
	public FrmConsumo() {
		getContentPane().setBackground(SystemColor.scrollbar);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setVisible(false);
		setTitle("Registro Consumo");
		setSize(650, 450);
		setFrameIcon(new ImageIcon(FrmConsumo.class.getResource("/imagenes/page_add.png")));
		getContentPane().setLayout(null);

		JLabel lblSocio = new JLabel("C\u00F3digo Ingreso:");
		lblSocio.setBounds(25, 65, 98, 14);
		getContentPane().add(lblSocio);

		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setBounds(25, 95, 71, 14);
		getContentPane().add(lblProducto);

		JButton btnGuardar = new JButton("Registrar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					registrar();
				} catch (Exception e2) {
					mensaje("Ingrese datos correctamente");
				}
			}
		});
		btnGuardar.setBounds(518, 120, 89, 23);
		getContentPane().add(btnGuardar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 208, 603, 201);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscar();
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Codigo Consumo", "Codigo Ingreso",
				"Producto", "Precio", "Cantidad", "Importe Total", "Estado" }));
		scrollPane.setViewportView(table);

		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(25, 169, 71, 14);
		getContentPane().add(lblCantidad);

		txtCan = new JTextField();
		txtCan.setBounds(133, 166, 109, 20);
		getContentPane().add(txtCan);
		txtCan.setColumns(10);

		JLabel lblSocio_1 = new JLabel("Socio(a): ");
		lblSocio_1.setBounds(267, 65, 65, 14);
		getContentPane().add(lblSocio_1);

		txtNom = new JTextField();
		txtNom.setBackground(SystemColor.activeCaption);
		txtNom.setEditable(false);
		txtNom.setBounds(336, 62, 271, 20);
		getContentPane().add(txtNom);
		txtNom.setColumns(10);

		JLabel lblCodpro = new JLabel("CodPro:");
		lblCodpro.setBounds(267, 92, 65, 14);
		getContentPane().add(lblCodpro);

		txtCodPro = new JTextField();
		txtCodPro.setBackground(SystemColor.activeCaption);
		txtCodPro.setEditable(false);
		txtCodPro.setBounds(336, 89, 96, 20);
		getContentPane().add(txtCodPro);
		txtCodPro.setColumns(10);

		txtPre = new JTextField();
		txtPre.setEditable(false);
		txtPre.setBackground(SystemColor.activeCaption);
		txtPre.setBounds(336, 121, 96, 20);
		getContentPane().add(txtPre);
		txtPre.setColumns(10);

		JLabel lblNewLabel = new JLabel("Precio:");
		lblNewLabel.setBounds(266, 124, 66, 14);
		getContentPane().add(lblNewLabel);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					actualizar();
				} catch (Exception e2) {
					mensaje("Ingrese datos correctamente");
				}

			}
		});
		btnModificar.setBounds(518, 154, 89, 23);
		getContentPane().add(btnModificar);

		JLabel lblCdigoConsumo = new JLabel("C\u00F3digo Consumo:");
		lblCdigoConsumo.setBounds(25, 29, 98, 14);
		getContentPane().add(lblCdigoConsumo);

		txtCon = new JTextField("" + con.codigoCorrelativo());
		txtCon.setBackground(SystemColor.activeCaption);
		txtCon.setEditable(false);
		txtCon.setBounds(134, 26, 96, 20);
		getContentPane().add(txtCon);
		txtCon.setColumns(10);
		cboIng = new JComboBox<Object>();
		cboIng.setBounds(133, 62, 109, 20);
		getContentPane().add(cboIng);
		for (int i = 0; i < ing.tamaño(); i++) {
			Ingreso obj = ing.obtener(i);
			if (obj.getEstado() == 0)
				cboIng.addItem(obj.getCodigoIngreso());
		}
		cboIng.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cambiarIngreso();
			}
		});

		cboPro = new JComboBox<Object>();
		cboPro.setBounds(133, 89, 109, 20);
		getContentPane().add(cboPro);
		for (int i = 0; i < pro.tamaño(); i++) {
			cboPro.addItem(pro.obtener(i).getDetalle());
		}
		cboPro.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cambiarProducto();
			}
		});
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pro = new ProductoControlador();
				ing = new IngresosControlador();
				cboPro.removeAllItems();
				for(int i = 0;i<pro.tamaño();i++){	
					cboPro.addItem(pro.obtener(i).getDetalle());
				}
				cboIng.removeAllItems();
				for(int i = 0;i<ing.tamaño();i++){	
					Ingreso obj = ing.obtener(i);
					if(obj.getEstado()==0){
						cboIng.addItem(obj.getCodigoIngreso());
					}
				}
				con = new ConsumoControlador();
				listar();
			}
		});
		btnActualizar.setBounds(267, 165, 89, 23);
		getContentPane().add(btnActualizar);
		listar();
		cambiarIngreso();
		cambiarProducto();
	}

	void cambiarIngreso() {
		if (cboIng.getSelectedItem() == null) {
			txtNom.setText("");
		} else {
			int codIng = Integer.parseInt("" + cboIng.getSelectedItem());
			int cod2 = ing.buscarPorCodigo(codIng).getCodigoSocio();
			String nom = soc.buscarPorCodigo(cod2).getNombres() + " " + soc.buscarPorCodigo(cod2).getApellidos();
			txtNom.setText(nom);
		}
	}

	void cambiarProducto() {
		if (cboPro.getSelectedItem() == null) {
			txtCodPro.setText("");
			txtPre.setText("");
		} else {
			String nom = String.valueOf(cboPro.getSelectedItem());
			int cod = pro.buscarPorNombre(nom).getCodigoProducto();
			double pre = pro.buscarPorNombre(nom).getPrecioUnitario();
			txtCodPro.setText("" + cod);
			txtPre.setText("" + pre);
		}

	}

	void listar() {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		for (int i = 0; i < con.tamaño(); i++) {
			Consumo x = con.obtener(i);
			Object[] f = { x.getCodigoConsumo(), x.getCodigoIngreso(), x.getDetalleProducto(), x.getPrecio(),
					x.getCantidad(), x.totalConsumo(), convierteEstado(x.getEstado()) };
			dtm.addRow(f);
		}
	}

	void registrar() {
		int codCon = Integer.parseInt(txtCon.getText());
		int codIng = Integer.parseInt("" + cboIng.getSelectedItem());
		String pro = String.valueOf(cboPro.getSelectedItem());
		double pre = Double.parseDouble(txtPre.getText());
		int cant = Integer.parseInt(txtCan.getText());
		int est = 0;
		if (cant < 0) {
			mensaje("Ingrese una cantidad válida");
		} else {
			Consumo obj = new Consumo(codCon, codIng, pro, pre, cant, est);
			con.agregar(obj);
			con.grabarData();
			listar();
			limpiarCajas();
			mensaje("Se agregó correctamente");
		}

	}

	void actualizar() {
		int codCon = Integer.parseInt(txtCodPro.getText());
		int codIng = Integer.parseInt("" + cboIng.getSelectedItem());
		String pro = String.valueOf(cboPro.getSelectedItem());
		double pre = Double.parseDouble(txtPre.getText());
		int cant = Integer.parseInt(txtCan.getText());
		int est = 0;
		if (cant < 0) {
			mensaje("Ingrese una cantidad válida");
		} else {
			Consumo obj = new Consumo(codCon, codIng, pro, pre, cant, est);
			con.actualizar(obj);
			con.grabarData();
			listar();
			limpiarCajas();
			mensaje("Se acutalizó correctamente");
		}

	}

	void buscar() {
		int fila = table.getSelectedRow();
		if (fila == -1) {
			mensaje("Seleccione una fila");
		} else {
			// Se obtiene valores de la celda
			int codCon = (Integer) table.getValueAt(fila, 0);
			int codIng = (Integer) table.getValueAt(fila, 1);
			String pro = (String) table.getValueAt(fila, 2);
			int cant = (Integer) table.getValueAt(fila, 4);

			txtCodPro.setText("" + codCon);
			cboIng.setSelectedItem(codIng);
			cboPro.setSelectedItem(pro);
			txtCan.setText("" + cant);
		}
	}

	void limpiarCajas() {
		txtCon.setText("" + con.codigoCorrelativo());
		txtCan.setText("");
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
