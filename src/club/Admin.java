package club;

import java.util.HashMap;
import java.util.Map;

public class Admin {
	
	private Map<Jugador,Partido>nuevas_propuestas = new HashMap<Jugador,Partido>();

	public Map<Jugador,Partido> getNuevas_propuestas() {
		return nuevas_propuestas;
	}

	public void setNuevas_propuestas(Map<Jugador,Partido> nuevas_propuestas) {
		this.nuevas_propuestas = nuevas_propuestas;
	}
	
	public void agregar_propuesta(Jugador jugador, Partido partido){
		this.nuevas_propuestas.put(jugador, partido);
	}
	
	public void aprobar_propuesta(Jugador jugador, IModalidad tipo){
		jugador.setTipo(tipo);
		Partido partido = this.nuevas_propuestas.get(jugador);
		jugador.inscribirse_a(partido);
	}
	
	public Respuesta rechazar_propuesta(Jugador jugador, String mensaje){
		Respuesta rta = new Respuesta();
		rta.setEsta_inscripto(false);
		rta.setMensaje(mensaje);
		this.nuevas_propuestas.remove(jugador);
		return rta;
	}
	
	
}
