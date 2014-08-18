package GUI;

import java.util.ArrayList;
import java.util.List;

import club.Admin;
import club.Jugador;
import club.Partido;

//Aca se cargan TODOS los harcodeos de la DB dinamica.
//Esta estructura aconsejo pasarla por parametro cada vez que se cargue una nueva ventana.

//Valga las redundancias, estaría bueno que admin sea un singleton.
public class Global {
	public List<Jugador> jugadores = new ArrayList<Jugador>();
	public List<Partido> partidos = new ArrayList<Partido>();
	public List<Admin> admin = new ArrayList<Admin>();
	
	
	public Global(){
		//Cargamos al Admin.
		Admin administrador = new Admin();
		administrador.setNombre("Clue");
		admin.add(administrador);
		
		//Cargamos 20 jugadores.
		int nombre;
		for (nombre='a';nombre<('a'+20);nombre++){
			Jugador player = new Jugador();
			player.setNombre(Character.toString((char)nombre));
			player.setApellido(Character.toString((char)nombre));
			jugadores.add(player);
		}
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
