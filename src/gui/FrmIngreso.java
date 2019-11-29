package gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FrmIngreso extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_2;
	private JTable table;

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
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setBounds(118, 45, 96, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(118, 110, 96, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setBounds(118, 76, 96, 20);
		getContentPane().add(comboBox);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(486, 44, 89, 23);
		getContentPane().add(btnIngresar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 153, 575, 243);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
