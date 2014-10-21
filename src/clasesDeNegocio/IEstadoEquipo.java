package clasesDeNegocio;

import java.io.Serializable;

import javax.persistence.*;


public interface IEstadoEquipo extends Serializable{
	public String getEstado(Partido partido);
	public String getSiguienteEstado(Partido partido);
	public Long getId();
	public void setId(Long id);
	public String getDescripcion();
	public void setDescripcion(String descripcion);
}
