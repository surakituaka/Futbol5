package GUI;

import java.awt.event.WindowEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.text.SimpleDateFormat;
import java.util.List;
import clasesDeNegocio.Penalizacion;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Jugador_Penalizaciones extends VentanaTheGrid {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private SimpleDateFormat formato_fecha = new SimpleDateFormat("dd/MM/yy");
	private SimpleDateFormat formato_hora = new SimpleDateFormat("hh:mm");
	
	public Jugador_Penalizaciones(GlobalParameters caller) {
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
		setTitle("Penalizaciones");
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
				obtenerPenalizaciones(),
			new String[] {
				"Fecha", "Hora", "Motivo"
			}
		) {
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
		table.getColumnModel().getColumn(0).setPreferredWidth(94);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(56);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(250);
		scrollPane.setViewportView(table);
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
}
