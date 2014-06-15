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
	public void orden(Jugador jugador, Partido partido) {
		this.admin.agregar_propuesta(jugador,partido);
	}

}
