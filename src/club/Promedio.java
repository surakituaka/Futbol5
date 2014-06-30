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
	
	@Override
	public void ordenar(Partido partido) {
		Collections.sort(partido.getJugadores(), this);
		
	}
	
	@Override
	public int compare(Jugador jugador1, Jugador jugador2) {
		// TODO Auto-generated method stub
		return 0;
	}


}
