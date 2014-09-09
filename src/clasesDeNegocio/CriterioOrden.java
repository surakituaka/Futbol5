package clasesDeNegocio;

import java.util.Comparator;

public abstract class CriterioOrden implements Comparator<Inscripcion>{
	
	public abstract void ordenar(Partido partido);
	public abstract String quienSoy();
	public int compare(Inscripcion jugador1, Inscripcion jugador2) {
		// TODO Auto-generated method stub
		return 0;
	}
}
