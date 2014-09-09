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
		Collections.sort(partido.getInscripciones(), this);
		
	}
	
	@Override
	public int compare(Inscripcion inscripcion1, Inscripcion inscripcion2) {
		
		Collections.sort(inscripcion1.getJugador_inscripto().getPartidos_jugados(),new OrdenadorPartidoFecha());
		Collections.sort(inscripcion2.getJugador_inscripto().getPartidos_jugados(),new OrdenadorPartidoFecha());
		
		Double prom1=0.0;
		Double prom2=0.0;
		Integer cant_cal1=0;
		Integer cant_cal2=0;
		
		int i=0;
		while(i<cantidad_partidos && i<inscripcion1.getJugador_inscripto().getPartidos_jugados().size()){
			for (Calificacion calificacion : inscripcion1.getJugador_inscripto().getCalificacionesByPartido(inscripcion1.getJugador_inscripto().getPartidos_jugados().get(i))) {
				if(calificacion.getCalificado()==inscripcion1.getJugador_inscripto()){
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
		while(i<cantidad_partidos && i<inscripcion2.getJugador_inscripto().getPartidos_jugados().size()){
			for (Calificacion calificacion : inscripcion2.getJugador_inscripto().getCalificacionesByPartido(inscripcion2.getJugador_inscripto().getPartidos_jugados().get(i))) {
				if(calificacion.getCalificado()==inscripcion2.getJugador_inscripto()){
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
