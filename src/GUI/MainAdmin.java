package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class MainAdmin extends VentanaTheGrid {
	private static final long serialVersionUID = 828297704025213357L;
	
	//Componentes no alterables
	private JLabel titulo;
	private JPanel contentPane;

	//Componentes Modificables
	private JButton btnPartidos;
	private JButton btnConfirmacion;
	private JButton btnGestionJugadores;
	private JButton btnCerrarSesion;
	private JButton btnPropuestas;
	private JButton btnFiltrarJugadores;
	
	//Constructor
	public MainAdmin(GlobalParameters caller) {
		super();
		global = caller; //Seteamos los atributos
		yo = this;
		
		//Creemos la JFrame principal
		setResizable(false);
		setTitle("The Grid");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		        global.pantalla_anterior.dispose(); 
		    }
		});
		setBounds(100, 100, 292, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Creemos los botones
		
		btnPartidos = new JButton("Partidos");
		btnConfirmacion = new JButton("Confirmacion");
		btnGestionJugadores = new JButton("Editar Jugadores");
		btnCerrarSesion = new JButton("Cerrar Sesi\u00F3n");
		btnPropuestas = new JButton("Propuestas de Jugadores");

		btnPartidos.setBounds(48, 75, 178, 29);
		btnConfirmacion.setBounds(48, 115, 178, 29);
		btnGestionJugadores.setBounds(48, 155, 178, 29);
		btnCerrarSesion.setBounds(48, 275, 178, 32);
		btnPropuestas.setBounds(48, 235, 178, 29);

		contentPane.add(btnPartidos);
		contentPane.add(btnConfirmacion);
		contentPane.add(btnGestionJugadores);
		contentPane.add(btnCerrarSesion);
		contentPane.add(btnPropuestas);
		
		btnCerrarSesion.setSelectedIcon(new ImageIcon(MainAdmin.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		btnCerrarSesion.setIcon(new ImageIcon(MainAdmin.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		
		//Creemos las labels
		
		titulo = new JLabel("Panel de Administraci\u00F3n");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		titulo.setBounds(10, 11, 272, 53);
		contentPane.add(titulo);
		
		btnFiltrarJugadores = new JButton("Filtrar Jugadores");
		btnFiltrarJugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Busqueda_Jugadores pantalla_jugadores = new Busqueda_Jugadores(new GlobalParameters(global, null, yo));
				habilitarPantalla(pantalla_jugadores);
			}
		});
		btnFiltrarJugadores.setBounds(48, 195, 178, 29);
		contentPane.add(btnFiltrarJugadores);
		
		//Comportamiento de los botones

		btnPartidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Admin_Partidos pantalla_gestion_partidos = new Admin_Partidos(new GlobalParameters(global, null, yo));
				habilitarPantalla(pantalla_gestion_partidos);

			}
		});

		btnConfirmacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Admin_ConfirmacionPartidos pantalla_confirmacion_partidos = new Admin_ConfirmacionPartidos(new GlobalParameters(global, null, yo));
				habilitarPantalla(pantalla_confirmacion_partidos);				
			}
		});
		
		btnGestionJugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Admin_Jugadores pantalla_gestion_jugadores = new Admin_Jugadores(new GlobalParameters(global, null, yo));
				habilitarPantalla(pantalla_gestion_jugadores);
			}
		});

		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				terminate();
			}
		});

		btnPropuestas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Admin_NuevosJugadores pantalla_gestion_jugadores = new Admin_NuevosJugadores(new GlobalParameters(global, null, yo));
				habilitarPantalla(pantalla_gestion_jugadores);
				/*Admin_BandejaEntrada pantalla_bandeja_entrada = new Admin_BandejaEntrada(new GlobalParameters(global, null, yo));
				habilitarPantalla(pantalla_bandeja_entrada);*/
			}
		});
	}
	public void refreshComboJugadores(){
		((Principal) global.pantalla_anterior).refreshCombo();
	}
}

