package clasesDeNegocio;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.IndexColumn;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "T_JUGADOR")
public class Jugador extends Usuario{	
	@Id
	@SequenceGenerator(name="secuencia_idJugador", sequenceName="seq6", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="secuencia_idJugador")
	@Column(name = "JUGADOR_ID", nullable = false)
	private Long id;	//TODO Cambiar la DB para añadir esta columna
	
	@Column(name = "JUGADOR_USUARIO", length = 20, nullable = false)
	private String usuario;
	
	@Column(name = "JUGADOR_NOMBRE", length = 20, nullable = false)
	private String nombre;
	
	@Column(name = "JUGADOR_APELLIDO", length = 20, nullable = false)
	private String apellido;
	
	@Column(name = "JUGADOR_FECHA_NACIMIENTO", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date fecha_nacimiento;
	
	@Column(name = "JUGADOR_HANDICAP", length = 20, nullable = true)
	private Integer handicap = 6; //Por defecto todos tienen 6
	
	@Column(name = "JUGADOR_EMAIL", length = 30, nullable = false)
	private String email;	
	
	@Column(name = "JUGADOR_PASSWORD", length = 20, nullable = false)
	private String password = "";
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="JUGADOR_ID")
	private List<Amigo> amigos = new ArrayList<Amigo>();
	
	@OneToMany(mappedBy = "jugador", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@IndexColumn(name="IDX")
	private List<Penalizacion> penalizaciones = new ArrayList<Penalizacion>();
	
	@OneToMany(mappedBy = "jugador_inscripto", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
	
	@OneToMany(mappedBy = "calificador", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Calificacion>calificaciones = new ArrayList<Calificacion>();
	
	@Transient
	private IOrden proposicion;
	
	@Transient
	@Deprecated
	private List<Partido>partidos_inscriptos = new ArrayList<Partido>();
	@Transient
	@Deprecated
	private IModalidad tipo;

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	public Integer getHandicap() {
		return handicap;
	}
	public void setHandicap(Integer handicap) {
		this.handicap = handicap;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
			if(this.inscripciones.get(i).getPartido_inscripto().getPartido_nombre().equals(partido.getPartido_nombre()))
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
				partido.enviar_mensaje("Partido "+partido.getPartido_nombre(), amigo.getEmail(), "tu amigo "+getNombre()+" ingreso a un partido");
			}
		}
		return inscripcion;
	}
	
	public void bajarse_de(Partido partido){
		partido.quitar_inscripcion(this);
		this.quitar_inscripcion(partido);
		this.agregar_penalizacion(new Penalizacion(new Date(),"por dejar un partido sin reemplazo",this));
	}
	
	public void bajarse_de(Partido partido,Jugador reemplazo){
		partido.reemplazarJugadores(this, reemplazo);
		this.quitar_inscripcion(partido);
	}
	
	public void proponer_jugador(Amigo amigo, Partido partido){
		this.proposicion.orden(amigo,partido,this);
	}
	
	public Calificacion calificar(Jugador calificado, Integer calificacion, String critica, Partido partido){
		Calificacion temp = new Calificacion(this, calificado, calificacion, critica, partido,new Date());
		this.agregar_calificacion(temp);
		return temp;
	}
	
	public boolean estoyInscripto(Partido partido) {
		boolean respuesta = false;
		for(Inscripcion papel : getInscripciones()){
			Partido partidoo = papel.getPartido_inscripto(); 
			if(partidoo.equals(partido))
				//respuesta = true;
				return true;
		}
			
		/*for(int i=0;i<getInscripciones().size();i++){
			if(getInscripciones().get(i).getPartido_inscripto().getPartido_nombre().equals(partido.getPartido_nombre()))
				//respuesta = true;
				return true;
		}*/
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
		for(Inscripcion papel : getInscripciones()){
			Partido partidoo = papel.getPartido_inscripto(); 
			if(partidoo.estaConfirmado())
				partidos_jugados.add(partidoo);
		}
		/*
		for(int i=0;i< this.getInscripciones().size();i++){
			if(this.getInscripciones().get(i).getPartido_inscripto().estaConfirmado())
				partidos_jugados.add(this.getInscripciones().get(i).getPartido_inscripto());
		}*/
		return partidos_jugados;
	}
	
	public List<Calificacion> getCalificacionesByPartido(Partido partido) {
		List<Calificacion> calificaciones = new ArrayList<Calificacion>();
		for(int i=0;i< this.getCalificaciones().size();i++){
			if(this.getCalificaciones().get(i).getPartido().getPartido_nombre().equals(partido.getPartido_nombre()))
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
	
	public boolean imBlue()
	{
		if(this.getHandicap() > 8)
			return true;
		else
			return false;
	}
	public Partido getUltimoPartidoJugado()
	{
		Partido partido_base = null;
		if(inscripciones.size() > 0)
		{
			for(int i = 0; i < inscripciones.size(); i++)
			{
				if(inscripciones.get(i).getPartido_inscripto().estaConfirmado())
					if(partido_base == null)
						partido_base = inscripciones.get(i).getPartido_inscripto();
					else
						if(inscripciones.get(i).getPartido_inscripto().getFecha().after(partido_base.getFecha()))
							partido_base = inscripciones.get(i).getPartido_inscripto();	
			}		
		}
		return partido_base;
	}
	
	public int promedioUltimoPartido() {
		int promedio = 0;
		Partido ultimoPartido = getUltimoPartidoJugado();
		if(ultimoPartido != null)
			for(int i = 0; i < calificaciones.size(); i++)
				if(calificaciones.get(i).getPartido().getId().equals(ultimoPartido.getId()))
					promedio += calificaciones.get(i).getCalificacion();
		return promedio;
	}
	public int promedioGeneral(){
		int promedio = 0;
		for(int i = 0; i< calificaciones.size(); i++)
			promedio += calificaciones.get(i).getCalificacion();		
		return promedio;
	}
}
