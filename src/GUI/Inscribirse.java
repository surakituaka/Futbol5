package GUI;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class Inscribirse extends JDialog {

	private static final long serialVersionUID = 4024067139818249946L;
	private static MainJugador pantalla_jugador;
	private static JRadioButton botonStandar;
	private static JRadioButton botonCondicional;
	private static JRadioButton botonSolidaria;
	private JTextField campoCondicion;
	private static JCheckBox checkRecomendarAmigo;
	private static JButton btnInscribirse;
	private static Inscribirse yo;
	
	public Inscribirse(MainJugador caller) {
		yo = this;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		        // Perhaps ask user if they want to save any unsaved files first.
		        pantalla_jugador.setEnabled(true); 
		    }
		});
		setResizable(false);
		
		setTitle("Inscribirse a un Partido");
		setBounds(100, 100, 394, 279);
		getContentPane().setLayout(null);
		pantalla_jugador = caller;
		
		@SuppressWarnings("rawtypes")
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(163, 11, 215, 20);
		getContentPane().add(comboBox);
		
		JLabel lblPartidos = new JLabel("Partidos Disponibles");
		lblPartidos.setBounds(10, 14, 143, 14);
		getContentPane().add(lblPartidos);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pantalla_jugador.setEnabled(true);
				dispose();
			}
		});
		btnCancelar.setBounds(289, 219, 89, 23);
		getContentPane().add(btnCancelar);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setForeground(new Color(0, 0, 0));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 63, 368, 119);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Puestos Disponibles:");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(10, 11, 149, 14);
		panel.add(lblNewLabel);
		
		JLabel lblTipoDeInscripcin = new JLabel("Tipo de Inscripci\u00F3n:");
		lblTipoDeInscripcin.setBounds(10, 36, 100, 14);
		panel.add(lblTipoDeInscripcin);
		
		botonStandar = new JRadioButton("Standar");
		botonStandar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				botonCondicional.setSelected(false);
				botonSolidaria.setSelected(false);
			}
		});
		botonStandar.setBackground(Color.LIGHT_GRAY);
		botonStandar.setBounds(116, 32, 109, 23);
		panel.add(botonStandar);
		
		botonCondicional = new JRadioButton("Condicional");
		botonCondicional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					botonStandar.setSelected(false);
					botonSolidaria.setSelected(false);
			}
		});
		botonCondicional.setBackground(Color.LIGHT_GRAY);
		botonCondicional.setBounds(116, 59, 130, 23);
		panel.add(botonCondicional);
		
		botonSolidaria = new JRadioButton("Solidaria");
		botonSolidaria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				botonStandar.setSelected(false);
				botonCondicional.setSelected(false);
			}
		});
		botonSolidaria.setBackground(Color.LIGHT_GRAY);
		botonSolidaria.setBounds(116, 85, 109, 23);
		panel.add(botonSolidaria);
		
		campoCondicion = new JTextField();
		campoCondicion.setBounds(252, 59, 106, 20);
		panel.add(campoCondicion);
		campoCondicion.setColumns(10);
		
		JLabel lblCondicion = new JLabel("Condicion:");
		lblCondicion.setBounds(252, 36, 66, 14);
		panel.add(lblCondicion);
		
		btnInscribirse = new JButton("Recomendar Amigo e Inscribirme");
		btnInscribirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(inscripcionEsValida()){
					
					//TODO: Inscribir a partido
					if(checkRecomendarAmigo.isSelected()) {
						RecomendacionPopup pantalla_recomendacion = new RecomendacionPopup(yo);
						pantalla_recomendacion.setVisible(true);
						yo.setEnabled(false);
					}
					else {
						pantalla_jugador.setEnabled(true);
						dispose();
					}					
				}
			}
		});
		btnInscribirse.setBounds(10, 219, 269, 23);
		getContentPane().add(btnInscribirse);
		
		checkRecomendarAmigo = new JCheckBox("Recomendar Amigo al Inscribirme");
		checkRecomendarAmigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkRecomendarAmigo.isSelected()) {
					btnInscribirse.setText("Recomendar Amigo e Inscribirse");
				}
				else
					btnInscribirse.setText("Inscribirse");

				
			}
		});
		checkRecomendarAmigo.setSelected(true);
		checkRecomendarAmigo.setBounds(10, 189, 280, 23);
		getContentPane().add(checkRecomendarAmigo);

	}
	
	private boolean inscripcionEsValida() {
	
		return true;
	}
	
	public void terminate() {
		dispose();
	}
}
