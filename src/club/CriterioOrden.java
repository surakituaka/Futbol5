package club;

import java.util.Comparator;

public abstract class CriterioOrden implements Comparator<Jugador>{
	
	public abstract void ordenar(Partido partido);
}
