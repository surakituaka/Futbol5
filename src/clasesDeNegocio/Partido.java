package clasesDeNegocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import clasesDeNegocio.OrdenadorJugadoresTipo;
import ServiciosExternos.IMensajero;


public class Partido {
	
	private String id_partido;
	private Date fecha;
	private String lugar;
	private List<Jugador> jugadores = new ArrayList<Jugador>();
	private IMensajero mensajero;
	private Equipo equipo1 = new Equipo();
	private Equipo equipo2 = new Equipo();
	private IEstadoEquipo estado = new EstadoPendiente();
	
	public Partido(IMensajero emailInterface){
		setMensajero(emailInterface);
	}
	
	public List<Jugador> getJugadores() {
		return jugadores;
	}
	
	public void setId(String id){
		this.id_partido = id;
	}
	public String getId(){
		return id_partido;
	}
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
	
	public void agregar_jugador(Jugador jugador){
		
		this.jugadores.add(jugador);
	}
	
	public Jugador quitar_jugador(Jugador jugador){
		int i = this.jugadores.indexOf(jugador);
		return this.jugadores.remove(i);
	}
	
	public String enviar_mensaje(String emisor,String receptor,String mensaje){
		return mensajero.enviar_mensaje(emisor, receptor, mensaje);
	}

	public Respuesta inscribir(Jugador jugador){
		Respuesta respuesta = new Respuesta();
		if(!hay10Standar()){
			if(!validar10()){
				agregar_jugador(jugador);
				respuesta.setEsta_inscripto(true);
				respuesta.setMensaje("El jugador esta inscripto.");
				return respuesta;
			}
			else{
				Jugador jugador_a_eliminar = buscarAlgunoConMenosPrioridad(jugador);
				if(jugador_a_eliminar != null){
					jugador_a_eliminar.darBajaLista(this);
					quitar_jugador(jugador_a_eliminar);
					agregar_jugador(jugador);
					respuesta.setEsta_inscripto(true);
					respuesta.setMensaje("El jugador esta inscripto.");
					return respuesta;
				}
			}
		}
		respuesta.setEsta_inscripto(false);
		respuesta.setMensaje("El jugador no ha sido inscipto, no hay mas cupos.");
		return respuesta;			
	}
	public Jugador buscarAlgunoConMenosPrioridad(Jugador jugador){
		OrdenadorJugadoresTipo comparador = new OrdenadorJugadoresTipo();
		Collections.sort(getJugadores(),comparador); //Ordena la lista con los de mayor prioridad al final
		Jugador ultimo_jugador_inscripto = jugadores.get(0);
		//Se agrega esta sentencia porque si ambos son solidarios los toma por fecha, y al estar inscripto ser mas viejo que el nuevo
		//Nos devuelve que el nuevo tiene mas prioridad, pero queremos priorizar el solidario inscripto por sobre el nuevo
		if(ultimo_jugador_inscripto.getTipo().getInscripcion().equals("SOLIDARIA") && jugador.getTipo().getInscripcion().equals("SOLIDARIA"))
			return null;
		
		int comparacion = comparador.compare(ultimo_jugador_inscripto,jugador);
		if(comparacion < 0)
			return ultimo_jugador_inscripto;
		return null;
	}
	
	public Boolean validar10(){
		return jugadores.size() == 10;
	}
	
	public boolean hay10Standar() {
		int contador = 0;
		for (int i=0;i<jugadores.size();i++) 
			if(jugadores.get(i).getTipo().getInscripcion().equals("STANDAR"))
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
