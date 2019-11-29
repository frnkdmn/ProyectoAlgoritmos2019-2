package gui;

import java.awt.EventQueue;

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
import controlador.bungalowControlador;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class FrmBungalow extends JInternalFrame implements ActionListener, ItemListener, MouseListener {

	/**
	 * 
	 */
	// PASO1
	private bungalowControlador c = new bungalowControlador();
	private static final long serialVersionUID = 1L;
	private JTextField txtNumero;
	private JTable table;
	private JComboBox<String> cboCategoria;
	private JComboBox<String> cboEstado;
	private JTextField txtPrecio;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JScrollPane scrollPane;

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

		JLabel lblEstado = new JLabel("Estado: ");
		lblEstado.setBounds(34, 75, 81, 14);
		getContentPane().add(lblEstado);

		cboEstado = new JComboBox<String>();
		cboEstado.setModel(new DefaultComboBoxModel<String>(new String[] { "Libre", "Ocupado" }));
		cboEstado.setBounds(125, 72, 96, 20);
		getContentPane().add(cboEstado);

		txtNumero = new JTextField();
		txtNumero.setBounds(125, 33, 96, 20);
		getContentPane().add(txtNumero);
		txtNumero.setColumns(10);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.setBounds(484, 32, 89, 23);
		getContentPane().add(btnAdicionar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setBounds(484, 71, 89, 23);
		getContentPane().add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(484, 105, 89, 23);
		getContentPane().add(btnEliminar);

		JLabel lblCatergora = new JLabel("Catergor\u00EDa:");
		lblCatergora.setBounds(34, 109, 81, 14);
		getContentPane().add(lblCatergora);

		cboCategoria = new JComboBox<String>();
		cboCategoria.addItemListener(this);
		cboCategoria.setModel(new DefaultComboBoxModel<String>(new String[] { "Simple", "Doble", "Familiar" }));
		cboCategoria.setBounds(125, 106, 96, 20);
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
		lblPrecio.setBounds(31, 143, 48, 14);
		getContentPane().add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setText("100");
		txtPrecio.setEditable(false);
		txtPrecio.setBounds(125, 140, 96, 20);
		getContentPane().add(txtPrecio);
		txtPrecio.setColumns(10);
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
		eliminar();
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
			Object[] f = { x.getNumeroBungalow(), convierteEstado(x.getEstado()), convierteCategoria(x.getCategoria()), x.getPrecioPorDia(), };
			dtm.addRow(f);
		}
	}

	void adicionar() {
		String cod = txtNumero.getText().trim();
		int est = cboEstado.getSelectedIndex();
		int cat = cboCategoria.getSelectedIndex();
		String pre = txtPrecio.getText().trim();
		// No existe
		if (c.buscarPorCodigo(Integer.parseInt(cod)) == null) {
			Bungalow obj = new Bungalow();
			obj.setNumeroBungalow(Integer.parseInt(cod));
			obj.setEstado(est);
			obj.setCategoria(cat);
			obj.setPrecioPorDia(Double.parseDouble(pre));
			c.agregar(obj);
			c.grabarData();
			listar();
			mensaje("Se agregó correctamente");
			limpiarCajas();
		} else {
			// Si existe un vehiculo con esa placa
			mensaje("Ya existe producto con código :" + cod);
		}

	}

	void buscar() {
		int fila = table.getSelectedRow();
		if (fila == -1) {
			mensaje("Seleccione una fila");
		} else {
			// Se obtiene valores de la celda
			int cod = (Integer) table.getValueAt(fila, 0);
			int est = convierteEstadoString((String)table.getValueAt(fila, 1));
			int cat = convierteCategoriaString((String)table.getValueAt(fila, 2));
			double pre = (Double) table.getValueAt(fila, 3);

			txtNumero.setText(String.valueOf(cod));
			cboEstado.setSelectedIndex(est);
			cboCategoria.setSelectedIndex(cat);
			txtPrecio.setText(String.valueOf(pre));
		}
	}

	void eliminar() {
		int cod = Integer.parseInt(txtNumero.getText().trim());

		// NO EXISTE auto con esa placa
		if (c.buscarPorCodigo(cod) == null) {
			mensaje("No existe producto con codigo " + cod);
		} else {// SI EXISTE auto con esa placa
			c.eliminaPorCodigo(cod);
			c.grabarData();
			listar();
			limpiarCajas();
		}
	}

	void actualizar() {
		String cod = txtNumero.getText().trim();
		int est = cboEstado.getSelectedIndex();
		int cat = cboCategoria.getSelectedIndex();
		String pre = txtPrecio.getText().trim();

		// No existe
		if (c.buscarPorCodigo(Integer.parseInt(cod)) == null) {
			mensaje("No existe producto con código :" + cod);
		} else {
			Bungalow obj = new Bungalow();
			obj.setNumeroBungalow(Integer.parseInt(cod));
			obj.setEstado(est);
			obj.setCategoria(cat);
			obj.setPrecioPorDia(Double.parseDouble(pre));
			c.actualizar(obj);
			c.grabarData();
			listar();
			mensaje("Se acutalizó correctamente");
			limpiarCajas();
		}
	}

	void limpiarCajas() {
		txtNumero.setText("");
		cboEstado.setSelectedIndex(0);
		cboCategoria.setSelectedIndex(0);
		txtPrecio.setText("100");
	}

	void mensaje(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
	String convierteEstado(int estado) {
		if(estado==0) {
			return "Libre";
		}
		else return "Ocupado";
	}
	int convierteEstadoString(String estado) {
		if(estado.equals("Libre")) {
			return 0;
		}
		else return 1;
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
		if(cat.equals("Simple")){
			return 0;
		} else if(cat.equals("Doble")) {
			return 1;
		} else return 2;
	}
}
