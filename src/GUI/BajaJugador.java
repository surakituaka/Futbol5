package GUI;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;

import javax.swing.SwingConstants;

import club.Jugador;
import club.Partido;

public class BajaJugador extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2710989898521557695L;
	private static MainJugador pantalla_jugador;
	private static JComboBox<String> comboReemplazo;
	/**
	 * Create the frame.
	 */

	public BajaJugador(final GlobalParameters global) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		        // Perhaps ask user if they want to save any unsaved files first.
		        pantalla_jugador.setEnabled(true); 
		    }
		});
		setResizable(false);
		setTitle("Baja de un Partido");
		setBounds(100, 100, 257, 273);
		getContentPane().setLayout(null);
		pantalla_jugador = (MainJugador) global.pantalla_anterior;
		JLabel lblDatosDelPartido = new JLabel("Datos del Partido:");
		lblDatosDelPartido.setBounds(10, 11, 168, 14);
		getContentPane().add(lblDatosDelPartido);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 36, 233, 83);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		Partido partido_inscripto = global.jugador_seleccionado.getInscripto();
		
		JLabel lblId = new JLabel(("ID: ").concat(partido_inscripto.getId()));
		lblId.setBounds(10, 11, 210, 14);
		panel.add(lblId);
		
		JLabel lblLugar = new JLabel(("Lugar: ").concat(partido_inscripto.getLugar()));
		lblLugar.setBounds(10, 36, 210, 14);
		panel.add(lblLugar);
		
		SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy");
		JLabel lblFecha = new JLabel(("Fecha: ").concat(dateformatter.format(partido_inscripto.getFecha())));
		lblFecha.setBounds(10, 61, 210, 14);
		panel.add(lblFecha);
		
		JLabel lblestSeguroQue = new JLabel("\u00BFEst\u00E1 seguro que desea Desinscribirse?");
		lblestSeguroQue.setHorizontalAlignment(SwingConstants.CENTER);
		lblestSeguroQue.setBounds(10, 175, 233, 14);
		getContentPane().add(lblestSeguroQue);
		
		comboReemplazo = new JComboBox<String>();
		comboReemplazo.setBounds(99, 130, 144, 20);
		for (int i = 0;i<global.jugador_seleccionado.getAmigos().size();i++){
			comboReemplazo.addItem(global.jugador_seleccionado.getAmigos().get(i).getNombre());
		}
		getContentPane().add(comboReemplazo);
		
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pantalla_jugador.setEnabled(true);
				dispose();
			}
		});
		botonCancelar.setBounds(154, 200, 89, 23);
		getContentPane().add(botonCancelar);
		
		JButton botonDesinscribirse = new JButton("Desinscribir");
		botonDesinscribirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Partido partido_inscripto = global.jugador_seleccionado.getInscripto();
				
				if(comboReemplazo.getSelectedItem() != null) {
					Jugador amigo_reemplazo = null;
					for (int i = 0;i<global.jugador_seleccionado.getAmigos().size();i++){
						if(global.jugador_seleccionado.getAmigos().get(i).getNombre().equals(comboReemplazo.getSelectedItem()))
							amigo_reemplazo = global.jugador_seleccionado.getAmigos().get(i);
					}
					global.jugador_seleccionado.bajarse_de(partido_inscripto, amigo_reemplazo);
					salirDeBaja(global.jugador_seleccionado);
				}
				else {
					int response = JOptionPane.showConfirmDialog(null, "Será Penalizado si no Selecciona un reemplazo", "Baja de un Partido", JOptionPane.WARNING_MESSAGE);
					if(response == JOptionPane.OK_OPTION) {
						global.jugador_seleccionado.bajarse_de(partido_inscripto);	
						salirDeBaja(global.jugador_seleccionado);
					}
				}

			}
		});
		botonDesinscribirse.setBounds(10, 200, 116, 23);
		getContentPane().add(botonDesinscribirse);
		JLabel lblReemplazo = new JLabel("Reemplazo");
		lblReemplazo.setBounds(10, 130, 79, 14);
		getContentPane().add(lblReemplazo);
	}
	
	private void salirDeBaja(Jugador jugador_seleccionado) {
		pantalla_jugador.setEnabled(true);
		pantalla_jugador.NoseInscribio();
		jugador_seleccionado.setInscripto(null);
		dispose();
	}
}

