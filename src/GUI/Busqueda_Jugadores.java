package GUI;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;

import org.jbundle.thin.base.screen.jcalendarbutton.JCalendarButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
    
    public Busqueda_Jugadores(GlobalParameters caller) {
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
		setTitle("B\u00FAsqueda de Jugador");
		setBounds(100, 100, 480, 442);
		getContentPane().setLayout(null);      
		
		panel = new JPanel();
		panel.setToolTipText("");
		panel.setBounds(10, 11, 454, 156);
		getContentPane().add(panel);
		panel.setLayout(null);
			
		//Creemos las Labels
		
		titulo = new JLabel("Filtros de Busqueda");
		titulo.setBounds(10, 11, 434, 14);
		panel.add(titulo);
		
		lblNombre = new JLabel("Comienza por:");
		lblNombre.setBounds(10, 32, 175, 14);
		panel.add(lblNombre);		
		
		lblNacimiento = new JLabel("Fecha de Nacimiento anterior a:");
		lblNacimiento.setBounds(10, 57, 175, 14);
		panel.add(lblNacimiento);
		
		lblHandicap = new JLabel("Handicap:");
		lblHandicap.setBounds(10, 82, 114, 14);
		panel.add(lblHandicap);
		
		lblInfracciones = new JLabel("Infracciones:");
		lblInfracciones.setBounds(10, 132, 114, 14);
		panel.add(lblInfracciones);
		
		lblPromedios = new JLabel("Promedios");
		lblPromedios.setBounds(10, 107, 114, 14);
		panel.add(lblPromedios);
		
		//Creemos los Campos de Texto		

		textNacimiento = new JTextField();
		textNacimiento.setBounds(280, 54, 114, 20);
		panel.add(textNacimiento);
		textNacimiento.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setBounds(280, 29, 114, 20);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		textHandicap = new JTextField();
		textHandicap.setBounds(280, 79, 114, 20);
		panel.add(textHandicap);
		textHandicap.setColumns(10);
		
		textPromedios = new JTextField();
		textPromedios.setBounds(280, 104, 114, 20);
		panel.add(textPromedios);
		textPromedios.setColumns(10);
					
		//Creemos y setiemos las Combos		
		
		comboHandicap = new JComboBox<String>();
		comboHandicap.setBounds(156, 79, 114, 20);
		panel.add(comboHandicap);
		comboHandicap.addItem("desde");
		comboHandicap.addItem("hasta");
		comboHandicap.setSelectedIndex(-1);
		
		comboPromedios = new JComboBox<String>();
		comboPromedios.setBounds(156, 104, 114, 20);
		panel.add(comboPromedios);
		comboPromedios.addItem("desde");
		comboPromedios.addItem("hasta");
		comboPromedios.setSelectedIndex(-1);
		
		comboInfracciones = new JComboBox<String>();
		comboInfracciones.setBounds(156, 129, 238, 20);
		panel.add(comboInfracciones);
		comboInfracciones.addItem("Jugadores CON Infraccciones");
		comboInfracciones.addItem("Jugadores SIN Infraccciones");
		comboInfracciones.addItem("Todos los Jugadores");
		comboInfracciones.setSelectedIndex(-1);
		
		//Creemos los demas Elementos
		
		calendario = new JCalendarButton();
        calendario.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                if (evt.getNewValue() instanceof Date)
                    setDate(textNacimiento, calendario, (Date)evt.getNewValue());
            }
        });
        calendario.setBounds(404,54,40,20);
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
		btnLimpiar.setBounds(375, 178, 89, 23);
		getContentPane().add(btnLimpiar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(10, 178, 89, 23);
		getContentPane().add(btnBuscar);
		
		table = new JTable();
		table.setBounds(10, 226, 454, 142);
		table.setRowSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setPreferredScrollableViewportSize(new Dimension(265,100));
		table.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane);
		
		
	}
    
}
