package club;

import java.util.Collections;
import java.util.Date;

public class Solidaria implements IModalidad{

	private final String inscripcion ="SOLIDARIA";
	private Date fecha;
	
	
	public Date getFecha() {
		return fecha;
	}
	
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	@Override
	public String getInscripcion() {
		return inscripcion;
	}
	
	@Override
	public Respuesta inscribirse(Partido partido, Jugador jugador) {
		
		Respuesta respuesta = new Respuesta();
		
		if(!jugador.getPenalizaciones().isEmpty()){
			respuesta.setEsta_inscripto(false);
			respuesta.setMensaje("El jugador esta penalizado. No puede jugar.");
			return respuesta;
		}
		
		if(partido.getJugadores().size()==10){
			Collections.sort(partido.getJugadores());
			if((partido.getJugadores()).get(partido.getJugadores().size()-1).getTipo().getInscripcion().equals("CONDICIONAL")){
				partido.quitar_jugador(partido.getJugadores().get(partido.getJugadores().size()-1));
				partido.agregar_jugador(jugador);
				
				for(Jugador amigo:jugador.getAmigos()){
					partido.enviar_mensaje("partido", amigo.getNombre(), "tu amigo "+jugador.getNombre()+" ingreso a un partido");
				}
				
				respuesta.setEsta_inscripto(true);
				respuesta.setMensaje("El jugador esta inscripto.");
				return respuesta;
			}else{
				respuesta.setEsta_inscripto(false);
				respuesta.setMensaje("El jugador no ha sido inscipto, no hay mas cupos. Pruebe con una inscripcion de mayor prioridad.");
				return respuesta;
			}
			
		}else{
			partido.agregar_jugador(jugador);
			if(partido.getJugadores().size()==10){
				partido.enviar_mensaje("partido", "admin", "partido completo");
			}
			
			for(Jugador amigo:jugador.getAmigos()){
				partido.enviar_mensaje("partido", amigo.getNombre(), "tu amigo "+jugador.getNombre()+" ingreso a un partido");
			}
			
			respuesta.setEsta_inscripto(true);
			respuesta.setMensaje("El jugador esta inscripto.");
			return respuesta;
		}
		
		
		
		
	}
}
