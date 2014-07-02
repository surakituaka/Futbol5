package club;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Admin {
	
	private Map<Jugador,Partido>nuevas_propuestas = new HashMap<Jugador,Partido>();
	private Set<Partido> partidos_validados = new HashSet<Partido>();
	
	
	public Map<Jugador,Partido> getNuevas_propuestas() {
		return nuevas_propuestas;
	}

	public void setNuevas_propuestas(Map<Jugador,Partido> nuevas_propuestas) {
		this.nuevas_propuestas = nuevas_propuestas;
	}
	
	public Set<Partido> getPartidos_validados() {
		return partidos_validados;
	}

	public void setPartidos_validados(Set<Partido> partidos_validados) {
		this.partidos_validados = partidos_validados;
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
	
	public void organizar_equipo(Partido partido, CriterioOrden[] criterios){
		Collections.sort(partido.getJugadores(),new CriterioCompuesto(criterios));
	}
	
	public void agregar_partido_validado(Partido partido){
		getPartidos_validados().add(partido);
	}
	
	public void quitar_partido_validado(Partido partido){
		getPartidos_validados().remove(partido);
	}
	
	public void dividir_equipos(Partido partido, IGeneradorEquipos generador){
		generador.generar_equipos(partido);
	}
	
	
}
