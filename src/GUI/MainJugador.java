package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

public class MainJugador extends VentanaTheGrid {
	private static final long serialVersionUID = -2861295277923642005L;
	
	//Componentes
	private JPanel contentPane;
	private JLabel titulo;	
	
	//Botones
	private JButton btnInscripcion;
	private JButton btnBaja;
	private JButton btnCalificar;
	private JButton btnDatosPersonales;
	private JButton btnCerrarSesion;
	private JButton btnPartidos;

	//Constructor
	public MainJugador(GlobalParameters caller) {
		super();
		global = caller;//Seteamos el atributo global
		yo = this;
		
		//Definimos la Jframe Principal
		setResizable(false);
		setTitle("The Grid");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		        global.pantalla_anterior.dispose(); 
		    }
		});
		setBounds(100, 100, 322, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Creamos los botones
		
		btnInscripcion = new JButton("Inscribirse a un Partido");
		btnBaja = new JButton("Baja de un Partido");
		btnPartidos = new JButton("Partidos");
		btnCalificar = new JButton("Calificar");
		btnDatosPersonales = new JButton("Datos Personales");
		btnCerrarSesion = new JButton("Cerrar Sesi\u00F3n");
		
		btnInscripcion.setBounds(65, 75, 182, 29);
		btnBaja.setBounds(65, 115, 182, 29);
		btnPartidos.setBounds(65, 155, 182, 29);
		btnCalificar.setBounds(65, 195, 182, 29);
		btnDatosPersonales.setBounds(65, 235, 182, 29);
		btnCerrarSesion.setBounds(65, 275, 182, 29);
	
		contentPane.add(btnInscripcion);
		contentPane.add(btnBaja);
		contentPane.add(btnPartidos);
		contentPane.add(btnCalificar);
		contentPane.add(btnDatosPersonales);
		contentPane.add(btnCerrarSesion);

		btnCerrarSesion.setToolTipText("");
		btnCerrarSesion.setSelectedIcon(new ImageIcon(MainJugador.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		btnCerrarSesion.setIcon(new ImageIcon(MainJugador.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		
		//Creamos los labels
		
		titulo = new JLabel((new String("Usuario: ")).concat(global.jugador_seleccionado.getUsuario()));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		titulo.setBounds(10, 11, 296, 53);
		contentPane.add(titulo);		
		
		//Comportamiento de los botones
		
		btnInscripcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Jugador_Inscribirse pantalla_inscripcion = new Jugador_Inscribirse(new GlobalParameters(global, global.jugador_seleccionado, yo));
				habilitarPantalla(pantalla_inscripcion);			
			}
		});

		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Jugador_Baja pantalla_baja = new Jugador_Baja(new GlobalParameters(global, global.jugador_seleccionado, yo));
				habilitarPantalla(pantalla_baja);			
				//JOptionPane.showMessageDialog(null, "En Construccion...", "Baja", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		btnPartidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Jugador_Partidos pantalla_partidos = new Jugador_Partidos(new GlobalParameters(global, global.jugador_seleccionado, yo));
				habilitarPantalla(pantalla_partidos);			
				//JOptionPane.showMessageDialog(null, "En Construccion...", "Baja", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		btnCalificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Jugador_Calificaciones pantalla_jugador_Calificaciones = new Jugador_Calificaciones(new GlobalParameters(global, global.jugador_seleccionado, yo));
				habilitarPantalla(pantalla_jugador_Calificaciones);			
				//JOptionPane.showMessageDialog(null, "En Construccion...", "Calificar", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		btnDatosPersonales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Jugador_DatosPersonales pantalla_datos_jugador = new Jugador_DatosPersonales(new GlobalParameters(global, global.jugador_seleccionado, yo));
				habilitarPantalla(pantalla_datos_jugador);
				//JOptionPane.showMessageDialog(null, "En Construccion...", "Datos Personales", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				terminate();
			}
		});
	}
}
