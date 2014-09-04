package clasesDeNegocio;

public class Amigo {
	
	private String nombre;
	private String apellido;
	private String email;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Amigo(String nombre, String apellido, String email) {
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setEmail(email);
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
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
