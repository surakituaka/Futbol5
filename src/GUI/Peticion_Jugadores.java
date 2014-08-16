package GUI;

import java.awt.FlowLayout;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;

public class Peticion_Jugadores extends JDialog {

	private static final long serialVersionUID = 5894712599675922771L;
	private final JPanel contentPanel = new JPanel();
	private static MainAdmin pantalla_admin;
	private JTextField campoNuevoUsuario;
	private JTextField campoNuevaContrasenia;

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
		setBounds(100, 100, 261, 383);
		getContentPane().setLayout(null);
		
		JLabel lblSolicitudesDeNuevo = new JLabel("Solicitudes de Nuevo Jugador");
		lblSolicitudesDeNuevo.setBounds(10, 11, 176, 14);
		getContentPane().add(lblSolicitudesDeNuevo);
		
		JComboBox comboSolicitudes = new JComboBox();
		comboSolicitudes.setBounds(10, 30, 235, 20);
		getContentPane().add(comboSolicitudes);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 61, 235, 283);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(10, 249, 89, 23);
		panel.add(btnAceptar);
		
		JButton btnRechazar = new JButton("Rechazar");
		btnRechazar.setBounds(136, 249, 89, 23);
		panel.add(btnRechazar);
		
		JLabel lblPropuestoPor = new JLabel("Propuesto Por:");
		lblPropuestoPor.setBounds(10, 111, 215, 14);
		panel.add(lblPropuestoPor);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 11, 215, 14);
		panel.add(lblNombre);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento:");
		lblFechaDeNacimiento.setBounds(10, 36, 215, 14);
		panel.add(lblFechaDeNacimiento);
		
		JLabel lblAmigos = new JLabel("Amigos:");
		lblAmigos.setBounds(10, 61, 215, 14);
		panel.add(lblAmigos);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 86, 215, 14);
		panel.add(lblEmail);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 133, 215, 105);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		campoNuevaContrasenia = new JTextField();
		campoNuevaContrasenia.setBounds(106, 42, 86, 20);
		panel_1.add(campoNuevaContrasenia);
		campoNuevaContrasenia.setColumns(10);
		
		campoNuevoUsuario = new JTextField();
		campoNuevoUsuario.setBounds(106, 11, 86, 20);
		panel_1.add(campoNuevoUsuario);
		campoNuevoUsuario.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(10, 14, 86, 14);
		panel_1.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(10, 45, 86, 14);
		panel_1.add(lblContrasea);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

	}
}
