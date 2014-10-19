package tests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Test;

import clasesDeNegocio.*;
import clasesManejadoras.ConexionDB;
import Managers.*;
import ServiciosExternos.*;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.*;
import org.hibernate.cfg.*;

@SuppressWarnings("unused")
public class TestSobrePersistencia {

	@Test
	public void persistencia_jugador() throws ParseException{
		new ConexionDB();
		
		//ESTADO PARTIDO
		IEstadoEquipo estado = new EstadoConfirmado();
		estado.setDescripcion("CONFIRMAADO!");
		ConexionDB.guardar(estado);		
		estado = new EstadoPendiente();
		estado.setDescripcion("PENDIENTE :(");
		ConexionDB.guardar(estado);
		
		//Preparo una fecha para usar
		DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		Date date = formatter.parse("01/29/02");
		
		//AMIGO - Se guarda cuando guardo el jugador.
		Amigo nuevo = new Amigo();
		nuevo.setNombre("Lucas2");
		nuevo.setApellido("Martinez");
		nuevo.setEmail("c@d.com");		
		
		//PENALIZACION - Se guarda cuando guardo el jugador - Tambien deberian estar ordenadas.
		Penalizacion penal = new Penalizacion();
		penal.setMotivo("Por GIL");
		penal.setFecha(date);
		//El jugador lo seteo despues de crearlo.
		Penalizacion penal2 = new Penalizacion();
		penal2.setMotivo("Por GIL, otra vez");
		penal2.setFecha(date);
		
		//JUGADOR
		Jugador nuevo2 = new Jugador();
		nuevo2.setUsuario("pala");
		nuevo2.setPassword("1234");
		nuevo2.setNombre("Ari");
		nuevo2.setApellido("Pereza");
		nuevo2.setFecha_nacimiento(date);
		nuevo2.setHandicap(7);
		nuevo2.setEmail("m@s.com");
		nuevo2.agregar_amigo(nuevo);
		nuevo2.agregar_penalizacion(penal);
		nuevo2.agregar_penalizacion(penal2);
		
		//Agrego el jugador a la penalizacion.
		penal.setJugador(nuevo2);		
		penal2.setJugador(nuevo2);
		
		ConexionDB.guardar(nuevo2);
		
		
		assertTrue(true);
	}
}
