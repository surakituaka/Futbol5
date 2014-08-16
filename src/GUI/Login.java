package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7554719727131540049L;
	private JPanel contentPane;
	private JTextField campoUsuario;
	private JPasswordField campoContrasenia;
	private JButton btnLogin;
	private JButton btnCancelar;
	
	private static MainJugador main_jugador;
	private static MainAdmin main_admin;
	private static Login login_interface;
	private static String admin_user = "admin";
	private static String admin_pass = "admin";


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login_interface = new Login();
					login_interface.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	//Constructor
	public Login() {
		setResizable(false);
		setTitle("The Grid");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 218, 133);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 11, 46, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(10, 36, 66, 14);
		contentPane.add(lblContrasea);
		
		campoUsuario = new JTextField();
		campoUsuario.setBounds(111, 8, 86, 20);
		contentPane.add(campoUsuario);
		campoUsuario.setColumns(10);
		
		campoContrasenia = new JPasswordField();
		campoContrasenia.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER){
					btnLogin.doClick();
				}
			}
			public void keyPressed(KeyEvent e) {
				//Do Nothing
			}
			public void keyReleased(KeyEvent e) {
				//Do Nothing
			}
		});
		campoContrasenia.setBounds(111, 33, 86, 20);
		contentPane.add(campoContrasenia);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(usarIsAdmin()) {
					main_admin = new MainAdmin(login_interface);
					main_admin.setVisible(true);					
				}
				else {
					
					// TODO Chequear si es un usuario en la DB
					
					main_jugador = new MainJugador(login_interface);
					main_jugador.setVisible(true);
				}
				campoUsuario.setText(null);
				campoContrasenia.setText(null);
				login_interface.setVisible(false);
			}
		});

		btnLogin.setBounds(10, 61, 89, 23);
		contentPane.add(btnLogin);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		btnCancelar.setBounds(108, 61, 89, 23);
		contentPane.add(btnCancelar);
	}
	
	private boolean usarIsAdmin() {
		
		// TODO Definir despues cuales son los admins
		
		String usuario = campoUsuario.getText();
		String password = new String(campoContrasenia.getPassword());
		return (usuario.equals(admin_user) && password.equals(admin_pass));
	}
}


