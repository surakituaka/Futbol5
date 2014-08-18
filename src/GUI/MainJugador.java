package GUI;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import club.*;

public class MainJugador extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2861295277923642005L;
	private JPanel contentPane;
	private Principal pantalla_principal;
	private MainJugador pantalla_jugador;
	/**
	 * Create the frame.
	 */
	public MainJugador(Principal principal_caller, Jugador jugador) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainJugador.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
		setTitle("The Grid");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 287);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		pantalla_principal = principal_caller;
		pantalla_jugador = this;
		
		JLabel lblNewLabel = new JLabel("Organizador de Torneos de Futbol 5");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(69, 11, 409, 53);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Inscribirse a un Partido");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Inscribirse pantalla_inscripcion = new Inscribirse(pantalla_jugador);
				pantalla_inscripcion.setVisible(true);
				pantalla_jugador.setEnabled(false);
			}
		});
		btnNewButton.setBounds(10, 75, 237, 41);
		contentPane.add(btnNewButton);
		
		JButton btnEditarDatosPersonales = new JButton("Datos Personales");
		btnEditarDatosPersonales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnEditarDatosPersonales.setBounds(315, 75, 237, 41);
		contentPane.add(btnEditarDatosPersonales);
		
		JButton btnBajaDeUn = new JButton("Baja de un Partido");
		btnBajaDeUn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BajaJugador pantalla_bajaJugador = new BajaJugador(pantalla_jugador);
				pantalla_bajaJugador.setVisible(true);	
				pantalla_jugador.setEnabled(false);				
			}
		});
		btnBajaDeUn.setBounds(10, 149, 237, 41);
		contentPane.add(btnBajaDeUn);
		
		JButton btnPenalizaciones = new JButton("Proponer Amigos");
		btnPenalizaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnPenalizaciones.setBounds(315, 149, 237, 41);
		contentPane.add(btnPenalizaciones);
		
		JButton btnCerrarSesin = new JButton("Cerrar Sesi\u00F3n");
		btnCerrarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pantalla_principal.setVisible(true);
				dispose();
			}
		});
		btnCerrarSesin.setToolTipText("");
		btnCerrarSesin.setSelectedIcon(new ImageIcon(MainJugador.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		btnCerrarSesin.setIcon(new ImageIcon(MainJugador.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		btnCerrarSesin.setBounds(417, 218, 135, 29);
		contentPane.add(btnCerrarSesin);
	}
}
