package GUI;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;
import javax.swing.JPanel;

import clasesDeNegocio.Penalizacion;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;


public class Visualizacion_Jugadores extends VentanaTheGrid {
	//Componentes no cambiantes
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JLabel lblPenalizaciones;

	//Componentes modificables
	
	private JLabel lblNombre;
	private JLabel lblHandicap;
	private JLabel lblPromedioUltimo;
	private JLabel lblPromedioTodos;
	private JLabel lblFecha;
	private JLabel lblPartidosJugados;
	private JButton btnVolver;
	
	//Generadores de Texto
	private String gen_nombre = "Nombre: ";
	private String gen_handicap = "Handicap: ";
	private String gen_promedioUltimo = "Promedio del Ultimo Partido: ";
	private String gen_promedioTodos = "Promedio de los Partidos Jugados: ";
	private String gen_fecha = "Fecha de Nacimiento: ";
	private String gen_partidos = "Partidos Jugados: ";
	//Formato de Fecha
	private SimpleDateFormat formato_fecha = new SimpleDateFormat("dd/MM/yy");
	private SimpleDateFormat formato_hora = new SimpleDateFormat("hh:mm");

	//Constructor
	public Visualizacion_Jugadores(GlobalParameters caller) {
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
		setBounds(100, 100, 291, 400);
		getContentPane().setLayout(null);
		
		//Creemos las Labels
		lblNombre = new JLabel(gen_nombre);
		lblNombre.setBounds(10, 11, 265, 14);
		getContentPane().add(lblNombre);
		
		lblHandicap = new JLabel(gen_handicap);
		lblHandicap.setBounds(10, 36, 265, 14);
		getContentPane().add(lblHandicap);
		
		lblPromedioUltimo = new JLabel(gen_promedioUltimo);
		lblPromedioUltimo.setBounds(10, 61, 265, 14);
		getContentPane().add(lblPromedioUltimo);
		
		lblPromedioTodos = new JLabel(gen_promedioTodos);
		lblPromedioTodos.setBounds(10, 86, 265, 14);
		getContentPane().add(lblPromedioTodos);
		
		lblFecha = new JLabel(gen_fecha);
		lblFecha.setBounds(10, 111, 265, 14);
		getContentPane().add(lblFecha);
		
		lblPartidosJugados = new JLabel(gen_partidos);
		lblPartidosJugados.setBounds(10, 136, 265, 14);
		getContentPane().add(lblPartidosJugados);
		
		lblPenalizaciones = new JLabel("Penalizaciones");
		lblPenalizaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblPenalizaciones.setBounds(10, 161, 265, 14);
		getContentPane().add(lblPenalizaciones);
		
		actualizarLabels();
		//Creemos el boton para volver
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(186, 338, 89, 23);
		getContentPane().add(btnVolver);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				terminate();
			}
		});
		
		//Creemos la tabla de Penalizaciones
		JPanel panel = new JPanel();
		panel.setBounds(10, 176, 265, 151);
		getContentPane().add(panel);
		//table = new JTable(obtenerPenalizaciones(), columnas);
		table = new JTable();
		table.setModel(new DefaultTableModel(
				obtenerPenalizaciones(),
			new String[] {
				"Fecha", "Hora", "Motivo"
			}
		) {
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(54);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.setCellSelectionEnabled(true);
		table.setRowSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setPreferredScrollableViewportSize(new Dimension(240,100));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
	}
	private String[][] obtenerPenalizaciones() {
		List<Penalizacion> penalizaciones = global.jugador_seleccionado.getPenalizaciones();
		int cantPenalizaciones = penalizaciones.size();
		String[][] tabla = new String[cantPenalizaciones][3];
		for(int i=0; i< cantPenalizaciones;i++){
			tabla[i][0] = formato_fecha.format(penalizaciones.get(i).getFecha());
			tabla[i][1] = formato_hora.format(penalizaciones.get(i).getFecha());
			tabla[i][2] = penalizaciones.get(i).getMotivo();
		}
		return tabla;
	}
	private void actualizarLabels(){
		if(global.jugador_seleccionado != null) {
			
			lblNombre.setText(gen_nombre + global.jugador_seleccionado.getNombre() + " " 	+ global.jugador_seleccionado.getApellido());
			lblNombre.setForeground(colorJugador(global.jugador_seleccionado)); //Actualiza el color del nombre segun el jugador
			lblHandicap.setText(gen_handicap + global.jugador_seleccionado.getHandicap().toString());
			if(global.jugador_seleccionado.getFecha_nacimiento() != null)
				lblFecha.setText(gen_fecha + formato_fecha.format(global.jugador_seleccionado.getFecha_nacimiento()));
			lblPartidosJugados.setText(gen_partidos + global.jugador_seleccionado.getPartidos_jugados().size());		
			//Promedios
			lblPromedioUltimo.setText(gen_promedioUltimo + global.jugador_seleccionado.promedioUltimoPartido());
			lblPromedioTodos.setText(gen_promedioTodos + global.jugador_seleccionado.promedioGeneral());
		}
	}
}
