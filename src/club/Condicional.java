package club;

import java.util.Date;

public class Condicional implements IModalidad{
	
	private final String inscripcion ="CONDICIONAL";
	private String condicion;
	private Date fecha;
	
	@Override
	public Respuesta inscribirse(Partido partido, Jugador jugador) {
		
		Respuesta respuesta = new Respuesta();
		
		if(!jugador.getPenalizaciones().isEmpty()){
			respuesta.setEsta_inscripto(false);
			respuesta.setMensaje("El jugador esta penalizado. No puede jugar.");
			return respuesta;
		}
		
		if(partido.getJugadores().size()==10){
			respuesta.setEsta_inscripto(false);
			respuesta.setMensaje("El jugador no ha sido inscipto, no hay mas cupos. Pruebe con una inscripcion de mayor prioridad.");
			return respuesta;
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

	@Override
	public String getInscripcion() {
		return inscripcion;
	}

	public String getCondicion() {
		return condicion;
	}
	
	
	public Date getFecha() {
		return fecha;
	}

	
	public void setFecha(Date fecha) {
		this.fecha = fecha;		
	}
	
	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}
	
	
	public Condicional(String condicion){
		
		super();
		setCondicion(condicion);
	}

	
	
	
}
