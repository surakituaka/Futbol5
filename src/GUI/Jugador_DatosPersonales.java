package GUI;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	private JLabel lblFormatoDate;

	//Componentes modificables
	
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnPenalizaciones;
	
	private JTextField campoNombre;
	private JTextField campoNacimiento;
	private JTextField campoApellido;
	private JTextField campoUsuario;
	private JTextField campoEmail;

	private JPasswordField campoPassword;
	private JPasswordField campoPasswordConfirmacion;

	//Atributos
	private SimpleDateFormat formato_fecha = new SimpleDateFormat("dd/MM/yy");
	
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
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Datos Personales");
		setBounds(100, 100, 318, 344);
		getContentPane().setLayout(null);		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 180, 292, 91);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		//Creemos los botones

		btnGuardar = new JButton("Guardar");
		btnCancelar = new JButton("Cancelar");
		btnPenalizaciones = new JButton("Penalizaciones");

		btnGuardar.setBounds(10, 282, 89, 23);
		btnCancelar.setBounds(212, 282, 89, 23);
		btnPenalizaciones.setBounds(163, 149, 139, 20);

		getContentPane().add(btnGuardar);
		getContentPane().add(btnCancelar);
		getContentPane().add(btnPenalizaciones);
		
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
		campoUsuario = new JTextField();

		campoNombre.setColumns(10);
		campoApellido.setColumns(10);
		campoEmail.setColumns(10);
		campoNacimiento.setColumns(10);
		campoUsuario.setColumns(10);

		campoNombre.setBounds(163, 22, 139, 20);
		campoApellido.setBounds(163, 47, 139, 20);
		campoEmail.setBounds(163, 72, 139, 20);
		campoNacimiento.setBounds(163, 97, 139, 20);
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
		lblFormatoDate = new JLabel("Formato: DD/MM/YY");

		
		lblNombre.setBounds(10, 25, 46, 14);
		lblApellido.setBounds(10, 50, 126, 14);
		lblEmail.setBounds(10, 75, 46, 14);
		lblNacimiento.setBounds(10, 100, 126, 14);
		lblUsuario.setBounds(10, 11, 93, 14);
		lblPassword.setBounds(10, 36, 126, 14);
		lblPasswordConfirmacion.setBounds(10, 61, 126, 14);
		lblFormatoDate.setBounds(163, 124, 139, 14);


		getContentPane().add(lblNombre);		
		getContentPane().add(lblApellido);
		getContentPane().add(lblEmail);
		getContentPane().add(lblNacimiento);
		getContentPane().add(lblFormatoDate);

		panel.add(lblUsuario);
		panel.add(lblPassword);
		panel.add(lblPasswordConfirmacion);
		
		lblFormatoDate.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblFormatoDate.setHorizontalAlignment(SwingConstants.CENTER);

		//Carguemos los datos en los campos de text
		
		campoNombre.setText(global.jugador_seleccionado.getNombre());
		campoApellido.setText(global.jugador_seleccionado.getApellido());
		campoEmail.setText(global.jugador_seleccionado.getEmail());
		if(global.jugador_seleccionado.getFecha_nacimiento() != null)
			campoNacimiento.setText(formato_fecha.format(global.jugador_seleccionado.getFecha_nacimiento()));
		campoUsuario.setText(global.jugador_seleccionado.getUsuario());
		
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
				terminate();				
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				terminate();
			}
		});
		
		btnPenalizaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

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
}