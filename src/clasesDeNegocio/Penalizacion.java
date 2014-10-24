package clasesDeNegocio;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_PENALIZACION")
public class Penalizacion {
	
	@Id
	@SequenceGenerator(name="secuencia_idPenalizacion", sequenceName="seq8", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="secuencia_idPenalizacion")
	@Column(name = "PENALIZACION_ID", nullable = false)
	private Long id;
	
	@Column(name = "PENALIZACION_MOTIVO", length = 50, nullable = false)
	private String motivo;
	
	@Column(name = "PENALIZACION_FECHA", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date fecha;
		
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "JUGADOR_ID", nullable = false)
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
