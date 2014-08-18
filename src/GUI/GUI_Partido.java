package GUI;

import java.awt.FlowLayout;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import club.*;

public class GUI_Partido extends JDialog {

	private static final long serialVersionUID = 6057447328239256306L;
	private final JPanel contentPanel = new JPanel();
	private static MainAdmin pantalla_admin;
	private final JButton btnNuevoPartido;
	private final JComboBox<String> comboPartidos;
	private static Partido partido_a_eliminar;
	/**
	 * Create the dialog.
	 */
	public GUI_Partido(final GlobalParameters global) {
		setResizable(false);
		setTitle("Gestionar Partidos");
		pantalla_admin = (MainAdmin) global.pantalla_anterior;
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		        // Perhaps ask user if they want to save any unsaved files first.
				pantalla_admin.setEnabled(true);
		    }
		});
		setBounds(100, 100, 204, 148);
		getContentPane().setLayout(null);
		
		btnNuevoPartido = new JButton("Nuevo Partido");
		btnNuevoPartido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Partido partido_nuevo = new Partido();
				String nombre_partido = JOptionPane.showInputDialog(null, "Ingrese un nombre para el Partido", "Crear Partido", JOptionPane.PLAIN_MESSAGE);
				String lugar = JOptionPane.showInputDialog(null, "Ingrese el lugar del Partido", "Crear Partido", JOptionPane.PLAIN_MESSAGE);			
				String fecha_string = JOptionPane.showInputDialog(null, "Ingrese la fecha del Partido", "Crear Partido", JOptionPane.PLAIN_MESSAGE);
				if((nombre_partido != null) && (lugar != null) && (fecha_string != null))	{
					try {
						SimpleDateFormat dateformatter = new SimpleDateFormat("dd-MM-yyyy");
						Date fecha = dateformatter.parse(fecha_string);
						partido_nuevo.setFecha(fecha);
						partido_nuevo.setId(nombre_partido);
						partido_nuevo.setLugar(lugar);
						global.agregarPartido(partido_nuevo);
						comboPartidos.addItem(partido_nuevo.getId());
						pantalla_admin.setEnabled(true);
						dispose();
					} catch (ParseException e) {
						JOptionPane.showMessageDialog(null, "Fecha Ingresada Erronea", "Error al Crear Partido", JOptionPane.ERROR_MESSAGE);
						//TODO: Tratar que el formato de la fecha sea erroneo
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Datos Ingresados Erroneos", "Error al Crear Partido", JOptionPane.ERROR_MESSAGE);				
			}
		});
		btnNuevoPartido.setBounds(10, 11, 178, 23);
		getContentPane().add(btnNuevoPartido);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 45, 178, 64);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		comboPartidos = new JComboBox<String>();
		int i = 0;
		while(i < global.partidos.size()) {
			comboPartidos.addItem(global.partidos.get(i).getId());
			i++;
		}
		comboPartidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id_partido = (String) comboPartidos.getSelectedItem();
				int i = 0;
				while(i < global.partidos.size()){
					if(global.partidos.get(i).getId().equals(id_partido))
						partido_a_eliminar = global.partidos.get(i);
					i++;
				}
			}
		});
		comboPartidos.setBounds(10, 6, 158, 20);
		panel.add(comboPartidos);
		
		JButton btnEliminarPartido = new JButton("Eliminar Partido");
		btnEliminarPartido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(global.eliminarPartido(partido_a_eliminar))
					comboPartidos.removeItem(partido_a_eliminar.getId());
			}
		});
		btnEliminarPartido.setBounds(10, 32, 158, 23);
		panel.add(btnEliminarPartido);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));	
	}
}
