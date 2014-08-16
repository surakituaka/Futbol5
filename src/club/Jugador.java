package club;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

public class Jugador{
	
	private String nombre;
	private String apellido;
	private IModalidad tipo;
	private List<Penalizacion> penalizaciones = new ArrayList<Penalizacion>();
	//private Double prioridad;
	private List<Jugador> amigos = new ArrayList<Jugador>();
	private IOrden proposicion;
	private Integer handicap = null;
	private List<Partido>partidos_jugados = new ArrayList<Partido>();
	private Partido inscripto = null;
	
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public IModalidad getTipo() {
		return tipo;
	}
	
	public void setTipo(IModalidad tipo) {
		this.tipo = tipo;
	}

	public List<Penalizacion> getPenalizaciones() {
		return penalizaciones;
	}

	public void setPenalizaciones(List<Penalizacion> penalizaciones) {
		this.penalizaciones = penalizaciones;
	}
/*
	public Double getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Double prioridad) {
		this.prioridad = prioridad;
	}
*/
	public List<Jugador> getAmigos() {
		return amigos;
	}

	public void setAmigos(List<Jugador> amigos) {
		this.amigos = amigos;
	}
	
	public IOrden getPropocicion() {
		return proposicion;
	}

	public void setPropocicion(IOrden propocicion) {
		this.proposicion = propocicion;
	}

	public Integer getHandicap() {
		return handicap;
	}

	public void setHandicap(Integer handicap) {
		this.handicap = handicap;
	}

	public List<Partido> getPartidos_jugados() {
		return partidos_jugados;
	}

	public void setPartidos_jugados(List<Partido> partidos_jugados) {
		this.partidos_jugados = partidos_jugados;
	}

	public Partido getInscripto() {
		return inscripto;
	}

	public void setInscripto(Partido inscripto) {
		this.inscripto = inscripto;
	}

	public void agregar_amigo(Jugador amigo){
		this.amigos.add(amigo);
	}
	
	public Jugador quitar_amigo(Jugador amigo){
		int i = this.amigos.indexOf(amigo);
		return this.amigos.remove(i);
	}
	
	public void agregar_penalizacion(Penalizacion penalizacion){
		this.penalizaciones.add(penalizacion);
	}
	
	public Penalizacion quitar_penalizacion(Penalizacion penalizacion){
		int i = this.penalizaciones.indexOf(penalizacion);
		return this.penalizaciones.remove(i);
	}	
	
	public Respuesta inscribirse_a(Partido partido){
		
		Respuesta respuesta = new Respuesta();
		
		if(!getPenalizaciones().isEmpty() || !(getInscripto()==null)){
			respuesta.setEsta_inscripto(false);
			respuesta.setMensaje("El jugador esta penalizado. No puede jugar.");
			return respuesta;
		}
		
		Collections.sort(partido.getJugadores(),new OrdenadorJugadoresTipo());
		
		if(partido.getJugadores().size()>=10 && partido.getJugadores().get(9).getTipo().getInscripcion().equals("STANDAR")){
			
			respuesta.setEsta_inscripto(false);
			respuesta.setMensaje("El jugador no ha sido inscipto, no hay mas cupos.");
			return respuesta;
		}
			partido.agregar_jugador(this);
			
			setInscripto(partido);
			
			for(Jugador amigo:getAmigos()){
				partido.enviar_mensaje("partido", amigo.getNombre(), "tu amigo "+getNombre()+" ingreso a un partido");
			}
			
			respuesta.setEsta_inscripto(true);
			respuesta.setMensaje("El jugador esta inscripto.");
			return respuesta;
		
		//return getTipo().inscribirse(partido, this);
	}
	
	public void bajarse_de(Partido partido){
		
		Boolean hay10 = partido.validar10();
		partido.quitar_jugador(this);
		this.agregar_penalizacion(new Penalizacion(new GregorianCalendar(),"por dejar un partido sin reemplazo"));
		if(hay10){
			partido.enviar_mensaje("partido", "admin", "ya no hay 10");
		}
	}
	
	public void bajarse_de(Partido partido,Jugador reemplazo){
		
		partido.quitar_jugador(this);
		reemplazo.setTipo(this.getTipo());
		partido.agregar_jugador(reemplazo);
	}
	
	public void proponer_jugador(Jugador amigo, Partido partido){
		this.proposicion.orden(amigo,partido,this);
	}
	
	public void calificar(Jugador calificado, Integer calificacion, String critica, Partido partido){
		partido.agregar_calificacion(new Calificacion(this, calificado, calificacion, critica, partido,new GregorianCalendar()));
	}
	
	
	
	
	
	
	
	
}
