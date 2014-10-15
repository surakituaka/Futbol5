package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Visualizacion_Jugadores extends VentanaTheGrid {
	//Componentes no cambiantes
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JLabel lblPenalizaciones;

	//Componentes modificables
	
	private JLabel lblNombre;
	private JLabel lblHandicap;
	private JLabel lblPromedioUltimo;
	private JLabel lblPromedioTodos;
	private JLabel lblFecha;
	private JLabel lblPartidosJugados;
	private JButton btnVolver;
	
	//Generadores de Texto
	private String gen_nombre = "Nombre ";
	private String gen_handicap = "Handicap ";
	private String gen_promedioUltimo = "Promedio del Ultimo Partido ";
	private String gen_promedioTodos = "Promedio de los Partidos Jugados ";
	private String gen_fecha = "Fecha de Nacimiento ";
	private String gen_partidos = "Partidos Jugados ";
	
	//Constructor
	public Visualizacion_Jugadores(GlobalParameters caller) {
		global = caller; //Seteamos el atributo global
		
		//Definimos la Jframe Principal
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		        global.pantalla_anterior.setVisible(true); 
		    }
		});
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Datos Personales");
		setBounds(100, 100, 291, 400);
		getContentPane().setLayout(null);
		
		//Creemos las Labels
		lblNombre = new JLabel(gen_nombre);
		lblNombre.setBounds(10, 11, 265, 14);
		getContentPane().add(lblNombre);
		
		lblHandicap = new JLabel(gen_handicap);
		lblHandicap.setBounds(10, 36, 265, 14);
		getContentPane().add(lblHandicap);
		
		lblPromedioUltimo = new JLabel(gen_promedioUltimo);
		lblPromedioUltimo.setBounds(10, 61, 265, 14);
		getContentPane().add(lblPromedioUltimo);
		
		lblPromedioTodos = new JLabel(gen_promedioTodos);
		lblPromedioTodos.setBounds(10, 86, 265, 14);
		getContentPane().add(lblPromedioTodos);
		
		lblFecha = new JLabel(gen_fecha);
		lblFecha.setBounds(10, 111, 265, 14);
		getContentPane().add(lblFecha);
		
		lblPartidosJugados = new JLabel(gen_partidos);
		lblPartidosJugados.setBounds(10, 136, 265, 14);
		getContentPane().add(lblPartidosJugados);
		
		lblPenalizaciones = new JLabel("Penalizaciones");
		lblPenalizaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblPenalizaciones.setBounds(10, 161, 265, 14);
		getContentPane().add(lblPenalizaciones);
		
		//Creemos el boton para volver
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(186, 338, 89, 23);
		getContentPane().add(btnVolver);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				terminate();
			}
		});
		
		//Creemos la tabla de Penalizaciones
		table = new JTable();
		table.setBounds(10, 327, 265, -131);
		getContentPane().add(table);
		
		
	}
	

}
