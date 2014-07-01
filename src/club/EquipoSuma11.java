package club;

import java.util.ArrayList;
import java.util.List;

public class EquipoSuma11 implements IGeneradorEquipos{

	@Override
	public void generar_equipos(Partido partido) {
		
		List<Jugador> e1 = new ArrayList<Jugador>();
		List<Jugador> e2 = new ArrayList<Jugador>();
		int i = 0;
		int j = 9;
		while ( i < 5) {
			while ( j > 4) {
				if(i+j==9){
					e1.add(partido.getJugadores().get(i));
					e2.add(partido.getJugadores().get(j));
					break;
				}
			}
			i++;
			j--;
		}
		
		
		partido.getEquipo1().setJugadores(e1);
		partido.getEquipo2().setJugadores(e2);
		
		
	}
	
}
