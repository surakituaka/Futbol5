package club;

import java.util.Comparator;

public class OrdenadorPartidoFecha implements Comparator<Partido>{

	@Override
	public int compare(Partido partido1, Partido partido2) {
		
		if(partido1.getFecha().after(partido2.getFecha())){
			return 1;
		}
		if(partido1.getFecha().before(partido2.getFecha())){
			return -1;
		}
		return 0;
	}

}
