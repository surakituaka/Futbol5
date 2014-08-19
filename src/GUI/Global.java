package GUI;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import club.Admin;
import club.Condicional;
import club.Jugador;
import club.Partido;
import club.Solidaria;
import club.Standar;

//Aca se cargan TODOS los harcodeos de la DB dinamica.
//Esta estructura aconsejo pasarla por parametro cada vez que se cargue una nueva ventana.

//Valga las redundancias, estaría bueno que admin sea un singleton.
public class Global {
	public List<Jugador> jugadores = new ArrayList<Jugador>();
	public List<Partido> partidos = new ArrayList<Partido>();
	public Admin administrador;
	
	
	public Global(){
		//Creamos 3 amigos libres.
		Jugador amigo1 = new Jugador();
		amigo1.setNombre("Amigo-Ideal");
		amigo1.setApellido("Apellido-Ideal");
		this.jugadores.add(amigo1);	
		Jugador amigo2 = new Jugador();
		amigo2.setNombre("Amigo-Siguiente");
		amigo2.setApellido("Apellido-Siguiente");
		this.jugadores.add(amigo2);
		Jugador amigo3 = new Jugador();
		amigo3.setNombre("Amigo-Restante");
		amigo3.setApellido("Apellido-Restante");
		jugadores.add(amigo3);
		
		
		
		//Creamos 1 partido para calificar.
		List<Partido> calificables = new ArrayList<Partido>();
		Partido calificable = new Partido();
		calificable.setFecha(new Date());
		calificable.setId("Partido-Terminado");
		calificable.setLugar("Salon N* 99");	
		calificables.add(calificable);
		
		int num,sol=0,stan=0,cond=0;		
		Partido partido;
		//Creamos 3 partidos:
		
		//-----------------------------------------------
		//Partido de 10 Standars mas otros			
		partido = new Partido();
		partido.setFecha(new Date());
		partido.setId("Completo");
		partido.setLugar("Salon N* 2");	
		partidos.add(partido);				
		//3 Solidarios
		for (num=sol;num<sol+3;num++){
			Jugador player = new Jugador();
			//player.setNombre((new String("Solidario-")).concat(Integer.toString(num)));
			//player.setApellido((new String("Apellido-")).concat(Integer.toString(num)));
			player.setTipo(new Solidaria());
			//player.agregar_amigo(amigo1);
			//player.agregar_amigo(amigo2);
			//player.agregar_amigo(amigo3);
//------------->			jugadores.add(player);			
			player.inscribirse_a(partido);
			//if(num==sol+2){player.inscribirse_a(calificable);player.setPartidos_jugados(calificables);}
		}
		/*
		sol=num;
		//10 Standar
		for (num=stan;num<stan+10;num++){
			Jugador player = new Jugador();
			player.setNombre((new String("Standar-")).concat(Integer.toString(num)));
			player.setApellido((new String("Apellido-")).concat(Integer.toString(num)));
			player.setTipo(new Standar());
			player.agregar_amigo(amigo1);
			player.agregar_amigo(amigo2);
			player.agregar_amigo(amigo3);
			jugadores.add(player);			
			player.inscribirse_a(partido);
			if(num==stan+1){player.inscribirse_a(calificable);player.setPartidos_jugados(calificables);}
			if(num==stan+5){player.inscribirse_a(calificable);player.setPartidos_jugados(calificables);}
			if(num==stan+8){player.inscribirse_a(calificable);player.setPartidos_jugados(calificables);}
		}
		stan=num;
		
		//-----------------------------------------------
		//Partido de 5 Standar + 5 extras
		partido = new Partido();
		partido.setFecha(new Date());
		partido.setId("5+5");
		partido.setLugar("Salon N* 7");		
		partidos.add(partido);
		//2 Solidarios
		for (num=sol;num<sol+2;num++){
			Jugador player = new Jugador();
			player.setNombre((new String("Solidario-")).concat(Integer.toString(num)));
			player.setApellido((new String("Apellido-")).concat(Integer.toString(num)));
			player.setTipo(new Solidaria());
			player.agregar_amigo(amigo1);
			player.agregar_amigo(amigo2);
			player.agregar_amigo(amigo3);
			jugadores.add(player);			
			player.inscribirse_a(partido);
			if(num==sol+0){player.inscribirse_a(calificable);player.setPartidos_jugados(calificables);}
		}
		sol=num;
		//3 Condicionales
		for (num=cond;num<cond+3;num++){
			Jugador player = new Jugador();
			player.setNombre((new String("Condicional-")).concat(Integer.toString(num)));
			player.setApellido((new String("Apellido-")).concat(Integer.toString(num)));
			player.setTipo(new Condicional("condicion loca"));
			player.agregar_amigo(amigo1);
			player.agregar_amigo(amigo2);
			player.agregar_amigo(amigo3);
			jugadores.add(player);			
			player.inscribirse_a(partido);
			if(num==cond+1){player.inscribirse_a(calificable);player.setPartidos_jugados(calificables);}
			if(num==cond+2){player.inscribirse_a(calificable);player.setPartidos_jugados(calificables);}
		}
		cond=num;
		//5 Standar
		for (num=stan;num<stan+5;num++){
			Jugador player = new Jugador();
			player.setNombre((new String("Standar-")).concat(Integer.toString(num)));
			player.setApellido((new String("Apellido-")).concat(Integer.toString(num)));
			player.setTipo(new Standar());
			player.agregar_amigo(amigo1);
			player.agregar_amigo(amigo2);
			player.agregar_amigo(amigo3);
			jugadores.add(player);			
			player.inscribirse_a(partido);
			if(num==stan+0){player.inscribirse_a(calificable);player.setPartidos_jugados(calificables);}
			if(num==stan+2){player.inscribirse_a(calificable);player.setPartidos_jugados(calificables);}
			if(num==stan+4){player.inscribirse_a(calificable);player.setPartidos_jugados(calificables);}
		}
		stan=num;
		//-----------------------------------------------
			
		//Cargamos 10 jugadores useless
		for (num=0;num<10;num++){
			Jugador player = new Jugador();
			player.setNombre((new String("Extra-")).concat(Integer.toString(num)));
			player.setApellido((new String("Apellido-")).concat(Integer.toString(num)));
			player.setTipo(new Standar());
			player.agregar_amigo(amigo1);
			player.agregar_amigo(amigo2);
			player.agregar_amigo(amigo3);
			jugadores.add(player);		
		}
		*/
		//Cargamos al Admin.
				administrador = new Admin();
				administrador.setNombre("Clue");
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
	
}
