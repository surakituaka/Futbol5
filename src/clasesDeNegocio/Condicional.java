package clasesDeNegocio;

import java.util.Date;

public class Condicional implements IModalidad{
	
	private Long id;
	private String inscripcion ="CONDICIONAL";
	private String condicion;
	private Date fecha;
	

	public String getInscripcion() {
		return inscripcion;
	}
	public void setInscripcion(String texto) {
		inscripcion = texto;
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
	
	public Condicional(){
		super();
		setCondicion("Condicion Estandar");
	}
	
	public Condicional(String condicion){
		
		super();
		setCondicion(condicion);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
