package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import club.Admin;
import club.CriterioOrden;
import club.Handicap;
import club.IMensajero;
import club.Jugador;
import club.Partido;
import club.Promedio;
import club.Standar;
import static org.mockito.Mockito.*;

public class TestSobreMensajero {

	@Test
	public void partido_envia_mensaje_a_admin_cuando_esta_lleno() {
		
		IMensajero mensajero = mock(IMensajero.class);
		when(mensajero.enviar_mensaje("partido","admin","partido lleno")).thenReturn("Mensaje enviado al admin");
		
		Partido partido = new Partido();
		partido.setMensajero(mensajero);
		
		for(int i = 0;i<10;i++){
			Jugador j = new Jugador();
			j.setTipo(new Standar());
			partido.getJugadores().add(j);
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
		
		Partido partido = new Partido();
		partido.setMensajero(mensajero);
		
		Jugador j= null;
		for(int i = 0;i<10;i++){
			j = new Jugador();
			j.setTipo(new Standar());
			partido.getJugadores().add(j);
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
