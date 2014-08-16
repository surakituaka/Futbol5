package club;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Admin {
	
	private List<Propuesta>nuevas_propuestas = new ArrayList<Propuesta>();
	private Set<Partido> partidos_validados = new HashSet<Partido>();
	
	
	public List<Propuesta> getNuevas_propuestas() {
		return nuevas_propuestas;
	}

	public void setNuevas_propuestas(List<Propuesta> nuevas_propuestas) {
		this.nuevas_propuestas = nuevas_propuestas;
	}
	
	public Set<Partido> getPartidos_validados() {
		return partidos_validados;
	}

	public void setPartidos_validados(Set<Partido> partidos_validados) {
		this.partidos_validados = partidos_validados;
	}

	public void agregar_propuesta(Propuesta propuesta){
		this.nuevas_propuestas.add(propuesta);
	}
	
	public void aprobar_propuesta(Propuesta propuesta){
		propuesta.getJugador().agregar_amigo(propuesta.getAmigo());
		propuesta.getAmigo().agregar_amigo(propuesta.getJugador());
		propuesta.getAmigo().inscribirse_a(propuesta.getPartido());
	}
	
	public Respuesta rechazar_propuesta(Propuesta propuesta, String mensaje){
		Respuesta rta = new Respuesta();
		rta.setEsta_inscripto(false);
		rta.setMensaje(mensaje);
		this.nuevas_propuestas.remove(propuesta);
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
