package gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FrmIngresosConsumos extends JInternalFrame {

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
					FrmIngresosConsumos frame = new FrmIngresosConsumos();
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
	public FrmIngresosConsumos() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setVisible(false);
		setTitle("Pago Ingresos y Cosumos");
		setSize(650, 450);
		setFrameIcon(new ImageIcon(FrmIngresosConsumos.class.getResource("/imagenes/money.png")));
		getContentPane().setLayout(null);
		
		JLabel lblIngreso = new JLabel("Ingreso:");
		lblIngreso.setBounds(29, 45, 85, 14);
		getContentPane().add(lblIngreso);
		
		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setBounds(124, 42, 85, 20);
		getContentPane().add(comboBox);
		
		JButton btnPagar = new JButton("Pagar");
		btnPagar.setBounds(486, 41, 89, 23);
		getContentPane().add(btnPagar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 113, 582, 296);
		getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}
}
