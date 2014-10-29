package GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clasesDeNegocio.Admin;
import clasesDeNegocio.Amigo;
import clasesDeNegocio.IModalidad;
import clasesDeNegocio.Jugador;
import clasesDeNegocio.Propuesta;
import clasesManejadoras.ConexionDB;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.border.BevelBorder;
import javax.swing.JTextField;

public class Admin_NuevosJugadores extends VentanaTheGrid {

	private static final long serialVersionUID = -3585753395902584681L;

	private final JPanel contentPanel = new JPanel();
	private final JButton btnAceptar;
	private final JComboBox<String> comboJugadores;
	private final JComboBox<String> comboInscripcion;
	private static Propuesta propuesta_seleccionada;
	private final JLabel l_nombre;
	private final JLabel l_apellido;
	private final JLabel l_email;
	private final JLabel l_partido;
	private final JLabel l_propositor;
	
	private String gen_nombre = "Nombre: ";
	private String gen_apellido = "Apellido: ";
	private String gen_email = "Email: ";
	private String gen_partido = "Partido: ";
	private String gen_propositor = "Propuesto por: ";
	private JTextField t_condicion;
	private String tipo_inscripcion_seleccionada;
	private JTextField nombreUsuario;


	public Admin_NuevosJugadores(final GlobalParameters caller) {
		setResizable(false);
		setTitle("Gestionar Propuestas");
		global = caller;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
				global.pantalla_anterior.setVisible(true);
		    }
		});
		setBounds(100, 100, 410, 365);
		getContentPane().setLayout(null);
		
		btnAceptar = new JButton("Crear e Inscribir Jugador");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboJugadores.getSelectedIndex() > -1) {
					String usuario = nombreUsuario.getText();
					if(!usuario.equals("")) {
						if(!global.existeUsuario(usuario)) {
							Jugador nuevo_jugador = new Jugador();
							nuevo_jugador.setUsuario(usuario);
							nuevo_jugador.setNombre(propuesta_seleccionada.getAmigo().getNombre());
							nuevo_jugador.setApellido(propuesta_seleccionada.getAmigo().getApellido());
							nuevo_jugador.setEmail(propuesta_seleccionada.getAmigo().getEmail());
							nuevo_jugador.setPassword("1234");
							IModalidad modalidad = nuevo_jugador.getTipofromString(tipo_inscripcion_seleccionada, t_condicion.getText());
							Jugador jugador_yaInscripto = propuesta_seleccionada.getJugador();
							Amigo nuevo_amigo = new Amigo(jugador_yaInscripto.getNombre(),jugador_yaInscripto.getApellido(),jugador_yaInscripto.getEmail());
							nuevo_jugador.agregar_amigo(nuevo_amigo);
							global.administrador.aprobar_propuesta(propuesta_seleccionada, nuevo_jugador, modalidad);
							global.administrador.getNuevas_propuestas().remove(propuesta_seleccionada);							
							ConexionDB.guardar(nuevo_jugador);
							//ConexionDB.guardar(jugador_yaInscripto);
							//ConexionDB.guardar(propuesta_seleccionada.getPartido());
							ConexionDB.borrar(propuesta_seleccionada.getAmigo());
							//ConexionDB.borrar(propuesta_seleccionada);
							terminate();
						}
						else JOptionPane.showMessageDialog(null, "Nombre de Usuario Inválido o Existente.", "Error", JOptionPane.ERROR_MESSAGE);
						
					}
					else JOptionPane.showMessageDialog(null, "Nombre de Usuario Inválido o Existente.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(null, "Seleccione una propuesta.", "Error", JOptionPane.WARNING_MESSAGE);

			}
		});
		btnAceptar.setBounds(10, 308, 158, 23);
		getContentPane().add(btnAceptar);
		JButton btnRechazar = new JButton("Rechazar");
		btnRechazar.setBounds(297, 308, 97, 23);
		getContentPane().add(btnRechazar);
		btnRechazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboJugadores.getSelectedIndex() > -1) {
					global.administrador.getNuevas_propuestas().remove(propuesta_seleccionada);
					String razon = JOptionPane.showInputDialog(null, "Ingrese una Razón", "Rechazar una Propuesta", JOptionPane.PLAIN_MESSAGE);
					global.administrador.rechazar_propuesta(propuesta_seleccionada,razon);					
					terminate();
				}
				else
					JOptionPane.showMessageDialog(null, "Seleccione una propuesta.", "Error", JOptionPane.WARNING_MESSAGE);
				//TODO TERMINAR
			}
		});
		
		comboJugadores = new JComboBox<String>();
		for(int i = 0;i < global.administrador.getNuevas_propuestas().size();i++) {

			if(global.administrador.getNuevas_propuestas().get(i).estado_propuesta.matches("pendiente"))
				comboJugadores.addItem(global.administrador.getNuevas_propuestas().get(i).getAmigo().getNombre());
		}
		
		comboJugadores.setBounds(161, 50, 233, 20);
		getContentPane().add(comboJugadores);
		
		JLabel lblPropuestasDeJugador = new JLabel("Propuestas de Jugadores:");
		lblPropuestasDeJugador.setBounds(10, 53, 141, 14);
		getContentPane().add(lblPropuestasDeJugador);
		
		JLabel Titulo = new JLabel("Nuevas propuestas de Jugador");
		Titulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setBounds(10, 11, 384, 28);
		getContentPane().add(Titulo);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 78, 384, 138);
		getContentPane().add(panel);
		panel.setLayout(null);
		l_nombre = new JLabel(gen_nombre);
		l_nombre.setBounds(10, 11, 289, 14);
		panel.add(l_nombre);
		l_apellido = new JLabel(gen_apellido);
		l_apellido.setBounds(10, 36, 289, 14);
		panel.add(l_apellido);
		l_email = new JLabel(gen_email);
		l_email.setBounds(10, 61, 289, 14);
		panel.add(l_email);
		l_partido = new JLabel(gen_partido);
		l_partido.setBounds(10, 86, 289, 14);
		panel.add(l_partido);
		l_propositor = new JLabel(gen_propositor);
		l_propositor.setBounds(10, 111, 289, 14);
		panel.add(l_propositor);
		
		t_condicion = new JTextField();
		t_condicion.setBounds(161, 277, 158, 20);
		getContentPane().add(t_condicion);
		t_condicion.setColumns(10);
		
		comboInscripcion = new JComboBox<String>();
		comboInscripcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tipo_inscripcion_seleccionada = (String) comboInscripcion.getSelectedItem();
			}
		});
		comboInscripcion.addItem("Standar");
		tipo_inscripcion_seleccionada = "Standar";
		comboInscripcion.addItem("Condicional");
		comboInscripcion.addItem("Solidaria");
		comboInscripcion.setBounds(10, 277, 141, 20);
		getContentPane().add(comboInscripcion);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 227, 152, 14);
		getContentPane().add(lblUsuario);
		
		nombreUsuario = new JTextField();
		nombreUsuario.setBounds(173, 224, 221, 20);
		getContentPane().add(nombreUsuario);
		nombreUsuario.setColumns(10);
		
		JLabel lblDatosDeLa = new JLabel("Datos de la Inscripci\u00F3n");
		lblDatosDeLa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDatosDeLa.setBounds(10, 252, 384, 14);
		getContentPane().add(lblDatosDeLa);
		
		if(global.administrador.getNuevas_propuestas().size() > 0) //Seteamos el inicial si existen nuevas propuestas
			setearLabels(global.administrador, 0);		
		comboJugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre_amigo = (String) comboJugadores.getSelectedItem();
				for(int i=0;i < global.administrador.getNuevas_propuestas().size();i++){
					if(global.administrador.getNuevas_propuestas().get(i).estado_propuesta.matches("pendiente")){
						Amigo amigo = global.administrador.getNuevas_propuestas().get(i).getAmigo();
						if(amigo.getNombre().equals(nombre_amigo)) 
							setearLabels(global.administrador, i);
					}
				}
			}
		});
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));	
	}
	
	public void setearLabels(Admin admin, int i){
		Amigo amigo = admin.getNuevas_propuestas().get(i).getAmigo();
		l_nombre.setText(gen_nombre.concat(amigo.getNombre()));
		l_apellido.setText(gen_apellido.concat(amigo.getApellido()));
		l_email.setText(gen_email.concat(amigo.getEmail()));
		l_partido.setText(gen_partido.concat(admin.getNuevas_propuestas().get(i).getPartido().getPartido_nombre()));
		l_propositor.setText(gen_propositor.concat(admin.getNuevas_propuestas().get(i).getJugador().getNombre()));
		propuesta_seleccionada = admin.getNuevas_propuestas().get(i);

	}
}
