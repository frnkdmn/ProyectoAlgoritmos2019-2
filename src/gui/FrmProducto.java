package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import clases.Producto;
import controlador.ProductoControlador;

public class FrmProducto extends JInternalFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private ProductoControlador c = new ProductoControlador();
	private static final long serialVersionUID = 1L;
	private JTextField txtCodigo;
	private JTextField txtDetalle;
	private JTextField txtPrecio;
	private JTextField txtStock;
	private JTable table;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnEliminar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmProducto frame = new FrmProducto();
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
	public FrmProducto() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setVisible(false);
		setTitle("Mantenimiento Producto");
		setSize(650, 450);
		getContentPane().setLayout(null);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo: ");
		lblCdigo.setBounds(30, 38, 85, 14);
		getContentPane().add(lblCdigo);
		
		JLabel lblDetalle = new JLabel("Detalle:");
		lblDetalle.setBounds(30, 84, 85, 14);
		getContentPane().add(lblDetalle);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(30, 109, 85, 14);
		getContentPane().add(lblPrecio);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(30, 134, 85, 14);
		getContentPane().add(lblStock);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(125, 35, 96, 20);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtDetalle = new JTextField();
		txtDetalle.setBounds(125, 81, 150, 20);
		getContentPane().add(txtDetalle);
		txtDetalle.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(125, 106, 150, 20);
		getContentPane().add(txtPrecio);
		txtPrecio.setColumns(10);
		
		txtStock = new JTextField();
		txtStock.setBounds(125, 131, 150, 20);
		getContentPane().add(txtStock);
		txtStock.setColumns(10);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.setBounds(508, 38, 89, 23);
		getContentPane().add(btnAdicionar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setBounds(508, 80, 89, 23);
		getContentPane().add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(508, 109, 89, 23);
		getContentPane().add(btnEliminar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 183, 575, 226);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Detalle", "Precio Unitario", "Stock"
			}
		));
		scrollPane.setViewportView(table);
		setFrameIcon(new ImageIcon(FrmProducto.class.getResource("/imagenes/movimiento.JPG")));
		//Se muestra la lista al terminar la GUI.
		listar();
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminarJButton(arg0);
		}
		if (arg0.getSource() == btnModificar) {
			actionPerformedBtnModificarJButton(arg0);
		}
		if (arg0.getSource() == btnAdicionar) {
			actionPerformedBtnAdicionarJButton(arg0);
		}
	}
	protected void actionPerformedBtnAdicionarJButton(ActionEvent arg0) {
		adicionar();
	}
	protected void actionPerformedBtnModificarJButton(ActionEvent arg0) {
		actualizar();
	}
	protected void actionPerformedBtnEliminarJButton(ActionEvent arg0) {
		eliminar();
	}
	
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == table) {
			mouseClickedTableJTable(arg0);
		}
	}
	public void mouseEntered(MouseEvent arg0) {
		
	}
	public void mouseExited(MouseEvent arg0) {
		
	}
	public void mousePressed(MouseEvent arg0) {
		
	}
	public void mouseReleased(MouseEvent arg0) {
		
	}
	protected void mouseClickedTableJTable(MouseEvent arg0) {
		buscar();
	}
	 
		// métodos del matenimiento
		void listar() {
			//Limpia la tabla de la GUI
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			dtm.setRowCount(0);

			for (int i = 0; i < c.tamaño(); i++) {
				Producto x =c.obtener(i);
				Object[] f = { x.getCodigoProducto(), x.getDetalle(), x.getPrecioUnitario(), x.getStock() };
				dtm.addRow(f);
			}
		}

		void adicionar() {
			String cod = txtCodigo.getText().trim();
			String nom = txtDetalle.getText().trim();
			String pre = txtPrecio.getText().trim();
			String stock = txtStock.getText().trim();

			// No existe
			if (c.buscarPorCodigo(Integer.parseInt(cod)) == null) {
				Producto obj = new Producto();
				obj.setCodigoProducto(Integer.parseInt(cod));
				obj.setDetalle(nom);
				obj.setPrecioUnitario(Double.parseDouble(pre));
				obj.setStock(Integer.parseInt(stock));
				c.agregar(obj);
				c.grabarData();
				listar();
				mensaje("Se agregó correctamente");
				limpiarCajas();
			} else {// Si existe un vehiculo con esa placa
				mensaje("Ya existe producto con código :" + cod);
			}

		}

		void buscar() {
			int fila = table.getSelectedRow();
			if (fila == -1) {
				mensaje("Seleccione una fila");
			} else {
				//Se obtiene valores de la celda
				int cod = (Integer) table.getValueAt(fila, 0);
				String nom = (String) table.getValueAt(fila, 1);
				double pre = (Double) table.getValueAt(fila, 2);
				int stock = (Integer) table.getValueAt(fila, 3);

				txtCodigo.setText(String.valueOf(cod));
				txtDetalle.setText(nom);
				txtPrecio.setText(String.valueOf(pre));
				txtStock.setText(String.valueOf(stock));
			}
		}

		void eliminar() {
			int cod = Integer.parseInt(txtCodigo.getText().trim());

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
			String cod = txtCodigo.getText().trim();
			String nom = txtDetalle.getText().trim();
			String pre = txtPrecio.getText().trim();
			String stock = txtStock.getText().trim();

			// No existe
			if (c.buscarPorCodigo(Integer.parseInt(cod)) == null) {
				mensaje("No existe producto con código :" + cod);
			} else {
				Producto obj = new Producto();
				obj.setCodigoProducto(Integer.parseInt(cod));
				obj.setDetalle(nom);
				obj.setPrecioUnitario(Double.parseDouble(pre));
				obj.setStock(Integer.parseInt(stock));
				c.actualizar(obj);
				c.grabarData();
				listar();
				mensaje("Se acutalizó correctamente");
			}
		}

		void limpiarCajas() {
			txtCodigo.setText("");
			txtDetalle.setText("");
			txtPrecio.setText("");
			txtStock.setText("");
		}

		void mensaje(String msg) {
			JOptionPane.showMessageDialog(this, msg);
		}
}
