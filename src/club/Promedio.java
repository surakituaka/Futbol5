package club;

import java.util.Collections;

public class Promedio extends CriterioEspecifico{
	
	private Integer cantidad_partidos;
	
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
			for (Calificacion calificacion : jugador1.getPartidos_jugados().get(i).getCalificaciones()) {
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
			for (Calificacion calificacion : jugador2.getPartidos_jugados().get(i).getCalificaciones()) {
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
		
		return prom1.compareTo(prom2);
	}


}
