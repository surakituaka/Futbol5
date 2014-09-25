package clasesDeNegocio;

import java.util.GregorianCalendar;

public class Penalizacion {
	
	
	private Long id;
	private GregorianCalendar fecha;
	private String motivo;
	private Jugador jugador;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GregorianCalendar getFecha() {
		return fecha;
	}
	
	public void setFecha(GregorianCalendar fecha) {
		this.fecha = fecha;
	}
	
	public String getMotivo() {
		return motivo;
	}
	
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public Penalizacion(GregorianCalendar fecha, String motivo, Jugador jugador) {
		super();
		this.fecha = fecha;
		this.motivo = motivo;
		this.setJugador(jugador);
	}

}
