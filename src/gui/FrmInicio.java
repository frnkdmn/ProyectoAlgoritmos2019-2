package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class FrmInicio extends JFrame implements ActionListener, WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenu mnMantenimiento;
	private JMenuItem mntmProducto;
	private JMenuItem mntmBungalow;
	private JMenuItem mntmIngreso;
	private JMenuItem mntmConsumo;
	private JMenuItem mntmHospedaje;
	private JMenuItem mntmIngresosYConsumos;
	private JMenuItem mntmHospedajes;
	private JMenuItem mntmIngresosYConsumos_1;
	private JMenuItem mntmIngresosYConsumos_2;
	private JMenuItem mntmHospedajesPendientes;
	private JMenuItem mntmHospedajesPagados;
	private JMenuItem mntmBoletas;
	private JMenuItem mntmSocio;
	private FrmSocio frmSocio = new FrmSocio();
	private FrmProducto frmProducto = new FrmProducto();
	private FrmBungalow frmBungalow = new FrmBungalow();
	private FrmIngreso frmIngreso = new FrmIngreso();
	private FrmConsumo frmConsumo = new FrmConsumo();
	private FrmHospedaje frmHospedaje = new FrmHospedaje();
	private FrmIngresosConsumos frmIngresosConsumos = new FrmIngresosConsumos();
	private FrmHospedajes frmHospedajes = new FrmHospedajes();
	private FrmIngresosConsumosPendientes frmIngresosConsumosPendientes = new FrmIngresosConsumosPendientes();
	private FrmIngresosConsumosPagados frmIngresosConsumosPagados = new FrmIngresosConsumosPagados();
	private FrmHospedajesPendientes frmHospedajesPendientes = new FrmHospedajesPendientes();
	private FrmHospedajesPagados frmHospedajesPagados = new FrmHospedajesPagados();
	private FrmBoletas frmBoletas = new FrmBoletas();
	private JMenuItem mntmPagoExtra;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
					FrmInicio frame = new FrmInicio();
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
	public FrmInicio() {
		addWindowListener(this);
		//setIconImage(Toolkit.getDefaultToolkit().getImage(FrmInicio.class.getResource("/imagenes/report.png")));
		setTitle("Sistema de INGRESO, ATENCI\u00D3N Y HOSPEDAJE");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnMantenimiento = new JMenu("Mantenimiento");
		mnMantenimiento.setMnemonic('M');
		mnMantenimiento.addActionListener(this);
		mnMantenimiento.setIcon(new ImageIcon(FrmInicio.class.getResource("/imagenes/mantenimiento.jpg")));
		menuBar.add(mnMantenimiento);
		
		mntmSocio = new JMenuItem("Socio");
		mntmSocio.setMnemonic('S');
		mntmSocio.setIcon(new ImageIcon(FrmInicio.class.getResource("/imagenes/user_blue_32.png")));
		mntmSocio.addActionListener(this);
		mnMantenimiento.add(mntmSocio);
		
		mntmProducto = new JMenuItem("Producto");
		mntmProducto.setMnemonic('P');
		mntmProducto.setIcon(new ImageIcon(FrmInicio.class.getResource("/imagenes/movimiento.JPG")));
		mntmProducto.addActionListener(this);
		mnMantenimiento.add(mntmProducto);
		
		mntmBungalow = new JMenuItem("Bungalow");
		mntmBungalow.setMnemonic('B');
		mntmBungalow.setIcon(new ImageIcon(FrmInicio.class.getResource("/imagenes/door_in.png")));
		mntmBungalow.addActionListener(this);
		mnMantenimiento.add(mntmBungalow);
		
		JMenu mnRegistro = new JMenu("Registro");
		mnRegistro.setIcon(new ImageIcon(FrmInicio.class.getResource("/imagenes/report_add.png")));
		menuBar.add(mnRegistro);
		
		mntmIngreso = new JMenuItem("Ingreso");
		mntmIngreso.setIcon(new ImageIcon(FrmInicio.class.getResource("/imagenes/page_add.png")));
		mntmIngreso.addActionListener(this);
		mnRegistro.add(mntmIngreso);
		
		mntmConsumo = new JMenuItem("Consumo");
		mntmConsumo.setIcon(new ImageIcon(FrmInicio.class.getResource("/imagenes/page_add.png")));
		mntmConsumo.addActionListener(this);
		mnRegistro.add(mntmConsumo);
		
		mntmHospedaje = new JMenuItem("Hospedaje");
		mntmHospedaje.setIcon(new ImageIcon(FrmInicio.class.getResource("/imagenes/page_add.png")));
		mntmHospedaje.addActionListener(this);
		mnRegistro.add(mntmHospedaje);
		
		JMenu mnPago = new JMenu("Pago");
		mnPago.setIcon(new ImageIcon(FrmInicio.class.getResource("/imagenes/coins_add.png")));
		menuBar.add(mnPago);
		
		mntmIngresosYConsumos = new JMenuItem("Ingresos y consumos");
		mntmIngresosYConsumos.setIcon(new ImageIcon(FrmInicio.class.getResource("/imagenes/money.png")));
		mntmIngresosYConsumos.addActionListener(this);
		mnPago.add(mntmIngresosYConsumos);
		
		mntmHospedajes = new JMenuItem("Hospedajes");
		mntmHospedajes.setIcon(new ImageIcon(FrmInicio.class.getResource("/imagenes/money.png")));
		mntmHospedajes.addActionListener(this);
		mnPago.add(mntmHospedajes);
		
		mntmPagoExtra = new JMenuItem("pago extra");
		mntmPagoExtra.addActionListener(this);
		mntmPagoExtra.setIcon(new ImageIcon(FrmInicio.class.getResource("/imagenes/money.png")));
		mnPago.add(mntmPagoExtra);
		
		JMenu mnReporte = new JMenu("Reporte");
		mnReporte.setIcon(new ImageIcon(FrmInicio.class.getResource("/imagenes/report_magnify.png")));
		menuBar.add(mnReporte);
		
		mntmIngresosYConsumos_1 = new JMenuItem("Ingresos y consumos pendientes");
		mntmIngresosYConsumos_1.setIcon(new ImageIcon(FrmInicio.class.getResource("/imagenes/page_find.png")));
		mntmIngresosYConsumos_1.addActionListener(this);
		mnReporte.add(mntmIngresosYConsumos_1);
		
		mntmIngresosYConsumos_2 = new JMenuItem("Ingresos y consumos pagados");
		mntmIngresosYConsumos_2.setIcon(new ImageIcon(FrmInicio.class.getResource("/imagenes/page_find.png")));
		mntmIngresosYConsumos_2.addActionListener(this);
		mnReporte.add(mntmIngresosYConsumos_2);
		
		mntmHospedajesPendientes = new JMenuItem("Hospedajes pendientes");
		mntmHospedajesPendientes.setIcon(new ImageIcon(FrmInicio.class.getResource("/imagenes/page_find.png")));
		mntmHospedajesPendientes.addActionListener(this);
		mnReporte.add(mntmHospedajesPendientes);
		
		mntmHospedajesPagados = new JMenuItem("Hospedajes pagados");
		mntmHospedajesPagados.setIcon(new ImageIcon(FrmInicio.class.getResource("/imagenes/page_find.png")));
		mntmHospedajesPagados.addActionListener(this);
		mnReporte.add(mntmHospedajesPagados);
		
		JMenu mnBoleta = new JMenu("Boleta");
		mnBoleta.setIcon(new ImageIcon(FrmInicio.class.getResource("/imagenes/money_dollar.png")));
		menuBar.add(mnBoleta);
		
		mntmBoletas = new JMenuItem("Boletas");
		mntmBoletas.setIcon(new ImageIcon(FrmInicio.class.getResource("/imagenes/money_dollar.png")));
		mntmBoletas.addActionListener(this);
		mnBoleta.add(mntmBoletas);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.activeCaption);
		contentPane.add(desktopPane, BorderLayout.CENTER);
		desktopPane.add(frmSocio);
		desktopPane.add(frmProducto);
		desktopPane.add(frmBungalow);
		desktopPane.add(frmIngreso);
		desktopPane.add(frmConsumo);
		desktopPane.add(frmHospedaje);
		desktopPane.add(frmIngresosConsumos);
		desktopPane.add(frmHospedajes);
		desktopPane.add(frmIngresosConsumosPendientes);
		desktopPane.add(frmIngresosConsumosPagados);
		desktopPane.add(frmHospedajesPendientes);
		desktopPane.add(frmHospedajesPagados);
		desktopPane.add(frmBoletas);
	}

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == mntmSocio) {
			do_mntmSocio_actionPerformed(e);
		}
		if (e.getSource() == mntmBoletas) {
			do_mntmBoletas_actionPerformed(e);
		}
		if (e.getSource() == mntmHospedajesPagados) {
			do_mntmHospedajesPagados_actionPerformed(e);
		}
		if (e.getSource() == mntmHospedajesPendientes) {
			do_mntmHospedajesPendientes_actionPerformed(e);
		}
		if (e.getSource() == mntmIngresosYConsumos_2) {
			do_mntmIngresosYConsumos_2_actionPerformed(e);
		}
		if (e.getSource() == mntmIngresosYConsumos_1) {
			do_mntmIngresosYConsumos_1_actionPerformed(e);
		}
		if (e.getSource() == mntmHospedajes) {
			do_mntmHospedajes_actionPerformed(e);
		}
		if (e.getSource() == mntmIngresosYConsumos) {
			do_mntmIngresosYConsumos_actionPerformed(e);
		}
		if (e.getSource() == mntmHospedaje) {
			do_mntmHospedaje_actionPerformed(e);
		}
		if (e.getSource() == mntmConsumo) {
			do_mntmConsumo_actionPerformed(e);
		}
		if (e.getSource() == mntmIngreso) {
			do_mntmIngreso_actionPerformed(e);
		}
		if (e.getSource() == mntmBungalow) {
			do_mntmBungalow_actionPerformed(e);
		}
		if (e.getSource() == mntmProducto) {
			do_mntmProducto_actionPerformed(e);
		}
		
	}
	protected void do_mntmSocio_actionPerformed(ActionEvent e) {
		centrar(frmSocio);
		frmSocio.setVisible(true);

	}
	protected void do_mntmProducto_actionPerformed(ActionEvent e) {
		centrar(frmProducto);
		frmProducto.setVisible(true);
	}
	protected void do_mntmBungalow_actionPerformed(ActionEvent e) {
		centrar(frmBungalow);
		frmBungalow.setVisible(true);
	}
	protected void do_mntmIngreso_actionPerformed(ActionEvent e) {
		centrar(frmIngreso);
		frmIngreso.setVisible(true);
	}
	protected void do_mntmConsumo_actionPerformed(ActionEvent e) {
		centrar(frmConsumo);
		frmConsumo.setVisible(true);
	}
	protected void do_mntmHospedaje_actionPerformed(ActionEvent e) {
		centrar(frmHospedaje);
		frmHospedaje.setVisible(true);
	}
	protected void do_mntmIngresosYConsumos_actionPerformed(ActionEvent e) {
		centrar(frmIngresosConsumos);
		frmIngresosConsumos.setVisible(true);
	}
	protected void do_mntmHospedajes_actionPerformed(ActionEvent e) {
		centrar(frmHospedajes);
		frmHospedajes.setVisible(true);
	}
	protected void do_mntmIngresosYConsumos_1_actionPerformed(ActionEvent e) {
		centrar(frmIngresosConsumosPendientes);
		frmIngresosConsumosPendientes.setVisible(true);
	}
	protected void do_mntmIngresosYConsumos_2_actionPerformed(ActionEvent e) {
		centrar(frmIngresosConsumosPagados);
		frmIngresosConsumosPagados.setVisible(true);
	}
	protected void do_mntmHospedajesPendientes_actionPerformed(ActionEvent e) {
		centrar(frmHospedajesPendientes);
		frmHospedajesPendientes.setVisible(true);
	}
	protected void do_mntmHospedajesPagados_actionPerformed(ActionEvent e) {
		centrar(frmHospedajesPagados);
		frmHospedajesPagados.setVisible(true);
	}
	protected void do_mntmBoletas_actionPerformed(ActionEvent e) {
		centrar(frmBoletas);
		frmBoletas.setVisible(true);
	}
	
	public void windowActivated(WindowEvent e) {
	}
	public void windowClosed(WindowEvent e) {
	}
	public void windowClosing(WindowEvent e) {
		if (e.getSource() == this) {
			do_this_windowClosing(e);
		}
	}
	public void windowDeactivated(WindowEvent e) {
	}
	public void windowDeiconified(WindowEvent e) {
	}
	public void windowIconified(WindowEvent e) {
	}
	public void windowOpened(WindowEvent e) {
	}
	protected void do_this_windowClosing(WindowEvent e) {
		int n = JOptionPane.showConfirmDialog(this, "¿Desea salir de la aplicación?","Confirmación",JOptionPane.YES_NO_OPTION);
		if(n == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	public void centrar(JInternalFrame frm) {
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension formulario = frm.getSize();
		
		int posX = (int) (pantalla.getWidth() - formulario.getWidth()) / 2;
		int posY = (int) (pantalla.getHeight() - formulario.getHeight()) /2;
		
		frm.setLocation(posX,posY-100);
	}
	
}
