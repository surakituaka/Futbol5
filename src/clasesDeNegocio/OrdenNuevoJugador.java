package clasesDeNegocio;

import clasesManejadoras.ConexionDB;

public class OrdenNuevoJugador implements IOrden{
	
	private Admin admin;
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	
	public void orden(Amigo amigo, Partido partido, Jugador jugador) {
		Propuesta propuesta = new Propuesta();
		propuesta.setModalidad(new Standar());
		propuesta.setAmigo(amigo);
		propuesta.setPartido(partido);
		propuesta.setJugador(jugador);		
		this.admin.agregar_propuesta(propuesta);
		//ConexionDB.guardar(propuesta);
	}

}
