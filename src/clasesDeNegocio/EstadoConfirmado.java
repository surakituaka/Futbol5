package clasesDeNegocio;


import java.util.Calendar;

public class EstadoConfirmado implements IEstadoEquipo {
	
	/**
	 * 
	 */
	private Long id;
	private String descripcion = "Confirmado";
	private String estado = "Confirmado";
	private String estado_vencido = "Vencido";
	
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
