package GUI;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.List; 

import clasesDeNegocio.Jugador;
import clasesDeNegocio.Partido;


public class Jugador_Calificaciones extends VentanaTheGrid {
	private static final long serialVersionUID = 5690959307933499955L;

	//Componentes no cambiantes
	private JPanel panel;
	private JLabel lblPartidosJugados;
	private JLabel lblCalificacionesFaltantes;
	private JLabel lblJugador;
	private JLabel lblCalificacion;
	private JLabel lblCritica;
	
	//Componentes modificables
	private JComboBox<String> partidos_jugados;
	private JComboBox<String> jugadores_sin_calificar;
	private JComboBox<String> calificacion;

	private JButton btnGuardar;
	private JButton btnVolver;
	
	private JTextField campoCritica;

	//Atributos
	private Partido partido_seleccionado = null;
	private Jugador jugador_sin_calificacion = null;

	//Constructor
	public Jugador_Calificaciones(GlobalParameters caller) {
		global = caller; //Seteamos el atributo global
		
		//Definimos la Jframe Principal

		setTitle("Calificaciones");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		        global.pantalla_anterior.setVisible(true); 
		    }
		});
		setBounds(100, 100, 338, 245);
		getContentPane().setLayout(null);
		panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 39, 306, 126);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		//Creemos los Combos

		partidos_jugados = new JComboBox<String>();
		jugadores_sin_calificar = new JComboBox<String>();
		calificacion = new JComboBox<String>();

		partidos_jugados.setBounds(155, 8, 150, 20);
		jugadores_sin_calificar.setBounds(147, 33, 150, 20);
		calificacion.setBounds(147, 61, 55, 20);

		getContentPane().add(partidos_jugados);
		panel.add(jugadores_sin_calificar);
		panel.add(calificacion);
		//inicializamos los valores de las combos
		for(int i=1; i<11;i++)
			calificacion.addItem(Integer.toString(i));
		for(int i=0;i<global.jugador_seleccionado.getInscripciones().size();i++){
			if(global.jugador_seleccionado.getInscripciones().get(i).getPartido_inscripto().estaConfirmado())
				partidos_jugados.addItem(global.jugador_seleccionado.getInscripciones().get(i).getPartido_inscripto().getPartido_nombre());
		}
		if(partidos_jugados.getSelectedIndex() > -1){
			partido_seleccionado = global.getPartidoById((String) partidos_jugados.getSelectedItem());
			mostrarJugadoresPendientes(partido_seleccionado);
		}
		
		//Creemos los campos de texto
		
		campoCritica = new JTextField();
		campoCritica.setAutoscrolls(true);
		campoCritica.setBounds(147, 92, 150, 23);
		panel.add(campoCritica);
		campoCritica.setColumns(10);
		
		//Creemos las Labels

		lblPartidosJugados = new JLabel("Partidos Jugados");
		lblJugador = new JLabel("Jugador:");
		lblCalificacionesFaltantes = new JLabel("Calificaciones Faltantes:");
		lblCalificacion = new JLabel("Calificacion");
		lblCritica = new JLabel("Critica");

		lblPartidosJugados.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblPartidosJugados.setBounds(10, 11, 135, 14);
		lblJugador.setBounds(10, 36, 127, 14);
		lblCalificacionesFaltantes.setBounds(10, 11, 287, 14);
		lblCalificacion.setBounds(10, 64, 127, 14);
		lblCritica.setBounds(10, 92, 55, 14);

		getContentPane().add(lblPartidosJugados);
		panel.add(lblJugador);
		panel.add(lblCalificacionesFaltantes);		
		panel.add(lblCalificacion);
		panel.add(lblCritica);
		
		//Creemos los botones

		btnGuardar = new JButton("Guardar");
		btnVolver = new JButton("Volver");

		btnGuardar.setBounds(10, 176, 89, 23);
		btnVolver.setBounds(219, 176, 97, 23);

		getContentPane().add(btnGuardar);
		getContentPane().add(btnVolver);
		
		//Comportamiento de las combo
		
		partidos_jugados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				partido_seleccionado = global.getPartidoById((String) partidos_jugados.getSelectedItem());
				mostrarJugadoresPendientes(partido_seleccionado);
			}
		});		
		
		jugadores_sin_calificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(jugadores_sin_calificar.getSelectedIndex() < 0){
					jugador_sin_calificacion = null;
					return;
				}
				jugador_sin_calificacion = global.getJugadorByUsuario((String) jugadores_sin_calificar.getSelectedItem());
			}
		});		
		
		//Comportamiento de los botones
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				terminate();
			}
		});
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!estaTodoSeleccionado()){
					JOptionPane.showMessageDialog(null, "Error al guardar la Critica, campos seleccionados invalidos", "Guardar Critica", JOptionPane.ERROR_MESSAGE);
					return;
				}
				int response = JOptionPane.showConfirmDialog(null, "Una vez confirmada la Critica, no será modificable. Continuar?", "Guardar Critica", JOptionPane.WARNING_MESSAGE);
				if(response == JOptionPane.OK_OPTION) {
					global.jugador_seleccionado.calificar(jugador_sin_calificacion, Integer.parseInt((String) calificacion.getSelectedItem()), campoCritica.getText(), partido_seleccionado);
					mostrarJugadoresPendientes(partido_seleccionado);
				}
			}
		});
		
		
	}
	private void mostrarJugadoresPendientes(Partido partido){
		jugadores_sin_calificar.removeAllItems();
		List<Jugador> noCalificados = global.jugador_seleccionado.getNoCalificados(partido_seleccionado);
		for(int i=0;i<noCalificados.size();i++)
			jugadores_sin_calificar.addItem(noCalificados.get(i).getUsuario());
		jugador_sin_calificacion = global.getJugadorByUsuario((String) jugadores_sin_calificar.getSelectedItem());
	}
	private boolean estaTodoSeleccionado(){
		if(partidos_jugados.getSelectedIndex() > -1)
			if(jugadores_sin_calificar.getSelectedIndex() > -1)
				return true;
		return false;
	}
}
