package club;

import java.util.Collections;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

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

	

	@Override
	public Comparator<Jugador> reversed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Jugador> thenComparing(Comparator<? super Jugador> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U extends Comparable<? super U>> Comparator<Jugador> thenComparing(
			Function<? super Jugador, ? extends U> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> Comparator<Jugador> thenComparing(
			Function<? super Jugador, ? extends U> arg0,
			Comparator<? super U> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Jugador> thenComparingDouble(
			ToDoubleFunction<? super Jugador> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Jugador> thenComparingInt(
			ToIntFunction<? super Jugador> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Jugador> thenComparingLong(
			ToLongFunction<? super Jugador> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
