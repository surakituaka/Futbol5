package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import clasesDeNegocio.*;
import ServiciosExternos.*;

public class TestSobrePartidos {

	@Test
	public void ordenar_equipo_handicap(){
		
		IMensajero mensajero = new MockMensajero();
		Partido partido = new Partido(mensajero);
		for(int i=0;i < 8;i++){
			Jugador jugador = new Jugador();
			jugador.setUsuario("j"+ Integer.toString(i));
			jugador.setNombre(Integer.toString(i));
			jugador.setHandicap(5);
			jugador.inscribirse_a(partido,new Standar());
		}
		Jugador jugador_s = new Jugador();
		jugador_s.setUsuario("Primero");
		jugador_s.setHandicap(10);
		jugador_s.inscribirse_a(partido,new Standar());
		Jugador jugador_u = new Jugador();
		jugador_u.setUsuario("Ultimo");
		jugador_u.setHandicap(1);
		jugador_u.inscribirse_a(partido,new Standar());
		CriterioOrden[] criterios = {new Handicap()};

		Admin admin = new Admin();
		admin.organizar_equipo(partido, criterios);
		
		String nombrePrimero = partido.getInscripciones().get(0).getJugador_inscripto().getUsuario();
		String nombreUltimo = partido.getInscripciones().get(9).getJugador_inscripto().getUsuario();
		assertTrue(nombrePrimero.equals("Primero") && nombreUltimo.equals("Ultimo"));
	}

	@Test
	public void generar_equipo_tentativo_parimpar(){
		
		IMensajero mensajero = new MockMensajero();
		Partido partido = new Partido(mensajero);
		for(int i=0;i < 8;i++){
			Jugador jugador = new Jugador();
			jugador.setUsuario("j"+ Integer.toString(i));
			jugador.setNombre(Integer.toString(i));
			jugador.setHandicap(5);
			jugador.inscribirse_a(partido,new Standar());
		}
		Jugador jugadorConMejorHandicap = new Jugador();
		jugadorConMejorHandicap.setUsuario("Primero");
		jugadorConMejorHandicap.setHandicap(10);
		jugadorConMejorHandicap.inscribirse_a(partido,new Standar());

		Jugador segundoConMejorHandicap = new Jugador();
		segundoConMejorHandicap.setUsuario("Segundo");
		segundoConMejorHandicap.setHandicap(9);
		segundoConMejorHandicap.inscribirse_a(partido,new Standar());
		CriterioOrden[] criterios = {new Handicap()};
		Admin admin = new Admin();
		admin.organizar_equipo(partido, criterios);
		
		admin.dividir_equipos(partido, new EquipoParImpar());
		
		boolean PrimeroEsPrimeroEquipo1 = partido.getEquipo1().getJugadores().get(0).getUsuario().equals("Primero");
		boolean SegundoEsPrimeroEquipo2 = partido.getEquipo2().getJugadores().get(0).getUsuario().equals("Segundo");
		
		assertTrue(PrimeroEsPrimeroEquipo1 && SegundoEsPrimeroEquipo2);
	}
	
	@Test
	public void generar_equipo_tentativo_suma11(){
		
		IMensajero mensajero = new MockMensajero();
		Partido partido = new Partido(mensajero);
		for(int i=0;i < 8;i++){
			Jugador jugador = new Jugador();
			jugador.setUsuario("j"+ Integer.toString(i));
			jugador.setNombre(Integer.toString(i));
			jugador.setHandicap(5);
			jugador.inscribirse_a(partido,new Standar());
		}
		Jugador jugadorConMejorHandicap = new Jugador(); //Deberia ser ordenado como primero
		jugadorConMejorHandicap.setUsuario("Primero");
		jugadorConMejorHandicap.setHandicap(10);
		jugadorConMejorHandicap.inscribirse_a(partido,new Standar());

		Jugador jugadorConPeorHandicap = new Jugador(); //Deberia ser ordenado como ultimo
		jugadorConPeorHandicap.setUsuario("Segundo");
		jugadorConPeorHandicap.setHandicap(9);
		jugadorConPeorHandicap.inscribirse_a(partido,new Standar());
		CriterioOrden[] criterios = {new Handicap()};
		Admin admin = new Admin();
		admin.organizar_equipo(partido, criterios);
		
		admin.dividir_equipos(partido, new EquipoSuma11());
		
		/* Ordena asi	e1 = (01) - (05) - (09) - (08) - (04)
						e2 = (10) - (06) - (02) - (03) - (07) 
		*/
		
		boolean PrimeroEsPrimeroEquipo1 = partido.getEquipo1().getJugadores().get(0).getUsuario().equals("Primero");
		boolean SegundoEsPrimeroEquipo2 = partido.getEquipo2().getJugadores().get(2).getUsuario().equals("Segundo");
		
		assertTrue(PrimeroEsPrimeroEquipo1 && SegundoEsPrimeroEquipo2);
	}
	

}
