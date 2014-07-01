package club;

import java.util.Comparator;

public class OrdenadorJugadoresTipo implements Comparator<Jugador>{

	@Override
	public int compare(Jugador jugador1, Jugador jugador2) {
		
		final int MAYOR =1;
		final int MENOR =-1;
		final int IGUAL=0;
		
		if(jugador1 == jugador2){
			return IGUAL;
		}
	/*
		//entre standar
		if(this.getTipo().getInscripcion().equals("STANDAR") && this.getTipo().getInscripcion().equals("STANDAR") && this.getPrioridad()>jugador.getPrioridad()){
			return MAYOR;
		}
		
		if(this.getTipo().getInscripcion().equals("STANDAR") && this.getTipo().getInscripcion().equals("STANDAR") && this.getPrioridad()<jugador.getPrioridad()){
			return MENOR;
		}
		
		//entre condicionales
		if(this.getTipo().getInscripcion().equals("CONDICIONAL") && this.getTipo().getInscripcion().equals("CONDICIONAL") && this.getPrioridad()>jugador.getPrioridad()){
			return MAYOR;
		}
		
		if(this.getTipo().getInscripcion().equals("CONDICIONAL") && this.getTipo().getInscripcion().equals("CONDICIONAL") && this.getPrioridad()<jugador.getPrioridad()){
			return MENOR;
		}
	*/
		//entre solidarios
		if(jugador1.getTipo().getInscripcion().equals("SOLIDARIA") && jugador2.getTipo().getInscripcion().equals("SOLIDARIA") && jugador1.getTipo().getFecha().after(jugador2.getTipo().getFecha())){
			return MAYOR;
		}
		
		if(jugador1.getTipo().getInscripcion().equals("SOLIDARIA") && jugador2.getTipo().getInscripcion().equals("SOLIDARIA") && jugador1.getTipo().getFecha().before(jugador2.getTipo().getFecha())){
			return MENOR;
		}
		
		//standar mayor a demas tipos		
		if((jugador1.getTipo().getInscripcion().equals("STANDAR") && !jugador2.getTipo().getInscripcion().equals("STANDAR"))){
			return MAYOR;
		}
		
		//solidario mayor a condicional
		if((jugador1.getTipo().getInscripcion().equals("SOLIDARIA")) &&	(jugador2.getTipo().getInscripcion().equals("CONDICIONAL"))){
			return MAYOR;
		}
		//solidario menor a standar
		if((jugador1.getTipo().getInscripcion().equals("SOLIDARIA")) &&	(jugador2.getTipo().getInscripcion().equals("STANDAR"))){
			return MAYOR;
		}
		
		//solidaria menor a standar
		if((jugador1.getTipo().getInscripcion().equals("SOLIDARIA")) && (jugador2.getTipo().getInscripcion().equals("STANDAR"))){
			return MENOR;
		}
		
		//condicional menor a todos
		if((jugador1.getTipo().getInscripcion().equals("CONDICIONAL") && !jugador2.getTipo().getInscripcion().equals("CONDICIONAL"))){
			return MENOR;
		}
		
		return IGUAL;
		}
}
