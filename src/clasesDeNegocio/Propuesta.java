package clasesDeNegocio;

import javax.persistence.*;

@Entity
@Table(name = "T_PROPUESTA")
public class Propuesta {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "PROPUESTA_ID", nullable = false)
	public Long id;
	
	@Column(name = "PROPUESTA_ESTADO", length = 20, nullable = false)	
	public String estado_propuesta;		//TODO SE LO AGREGUE --CRISTIAN-- FALTA DARLE UTILIDAD
	
	@Column(name = "PROPUESTA_RAZON_RECHAZO", length = 20, nullable = false)
	public String razon_rechazo;		//TODO SE LO AGREGUE --CRISTIAN-- FALTA DARLE UTILIDAD
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "AMIGO_ID", nullable = false)
	private Amigo amigo;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "PARTIDO_ID", nullable = false)
	private Partido partido;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "JUGADOR_ID", nullable = false)
	private Jugador jugador;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "MODALIDAD_ID", nullable = false)
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
