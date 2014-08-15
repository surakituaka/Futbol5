package GUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class BajaJugador extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2710989898521557695L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BajaJugador frame = new BajaJugador();
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
	public BajaJugador() {
		setTitle("Baja de un Partido");
		setBounds(100, 100, 322, 184);
		getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(129, 11, 167, 20);
		getContentPane().add(comboBox);
		
		JLabel lblPartidos = new JLabel("Partidos Inscriptos");
		lblPartidos.setBounds(10, 14, 116, 14);
		getContentPane().add(lblPartidos);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(207, 122, 89, 23);
		getContentPane().add(btnCancelar);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setForeground(new Color(0, 0, 0));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 42, 286, 69);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblTipoDeInscripcin = new JLabel("Reemplazo:");
		lblTipoDeInscripcin.setBounds(10, 11, 100, 14);
		panel.add(lblTipoDeInscripcin);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(142, 8, 134, 20);
		panel.add(comboBox_1);
		
		JButton btnInscribirse = new JButton("Inscribirse");
		btnInscribirse.setBounds(10, 122, 89, 23);
		getContentPane().add(btnInscribirse);

	}
}

