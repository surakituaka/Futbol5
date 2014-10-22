package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.border.MatteBorder;

import java.awt.Color;

public class Intro_TheGrid extends VentanaTheGrid {
	
	private JPanel panel;
	private JPanel contentPane;
	private JButton btnIngresar;
	public Intro_TheGrid() {
		setType(Type.UTILITY);
		setResizable(false);
		setTitle("Intro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(10, 11, 424, 193);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(118, 222, 225, 37);
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Accion del boton
				Principal ventana_principal = new Principal();
				habilitarPantalla(ventana_principal);
			}
		});
		btnIngresar.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER){
					btnIngresar.doClick();
				}
			} //Como esta por interfaz hay que poner todas
			public void keyPressed(KeyEvent e) {} //No hacemos nada
			public void keyReleased(KeyEvent e) {} //No hacemos nada
		});
		getContentPane().setLayout(null);
		getContentPane().add(panel);
		getContentPane().add(btnIngresar);
		
		//Nos conectamos a la DB; es estatico
		VentanaTheGrid.global_init = new Global();
	}
	private static final long serialVersionUID = 1L;
}
