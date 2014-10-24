package clasesDeNegocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.IndexColumn;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "T_EQUIPO")
public class Equipo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 866587641584392083L;

	@Id
	@SequenceGenerator(name="secuencia_idEquipo", sequenceName="seq4", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="secuencia_idEquipo")
	@Column(name = "EQUIPO_ID", nullable = false)
	private Long id;
	
	@Id
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	//@JoinColumns( {
       // @JoinColumn(name = "EQUIPO_ID", referencedColumnName = "EQUIPO_ID"),
        @JoinColumn(name = "PARTIDO_ID", referencedColumnName = "PARTIDO_ID")
	//})
	private Partido partido;
	
	@Column(name = "EQUIPO_NOMBRE", length = 20, nullable = true)
	public String nombre;	//TODO le agregue este atributo.
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@IndexColumn(name="IDX")
	private List<Jugador>jugadores = new ArrayList<Jugador>();

	public Equipo(){}
	public Equipo(Partido match){
		partido = match;
	}
	
	public List<Jugador> getJugadores() { return jugadores;	}
	public void setJugadores(List<Jugador> jugadores) { this.jugadores = jugadores;	}
	public void addJugador(Jugador player){ jugadores.add(player); }
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getNombre() { return nombre; }
	public void setNombre(String texto) { this.nombre = texto; }	
	
}
