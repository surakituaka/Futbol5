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
import clasesDeNegocio.*;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private JPanel panel_1;

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
		setBounds(100, 100, 295, 401);
		getContentPane().setLayout(null);

		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 154, 269, 177);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		//Creamos las listas
		listaEquipo1 = new DefaultListModel<String>();
		listaEquipo2 = new DefaultListModel<String>();
		DefaultListModel<CriterioOrden> listaCriterios = new DefaultListModel<CriterioOrden>();

		final JList<String> lista_e1 = new JList<String>(listaEquipo1);
		lista_e1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
		        if (evt.getClickCount() == 2) {
		        	String nombre = listaEquipo1.elementAt(lista_e1.locationToIndex(evt.getPoint()));
		            mostrarVentana(nombre);
		        }
			}
		});
		scroll_e1 = new JScrollPane(lista_e1);
		scroll_e1.setBounds(10, 36, 113, 130);
		panel.add(scroll_e1);
	
		final JList<String> lista_e2 = new JList<String>(listaEquipo2);
		lista_e2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
		        if (evt.getClickCount() == 2) {
		        	String nombre = listaEquipo2.elementAt(lista_e2.locationToIndex(evt.getPoint()));
		            mostrarVentana(nombre);
		        }
			}
		});
		scroll_e2 = new JScrollPane(lista_e2);
		scroll_e2.setBounds(146, 36, 113, 130);
		panel.add(scroll_e2);
		
		lista_crit = new JList<CriterioOrden>(listaCriterios);
		lista_crit.setCellRenderer(new CriterioRenderer());
		scroll_crit = new JScrollPane(lista_crit);
		scroll_crit.setBounds(157, 9, 122, 99);
		getContentPane().add(scroll_crit);
				
		for(int i=0;i<global.getCriterios().size();i++) //Cargamos los criterios en la lista
			listaCriterios.addElement(global.getCriterios().get(i));
				
		//Crear Labels
		
		lblCriterios = new JLabel("Ordenar Por:");
		lblCriterios.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEquipo1 = new JLabel("Equipo 1");
		lblEquipo2 = new JLabel("Equipo 2");

		lblCriterios.setBounds(10, 11, 137, 14);
		lblEquipo1.setBounds(10, 11, 113, 14);
		lblEquipo2.setBounds(146, 11, 113, 14);

		getContentPane().add(lblCriterios);
		panel.add(lblEquipo1);
		panel.add(lblEquipo2);
		lblEquipo1.setLabelFor(lista_e1);
		lblEquipo2.setLabelFor(lista_e2);

		lblEquipo1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEquipo2.setHorizontalAlignment(SwingConstants.CENTER);
		
		//inicializemos los combos
		for(int i=0;i<global.getGeneradoresEquipos().size();i++)
			comboEquipos.addItem(global.getGeneradoresEquipos().get(i));
		if(comboEquipos.getSelectedIndex() > -1)
			generador_equipo = global.getGeneradorEquiposByName((String) comboEquipos.getSelectedItem());
		//Creamos los botones
		
		btnGenerar = new JButton("Generar Equipo");
		btnGenerar.setBounds(10, 120, 269, 23);
		getContentPane().add(btnGenerar);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(190, 342, 89, 23);
		getContentPane().add(btnVolver);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 36, 137, 72);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		comboEquipos.setBounds(8, 32, 124, 20);
		panel_1.add(comboEquipos);
		lblGenerar = new JLabel("Generar Equipo Por:");
		lblGenerar.setBounds(10, 7, 122, 14);
		panel_1.add(lblGenerar);
		lblGenerar.setLabelFor(comboEquipos);
		
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
	protected void mostrarVentana(String nombre) {
		Visualizacion_Jugadores datos_pantalla = new Visualizacion_Jugadores(new GlobalParameters(global,global.getJugadorByUsuario(nombre), yo));
		habilitarPantalla(datos_pantalla);
		// TODO Auto-generated method stub
		
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

