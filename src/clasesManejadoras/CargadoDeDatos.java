package clasesManejadoras;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import clasesDeNegocio.*;

public final class CargadoDeDatos {
	@SuppressWarnings("unused")
	public static void cargarAutomaticaDB(){
		
		/*		//Al parecer ya se cargan dinamicamente.
		//ESTADO PARTIDO
		IEstadoEquipo estadoConfirmado = new EstadoConfirmado();
		ConexionDB.guardar(estadoConfirmado);		
		IEstadoEquipo estadoPendiente = new EstadoPendiente();
		ConexionDB.guardar(estadoPendiente);
		*/
		
		System.out.println("\n Creando Admin\n");
		//ADMIN
		Admin admin = new Admin();
        admin.setUsuario("TheADMIN");
        admin.setPassword("unrecheable");
        admin.setEmail("elMejor@Ubuntu.com");
        //List<Admin> admines = new ArrayList<Admin>();
        //admines.add(admin);
		
		//LISTAS
        System.out.println("--Creando jugadores--"); List<Jugador> jugadores = cargarJugadores();		
        System.out.println("--Creando partidos--"); List<Partido> partidos = cargarPartidos();
        System.out.println("--Creando inscripciones--"); List<Inscripcion> inscripciones = cargarInscripciones(jugadores,partidos);
        System.out.println("--Creando amigos--"); List<Amigo> amigos = cargarAmigos();
        System.out.println("--Creando propuestas--"); List<Propuesta> propuestas = cargarPropuestas(amigos, jugadores, partidos, admin);	//Asigna los amigos a los jugadores.
        System.out.println("--Creando penalizaciones--"); List<Penalizacion> penalizaciones = cargarPenalizaciones(jugadores);
        System.out.println("--Creando calificaciones--"); List<Calificacion> calificaciones = cargarCalificaciones(partidos);
		
        propuestas = admin.getNuevas_propuestas();
		
		//Actualizamos las listas
		System.out.println("\n Guardando Administrador\n"); ConexionDB.guardar(admin);
		//System.out.println("\n Guardando Jugadores\n"); for ( Jugador temporal : jugadores ){ ConexionDB.guardar(temporal); }
		System.out.println("\n Guardando Partidos\n"); for ( Partido temporal : partidos ){ ConexionDB.guardar(temporal); }
		//System.out.println("\n Guardando Inscripciones\n");for ( Inscripcion temporal : inscripciones ){ ConexionDB.guardar(temporal); }
		//System.out.println("\n Guardando Amigos\n"); for ( Amigo temporal : amigos ){ ConexionDB.guardar(temporal); }
		//System.out.println("\n Guardando Propuestas\n"); for ( Propuesta temporal : propuestas ){ ConexionDB.guardar(temporal); }
		//System.out.println("\n Guardando Penalizaciones\n"); for ( Penalizacion temporal : penalizaciones ){ ConexionDB.guardar(temporal); }
		//System.out.println("\n Guardando Calificaciones\n"); for ( Calificacion temporal : calificaciones ){ ConexionDB.guardar(temporal); }
		System.out.println("\n CARGA DE DATOS FINALIZADA!!"); 
	}

	private static List<Jugador> cargarJugadores(){
		//Preparo una fecha para usar
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		@SuppressWarnings("unused")
		Date date;
		try { date = formatter.parse("01/29/2002"); } catch (ParseException e) { e.printStackTrace();	}
		Calendar calendario = new GregorianCalendar();		
		
		List<Jugador> jugadores = new ArrayList<Jugador>();
		Jugador player;
		
		//Jugador 1
		player = new Jugador();
		player.setUsuario("Jorgelin");
		player.setPassword("1234");
		player.setNombre("Jorge");
		player.setApellido("GARCIA");
		calendario.set(1992, 11, 21);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("jorge.12@gmail.com");
		player.setHandicap(6);
		jugadores.add(player);
		
		//Jugador 2
		player = new Jugador();
		player.setUsuario("laucha");
		player.setPassword("1234");
		player.setNombre("Lautaro");
		player.setApellido("GONZALEZ");
		calendario.set(1992, 1, 2);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("laucha@gmail.com");
		player.setHandicap(7);
		jugadores.add(player);
		
		//Jugador 3
		player = new Jugador();
		player.setUsuario("leo");
		player.setPassword("cabeza");
		player.setNombre("Leonidas");
		player.setApellido("RODRIGUEZ");
		calendario.set(1991, 11, 21);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("300@hotmail.com");
		player.setHandicap(10);
		jugadores.add(player);
		
		//Jugador 4
		player = new Jugador();
		player.setUsuario("Marge");
		player.setPassword("laLoca");
		player.setNombre("Margaret");
		player.setApellido("FERNANDEZ");
		calendario.set(1983, 9, 17);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("margie@yahoo.com");
		player.setHandicap(2);
		jugadores.add(player);
		
		//Jugador 5
		player = new Jugador();
		player.setUsuario("paul");
		player.setPassword("mcArni");
		player.setNombre("Pablo");
		player.setApellido("LOPEZ");
		calendario.set(1977, 11, 21);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("theBeat@netflix.com");
		player.setHandicap(7);
		jugadores.add(player);
		
		//Jugador 6
		player = new Jugador();
		player.setUsuario("danimarti");
		player.setPassword("adsdas");
		player.setNombre("Daniel");
		player.setApellido("MARTINEZ");
		calendario.set(1973, 1, 27);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("danimarti@gmail.com");
		player.setHandicap(3);
		jugadores.add(player);
		
		//Jugador 7
		player = new Jugador();
		player.setUsuario("elDAVI");
		player.setPassword("fdghu");
		player.setNombre("David");
		player.setApellido("SANCHEZ");
		calendario.set(1979, 2, 2);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("elDAVI@gmail.com");
		player.setHandicap(6);
		jugadores.add(player);
		
		//Jugador 8
		player = new Jugador();
		player.setUsuario("sirGabi");
		player.setPassword("dsfsdf");
		player.setNombre("Gabriel");
		player.setApellido("MARTIN");
		calendario.set(1977, 3, 12);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("sirGabi@gmail.com");
		player.setHandicap(6);
		jugadores.add(player);
		
		//Jugador 9
		player = new Jugador();
		player.setUsuario("benja");
		player.setPassword("afdgst");
		player.setNombre("Benjamín");
		player.setApellido("PEREZ");
		calendario.set(1977, 4, 1);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("benja@gmail.com");
		player.setHandicap(6);
		jugadores.add(player);
		
		//Jugador 10
		player = new Jugador();
		player.setUsuario("samuelgomez");
		player.setPassword("WRFGH");
		player.setNombre("Samuel");
		player.setApellido("GOMEZ");
		calendario.set(1977, 9, 8);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("samuelgomez@gmail.com");
		player.setHandicap(4);
		jugadores.add(player);
		
		//Jugador 11
		player = new Jugador();
		player.setUsuario("lujicas");
		player.setPassword("FGR4ge");
		player.setNombre("Lucas");
		player.setApellido("JIMENEZ");
		calendario.set(1997, 4, 8);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("lujicas@gmail.com");
		player.setHandicap(7);
		jugadores.add(player);
		
		//Jugador 12
		player = new Jugador();
		player.setUsuario("angielo");
		player.setPassword("3fgaz332");
		player.setNombre("Ángel");
		player.setApellido("RUIZ");
		calendario.set(1992, 3, 21);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("angielo@gmail.com");
		player.setHandicap(6);
		jugadores.add(player);
		
		//Jugador 13
		player = new Jugador();
		player.setUsuario("AlexCapo");
		player.setPassword("213egv");
		player.setNombre("Alex");
		player.setApellido("HERNANDEZ");
		calendario.set(1993, 11, 2);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("AlexCapo@gmail.com");
		player.setHandicap(6);
		jugadores.add(player);
		
		//Jugador 14
		player = new Jugador();
		player.setUsuario("nati221");
		player.setPassword("f32fs3");
		player.setNombre("Natalia");
		player.setApellido("DIAZ");
		calendario.set(1987, 1, 29);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("nati221@gmail.com");
		player.setHandicap(6);
		jugadores.add(player);
		
		//Jugador 15
		player = new Jugador();
		player.setUsuario("alanElBro");
		player.setPassword("5678ikafsd");
		player.setNombre("Alan");
		player.setApellido("MORENO");
		calendario.set(1977, 11, 22);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("alanElBro@gmail.com");
		player.setHandicap(6);
		jugadores.add(player);
		
		//Jugador 16
		player = new Jugador();
		player.setUsuario("alaGenia");
		player.setPassword("sdf32fsda");
		player.setNombre("Alana");
		player.setApellido("ALVAREZ");
		calendario.set(1979, 9, 22);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("alaGenia@gmail.com");
		player.setHandicap(3);
		jugadores.add(player);
		
		//Jugador 17
		player = new Jugador();
		player.setUsuario("marito");
		player.setPassword("Afg56-rhg");
		player.setNombre("Mario");
		player.setApellido("MUÑOZ");
		calendario.set(1983, 12, 3);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("marito@gmail.com");
		player.setHandicap(6);
		jugadores.add(player);
		
		//Jugador 18
		player = new Jugador();
		player.setUsuario("emaRomero");
		player.setPassword("21dzZs1");
		player.setNombre("Emanuel");
		player.setApellido("ROMERO");
		calendario.set(1995, 2, 23);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("emaRomero@gmail.com");
		player.setHandicap(1);
		jugadores.add(player);
		
		//Jugador 19
		player = new Jugador();
		player.setUsuario("guadania");
		player.setPassword("GGufj-ur3");
		player.setNombre("Guadalupe");
		player.setApellido("ALONSO");
		calendario.set(1991, 2, 3);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("guadania@gmail.com");
		player.setHandicap(8);
		jugadores.add(player);
		
		//Jugador 20
		player = new Jugador();
		player.setUsuario("juliusCaesar");
		player.setPassword("asd1efdvty23");
		player.setNombre("Julio");
		player.setApellido("GUTIERREZ");
		calendario.set(1992, 3, 16);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("juliusCaesar@gmail.com");
		player.setHandicap(6);
		jugadores.add(player);
		
		//Jugador 21
		player = new Jugador();
		player.setUsuario("jaimito");
		player.setPassword("ascrv5h7qw");
		player.setNombre("Jaime");
		player.setApellido("NAVARRO");
		calendario.set(1977, 1, 11);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("jaimito@gmail.com");
		player.setHandicap(6);
		jugadores.add(player);
		
		//Jugador 22
		player = new Jugador();
		player.setUsuario("henry999");
		player.setPassword("asdfrg6-7gf");
		player.setNombre("Enrique");
		player.setApellido("TORRES");
		calendario.set(1996, 6, 2);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("henry999@gmail.com");
		player.setHandicap(6);
		jugadores.add(player);
		
		//Jugador 23
		player = new Jugador();
		player.setUsuario("KingArthur");
		player.setPassword("Sdedsdg6hw");
		player.setNombre("Arturo");
		player.setApellido("DOMINGUEZ");
		calendario.set(1986, 3, 28);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("KingArthur@gmail.com");
		player.setHandicap(6);
		jugadores.add(player);
		
		//Jugador 24
		player = new Jugador();
		player.setUsuario("marita");
		player.setPassword("asdasf3454fvt");
		player.setNombre("Marisol");
		player.setApellido("VAZQUEZ");
		calendario.set(1982, 5, 12);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("marita@gmail.com");
		player.setHandicap(6);
		jugadores.add(player);
		
		//Jugador 25
		player = new Jugador();
		player.setUsuario("guille_127");
		player.setPassword("12345");
		player.setNombre("Guillermo");
		player.setApellido("RAMOS");
		calendario.set(1988, 1, 26);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("guille_127@gmail.com");
		player.setHandicap(6);
		jugadores.add(player);
		
		//Jugador 26
		player = new Jugador();
		player.setUsuario("iziquiel");
		player.setPassword("dftgfzb3");
		player.setNombre("Ezequiel");
		player.setApellido("GIL");
		calendario.set(1987, 3, 25);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("iziquiel@gmail.com");
		player.setHandicap(4);
		jugadores.add(player);
		
		//Jugador 27
		player = new Jugador();
		player.setUsuario("alSonso");
		player.setPassword("asd345huh");
		player.setNombre("Alonzo");
		player.setApellido("RAMIREZ");
		calendario.set(1987, 9, 24);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("alSonso@gmail.com");
		player.setHandicap(8);
		jugadores.add(player);
		
		//Jugador 28
		player = new Jugador();
		player.setUsuario("comoElPan");
		player.setPassword("vyhn34fer");
		player.setNombre("Felipe");
		player.setApellido("SERRANO");
		calendario.set(1988, 8, 30);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("comoElPan@gmail.com");
		player.setHandicap(7);
		jugadores.add(player);
		
		//Jugador 29
		player = new Jugador();
		player.setUsuario("ttTomas");
		player.setPassword("di634sdf2");
		player.setNombre("Tomás");
		player.setApellido("BLANCO");
		calendario.set(1985, 5, 12);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("ttTomas@gmail.com");
		player.setHandicap(6);
		jugadores.add(player);
		
		//Jugador 30
		player = new Jugador();
		player.setUsuario("mexicanIdol");
		player.setPassword("3d4_f32");
		player.setNombre("Jairo");
		player.setApellido("SUAREZ");
		calendario.set(1992, 1, 12);		
		player.setFecha_nacimiento(calendario.getTime());
		player.setEmail("mexicanIdol@gmail.com");
		player.setHandicap(2);
		jugadores.add(player);
		
		return jugadores;		
	}
	
	private static List<Partido> cargarPartidos(){
		//Preparo una fecha para usar
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		@SuppressWarnings("unused")
		Date date;
		try { date = formatter.parse("01/29/2002"); } catch (ParseException e) { e.printStackTrace();	}
		Calendar calendario = new GregorianCalendar();		
		
		List<Partido> partidos = new ArrayList<Partido>();
		Partido match;
		
		//Partido 1
		match = new Partido();
		match.setEstado(new EstadoConfirmado());
		match.setPartido_nombre("PartidoConfirmado1");
		match.setLugar("Base5");
		calendario.set(2014, 11, 21);		
		match.setFecha(calendario.getTime());
		partidos.add(match);
		
		//Partido 2
		match = new Partido();
		match.setEstado(new EstadoConfirmado());
		match.setPartido_nombre("PartidoConfirmado2");
		match.setLugar("Alumni");
		calendario.set(2015, 5, 13);		
		match.setFecha(calendario.getTime());
		partidos.add(match);
		
		//Partido 3
		match = new Partido();
		//match.setEstado(estadoConfirmado);	// Se supone ya se auto carga.
		match.setPartido_nombre("PartidoPendiente1");
		match.setLugar("Base5");
		calendario.set(2015, 11, 14);		
		match.setFecha(calendario.getTime());
		partidos.add(match);
		
		//Partido 4
		match = new Partido();
		//match.setEstado(estadoConfirmado);	// Se supone ya se auto carga.
		match.setPartido_nombre("PartidoPendiente2");
		match.setLugar("Base5");
		calendario.set(2014, 11, 21);		
		match.setFecha(calendario.getTime());
		partidos.add(match);
		
		//Partido 5
		match = new Partido();
		//match.setEstado(estadoConfirmado);	// Se supone ya se auto carga.
		match.setPartido_nombre("PartidoPendiente3");
		match.setLugar("OtroLugarNuevo");
		calendario.set(2015, 1, 4);		
		match.setFecha(calendario.getTime());
		partidos.add(match);
		
		return partidos;		
	}
	
	private static List<Amigo> cargarAmigos(){
		
		List<Amigo> amigos = new ArrayList<Amigo>();
		Amigo friend;
		
		//Amigo 1
		friend = new Amigo();
		friend.setNombre("Lautaro");
		friend.setApellido("FLEYMAN");
		friend.setEmail("entreElMonton@hotmail.com");
		amigos.add(friend);
		
		//Amigo 2
		friend = new Amigo();
		friend.setNombre("Mateo");
		friend.setApellido("ANDREI");
		friend.setEmail("elSeguidor@hotmail.com");
		amigos.add(friend);
		
		//Amigo 3
		friend = new Amigo();
		friend.setNombre("Guillermo");
		friend.setApellido("GIMENEZ");
		friend.setEmail("guille_12@hotmail.com");
		amigos.add(friend);
		
		//Amigo 4
		friend = new Amigo();
		friend.setNombre("Ignacio");
		friend.setApellido("PEREZ");
		friend.setEmail("nacho@hotmail.com");
		amigos.add(friend);
		
		//Amigo 5
		friend = new Amigo();
		friend.setNombre("Raul");
		friend.setApellido("HIERRO");
		friend.setEmail("likeRM@hotmail.com");
		amigos.add(friend);
		
		//Amigo 6
		friend = new Amigo();
		friend.setNombre("Juan");
		friend.setApellido("APOCALIPSIS");
		friend.setEmail("revelaciones@hotmail.com");
		amigos.add(friend);
		
		//Amigo 7
		friend = new Amigo();
		friend.setNombre("Isaias");
		friend.setApellido("MACRI");
		friend.setEmail("profetium@hotmail.com");
		amigos.add(friend);
		
		//Amigo 8
		friend = new Amigo();
		friend.setNombre("Cristian");
		friend.setApellido("BAROK");
		friend.setEmail("crichu@hotmail.com");
		amigos.add(friend);
		
		//Amigo 9
		friend = new Amigo();
		friend.setNombre("Oscar");
		friend.setApellido("FLEITAS");
		friend.setEmail("oscarcito@hotmail.com");
		amigos.add(friend);
		
		//Amigo 10
		friend = new Amigo();
		friend.setNombre("Ruben");
		friend.setApellido("CASPIO");
		friend.setEmail("elRbuen@hotmail.com");
		amigos.add(friend);
		
		return amigos;		
	}

	private static List<Propuesta> cargarPropuestas(List<Amigo> amigos, List<Jugador> jugadores ,List<Partido> partidos, Admin admin) {
		
		List<Propuesta> propuestas = new ArrayList<Propuesta>(0);
		Amigo friend;
		Jugador player;
		Partido match;		
		OrdenNuevoJugador orden = new OrdenNuevoJugador();		
		orden.setAdmin(admin);
		
		//Invitacion del amigo 1
		friend = amigos.get(0);
		player = jugadores.get(0);
		match = partidos.get(0);	//0 o 1 que son los confirmados
		player.setProposicion(orden);
		player.proponer_jugador(friend, match);
		player.agregar_amigo(friend);	//Si lo propuso es porque es su amigo.
		
		//Invitacion del amigo 2
		friend = amigos.get(1);
		player = jugadores.get(0);
		match = partidos.get(0);	//0 o 1 que son los confirmados
		player.setProposicion(orden);
		player.proponer_jugador(friend, match);
		player.agregar_amigo(friend);	//Si lo propuso es porque es su amigo.
		
		//Invitacion del amigo 3
		friend = amigos.get(2);
		player = jugadores.get(0);
		match = partidos.get(1);	//0 o 1 que son los confirmados
		player.setProposicion(orden);
		player.proponer_jugador(friend, match);
		player.agregar_amigo(friend);	//Si lo propuso es porque es su amigo.
		
		//Invitacion del amigo 4
		friend = amigos.get(3);
		player = jugadores.get(5);
		match = partidos.get(1);	//0 o 1 que son los confirmados
		player.setProposicion(orden);
		player.proponer_jugador(friend, match);
		player.agregar_amigo(friend);	//Si lo propuso es porque es su amigo.
		
		//Invitacion del amigo 5
		friend = amigos.get(4);
		player = jugadores.get(12);
		match = partidos.get(1);	//0 o 1 que son los confirmados
		player.setProposicion(orden);
		player.proponer_jugador(friend, match);
		player.agregar_amigo(friend);	//Si lo propuso es porque es su amigo.
		
		//Invitacion del amigo 6
		friend = amigos.get(5);
		player = jugadores.get(27);
		match = partidos.get(4);	//Deberia ser una propuesta pendiente
		player.setProposicion(orden);
		player.proponer_jugador(friend, match);
		player.agregar_amigo(friend);	//Si lo propuso es porque es su amigo.
		
		//Invitacion del amigo 7
		friend = amigos.get(6);
		player = jugadores.get(9);
		match = partidos.get(3);	//Deberia ser una propuesta pendiente
		player.setProposicion(orden);
		player.proponer_jugador(friend, match);
		player.agregar_amigo(friend);	//Si lo propuso es porque es su amigo.
		
		//Invitacion del amigo 8
		friend = amigos.get(7);
		player = jugadores.get(12);
		match = partidos.get(2);	//Deberia ser una propuesta pendiente
		player.setProposicion(orden);
		player.proponer_jugador(friend, match);
		player.agregar_amigo(friend);	//Si lo propuso es porque es su amigo.
		
		//Invitacion del amigo 9
		friend = amigos.get(8);
		player = jugadores.get(0);
		match = partidos.get(4);	//Deberia ser una propuesta pendiente
		player.setProposicion(orden);
		player.proponer_jugador(friend, match);
		player.agregar_amigo(friend);	//Si lo propuso es porque es su amigo.
		
		//Invitacion del amigo 10
		friend = amigos.get(9);
		player = jugadores.get(7);
		match = partidos.get(3);	//Deberia ser una propuesta pendiente
		player.setProposicion(orden);
		player.proponer_jugador(friend, match);
		player.agregar_amigo(friend);	//Si lo propuso es porque es su amigo.
		
		
		
		return propuestas;
	}
	
	private static List<Inscripcion> cargarInscripciones(List<Jugador> jugadores ,List<Partido> partidos) {
		
		List<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
		IModalidad modalidad;
		Jugador player;
		Partido match;
		
	//Partido 1
		//Jugador 1
		modalidad = new Standar();
		player = jugadores.get(0);
		match = partidos.get(0);
		inscripciones.add(player.inscribirse_a(match, modalidad));
		match.getEquipo1().addJugador(player);
		
		//Jugador 2
		modalidad = new Standar();
		player = jugadores.get(1);
		match = partidos.get(0);
		inscripciones.add(player.inscribirse_a(match, modalidad));
		match.getEquipo1().addJugador(player);
		
		//Jugador 3
		modalidad = new Standar();
		player = jugadores.get(2);
		match = partidos.get(0);
		inscripciones.add(player.inscribirse_a(match, modalidad));
		match.getEquipo1().addJugador(player);
		
		//Jugador 4
		modalidad = new Standar();
		player = jugadores.get(3);
		match = partidos.get(0);
		inscripciones.add(player.inscribirse_a(match, modalidad));
		match.getEquipo1().addJugador(player);
		
		//Jugador 5
		modalidad = new Standar(); 
		player = jugadores.get(4);
		match = partidos.get(0);
		inscripciones.add(player.inscribirse_a(match, modalidad));
		match.getEquipo1().addJugador(player);
		
		//Jugador 6
		modalidad = new Standar();
		player = jugadores.get(5);
		match = partidos.get(0);
		inscripciones.add(player.inscribirse_a(match, modalidad));
		match.getEquipo2().addJugador(player);
		
		//Jugador 7
		modalidad = new Standar();
		player = jugadores.get(6);
		match = partidos.get(0);
		inscripciones.add(player.inscribirse_a(match, modalidad));
		match.getEquipo2().addJugador(player);
		
		//Jugador 8
		modalidad = new Standar();
		player = jugadores.get(7);
		match = partidos.get(0);
		inscripciones.add(player.inscribirse_a(match, modalidad));
		match.getEquipo2().addJugador(player);
		
		//Jugador 9
		modalidad = new Standar();
		player = jugadores.get(8);
		match = partidos.get(0);
		inscripciones.add(player.inscribirse_a(match, modalidad));
		match.getEquipo2().addJugador(player);
		
		//Jugador 10
		modalidad = new Standar();
		player = jugadores.get(9);
		match = partidos.get(0);
		inscripciones.add(player.inscribirse_a(match, modalidad));
		match.getEquipo2().addJugador(player);
		
		
		match.confirmar();		//Un intento de decirle al partido que ya se jugo.

	//Partido 2
		//Jugador 1
		modalidad = new Standar();
		player = jugadores.get(10);
		match = partidos.get(1);
		inscripciones.add(player.inscribirse_a(match, modalidad));
		match.getEquipo1().addJugador(player);
		
		//Jugador 2
		modalidad = new Standar();
		player = jugadores.get(11);
		match = partidos.get(1);
		inscripciones.add(player.inscribirse_a(match, modalidad));
		match.getEquipo1().addJugador(player);
		
		//Jugador 3
		modalidad = new Standar();
		player = jugadores.get(12);
		match = partidos.get(1);
		inscripciones.add(player.inscribirse_a(match, modalidad));
		match.getEquipo1().addJugador(player);
		
		//Jugador 4
		modalidad = new Standar();
		player = jugadores.get(13);
		match = partidos.get(1);
		inscripciones.add(player.inscribirse_a(match, modalidad));
		match.getEquipo1().addJugador(player);
		
		//Jugador 5
		modalidad = new Standar();
		player = jugadores.get(14);
		match = partidos.get(1);
		inscripciones.add(player.inscribirse_a(match, modalidad));
		match.getEquipo1().addJugador(player);
		
		//Jugador 6
		modalidad = new Standar();
		player = jugadores.get(15);
		match = partidos.get(1);
		inscripciones.add(player.inscribirse_a(match, modalidad));
		match.getEquipo2().addJugador(player);
		
		//Jugador 7
		modalidad = new Solidaria();
		player = jugadores.get(16);
		match = partidos.get(1);
		inscripciones.add(player.inscribirse_a(match, modalidad));
		match.getEquipo2().addJugador(player);
		
		//Jugador 8
		modalidad = new Solidaria();
		player = jugadores.get(17);
		match = partidos.get(1);
		inscripciones.add(player.inscribirse_a(match, modalidad));
		match.getEquipo2().addJugador(player);
		
		//Jugador 9
		modalidad = new Solidaria();
		player = jugadores.get(18);
		match = partidos.get(1);
		inscripciones.add(match.inscribir(player, modalidad));
		match.getEquipo2().addJugador(player);
		
		//Jugador 10
		modalidad = new Solidaria();
		player = jugadores.get(19);
		match = partidos.get(1);
		inscripciones.add(match.inscribir(player, modalidad));
		match.getEquipo2().addJugador(player);

		match.confirmar();		//Un intento de decirle al partido que ya se jugo.

	//Partido 3
		//Jugador 1
		modalidad = new Standar();
		player = jugadores.get(20);
		match = partidos.get(2);
		inscripciones.add(match.inscribir(player, modalidad));
		
		//Jugador 2
		modalidad = new Standar();
		player = jugadores.get(21);
		match = partidos.get(2);
		inscripciones.add(match.inscribir(player, modalidad));
		
		//Jugador 3
		modalidad = new Standar();
		player = jugadores.get(22);
		match = partidos.get(2);
		inscripciones.add(match.inscribir(player, modalidad));
		
		//Jugador 4
		modalidad = new Standar();
		player = jugadores.get(23);
		match = partidos.get(2);
		inscripciones.add(match.inscribir(player, modalidad));
		
		//Jugador 5
		modalidad = new Solidaria();
		player = jugadores.get(24);
		match = partidos.get(2);
		inscripciones.add(match.inscribir(player, modalidad));
		
		//Jugador 6
		modalidad = new Solidaria();
		player = jugadores.get(25);
		match = partidos.get(2);
		inscripciones.add(match.inscribir(player, modalidad));
		
		//Jugador 7
		modalidad = new Solidaria();
		player = jugadores.get(26);
		match = partidos.get(2);
		inscripciones.add(match.inscribir(player, modalidad));
		
		//Jugador 8
		modalidad = new Condicional("Si se me canta");
		player = jugadores.get(27);
		match = partidos.get(2);
		inscripciones.add(match.inscribir(player, modalidad));
		
		//Jugador 9
		modalidad = new Condicional("Si no llueve");
		player = jugadores.get(28);
		match = partidos.get(2);
		inscripciones.add(match.inscribir(player, modalidad));
		
		//Jugador 10
		modalidad = new Solidaria();
		player = jugadores.get(29);
		match = partidos.get(2);
		inscripciones.add(match.inscribir(player, modalidad));
		

	//Partido 4
		//Jugador 1
		modalidad = new Standar();
		player = jugadores.get(0);
		match = partidos.get(3);
		inscripciones.add(match.inscribir(player, modalidad));
		
		//Jugador 2
		modalidad = new Standar();
		player = jugadores.get(1);
		match = partidos.get(3);
		inscripciones.add(match.inscribir(player, modalidad));
		
		//Jugador 3
		modalidad = new Standar();
		player = jugadores.get(2);
		match = partidos.get(3);
		inscripciones.add(match.inscribir(player, modalidad));
		
		//Jugador 4
		modalidad = new Standar();
		player = jugadores.get(3);
		match = partidos.get(3);
		inscripciones.add(match.inscribir(player, modalidad));
		
		//Jugador 5
		modalidad = new	Solidaria();
		player = jugadores.get(4);
		match = partidos.get(3);
		inscripciones.add(match.inscribir(player, modalidad));
		
		//Jugador 6
		modalidad = new Solidaria();
		player = jugadores.get(5);
		match = partidos.get(3);
		inscripciones.add(match.inscribir(player, modalidad));
		
		//Jugador 7
		modalidad = new Condicional("Solo si mi mama me lleva");
		player = jugadores.get(6);
		match = partidos.get(3);
		inscripciones.add(match.inscribir(player, modalidad));
		
		//Jugador 8
		modalidad = new Solidaria();
		player = jugadores.get(7);
		match = partidos.get(3);
		inscripciones.add(match.inscribir(player, modalidad));
		
		//Jugador 9
		modalidad = new Standar();
		player = jugadores.get(8);
		match = partidos.get(3);
		inscripciones.add(match.inscribir(player, modalidad));
		
		//Jugador 10
		modalidad = new Standar();
		player = jugadores.get(9);
		match = partidos.get(3);
		inscripciones.add(match.inscribir(player, modalidad));
		

	//Partido 5
		//Jugador 1
		modalidad = new Standar();
		player = jugadores.get(10);
		match = partidos.get(4);
		inscripciones.add(match.inscribir(player, modalidad));
		
		//Jugador 2
		modalidad = new Standar();
		player = jugadores.get(11);
		match = partidos.get(4);
		inscripciones.add(match.inscribir(player, modalidad));
		
		//Jugador 3
		modalidad = new Standar();
		player = jugadores.get(12);
		match = partidos.get(4);
		inscripciones.add(match.inscribir(player, modalidad));
		
		//Jugador 4
		modalidad = new Standar();
		player = jugadores.get(13);
		match = partidos.get(4);
		inscripciones.add(match.inscribir(player, modalidad));
		
		//Jugador 5
		modalidad = new Condicional("Si somos 10");
		player = jugadores.get(14);
		match = partidos.get(4);
		inscripciones.add(match.inscribir(player, modalidad));
		
		//Jugador 6
		modalidad = new Condicional("Si juega Mateo");
		player = jugadores.get(15);
		match = partidos.get(4);
		inscripciones.add(match.inscribir(player, modalidad));
		
		//Jugador 7
		modalidad = new Condicional("Si me levanto temprano");
		player = jugadores.get(16);
		match = partidos.get(4);
		inscripciones.add(match.inscribir(player, modalidad));
		
		//Jugador 8
		modalidad = new Condicional("Si no dan Friends a esa hora");
		player = jugadores.get(17);
		match = partidos.get(4);
		inscripciones.add(match.inscribir(player, modalidad));
		
		//Jugador 9
		modalidad = new Solidaria();
		player = jugadores.get(18);
		match = partidos.get(4);
		inscripciones.add(match.inscribir(player, modalidad));
		
		//Jugador 10
		modalidad = new Solidaria();
		player = jugadores.get(19);
		match = partidos.get(4);
		inscripciones.add(match.inscribir(player, modalidad));
		
		
		return inscripciones;
	}

	private static List<Penalizacion> cargarPenalizaciones(List<Jugador> jugadores) {
		//Preparo una fecha para usar
				DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				@SuppressWarnings("unused")
				Date date;
				try { date = formatter.parse("01/29/2002"); } catch (ParseException e) { e.printStackTrace();	}
				Calendar calendario = new GregorianCalendar();		
				
				List<Penalizacion> penalizaciones = new ArrayList<Penalizacion>();
				Penalizacion fault;
				Jugador player;

		//Jugador 1
				player = jugadores.get(0);
				
				//Penalizacion 1
				fault = new Penalizacion();
				fault.setJugador(player);
				fault.setMotivo("Retener el balon");
				calendario.set(2000, 10, 12);
				fault.setFecha(calendario.getTime());
				player.agregar_penalizacion(fault);
				penalizaciones.add(fault);
				
				//Penalizacion 2
				fault = new Penalizacion();
				fault.setJugador(player);
				fault.setMotivo("Hacer 2 veces falta");
				calendario.set(2000, 11, 9);
				fault.setFecha(calendario.getTime());
				player.agregar_penalizacion(fault);
				penalizaciones.add(fault);
				
				//Penalizacion 3
				fault = new Penalizacion();
				fault.setJugador(player);
				fault.setMotivo("Mala onda con el arbitro");
				calendario.set(2000, 12, 12);
				fault.setFecha(calendario.getTime());
				player.agregar_penalizacion(fault);
				penalizaciones.add(fault);
				
		//Jugador 2
				player = jugadores.get(3);
				
				//Penalizacion 1
				fault = new Penalizacion();
				fault.setJugador(player);
				fault.setMotivo("La toco con la mano");
				calendario.set(2005, 7, 12);
				fault.setFecha(calendario.getTime());
				player.agregar_penalizacion(fault);
				penalizaciones.add(fault);
				
				//Penalizacion 2
				fault = new Penalizacion();
				fault.setJugador(player);
				fault.setMotivo("Defeco en el campo");
				calendario.set(2006, 1, 3);
				fault.setFecha(calendario.getTime());
				player.agregar_penalizacion(fault);
				penalizaciones.add(fault);
				
		//Jugador 3
				player = jugadores.get(11);
				
				//Penalizacion 1
				fault = new Penalizacion();
				fault.setJugador(player);
				fault.setMotivo("Le pego con el puño a un compañero");
				calendario.set(2003, 2, 4);
				fault.setFecha(calendario.getTime());
				player.agregar_penalizacion(fault);
				penalizaciones.add(fault);
				
				//Penalizacion 2
				fault = new Penalizacion();
				fault.setJugador(player);
				fault.setMotivo("Falto mucho a los partidos");
				calendario.set(2004, 4, 9);
				fault.setFecha(calendario.getTime());
				player.agregar_penalizacion(fault);
				penalizaciones.add(fault);
				
				//Penalizacion 3
				fault = new Penalizacion();
				fault.setJugador(player);
				fault.setMotivo("Nadie sabe por que");
				calendario.set(2014, 10, 5);
				fault.setFecha(calendario.getTime());
				player.agregar_penalizacion(fault);
				penalizaciones.add(fault);
				
		//Jugador 4
				player = jugadores.get(23);
				
				//Penalizacion 1
				fault = new Penalizacion();
				fault.setJugador(player);
				fault.setMotivo("Por buena persona");
				calendario.set(2014, 7, 12);
				fault.setFecha(calendario.getTime());
				player.agregar_penalizacion(fault);
				penalizaciones.add(fault);
				
				//Penalizacion 2
				fault = new Penalizacion();
				fault.setJugador(player);
				fault.setMotivo("Es divertido penalizarlo");
				calendario.set(2014, 8, 2);
				fault.setFecha(calendario.getTime());
				player.agregar_penalizacion(fault);
				penalizaciones.add(fault);
				
				//Penalizacion 3
				fault = new Penalizacion();
				fault.setJugador(player);
				fault.setMotivo("No se queja nunca");
				calendario.set(2014, 9, 27);
				fault.setFecha(calendario.getTime());
				player.agregar_penalizacion(fault);
				penalizaciones.add(fault);

		//Jugador 5
				player = jugadores.get(7);
				
				//Penalizacion 1
				fault = new Penalizacion();
				fault.setJugador(player);
				fault.setMotivo("Demasiado violento");
				calendario.set(2001, 9, 29);
				fault.setFecha(calendario.getTime());
				player.agregar_penalizacion(fault);
				penalizaciones.add(fault);
				
		//Jugador 6
				player = jugadores.get(2);
				
				//Penalizacion 1
				fault = new Penalizacion();
				fault.setJugador(player);
				fault.setMotivo("Robo en los vestuarios");
				calendario.set(2007, 12, 10);
				fault.setFecha(calendario.getTime());
				player.agregar_penalizacion(fault);
				penalizaciones.add(fault);
				
				//Penalizacion 2
				fault = new Penalizacion();
				fault.setJugador(player);
				fault.setMotivo("Ataque con cuchillo durante el partido");
				calendario.set(2009, 3, 6);
				fault.setFecha(calendario.getTime());
				player.agregar_penalizacion(fault);
				penalizaciones.add(fault);
				
				//Penalizacion 3
				fault = new Penalizacion();
				fault.setJugador(player);
				fault.setMotivo("Mato a 3 personas por hacerle falta");
				calendario.set(2012, 12, 12);
				fault.setFecha(calendario.getTime());
				player.agregar_penalizacion(fault);
				penalizaciones.add(fault);
				
		return penalizaciones;		
	}

	private static List<Calificacion> cargarCalificaciones(List<Partido> partidos) {

		List<Calificacion> calificaciones = new ArrayList<Calificacion>();
		Calificacion score;		
		Jugador calificado;
		Jugador calificador;
		Partido match;
		
		//Calificacion 1
		match = partidos.get(0);
		calificado = match.getEquipo1().getJugadores().get(0);
		calificador = match.getEquipo1().getJugadores().get(2);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "Muy bueno", match);	
		calificaciones.add(score);

		//Calificacion 2
		match = partidos.get(0);
		calificado = match.getEquipo1().getJugadores().get(2);
		calificador = match.getEquipo1().getJugadores().get(4);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "Apesta", match);	
		calificaciones.add(score);
		
		//Calificacion 3
		match = partidos.get(0);
		calificado = match.getEquipo1().getJugadores().get(3);
		calificador = match.getEquipo1().getJugadores().get(1);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "Podria ser mejor", match);	
		calificaciones.add(score);
		
		//Calificacion 4
		match = partidos.get(0);
		calificado = match.getEquipo1().getJugadores().get(0);
		calificador = match.getEquipo1().getJugadores().get(3);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "No la pasa nunca", match);	
		calificaciones.add(score);
		
		//Calificacion 5
		match = partidos.get(0);
		calificado = match.getEquipo1().getJugadores().get(0);
		calificador = match.getEquipo2().getJugadores().get(3);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "No sabe resolver jugadas", match);	
		calificaciones.add(score);
		
		//Calificacion 6
		match = partidos.get(0);
		calificado = match.getEquipo1().getJugadores().get(1);
		calificador = match.getEquipo2().getJugadores().get(4);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "Genial", match);	
		calificaciones.add(score);
		
		//Calificacion 7
		match = partidos.get(0);
		calificado = match.getEquipo1().getJugadores().get(2);
		calificador = match.getEquipo2().getJugadores().get(1);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "Somos un duo perfecto", match);	
		calificaciones.add(score);
		
		//Calificacion 8
		match = partidos.get(0);
		calificado = match.getEquipo1().getJugadores().get(0);
		calificador = match.getEquipo2().getJugadores().get(1);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "Es medio gay", match);	
		calificaciones.add(score);
		
		//Calificacion 9
		match = partidos.get(0);
		calificado = match.getEquipo2().getJugadores().get(4);
		calificador = match.getEquipo1().getJugadores().get(2);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "Parece Dante jugando futbol", match);	
		calificaciones.add(score);
		
		//Calificacion 10
		match = partidos.get(0);
		calificado = match.getEquipo2().getJugadores().get(4);
		calificador = match.getEquipo1().getJugadores().get(1);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "Nunca nadie jugo peor", match);	
		calificaciones.add(score);
		
		//Calificacion 11
		match = partidos.get(0);
		calificado = match.getEquipo2().getJugadores().get(2);
		calificador = match.getEquipo1().getJugadores().get(2);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "Es un crack", match);	
		calificaciones.add(score);
		
		//Calificacion 12
		match = partidos.get(0);
		calificado = match.getEquipo2().getJugadores().get(2);
		calificador = match.getEquipo1().getJugadores().get(4);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "muy buen jugador", match);	
		calificaciones.add(score);
		
		//Calificacion 13
		match = partidos.get(0);
		calificado = match.getEquipo2().getJugadores().get(0);
		calificador = match.getEquipo2().getJugadores().get(1);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "Regular", match);	
		calificaciones.add(score);
		
		//Calificacion 14
		match = partidos.get(0);
		calificado = match.getEquipo2().getJugadores().get(4);
		calificador = match.getEquipo2().getJugadores().get(2);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "Ok", match);	
		calificaciones.add(score);
		
		//Calificacion 15
		match = partidos.get(0);
		calificado = match.getEquipo2().getJugadores().get(1);
		calificador = match.getEquipo2().getJugadores().get(2);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "dsada", match);	
		calificaciones.add(score);
		
		//Calificacion 16
		match = partidos.get(1);
		calificado = match.getEquipo1().getJugadores().get(0);
		calificador = match.getEquipo1().getJugadores().get(2);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "frasdsa", match);	
		calificaciones.add(score);
		
		//Calificacion 17
		match = partidos.get(1);
		calificado = match.getEquipo1().getJugadores().get(1);
		calificador = match.getEquipo1().getJugadores().get(2);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "easd", match);	
		calificaciones.add(score);
		
		//Calificacion 18
		match = partidos.get(1);
		calificado = match.getEquipo1().getJugadores().get(3);
		calificador = match.getEquipo1().getJugadores().get(2);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "No se para que comento", match);	
		calificaciones.add(score);
		
		//Calificacion 19
		match = partidos.get(1);
		calificado = match.getEquipo1().getJugadores().get(2);
		calificador = match.getEquipo1().getJugadores().get(4);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "Muy bueno", match);	
		calificaciones.add(score);
		
		//Calificacion 20
		match = partidos.get(1);
		calificado = match.getEquipo1().getJugadores().get(2);
		calificador = match.getEquipo2().getJugadores().get(2);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "Okey", match);	
		calificaciones.add(score);
		
		//Calificacion 21
		match = partidos.get(1);
		calificado = match.getEquipo1().getJugadores().get(2);
		calificador = match.getEquipo2().getJugadores().get(1);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "Mas o menos", match);	
		calificaciones.add(score);
		
		//Calificacion 22
		match = partidos.get(1);
		calificado = match.getEquipo1().getJugadores().get(4);
		calificador = match.getEquipo2().getJugadores().get(2);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "Muy buen pateador", match);	
		calificaciones.add(score);
		
		//Calificacion 23
		match = partidos.get(1);
		calificado = match.getEquipo1().getJugadores().get(3);
		calificador = match.getEquipo2().getJugadores().get(3);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "Es una pared en el fondo", match);	
		calificaciones.add(score);
		
		//Calificacion 24
		match = partidos.get(1);
		calificado = match.getEquipo2().getJugadores().get(1);
		calificador = match.getEquipo1().getJugadores().get(2);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "Nadie mejor que el", match);	
		calificaciones.add(score);
		
		//Calificacion 25
		match = partidos.get(1);
		calificado = match.getEquipo2().getJugadores().get(2);
		calificador = match.getEquipo1().getJugadores().get(2);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "Casi tan bueno como yo", match);	
		calificaciones.add(score);
		
		//Calificacion 26
		match = partidos.get(1);
		calificado = match.getEquipo2().getJugadores().get(3);
		calificador = match.getEquipo1().getJugadores().get(1);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "dasgt", match);	
		calificaciones.add(score);
		
		//Calificacion 27
		match = partidos.get(1);
		calificado = match.getEquipo2().getJugadores().get(0);
		calificador = match.getEquipo1().getJugadores().get(1);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "Zafable", match);	
		calificaciones.add(score);
		
		//Calificacion 28
		match = partidos.get(1);
		calificado = match.getEquipo2().getJugadores().get(0);
		calificador = match.getEquipo2().getJugadores().get(1);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "Eze juega mejor", match);	
		calificaciones.add(score);
		
		//Calificacion 29
		match = partidos.get(1);
		calificado = match.getEquipo2().getJugadores().get(0);
		calificador = match.getEquipo2().getJugadores().get(4);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "Como jugar solo", match);	
		calificaciones.add(score);
		
		//Calificacion 30
		match = partidos.get(1);
		calificado = match.getEquipo2().getJugadores().get(3);
		calificador = match.getEquipo2().getJugadores().get(1);
		score = new Calificacion();
		score = calificador.calificar(calificado, 2, "Todo bien", match);	
		calificaciones.add(score);
		
		return calificaciones;
	}

	
}
