package tests;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Test;

import club.Admin;
import club.Condicional;
import club.IOrden;
import club.OrdenNuevoJugador;
import club.Penalizacion;
import club.Solidaria;
import club.Standar;
import club.Jugador;
import club.Partido;
import club.Respuesta;


public class TestSobreJugadores {
	
	@Test
	public void standar_puede_entrar_a_partido() {
		
		Partido partido = new Partido();
		Jugador jugador = new Jugador();
		
		jugador.setTipo(new Standar());
		
		Respuesta respuesta = new Respuesta();
		
		respuesta=jugador.getTipo().inscribirse(partido, jugador);
		
		assertTrue(respuesta.getEsta_inscripto());
	}
	
	@Test
	public void solidario_puede_entrar_a_partido() {
		
		Partido partido = new Partido();
		Jugador jugador = new Jugador();
		
		jugador.setTipo(new Solidaria());
		
		Respuesta respuesta = new Respuesta();
		
		respuesta=jugador.getTipo().inscribirse(partido, jugador);
		
		assertTrue(respuesta.getEsta_inscripto());
	}
	
	@Test
	public void condicional_puede_entrar_a_partido() {
		
		Partido partido = new Partido();
		Jugador jugador = new Jugador();
		
		jugador.setTipo(new Condicional("condicion loca"));
		
		Respuesta respuesta = new Respuesta();
		
		respuesta=jugador.getTipo().inscribirse(partido, jugador);
		
		assertTrue(respuesta.getEsta_inscripto());
	}
	
	@Test
	public void standar_no_puede_entrar_a_partido_por_estar_penalizado() {
		
		Partido partido = new Partido();
		Jugador jugador = new Jugador();
		
		jugador.setTipo(new Standar());
		jugador.agregar_penalizacion(new Penalizacion(new GregorianCalendar(), "porque si"));
		Respuesta respuesta = new Respuesta();
		
		respuesta=jugador.getTipo().inscribirse(partido, jugador);
		
		assertFalse(respuesta.getEsta_inscripto());
	}
	
	
	@Test
	public void standar_no_puede_entrar_a_partido_por_estar_lleno() {
		
		Partido partido = new Partido();
		
		for(int i = 0;i<10;i++){
			Jugador j = new Jugador();
			j.setTipo(new Standar());
//			j.setPrioridad(10.0);
			partido.getJugadores().add(j);
		}
		
		Jugador jugador = new Jugador();
		jugador.setTipo(new Standar());
//		jugador.setPrioridad(1.0);
		Respuesta respuesta = new Respuesta();
		
		respuesta=jugador.getTipo().inscribirse(partido, jugador);
		
		assertFalse(respuesta.getEsta_inscripto());
	}
	
	@Test
	public void standar_puede_entrar_a_partido_por_tener_mas_prioridad() {
		
		Partido partido = new Partido();
		
		for(int i = 0;i<9;i++){
			Jugador j = new Jugador();
			j.setTipo(new Standar());
//			j.setPrioridad(1.0);
			partido.getJugadores().add(j);
		}
		
		Jugador j = new Jugador();
		j.setTipo(new Solidaria());
//		j.setPrioridad(1.0);
		partido.getJugadores().add(j);
		
		Jugador jugador = new Jugador();
		jugador.setTipo(new Standar());
//		jugador.setPrioridad(2.0);
		Respuesta respuesta = new Respuesta();
		
		respuesta=jugador.getTipo().inscribirse(partido, jugador);
		
		assertTrue(respuesta.getEsta_inscripto());
	}
	
	@Test
	public void jugador_es_penalizado_por_no_tener_reemplazo(){
		
		Jugador jugador = new Jugador();
		Partido partido = new Partido();
		
		partido.agregar_jugador(jugador);
		
		jugador.bajarse_de(partido);
		
		assertTrue(jugador.getPenalizaciones().size()==1);
	}
	
	@Test
	public void jugador_no_es_penalizado(){
		
		Jugador jugador = new Jugador();
		Jugador reemplazo = new Jugador();
		Partido partido = new Partido();
		
		partido.agregar_jugador(jugador);
		
		jugador.bajarse_de(partido,reemplazo);
		
		assertTrue(jugador.getPenalizaciones().isEmpty());
		assertTrue((partido.getJugadores().size()==1) && (partido.getJugadores().get(0))==reemplazo);
	}
	
	
	
	@Test
	public void jugador_puede_calificar(){
		
		Jugador calificador = new Jugador();
		Jugador calificado = new Jugador();
		Partido partido = new Partido();
		
		calificador.calificar(calificado, 6, "Jugo bien", partido);
		
		assertTrue(partido.getCalificaciones().size()>0);
	}
	
	@Test
	public void jugador_puede_proponer_amigo(){
		
		Jugador jugador = new Jugador();
		Jugador amigo = new Jugador();
		Partido partido = new Partido();
		Admin admin = new Admin();
		IOrden orden = new OrdenNuevoJugador();
		
		((OrdenNuevoJugador) orden).setAdmin(admin);
		
		jugador.setPropocicion(orden);
		
		jugador.proponer_jugador(amigo, partido);
		
		assertTrue(admin.getNuevas_propuestas().containsKey(amigo));
	}
	
	
}
