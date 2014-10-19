package clasesDeNegocio;


import java.util.Calendar;

import javax.persistence.*;

//@Entity
//@DiscriminatorValue(value="EC")
public class EstadoConfirmado implements IEstadoEquipo {
	
	/**
	 * 
	 */
	//@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "ESTADO_ID", nullable = false)
	private Long id;
	
	//@Column(name = "ESTADO_DESCRIPCION", length = 20, nullable = false)
	private String descripcion = "Confirmado";
	
	//@Transient
	private String estado;
	//@Transient
	private String estado_vencido;
	
	public EstadoConfirmado(){
		estado = "Confirmado";
		estado_vencido = "Vencido";
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado(Partido partido) {
		// TODO Auto-generated method stub
		if(partido.getFecha().before(Calendar.getInstance().getTime()))
			return estado_vencido;
		return estado;
	}
	
	public String getSiguienteEstado(Partido partido) {
		return " ";
	}
}
