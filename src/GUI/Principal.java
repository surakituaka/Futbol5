package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import club.*;




public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7554719727131540049L;
	private JPanel contentPane;
	private JButton btnJugador;
	private JButton btnAccesoAdmin;
	
	private static MainJugador main_jugador;
	private static MainAdmin main_admin;
	private static Principal principal_interface;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					principal_interface = new Principal();
					principal_interface.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	//Constructor
	public Principal() {
		setResizable(false);
		setTitle("The Grid");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 341, 135);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnJugador = new JButton("Acceder");
		btnJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// TODO: A utilizar los hardcodeados, por ahora lo pongo asi
				Jugador pepito = new Jugador();
				
				main_jugador = new MainJugador(principal_interface, pepito);
				main_jugador.setVisible(true);
				//login_interface.setEnabled(false);
			}
		});

		btnJugador.setBounds(236, 11, 89, 23);
		contentPane.add(btnJugador);
		
		btnAccesoAdmin = new JButton("Acceso como Admin");
		btnAccesoAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main_admin = new MainAdmin(principal_interface);
				main_admin.setVisible(true);	
				//login_interface.setEnabled(false);
			}
		});

		btnAccesoAdmin.setBounds(84, 73, 187, 23);
		contentPane.add(btnAccesoAdmin);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 12, 187, 20);
		contentPane.add(comboBox);
	}

}


