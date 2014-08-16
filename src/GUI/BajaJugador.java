package GUI;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class BajaJugador extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2710989898521557695L;
	private static MainJugador pantalla_jugador;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("rawtypes")
	public BajaJugador(MainJugador caller) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		        // Perhaps ask user if they want to save any unsaved files first.
		        pantalla_jugador.setEnabled(true); 
		    }
		});
		setResizable(false);
		setTitle("Baja de un Partido");
		setBounds(100, 100, 322, 184);
		getContentPane().setLayout(null);
		pantalla_jugador = caller;
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(129, 11, 167, 20);
		getContentPane().add(comboBox);
		
		JLabel lblPartidos = new JLabel("Partidos Inscriptos");
		lblPartidos.setBounds(10, 14, 116, 14);
		getContentPane().add(lblPartidos);

		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setForeground(new Color(0, 0, 0));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 42, 286, 69);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblTipoDeInscripcin = new JLabel("Reemplazo:");
		lblTipoDeInscripcin.setBounds(10, 11, 100, 14);
		panel.add(lblTipoDeInscripcin);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(142, 8, 134, 20);
		panel.add(comboBox_1);
		
		// TODO Cargar los partidos a los que el Jugador se encuentra inscripto

		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pantalla_jugador.setEnabled(true);
				dispose();
			}
		});
		botonCancelar.setBounds(207, 122, 89, 23);
		getContentPane().add(botonCancelar);
		
		JButton botonDesinscribirse = new JButton("Desinscribir");
		botonDesinscribirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// TODO Desinscribir al Jugador
				
				pantalla_jugador.setEnabled(true);
				dispose();
			}
		});
		botonDesinscribirse.setBounds(10, 122, 116, 23);
		getContentPane().add(botonDesinscribirse);

	}
}

