package clasesDeNegocio;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "T_CALIFICACION")
public class Calificacion {
	
	@Id
	
	private Long id;	//TODO Agregar dato en tabla
	
	
	private Jugador calificador;
	
	
	private Jugador calificado;
	
	@Column(name = "ADMINISTADOR_ID", length = 20, nullable = false)
	private Integer calificacion;
	
	@Column(name = "ADMINISTADOR_ID", length = 20, nullable = false)
	private String critica;
	
	
	private Partido partido;
	
	
	private Date fecha;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Jugador getCalificador() {
		return calificador;
	}
	
	public void setCalificador(Jugador calificador) {
		this.calificador = calificador;
	}
	
	public Jugador getCalificado() {
		return calificado;
	}
	
	public void setCalificado(Jugador calificado) {
		this.calificado = calificado;
	}
	
	public Integer getCalificacion() {
		return calificacion;
	}
	
	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}
	
	public String getCritica() {
		return critica;
	}
	
	public void setCritica(String critica) {
		this.critica = critica;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	
	public Calificacion(){
		super();
	}
	
	
	public Calificacion(Jugador calificador, Jugador calificado, Integer calificacion, String critica, Partido partido,Date fecha){
		super();
		this.calificador=calificador;
		this.calificado=calificado;
		this.calificacion=calificacion;
		this.critica=critica;
		this.partido=partido;
		this.fecha=fecha;
		
	}
	
	public boolean estaCalificado(Jugador jugador){
		return getCalificado().getUsuario().matches(jugador.getUsuario());
	}
}
