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

public class MainAdmin extends JFrame {
	private static final long serialVersionUID = 828297704025213357L;
	private JPanel contentPane;
	private Login pantalla_login;

	
	public MainAdmin(Login login_caller) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainAdmin.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
		setTitle("The Grid");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 292, 287);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		pantalla_login = login_caller;
		
		JLabel lblNewLabel = new JLabel("Panel de Administraci\u00F3n");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(10, 11, 272, 53);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Organizar nuevo Partido");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(62, 75, 149, 29);
		contentPane.add(btnNewButton);
		
		JButton btnEditarDatosPersonales = new JButton("Equipos");
		btnEditarDatosPersonales.setBounds(62, 155, 149, 29);
		contentPane.add(btnEditarDatosPersonales);
		
		JButton btnBajaDeUn = new JButton("Gesti\u00F3n de Jugadores");
		btnBajaDeUn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBajaDeUn.setBounds(62, 115, 149, 29);
		contentPane.add(btnBajaDeUn);
		
		JButton btnCerrarSesin = new JButton("Cerrar Sesi\u00F3n");
		btnCerrarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pantalla_login.setVisible(true);
				dispose();
			}
		});
		btnCerrarSesin.setToolTipText("");
		btnCerrarSesin.setSelectedIcon(new ImageIcon(MainAdmin.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		btnCerrarSesin.setIcon(new ImageIcon(MainAdmin.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		btnCerrarSesin.setBounds(72, 203, 121, 29);
		contentPane.add(btnCerrarSesin);
	}
}

