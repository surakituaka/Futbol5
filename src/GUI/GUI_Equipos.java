package GUI;

import java.awt.FlowLayout;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JButton;

public class GUI_Equipos extends JDialog {

	private static final long serialVersionUID = 5894712599675922771L;
	private final JPanel contentPanel = new JPanel();
	private static MainAdmin pantalla_admin;

	/**
	 * Create the dialog.
	 */
	public GUI_Equipos(GlobalParameters global) {
		setResizable(false);
		setTitle("Gestionar Equipos");
		pantalla_admin = (MainAdmin) global.pantalla_anterior;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		        // Perhaps ask user if they want to save any unsaved files first.
		    	pantalla_admin.setEnabled(true); 
		    }
		});
		setBounds(100, 100, 352, 241);
		getContentPane().setLayout(null);
		
		JLabel lblPartido = new JLabel("Partido");
		lblPartido.setBounds(10, 11, 103, 14);
		getContentPane().add(lblPartido);
		
		JComboBox comboPartidos = new JComboBox();
		comboPartidos.setBounds(72, 8, 262, 20);
		getContentPane().add(comboPartidos);
		
		JList list = new JList();
		list.setBounds(10, 77, 139, 129);
		getContentPane().add(list);
		
		JLabel lblNuevocriterio = new JLabel("Nuevo Criterio:");
		lblNuevocriterio.setBounds(159, 120, 124, 14);
		getContentPane().add(lblNuevocriterio);
		
		JLabel lblCriterios = new JLabel("Criterios");
		lblCriterios.setBounds(10, 52, 46, 14);
		getContentPane().add(lblCriterios);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(159, 181, 89, 23);
		getContentPane().add(btnAgregar);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(159, 145, 124, 20);
		getContentPane().add(comboBox);
		
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

	}
}
