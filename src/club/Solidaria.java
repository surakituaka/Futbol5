package club;


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
	
	@Override
	public String getInscripcion() {
		return inscripcion;
	}
	
	
}
