package club;

import java.util.GregorianCalendar;

public class Calificacion {
	
	private Jugador calificador;
	private Jugador calificado;
	private Integer calificacion;
	private String critica;
	private Partido partido;
	private GregorianCalendar fecha;
	
	public Jugador getCalificador() {
		return calificador;
	}
	
	public void setCalificador(Jugador calificador) {
		this.calificador = calificador;
	}
	
	public Jugador getCalificado() {
		return calificado;
	}
	
	public void setCalificado(Jugador calificado) {
		this.calificado = calificado;
	}
	
	public Integer getCalificacion() {
		return calificacion;
	}
	
	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}
	
	public String getCritica() {
		return critica;
	}
	
	public void setCritica(String critica) {
		this.critica = critica;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}
	
	public GregorianCalendar getFecha() {
		return fecha;
	}

	public void setFecha(GregorianCalendar fecha) {
		this.fecha = fecha;
	}

	public Calificacion(Jugador calificador, Jugador calificado, Integer calificacion, String critica, Partido partido,GregorianCalendar fecha){
		super();
		this.calificador=calificador;
		this.calificado=calificado;
		this.calificacion=calificacion;
		this.critica=critica;
		this.partido=partido;
		this.fecha=fecha;
		
	}
}
