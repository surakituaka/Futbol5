package GUI;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;

import javax.swing.SwingConstants;

import clasesDeNegocio.Jugador;
import clasesDeNegocio.Partido;

public class Jugador_Baja extends VentanaTheGrid {
	private static final long serialVersionUID = 2710989898521557695L;
	
	//Componentes no cambiantes
	private JPanel panel_datos;
	private JLabel lblDatosDelPartido;
	private JLabel lblestSeguroQue;
	private JLabel lblReemplazo;
	private String gen_id = "ID: ";
	private String gen_fecha = "Fecha: ";
	private String gen_lugar = "Lugar: ";

	
	//Componentes modificables
	private JComboBox<String> lista_reemplazo = new JComboBox<String>();
	private JComboBox<String> lista_partido = new JComboBox<String>();
	private JLabel id;
	private JLabel lugar;
	private JLabel fecha;
	private JButton botonDesinscribirse;
	private JButton botonCancelar;
	
	//Atributos
	private Partido partido_a_baja = null;
	private Jugador reemplazo = null;
	
	//Constructor
	public Jugador_Baja(GlobalParameters caller) {
		global = caller; //Seteamos el atributo global
		
		//Definimos la Jframe Principal
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		        global.pantalla_anterior.setVisible(true); 
		    }
		});
		setResizable(false);
		setTitle("Baja de un Partido");
		setBounds(100, 100, 257, 257);
		getContentPane().setLayout(null);
		panel_datos = new JPanel();
		panel_datos.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_datos.setBounds(10, 36, 233, 83);
		panel_datos.setLayout(null);
		getContentPane().add(panel_datos);
		
		//Creemos los botones
		
		botonDesinscribirse = new JButton("Desinscribir");
		botonCancelar = new JButton("Cancelar");
		botonDesinscribirse.setBounds(10, 200, 116, 23);
		botonCancelar.setBounds(154, 200, 89, 23);
		getContentPane().add(botonDesinscribirse);
		getContentPane().add(botonCancelar);
		
		//Creemos los Combos
		
		lista_partido.setBounds(97, 8, 144, 20);
		lista_reemplazo.setBounds(99, 130, 144, 20);
		getContentPane().add(lista_partido);
		getContentPane().add(lista_reemplazo);
		for(int i=0;i<global.jugador_seleccionado.getInscripciones().size();i++){
			Partido partido_inscripto = global.jugador_seleccionado.getInscripciones().get(i).getPartido_inscripto();
			if(!partido_inscripto.estaConfirmado())
				lista_partido.addItem(partido_inscripto.getPartido_nombre());
		}

		//Creemos las Labels
		
		lblDatosDelPartido = new JLabel("Partido:");
		lblReemplazo = new JLabel("Reemplazo");
		lblestSeguroQue = new JLabel("\u00BFEst\u00E1 seguro que desea Desinscribirse?");
		
		id = new JLabel(gen_id);
		fecha = new JLabel(gen_fecha);
		lugar = new JLabel(gen_lugar);

		lblestSeguroQue.setHorizontalAlignment(SwingConstants.CENTER);
	
		lblDatosDelPartido.setBounds(10, 11, 79, 14);
		lblReemplazo.setBounds(10, 133, 79, 14);
		lblestSeguroQue.setBounds(10, 175, 233, 14);
		id.setBounds(10, 11, 210, 14);
		lugar.setBounds(10, 36, 210, 14);
		fecha.setBounds(10, 61, 210, 14);

		getContentPane().add(lblDatosDelPartido);
		getContentPane().add(lblReemplazo);		
		getContentPane().add(lblestSeguroQue);
		panel_datos.add(id);
		panel_datos.add(lugar);
		panel_datos.add(fecha);
		
		//Cargamos las labels y el reemplazo en funcion a lo seleccionado
		cargarDatos();
		
		//Comportamiento de las Combo
		
		lista_partido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarDatos();		
			}
		});
		lista_reemplazo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(lista_reemplazo.getSelectedIndex() > -1) {
					String usuario_reemplazo = (String) lista_reemplazo.getSelectedItem();
					reemplazo = global.getJugadorByUsuario(usuario_reemplazo);
				}
			}
		});
		
		//Logica de los Botones
		
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				terminate();
			}
		});		
		botonDesinscribirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(partido_a_baja != null){
					if(reemplazo != null) {
						global.jugador_seleccionado.bajarse_de(partido_a_baja, reemplazo);
						partido_a_baja.enviar_mensaje("partido "+partido_a_baja.getPartido_nombre(),global.administrador.getEmail(), "ya no hay 10 jugadores");	
					}
					else {
						int response = JOptionPane.showConfirmDialog(null, "Será Penalizado si no Selecciona un reemplazo", "Baja de un Partido", JOptionPane.WARNING_MESSAGE);
						if(response == JOptionPane.OK_OPTION)
							global.jugador_seleccionado.bajarse_de(partido_a_baja);			
					}
					lista_partido.removeItem(partido_a_baja.getPartido_nombre());
					cargarDatos();
				}
			}
		});
	}
	
	public void cargarDatos(){
		limpiarDatos();
		if(lista_partido.getSelectedIndex() > -1) {
			String nombre_partido = (String) lista_partido.getSelectedItem();
			partido_a_baja = global.getPartidoById(nombre_partido); //Seteamos el partido	
			//Actualizamos la lista de reemplazos para el partido seleccionado
			actualizarReemplazos(partido_a_baja);
			//Actualizamos las labels
			actualizarLabels(partido_a_baja);		
		}
	}
	
	public void limpiarDatos(){
		partido_a_baja = null;
		lista_reemplazo.removeAllItems();
		id.setText(gen_id);
		fecha.setText(gen_fecha);
		lugar.setText(gen_lugar);
	}
	
	private void actualizarReemplazos(Partido partido){
		lista_reemplazo.addItem("Sin Reemplazo");
		for (int i = 0;i<global.jugadores.size();i++){
			Jugador posible_jugador = global.jugadores.get(i);
				if(!posible_jugador.estoyInscripto(partido))
					lista_reemplazo.addItem(posible_jugador.getUsuario());
		}
	}
	
	private void actualizarLabels(Partido partido){
		id.setText(gen_id + partido.getId());
		fecha.setText(gen_fecha + (new SimpleDateFormat("dd-MM.yyyy")).format(partido.getFecha()));
		lugar.setText(gen_lugar + partido.getLugar());			
	}
}

