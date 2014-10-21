package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import clasesDeNegocio.*;
import clasesManejadoras.*;


public class Principal extends VentanaTheGrid {
	private static final long serialVersionUID = 7554719727131540049L;
	
	//Componentes no alterables
	private JPanel contentPane;
	
	//Componentes Modificables
	private JButton btnAcceder;
	private JComboBox<String> comboUsuarios;
	private JPasswordField campoPassword;
	private JLabel lblRegistrarme;
	private JLabel lblOlvidePassword;
	
	//Atributos
	private Jugador jugador_seleccionado = null;
	private static Principal principal;

	//Lanzamos la aplicacion
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ConexionDB();
					CargadoDeDatos.cargarAutomaticaDB();
					global_init = new Global();					
					principal = new Principal();
					principal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	//Constructor
	public Principal() {
		//Creemos la JFrame principal
		setResizable(false);
		setTitle("The Grid");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 213, 182);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//Creemos la comboBox
		
		comboUsuarios = new JComboBox<String>();
		comboUsuarios.setBounds(10, 12, 187, 20);
		contentPane.add(comboUsuarios);
		for(int i=0;i<global_init.jugadores.size();i++){
			comboUsuarios.addItem(global_init.jugadores.get(i).getUsuario());
		}
		comboUsuarios.addItem(global_init.administrador.getUsuario());

		//Creemos el campo de Password
		
		campoPassword = new JPasswordField();
		campoPassword.setBounds(10, 43, 187, 20);
		contentPane.add(campoPassword);
		
		//Creemos el boton de acceder
		
		btnAcceder = new JButton("Acceder");
		btnAcceder.setBounds(10, 74, 187, 23);
		contentPane.add(btnAcceder);
		
		//Creemos la label de Registro
		
		lblRegistrarme = new JLabel("Registrarme");
		lblOlvidePassword = new JLabel("Olvide mi contrase\u00F1a");
		lblRegistrarme.setForeground(Color.BLUE);
		lblOlvidePassword.setForeground(Color.BLUE);
		lblRegistrarme.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblOlvidePassword.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblRegistrarme.setBounds(10, 108, 76, 14);
		lblOlvidePassword.setBounds(10, 129, 110, 14);
		contentPane.add(lblRegistrarme);
		contentPane.add(lblOlvidePassword);
		
		//Comportamiento del comboBox
		
		if(comboUsuarios.getSelectedIndex() > -1)
			jugador_seleccionado = global_init.getJugadorByUsuario((String) comboUsuarios.getSelectedItem());
		comboUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombreUsuario = (String) comboUsuarios.getSelectedItem();	
				jugador_seleccionado = global_init.getJugadorByUsuario(nombreUsuario);	
			}
		});
		
		//Comportamiento de la Label y campo Password
		
		campoPassword.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER){
					btnAcceder.doClick();
				}
			} //Como esta por interfaz hay que poner todas
			public void keyPressed(KeyEvent e) {} //No hacemos nada
			public void keyReleased(KeyEvent e) {} //No hacemos nada
		});
		
		lblRegistrarme.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Registrarse pantalla_registro = new Registrarse(new GlobalParameters(global_init, null, principal));
				habilitarPantalla(pantalla_registro);
			}
		});
		lblOlvidePassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(comboUsuarios.getSelectedIndex() > -1){
					Jugador jugador_perdido =  global_init.getJugadorByUsuario((String) comboUsuarios.getSelectedItem());
					global_init.administrador.getMensajero().enviar_mensaje(global_init.administrador.getEmail(),jugador_perdido.getEmail() , "Buen dia " + jugador_perdido.getNombre()+"\n" + "Su usuario es "+jugador_perdido.getUsuario()+" y su contrase\u00F1a es: "+jugador_perdido.getPassword());
					JOptionPane.showMessageDialog(null, "Se ha enviado un email con sus datos. Por favor revise su correo.", "Olvide mi Contrase\u00F1a", JOptionPane.PLAIN_MESSAGE);					
				}
			}
		});
		
		//Comportamiento del boton
			
		btnAcceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				if(comboUsuarios.getSelectedIndex() < 0){
					JOptionPane.showMessageDialog(null, "Seleccione un Usuario.", "Error al Acceder", JOptionPane.ERROR_MESSAGE);					
					return;
				}
				String password = new String(campoPassword.getPassword());
				//TODO: Implementar validacion de password
				if(global_init.administrador.getUsuario().equals((String) comboUsuarios.getSelectedItem())){
					if(!chequearPassword(global_init.administrador,password)){
						JOptionPane.showMessageDialog(null, "Password Incorrecto, intente nuevamente.", "Error al Acceder", JOptionPane.ERROR_MESSAGE);					
						return;
					}						
					MainAdmin pantalla_administrador = new MainAdmin(new GlobalParameters(global_init, null, principal));
					habilitarPantalla(pantalla_administrador);
				}
				else {
					if(!chequearPassword(jugador_seleccionado,password)){
						JOptionPane.showMessageDialog(null, "Password Incorrecto, intente nuevamente.", "Error al Acceder", JOptionPane.ERROR_MESSAGE);					
						return;
					}	
					MainJugador main_jugador = new MainJugador(new GlobalParameters(global_init, jugador_seleccionado, principal));
					habilitarPantalla(main_jugador);			
				}
			}
		});
	}
	//TODO: Hacer que cada vez que salga de admin refresque la combo
	public boolean chequearPassword(Usuario usuario, String password) { //Recibe un usuario para que sirva para admin y jugador
		campoPassword.setText("");
		if(usuario.getPassword().matches(password))
			return true;
		return false;
	}
	public void refreshCombo() {
		comboUsuarios.removeAllItems();
		for(int i=0;i < global_init.jugadores.size();i++)
			comboUsuarios.addItem(global_init.jugadores.get(i).getUsuario());
		comboUsuarios.addItem(global_init.administrador.getUsuario());
	}	
}


