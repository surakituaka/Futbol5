package GUI;

import javax.swing.*;

import auxiliares.PenalizacionesRenderer;
import clasesDeNegocio.Jugador;
import clasesDeNegocio.Penalizacion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class Admin_Jugadores extends VentanaTheGrid {
	private static final long serialVersionUID = -7314897519517182277L;
	
	//Componentes no cambiantes
	private JLabel lblNombre;
	private JLabel lblEmail;
	private JLabel lblApellido;
	private JLabel lblUsuario;
	private JLabel lblHandicap;
	private JLabel lblPenalizaciones;

	private JTextField campoNombre;
	private JTextField campoApellido;
	private JTextField campoEmail;
	
	//Componentes moficables
	private DefaultListModel<Penalizacion> modelo_lista;
	private JScrollPane scroll_lista;
	private JList<Penalizacion> lista_penalizaciones;
	private JComboBox<Integer> handicap;
	private JComboBox<String> comboUsuarios;
	private JButton btnGuardarCambios;
	private JButton btnVolver;

	//Constructor
	public Admin_Jugadores(GlobalParameters caller) {
		global = caller; //Seteamos el atributo global
		
		//Definimos la Jframe Principal
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		        global.pantalla_anterior.setVisible(true); 
		    }
		});
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Datos Personales");
		setBounds(100, 100, 318, 320);
		getContentPane().setLayout(null);
				
		//Creemos los cambios de texto
		campoNombre = new JTextField();
		campoApellido = new JTextField();
		campoEmail = new JTextField();

		campoNombre.setColumns(10);
		campoApellido.setColumns(10);
		campoEmail.setColumns(10);
		campoNombre.setBounds(162, 46, 139, 20);
		campoApellido.setBounds(162, 71, 139, 20);
		campoEmail.setBounds(162, 96, 139, 20);

		getContentPane().add(campoNombre);
		getContentPane().add(campoApellido);
		getContentPane().add(campoEmail);

		//Creemos las Labels
		
		lblNombre = new JLabel("Nombre");
		lblApellido = new JLabel("Apellido");
		lblEmail = new JLabel("E-mail");
		lblUsuario = new JLabel("Usuario");
		lblHandicap = new JLabel("Handicap");
		lblPenalizaciones = new JLabel("Penalizaciones");

		lblNombre.setBounds(10, 49, 46, 14);
		lblApellido.setBounds(10, 74, 126, 14);
		lblEmail.setBounds(10, 99, 46, 14);
		lblUsuario.setBounds(10, 22, 93, 14);
		lblHandicap.setBounds(10, 124, 139, 14);
		lblPenalizaciones.setBounds(10, 149, 139, 14);

		getContentPane().add(lblNombre);		
		getContentPane().add(lblApellido);
		getContentPane().add(lblEmail);
		getContentPane().add(lblUsuario);
		getContentPane().add(lblHandicap);
		getContentPane().add(lblPenalizaciones);

		//Carguemos las combo
		comboUsuarios = new JComboBox<String>();
		comboUsuarios.setBounds(162, 19, 139, 20);
		getContentPane().add(comboUsuarios);
		for(int i=0;i<global.jugadores.size();i++){
			comboUsuarios.addItem(global.jugadores.get(i).getUsuario());
		}
		handicap = new JComboBox<Integer>();
		handicap.setBounds(162, 122, 139, 20);
		getContentPane().add(handicap);
		for(int i=1; i< 11;i++)
			handicap.addItem(i);
		
		//Creemos los botones
		
		btnGuardarCambios = new JButton("Guardar");
		btnGuardarCambios.setBounds(10, 257, 89, 23);
		getContentPane().add(btnGuardarCambios);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(212, 257, 89, 23);
		getContentPane().add(btnVolver);
		
		//Carguemos la lista de penalizaciones
		
		modelo_lista = new DefaultListModel<Penalizacion>();
		lista_penalizaciones = new JList<Penalizacion>(modelo_lista);
		lista_penalizaciones.setBounds(9, 177, 292, 69);
		getContentPane().add(lista_penalizaciones);
		lista_penalizaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista_penalizaciones.setCellRenderer(new PenalizacionesRenderer());
		scroll_lista = new JScrollPane();
		scroll_lista.setBounds(10, 177, 291, 69);
		getContentPane().add(scroll_lista);
		
		//Carguemos los datos
		cargarDatos();
		
		//Comportamiento de la combo
		
		comboUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					cargarDatos();
			}
		});		
		
		//Comportamiento de los botones
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				terminate();
			}
		});
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboUsuarios.getSelectedIndex() > -1){
					Jugador jugador = global.getJugadorByUsuario((String) comboUsuarios.getSelectedItem());
					if(!campoNombre.getText().equals(jugador.getNombre()))
						jugador.setNombre(campoNombre.getText());
					if(!campoApellido.getText().equals(jugador.getAmigos()))
						jugador.setApellido(campoApellido.getText());
					if(!campoEmail.getText().equals(jugador.getEmail()))
						jugador.setEmail(campoEmail.getText());//En alguna iteracion posterior se validara el formato
					jugador.setHandicap((Integer) handicap.getSelectedItem());
					JOptionPane.showMessageDialog(null, "SE han guardado los datos.", "Datos Guardados", JOptionPane.PLAIN_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(null, "Elija un jugador valido.", "Error Guardado", JOptionPane.ERROR_MESSAGE);
			}
		});
	}
	
	public void cargarDatos(){
		limpiarDatos();
		if(comboUsuarios.getSelectedIndex() > -1){
			String id_usuario = (String) comboUsuarios.getSelectedItem();
			Jugador jugador = global.getJugadorByUsuario(id_usuario);
			campoNombre.setText(jugador.getNombre());
			campoNombre.setForeground(this.colorJugador(jugador));
			campoApellido.setText(jugador.getApellido());
			campoApellido.setForeground(this.colorJugador(jugador));
			campoEmail.setText(jugador.getEmail());
			campoEmail.setForeground(this.colorJugador(jugador));
			handicap.setSelectedIndex(jugador.getHandicap() -1);
			for(int i=0;i<jugador.getPenalizaciones().size();i++){
				modelo_lista.addElement(jugador.getPenalizaciones().get(i));
			}
		}

	}
	
	private void limpiarDatos(){
		campoNombre.setText("");
		campoApellido.setText("");
		campoEmail.setText("");
		modelo_lista.removeAllElements();
	}
}
