package clasesDeNegocio;

import java.util.Comparator;

public abstract class CriterioOrden implements Comparator<Jugador>{
	
	public abstract void ordenar(Partido partido);
	public abstract String quienSoy();
	public int compare(Jugador jugador1, Jugador jugador2) {
		// TODO Auto-generated method stub
		return 0;
	}
}
