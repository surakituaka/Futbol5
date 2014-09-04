package GUI;

import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import clasesDeNegocio.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;

public class Jugador_RecomendacionPopup extends VentanaTheGrid {
	private static final long serialVersionUID = 6733821951707675946L;
	
	//Componentes no cambiantes
	private JPanel panel;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblEmail;
	private JLabel lblNuevoAmigo;
	
	//Componentes modificables
	private JTextField campoNombre;
	private JTextField campoApellido;
	private JTextField campoEmail;
	private JButton btnRecomendar;
	private JButton btnCancelar;

	//Constructor
	public Jugador_RecomendacionPopup(GlobalParameters caller, final Partido partido) {
		/*Como lo que se manda es un JDialog, que no extiende de JFrame, 
		 no nos sirve la definida en Global :/
		*/
		//Seteamos los atributos
		global = caller;
		
		//Definimos el JFrame principal 
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		    	global.pantalla_anterior.setEnabled(true); 
		    }
		});
		setResizable(false);
		setTitle("Recomendar Amigos");
		setBounds(100, 100, 293, 200);
		getContentPane().setLayout(null);
		panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 11, 267, 121);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		//Creamos los botones
		
		btnRecomendar = new JButton("Recomendar");
		btnCancelar = new JButton("Cancelar");

		btnRecomendar.setBounds(10, 143, 127, 23);
		btnCancelar.setBounds(188, 143, 89, 23);

		getContentPane().add(btnRecomendar);
		getContentPane().add(btnCancelar);
		
		//Creemos los campos de Texto y las labels

		campoNombre = new JTextField();
		campoApellido = new JTextField();
		campoEmail = new JTextField();

		campoNombre.setBounds(105, 40, 152, 20);
		campoApellido.setBounds(105, 65, 152, 20);
		campoEmail.setBounds(105, 90, 152, 20);

		panel.add(campoNombre);
		panel.add(campoApellido);
		panel.add(campoEmail);

		campoNombre.setColumns(10);
		campoApellido.setColumns(10);		
		campoEmail.setColumns(10);
		
		
		lblNombre = new JLabel("Nombre(s)");
		lblApellido = new JLabel("Apellido(s)");
		lblEmail = new JLabel("Email");
		lblNuevoAmigo = new JLabel("Datos Amigo");

		lblNombre.setBounds(10, 43, 85, 14);
		lblApellido.setBounds(10, 68, 85, 14);
		lblEmail.setBounds(10, 93, 85, 14);
		lblNuevoAmigo.setBounds(10, 11, 247, 14);

		panel.add(lblNombre);
		panel.add(lblApellido);
		panel.add(lblEmail);
		panel.add(lblNuevoAmigo);
		lblNuevoAmigo.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Comportamiento de los Botones
			
		btnRecomendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(completoLosCampos()){
					Amigo nuevo_amigo = new Amigo(campoNombre.getText(),campoApellido.getText(),campoEmail.getText());
					if(!global.jugador_seleccionado.yaExisteAmigo(nuevo_amigo)) {
						IOrden orden = new OrdenNuevoJugador();
						((OrdenNuevoJugador) orden).setAdmin(global.administrador);
						global.jugador_seleccionado.setPropocicion(orden);
						global.jugador_seleccionado.proponer_jugador(nuevo_amigo, partido);
						global.pantalla_anterior.terminate();
						dispose();
					}
					JOptionPane.showMessageDialog(null, "El amigo ingresado ya existe en el sistema!", "Error al Recomendar Amigo", JOptionPane.ERROR_MESSAGE);					
				}
				else
					JOptionPane.showMessageDialog(null, "Complete todos los campos para proponer a un amigo", "Error al Recomendar Amigo", JOptionPane.ERROR_MESSAGE);					
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				global.pantalla_anterior.terminate(); 
		    	dispose();
			}
		});
	}
	
	private boolean completoLosCampos(){ //Veamos si se llenaron todos los campos
		if(campoNombre.getText().equals(""))
			return false;
		if(campoApellido.getText().equals(""))
			return false;
		if(campoEmail.getText().equals(""))
			return false;
		return true;
	}
}
