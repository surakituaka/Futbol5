package GUI;

import javax.swing.JFrame;

import club.*;

public class GlobalParameters extends Global {

	public JFrame pantalla_anterior;
	public Jugador jugador_seleccionado;
	
	
	public GlobalParameters(Global harcodeo, Jugador jugador, JFrame pantalla) {
		pantalla_anterior = pantalla;
		jugador_seleccionado = jugador;
		jugadores = harcodeo.jugadores;
		administrador = harcodeo.administrador;
		partidos = harcodeo.partidos;

		
		// TODO Auto-generated constructor stub
	}

}
