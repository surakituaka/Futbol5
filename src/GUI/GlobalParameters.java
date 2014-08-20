package GUI;

import javax.swing.JFrame;

import club.*;

public class GlobalParameters extends Global {

	public JFrame pantalla_anterior;
	public Jugador jugador_seleccionado;
	
	
	public GlobalParameters(Global hardcodeo, Jugador jugador, JFrame pantalla) {
		pantalla_anterior = pantalla;
		jugador_seleccionado = jugador;
		mensajero = hardcodeo.mensajero;
		criterios = hardcodeo.criterios;
		jugadores = hardcodeo.jugadores;
		administrador = hardcodeo.administrador;
		partidos = hardcodeo.partidos;

		
		// TODO Auto-generated constructor stub
	}

}
