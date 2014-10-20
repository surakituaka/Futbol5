package clasesDeNegocio;

import java.util.Date;

public interface IModalidad {
//	public Respuesta inscribirse(Partido partido, Jugador jugador);
	public Long getId();
	public void setId(Long id);
	public String getInscripcion();
	public void setInscripcion(String texto);
	public Date getFecha();
	public void setFecha(Date fecha);
}
