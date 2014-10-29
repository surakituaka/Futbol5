package GUI;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;
import javax.swing.JCheckBox;

import clasesDeNegocio.IModalidad;
import clasesDeNegocio.Inscripcion;
import clasesDeNegocio.Jugador;
import clasesDeNegocio.Partido;
import clasesManejadoras.ConexionDB;

public class Jugador_Inscribirse extends VentanaTheGrid {
	private static final long serialVersionUID = 4024067139818249946L;
	
	//Componentes no cambiantes
	private JLabel l_cantJugadores;
	private JLabel l_partidos;
	private JLabel lblTipoDeInscripcin; 
	private JLabel lblCondicion;
	private JPanel panel;
	private String gen_cantJugadores = "Cantidad de Jugadores: ";
	private String gen_recomendacion = "Recomendar Amigo al Inscribirme";
	private String gen_inscribirme = "Inscribirse";

	//Componentes modificables
	private JRadioButton botonStandar;
	private JRadioButton botonCondicional;
	private JRadioButton botonSolidaria;
	private JTextField campoCondicion;
	private JButton btnInscribirse;
	private JButton btnCancelar;
	private JCheckBox recomendarAmigo;
	private JComboBox<String> partidos_disponibles;
	
	//Atributos
	private Partido partido_actual;
	private String label_inscripcion;
	
	//Constructor
	public Jugador_Inscribirse(final GlobalParameters caller) {
		//Seteamos el atributo global
		global = caller;
		
		//Definimos el JFrame principal 
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		        global.pantalla_anterior.setVisible(true); 
		    }
		});
		setResizable(false);
		setTitle("Inscribirse a un Partido");
		setBounds(100, 100, 394, 279);
		getContentPane().setLayout(null);
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setForeground(new Color(0, 0, 0));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 63, 368, 119);
		getContentPane().add(panel);
		panel.setLayout(null);

		//Creemos los botones
		
		btnCancelar = new JButton("Cancelar");
		btnInscribirse = new JButton(gen_recomendacion);
		btnInscribirse.setBounds(10, 219, 269, 23);
		btnCancelar.setBounds(289, 219, 89, 23);
		getContentPane().add(btnInscribirse);
		getContentPane().add(btnCancelar);

		//Creemos el Combo, los Radio Buttons y la checkbox
				
		partidos_disponibles = new JComboBox<String>();
		partidos_disponibles.setBounds(163, 11, 215, 20);
		getContentPane().add(partidos_disponibles);		
		for(int i=0;i < global.partidos.size();i++){ //Bucle de carga de la combo
			if(!(global.partidos.get(i).estaConfirmado() || global.partidos.get(i).estaConfirmado()))
				partidos_disponibles.addItem(global.partidos.get(i).getPartido_nombre());
		}
		
		botonStandar = new JRadioButton("Standar");
		botonCondicional = new JRadioButton("Condicional");
		botonSolidaria = new JRadioButton("Solidaria");
		
		botonStandar.setBackground(Color.LIGHT_GRAY);
		botonCondicional.setBackground(Color.LIGHT_GRAY);
		botonSolidaria.setBackground(Color.LIGHT_GRAY);
		
		botonCondicional.setBounds(116, 59, 130, 23);
		botonStandar.setBounds(116, 32, 109, 23);
		botonSolidaria.setBounds(116, 85, 109, 23);
		
		panel.add(botonStandar);
		panel.add(botonCondicional);
		panel.add(botonSolidaria);
		
		recomendarAmigo = new JCheckBox("Recomendar Amigo al Inscribirme");
		recomendarAmigo.setSelected(true);
		recomendarAmigo.setBounds(10, 189, 280, 23);
		getContentPane().add(recomendarAmigo);
		
		//Creemos las labels
		
		lblTipoDeInscripcin = new JLabel("Tipo de Inscripci\u00F3n:");
		l_partidos = new JLabel("Partidos Disponibles");
		lblCondicion = new JLabel("Condicion:");
		l_cantJugadores = new JLabel();
		campoCondicion = new JTextField();


		lblTipoDeInscripcin.setBounds(10, 36, 100, 14);
		l_partidos.setBounds(10, 14, 143, 14);
		lblCondicion.setBounds(252, 36, 66, 14);
		l_cantJugadores.setBounds(10, 11, 348, 14);
		campoCondicion.setBounds(252, 59, 106, 20);

		panel.add(lblTipoDeInscripcin);
		getContentPane().add(l_partidos);
		panel.add(lblCondicion);
		panel.add(l_cantJugadores);	
		panel.add(campoCondicion);	
		campoCondicion.setColumns(10);
		
		l_cantJugadores.setForeground(Color.RED);
		setearLabels();
		//Comportamiento de la Combo y Checkbox

		partidos_disponibles.addActionListener(new ActionListener() { //Comportamiento de la combo
			public void actionPerformed(ActionEvent arg0) {
				setearLabels();
				partido_actual =global.getPartidoById((String) partidos_disponibles.getSelectedItem());
			}
		});
		
		recomendarAmigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(recomendarAmigo.isSelected()) 
					btnInscribirse.setText(gen_recomendacion);
				else
					btnInscribirse.setText(gen_inscribirme);
			}
		});
		
		//Comportamiento RadioButtons
		
		botonStandar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				label_inscripcion = botonSeleccionado(botonStandar);	
			}
		});

		botonCondicional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				label_inscripcion = botonSeleccionado(botonCondicional);	
			}
		});

		botonSolidaria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				label_inscripcion = botonSeleccionado(botonSolidaria);	
			}
		});
		
		//Comportamiento de los botones de Inscripcion y Cancelar

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				terminate();
			}
		});
	
		btnInscribirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(inscripcionEsValida(global.jugador_seleccionado)){
					if(global.jugador_seleccionado.estoyInscripto(partido_actual)){
						JOptionPane.showMessageDialog(null, "Usted se encuentra ya se encuentra inscripto a este Partido", "Error al Inscribirse al Partido", JOptionPane.ERROR_MESSAGE);				
						return;
					}
					IModalidad modalidad = global.jugador_seleccionado.getTipofromString(label_inscripcion, campoCondicion.getText());
					Inscripcion inscripcion = global.jugador_seleccionado.inscribirse_a(partido_actual,modalidad);	
					
					if(inscripcion != null){
						ConexionDB.guardar(inscripcion);
						//ConexionDB.guardar(global.jugador_seleccionado);
						//ConexionDB.guardar(partido_actual);						
						if(partido_actual.validar10())
							partido_actual.enviar_mensaje("partido "+partido_actual.getPartido_nombre(),global.administrador.getEmail(), "Hay 10 jugadores"); //Enviamos el email
						if(recomendarAmigo.isSelected()) {
							Jugador_RecomendacionPopup pantalla_recomendacion = new Jugador_RecomendacionPopup(new GlobalParameters(global, global.jugador_seleccionado, yo), partido_actual);
							pantalla_recomendacion.setVisible(true);							
							yo.setEnabled(false);
							
						}
						else 
							terminate();
					}
					else
						JOptionPane.showMessageDialog(null, "El Partido Seleccionado está lleno", "Error al Inscribirse al Partido", JOptionPane.ERROR_MESSAGE);				
				}
				else 
					JOptionPane.showMessageDialog(null, "Datos Ingresados Erroneos", "Error al Inscribirse al Partido", JOptionPane.ERROR_MESSAGE);				
			}
		});

	}
	
	private boolean inscripcionEsValida(Jugador jugador) {
		if(partidos_disponibles.getSelectedItem() != null) {
				if(botonStandar.isSelected() || botonSolidaria.isSelected())
					return true;
				if(botonCondicional.isSelected() && !(campoCondicion.getText().equals("")))
					return true;
		}
		return false;
	}
	
	private String botonSeleccionado(JRadioButton boton) {
		switch(boton.getText()){
			case "Standar":
				botonStandar.setSelected(true);
				botonCondicional.setSelected(false);
				botonSolidaria.setSelected(false);
				break;
			case "Condicional":
				botonStandar.setSelected(false);
				botonCondicional.setSelected(true);
				botonSolidaria.setSelected(false);
				break;
			case "Solidaria":
				botonStandar.setSelected(false);
				botonCondicional.setSelected(false);
				botonSolidaria.setSelected(true);
				break;
		}
		return boton.getText();	
	}
	public void setearLabels(){
		if(partidos_disponibles.getSelectedItem() != null) { //Setear el valor de la label
			String nombre_partido = (String) partidos_disponibles.getSelectedItem();
			Partido partido_seleccionado = global.getPartidoById(nombre_partido);
			l_cantJugadores.setText(gen_cantJugadores.concat(Integer.toString(partido_seleccionado.getInscripciones().size())));
			partido_actual = global.partidos.get(0);
		}
	}
}
