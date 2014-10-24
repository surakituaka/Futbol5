package GUI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ServiciosExternos.IMensajero;
import ServiciosExternos.MockMensajero;
import clasesDeNegocio.*;
import clasesManejadoras.ConexionDB;

//Aca se cargan TODOS los harcodeos de la DB dinamica.
//Esta estructura aconsejo pasarla por parametro cada vez que se cargue una nueva ventana.

//Valga las redundancias, estaría bueno que admin sea un singleton.
public class Global {
	public List<Jugador> jugadores = new ArrayList<Jugador>();
	public List<Partido> partidos = new ArrayList<Partido>();
	public Admin administrador;
	public IMensajero mensajero;
	public List<CriterioOrden> criterios = new ArrayList<CriterioOrden>();
	public List<IGeneradorEquipos> generadores_equipo = new ArrayList<IGeneradorEquipos>();
	private SimpleDateFormat formato_fecha = new SimpleDateFormat("dd/MM/yy");
	
	public Global(){
		
		/*
		//Cargamos al Admin.
		administrador = new Admin();
		administrador.setUsuario("Admin");
		administrador.setEmail("system@thegrid.com");
		administrador.setMensajero(new MockMensajero());
		administrador.setPassword("TheGrid");
		*/
		administrador = ConexionDB.noListaAdmin();
			
		//Creamos los criterios de ordenamiento
		criterios.add(new Handicap());
		criterios.add(new Promedio(10));
		criterios.add(new PromedioUnitario());
		
		
		//Creamos los Generadores de Equipo
		generadores_equipo.add(new EquipoParImpar());
		generadores_equipo.add(new EquipoSuma11());

		//Creemos 1 partido
		Partido partidito = new Partido(administrador.getMensajero());
		partidito.setPartido_nombre("partidito");
		partidito.setLugar("Lugano");
		try {
			partidito.setFecha((new SimpleDateFormat("dd/MM/yy")).parse("12/12/14"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		partidos.add(partidito);
		
		//Partido para chequear criticas - NO USAR PARA NADA MAS
		Partido partido_criticas = new Partido(administrador.getMensajero());
		partido_criticas.setPartido_nombre("Critias");
		partido_criticas.setLugar("Lugano");
		try {
			partido_criticas.setFecha((new SimpleDateFormat("dd/MM/yy")).parse("25/12/14"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		partidos.add(partido_criticas);	
		
		//Levantamos de la DB
		//for ( Partido temporal :  ConexionDB.listaPartidos()) agregarPartido(temporal);		
		partidos.addAll(ConexionDB.listaPartidos());
		jugadores.addAll(ConexionDB.listaJugadores());
		
		//Creamos 10 amigos inscriptos a partidito
		for(int i = 1;i<12;i++){
			String numero = Integer.toString(i);
			Jugador amigo = new Jugador();
			amigo.setNombre("Jugador"+numero);
			amigo.setApellido("Apellido"+numero);
			amigo.setEmail("j"+numero+"@gmail.com");
			amigo.setUsuario("j"+numero);
			amigo.setHandicap(9);
			this.jugadores.add(amigo);
		}
		for(int i = 0; i<10;i++){
			if(i == 9){
				jugadores.get(i).inscribirse_a(partidito, new Solidaria());
				jugadores.get(i).inscribirse_a(partido_criticas, new Solidaria());
			}
			else {
				jugadores.get(i).inscribirse_a(partidito, new Standar());
				jugadores.get(i).inscribirse_a(partido_criticas, new Standar());
			}
		}
		for(int i = 0; i < 7; i++) {
			Penalizacion penalizacion_prueba = new Penalizacion();
			penalizacion_prueba.setJugador(jugadores.get(0));
			penalizacion_prueba.setMotivo("Prueba Largaaaaaaaaaaaaaaa");
			Date fecha_1 = new Date();
			try {
				fecha_1 = formato_fecha.parse("15/10/14");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			penalizacion_prueba.setFecha(fecha_1);
			jugadores.get(0).agregar_penalizacion(penalizacion_prueba);
		}
		//Seteamos CRITIAS
		CriterioOrden[] criterios_partido_prueba = new CriterioOrden[criterios.size()];
		administrador.organizar_equipo(partido_criticas, criterios.toArray(criterios_partido_prueba));
		administrador.dividir_equipos(partido_criticas, new EquipoParImpar());
		administrador.confirmarPartido(partido_criticas);
		
	}
	
	public void agregarPartido(Partido partido) {
		partidos.add(partido);
	}
	public boolean eliminarPartido(Partido partido) {
		int index = partidos.indexOf(partido);
		if(index > -1) {
			partidos.remove(index);
			return true;
		}
		return false;
	}
	
	public void agregarJugador(Jugador jugador){
		jugadores.add(jugador);
	}
	
	public boolean existeUsuario(String usuario){
		for(int i=0;i< jugadores.size();i++)
			if(jugadores.get(i).getUsuario().equals(usuario))
				return true;
		return false;
	}
	
	public Partido getPartidoById(String id){
		for(int i=0;i<partidos.size();i++)
			if(partidos.get(i).getPartido_nombre().equals(id))
				return partidos.get(i);
		return null;
	}
	
	public Jugador getJugadorByUsuario(String usuario){
		for(int i=0;i<jugadores.size();i++)
			if(jugadores.get(i).getUsuario().equals(usuario))
				return jugadores.get(i);
		return null;
	}
	
	public List<CriterioOrden> getCriterios(){
		return criterios;
	}
	
	public List<String> getGeneradoresEquipos(){
		List<String> lista_nombre = new ArrayList<String>();
		for(int i=0;i<generadores_equipo.size();i++)
			lista_nombre.add(generadores_equipo.get(i).quienSoy());
		return lista_nombre;
	}
	
	public IGeneradorEquipos getGeneradorEquiposByName(String nombre){
		for(int i=0;i<generadores_equipo.size();i++)
			if(generadores_equipo.get(i).quienSoy().matches(nombre))
				return generadores_equipo.get(i);
		return null;
	}
	
	public CriterioOrden getCriterioByName(String nombre){
		for(int i=0;i<criterios.size();i++)
			if(criterios.get(i).quienSoy().matches(nombre))
				return criterios.get(i);
		return null;
	}
	
}
