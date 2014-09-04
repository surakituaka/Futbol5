package clasesDeNegocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Jugador extends Usuario{
		
	private String nombre;
	private String apellido;
	private Date fecha_nacimiento;
	private IModalidad tipo;
	private List<Penalizacion> penalizaciones = new ArrayList<Penalizacion>();
	//private Double prioridad;
	private List<Amigo> amigos = new ArrayList<Amigo>();
	private IOrden proposicion;
	private Integer handicap = 1; //Por defecto todos tienen 1
	private List<Partido>partidos_inscriptos = new ArrayList<Partido>();
	private List<Calificacion>calificaciones = new ArrayList<Calificacion>();

	
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
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
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
	public List<Amigo> getAmigos() {
		return amigos;
	}
	public void setAmigos(List<Amigo> amigos) {
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
	public List<Partido> getPartidos_inscriptos() {
		return partidos_inscriptos;
	}
	public void setPartidos_inscriptos(List<Partido> partidos_inscriptos) {
		this.partidos_inscriptos = partidos_inscriptos;
	}
	
	public List<Calificacion> getCalificaciones() {
		return calificaciones;
	}
	public void setCalificaciones(List<Calificacion> calificaciones) {
		this.calificaciones = calificaciones;
	}
	public Calificacion remover_calificacion(Calificacion calificacion) {
		int index = calificaciones.indexOf(calificacion);
		return calificaciones.remove(index);
		
	}
	public void agregar_calificacion(Calificacion calificacion) {
		calificaciones.add(calificacion);
	}
	/*
	public Double getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Double prioridad) {
		this.prioridad = prioridad;
	}
*/	
	public void agregar_inscripcion(Partido partido){
		this.partidos_inscriptos.add(partido);
	}

	public Partido quitar_inscripcion(Partido partido){
		int i = this.partidos_inscriptos.indexOf(partido);
		return this.partidos_inscriptos.remove(i);
	}
	public void agregar_amigo(Amigo amigo){
		this.amigos.add(amigo);
	}
	
	public Amigo quitar_amigo(Amigo amigo){
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
		Respuesta respuesta;
		if(estoyInscripto(partido)){
			respuesta = new Respuesta();
			respuesta.setEsta_inscripto(true);
			respuesta.setMensaje("El jugador ya esta inscripto");
			return respuesta;	
		}
		respuesta = partido.inscribir(this);
		if(respuesta.getEsta_inscripto()) {
			agregar_inscripcion(partido);
			for(Amigo amigo:getAmigos()){
				partido.enviar_mensaje("Partido "+partido.getId(), amigo.getEmail(), "tu amigo "+getNombre()+" ingreso a un partido");
			}
		}
		return respuesta;
	}
	
	public void bajarse_de(Partido partido){
		partido.quitar_jugador(this);
		darBajaLista(partido);
		this.agregar_penalizacion(new Penalizacion(new GregorianCalendar(),"por dejar un partido sin reemplazo"));
	}
	
	public void bajarse_de(Partido partido,Jugador reemplazo){
		partido.quitar_jugador(this);
		darBajaLista(partido);
		reemplazo.setTipo(this.getTipo());
		partido.agregar_jugador(reemplazo);
	}
	
	public void proponer_jugador(Amigo amigo, Partido partido){
		this.proposicion.orden(amigo,partido,this);
	}
	
	public void calificar(Jugador calificado, Integer calificacion, String critica, Partido partido){
		this.agregar_calificacion(new Calificacion(this, calificado, calificacion, critica, partido,new GregorianCalendar()));
	}
	
	public boolean estoyInscripto(Partido partido) {
		boolean respuesta = false;
		for(int i=0;i<partidos_inscriptos.size();i++){
			if(partidos_inscriptos.get(i).getId().matches(partido.getId()))
				respuesta = true;
		}
		return respuesta;
	}
	
	public void setTipofromString(String tipo_string, String condicion) {
		if(tipo_string.equals("Standar"))
			this.setTipo(new Standar());
		if(tipo_string.equals("Condicional")) 
			this.setTipo(new Condicional(condicion));
		if(tipo_string.equals("Solidaria"))
			this.setTipo(new Solidaria());
	}

	public List<Partido> getPartidos_jugados() {
		List<Partido>partidos_jugados = new ArrayList<Partido>();
		for(int i=0;i< this.getPartidos_inscriptos().size();i++){
			if(this.getPartidos_inscriptos().get(i).estaConfirmado())
				partidos_jugados.add(this.getPartidos_inscriptos().get(i));
		}
		return partidos_jugados;
	}
	
	public List<Calificacion> getCalificacionesByPartido(Partido partido) {
		List<Calificacion> calificaciones = new ArrayList<Calificacion>();
		for(int i=0;i< this.getCalificaciones().size();i++){
			if(this.getCalificaciones().get(i).getPartido().getId().matches(partido.getId()))
				calificaciones.add(this.getCalificaciones().get(i));
		}
		return calificaciones;
	}
	
	
	public List<Jugador> getNoCalificados(Partido partido){ 
		List<Jugador> jugadores_noCalificados = new ArrayList<Jugador>();
		List<Calificacion> calificaciones_partido = getCalificacionesByPartido(partido); //Lista de los calificados
		for(int i=0;i<partido.getJugadores().size();i++) 
			if(!partido.getJugadores().get(i).getUsuario().matches(getUsuario())){ //Si no soy yo prosigo
				jugadores_noCalificados.add(partido.getJugadores().get(i)); //Agrego al jugador a la lista
				for(int j=0;j<calificaciones_partido.size();j++)//Este jugador, posee alguna calificacion?
					if(calificaciones.get(j).estaCalificado(partido.getJugadores().get(i))) 
						jugadores_noCalificados.remove(partido.getJugadores().get(i));//Si esta calificado, me arrepiento y lo saco
			}
		return jugadores_noCalificados;
	}
	
	public void darBajaLista(Partido partido) {
		int index = getPartidos_inscriptos().indexOf(partido);
		getPartidos_inscriptos().remove(index);
	}
	
	public boolean yaExisteAmigo(Amigo amigo){
		for(int i=0;i<amigos.size();i++)
			if(amigos.get(i).concuerdoCon(amigo))
				return true;
		return false;
	}
}
