package clasesDeNegocio;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class OrdenadorJugadoresTipo implements Comparator<Inscripcion>{

	public int compare(Inscripcion jugador1, Inscripcion jugador2) {
		
		final int MAYOR = 1;
		final int MENOR =-1;
		final int IGUAL = 0;

		if(jugador1 == jugador2)
			return IGUAL;
		
		//standar mayor a demas tipos		
		if((jugador1.getModalidad().getInscripcion().equals("STANDAR") && !jugador2.getModalidad().getInscripcion().equals("STANDAR"))){
			return MAYOR;
		}
		
		if((!jugador1.getModalidad().getInscripcion().equals("STANDAR") && jugador2.getModalidad().getInscripcion().equals("STANDAR"))){
			return MENOR;
		}	
		
		//entre solidarios
		if(jugador1.getModalidad().getInscripcion().equals("SOLIDARIA") && jugador2.getModalidad().getInscripcion().equals("SOLIDARIA") && jugador1.getModalidad().getFecha().after(jugador2.getModalidad().getFecha())){
			return MAYOR;
		}
			
		if(jugador1.getModalidad().getInscripcion().equals("SOLIDARIA") && jugador2.getModalidad().getInscripcion().equals("SOLIDARIA") && jugador1.getModalidad().getFecha().before(jugador2.getModalidad().getFecha())){
			return MENOR;
		}
	 		
		//solidario mayor a condicional
		if((jugador1.getModalidad().getInscripcion().equals("SOLIDARIA")) &&	(jugador2.getModalidad().getInscripcion().equals("CONDICIONAL"))){
			return MAYOR;
		}
				
		//condicional menor a todos
		if((jugador1.getModalidad().getInscripcion().equals("CONDICIONAL") && !jugador2.getModalidad().getInscripcion().equals("SOLIDARIA"))){
			return MENOR;
		}
		
	
		return IGUAL;
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
