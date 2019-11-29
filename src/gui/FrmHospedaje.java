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
import javax.swing.JTextArea;

public class FrmHospedaje extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;

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
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setBounds(108, 51, 96, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setBounds(108, 76, 96, 20);
		getContentPane().add(comboBox);
		
		JComboBox<Object> comboBox_1 = new JComboBox<Object>();
		comboBox_1.setBounds(108, 101, 96, 20);
		getContentPane().add(comboBox_1);
		
		JButton btnHospedar = new JButton("Hospedar");
		btnHospedar.setBounds(482, 50, 89, 23);
		getContentPane().add(btnHospedar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 146, 614, 263);
		getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

	}

}
