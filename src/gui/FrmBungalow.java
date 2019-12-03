package gui;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultComboBoxModel;
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

import clases.Bungalow;
import controlador.BungalowControlador;

public class FrmBungalow extends JInternalFrame implements ActionListener, ItemListener, MouseListener {

	/**
	 * 
	 */
	// PASO1
	private BungalowControlador c = new BungalowControlador();
	private static final long serialVersionUID = 1L;
	private JTextField txtNumero;
	private JTable table;
	private JComboBox<String> cboCategoria;
	private JTextField txtPrecio;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JScrollPane scrollPane;
	private JButton btnConsultar;
	private JLabel lblConsultacdigo;
	private JTextField txtCod2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmBungalow frame = new FrmBungalow();
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
	public FrmBungalow() {
		setBackground(SystemColor.scrollbar);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setVisible(false);
		setTitle("Mantenimiento Bungalow");
		setSize(650, 450);
		setFrameIcon(new ImageIcon(FrmSocio.class.getResource("/imagenes/mantenimiento.jpg")));
		getContentPane().setLayout(null);

		JLabel lblNmero = new JLabel("N\u00FAmero:");
		lblNmero.setBounds(34, 36, 81, 14);
		getContentPane().add(lblNmero);

		txtNumero = new JTextField("" + c.codigoCorrelativo());
		txtNumero.setBackground(SystemColor.activeCaption);
		txtNumero.setEditable(false);
		txtNumero.setBounds(125, 33, 96, 20);
		getContentPane().add(txtNumero);
		txtNumero.setColumns(10);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.setBounds(484, 32, 89, 23);
		getContentPane().add(btnAdicionar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setBounds(484, 100, 89, 23);
		getContentPane().add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(484, 134, 89, 23);
		getContentPane().add(btnEliminar);

		JLabel lblCatergora = new JLabel("Catergor\u00EDa:");
		lblCatergora.setBounds(34, 75, 81, 14);
		getContentPane().add(lblCatergora);

		cboCategoria = new JComboBox<String>();
		cboCategoria.addItemListener(this);
		cboCategoria.setModel(new DefaultComboBoxModel<String>(new String[] { "Simple", "Doble", "Familiar" }));
		cboCategoria.setBounds(125, 72, 96, 20);
		getContentPane().add(cboCategoria);

		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(this);
		scrollPane.setBounds(34, 184, 567, 225);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "N\u00FAmero", "Estado", "Categoria", "Precio" }));
		scrollPane.setViewportView(table);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(34, 104, 48, 14);
		getContentPane().add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setText("100");
		txtPrecio.setEditable(false);
		txtPrecio.setBounds(125, 101, 96, 20);
		getContentPane().add(txtPrecio);
		txtPrecio.setColumns(10);

		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					consultar();
				} catch (Exception e2) {
					mensaje("Debe ingresar un codigo válido");
				}
			}
		});
		btnConsultar.setBounds(484, 66, 89, 23);
		getContentPane().add(btnConsultar);

		lblConsultacdigo = new JLabel("Consulta(C\u00F3digo):");
		lblConsultacdigo.setBounds(248, 36, 113, 14);
		getContentPane().add(lblConsultacdigo);

		txtCod2 = new JTextField();
		txtCod2.setBounds(371, 33, 96, 20);
		getContentPane().add(txtCod2);
		txtCod2.setColumns(10);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c = new BungalowControlador();
				listar();
			}
		});
		btnActualizar.setBounds(86, 134, 89, 23);
		getContentPane().add(btnActualizar);
		// Inicia el programa con un listar
		listar();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEliminar) {
			handleBtnEliminarActionPerformed(e);
		}
		if (e.getSource() == btnModificar) {
			handleBtnModificarActionPerformed(e);
		}
		if (e.getSource() == btnAdicionar) {
			handleBtnAdicionarActionPerformed(e);
		}
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cboCategoria) {
			handleCboCategoriaItemStateChanged(e);
		}
	}

	protected void handleCboCategoriaItemStateChanged(ItemEvent e) {
		switch (cboCategoria.getSelectedIndex()) {
		case 0:
			txtPrecio.setText("100");
			break;
		case 1:
			txtPrecio.setText("200");
			break;
		default:
			txtPrecio.setText("500");
			break;
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == table) {
			handleTableMouseClicked(e);
		}
	}

	protected void handleTableMouseClicked(MouseEvent e) {
		buscar();
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	protected void handleBtnAdicionarActionPerformed(ActionEvent e) {
		adicionar();
	}

	protected void handleBtnModificarActionPerformed(ActionEvent e) {
		actualizar();
	}

	protected void handleBtnEliminarActionPerformed(ActionEvent e) {
		if (c.tamaño() == 0) {
			mensaje("No existen bungalows");
		} else {
			int ok = confirmar("¿Desea eliminar el bungalow " + txtNumero.getText() + "?");
			if (ok == 0) {
				eliminar();
			}
		}
	}

	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
	}

	// métodos del matenimiento
	void listar() {
		// Limpia la tabla de la GUI
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		// Se limpia la tabla
		dtm.setRowCount(0);
		// Se añade datos a la tabla
		for (int i = 0; i < c.tamaño(); i++) {
			Bungalow x = c.obtener(i);
			Object[] f = { x.getNumeroBungalow(), convierteEstado(x.getEstado()), convierteCategoria(x.getCategoria()),
					x.getPrecioPorDia() };
			dtm.addRow(f);
		}
	}

	void adicionar() {
		int cod = Integer.parseInt(txtNumero.getText().trim());
		int est = 0;
		int cat = cboCategoria.getSelectedIndex();
		double pre = Double.parseDouble(txtPrecio.getText().trim());
		// No existe
		if (est == 0) {

			Bungalow obj = new Bungalow(cod, est, cat, pre);
			c.agregar(obj);
			c.grabarData();
			listar();
			mensaje("Se agregó correctamente");
			limpiarCajas();

		} else
			mensaje("Estado debería ser libre");

	}

	void consultar() {
		int cod = Integer.parseInt(txtCod2.getText().trim());
		if (c.buscarPorCodigo(cod) == null) {
			mensaje("No existe Socio con codigo " + cod);
		} else {
			Bungalow obj = c.buscarPorCodigo(cod);
			txtNumero.setText(String.valueOf(obj.getNumeroBungalow()));
			cboCategoria.setSelectedIndex(obj.getCategoria());
			txtPrecio.setText(String.valueOf(obj.getPrecioPorDia()));
		}
	}

	void buscar() {
		int fila = table.getSelectedRow();
		if (fila == -1) {
			mensaje("Seleccione una fila");
		} else {
			// Se obtiene valores de la celda
			int cod = (Integer) table.getValueAt(fila, 0);
			int cat = convierteCategoriaString((String) table.getValueAt(fila, 2));
			double pre = (Double) table.getValueAt(fila, 3);

			txtNumero.setText(String.valueOf(cod));
			cboCategoria.setSelectedIndex(cat);
			txtPrecio.setText(String.valueOf(pre));
		}
	}

	void eliminar() {
		int cod = Integer.parseInt(txtNumero.getText().trim());
		// NO EXISTE auto con esa placa
		if (c.buscarPorCodigo(cod) == null) {
			mensaje("No existe Bungalow N°" + cod);
		} else {
			if (c.buscarPorCodigo(cod).getEstado() == 1) {
				mensaje("No se puede modificar un bugalow ocupado");
			} else {// SI EXISTE auto con esa placa
				c.eliminaPorCodigo(cod);
				c.grabarData();
				listar();
				limpiarCajas();
			}
		}

	}

	void actualizar() {
		int cod = Integer.parseInt(txtNumero.getText().trim());
		int est = 0;
		int cat = cboCategoria.getSelectedIndex();
		double pre = Double.parseDouble(txtPrecio.getText().trim());
		if (c.buscarPorCodigo(cod) == null) {
			mensaje("No existe Bungalow N°" + cod);
		} else {
			if (c.buscarPorCodigo(cod).getEstado() == 1) {
				mensaje("No se puede modificar un bugalow ocupado");
			} else {
				Bungalow obj = new Bungalow(cod, est, cat, pre);
				c.actualizar(obj);
				c.grabarData();
				listar();
				mensaje("Se acutalizó correctamente");
				limpiarCajas();
			}
		}
	}

	void limpiarCajas() {
		txtNumero.setText("" + c.codigoCorrelativo());
		cboCategoria.setSelectedIndex(0);
		txtPrecio.setText("100");
	}

	void mensaje(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}

	String convierteEstado(int estado) {
		if (estado == 0) {
			return "Libre";
		} else
			return "Ocupado";
	}
	String convierteCategoria(int cat) {
		switch (cat) {
		case 0:
			return "Simple";
		case 1:
			return "Doble";
		default:
			return "Familiar";
		}
	}

	int convierteCategoriaString(String cat) {
		if (cat.equals("Simple")) {
			return 0;
		} else if (cat.equals("Doble")) {
			return 1;
		} else
			return 2;
	}
}
