package clasesDeNegocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import clasesDeNegocio.OrdenadorJugadoresTipo;
import ServiciosExternos.IMensajero;


public class Partido implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String partido_nombre;
	private Date fecha;
	private String lugar;
	@Deprecated
	private List<Jugador> jugadores = new ArrayList<Jugador>();
	private List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
	private IMensajero mensajero;
	private Equipo equipo1 = new Equipo();
	private Equipo equipo2 = new Equipo();
	private IEstadoEquipo estado = new EstadoPendiente();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPartido_nombre() {
		return partido_nombre;
	}

	public void setPartido_nombre(String partido_nombre) {
		this.partido_nombre = partido_nombre;
	}

	public Partido(IMensajero emailInterface){
		setMensajero(emailInterface);
	}
	
	@Deprecated
	public List<Jugador> getJugadores() {
		return jugadores;
	}
	
	@Deprecated
	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String getLugar() {
		return lugar;
	}
	
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	
	public IMensajero getMensajero() {
		return mensajero;
	}

	public void setMensajero(IMensajero mensajero) {
		this.mensajero = mensajero;
	}

	public Equipo getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(Equipo equipo1) {
		this.equipo1 = equipo1;
	}

	public Equipo getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(Equipo equipo2) {
		this.equipo2 = equipo2;
	}
	
	public String getEstado() {
		return estado.getEstado(this);
	}

	public void setEstado(IEstadoEquipo estado) {
		this.estado = estado;
	}
	
	public List<Inscripcion> getInscripciones() {
		return inscripciones;
	}

	public void setInscripciones(List<Inscripcion> inscripciones) {
		this.inscripciones = inscripciones;
	}

	@Deprecated
	public void agregar_jugador(Jugador jugador){
		
		this.jugadores.add(jugador);
	}
	
	@Deprecated
	public Jugador quitar_jugador(Jugador jugador){
		int i = this.jugadores.indexOf(jugador);
		return this.jugadores.remove(i);
	}
	
	public void agregar_inscripcion(Inscripcion inscripcion){
		
		this.inscripciones.add(inscripcion);
	}

	public Inscripcion quitar_inscripcion(Jugador jugador){
		for(int i=0;i<inscripciones.size();i++)
			if(this.inscripciones.get(i).getJugador_inscripto().getUsuario().matches(jugador.getUsuario()))
				return this.inscripciones.remove(i);
		return null;
	}
	
	public String enviar_mensaje(String emisor,String receptor,String mensaje){
		return mensajero.enviar_mensaje(emisor, receptor, mensaje);
	}

	public Inscripcion inscribir(Jugador jugador, IModalidad tipo_inscripcion){
		if(!hay10Standar()){
			if(!validar10()){
				Inscripcion inscripcion = new Inscripcion();
				inscripcion.setJugador_inscripto(jugador);
				inscripcion.setModalidad(tipo_inscripcion);
				inscripcion.setPartido_inscripto(this);
				agregar_inscripcion(inscripcion);
				return inscripcion;
			}
			else{
				Jugador jugador_a_eliminar = buscarAlgunoConMenosPrioridad(tipo_inscripcion);
				if(jugador_a_eliminar != null){
					jugador_a_eliminar.quitar_inscripcion(this);
					quitar_inscripcion(jugador_a_eliminar);
					
					Inscripcion inscripcion = new Inscripcion();
					inscripcion.setJugador_inscripto(jugador);
					inscripcion.setModalidad(tipo_inscripcion);
					inscripcion.setPartido_inscripto(this);
					agregar_inscripcion(inscripcion);
					return inscripcion;
				}
			}
		}
		return null;			
	}
	public Jugador buscarAlgunoConMenosPrioridad(IModalidad modalidad){
		OrdenadorJugadoresTipo comparador = new OrdenadorJugadoresTipo();
		Collections.sort(getInscripciones(),comparador); //Ordena la lista con los de mayor prioridad al final
		Inscripcion ultimo_jugador_inscripto = getInscripciones().get(0);
		//Se agrega esta sentencia porque si ambos son solidarios los toma por fecha, y al estar inscripto ser mas viejo que el nuevo
		//Nos devuelve que el nuevo tiene mas prioridad, pero queremos priorizar el solidario inscripto por sobre el nuevo
		if(ultimo_jugador_inscripto.getModalidad().getInscripcion().equals("SOLIDARIA") && modalidad.getInscripcion().equals("SOLIDARIA"))
			return null;
		Inscripcion aux = new Inscripcion(); aux.setModalidad(modalidad);
		int comparacion = comparador.compare(ultimo_jugador_inscripto,aux);
		if(comparacion < 0)
			return ultimo_jugador_inscripto.getJugador_inscripto();
		return null;
	}
	
	public void reemplazarJugadores(Jugador baja, Jugador reemplazo){
		Inscripcion aux = this.quitar_inscripcion(baja);
		aux.setJugador_inscripto(reemplazo);
		this.agregar_inscripcion(aux);
	}
	
	public Boolean validar10(){
		return inscripciones.size() == 10;
	}
	
	public boolean hay10Standar() {
		int contador = 0;
		for (int i=0;i<inscripciones.size();i++) 
			if(inscripciones.get(i).getModalidad().getInscripcion().equals("STANDAR"))
				contador++;
		
		if(contador == 10)
			return true;
		
		return false;
	}
	
	public boolean estaConfirmado() {
		if(estado.getEstado(this).equals("Confirmado"))
			return true;
		return false;
	}
	
	public boolean tieneEquipos() {
		if(estado.getEstado(this).equals("Con Equipos Tentativos"))
			return true;
		return false;
	}
	
	public String getSigEstado(){
		return estado.getSiguienteEstado(this);
	}
	
	public void confirmar(){
		if(!estaConfirmado()){
			IEstadoEquipo estado_nuevo = new EstadoConfirmado();
			setEstado(estado_nuevo);
		}
	}
}
