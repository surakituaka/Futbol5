package club;

public class OrdenNuevoJugador implements IOrden{
	
	private Admin admin;
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Override
	public void orden(Jugador amigo, Partido partido, Jugador jugador) {
		Propuesta propuesta = new Propuesta();
		propuesta.setAmigo(amigo);
		propuesta.setPartido(partido);
		propuesta.setJugador(jugador);
		
		this.admin.agregar_propuesta(propuesta);
	}

}
