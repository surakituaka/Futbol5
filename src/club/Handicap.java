package club;

import java.util.Collections;

public class Handicap extends CriterioOrden{

	@Override
	public void ordenar(Partido partido) {
		
		Collections.sort(partido.getJugadores(),this);
	}

	@Override
	public int compare(Jugador jugador1, Jugador jugador2) {
		if(jugador1.getHandicap()>jugador2.getHandicap()){
			return 1;
		}
		if(jugador1.getHandicap()<jugador2.getHandicap()){
			return -1;
		}
		return 0;
	}

}
