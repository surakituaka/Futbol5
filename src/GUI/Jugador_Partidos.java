package GUI;

import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;

import auxiliares.PartidoRenderer;
import clasesDeNegocio.Partido;

public class Jugador_Partidos extends VentanaTheGrid {
	private static final long serialVersionUID = -8708965644427177781L;

	private DefaultListModel<Partido> modelo_lista;
	private JScrollPane scroll_lista;
	private JList<Partido> lista_partidos;
	private JButton btnVolver;
	private JLabel lblEstado;
	private JLabel campoEstado;
	
	public Jugador_Partidos(GlobalParameters caller) {
		global = caller; //Seteamos el global
		
		//Creamos el JFrame principal
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		        global.pantalla_anterior.setVisible(true); 
		    }
		});
		setResizable(false);
		setBounds(100, 100, 279, 144);
		getContentPane().setLayout(null);
		setTitle("Partidos");
		
		//Creamos la lista de partidos
		modelo_lista = new DefaultListModel<Partido>();
		lista_partidos = new JList<Partido>(modelo_lista);
		lista_partidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista_partidos.setCellRenderer(new PartidoRenderer());
		scroll_lista = new JScrollPane(lista_partidos);
		scroll_lista.setBounds(10, 10, 122, 99);
		getContentPane().add(scroll_lista);
		for(int i=0;i<global.jugador_seleccionado.getInscripciones().size();i++)
			modelo_lista.addElement(global.jugador_seleccionado.getInscripciones().get(i).getPartido_inscripto());
		
		//Crear botones y labels
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(175, 86, 88, 23);
		getContentPane().add(btnVolver);
		
		lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(143, 11, 120, 23);
		getContentPane().add(lblEstado);
		
		campoEstado = new JLabel("");
		campoEstado.setBounds(142, 45, 121, 28);
		campoEstado.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(campoEstado);
		
		//Comportamiento del boton
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				terminate();
			}
		});
		
		//Comportamiento de la lista
		lista_partidos.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Partido partido_seleccionado = lista_partidos.getSelectedValue();
				if(partido_seleccionado != null){
					campoEstado.setText(partido_seleccionado.getEstado());
				}
			}
		});
	}
}
