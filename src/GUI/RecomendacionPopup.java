package GUI;

import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import club.IOrden;
import club.Jugador;
import club.OrdenNuevoJugador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class RecomendacionPopup extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6733821951707675946L;
	private static Inscribirse pantalla_inscripcion;
	private JTextField campoNombre;
	private JTextField campoApellido;
	private JTextField campoEmail;
	/**
	 * Create the dialog.
	 */
	public RecomendacionPopup(final GlobalParameters global, JDialog caller) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		        // Perhaps ask user if they want to save any unsaved files first.
		    	pantalla_inscripcion.setEnabled(true); 
		    }
		});
		setResizable(false);
		setTitle("Recomendar Amigos");
		pantalla_inscripcion = (Inscribirse) caller;
		setBounds(100, 100, 293, 228);
		getContentPane().setLayout(null);
		
		JButton btnRecomendar = new JButton("Recomendar");
		btnRecomendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Jugador amigo = new Jugador();
				amigo.setNombre(campoNombre.getText());
				amigo.setApellido(campoNombre.getText());
				amigo.setEmail(campoEmail.getText());
				
				IOrden orden = new OrdenNuevoJugador();
				((OrdenNuevoJugador) orden).setAdmin(global.administrador);
				global.jugador_seleccionado.setPropocicion(orden);
				global.jugador_seleccionado.proponer_jugador(amigo, global.jugador_seleccionado.getInscripto());		
				pantalla_inscripcion.terminate();
				dispose();
			}
		});
		btnRecomendar.setBounds(10, 168, 127, 23);
		getContentPane().add(btnRecomendar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		    	pantalla_inscripcion.setEnabled(true); 
		    	dispose();
			}
		});
		btnCancelar.setBounds(188, 168, 89, 23);
		getContentPane().add(btnCancelar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 36, 267, 121);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre(s)");
		lblNombre.setBounds(10, 43, 85, 14);
		panel.add(lblNombre);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 93, 85, 14);
		panel.add(lblEmail);
		
		JLabel lblApellido = new JLabel("Apellido(s)");
		lblApellido.setBounds(10, 68, 85, 14);
		panel.add(lblApellido);
		
		campoNombre = new JTextField();
		campoNombre.setBounds(105, 40, 152, 20);
		panel.add(campoNombre);
		campoNombre.setColumns(10);
		
		campoApellido = new JTextField();
		campoApellido.setBounds(105, 65, 152, 20);
		panel.add(campoApellido);
		campoApellido.setColumns(10);
		
		campoEmail = new JTextField();
		campoEmail.setColumns(10);
		campoEmail.setBounds(105, 90, 152, 20);
		panel.add(campoEmail);
		
		JLabel lblNuevoAmigo = new JLabel("Datos Amigo");
		lblNuevoAmigo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevoAmigo.setBounds(10, 11, 247, 14);
		panel.add(lblNuevoAmigo);
		
		JLabel lblAmigosExistentes = new JLabel("Amigos Existentes");
		lblAmigosExistentes.setBounds(10, 11, 114, 14);
		getContentPane().add(lblAmigosExistentes);
		
		final JComboBox<String> comboAmigos = new JComboBox<String>();
		comboAmigos.setBounds(134, 8, 143, 20);
		getContentPane().add(comboAmigos);
		for(int i=0;i < global.jugador_seleccionado.getAmigos().size();i++) 
			comboAmigos.addItem(global.jugador_seleccionado.getAmigos().get(i).getNombre());
		comboAmigos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre_amigo = (String) comboAmigos.getSelectedItem();
				for(int i=0;i<global.jugador_seleccionado.getAmigos().size();i++)
					if(global.jugador_seleccionado.getAmigos().get(i).getNombre().equals(nombre_amigo)) {
						campoNombre.setText(global.jugador_seleccionado.getAmigos().get(i).getNombre());
						campoApellido.setText(global.jugador_seleccionado.getAmigos().get(i).getApellido());
						campoEmail.setText(global.jugador_seleccionado.getAmigos().get(i).getEmail());
					}
			}
		});

	}
}
