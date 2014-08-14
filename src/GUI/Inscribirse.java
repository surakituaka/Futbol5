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

public class Inscribirse extends JDialog {

	private static final long serialVersionUID = 4024067139818249946L;
	private static MainJugador pantalla_principal;
	private static JRadioButton botonStandar;
	private static JRadioButton botonCondicional;
	private static JRadioButton botonSolidaria;
	
	public Inscribirse(MainJugador caller) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		setTitle("Inscribirse a un Partido");
		setBounds(100, 100, 356, 265);
		getContentPane().setLayout(null);
		pantalla_principal = caller;
		
		@SuppressWarnings("rawtypes")
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(163, 11, 167, 20);
		getContentPane().add(comboBox);
		
		JLabel lblPartidos = new JLabel("Partidos Disponibles");
		lblPartidos.setBounds(10, 14, 143, 14);
		getContentPane().add(lblPartidos);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(241, 193, 89, 23);
		getContentPane().add(btnCancelar);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setForeground(new Color(0, 0, 0));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 63, 320, 119);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Puestos Disponibles: X");
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
		botonCondicional.setBounds(116, 59, 109, 23);
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
		
		JButton btnInscribirse = new JButton("Inscribirse");
		btnInscribirse.setBounds(10, 193, 89, 23);
		getContentPane().add(btnInscribirse);

	}
}
