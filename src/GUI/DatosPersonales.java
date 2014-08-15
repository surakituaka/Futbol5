package GUI;

import java.awt.Color;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class DatosPersonales extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7377006624212654507L;
	private JTextField campo_nombre;
	private JTextField campo_fecha_nacimiento;
	private JTextField campo_amigos;
	private JTextField campo_usuario;
	private JTextField campo_email;
	private JPasswordField campo_password;
	private JPasswordField campo_confirmarPass;
	private static DatosPersonales yo;
	private static MainJugador pantalla_jugador;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public DatosPersonales(MainJugador player_class) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		        // Perhaps ask user if they want to save any unsaved files first.
		        pantalla_jugador.setEnabled(true); 
		    }
		});
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Datos Personales");
		setBounds(100, 100, 331, 366);
		getContentPane().setLayout(null);		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 25, 46, 14);
		getContentPane().add(lblNombre);
		pantalla_jugador = player_class;
		
		JLabel lblNewLabel = new JLabel("Fecha de Nacimiento");
		lblNewLabel.setBounds(10, 50, 126, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblAmigos = new JLabel("Amigos");
		lblAmigos.setBounds(10, 103, 126, 14);
		getContentPane().add(lblAmigos);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(10, 75, 46, 14);
		getContentPane().add(lblEmail);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 171, 292, 91);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setIcon(null);
		lblUsuario.setBounds(10, 11, 93, 14);
		panel.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(10, 36, 126, 14);
		panel.add(lblContrasea);
		
		JLabel lblRepitaContrasea = new JLabel("Repita Contrase\u00F1a");
		lblRepitaContrasea.setBounds(10, 61, 126, 14);
		panel.add(lblRepitaContrasea);
		
		campo_usuario = new JTextField();
		campo_usuario.setEditable(false);
		campo_usuario.setColumns(10);
		campo_usuario.setBounds(143, 8, 139, 20);
		panel.add(campo_usuario);
		
		campo_password = new JPasswordField();
		campo_password.setBounds(143, 33, 139, 20);
		panel.add(campo_password);
		
		campo_confirmarPass = new JPasswordField();
		campo_confirmarPass.setBounds(143, 58, 139, 20);
		panel.add(campo_confirmarPass);
		
		campo_nombre = new JTextField();
		campo_nombre.setBounds(163, 22, 139, 20);
		getContentPane().add(campo_nombre);
		campo_nombre.setColumns(10);
		
		campo_fecha_nacimiento = new JTextField();
		campo_fecha_nacimiento.setColumns(10);
		campo_fecha_nacimiento.setBounds(163, 47, 139, 20);
		getContentPane().add(campo_fecha_nacimiento);
		
		campo_amigos = new JTextField();
		campo_amigos.setColumns(10);
		campo_amigos.setBounds(163, 100, 139, 20);
		getContentPane().add(campo_amigos);
		
		campo_email = new JTextField();
		campo_email.setColumns(10);
		campo_email.setBounds(163, 72, 139, 20);
		getContentPane().add(campo_email);
		yo = this;
		
		// TODO Levantar los datos de la DB
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Guardar los Datos de Usuario y chequear consistencia de Password
				pantalla_jugador.setEnabled(true);
				dispose();
			}
		});
		btnGuardar.setBounds(10, 294, 89, 23);
		getContentPane().add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pantalla_jugador.setEnabled(true);
				dispose();
			}
		});
		btnCancelar.setBounds(216, 294, 89, 23);
		getContentPane().add(btnCancelar);
		
		JButton btnPenalizaciones = new JButton("Penalizaciones");
		btnPenalizaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PenalizacionesPopup pantalla_penalizaciones = new PenalizacionesPopup(yo);
				yo.setEnabled(false);
				pantalla_penalizaciones.setVisible(true);
			}
		});
		btnPenalizaciones.setBounds(163, 131, 139, 20);
		getContentPane().add(btnPenalizaciones);

	}
}
