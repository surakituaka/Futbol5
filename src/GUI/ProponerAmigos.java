package GUI;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProponerAmigos extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8299159870982517446L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProponerAmigos frame = new ProponerAmigos();
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
	public ProponerAmigos() {
		setTitle("Proponer Amigos");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCancelar.setBounds(335, 236, 89, 23);
		getContentPane().add(btnCancelar);

	}

}
