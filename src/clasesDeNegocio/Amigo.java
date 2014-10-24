package clasesDeNegocio;

import javax.persistence.*;

@Entity
@Table(name = "T_AMIGO")
public class Amigo {
	
	@Id
	@SequenceGenerator(name="secuencia_idAmigo", sequenceName="seq2", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="secuencia_idAmigo")
	@Column(name = "AMIGO_ID", nullable = false)
	private Long id;	//TODO Cambiar la DB para añadir esta columna
	
	@Column(name = "AMIGO_NOMBRE", length = 20, nullable = false)
	private String nombre;
	
	@Column(name = "AMIGO_APELLIDO", length = 20, nullable = false)
	private String apellido;
	
	@Column(name = "AMIGO_EMAIL", length = 30, nullable = false)
	private String email;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Amigo() {}
	
	public Amigo(String nombre, String apellido, String email) {
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setEmail(email);
	}
	
	public boolean concuerdoCon(Amigo amigo){
		if(!amigo.getNombre().matches(nombre))
			return false;
		if(!amigo.getApellido().matches(apellido))
			return false;
		if(!amigo.getEmail().matches(email))
				return false;		
		return true;
	}
}
