package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GUI_Equipos extends JDialog {

	private static final long serialVersionUID = 5894712599675922771L;
	private final JPanel contentPanel = new JPanel();
	private static MainAdmin pantalla_admin;

	/**
	 * Create the dialog.
	 */
	public GUI_Equipos(MainAdmin caller) {
		setResizable(false);
		setTitle("Gestionar Equipos");
		pantalla_admin = caller;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(WindowEvent winEvt) {
		        // Perhaps ask user if they want to save any unsaved files first.
		    	pantalla_admin.setEnabled(true); 
		    }
		});
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

	}

}
