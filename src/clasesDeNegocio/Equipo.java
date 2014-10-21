package clasesDeNegocio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "T_EQUIPO")
public class Equipo {
	
	@Id
	@SequenceGenerator(name="secuencia_idEquipo", sequenceName="seq4", allocationSize = 1, initialValue = 1000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="secuencia_idEquipo")
	@Column(name = "EQUIPO_ID", nullable = false)
	private Long id;
	
	@Column(name = "EQUIPO_NOMBRE", length = 20, nullable = true)
	public String nombre;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@IndexColumn(name="IDX")
	private List<Jugador>jugadores = new ArrayList<Jugador>();

	public List<Jugador> getJugadores() { return jugadores;	}
	public void setJugadores(List<Jugador> jugadores) { this.jugadores = jugadores;	}
	public void addJugador(Jugador player){ jugadores.add(player); }
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getNombre() { return nombre; }
	public void setNombre(String texto) { this.nombre = texto; }	
	
}
