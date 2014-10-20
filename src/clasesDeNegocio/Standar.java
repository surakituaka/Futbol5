package clasesDeNegocio;

import java.util.Date;

public class Standar implements IModalidad{
	
	private Long id;
	private String inscripcion ="STANDAR";
	private Date fecha;
	

	
	public String getInscripcion() {
		return inscripcion;
	}
	public void setInscripcion(String texto) {
		inscripcion = texto;
	}
	
	public Date getFecha() {
		return fecha;
	}

	
	public void setFecha(Date fecha) {
		this.fecha=fecha;		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

}
