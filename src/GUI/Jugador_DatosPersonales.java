package GUI;

import javax.swing.*;
import javax.swing.border.*;

import org.jbundle.thin.base.screen.jcalendarbutton.JCalendarButton;

import clasesManejadoras.ConexionDB;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Jugador_DatosPersonales extends VentanaTheGrid {
	private static final long serialVersionUID = -7377006624212654507L;

	//Componentes no cambiantes
	private JLabel lblNombre;
	private JLabel lblEmail;
	private JLabel lblNacimiento;
	private JLabel lblApellido;
	private JPanel panel;
	private JLabel lblUsuario;
	private JLabel lblPassword;
	private JLabel lblPasswordConfirmacion;

	//Componentes modificables
	
	private JButton btnGuardar;
	private JButton btnCancelar;
	
	private JTextField campoNombre;
	private JTextField campoNacimiento;
	private JTextField campoApellido;
	private JTextField campoUsuario;
	private JTextField campoEmail;

	private JPasswordField campoPassword;
	private JPasswordField campoPasswordConfirmacion;

	//Atributos
	private SimpleDateFormat formato_fecha = new SimpleDateFormat("dd/MM/yy");
    private JCalendarButton calendario;
	
	//Constructor
	public Jugador_DatosPersonales(GlobalParameters caller) {
		global = caller; //Seteamos el atributo global
		
		//Definimos la Jframe Principal
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		        global.pantalla_anterior.setVisible(true); 
		    }
		});
		setResizable(false);
		setTitle("Datos Personales");
		setBounds(100, 100, 318, 344);
		getContentPane().setLayout(null);		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 157, 292, 91);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		//Creemos los botones

		btnGuardar = new JButton("Guardar");
		btnCancelar = new JButton("Cancelar");

		btnGuardar.setBounds(10, 259, 89, 23);
		btnCancelar.setBounds(212, 259, 89, 23);

		getContentPane().add(btnGuardar);
		getContentPane().add(btnCancelar);
		
		//Creemos los campos de texto
		
		campoPassword = new JPasswordField();
		campoPasswordConfirmacion = new JPasswordField();

		campoPassword.setBounds(143, 33, 139, 20);
		campoPasswordConfirmacion.setBounds(143, 58, 139, 20);

		panel.add(campoPassword);
		panel.add(campoPasswordConfirmacion);
		
		campoNombre = new JTextField();
		campoApellido = new JTextField();
		campoEmail = new JTextField();
		campoNacimiento = new JTextField();
		campoNacimiento.setEditable(false);
		campoUsuario = new JTextField();

		campoNombre.setColumns(10);
		campoApellido.setColumns(10);
		campoEmail.setColumns(10);
		campoNacimiento.setColumns(10);
		campoUsuario.setColumns(10);

		campoNombre.setBounds(163, 11, 139, 20);
		campoApellido.setBounds(163, 36, 139, 20);
		campoEmail.setBounds(163, 61, 139, 20);
		campoNacimiento.setBounds(163, 92, 139, 20);
		campoUsuario.setBounds(143, 8, 139, 20);

		getContentPane().add(campoNombre);
		getContentPane().add(campoApellido);
		getContentPane().add(campoEmail);
		getContentPane().add(campoNacimiento);
		panel.add(campoUsuario);

		campoUsuario.setEditable(false);

		//Creemos las Labels
		
		lblNombre = new JLabel("Nombre");
		lblApellido = new JLabel("Apellido");
		lblEmail = new JLabel("E-mail");
		lblNacimiento = new JLabel("Fecha de Nacimiento");
		lblUsuario = new JLabel("Usuario");
		lblPassword = new JLabel("Contrase\u00F1a");
		lblPasswordConfirmacion = new JLabel("Repita Contrase\u00F1a");

		
		lblNombre.setBounds(10, 14, 46, 14);
		lblApellido.setBounds(10, 39, 126, 14);
		lblEmail.setBounds(10, 64, 46, 14);
		lblNacimiento.setBounds(10, 95, 126, 14);
		lblUsuario.setBounds(10, 11, 93, 14);
		lblPassword.setBounds(10, 36, 126, 14);
		lblPasswordConfirmacion.setBounds(10, 61, 126, 14);


		getContentPane().add(lblNombre);		
		getContentPane().add(lblApellido);
		getContentPane().add(lblEmail);
		getContentPane().add(lblNacimiento);

		panel.add(lblUsuario);
		panel.add(lblPassword);
		panel.add(lblPasswordConfirmacion);

		//Carguemos los datos en los campos de text
		
		campoNombre.setText(global.jugador_seleccionado.getNombre());
		campoApellido.setText(global.jugador_seleccionado.getApellido());
		campoEmail.setText(global.jugador_seleccionado.getEmail());
		if(global.jugador_seleccionado.getFecha_nacimiento() != null)
			campoNacimiento.setText(formato_fecha.format(global.jugador_seleccionado.getFecha_nacimiento()));
		campoUsuario.setText(global.jugador_seleccionado.getUsuario());
		
		calendario = new JCalendarButton();
		calendario.setBounds(163, 123, 139, 23);
		getContentPane().add(calendario);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnPenalizaciones = new JMenu("Penalizaciones");
		mnPenalizaciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Penalizaciones PopUp
				Jugador_Penalizaciones ventana = new Jugador_Penalizaciones(new GlobalParameters(global, global.jugador_seleccionado, yo));
				habilitarPantalla(ventana);
			}
		});
	menuBar.add(mnPenalizaciones);
		
		JMenu mnCalificaciones = new JMenu("Calificaciones Realizadas");
		mnCalificaciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Jugador_PopUpCalificacionesRealizadas ventana = new Jugador_PopUpCalificacionesRealizadas(new GlobalParameters(global, global.jugador_seleccionado, yo));
				habilitarPantalla(ventana);
			}
		});
		menuBar.add(mnCalificaciones);
        calendario.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                if (evt.getNewValue() instanceof Date)
                    setDate(campoNacimiento, calendario, (Date)evt.getNewValue());
            }
        });
		
		//Comportamiento de los botones
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String password;
				if((password = nuevaPassCoincide()) == null){
					JOptionPane.showMessageDialog(null, "Las Contrase\u00F1as no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
					return;				
				}
				if(!campoNacimiento.getText().equals(""))
					try {	
						Date fecha = formato_fecha.parse(campoNacimiento.getText());
						global.jugador_seleccionado.setFecha_nacimiento(fecha);
					} catch (ParseException e) {
						JOptionPane.showMessageDialog(null, "Fecha Ingresada Erronea", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				if(!password.equals(""))
					global.jugador_seleccionado.setPassword(password);
				if(!campoNombre.getText().equals(""))
					global.jugador_seleccionado.setNombre(campoNombre.getText());
				if(!campoApellido.getText().equals(""))
					global.jugador_seleccionado.setApellido(campoApellido.getText());
				if(!campoEmail.getText().equals(""))
					global.jugador_seleccionado.setEmail(campoEmail.getText());//En alguna iteracion posterior se validara el formato
				ConexionDB.guardar(global.jugador_seleccionado);
				terminate();				
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				terminate();
			}
		});
		
	}		
	
	private String nuevaPassCoincide() {
		String pass_1 = new String(campoPassword.getPassword());
		String pass_2 = new String(campoPasswordConfirmacion.getPassword());
		if(pass_1.equals(pass_2))
			return pass_1;
		return null;
	}
	
	public void habilitarPantalla(VentanaTheGrid ventana) {
		ventana.setVisible(true);
		this.setEnabled(false);
	}
}
