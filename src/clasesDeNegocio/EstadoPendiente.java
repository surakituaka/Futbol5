package clasesDeNegocio;

import java.util.Calendar;
import java.util.List;

public class EstadoPendiente implements IEstadoEquipo {

	private String estado_base = "Pendiente";
	private String estado_equipos = "Con Equipos Tentativos";
	private IEstadoEquipo siguiente = new EstadoConfirmado();
	private String estado_vencido = "Vencido";
	
	public String getEstado(Partido partido) {	
		if(partido.getFecha().before(Calendar.getInstance().getTime()))
			return estado_vencido;
		if(equiposCorrectos(partido.getEquipo1(),partido.getEquipo2(), partido.getInscripciones()))
			return estado_equipos;
		return estado_base;
	}
	
	private boolean equiposCorrectos(Equipo equipo1, Equipo equipo2, List<Inscripcion> inscripciones){
		
		int cantidad_jugadores_correctos = 0;
		for(int i=0; i < inscripciones.size();i++){
			for(int j=0; j < equipo1.getJugadores().size();j++){
				if(equipo1.getJugadores().get(j).getUsuario().equals(inscripciones.get(i).getJugador_inscripto().getUsuario()))
					cantidad_jugadores_correctos++;
			}
			for(int j=0; j < equipo2.getJugadores().size();j++){
				if(equipo2.getJugadores().get(j).getUsuario().equals(inscripciones.get(i).getJugador_inscripto().getUsuario()))
					cantidad_jugadores_correctos++;
			}
		}
		
		if(cantidad_jugadores_correctos == 10)
			return true;
		return false;
	}

	public String getSiguienteEstado(Partido partido) {
		if(partido.getFecha().before(Calendar.getInstance().getTime()))
			return " ";
		if(this.getEstado(partido).matches(estado_base))
			return estado_equipos;
		return siguiente.getEstado(partido);
	}
}


