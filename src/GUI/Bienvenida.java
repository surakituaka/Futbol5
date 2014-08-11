package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Bienvenida extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3499068278467828908L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bienvenida frame = new Bienvenida();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Bienvenida() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 193, 156);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JLabel Label1A = new JLabel("No ha pulsado nada a\u00FAn...");
		Label1A.setBounds(10, 11, 157, 41);
		contentPane.add(Label1A);
		JButton btnNewButton = new JButton("Pulsador");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Label1A.setText("Ha pulsado el Botón!");
			}
		});
		btnNewButton.setBounds(48, 72, 89, 23);
		contentPane.add(btnNewButton);
	}
}
