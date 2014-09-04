package clasesDeNegocio;


import java.util.Date;

public class Solidaria implements IModalidad{

	private final String inscripcion ="SOLIDARIA";
	private Date fecha = new Date();
	
	
	public Date getFecha() {
		return fecha;
	}
	
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	public String getInscripcion() {
		return inscripcion;
	}
}
