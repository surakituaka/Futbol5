package GUI;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.SwingConstants;

import org.jbundle.thin.base.screen.jcalendarbutton.JCalendarButton;

import auxiliares.RenderizarConAzul;
import clasesDeNegocio.Jugador;


public class Busqueda_Jugadores extends VentanaTheGrid {
	private static final long serialVersionUID = 1L;

	//TextFields
	private JTextField textNacimiento;
    private JTextField textNombre;
    private JTextField textHandicap;
    private JTextField textPromedios;
    //Labels
    private JTable table;
    private JLabel titulo;
    private JLabel lblNombre;
    private JLabel lblNacimiento;
    private JLabel lblHandicap;
    private JLabel lblInfracciones;
    private JLabel lblPromedios;
    //Combos
    private JComboBox<String> comboHandicap;
    private JComboBox<String> comboPromedios;
    private JComboBox<String> comboInfracciones;
    //Extras
    private JButton btnVolver;
    private JButton btnLimpiar;
    private JButton btnBuscar;
    private JCalendarButton calendario;
    private JPanel panel;
    private JScrollPane scrollPane;
    private JPanel panel_tabla;
    
    public Busqueda_Jugadores(GlobalParameters caller) {
		global = caller; //Seteamos el atributo global
		
		//Definimos la Jframe Principal
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		        global.pantalla_anterior.setVisible(true); 
		    }
		});
		setResizable(false);
		setTitle("B\u00FAsqueda de Jugador");
		setBounds(100, 100, 480, 442);
		getContentPane().setLayout(null);      
		
		panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setToolTipText("");
		panel.setBounds(10, 11, 454, 170);
		getContentPane().add(panel);
		panel.setLayout(null);
			
		//Creemos las Labels
		
		titulo = new JLabel("Filtros de Busqueda");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(10, 11, 434, 20);
		panel.add(titulo);
		
		lblNombre = new JLabel("Comienza por:");
		lblNombre.setBounds(10, 42, 175, 14);
		panel.add(lblNombre);		
		
		lblNacimiento = new JLabel("Fecha de Nacimiento anterior a:");
		lblNacimiento.setBounds(10, 67, 175, 14);
		panel.add(lblNacimiento);
		
		lblHandicap = new JLabel("Handicap:");
		lblHandicap.setBounds(10, 92, 114, 14);
		panel.add(lblHandicap);
		
		lblInfracciones = new JLabel("Infracciones:");
		lblInfracciones.setBounds(10, 142, 114, 14);
		panel.add(lblInfracciones);
		
		lblPromedios = new JLabel("Promedios");
		lblPromedios.setBounds(10, 117, 114, 14);
		panel.add(lblPromedios);
		
		//Creemos los Campos de Texto		

		textNacimiento = new JTextField();
		textNacimiento.setEditable(false);
		textNacimiento.setBounds(280, 64, 114, 20);
		panel.add(textNacimiento);
		textNacimiento.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setBounds(280, 39, 114, 20);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		textHandicap = new JTextField();
		textHandicap.setBounds(280, 89, 114, 20);
		panel.add(textHandicap);
		textHandicap.setColumns(10);
		
		textPromedios = new JTextField();
		textPromedios.setBounds(280, 114, 114, 20);
		panel.add(textPromedios);
		textPromedios.setColumns(10);
					
		//Creemos y setiemos las Combos		
		
		comboHandicap = new JComboBox<String>();
		comboHandicap.setBounds(156, 89, 114, 20);
		panel.add(comboHandicap);
		comboHandicap.addItem("desde");
		comboHandicap.addItem("hasta");
		comboHandicap.setSelectedIndex(-1);
		
		comboPromedios = new JComboBox<String>();
		comboPromedios.setBounds(156, 114, 114, 20);
		panel.add(comboPromedios);
		comboPromedios.addItem("desde");
		comboPromedios.addItem("hasta");
		comboPromedios.setSelectedIndex(-1);
		
		comboInfracciones = new JComboBox<String>();
		comboInfracciones.setBounds(156, 139, 238, 20);
		panel.add(comboInfracciones);
		comboInfracciones.addItem("Todos los Jugadores");
		comboInfracciones.addItem("Jugadores CON Infraccciones");
		comboInfracciones.addItem("Jugadores SIN Infraccciones");
		comboInfracciones.setSelectedIndex(0);
		
		//Creemos los demas Elementos
		
		calendario = new JCalendarButton();
        calendario.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                if (evt.getNewValue() instanceof Date)
                    setDate(textNacimiento, calendario, (Date)evt.getNewValue());
            }
        });
        calendario.setBounds(404,64,40,20);
        panel.add(calendario);
				
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				terminate();
			}
		});
		btnVolver.setBounds(375, 379, 89, 23);
		getContentPane().add(btnVolver);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarTodo();
			}
		});
		btnLimpiar.setBounds(375, 192, 89, 23);
		getContentPane().add(btnLimpiar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Jugador> lista = buscar();
				mostrarLista(lista);
			}
		});
		btnBuscar.setBounds(10, 192, 89, 23);
		getContentPane().add(btnBuscar);
		
		panel_tabla = new JPanel();
		panel_tabla.setBounds(10, 226, 454, 144);
		getContentPane().add(panel_tabla);
		
		table = new JTable() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) { return false;}
		};
		table.setCellSelectionEnabled(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Usuario", "Nombre", "Handicap", "Promedio"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(78);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(54);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(54);
		TableCellRenderer base =  table.getDefaultRenderer(String.class);
		TableCellRenderer nuevo_renderer = new RenderizarConAzul(yo,base);	
		table.setDefaultRenderer(String.class, nuevo_renderer);
		table.setRowSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setPreferredScrollableViewportSize(new Dimension(440,100));
		table.setFillsViewportHeight(true);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if(evt.getClickCount() == 2) {
					JTable target = (JTable) evt.getSource();
					int row = target.getSelectedRow();
					Jugador jugador = global.getJugadorByUsuario((String) target.getValueAt(row, 0));
					Visualizacion_Jugadores nueva_ventana = new Visualizacion_Jugadores(new GlobalParameters(global, jugador, yo));
					habilitarPantalla(nueva_ventana);
				}
			}
		});
		scrollPane = new JScrollPane(table);
		panel_tabla.add(scrollPane);
	}
    
    public void limpiarTodo()
    {
    	//Reseteamos las Combos
		comboHandicap.setSelectedIndex(-1);
		comboPromedios.setSelectedIndex(-1);
		comboInfracciones.setSelectedIndex(0);
		//Reseteamos los TextBox
		textNacimiento.setText("");
	    textNombre.setText("");
	    textHandicap.setText("");
	    textPromedios.setText("");
	    //Limpia la tabla
	    ((DefaultTableModel) table.getModel()).setRowCount(0);

    }
    public List<Jugador> buscar()
    {
    	List<Jugador> lista = global.jugadores;
    	if(!textNombre.getText().matches(""))
    		lista = filtrarNombre(lista, textNombre.getText());
    	if(!textNacimiento.getText().matches(""))
    		lista = filtrarNacimiento(lista, textNacimiento.getText());
    	if((!textHandicap.getText().matches("")) && (comboHandicap.getSelectedIndex() > -1))
    		lista = filtrarHandicap(lista,(String) comboHandicap.getSelectedItem(), textHandicap.getText());
    	if((!textPromedios.getText().matches("")) && (comboPromedios.getSelectedIndex() > -1))
    		lista = filtrarPromedios(lista,(String) comboPromedios.getSelectedItem(), textPromedios.getText());
    	if(comboInfracciones.getSelectedIndex() > 0)
    		lista = filtrarInfracciones(lista, (String) comboInfracciones.getSelectedItem());
  
    	return lista;
    }

	private List<Jugador> filtrarInfracciones(List<Jugador> lista, String forma) {
		List<Jugador> lista_filtrada = new ArrayList<Jugador>();
		for(int i = 0; i < lista.size(); i++)
		{
			if(forma == "Jugadores CON Infraccciones"){
				if(lista.get(i).getPenalizaciones().size() > 0)
					lista_filtrada.add(lista.get(i));
			}
			else
				if(lista.get(i).getPenalizaciones().size() == 0)
					lista_filtrada.add(lista.get(i));
		}
		return lista_filtrada;
	}

	private List<Jugador> filtrarPromedios(List<Jugador> lista, String forma, String promedio) {
		List<Jugador> lista_filtrada = new ArrayList<Jugador>();
		int valor_promedio;
		try {
			valor_promedio = Integer.parseInt(promedio);
		} 
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "El Promedio ingresado es invalido", "Error Buscar", JOptionPane.ERROR_MESSAGE);				
			return lista_filtrada;
		}
		for(int i = 0; i < lista.size(); i++)
		{
			if(forma == "desde"){
				if(lista.get(i).promedioUltimoPartido() >= valor_promedio)
					lista_filtrada.add(lista.get(i));
			}
			else
				if(lista.get(i).promedioUltimoPartido() <= valor_promedio)
					lista_filtrada.add(lista.get(i));
		}
		return lista_filtrada;
	}

	private List<Jugador> filtrarHandicap(List<Jugador> lista, String forma, String handicap) {
		List<Jugador> lista_filtrada = new ArrayList<Jugador>();
		int valor_handicap;
		try {
			valor_handicap = Integer.parseInt(handicap);
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "El Promedio ingresado es invalido", "Error Buscar", JOptionPane.ERROR_MESSAGE);				
			return lista_filtrada;
		}
		for(int i = 0; i < lista.size(); i++)
		{
			if(forma == "desde"){
				if(lista.get(i).getHandicap() >= valor_handicap)
					lista_filtrada.add(lista.get(i));
			}
			else
				if(lista.get(i).getHandicap() <= valor_handicap)
					lista_filtrada.add(lista.get(i));	
		}
		return lista_filtrada;
	}

	private List<Jugador> filtrarNacimiento(List<Jugador> lista, String fecha) {
		List<Jugador> lista_filtrada = new ArrayList<Jugador>();
		SimpleDateFormat formato_fecha = new SimpleDateFormat("dd/MM/yy");
		Date fecha_anterior = null;
		try {
			fecha_anterior = formato_fecha.parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < lista.size(); i++)
		{
			if(lista.get(i).getFecha_nacimiento() != null)
				if(lista.get(i).getFecha_nacimiento().before(fecha_anterior))
					lista_filtrada.add(lista.get(i));
		}
		return lista_filtrada;
	}

	private List<Jugador> filtrarNombre(List<Jugador> lista, String parteNombre) {
		List<Jugador> lista_filtrada =  new ArrayList<Jugador>();
		for(int i = 0;i < lista.size(); i++)
		{
			if(lista.get(i).getNombre().startsWith(parteNombre))
				lista_filtrada.add(lista.get(i));
		}
		return lista_filtrada;
	}
	
	public void mostrarLista(List<Jugador> lista) {
		((DefaultTableModel) table.getModel()).setRowCount(0);
		int cantJugadores = lista.size();
		for(int i=0; i< cantJugadores;i++){	
			String[] datos = new String[4];
			datos[0] = lista.get(i).getUsuario();
			datos[1] = lista.get(i).getNombre() + " " + lista.get(i).getApellido();
			datos[2] = lista.get(i).getHandicap().toString();
			datos[3] = Integer.toString(lista.get(i).promedioGeneral());
			((DefaultTableModel) table.getModel()).addRow(datos);
		}
		table.setPreferredScrollableViewportSize(new Dimension(435,150));
		// TODO Auto-generated method stub
		
	}
    
	
}
