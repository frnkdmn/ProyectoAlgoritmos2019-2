package gui;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FrmHospedajes extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmHospedajes frame = new FrmHospedajes();
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
	public FrmHospedajes() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setVisible(false);
		setTitle("Pagos Hospedajes");
		setSize(650, 450);
		//setFrameIcon(new ImageIcon(FrmHospedajes.class.getResource("/imagenes/money.png")));
		getContentPane().setLayout(null);
		
		JLabel lblHospedaje = new JLabel("Hospedaje:");
		lblHospedaje.setBounds(24, 54, 91, 14);
		getContentPane().add(lblHospedaje);
		
		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setBounds(114, 51, 91, 20);
		getContentPane().add(comboBox);
		
		JButton btnPagar = new JButton("Pagar");
		btnPagar.setBounds(459, 50, 89, 23);
		getContentPane().add(btnPagar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 110, 587, 286);
		getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}
}
