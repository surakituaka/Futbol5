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
	private JComboBox<String> comboJugadores;
	private static MainJugador main_jugador;
	private static MainAdmin main_admin;
	private static Principal principal_interface;
	private static Jugador jugador_seleccionado;
	private final Global global;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Global inicializacionGlobal = new Global();					
					principal_interface = new Principal(inicializacionGlobal);
					principal_interface.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	//Constructor
	public Principal(final Global inicializador) {
		setResizable(false);
		setTitle("The Grid");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 341, 135);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		global = inicializador;
		
		
		comboJugadores = new JComboBox<String>();
		comboJugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre_jugador = (String) comboJugadores.getSelectedItem();
				int i = 0;
				while(i < inicializador.jugadores.size()){
					if(inicializador.jugadores.get(i).getNombre().equals(nombre_jugador))
						jugador_seleccionado = inicializador.jugadores.get(i);
					i++;
				}
			}
		});
		comboJugadores.setBounds(10, 12, 187, 20);
		refreshComboJugadores();
		contentPane.add(comboJugadores);

		
		
		btnJugador = new JButton("Acceder");
		btnJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				main_jugador = new MainJugador(new GlobalParameters(inicializador, jugador_seleccionado, principal_interface));
				main_jugador.setVisible(true);
				//principal_interface.setEnabled(false);
			}
		});

		btnJugador.setBounds(236, 11, 89, 23);
		contentPane.add(btnJugador);
		
		btnAccesoAdmin = new JButton("Acceso como Admin");
		btnAccesoAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main_admin = new MainAdmin(new GlobalParameters(inicializador, null, principal_interface));
				main_admin.setVisible(true);	
				btnAccesoAdmin.setEnabled(false);
			}
		});

		btnAccesoAdmin.setBounds(84, 73, 187, 23);
		contentPane.add(btnAccesoAdmin);

	}
	
	public void habilitarAdmin() {
		btnAccesoAdmin.setEnabled(true);
	}
	public void refreshComboJugadores() {
		comboJugadores.removeAllItems();
		for(int i=0;i < global.jugadores.size();i++)
			comboJugadores.addItem(global.jugadores.get(i).getNombre());
	}
}


