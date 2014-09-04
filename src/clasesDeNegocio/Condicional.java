package clasesDeNegocio;

import java.util.Date;

public class Condicional implements IModalidad{
	
	private final String inscripcion ="CONDICIONAL";
	private String condicion;
	private Date fecha;
	

	public String getInscripcion() {
		return inscripcion;
	}

	public String getCondicion() {
		return condicion;
	}
	
	
	public Date getFecha() {
		return fecha;
	}

	
	public void setFecha(Date fecha) {
		this.fecha = fecha;		
	}
	
	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}
	
	
	public Condicional(String condicion){
		
		super();
		setCondicion(condicion);
	}
	
}
