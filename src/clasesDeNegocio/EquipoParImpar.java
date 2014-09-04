package clasesDeNegocio;

import java.util.ArrayList;
import java.util.List;

public class EquipoParImpar implements IGeneradorEquipos{

	public void generar_equipos(Partido partido) {
		
		List<Jugador> e1 = new ArrayList<Jugador>();
		List<Jugador> e2 = new ArrayList<Jugador>();
		
		for (Jugador jugador : partido.getJugadores()) {
			if(partido.getJugadores().indexOf(jugador)%2==0){
				e1.add(jugador);
			}
			else{
				e2.add(jugador);
			}
		}
		partido.getEquipo1().setJugadores(e1);
		partido.getEquipo2().setJugadores(e2);
	}

	@Override
	public String quienSoy() {
		// TODO Auto-generated method stub
		return "Par e Impar";
	}
	
}