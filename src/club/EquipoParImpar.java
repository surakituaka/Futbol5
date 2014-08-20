package club;

import java.util.ArrayList;
import java.util.List;

public class EquipoParImpar implements IGeneradorEquipos{

	@Override
	public void generar_equipos(Partido partido) {
		
		List<Jugador> e1 = new ArrayList<Jugador>();
		List<Jugador> e2 = new ArrayList<Jugador>();
		
		for (Jugador jugador : partido.getTitulares()) {
			if(partido.getTitulares().indexOf(jugador)%2==0){
				e1.add(jugador);
			}
			else{
				e2.add(jugador);
			}
		}
		partido.getEquipo1().setJugadores(e1);
		partido.getEquipo2().setJugadores(e2);
	}
	
}
