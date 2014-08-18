package GUI;

import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecomendacionPopup extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6733821951707675946L;
	private static Inscribirse pantalla_inscripcion;
	private JTextField campoNombre;
	private JTextField campoApellido;
	private JTextField campoEmail;
	/**
	 * Create the dialog.
	 */
	public RecomendacionPopup(GlobalParameters global, JDialog caller) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		        // Perhaps ask user if they want to save any unsaved files first.
		    	pantalla_inscripcion.setEnabled(true); 
		    }
		});
		setResizable(false);
		setTitle("Recomendar Amigos");
		pantalla_inscripcion = (Inscribirse) caller;
		setBounds(100, 100, 293, 172);
		getContentPane().setLayout(null);
		
		JButton btnRecomendar = new JButton("Recomendar");
		btnRecomendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//TODO: Generar Propuesta a Admin
				
				pantalla_inscripcion.terminate();
				dispose();
			}
		});
		btnRecomendar.setBounds(10, 112, 127, 23);
		getContentPane().add(btnRecomendar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		    	pantalla_inscripcion.setEnabled(true); 
		    	dispose();
			}
		});
		btnCancelar.setBounds(188, 112, 89, 23);
		getContentPane().add(btnCancelar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 11, 267, 90);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre(s)");
		lblNombre.setBounds(10, 11, 85, 14);
		panel.add(lblNombre);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 61, 85, 14);
		panel.add(lblEmail);
		
		JLabel lblApellido = new JLabel("Apellido(s)");
		lblApellido.setBounds(10, 36, 85, 14);
		panel.add(lblApellido);
		
		campoNombre = new JTextField();
		campoNombre.setBounds(105, 8, 152, 20);
		panel.add(campoNombre);
		campoNombre.setColumns(10);
		
		campoApellido = new JTextField();
		campoApellido.setBounds(105, 33, 152, 20);
		panel.add(campoApellido);
		campoApellido.setColumns(10);
		
		campoEmail = new JTextField();
		campoEmail.setColumns(10);
		campoEmail.setBounds(105, 58, 152, 20);
		panel.add(campoEmail);

	}
}
