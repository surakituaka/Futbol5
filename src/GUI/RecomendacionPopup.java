package GUI;

import javax.swing.JDialog;

public class RecomendacionPopup extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6733821951707675946L;
	private static Inscribirse pantalla_inscripcion;
	/**
	 * Create the dialog.
	 */
	public RecomendacionPopup(Inscribirse caller) {
		setResizable(false);
		setTitle("Recomendar Amigos");
		pantalla_inscripcion = caller;
		setBounds(100, 100, 450, 300);

	}

}
