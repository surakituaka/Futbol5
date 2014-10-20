package clasesDeNegocio;


import java.util.Date;

public class Solidaria implements IModalidad{

	private Long id;
	private String inscripcion ="SOLIDARIA";
	private Date fecha = new Date();
	
	
	public Date getFecha() {
		return fecha;
	}
	public void setInscripcion(String texto) {
		inscripcion = texto;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	public String getInscripcion() {
		return inscripcion;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
}
