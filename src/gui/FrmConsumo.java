package gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FrmConsumo extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;

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
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setVisible(false);
		setTitle("Registro Consumo");
		setSize(650, 450);
		setFrameIcon(new ImageIcon(FrmConsumo.class.getResource("/imagenes/page_add.png")));
		getContentPane().setLayout(null);
		
		JLabel lblSocio = new JLabel("Ingreso:");
		lblSocio.setBounds(25, 54, 65, 14);
		getContentPane().add(lblSocio);
		
		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setBounds(100, 51, 91, 20);
		getContentPane().add(comboBox);
		
		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setBounds(25, 92, 71, 14);
		getContentPane().add(lblProducto);
		
		JComboBox<Object> comboBox_1 = new JComboBox<Object>();
		comboBox_1.setBounds(100, 89, 91, 20);
		getContentPane().add(comboBox_1);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(211, 88, 89, 23);
		getContentPane().add(btnAgregar);
		
		JButton btnQuitar = new JButton("Quitar");
		btnQuitar.setBounds(310, 88, 89, 23);
		getContentPane().add(btnQuitar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(494, 50, 89, 23);
		getContentPane().add(btnGuardar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 133, 603, 276);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setRowHeaderView(table);
	}
}
