package clasesDeNegocio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import clasesManejadoras.ConexionDB;
import ServiciosExternos.IMensajero;
import ServiciosExternos.MockMensajero;

@Entity
@Table(name = "T_ADMINISTRADOR")
public class Admin extends Usuario {
	
	@Id
	@SequenceGenerator(name="secuencia_idAdmin", sequenceName="seq1", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="secuencia_idAdmin")
	@Column(name = "ADMINISTADOR_ID", nullable = false)
	private Long id;
	
	@Column(name = "ADMINISTADOR_USUARIO", length = 20, nullable = false)
	private String usuario;
	
	@Column(name = "ADMINISTRADOR_PASSWORD", length = 20, nullable = false)
	private String password = "admin";
	
	@OneToMany(fetch=FetchType.EAGER)
	@Cascade({CascadeType.ALL})
	@JoinColumn(name="ADMINISTRADOR_ID")
	private List<Propuesta>nuevas_propuestas = new ArrayList<Propuesta>();
	
	@Transient
	private IMensajero mensajero = new MockMensajero();
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	public String getUsuario() { return usuario; }
	public void setUsuario(String usuario) {this.usuario = usuario; }
	
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }

	public IMensajero getMensajero() { return mensajero; }
	public void setMensajero(IMensajero mensajero) { this.mensajero = mensajero; }

	public List<Propuesta> getNuevas_propuestas() { return nuevas_propuestas; }
	public void setNuevas_propuestas(List<Propuesta> nuevas_propuestas) { this.nuevas_propuestas = nuevas_propuestas; }
	public void agregar_propuesta(Propuesta propuesta){  this.nuevas_propuestas.add(propuesta);/*ConexionDB.guardar(this);*/ }
	
	public void aprobar_propuesta(Propuesta propuesta, Jugador nuevo_jugador, IModalidad modalidad){
		propuesta.estado_propuesta = "aprobada";
		ConexionDB.guardar(propuesta);
		propuesta.getJugador().agregar_amigo(propuesta.getAmigo());		
		nuevo_jugador.agregar_amigo(new Amigo(propuesta.getJugador().getNombre(),propuesta.getJugador().getApellido(),propuesta.getJugador().getEmail()));
		nuevo_jugador.inscribirse_a(propuesta.getPartido(), modalidad);
	}
	
	public Respuesta rechazar_propuesta(Propuesta propuesta, String razon){
		propuesta.estado_propuesta = "rechazada";
		propuesta.razon_rechazo = razon;
		ConexionDB.guardar(propuesta);
		Respuesta rta = new Respuesta();
		rta.setEsta_inscripto(false);
		rta.setMensaje(razon);
		return rta;
	}
	
	public void organizar_equipo(Partido partido, CriterioOrden[] criterios){
		CriterioOrden criterio = new CriterioCompuesto(criterios);
		criterio.ordenar(partido);
		//Collections.sort(partido.getTitulares(),new CriterioCompuesto(criterios));
	}
	
	public void dividir_equipos(Partido partido, IGeneradorEquipos generador){
		generador.generar_equipos(partido);
	}
	
	public void confirmarPartido(Partido partido){
		partido.confirmar();
	}
}
