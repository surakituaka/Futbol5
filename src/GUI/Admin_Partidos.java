package GUI;

import java.awt.FlowLayout;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import clasesDeNegocio.*;
import clasesManejadoras.ConexionDB;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;

import org.jbundle.thin.base.screen.jcalendarbutton.JCalendarButton;

public class Admin_Partidos extends VentanaTheGrid {
	private static final long serialVersionUID = 6057447328239256306L;
	
	//Componentes no alterables
	private JPanel contentPanel;
	private JPanel panel;
	
	private JMenuBar barraMenu;
	private JMenu menuAcciones;
	
	private JLabel lblPartido;
	private JLabel lblId;
	private JLabel lblFecha;
	private JLabel lblLugar;
	private JLabel lblJugadores;
	private JLabel lblEstadoPartido;

	//Componentes Modificables
	private JMenuItem accionNuevoPartido;
	private JMenuItem accionEliminarPartido;

	private JLabel lblCantInscriptos;
	
	private JTextField campoId;
	private JTextField campoFecha;
	private JTextField campoLugar;
	
	private JComboBox<String> comboPartidos;
	private JComboBox<String> comboJugadores;
	
	private JButton btnCambiarEstado;
	private JButton btnGuardarCambios;
	private JButton btnVolver;

	//Atributos
	private Partido partido_seleccionado = null;
	private String gen_cantJugadores = "Cantidad de Inscriptos: ";
	private String gen_EstadoActual = "Estado Actual: ";
	private String gen_equipos = "Generar Equipos Tentativos";
	private SimpleDateFormat formato_fecha = new SimpleDateFormat("dd/MM/yy");
    private JCalendarButton calendario;
	

	//Constructor
	public Admin_Partidos(GlobalParameters caller) {
		global = caller; //Seteamos los atributos
		
		//Creemos la JFrame principal y los paneles
		setResizable(false);
		setTitle("Gestionar Partidos");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		    	global.pantalla_anterior.setVisible(true);
		    }
		});
		
		setBounds(100, 100, 333, 337);
		getContentPane().setLayout(null);
		contentPanel = new JPanel();
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 60, 307, 178);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		//Creemos las labels
		
		lblPartido = new JLabel("Partido");
		lblId = new JLabel("Id:");
		lblFecha = new JLabel("Fecha:");
		lblLugar = new JLabel("Lugar:");
		lblCantInscriptos = new JLabel(gen_cantJugadores);
		lblJugadores = new JLabel("Jugadores:");
		lblEstadoPartido = new JLabel(gen_EstadoActual);

		lblPartido.setLabelFor(comboPartidos);
		lblId.setLabelFor(campoId);
		lblFecha.setLabelFor(campoFecha);
		lblLugar.setLabelFor(campoLugar);
		lblJugadores.setLabelFor(comboJugadores);

		lblPartido.setBounds(10, 35, 139, 14);
		lblId.setBounds(10, 11, 137, 14);
		lblFecha.setBounds(10, 36, 100, 14);
		lblLugar.setBounds(10, 61, 137, 14);
		lblCantInscriptos.setBounds(10, 91, 287, 14);
		lblJugadores.setBounds(10, 116, 137, 14);
		lblEstadoPartido.setBounds(10, 249, 307, 14);
		
		getContentPane().add(lblPartido);
		panel.add(lblId);
		panel.add(lblFecha);	
		panel.add(lblLugar);
		panel.add(lblCantInscriptos);	
		panel.add(lblJugadores);
		getContentPane().add(lblEstadoPartido);

		//Creemos los campos de Texto
		
		campoId = new JTextField();
		campoFecha = new JTextField();
		campoFecha.setEditable(false);
		campoLugar = new JTextField();
		
		campoId.setBounds(157, 8, 140, 20);
		campoFecha.setBounds(157, 33, 140, 20);
		campoLugar.setBounds(157, 58, 140, 20);
		
		campoId.setColumns(10);
		campoFecha.setColumns(10);
		campoLugar.setColumns(10);

		panel.add(campoId);
		panel.add(campoFecha);	
		panel.add(campoLugar);
		
		//Creemos los botones 
		
		btnCambiarEstado = new JButton("Generar Equipos Tentativos");
		btnGuardarCambios = new JButton("Guardar Cambios");
		btnVolver = new JButton("Volver");

		btnCambiarEstado.setBounds(10, 274, 207, 23);
		btnGuardarCambios.setBounds(8, 144, 139, 23);
		btnVolver.setBounds(227, 274, 90, 23);

		getContentPane().add(btnCambiarEstado);
		panel.add(btnGuardarCambios);
		getContentPane().add(btnVolver);
		
		calendario = new JCalendarButton();
		calendario.setBounds(120, 33, 30, 20);
		panel.add(calendario);
		calendario.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
		    public void propertyChange(java.beans.PropertyChangeEvent evt) {
		        if (evt.getNewValue() instanceof Date)
		            setDate(campoFecha, calendario, (Date)evt.getNewValue());
		    }
		});
		
		//Creemos las comboBox

		comboPartidos = new JComboBox<String>();
		comboJugadores = new JComboBox<String>();

		comboPartidos.setBounds(159, 32, 158, 20);
		comboJugadores.setBounds(157, 113, 140, 20);
				
		getContentPane().add(comboPartidos);
		panel.add(comboJugadores);
		//inicializamos las combo
		for(int i=0;i < global.partidos.size();i++) 
			comboPartidos.addItem(global.partidos.get(i).getPartido_nombre());
		if(comboPartidos.getSelectedIndex() > -1)
			llenarDatosPartido();

		//Creemos los menues
		
		barraMenu = new JMenuBar();
		barraMenu.setBounds(0, 0, 327, 21);
		getContentPane().add(barraMenu);
		
		menuAcciones = new JMenu("Acciones");
		barraMenu.add(menuAcciones);
		
		accionNuevoPartido = new JMenuItem("Crear Partido");
		menuAcciones.add(accionNuevoPartido);		
		
		accionEliminarPartido = new JMenuItem("Eliminar Partido Seleccionado");
		menuAcciones.add(accionEliminarPartido);
		
		//Comportamiento de las combo
		
		comboPartidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboPartidos.getSelectedIndex() > -1)
					llenarDatosPartido();
			}
		});
			
		//Comportamiento de los menues
		
		accionNuevoPartido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Admin_NuevoPartido ventana_nuevo_partido = new Admin_NuevoPartido(new GlobalParameters(global,null, yo));
				habilitarPantalla(ventana_nuevo_partido);
			}
		});
		
		accionEliminarPartido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int response = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar el partido?", "Eliminar Partido", JOptionPane.YES_NO_OPTION);
				if(response == JOptionPane.YES_OPTION)
					if(global.eliminarPartido(partido_seleccionado)){
						/*for(Jugador jugador : partido_seleccionado.getEquipo1().getJugadores()){
							jugador.getCalificacionesByPartido(partido_seleccionado);
							for(Calificacion calificacion :jugador.getCalificacionesByPartido(partido_seleccionado)) ConexionDB.borrar(calificacion);
						}
						for(Jugador jugador : partido_seleccionado.getEquipo2().getJugadores()){
							jugador.getCalificacionesByPartido(partido_seleccionado);
							for(Calificacion calificacion :jugador.getCalificacionesByPartido(partido_seleccionado)) ConexionDB.borrar(calificacion);
						}
						ConexionDB.borrar(partido_seleccionado.getEquipo1());
						ConexionDB.borrar(partido_seleccionado.getEquipo2());*/
						
						for (Inscripcion inc :partido_seleccionado.getInscripciones()){
							//partido_seleccionado.getInscripciones().remove(inc);
							ConexionDB.borrar(inc);
						}
						//ConexionDB.guardar(partido_seleccionado);
						//ConexionDB.borrar(partido_seleccionado);
						comboPartidos.removeItem(partido_seleccionado.getPartido_nombre());
						llenarDatosPartido();
					}
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
				int response = JOptionPane.showConfirmDialog(null, "Esta seguro que desea guardar los cambios?", "Guardar Partido", JOptionPane.PLAIN_MESSAGE);
				if(response == JOptionPane.OK_OPTION) {
					Date fecha;
					try {
						fecha = formato_fecha.parse(campoFecha.getText());
					} catch (ParseException e) {
						JOptionPane.showMessageDialog(null, "Fecha Ingresada Erronea", "Error al Guardar el Partido", JOptionPane.ERROR_MESSAGE);
						return;
					}
					partido_seleccionado.setPartido_nombre(campoId.getText());
					partido_seleccionado.setFecha(fecha);
					partido_seleccionado.setLugar(campoLugar.getText());
					ConexionDB.guardar(partido_seleccionado);//Actualiza el partido en la DB
					llenarDatosPartido();
				}
			}
		});
		
		btnCambiarEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					if(partido_seleccionado.getInscripciones().size() != 10) {
						JOptionPane.showMessageDialog(null, "El partido no tiene suficientes jugadores para generar equipos", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					Admin_Equipos pantalla_equipos = new Admin_Equipos(new GlobalParameters(global, null, yo), partido_seleccionado);
					pantalla_equipos.setVisible(true);
					yo.setVisible(false);
					llenarDatosPartido();
			}
		});
	}
	
	private void limpiarDatosPartidos(){
		campoId.setText("");
		campoFecha.setText("");
		campoLugar.setText("");
		lblCantInscriptos.setText(gen_cantJugadores);
		comboJugadores.removeAllItems();
		lblEstadoPartido.setText(gen_EstadoActual);
		btnCambiarEstado.setText(" ");
		btnCambiarEstado.setEnabled(true);
	}
	
	public void llenarDatosPartido(){
		//Limpiamos las labels y el combo
		limpiarDatosPartidos();
		String id_partido = (String) comboPartidos.getSelectedItem(); 
		partido_seleccionado = global.getPartidoById(id_partido);
		if(partido_seleccionado != null){
			for(int i=0;i< partido_seleccionado.getInscripciones().size();i++)
				comboJugadores.addItem(partido_seleccionado.getInscripciones().get(i).getJugador_inscripto().getUsuario());
			//Llenemos las labels
			campoId.setText(partido_seleccionado.getPartido_nombre());
			campoFecha.setText(formato_fecha.format(partido_seleccionado.getFecha()));
			campoLugar.setText(partido_seleccionado.getLugar());
			lblCantInscriptos.setText(gen_cantJugadores + Integer.toString(partido_seleccionado.getInscripciones().size()));
			lblEstadoPartido.setText(gen_EstadoActual + partido_seleccionado.getEstado());
			btnCambiarEstado.setText(obtenerEstadoBoton(partido_seleccionado));
			if(btnCambiarEstado.getText().matches(" "))
				btnCambiarEstado.setEnabled(false);
		}
	}
	private String obtenerEstadoBoton(Partido partido){
		if(partido.getSigEstado().matches("Pendiente"))
			return gen_equipos;
		if(partido.getSigEstado().matches(" "))
			return " ";
		return gen_equipos;
	}
	public void addPartido(Partido partido_nuevo)
	{
		comboPartidos.addItem(partido_nuevo.getPartido_nombre());
	}
	
}
