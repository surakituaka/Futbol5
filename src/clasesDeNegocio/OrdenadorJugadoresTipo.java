package clasesDeNegocio;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class OrdenadorJugadoresTipo implements Comparator<Jugador>{

	public int compare(Jugador jugador1, Jugador jugador2) {
		
		final int MAYOR = 1;
		final int MENOR =-1;
		final int IGUAL = 0;

		if(jugador1 == jugador2)
			return IGUAL;
		
		//standar mayor a demas tipos		
		if((jugador1.getTipo().getInscripcion().equals("STANDAR") && !jugador2.getTipo().getInscripcion().equals("STANDAR"))){
			return MAYOR;
		}
		
		if((!jugador1.getTipo().getInscripcion().equals("STANDAR") && jugador2.getTipo().getInscripcion().equals("STANDAR"))){
			return MENOR;
		}	
		
		//entre solidarios
		if(jugador1.getTipo().getInscripcion().equals("SOLIDARIA") && jugador2.getTipo().getInscripcion().equals("SOLIDARIA") && jugador1.getTipo().getFecha().after(jugador2.getTipo().getFecha())){
			return MAYOR;
		}
			
		if(jugador1.getTipo().getInscripcion().equals("SOLIDARIA") && jugador2.getTipo().getInscripcion().equals("SOLIDARIA") && jugador1.getTipo().getFecha().before(jugador2.getTipo().getFecha())){
			return MENOR;
		}
	 		
		//solidario mayor a condicional
		if((jugador1.getTipo().getInscripcion().equals("SOLIDARIA")) &&	(jugador2.getTipo().getInscripcion().equals("CONDICIONAL"))){
			return MAYOR;
		}
				
		//condicional menor a todos
		if((jugador1.getTipo().getInscripcion().equals("CONDICIONAL") && !jugador2.getTipo().getInscripcion().equals("SOLIDARIA"))){
			return MENOR;
		}
		
	
		return IGUAL;
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