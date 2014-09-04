package GUI;

import javax.swing.JFrame;

public abstract class VentanaTheGrid extends JFrame {
	private static final long serialVersionUID = 1L;
	static Global global_init;
	GlobalParameters global;
	VentanaTheGrid yo;
	
	public VentanaTheGrid(){
		super();
		yo = this;
	}
	
	public void terminate(){
		global.pantalla_anterior.setVisible(true);
		dispose();		
	}
	public void habilitarPantalla(VentanaTheGrid ventana){
		ventana.setVisible(true);
		this.setVisible(false);
	}
}
