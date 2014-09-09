package clasesDeNegocio;

public class Propuesta {

	private Amigo amigo;
	private Partido partido;
	private Jugador jugador;
	private IModalidad modalidad;
	
	public Amigo getAmigo() {
		return amigo;
	}
	
	public void setAmigo(Amigo amigo) {
		this.amigo = amigo;
	}
	
	public Partido getPartido() {
		return partido;
	}
	
	public void setPartido(Partido partido) {
		this.partido = partido;
	}
	
	public Jugador getJugador() {
		return jugador;
	}
	
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public IModalidad getModalidad() {
		return modalidad;
	}

	public void setModalidad(IModalidad modalidad) {
		this.modalidad = modalidad;
	}
}
