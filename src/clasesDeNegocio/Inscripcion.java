package clasesDeNegocio;

public class Inscripcion {
	
	private IModalidad modalidad;
	private Partido partido_inscripto;
	private Jugador jugador_inscripto;
	
	public IModalidad getModalidad() {
		return modalidad;
	}
	public void setModalidad(IModalidad modalidad) {
		this.modalidad = modalidad;
	}
	public Partido getPartido_inscripto() {
		return partido_inscripto;
	}
	public void setPartido_inscripto(Partido partido_inscripto) {
		this.partido_inscripto = partido_inscripto;
	}
	public Jugador getJugador_inscripto() {
		return jugador_inscripto;
	}
	public void setJugador_inscripto(Jugador jugador_inscripto) {
		this.jugador_inscripto = jugador_inscripto;
	}
}
