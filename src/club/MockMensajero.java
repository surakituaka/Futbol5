package club;

public class MockMensajero implements IMensajero {

	@Override
	public String enviar_mensaje(String emisor, String receptor, String mensaje) {
		// la la la, envio mensaje, tru ru ru
		return mensaje;
	}
}
