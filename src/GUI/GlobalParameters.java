package GUI;

import clasesDeNegocio.*;

public class GlobalParameters extends Global {

	public VentanaTheGrid pantalla_anterior;
	public Jugador jugador_seleccionado;
	
	
	public GlobalParameters(Global hardcodeo, Jugador jugador, VentanaTheGrid pantalla) {
		pantalla_anterior = pantalla;
		jugador_seleccionado = jugador;
		
		mensajero = hardcodeo.mensajero;
		jugadores = hardcodeo.jugadores;
		administrador = hardcodeo.administrador;
		partidos = hardcodeo.partidos;
		criterios = hardcodeo.criterios;
		generadores_equipo = hardcodeo.generadores_equipo;
		
		// TODO Auto-generated constructor stub
	}

}
