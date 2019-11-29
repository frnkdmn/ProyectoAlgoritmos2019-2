package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FrmSocio extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnEliminar;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	private JTextField txtNoEditable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSocio frame = new FrmSocio();
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
	public FrmSocio() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setVisible(false);
		setTitle("Mantenimiento Socio");
		setSize(650, 450);
		setFrameIcon(new ImageIcon(FrmSocio.class.getResource("/imagenes/user_blue_32.png")));
		getContentPane().setLayout(null);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(502, 49, 89, 23);
		getContentPane().add(btnAdicionar);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(502, 83, 89, 23);
		getContentPane().add(btnConsultar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(502, 117, 89, 23);
		getContentPane().add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(502, 151, 89, 23);
		getContentPane().add(btnEliminar);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(39, 28, 89, 14);
		getContentPane().add(lblCdigo);
		
		JLabel lblNombres = new JLabel("Nombres: ");
		lblNombres.setBounds(39, 53, 89, 14);
		getContentPane().add(lblNombres);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(39, 87, 89, 14);
		getContentPane().add(lblApellidos);
		
		JLabel lblNewLabel = new JLabel("DNI:");
		lblNewLabel.setBounds(39, 121, 89, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(39, 155, 89, 14);
		getContentPane().add(lblTelfono);
		
		textField = new JTextField();
		textField.setBounds(138, 50, 135, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(138, 84, 135, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(138, 118, 135, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(138, 152, 135, 20);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		txtNoEditable = new JTextField();
		txtNoEditable.setText("No editable");
		txtNoEditable.setEnabled(false);
		txtNoEditable.setEditable(false);
		txtNoEditable.setBounds(138, 25, 96, 20);
		getContentPane().add(txtNoEditable);
		txtNoEditable.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 217, 557, 178);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnGrabar = new JButton("Grabar");
		btnGrabar.setBounds(291, 24, 89, 23);
		getContentPane().add(btnGrabar);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEliminar) {
			do_btnEliminar_actionPerformed(e);
		}
	}
	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
	}
}
