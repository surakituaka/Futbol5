package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import clasesDeNegocio.*;
import ServiciosExternos.*;
import static org.mockito.Mockito.*;

public class TestSobreMensajero {

	@Test
	public void partido_envia_mensaje_a_admin_cuando_esta_lleno() {
		
		IMensajero mensajero = mock(IMensajero.class);
		when(mensajero.enviar_mensaje("partido","admin","partido lleno")).thenReturn("Mensaje enviado al admin");
		
		Partido partido = new Partido(mensajero);
		partido.setMensajero(mensajero);
		
		for(int i = 0;i<10;i++){
			Jugador j = new Jugador();
			j.inscribirse_a(partido, new Standar());
		}
		
		String rta = null;
		
		if(partido.validar10()){
			rta = partido.enviar_mensaje("partido","admin","partido lleno");
		}
		
		assertEquals(rta,"Mensaje enviado al admin");
	}
	
	@Test
	public void partido_envia_mensaje_a_admin_cuando_ya_no_esta_lleno() {
		
		IMensajero mensajero = mock(IMensajero.class);
		when(mensajero.enviar_mensaje("partido","admin","partido lleno")).thenReturn("Mensaje bueno enviado al admin");
		when(mensajero.enviar_mensaje("partido","admin","se fue alguien del partido")).thenReturn("Mensaje malo enviado al admin");
		
		Partido partido = new Partido(mensajero);
		partido.setPartido_nombre("Partido Loco");
		
		Jugador j= null;
		for(int i = 0;i<10;i++){
			j = new Jugador();
			j.setUsuario("j"+ Integer.toString(i));
			j.inscribirse_a(partido, new Standar());
		}
		
		String rta = null;
		
		if(partido.validar10()){
			rta = partido.enviar_mensaje("partido","admin","partido lleno");
		}
		assertEquals(rta,"Mensaje bueno enviado al admin");
		
		j.bajarse_de(partido);
		
		if(!partido.validar10()){
			rta = partido.enviar_mensaje("partido","admin","se fue alguien del partido");
		}
		
		assertEquals(rta,"Mensaje malo enviado al admin");
		
		/*
		Admin a =new Admin();
		CriterioOrden[] criterios ={new Handicap() ,new Promedio(3)};
		a.organizar_equipo(partido, criterios);
		*/
		}	

}
