package GUI;

import javax.swing.JDialog;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class ProponerAmigos extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8299159870982517446L;
	private static MainJugador pantalla_jugador;
	/**
	 * Create the frame.
	 */
	public ProponerAmigos(MainJugador caller) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		        // Perhaps ask user if they want to save any unsaved files first.
		        pantalla_jugador.setEnabled(true); 
		    }
		});
		setTitle("Proponer Amigos");
		setBounds(100, 100, 369, 300);
		getContentPane().setLayout(null);
		pantalla_jugador = caller;
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pantalla_jugador.setEnabled(true);
				dispose();
			}
		});
		btnCancelar.setBounds(253, 228, 89, 23);
		getContentPane().add(btnCancelar);
		
		JButton btnProponer = new JButton("Proponer");
		btnProponer.setBounds(10, 228, 89, 23);
		getContentPane().add(btnProponer);
		
		JLabel lblAmigosNoJugadores = new JLabel("Amigos No Jugadores");
		lblAmigosNoJugadores.setBounds(10, 11, 112, 14);
		getContentPane().add(lblAmigosNoJugadores);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(174, 8, 168, 20);
		getContentPane().add(comboBox);

	}
}
