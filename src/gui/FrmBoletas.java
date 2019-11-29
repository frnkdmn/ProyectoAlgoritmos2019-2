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

public class FrmBoletas extends JInternalFrame {

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
					FrmBoletas frame = new FrmBoletas();
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
	public FrmBoletas() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setVisible(false);
		setTitle("Boletas");
		setSize(650, 450);
		setFrameIcon(new ImageIcon(FrmBoletas.class.getResource("/imagenes/money_dollar.png")));
		getContentPane().setLayout(null);
		
		JLabel lblBoleta = new JLabel("Ingreso:");
		lblBoleta.setBounds(27, 61, 65, 14);
		getContentPane().add(lblBoleta);
		
		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setBounds(106, 58, 93, 20);
		getContentPane().add(comboBox);
		
		JButton btnEmitir = new JButton("Emitir");
		btnEmitir.setBounds(454, 57, 89, 23);
		getContentPane().add(btnEmitir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 119, 614, 290);
		getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

	}

}
