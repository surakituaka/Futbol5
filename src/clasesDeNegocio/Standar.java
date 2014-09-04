package clasesDeNegocio;

import java.util.Date;

public class Standar implements IModalidad{
	
	private final String inscripcion ="STANDAR";
	private Date fecha;
	

	
	public String getInscripcion() {
		return inscripcion;
	}

	
	public Date getFecha() {
		return fecha;
	}

	
	public void setFecha(Date fecha) {
		this.fecha=fecha;		
	}

}
