package GUI;

import java.awt.Color;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.jbundle.thin.base.screen.jcalendarbutton.JCalendarButton;

import clasesDeNegocio.Jugador;

public abstract class VentanaTheGrid extends JFrame {
	private static final long serialVersionUID = 1L;
	static Global global_init;
	public GlobalParameters global;
	VentanaTheGrid yo;
    private DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
	
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
	
	public Color colorJugador(Jugador jugador){
		if(jugador.imBlue())
			return Color.BLUE;
		else
			return Color.BLACK;
	}

    public void setDate(JTextField textField, JCalendarButton boton, Date date)
    {
        String dateString = "";
        if (date != null)
    		dateString = dateFormat.format(date);
        textField.setText(dateString);
        boton.setTargetDate(date);
    }
}

