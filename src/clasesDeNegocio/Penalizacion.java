package clasesDeNegocio;

import java.util.Date;

public class Penalizacion {
	
	
	private Long id;
	private Date fecha;
	private String motivo;
	private Jugador jugador;
	
	public Penalizacion(){
		super();
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
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

	public Penalizacion(Date fecha, String motivo, Jugador jugador) {
		super();
		this.fecha = fecha;
		this.motivo = motivo;
		this.setJugador(jugador);
	}

}
