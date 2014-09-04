package clasesDeNegocio;

import java.util.Collections;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class Promedio extends CriterioOrden{
	
	private Integer cantidad_partidos;
	
	public String quienSoy(){
		return "Promedio";
	}
	
	public Integer getCantidad_partidos() {
		return cantidad_partidos;
	}
	
	public void setCantidad_partidos(Integer cantidad_partidos) {
		this.cantidad_partidos = cantidad_partidos;
	}
	
	public Promedio(Integer cant){
		super();
		setCantidad_partidos(cant);
	}
	
	@Override
	public void ordenar(Partido partido) {
		Collections.sort(partido.getJugadores(), this);
		
	}
	
	@Override
	public int compare(Jugador jugador1, Jugador jugador2) {
		
		Collections.sort(jugador1.getPartidos_jugados(),new OrdenadorPartidoFecha());
		Collections.sort(jugador2.getPartidos_jugados(),new OrdenadorPartidoFecha());
		
		Double prom1=0.0;
		Double prom2=0.0;
		Integer cant_cal1=0;
		Integer cant_cal2=0;
		
		int i=0;
		while(i<cantidad_partidos && i<jugador1.getPartidos_jugados().size()){
			for (Calificacion calificacion : jugador1.getCalificacionesByPartido(jugador1.getPartidos_jugados().get(i))) {
				if(calificacion.getCalificado()==jugador1){
					prom1+=calificacion.getCalificacion();
					cant_cal1++;
				}
			}
			i++;
		}
		if(!cant_cal1.equals(0)){
			prom1=prom1/cant_cal1;
		}
		
		
		i=0;
		while(i<cantidad_partidos && i<jugador2.getPartidos_jugados().size()){
			for (Calificacion calificacion : jugador2.getCalificacionesByPartido(jugador2.getPartidos_jugados().get(i))) {
				if(calificacion.getCalificado()==jugador2){
					prom2+=calificacion.getCalificacion();
					cant_cal2++;
				}
			}
			i++;
		}
		
		if(!cant_cal2.equals(0)){
			prom2=prom2/cant_cal2;
		}
		
		return prom2.compareTo(prom1);
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
