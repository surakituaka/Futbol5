package club;

import java.util.GregorianCalendar;

public class Penalizacion {
	
	private GregorianCalendar fecha;
	private String motivo;
	
	public GregorianCalendar getFecha() {
		return fecha;
	}
	
	public void setFecha(GregorianCalendar fecha) {
		this.fecha = fecha;
	}
	
	public String getMotivo() {
		return motivo;
	}
	
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	public Penalizacion(GregorianCalendar fecha, String motivo) {
		super();
		this.fecha = fecha;
		this.motivo = motivo;
	}

}
