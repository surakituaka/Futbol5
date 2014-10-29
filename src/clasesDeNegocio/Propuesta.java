package clasesDeNegocio;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "T_PROPUESTA")
public class Propuesta {
	
	@Id
	@SequenceGenerator(name="secuencia_idPropuesta", sequenceName="seq9", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="secuencia_idPropuesta")
	@Column(name = "PROPUESTA_ID", nullable = false)
	public Long id;
	
	@Column(name = "PROPUESTA_ESTADO", length = 20, nullable = true)	
	public String estado_propuesta;		//TODO SE LO AGREGUE --CRISTIAN-- FALTA DARLE UTILIDAD
	
	@Column(name = "PROPUESTA_RAZON_RECHAZO", length = 20, nullable = true)
	public String razon_rechazo;		//TODO SE LO AGREGUE --CRISTIAN-- FALTA DARLE UTILIDAD
	
	@OneToOne(fetch=FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinColumn(name = "AMIGO_ID", nullable = false)
	private Amigo amigo;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinColumn(name = "PARTIDO_ID", nullable = false)
	private Partido partido;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinColumn(name = "JUGADOR_ID", nullable = false)
	private Jugador jugador;
	
	@OneToOne(fetch=FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE})
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
