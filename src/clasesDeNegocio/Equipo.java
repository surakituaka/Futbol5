package clasesDeNegocio;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
	
	private List<Jugador>jugadores = new ArrayList<Jugador>();

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
}
