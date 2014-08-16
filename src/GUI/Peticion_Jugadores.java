package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class Peticion_Jugadores extends JDialog {

	private static final long serialVersionUID = 5894712599675922771L;
	private final JPanel contentPanel = new JPanel();
	private static MainAdmin pantalla_admin;

	/**
	 * Create the dialog.
	 */
	public Peticion_Jugadores(MainAdmin caller) {
		setResizable(false);
		setTitle("Gestionar Jugadores");
		pantalla_admin = caller;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		        // Perhaps ask user if they want to save any unsaved files first.
		    	pantalla_admin.setEnabled(true); 
		    }
		});
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblSolicitudesDeNuevo = new JLabel("Solicitudes de Nuevo Jugador");
		lblSolicitudesDeNuevo.setBounds(10, 11, 176, 14);
		getContentPane().add(lblSolicitudesDeNuevo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(235, 8, 199, 20);
		getContentPane().add(comboBox);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

	}
}
