package GUI;

import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clasesDeNegocio.Calificacion;
import clasesDeNegocio.Penalizacion;

public class Jugador_PopUpCalificacionesRealizadas extends VentanaTheGrid {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private SimpleDateFormat formato_fecha = new SimpleDateFormat("dd/MM/yy");
	
	public Jugador_PopUpCalificacionesRealizadas(GlobalParameters caller) {
		setType(Type.UTILITY);
		global = caller; //Seteamos el atributo global
		
		//Definimos la Jframe Principal
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		        global.pantalla_anterior.setEnabled(true); 
		    }
		});
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Calificaciones");
		setBounds(100, 100, 464, 316);
		getContentPane().setLayout(null);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		    global.pantalla_anterior.setEnabled(true); 
			yo.dispose();
			}
		});
		btnVolver.setBounds(359, 253, 89, 23);
		getContentPane().add(btnVolver);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 25, 417, 208);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 417, 208);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
				obtenerCalificaciones(),
			new String[] {
				"Partido", "Usuario", "Fecha", "Calificaci\u00F3n", "Critica"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, true, false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(98);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(114);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(210);
		scrollPane.setViewportView(table);
	}
	
	private String[][] obtenerCalificaciones() {
		List<Calificacion> calificaciones = global.jugador_seleccionado.getCalificaciones();
		int cantCalificaciones = calificaciones.size();
		String[][] tabla = new String[cantCalificaciones][5];
		for(int i=0; i< cantCalificaciones;i++){
			tabla[i][0] = calificaciones.get(i).getPartido().getPartido_nombre();
			tabla[i][1] = calificaciones.get(i).getCalificado().getUsuario();
			tabla[i][2] = formato_fecha.format(calificaciones.get(i).getFecha());
			tabla[i][3] = calificaciones.get(i).getCalificacion().toString();
			tabla[i][4] = calificaciones.get(i).getCritica();
		}
		return tabla;
	}
}
