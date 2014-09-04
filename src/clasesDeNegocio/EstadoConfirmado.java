package clasesDeNegocio;

import java.util.Calendar;

public class EstadoConfirmado implements IEstadoEquipo {

	private String estado = "Confirmado";
	private String estado_vencido = "Vencido";
	
	public String getEstado(Partido partido) {
		// TODO Auto-generated method stub
		if(partido.getFecha().before(Calendar.getInstance().getTime()))
			return estado_vencido;
		return estado;
	}
	
	public String getSiguienteEstado(Partido partido) {
		return " ";
	}
}
