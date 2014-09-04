package GUI;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import clasesDeNegocio.Jugador;

public class Registrarse extends VentanaTheGrid {
	private static final long serialVersionUID = 5623763011362421207L;

	//Atributos no cambiantes
	private JLabel lblUsuario;
	private JLabel lblPassword;
	private JLabel lblPassword_Confirmacion;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblEmail;
	private JLabel titulo;

	//Atributos modificables
	private JPasswordField password;
	private JPasswordField confirmacion_password;
	
	private JTextField campoUsuario;
	private JTextField campoNombre;
	private JTextField campoApellido;
	private JTextField campoEmail;
	
	private JButton btnRegistrarse;
	private JButton btnCancelar;
	
	//Constructor
	public Registrarse(GlobalParameters caller) {
		global = caller; //Inicializamos el atributo global
		
		//Creemos el JDialog principal
		
		setTitle("Registrarse");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		        global.pantalla_anterior.setVisible(true); 
		    }
		});
		setBounds(100, 100, 384, 299);
		getContentPane().setLayout(null);
		
		//Creemos los labels
		lblUsuario = new JLabel("Usuario:");
		lblPassword = new JLabel("Password:");
		lblPassword_Confirmacion = new JLabel("Confirmacion de Password:");
		lblNombre = new JLabel("Nombre:");
		lblApellido = new JLabel("Apellido:");
		lblEmail = new JLabel("Email:");
		titulo = new JLabel("Ingrese los siguientes datos");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblUsuario.setBounds(10, 42, 182, 20);
		lblPassword.setBounds(10, 166, 182, 20);
		lblPassword_Confirmacion.setBounds(10, 197, 182, 20);
		lblNombre.setBounds(10, 73, 182, 20);
		lblApellido.setBounds(10, 104, 182, 20);
		lblEmail.setBounds(10, 135, 182, 20);
		titulo.setBounds(10, 11, 312, 14);

		getContentPane().add(lblUsuario);
		getContentPane().add(lblPassword);
		getContentPane().add(lblPassword_Confirmacion);
		getContentPane().add(lblNombre);
		getContentPane().add(lblApellido);
		getContentPane().add(lblEmail);
		getContentPane().add(titulo);

		//Creemos los campos de Texto y Password
		password = new JPasswordField();
		confirmacion_password = new JPasswordField();
		
		campoUsuario = new JTextField();
		campoNombre = new JTextField();
		campoApellido = new JTextField();
		campoEmail = new JTextField();
		
		password.setBounds(202, 166, 156, 20);
		confirmacion_password.setBounds(202, 197, 156, 20);

		campoUsuario.setBounds(202, 42, 156, 20);
		campoNombre.setBounds(202, 73, 156, 20);
		campoApellido.setBounds(202, 104, 156, 20);
		campoEmail.setBounds(202, 135, 156, 20);

		getContentPane().add(password);
		getContentPane().add(confirmacion_password);
		
		getContentPane().add(campoUsuario);
		getContentPane().add(campoNombre);
		getContentPane().add(campoApellido);
		getContentPane().add(campoEmail);

		//Creemos los botones
		btnRegistrarse = new JButton("Registrarse");
		btnCancelar = new JButton("Cancelar");
		
		btnRegistrarse.setBounds(10, 228, 101, 23);
		btnCancelar.setBounds(271, 228, 87, 23);

		getContentPane().add(btnRegistrarse);
		getContentPane().add(btnCancelar);
		
		//Comportamiento de los Botones
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				terminate();
			}
		});
		
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(faltaAlgunCampo()){
					JOptionPane.showMessageDialog(null, "Debe completar todos los campos.", "Error al Registrarse", JOptionPane.ERROR_MESSAGE);					
					return;
				}
				if(ingresoUsuarioInvalido()){
					JOptionPane.showMessageDialog(null, "Usuario invalido, intente nuevamente.", "Error al Registrarse", JOptionPane.ERROR_MESSAGE);					
					return;
				}
				if(!coincidePassword()){
					JOptionPane.showMessageDialog(null, "Los Passwords no coinciden.", "Error al Registrarse", JOptionPane.ERROR_MESSAGE);	
					password.setText("");//Limpiamos los campos de password porque se equivoco
					confirmacion_password.setText("");
					return;
				}
				Jugador nuevo_jugador = new Jugador();
				nuevo_jugador.setUsuario(campoUsuario.getText());
				nuevo_jugador.setPassword(new String(password.getPassword()));
				nuevo_jugador.setNombre(campoNombre.getText());
				nuevo_jugador.setApellido(campoApellido.getText());
				nuevo_jugador.setEmail(campoEmail.getText());
				global.agregarJugador(nuevo_jugador); //Una vez creado lo agregamos a la lista
				((Principal) global.pantalla_anterior).refreshCombo(); //Refrescamos la combo de la pantalla principal para que aparesca el nuevo
				pasarAJugador(nuevo_jugador);
			}
		});
	}
	private boolean faltaAlgunCampo(){
		if(campoUsuario.getText().equals(""))
			return true;
		if(campoNombre.getText().equals(""))
			return true;
		if(campoApellido.getText().equals(""))
			return true;
		if(campoEmail.getText().equals(""))
			return true;
		if((new String(password.getPassword())).equals(""))
			return true;
		if((new String(confirmacion_password.getPassword())).equals(""))
			return true;
		return false;
	}
	private boolean ingresoUsuarioInvalido(){
		String usuario = campoUsuario.getText();
		if(usuario.matches("(?i:.*"+global.administrador.getUsuario()+".*)")) //Genera un matcheo buscando en algun lugar la palabra admin siendo case insensitive
			return true;
		for(int i=0;i<global.jugadores.size();i++) //Chequeamos que no exista otro jugador con el mismo usuario
			if(usuario.matches(global.jugadores.get(i).getUsuario()))
				return true;
		return false;
	}
	
	private boolean coincidePassword(){
		String pass_1 = new String(password.getPassword());
		String pass_2 = new String(confirmacion_password.getPassword());
		if(pass_1.equals(pass_2))
			return true;
		return false;
	}
	
	public void pasarAJugador(Jugador jugador){
		MainJugador main_jugador = new MainJugador(new GlobalParameters(global, jugador, global.pantalla_anterior));
		main_jugador.setVisible(true);
		this.setVisible(false);
	}
}
