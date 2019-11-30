package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import clases.Socio;
import controlador.SocioControlador;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import java.awt.Color;

public class FrmSocio extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnEliminar;
	private JTextField txtNom;
	private JTextField txtApe;
	private JTextField txtDni;
	private JTextField txtTel;
	private JTable table;
	private JTextField txtCod;
	private SocioControlador c = new SocioControlador();
	private JTextField txtCod2;

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
		setBackground(SystemColor.scrollbar);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setVisible(false);
		setTitle("Mantenimiento Socio");
		setSize(650, 450);
		setFrameIcon(new ImageIcon(FrmSocio.class.getResource("/imagenes/user_blue_32.png")));
		getContentPane().setLayout(null);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					adicionar();
			}
		});
		btnAdicionar.setBounds(502, 49, 89, 23);
		getContentPane().add(btnAdicionar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizar();
			}
		});
		btnModificar.setBounds(502, 117, 89, 23);
		getContentPane().add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(502, 151, 89, 23);
		getContentPane().add(btnEliminar);

		JLabel lblCdigo = new JLabel("C\u00F3digo:");
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

		txtNom = new JTextField();
		txtNom.setBounds(138, 50, 135, 20);
		getContentPane().add(txtNom);
		txtNom.setColumns(10);

		txtApe = new JTextField();
		txtApe.setBounds(138, 84, 135, 20);
		getContentPane().add(txtApe);
		txtApe.setColumns(10);

		txtDni = new JTextField();
		txtDni.setBounds(138, 118, 135, 20);
		getContentPane().add(txtDni);
		txtDni.setColumns(10);

		txtTel = new JTextField();
		txtTel.setBounds(138, 152, 135, 20);
		getContentPane().add(txtTel);
		txtTel.setColumns(10);

		txtCod = new JTextField("" + c.codigoCorrelativo());
		txtCod.setBackground(SystemColor.activeCaption);
		txtCod.setEditable(false);
		txtCod.setForeground(Color.BLACK);
		txtCod.setBounds(138, 25, 96, 20);
		getContentPane().add(txtCod);
		txtCod.setColumns(10);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 217, 557, 178);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscar();
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "C\u00F3digo", "Nombres", "Apellidos", "DNI", "Tel\u00E9fono" }));
		scrollPane.setViewportView(table);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					consultar();
				} catch (Exception e2) {
					mensaje("Debe ingresar un codigo válido");
				}
				
			}
		});
		btnConsultar.setBounds(502, 83, 89, 23);
		getContentPane().add(btnConsultar);
		
		JLabel lblConsulta = new JLabel("Consulta(C\u00F3digo):");
		lblConsulta.setBounds(277, 28, 110, 14);
		getContentPane().add(lblConsulta);
		
		txtCod2 = new JTextField();
		txtCod2.setToolTipText("Codigo\r\n");
		txtCod2.setBounds(391, 25, 96, 20);
		getContentPane().add(txtCod2);
		txtCod2.setColumns(10);
		listar();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEliminar) {
			do_btnEliminar_actionPerformed(e);
		}
	}

	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
		if (c.tamaño() == 0) {
			mensaje("No existen bungalows");
		} else {
			int ok = confirmar("¿Desea eliminar el bungalow " + txtCod.getText() + "?");
			if (ok == 0) {
				eliminar();
			}
		}
	}
	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
	}
	void listar() {
		// Limpia la tabla de la GUI
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		// Se limpia la tabla
		dtm.setRowCount(0);
		// Se añade datos a la tabla
		for (int i = 0; i < c.tamaño(); i++) {
			Socio x = c.obtener(i);
			Object[] f = { x.getCodigoSocio(), x.getNombres(), x.getApellidos(), x.getDni(), x.getTelefono() };
			dtm.addRow(f);
		}
	}

	void adicionar() {
		int cod = Integer.parseInt(txtCod.getText().trim());
		String nom = txtNom.getText().trim();
		String ape = txtApe.getText().trim();
		String dni = txtDni.getText().trim();
		String tel = txtTel.getText().trim();
		// No existe
		if (c.buscarPorCodigo(cod) != null) {
			limpiarCajas();
		} else {
			if (nom.equals("") || ape.equals("") || dni.equals("") || tel.equals("")) {
				mensaje("Completar correctamen todos los campos");
			} else {
				Socio obj = new Socio(cod, nom, ape, dni, tel);
				c.agregar(obj);
				c.grabarData();
				listar();
				limpiarCajas();
				mensaje("Se agregó correctamente");
			}
		}

	}
	void consultar() {
		int cod = Integer.parseInt(txtCod2.getText().trim());
		if(c.buscarPorCodigo(cod) ==null){
			mensaje("No existe Socio con codigo " + cod);
		} else {
			Socio obj = c.buscarPorCodigo(cod);
			txtCod.setText(String.valueOf(obj.getCodigoSocio()));
			txtNom.setText(obj.getNombres());
			txtApe.setText(obj.getApellidos());
			txtDni.setText(obj.getDni());
			txtTel.setText(obj.getTelefono());
		}
	}
	void buscar() {
		int fila = table.getSelectedRow();
		if (fila == -1) {
			mensaje("Seleccione una fila");
		} else {
			// Se obtiene valores de la celda
			int cod = (Integer) table.getValueAt(fila, 0);
			String nom = (String) table.getValueAt(fila, 1);
			String ape = (String) table.getValueAt(fila, 2);
			String dni = (String) table.getValueAt(fila, 3);
			String tel = (String) table.getValueAt(fila, 4);

			txtCod.setText(String.valueOf(cod));
			txtNom.setText(nom);
			txtApe.setText(ape);
			txtDni.setText(dni);
			txtTel.setText(tel);

		}
	}

	void eliminar() {
		int cod = Integer.parseInt(txtCod.getText().trim());

		// NO EXISTE auto con esa placa
		if (c.buscarPorCodigo(cod) == null) {
			mensaje("No existe producto con codigo " + cod);
		} else {// SI EXISTE auto con esa placa
			c.eliminaPorCodigo(cod);
			c.grabarData();
			listar();
			limpiarCajas();
			mensaje("Socio eliminado");
		}
	}

	void actualizar() {
		int cod = Integer.parseInt(txtCod.getText().trim());
		String nom = txtNom.getText().trim();
		String ape = txtApe.getText().trim();
		String dni = txtDni.getText().trim();
		String tel = txtTel.getText().trim();

		// No existe
		if (c.buscarPorCodigo(cod) == null) {
			mensaje("No existe producto con código :" + cod);
		} else {
			Socio obj = new Socio(cod, nom, ape, dni, tel);
			c.actualizar(obj);
			c.grabarData();
			listar();
			limpiarCajas();
			mensaje("Se acutalizó correctamente");
		}
	}

	void limpiarCajas() {
		txtCod.setText("" + c.codigoCorrelativo());
		txtNom.setText("");
		txtApe.setText("");
		txtDni.setText("");
		txtTel.setText("");
	}

	void mensaje(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}

	String convierteEstado(int estado) {
		if (estado == 0) {
			return "Libre";
		} else
			return "Ocupado";
	}

	int convierteEstadoString(String estado) {
		if (estado.equals("Libre")) {
			return 0;
		} else
			return 1;
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
		if (cat.equals("Simple")) {
			return 0;
		} else if (cat.equals("Doble")) {
			return 1;
		} else
			return 2;
	}
}
