package club;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Partido {
	
	private String id_partido;
	private List<Jugador> jugadores = new ArrayList<Jugador>();
	private Date fecha;
	private String lugar;
	private IMensajero mensajero;
	private List<Calificacion> calificaciones = new ArrayList<Calificacion>();
	private Equipo equipo1 = new Equipo();
	private Equipo equipo2 = new Equipo();
	
	
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

	public List<Calificacion> getCalificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(List<Calificacion> calificaciones) {
		this.calificaciones = calificaciones;
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

	public void agregar_jugador(Jugador jugador){
		
		this.jugadores.add(jugador);
	}
	
	public Jugador quitar_jugador(Jugador jugador){
		int i = this.jugadores.indexOf(jugador);
		return this.jugadores.remove(i);
	}
	
	public Boolean validar10(){
		return jugadores.size()==10;
	}
	
	public String enviar_mensaje(String emisor,String receptor,String mensaje){
		return mensajero.enviar_mensaje(emisor, receptor, mensaje);
	}
	
	public void agregar_calificacion(Calificacion calificacion){
		this.calificaciones.add(calificacion);
	}
	
	public boolean sePuedeInscribir(Jugador jugador){
		if(!this.hay10Standar())
				return true;
		return false;
	}

	private boolean hay10Standar() {
		int contador = 0;
		for(int i=0;i<jugadores.size();i++){
			if(jugadores.get(i).getTipo().soyStandar())
				contador++;
		}
		if(contador == 10)
			return true;
		return false;
	}
}
