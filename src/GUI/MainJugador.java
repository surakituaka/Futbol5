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
import javax.swing.SwingConstants;

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
	public MainJugador(final GlobalParameters globales) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainJugador.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
		setTitle("The Grid");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 428, 188);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		pantalla_principal = (Principal) globales.pantalla_anterior;
		pantalla_jugador = this;
		
		
		JLabel titulo = new JLabel((new String("Ha accedido como Jugador: ")).concat(globales.jugador_seleccionado.getNombre()).concat(" ").concat(globales.jugador_seleccionado.getApellido()));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		titulo.setBounds(10, 11, 409, 53);
		contentPane.add(titulo);
		
		JButton btnInscripcion = new JButton("Inscribirse a un Partido");
		btnInscripcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Inscribirse pantalla_inscripcion = new Inscribirse(new GlobalParameters(globales, globales.jugador_seleccionado, pantalla_jugador));
				pantalla_inscripcion.setVisible(true);
				pantalla_jugador.setEnabled(false);
			}
		});
		btnInscripcion.setBounds(10, 75, 182, 29);
		contentPane.add(btnInscripcion);
		
		JButton btnCalificar = new JButton("Calificar");
		btnCalificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Calificar pantalla_calificacion = new Calificar(pantalla_jugador);
				pantalla_calificacion.setVisible(true);
				pantalla_jugador.setEnabled(false);
			}
		});
		btnCalificar.setBounds(237, 75, 182, 29);
		contentPane.add(btnCalificar);
		
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
		btnCerrarSesin.setBounds(284, 120, 135, 29);
		contentPane.add(btnCerrarSesin);
	}
}
