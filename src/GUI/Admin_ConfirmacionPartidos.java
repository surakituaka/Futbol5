package GUI;

import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import clasesDeNegocio.Jugador;
import clasesDeNegocio.Partido;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Admin_ConfirmacionPartidos extends VentanaTheGrid {
	private static final long serialVersionUID = -8202945325005603430L;
	
	//Componentes no alterables
	private JPanel panel;
	private JLabel lblPartidos;
	private JLabel lblTitulo;
	private JLabel lblEquipo1;
	private JLabel lblEquipo2;
	
	//Componentes Modificables
	private JCheckBox e1_j1;
	private JCheckBox e1_j2;
	private JCheckBox e1_j3;
	private JCheckBox e1_j4;
	private JCheckBox e1_j5;
	private JCheckBox e2_j1;
	private JCheckBox e2_j2;
	private JCheckBox e2_j3;
	private JCheckBox e2_j4;
	private JCheckBox e2_j5;
	
	private JComboBox<String> comboPartidos;
	private JButton btnConfirmar;
	private JButton btnVolver;
	
	//Atributos
	Partido partido_seleccionado = null;

	//Constructor
	public Admin_ConfirmacionPartidos(GlobalParameters caller){
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
		setTitle("Confirmar Partidos");
		setBounds(100, 100, 318, 308);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 72, 292, 163);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		//Creemos las labels
			
		lblPartidos = new JLabel("Partidos a Confirmar");
		lblPartidos.setBounds(10, 13, 132, 14);
		getContentPane().add(lblPartidos);
		
		lblTitulo = new JLabel("Confirme los jugadores que asistieron al partido.");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 47, 292, 14);
		getContentPane().add(lblTitulo);
		
		lblEquipo1 = new JLabel("Equipo 1");
		lblEquipo1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEquipo1.setBounds(10, 11, 126, 14);
		panel.add(lblEquipo1);
		
		lblEquipo2 = new JLabel("Equipo 2");
		lblEquipo2.setHorizontalAlignment(SwingConstants.CENTER);
		lblEquipo2.setBounds(160, 11, 126, 14);
		panel.add(lblEquipo2);
		
		//Creemos las checkBox
		
		e1_j1 = new JCheckBox();
		e1_j1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 2){
					JCheckBox source = (JCheckBox) arg0.getSource();
					if(!source.getText().matches(""))
						mostrarDetalleJugador(source.getText());
				}
			}
		});
		e1_j1.setBounds(6, 29, 130, 23);
		panel.add(e1_j1);
		
		e2_j1 = new JCheckBox();
		e2_j1.setBounds(156, 29, 130, 23);
		e2_j1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 2){
					JCheckBox source = (JCheckBox) arg0.getSource();
					if(!source.getText().matches(""))
						mostrarDetalleJugador(source.getText());
				}
			}
		});
		panel.add(e2_j1);
		
		e1_j2 = new JCheckBox();
		e1_j2.setBounds(6, 55, 130, 23);
		e1_j2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 2){
					JCheckBox source = (JCheckBox) arg0.getSource();
					if(!source.getText().matches(""))
						mostrarDetalleJugador(source.getText());
				}
			}
		});
		panel.add(e1_j2);
		
		e1_j3 = new JCheckBox();
		e1_j3.setBounds(6, 81, 130, 23);
		e1_j3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 2){
					JCheckBox source = (JCheckBox) arg0.getSource();
					if(!source.getText().matches(""))
						mostrarDetalleJugador(source.getText());
				}
			}
		});
		panel.add(e1_j3);
		
		e1_j4 = new JCheckBox();
		e1_j4.setBounds(6, 107, 130, 23);
		e1_j4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 2){
					JCheckBox source = (JCheckBox) arg0.getSource();
					if(!source.getText().matches(""))
						mostrarDetalleJugador(source.getText());
				}
			}
		});
		panel.add(e1_j4);
		
		e1_j5 = new JCheckBox();
		e1_j5.setBounds(6, 133, 130, 23);
		e1_j5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 2){
					JCheckBox source = (JCheckBox) arg0.getSource();
					if(!source.getText().matches(""))
						mostrarDetalleJugador(source.getText());
				}
			}
		});
		panel.add(e1_j5);
		
		e2_j2 = new JCheckBox();
		e2_j2.setBounds(156, 55, 130, 23);
		e2_j2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 2){
					JCheckBox source = (JCheckBox) arg0.getSource();
					if(!source.getText().matches(""))
						mostrarDetalleJugador(source.getText());
				}
			}
		});
		panel.add(e2_j2);
		
		e2_j3 = new JCheckBox();
		e2_j3.setBounds(156, 81, 130, 23);
		e2_j3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 2){
					JCheckBox source = (JCheckBox) arg0.getSource();
					if(!source.getText().matches(""))
						mostrarDetalleJugador(source.getText());
				}
			}
		});
		panel.add(e2_j3);
		
		e2_j4 = new JCheckBox();
		e2_j4.setBounds(156, 107, 130, 23);
		e2_j4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 2){
					JCheckBox source = (JCheckBox) arg0.getSource();
					if(!source.getText().matches(""))
						mostrarDetalleJugador(source.getText());
				}
			}
		});
		panel.add(e2_j4);
		
		e2_j5 = new JCheckBox();
		e2_j5.setBounds(156, 133, 130, 23);
		e2_j5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 2){
					JCheckBox source = (JCheckBox) arg0.getSource();
					if(!source.getText().matches(""))
						mostrarDetalleJugador(source.getText());
				}
			}
		});
		panel.add(e2_j5);
		
		//Creemos la combo y los botones 
		
		comboPartidos = new JComboBox<String>();
		lblPartidos.setLabelFor(comboPartidos);
		comboPartidos.setBounds(170, 10, 132, 20);
		getContentPane().add(comboPartidos);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(10, 246, 89, 23);
		getContentPane().add(btnConfirmar);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(213, 246, 89, 23);
		getContentPane().add(btnVolver);
		
		//Carguemos los valores a la combo y a las labels
		
		for(int i=0;i<global.partidos.size();i++)
			if(global.partidos.get(i).tieneEquipos())
				comboPartidos.addItem(global.partidos.get(i).getPartido_nombre());
		
		if(comboPartidos.getSelectedIndex() > -1)
			setearCheckBoxes(partido_seleccionado = global.getPartidoById((String) comboPartidos.getSelectedItem()));
		
		
		//Comportamiento de la combo
		
		comboPartidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				blanquearCheckBoxes();
				if(comboPartidos.getSelectedIndex() > -1)
					setearCheckBoxes(partido_seleccionado = global.getPartidoById((String) comboPartidos.getSelectedItem()));
			}
		});
		
		//Comportamiento de los botones
		
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(partido_seleccionado != null){
					int response = JOptionPane.showConfirmDialog(null, "Todos los jugadores no seleccionados seran penalizados por no asistir. Continuar?", "Confirmar un Partido", JOptionPane.WARNING_MESSAGE);
					if(response == JOptionPane.OK_OPTION) {
						darBajaYPenalizarNoAsistentes(partido_seleccionado);
						partido_seleccionado.confirmar();
						comboPartidos.removeItem(partido_seleccionado.getPartido_nombre());
					}				
					return;
				}
				JOptionPane.showMessageDialog(null, "Seleccione el partido a Confirmar", "Confirmar un Partido", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				terminate();
			}
		});
		
	}
	public void blanquearCheckBoxes(){
		e1_j1.setSelected(false);
		e1_j1.setText("");
		e1_j2.setSelected(false);
		e1_j2.setText("");
		e1_j3.setSelected(false);
		e1_j3.setText("");
		e1_j4.setSelected(false);		
		e1_j4.setText("");
		e1_j5.setSelected(false);
		e1_j5.setText("");

		e2_j1.setSelected(false);
		e2_j1.setText("");
		e2_j2.setSelected(false);
		e2_j2.setText("");
		e2_j3.setSelected(false);
		e2_j3.setText("");
		e2_j4.setSelected(false);
		e2_j4.setText("");
		e2_j5.setSelected(false);
		e2_j5.setText("");
	}
	public void setearCheckBoxes(Partido partido){
		e1_j1.setSelected(false);
		e1_j1.setText(partido.getEquipo1().getJugadores().get(0).getUsuario());
		e1_j1.setForeground(this.colorJugador(partido.getEquipo1().getJugadores().get(0)));
		e1_j2.setSelected(false);
		e1_j2.setText(partido.getEquipo1().getJugadores().get(1).getUsuario());
		e1_j2.setForeground(this.colorJugador(partido.getEquipo1().getJugadores().get(1)));
		e1_j3.setSelected(false);
		e1_j3.setText(partido.getEquipo1().getJugadores().get(2).getUsuario());
		e1_j3.setForeground(this.colorJugador(partido.getEquipo1().getJugadores().get(2)));
		e1_j4.setSelected(false);		
		e1_j4.setText(partido.getEquipo1().getJugadores().get(3).getUsuario());
		e1_j4.setForeground(this.colorJugador(partido.getEquipo1().getJugadores().get(3)));
		e1_j5.setSelected(false);
		e1_j5.setText(partido.getEquipo1().getJugadores().get(4).getUsuario());
		e1_j5.setForeground(this.colorJugador(partido.getEquipo1().getJugadores().get(4)));

		e2_j1.setSelected(false);
		e2_j1.setText(partido.getEquipo2().getJugadores().get(0).getUsuario());
		e2_j1.setForeground(this.colorJugador(partido.getEquipo2().getJugadores().get(0)));
		e2_j2.setSelected(false);
		e2_j2.setText(partido.getEquipo2().getJugadores().get(1).getUsuario());
		e2_j2.setForeground(this.colorJugador(partido.getEquipo2().getJugadores().get(1)));
		e2_j3.setSelected(false);
		e2_j3.setText(partido.getEquipo2().getJugadores().get(2).getUsuario());
		e2_j3.setForeground(this.colorJugador(partido.getEquipo2().getJugadores().get(2)));
		e2_j4.setSelected(false);
		e2_j4.setText(partido.getEquipo2().getJugadores().get(3).getUsuario());
		e2_j4.setForeground(this.colorJugador(partido.getEquipo2().getJugadores().get(3)));
		e2_j5.setSelected(false);
		e2_j5.setText(partido.getEquipo2().getJugadores().get(4).getUsuario());
		e2_j5.setForeground(this.colorJugador(partido.getEquipo2().getJugadores().get(4)));
	}
	public void darBajaYPenalizarNoAsistentes(Partido partido){
		if(!e1_j1.isSelected())
			bajaPenalizada(global.getJugadorByUsuario(e1_j1.getText()), partido);
		if(!e1_j2.isSelected())
			bajaPenalizada(global.getJugadorByUsuario(e1_j2.getText()), partido);
		if(!e1_j3.isSelected())
			bajaPenalizada(global.getJugadorByUsuario(e1_j3.getText()), partido);
		if(!e1_j4.isSelected())
			bajaPenalizada(global.getJugadorByUsuario(e1_j4.getText()), partido);
		if(!e1_j5.isSelected())
			bajaPenalizada(global.getJugadorByUsuario(e1_j5.getText()), partido);
		if(!e2_j1.isSelected())
			bajaPenalizada(global.getJugadorByUsuario(e2_j1.getText()), partido);
		if(!e2_j2.isSelected())
			bajaPenalizada(global.getJugadorByUsuario(e2_j2.getText()), partido);
		if(!e2_j3.isSelected())
			bajaPenalizada(global.getJugadorByUsuario(e2_j3.getText()), partido);
		if(!e2_j4.isSelected())
			bajaPenalizada(global.getJugadorByUsuario(e2_j4.getText()), partido);
		if(!e2_j5.isSelected())
			bajaPenalizada(global.getJugadorByUsuario(e2_j5.getText()), partido);			
	}
	private void bajaPenalizada(Jugador jugador, Partido partido){
		jugador.bajarse_de(partido);
	}
	
	protected void mostrarDetalleJugador(String nombre) {
		Visualizacion_Jugadores datos_pantalla = new Visualizacion_Jugadores(new GlobalParameters(global,global.getJugadorByUsuario(nombre), yo));
		habilitarPantalla(datos_pantalla);
		// TODO Auto-generated method stub
		
	}
}
