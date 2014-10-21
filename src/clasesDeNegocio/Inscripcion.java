package clasesDeNegocio;

import javax.persistence.*;

@Entity
@Table(name = "T_INSCRIPCION")
public class Inscripcion {
	
	@Id
	@SequenceGenerator(name="secuencia_idInscripcion", sequenceName="seq5", allocationSize = 1, initialValue = 1000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="secuencia_idInscripcion")
	@Column(name = "INSCRIPCION_ID", nullable = false)
	private long id;	//TODO Cambiar la DB para añadir esta columna
	
	//@Column(name = "INSCRIPCION_EQUIPO", nullable = true)
	//public int equipo; 		//TODO Atributo agregado, para saber a que equipo pertenece el inscripto.
		
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "PARTIDO_ID", nullable = false)
	private Partido partido_inscripto;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "JUGADOR_ID", nullable = false)
	private Jugador jugador_inscripto;

	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "MODALIDAD_ID", nullable = false)
	private IModalidad modalidad;
	
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
