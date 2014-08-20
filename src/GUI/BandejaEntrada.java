package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BandejaEntrada extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();


	public BandejaEntrada(final GlobalParameters global) {
		final BandejaEntrada yo = this;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Bandeja de entrada");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 29, 424, 188);
		contentPanel.add(textArea);		
		textArea.setText("Acá van\r\n los diferentes\r\n mensajes que le\r\n llegan al administrador\r\n\r\n\r\n\r\n\r\nFin.");
		textArea.setEditable(false);
		
		JLabel lblMensajesEntrantes = new JLabel("Mensajes entrantes:");
		lblMensajesEntrantes.setBounds(10, 11, 200, 14);
		contentPanel.add(lblMensajesEntrantes);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Volver");
				cancelButton.addActionListener(new ActionListener() {
					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent arg0) {
						global.pantalla_anterior.enable(true);
						yo.dispose();	
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
}
