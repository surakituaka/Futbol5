package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Calificar extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -246190140763542583L;
	private final JPanel contentPanel = new JPanel();
	private static MainJugador pantalla_jugador;

	
	/**
	 * Create the dialog.
	 */
	public Calificar(MainJugador caller) {
		setResizable(false);
		setTitle("Calificar");
		pantalla_jugador = caller;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		        // Perhaps ask user if they want to save any unsaved files first.
		    	pantalla_jugador.setEnabled(true);
		    }
		});
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	}
}
