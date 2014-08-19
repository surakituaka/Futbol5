package GUI;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class MainAdmin extends JFrame {
	private static final long serialVersionUID = 828297704025213357L;
	private JPanel contentPane;
	private static Principal pantalla_principal;
	private static MainAdmin yo;
	private final GlobalParameters global;
	
	public MainAdmin(GlobalParameters globales) {
		global = globales;
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainAdmin.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
		setTitle("The Grid");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		        // Perhaps ask user if they want to save any unsaved files first.
				pantalla_principal.habilitarAdmin();
		    }
		});
		setBounds(100, 100, 292, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		pantalla_principal = (Principal) global.pantalla_anterior;
		yo = this;
		
		JLabel Titulo = new JLabel("Panel de Administraci\u00F3n");
		Titulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		Titulo.setBounds(10, 11, 272, 53);
		contentPane.add(Titulo);
		
		JButton btnPartidos = new JButton("Partidos");
		btnPartidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUI_Partido pantalla_gestion_partidos = new GUI_Partido(new GlobalParameters(global, null, yo));
				pantalla_gestion_partidos.setVisible(true);
				yo.setEnabled(false);
			}
		});
		btnPartidos.setBounds(48, 75, 178, 29);
		contentPane.add(btnPartidos);
		
		JButton btnEquipos = new JButton("Equipos");
		btnEquipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUI_Equipos pantalla_gestion_equipos = new GUI_Equipos(new GlobalParameters(global, null, yo));
				pantalla_gestion_equipos.setVisible(true);
				yo.setEnabled(false);
			}
		});
		btnEquipos.setBounds(48, 155, 178, 29);
		contentPane.add(btnEquipos);
		
		JButton btnGestionJugadores = new JButton("Gesti\u00F3n de Jugadores");
		btnGestionJugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUI_Jugadores pantalla_gestion_jugadores = new GUI_Jugadores(new GlobalParameters(global, null, yo));
				pantalla_gestion_jugadores.setVisible(true);
				yo.setEnabled(false);
			}
		});
		btnGestionJugadores.setBounds(48, 115, 178, 29);
		contentPane.add(btnGestionJugadores);
		
		JButton btnCerrarSesin = new JButton("Cerrar Sesi\u00F3n");
		btnCerrarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pantalla_principal.habilitarAdmin();
				dispose();
			}
		});
		btnCerrarSesin.setToolTipText("");
		btnCerrarSesin.setSelectedIcon(new ImageIcon(MainAdmin.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		btnCerrarSesin.setIcon(new ImageIcon(MainAdmin.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		btnCerrarSesin.setBounds(48, 235, 178, 29);
		contentPane.add(btnCerrarSesin);
		
		JButton btnBuzon = new JButton("Buzon de Mensajes");
		btnBuzon.setBounds(48, 195, 178, 29);
		contentPane.add(btnBuzon);
	}
	public void refreshComboJugadores(){
		pantalla_principal.refreshComboJugadores();
	}
}

