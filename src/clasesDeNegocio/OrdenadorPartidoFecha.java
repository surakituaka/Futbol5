package clasesDeNegocio;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class OrdenadorPartidoFecha implements Comparator<Partido>{

	
	public int compare(Partido partido1, Partido partido2) {
		
		if(partido1.getFecha().after(partido2.getFecha())){
			return 1;
		}
		if(partido1.getFecha().before(partido2.getFecha())){
			return -1;
		}
		return 0;
	}


	

	@Override
	public Comparator<Partido> reversed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Partido> thenComparing(Comparator<? super Partido> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U extends Comparable<? super U>> Comparator<Partido> thenComparing(
			Function<? super Partido, ? extends U> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> Comparator<Partido> thenComparing(
			Function<? super Partido, ? extends U> arg0,
			Comparator<? super U> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Partido> thenComparingDouble(
			ToDoubleFunction<? super Partido> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Partido> thenComparingInt(
			ToIntFunction<? super Partido> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Partido> thenComparingLong(
			ToLongFunction<? super Partido> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
