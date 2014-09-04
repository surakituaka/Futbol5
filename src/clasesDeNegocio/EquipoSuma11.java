package clasesDeNegocio;

import java.util.ArrayList;
import java.util.List;

public class EquipoSuma11 implements IGeneradorEquipos{

	public void generar_equipos(Partido partido) {
		
		List<Jugador> e1 = new ArrayList<Jugador>();
		List<Jugador> e2 = new ArrayList<Jugador>();

		int i = 0;
		while(i < 9){
			e1.add(partido.getJugadores().get(i));
			e2.add(partido.getJugadores().get(9-i));
			i += 4;
		}
		i -= 5;
		while(i > 2){
			e1.add(partido.getJugadores().get(i));
			e2.add(partido.getJugadores().get(9-i));
			i -= 4;
		}
		
		partido.getEquipo1().setJugadores(e1);
		partido.getEquipo2().setJugadores(e2);
		
		
	}

	@Override
	public String quienSoy() {
		// TODO Auto-generated method stub
		return "Posicion suma 11";
	}
	
}

/* Ordena asi	e1 = (01) - (05) - (09) - (08) - (04)
				e2 = (10) - (06) - (02) - (03) - (07) 
*/