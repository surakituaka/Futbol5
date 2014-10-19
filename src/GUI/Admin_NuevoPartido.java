package GUI;

import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clasesDeNegocio.Partido;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jbundle.thin.base.screen.jcalendarbutton.JCalendarButton;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Admin_NuevoPartido extends VentanaTheGrid {
	private static final long serialVersionUID = 1L;
	private JTextField textNombre;
	private JTextField textFecha;
	private JTextField textLugar;

	//Atributos
	private SimpleDateFormat formato_fecha = new SimpleDateFormat("dd/MM/yy");
    private JCalendarButton calendario;
    private JButton btnVolver;
    private JButton btnCrear;
    private JPanel panel;
	
	public Admin_NuevoPartido(GlobalParameters caller) {
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
		setBounds(100, 100, 254, 205);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 11, 228, 120);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 11, 76, 20);
		panel.add(lblNombre);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(10, 40, 46, 14);
		panel.add(lblFecha);
		
		JLabel lblLugar = new JLabel("Lugar");
		lblLugar.setBounds(10, 68, 46, 14);
		panel.add(lblLugar);
		
		textNombre = new JTextField();
		textNombre.setBounds(96, 11, 122, 20);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		textFecha = new JTextField();
		textFecha.setBounds(96, 40, 86, 20);
		panel.add(textFecha);
		textFecha.setEnabled(false);
		textFecha.setColumns(10);
		
		textLugar = new JTextField();
		textLugar.setBounds(96, 65, 122, 20);
		panel.add(textLugar);
		textLugar.setColumns(10);
		
		calendario = new JCalendarButton();
		calendario.setBounds(188, 40, 30, 20);
		panel.add(calendario);
		calendario.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
		    public void propertyChange(java.beans.PropertyChangeEvent evt) {
		        if (evt.getNewValue() instanceof Date)
		            setDate(textFecha, calendario, (Date)evt.getNewValue());
		    }
		});
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				terminate();
			}
		});
		btnVolver.setBounds(149, 142, 89, 23);
		getContentPane().add(btnVolver);
		
		btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Partido partido_nuevo = new Partido(global.administrador.getMensajero());
				if((!textNombre.getText().matches("")) && (!textLugar.getText().matches("")) && (!textFecha.getText().matches("")))	{
					try {
						partido_nuevo.setFecha(formato_fecha.parse(textFecha.getText()));
					} catch (ParseException e) {
						JOptionPane.showMessageDialog(null, "Fecha Ingresada Erronea", "Error al Crear Partido", JOptionPane.ERROR_MESSAGE);
						terminate();
					}
					partido_nuevo.setPartido_nombre(textNombre.getText());
					partido_nuevo.setLugar(textLugar.getText());
					global.agregarPartido(partido_nuevo);
					((Admin_Partidos)global.pantalla_anterior).addPartido(partido_nuevo);
					terminate();
				}
				else
					JOptionPane.showMessageDialog(null, "Datos Ingresados Erroneos", "Error al Crear Partido", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnCrear.setBounds(10, 142, 89, 23);
		getContentPane().add(btnCrear);
		
	}
	/*Partido partido_nuevo = new Partido(global.administrador.getMensajero());
	String nombre_partido = JOptionPane.showInputDialog(null, "Ingrese un nombre para el Partido", "Crear Partido", JOptionPane.PLAIN_MESSAGE);
	String lugar = JOptionPane.showInputDialog(null, "Ingrese el lugar del Partido", "Crear Partido", JOptionPane.PLAIN_MESSAGE);			
	String fecha_string = JOptionPane.showInputDialog(null, "Ingrese la fecha del Partido (dd/MM/yy)", "Crear Partido", JOptionPane.PLAIN_MESSAGE);
	Date fecha;
	if((nombre_partido != null) && (lugar != null) && (fecha_string != null))	{
		try {
			fecha = formato_fecha.parse(fecha_string);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Fecha Ingresada Erronea", "Error al Crear Partido", JOptionPane.ERROR_MESSAGE);
			return;
		}
		partido_nuevo.setFecha(fecha);
		partido_nuevo.setPartido_nombre(nombre_partido);
		partido_nuevo.setLugar(lugar);
		partido_nuevo.setMensajero(global.mensajero);
		global.agregarPartido(partido_nuevo);
		comboPartidos.addItem(partido_nuevo.getPartido_nombre());
	}
	else
		JOptionPane.showMessageDialog(null, "Datos Ingresados Erroneos", "Error al Crear Partido", JOptionPane.ERROR_MESSAGE);					*/
}
