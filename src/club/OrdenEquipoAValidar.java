package club;

public class OrdenEquipoAValidar implements IEquipoAValidar{
	
	private Admin admin;
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	@Override
	public void orden(Partido partido) {
		admin.agregar_partido_validado(partido);
		
	}

}
