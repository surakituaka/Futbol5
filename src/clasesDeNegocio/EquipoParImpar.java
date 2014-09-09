package clasesDeNegocio;

import java.util.ArrayList;
import java.util.List;

public class EquipoParImpar implements IGeneradorEquipos{

	public void generar_equipos(Partido partido) {
		
		List<Jugador> e1 = new ArrayList<Jugador>();
		List<Jugador> e2 = new ArrayList<Jugador>();
		
		for (Inscripcion inscripcion : partido.getInscripciones()) {
			if(partido.getInscripciones().indexOf(inscripcion)%2==0){
				e1.add(inscripcion.getJugador_inscripto());
			}
			else{
				e2.add(inscripcion.getJugador_inscripto());
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
