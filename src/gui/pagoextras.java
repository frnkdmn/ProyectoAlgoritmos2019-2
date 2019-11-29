package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class pagoextras extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pagoextras frame = new pagoextras();
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
	public pagoextras() {
		setTitle("Pago Extra");
		setIconifiable(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblPagoExtra = new JLabel("Pago Extra");
		lblPagoExtra.setBounds(40, 34, 92, 14);
		getContentPane().add(lblPagoExtra);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(40, 73, 69, 14);
		getContentPane().add(lblTipo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(119, 70, 80, 20);
		getContentPane().add(comboBox);
		
		JLabel lblRomeromorales = new JLabel("Romero_Morales");
		lblRomeromorales.setBounds(40, 123, 140, 14);
		getContentPane().add(lblRomeromorales);

	}
}
