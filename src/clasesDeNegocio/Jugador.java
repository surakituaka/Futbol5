package clasesDeNegocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Jugador extends Usuario{
	
	private Long id;
	private String nombre;
	private String apellido;
	private Date fecha_nacimiento;
	@Deprecated
	private IModalidad tipo;
	private List<Penalizacion> penalizaciones = new ArrayList<Penalizacion>();
	private List<Amigo> amigos = new ArrayList<Amigo>();
	private IOrden proposicion;
	private Integer handicap = 1; //Por defecto todos tienen 1
	@Deprecated
	private List<Partido>partidos_inscriptos = new ArrayList<Partido>();
	private List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
	private List<Calificacion>calificaciones = new ArrayList<Calificacion>();

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	@Deprecated
	public IModalidad getTipo() {
		return tipo;
	}	
	@Deprecated
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
	@Deprecated
	public List<Partido> getPartidos_inscriptos() {
		return partidos_inscriptos;
	}
	@Deprecated
	public void setPartidos_inscriptos(List<Partido> partidos_inscriptos) {
		this.partidos_inscriptos = partidos_inscriptos;
	}
	
	public List<Calificacion> getCalificaciones() {
		return calificaciones;
	}
	public void setCalificaciones(List<Calificacion> calificaciones) {
		this.calificaciones = calificaciones;
	}
	public List<Inscripcion> getInscripciones() {
		return inscripciones;
	}
	public void setInscripciones(List<Inscripcion> inscripciones) {
		this.inscripciones = inscripciones;
	}
	public IOrden getProposicion() {
		return proposicion;
	}
	public void setProposicion(IOrden proposicion) {
		this.proposicion = proposicion;
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
	
	public void agregar_inscripcion(Inscripcion inscripcion){
		
		this.inscripciones.add(inscripcion);
	}

	public Inscripcion quitar_inscripcion(Partido partido){
		for(int i=0;i<inscripciones.size();i++)
			if(this.inscripciones.get(i).getPartido_inscripto().getId().equals(partido.getId()))
				return this.inscripciones.remove(i);
		return null;
	}

	public Inscripcion inscribirse_a(Partido partido, IModalidad tipo_inscripcion){
		Inscripcion inscripcion = null;
		if(estoyInscripto(partido)){
			return inscripcion;	
		}
		inscripcion = partido.inscribir(this, tipo_inscripcion);
		if(inscripcion != null) {
			agregar_inscripcion(inscripcion);
			for(Amigo amigo:getAmigos()){
				partido.enviar_mensaje("Partido "+partido.getId(), amigo.getEmail(), "tu amigo "+getNombre()+" ingreso a un partido");
			}
		}
		return inscripcion;
	}
	
	public void bajarse_de(Partido partido){
		partido.quitar_inscripcion(this);
		this.quitar_inscripcion(partido);
		this.agregar_penalizacion(new Penalizacion(new GregorianCalendar(),"por dejar un partido sin reemplazo"));
	}
	
	public void bajarse_de(Partido partido,Jugador reemplazo){
		partido.reemplazarJugadores(this, reemplazo);
		this.quitar_inscripcion(partido);
	}
	
	public void proponer_jugador(Amigo amigo, Partido partido){
		this.proposicion.orden(amigo,partido,this);
	}
	
	public void calificar(Jugador calificado, Integer calificacion, String critica, Partido partido){
		this.agregar_calificacion(new Calificacion(this, calificado, calificacion, critica, partido,new GregorianCalendar()));
	}
	
	public boolean estoyInscripto(Partido partido) {
		boolean respuesta = false;
		for(int i=0;i<getInscripciones().size();i++){
			if(getInscripciones().get(i).getPartido_inscripto().getId().equals(partido.getId()))
				respuesta = true;
		}
		return respuesta;
	}
	
	public IModalidad getTipofromString(String tipo_string, String condicion) {
		if(tipo_string.equals("Standar"))
			return new Standar();
		if(tipo_string.equals("Condicional")) 
			return new Condicional(condicion);
		if(tipo_string.equals("Solidaria"))
			return new Solidaria();
		return null;
	}

	public List<Partido> getPartidos_jugados() {
		List<Partido>partidos_jugados = new ArrayList<Partido>();
		for(int i=0;i< this.getInscripciones().size();i++){
			if(this.getInscripciones().get(i).getPartido_inscripto().estaConfirmado())
				partidos_jugados.add(this.getInscripciones().get(i).getPartido_inscripto());
		}
		return partidos_jugados;
	}
	
	public List<Calificacion> getCalificacionesByPartido(Partido partido) {
		List<Calificacion> calificaciones = new ArrayList<Calificacion>();
		for(int i=0;i< this.getCalificaciones().size();i++){
			if(this.getCalificaciones().get(i).getPartido().getId().equals(partido.getId()))
				calificaciones.add(this.getCalificaciones().get(i));
		}
		return calificaciones;
	}
	
	
	public List<Jugador> getNoCalificados(Partido partido){ 
		List<Jugador> jugadores_noCalificados = new ArrayList<Jugador>();
		List<Calificacion> calificaciones_partido = getCalificacionesByPartido(partido); //Lista de los calificados
		for(int i=0;i<partido.getInscripciones().size();i++) 
			if(!partido.getInscripciones().get(i).getJugador_inscripto().getUsuario().matches(getUsuario())){ //Si no soy yo prosigo
				jugadores_noCalificados.add(partido.getInscripciones().get(i).getJugador_inscripto()); //Agrego al jugador a la lista
				for(int j=0;j<calificaciones_partido.size();j++)//Este jugador, posee alguna calificacion?
					if(calificaciones.get(j).estaCalificado(partido.getInscripciones().get(i).getJugador_inscripto())) 
						jugadores_noCalificados.remove(partido.getInscripciones().get(i).getJugador_inscripto());//Si esta calificado, me arrepiento y lo saco
			}
		return jugadores_noCalificados;
	}
	
	public boolean yaExisteAmigo(Amigo amigo){
		for(int i=0;i<amigos.size();i++)
			if(amigos.get(i).concuerdoCon(amigo))
				return true;
		return false;
	}
}
