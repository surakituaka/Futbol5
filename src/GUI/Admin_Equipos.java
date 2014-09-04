package GUI;

import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;

import auxiliares.CriterioRenderer;
import clasesDeNegocio.CriterioOrden;
import clasesDeNegocio.IGeneradorEquipos;
import clasesDeNegocio.Partido;
import clasesDeNegocio.Promedio;

public class Admin_Equipos extends VentanaTheGrid {
	private static final long serialVersionUID = 5894712599675922771L;
	
	//Componentes no alterables
	private JPanel contentPanel = new JPanel();
	JPanel panel = new JPanel();
	private JLabel lblGenerar;
	private JLabel lblCriterios;
	private JLabel lblEquipo1;
	private JLabel lblEquipo2;
	
	//Componentes Modificables
	private JButton btnGenerar;
	private JButton btnVolver;
	
	private JComboBox<String> comboEquipos = new JComboBox<String>();
	
	private DefaultListModel<String> listaEquipo1;
	private DefaultListModel<String> listaEquipo2;
	private JList<CriterioOrden> lista_crit;
	
	private JScrollPane scroll_e1;
	private JScrollPane scroll_e2;
	private JScrollPane scroll_crit;

	//Atributos
	private IGeneradorEquipos generador_equipo;
	private Partido partido_elegido;

	//Constructor
	public Admin_Equipos(GlobalParameters caller, Partido partido) {
		global = caller; //Inicializamos el valor global
		partido_elegido = partido;
		//Creamos la JFrame y los paneles
		setResizable(false);
		setTitle("Gestionar Equipos");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
				((Admin_Partidos) global.pantalla_anterior).llenarDatosPartido();
		    	global.pantalla_anterior.setVisible(true); 
		    }
		});
		setBounds(100, 100, 282, 401);
		getContentPane().setLayout(null);

		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 154, 256, 177);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		//Creamos las listas
		listaEquipo1 = new DefaultListModel<String>();
		listaEquipo2 = new DefaultListModel<String>();
		DefaultListModel<CriterioOrden> listaCriterios = new DefaultListModel<CriterioOrden>();

		JList<String> lista_e1 = new JList<String>(listaEquipo1);
		scroll_e1 = new JScrollPane(lista_e1);
		scroll_e1.setBounds(10, 36, 113, 130);
		panel.add(scroll_e1);
	
		JList<String> lista_e2 = new JList<String>(listaEquipo2);
		scroll_e2 = new JScrollPane(lista_e2);
		scroll_e2.setBounds(134, 36, 113, 130);
		panel.add(scroll_e2);
		
		lista_crit = new JList<CriterioOrden>(listaCriterios);
		lista_crit.setCellRenderer(new CriterioRenderer());
		scroll_crit = new JScrollPane(lista_crit);
		scroll_crit.setBounds(144, 10, 122, 99);
		getContentPane().add(scroll_crit);
				
		for(int i=0;i<global.getCriterios().size();i++) //Cargamos los criterios en la lista
			listaCriterios.addElement(global.getCriterios().get(i));
				
		//Crear Labels
		
		lblCriterios = new JLabel("Ordenar Por:");
		lblGenerar = new JLabel("Generar Equipo Por:");
		lblEquipo1 = new JLabel("Equipo 1");
		lblEquipo2 = new JLabel("Equipo 2");

		lblCriterios.setBounds(10, 11, 124, 14);
		lblGenerar.setBounds(10, 36, 122, 14);
		lblEquipo1.setBounds(10, 11, 113, 14);
		lblEquipo2.setBounds(134, 11, 113, 14);

		getContentPane().add(lblCriterios);
		getContentPane().add(lblGenerar);
		panel.add(lblEquipo1);
		panel.add(lblEquipo2);
		lblGenerar.setLabelFor(comboEquipos);
		lblEquipo1.setLabelFor(lista_e1);
		lblEquipo2.setLabelFor(lista_e2);

		lblEquipo1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEquipo2.setHorizontalAlignment(SwingConstants.CENTER);

		//Creamos los combos
		
		comboEquipos.setBounds(10, 61, 124, 20);

		getContentPane().add(comboEquipos);
		
		//inicializemos los combos
		for(int i=0;i<global.getGeneradoresEquipos().size();i++)
			comboEquipos.addItem(global.getGeneradoresEquipos().get(i));
		if(comboEquipos.getSelectedIndex() > -1)
			generador_equipo = global.getGeneradorEquiposByName((String) comboEquipos.getSelectedItem());
		//Creamos los botones
		
		btnGenerar = new JButton("Generar Equipo");
		btnGenerar.setBounds(10, 120, 256, 23);
		getContentPane().add(btnGenerar);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(177, 342, 89, 23);
		getContentPane().add(btnVolver);
		
		//Comportamiento de las combos
		
		comboEquipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboEquipos.getSelectedIndex() > -1)
					generador_equipo = global.getGeneradorEquiposByName((String) comboEquipos.getSelectedItem());			
			}
		});
		
		//Comportamiento del boton
		
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if((lista_crit.getSelectedValuesList().size() == 0) || (comboEquipos.getSelectedIndex() == -1)){
					JOptionPane.showMessageDialog(null, "Elija un criterio y un Generador de Equipos valido", "Error el Generar Equipos", JOptionPane.PLAIN_MESSAGE);
					return;
				}
		
				for(int i=0;i<lista_crit.getSelectedValuesList().size();i++) {
					int promedio;
					if(lista_crit.getSelectedValuesList().get(i).quienSoy().matches("Promedio")){
						String response = JOptionPane.showInputDialog(null, "Ingrese la cantidad de Partidos a promediar", "Generar Equipos", JOptionPane.PLAIN_MESSAGE);
						try {
						    promedio = Integer.parseInt(response);
						} catch(NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Cantidad inválida", "Error el Generar Equipos", JOptionPane.PLAIN_MESSAGE);
							return;
						}
						((Promedio) lista_crit.getSelectedValuesList().get(i)).setCantidad_partidos(promedio);
					}
				}
				CriterioOrden[] criterios = new CriterioOrden[lista_crit.getSelectedValuesList().size()];
				lista_crit.getSelectedValuesList().toArray(criterios);
				global.administrador.organizar_equipo(partido_elegido, criterios);
				global.administrador.dividir_equipos(partido_elegido, generador_equipo);
				actualizarListas();
			}
		});
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				terminate();
			}
		});
	}
	private void actualizarListas(){
		listaEquipo1.removeAllElements();
		listaEquipo2.removeAllElements();
		
		for(int i=0;i<partido_elegido.getEquipo1().getJugadores().size();i++)
			listaEquipo1.addElement(partido_elegido.getEquipo1().getJugadores().get(i).getUsuario());
		for(int j=0;j<partido_elegido.getEquipo2().getJugadores().size();j++)
			listaEquipo2.addElement(partido_elegido.getEquipo2().getJugadores().get(j).getUsuario());		
	}
	public void terminate(){
		((Admin_Partidos) global.pantalla_anterior).llenarDatosPartido();
		global.pantalla_anterior.setVisible(true);
		dispose();
	}
}

