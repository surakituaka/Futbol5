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
			jugador.setNombre(Integer.toString(i));
			jugador.setTipo(new Standar());
			jugador.setHandicap(5);
			jugador.inscribirse_a(partido);
		}
		Jugador jugador_s = new Jugador();
		jugador_s.setNombre("Primero");
		jugador_s.setHandicap(10);
		jugador_s.setTipo(new Standar());
		jugador_s.inscribirse_a(partido);
		Jugador jugador_u = new Jugador();
		jugador_u.setNombre("Ultimo");
		jugador_u.setHandicap(1);
		jugador_u.setTipo(new Standar());
		jugador_u.inscribirse_a(partido);
		CriterioOrden[] criterios = {new Handicap()};

		Admin admin = new Admin();
		admin.organizar_equipo(partido, criterios);
		
		String nombrePrimero = partido.getJugadores().get(0).getNombre();
		String nombreUltimo = partido.getJugadores().get(9).getNombre();
		assertTrue(nombrePrimero.equals("Primero") && nombreUltimo.equals("Ultimo"));
	}

	@Test
	public void generar_equipo_tentativo_parimpar(){
		
		IMensajero mensajero = new MockMensajero();
		Partido partido = new Partido(mensajero);
		for(int i=0;i < 8;i++){
			Jugador jugador = new Jugador();
			jugador.setNombre(Integer.toString(i));
			jugador.setTipo(new Standar());
			jugador.setHandicap(5);
			jugador.inscribirse_a(partido);
		}
		Jugador jugadorConMejorHandicap = new Jugador();
		jugadorConMejorHandicap.setNombre("Primero");
		jugadorConMejorHandicap.setHandicap(10);
		jugadorConMejorHandicap.setTipo(new Standar());
		jugadorConMejorHandicap.inscribirse_a(partido);

		Jugador segundoConMejorHandicap = new Jugador();
		segundoConMejorHandicap.setNombre("Segundo");
		segundoConMejorHandicap.setHandicap(9);
		segundoConMejorHandicap.setTipo(new Standar());
		segundoConMejorHandicap.inscribirse_a(partido);
		CriterioOrden[] criterios = {new Handicap()};
		Admin admin = new Admin();
		admin.organizar_equipo(partido, criterios);
		
		admin.dividir_equipos(partido, new EquipoParImpar());
		
		boolean PrimeroEsPrimeroEquipo1 = partido.getEquipo1().getJugadores().get(0).getNombre().equals("Primero");
		boolean SegundoEsPrimeroEquipo2 = partido.getEquipo2().getJugadores().get(0).getNombre().equals("Segundo");
		
		assertTrue(PrimeroEsPrimeroEquipo1 && SegundoEsPrimeroEquipo2);
	}
	
	@Test
	public void generar_equipo_tentativo_suma11(){
		
		IMensajero mensajero = new MockMensajero();
		Partido partido = new Partido(mensajero);
		for(int i=0;i < 8;i++){
			Jugador jugador = new Jugador();
			jugador.setNombre(Integer.toString(i));
			jugador.setTipo(new Standar());
			jugador.setHandicap(5);
			jugador.inscribirse_a(partido);
		}
		Jugador jugadorConMejorHandicap = new Jugador(); //Deberia ser ordenado como primero
		jugadorConMejorHandicap.setNombre("Primero");
		jugadorConMejorHandicap.setHandicap(10);
		jugadorConMejorHandicap.setTipo(new Standar());
		jugadorConMejorHandicap.inscribirse_a(partido);

		Jugador jugadorConPeorHandicap = new Jugador(); //Deberia ser ordenado como ultimo
		jugadorConPeorHandicap.setNombre("Segundo");
		jugadorConPeorHandicap.setHandicap(9);
		jugadorConPeorHandicap.setTipo(new Standar());
		jugadorConPeorHandicap.inscribirse_a(partido);
		CriterioOrden[] criterios = {new Handicap()};
		Admin admin = new Admin();
		admin.organizar_equipo(partido, criterios);
		
		admin.dividir_equipos(partido, new EquipoSuma11());
		
		/* Ordena asi	e1 = (01) - (05) - (09) - (08) - (04)
						e2 = (10) - (06) - (02) - (03) - (07) 
		*/
		
		boolean PrimeroEsPrimeroEquipo1 = partido.getEquipo1().getJugadores().get(0).getNombre().equals("Primero");
		boolean SegundoEsPrimeroEquipo2 = partido.getEquipo2().getJugadores().get(2).getNombre().equals("Segundo");
		
		assertTrue(PrimeroEsPrimeroEquipo1 && SegundoEsPrimeroEquipo2);
	}
	

}
