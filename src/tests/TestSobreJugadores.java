package tests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

import clasesDeNegocio.*;
import clasesManejadoras.ConexionDB;
import ServiciosExternos.*;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestSobreJugadores {
	
	@Test
	public void standar_puede_entrar_a_partido() {
		IMensajero mensajero = new MockMensajero();
		Partido partido = new Partido(mensajero);
		partido.setPartido_nombre("Partido 1");
		Jugador jugador = new Jugador();
				
		jugador.inscribirse_a(partido, new Standar());
		
		assertTrue(jugador.estoyInscripto(partido));
	}
	
	@Test
	public void solidario_puede_entrar_a_partido() {
		
		IMensajero mensajero = new MockMensajero();
		
		Partido partido = new Partido(mensajero);
		partido.setPartido_nombre("Partido 1");
		Jugador jugador = new Jugador();
		jugador.inscribirse_a(partido, new Solidaria());
		
		assertTrue(jugador.estoyInscripto(partido));
	}
	
	@Test
	public void condicional_puede_entrar_a_partido() {
		
		IMensajero mensajero = new MockMensajero();
		
		Partido partido = new Partido(mensajero);
		partido.setPartido_nombre("Partido 1");
		Jugador jugador = new Jugador();
			
		jugador.inscribirse_a(partido,new Condicional("condicion loca"));
		
		assertTrue(jugador.estoyInscripto(partido));
	}
	
	/*@Test
	@Deprecated
	public void standar_no_puede_entrar_a_partido_por_estar_penalizado() {
		
		IMensajero mensajero = new MockMensajero();
		
		Partido partido = new Partido(mensajero);
		Jugador jugador = new Jugador();
		
		jugador.setTipo(new Standar());
		jugador.agregar_penalizacion(new Penalizacion(new GregorianCalendar(), "porque si"));
		Respuesta respuesta = new Respuesta();
		
		respuesta=jugador.inscribirse_a(partido);
		
		assertFalse(respuesta.getEsta_inscripto());
	}*/
	
	
	@Test
	public void condicional_no_puede_entrar_a_partido_por_baja_prioridad() {
		
		IMensajero mensajero = new MockMensajero();
		
		Partido partido = new Partido(mensajero);
		partido.setPartido_nombre("Partido Loco");
		for(int i = 0;i<10;i++){
			Jugador j = new Jugador();
			if(i<9)
				j.inscribirse_a(partido, new Standar());
			else
				j.inscribirse_a(partido, new Solidaria());
//			j.setPrioridad(10.0);
		}
		Jugador jugador = new Jugador();
//		jugador.setPrioridad(1.0);		
		jugador.inscribirse_a(partido,new Condicional("Si me dan el arco"));
		
		assertFalse(jugador.estoyInscripto(partido));
	}
	@Test
	public void standar_no_puede_entrar_a_partido_por_estar_lleno() {
		
		IMensajero mensajero = new MockMensajero();
		
		Partido partido = new Partido(mensajero);
		partido.setPartido_nombre("Partido Loco");
		for(int i = 0;i<10;i++){
			Jugador j = new Jugador();
			j.inscribirse_a(partido, new Standar());
//			j.setPrioridad(10.0);
		}
		Jugador jugador = new Jugador();
//		jugador.setPrioridad(1.0);		
		jugador.inscribirse_a(partido,new Standar());
		
		assertFalse(jugador.estoyInscripto(partido));
	}
	
	@Test
	public void no_entra_nadie_porque_hay_10_standars() {
		
		IMensajero mensajero = new MockMensajero();
		
		Partido partido = new Partido(mensajero);
		partido.setPartido_nombre("Partido Loco");
		for(int i = 0;i<15;i++){
			Jugador j = new Jugador();
//			j.setPrioridad(10.0);
			j.inscribirse_a(partido, new Standar());
		}
		assertTrue(partido.getInscripciones().size()==10);
	}
	
	/*@Test
	@Deprecated
	public void todos_entran_porque_hay_9_standars() {
		
		IMensajero mensajero = new MockMensajero();
		
		Partido partido = new Partido(mensajero);
		
		for(int i = 0;i<9;i++){
			Jugador j = new Jugador();
			j.setTipo(new Standar());
//			j.setPrioridad(10.0);
			j.inscribirse_a(partido);
		}
		for(int i = 0;i<9;i++){
			Jugador j = new Jugador();
			j.setTipo(new Solidaria());
//			j.setPrioridad(10.0);
			j.inscribirse_a(partido);
		}
		
		assertTrue(partido.getJugadores().size()==18);
	}*/
	
	@Test
	public void standar_puede_entrar_a_partido_por_tener_mas_prioridad() {
		
		IMensajero mensajero = new MockMensajero();
		Partido partido = new Partido(mensajero);
		partido.setPartido_nombre("Partido Loco");

		for(int i = 0;i<9;i++){
			Jugador j = new Jugador();
			if(i<9)
				j.inscribirse_a(partido, new Standar());
			else
				j.inscribirse_a(partido, new Solidaria());
		}
		
		Jugador jugador_standar = new Jugador();
		jugador_standar.inscribirse_a(partido, new Standar());
		
		assertTrue(jugador_standar.estoyInscripto(partido));
	}
	
	@Test
	public void jugador_es_penalizado_por_no_tener_reemplazo(){
		
		Jugador jugador = new Jugador();
		jugador.setUsuario("j1");
		IMensajero mensajero = new MockMensajero();
		
		Partido partido = new Partido(mensajero);
		partido.setPartido_nombre("Partido Loco");

		jugador.inscribirse_a(partido, new Standar());
		
		jugador.bajarse_de(partido);
		
		assertTrue(jugador.getPenalizaciones().size()==1);
	}
	
	@Test
	public void jugador_no_es_penalizado(){
		
		Jugador jugador = new Jugador();
		jugador.setUsuario("j1");
		Jugador reemplazo = new Jugador();
		reemplazo.setUsuario("j2");
		IMensajero mensajero = new MockMensajero();
		
		Partido partido = new Partido(mensajero);
		partido.setPartido_nombre("Partido Loco");

		jugador.inscribirse_a(partido, new Standar());
		
		jugador.bajarse_de(partido,reemplazo);
		
		assertTrue(jugador.getPenalizaciones().isEmpty());
		assertTrue(partido.getInscripciones().size()==1);
		assertTrue(partido.getInscripciones().get(0).getJugador_inscripto()==reemplazo);
		assertTrue(partido.getInscripciones().get(0).getModalidad().getInscripcion().matches("STANDAR"));
	}
	
	
	
	@Test
	public void jugador_puede_calificar(){
		
		Jugador calificador = new Jugador();
		Jugador calificado = new Jugador();
		IMensajero mensajero = new MockMensajero();
		Partido partido = new Partido(mensajero);
		
		calificador.calificar(calificado, 6, "Jugo bien", partido);
		
		assertTrue(calificador.getCalificaciones().size()>0);
	}
	
	@Test
	public void jugador_puede_proponer_amigo(){
		
		Jugador jugador = new Jugador();
		Amigo amigo = new Amigo("pepe", "grillo", "email@email.com");
		IMensajero mensajero = new MockMensajero();
		Partido partido = new Partido(mensajero);
		Admin admin = new Admin();
		IOrden orden = new OrdenNuevoJugador();
		
		((OrdenNuevoJugador) orden).setAdmin(admin);
		
		jugador.setProposicion(orden);
		
		jugador.proponer_jugador(amigo, partido);
		
		assertTrue(admin.getNuevas_propuestas().size()==1);
	}
	
	/*
	
	@Test
	public void persistencia_jugador() throws ParseException{
		
		ConexionDB conexionDB = new ConexionDB();
		
		DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		Date date = formatter.parse("01/29/02");
		
		Jugador jugador = new Jugador();
		jugador.setUsuario("pepe333");
		jugador.setPassword("Pepeneitor");
		jugador.setNombre("Pepe");
		jugador.setApellido("Lopez");
		jugador.setEmail("pepel3@algo.com");
		jugador.setFecha_nacimiento(date);
		
		conexionDB.guardar(jugador);
		
		List<Jugador> jugadores = conexionDB.listaJugadores();
		
		assertTrue(jugadores.size()!=0);
		
	}
	*/
	/*
	@Test
	public void persistenciajugador_penalizado() throws ParseException{
		
		ConexionDB conexionDB = new ConexionDB();
		
		DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		Date date = formatter.parse("01/29/02");
		
		
		Jugador jugador = new Jugador();
		jugador.setUsuario("carlongo");
		jugador.setPassword("pirulin");
		jugador.setNombre("Carlos");
		jugador.setApellido("Perez");
		jugador.setEmail("CP@algo.com");
		jugador.setFecha_nacimiento(date);
		
		Penalizacion penalizacion = new Penalizacion(new GregorianCalendar(), "sos muy garca",jugador);
		jugador.getPenalizaciones().add(penalizacion);
		conexionDB.guardar(jugador);
		
		
		List<Jugador> jugadores = conexionDB.listaJugadores();
		
		assertTrue(jugadores.size()!=0);
		
	}
	*/
	
	/*
	@Test
	public void persistencia_calificacion() throws ParseException{
		
		ConexionDB conexionDB = new ConexionDB();
		
		List<Jugador> jugadores = conexionDB.listaJugadores();
		
		List<Partido> partidos = conexionDB.listaPartidos();
		
		DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		Date date = formatter.parse("01/29/02");
		
		jugadores.get(0).getCalificaciones().add(new Calificacion(jugadores.get(0), jugadores.get(1), 6, "jugo bien", partidos.get(0), date));
		

		conexionDB.guardar(jugadores.get(0));
		
		
		List<Calificacion> calificaciones = conexionDB.listaCalificaciones();
		
		
	}
	*/
	
	
	
}
