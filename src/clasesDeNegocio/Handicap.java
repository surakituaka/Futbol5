package clasesDeNegocio;

import java.util.Collections;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class Handicap extends CriterioOrden{

	@Override
	public void ordenar(Partido partido) {
		
		Collections.sort(partido.getInscripciones(),this);
	}

	public String quienSoy(){
		return "Handicap";
	}
	
	@Override
	public int compare(Inscripcion inscripcion1, Inscripcion inscripcion2) {
		
		return inscripcion2.getJugador_inscripto().getHandicap().compareTo(inscripcion1.getJugador_inscripto().getHandicap());
	}

	

	@Override
	public Comparator<Inscripcion> reversed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Inscripcion> thenComparing(Comparator<? super Inscripcion> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U extends Comparable<? super U>> Comparator<Inscripcion> thenComparing(
			Function<? super Inscripcion, ? extends U> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> Comparator<Inscripcion> thenComparing(
			Function<? super Inscripcion, ? extends U> arg0,
			Comparator<? super U> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Inscripcion> thenComparingDouble(
			ToDoubleFunction<? super Inscripcion> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Inscripcion> thenComparingInt(
			ToIntFunction<? super Inscripcion> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Inscripcion> thenComparingLong(
			ToLongFunction<? super Inscripcion> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
