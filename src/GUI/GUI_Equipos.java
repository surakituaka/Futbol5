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
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_Equipos extends JDialog {

	private static final long serialVersionUID = 5894712599675922771L;
	private final JPanel contentPanel = new JPanel();
	private static MainAdmin pantalla_admin;

	
	private final JComboBox<String> comboPartidos;
	private final JComboBox<String> comboCriterios;
	private final JList<String> l_criterios;
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
		setBounds(100, 100, 282, 241);
		getContentPane().setLayout(null);
		
		JLabel lblPartido = new JLabel("Partido");
		lblPartido.setBounds(10, 11, 106, 14);
		getContentPane().add(lblPartido);
		
		comboPartidos = new JComboBox<String>();
		comboPartidos.setBounds(126, 8, 140, 20);
		getContentPane().add(comboPartidos);
		
		l_criterios = new JList<String>();
		l_criterios.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		l_criterios.setBounds(10, 77, 124, 129);
		getContentPane().add(l_criterios);
		
		JLabel lblNuevocriterio = new JLabel("Nuevo Criterio:");
		lblNuevocriterio.setBounds(142, 120, 124, 14);
		getContentPane().add(lblNuevocriterio);
		
		JLabel lblCriterios = new JLabel("Criterios");
		lblCriterios.setBounds(10, 52, 124, 14);
		getContentPane().add(lblCriterios);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboCriterios.getSelectedItem();
				
			}
		});
		btnAgregar.setBounds(177, 176, 89, 23);
		getContentPane().add(btnAgregar);
		
		comboCriterios = new JComboBox<String>();
		comboCriterios.setBounds(142, 145, 124, 20);
		getContentPane().add(comboCriterios);
		
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		for(int i=0;i<global.partidos.size();i++){
			comboPartidos.addItem(global.partidos.get(i).getId());
		}
		
		for(int i=0;i<global.criterios.size();i++)
			comboCriterios.addItem(global.criterios.get(i));

	}
}
