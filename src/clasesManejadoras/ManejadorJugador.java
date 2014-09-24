package clasesManejadoras;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.Session;

import clasesDeNegocio.Jugador;


public class ManejadorJugador {
	
	private SessionFactory sessionFactory;
	
	public ManejadorJugador() {
		try {
			System.out.println("Inicalizando Hibernate");
			
			
			Configuration configuration = new Configuration();
		    configuration.configure();
		    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		    System.out.println("terminado la inicializacion de Hibernate");
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
	
	public void agregaJugador(String usuario,String pass,String nombre, String apellido,String email, Date fecha_nacimiento) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Jugador jugador = new Jugador();
			jugador.setUsuario(usuario);
			jugador.setPassword(pass);
			jugador.setNombre(nombre);
			jugador.setApellido(apellido);
			jugador.setEmail(email);
			jugador.setFecha_nacimiento(fecha_nacimiento);
			session.save(jugador);
			tx.commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
	
	public List<Jugador> listaJugadores() {
		
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			List<Jugador> result = (List<Jugador>) session.createQuery("from Jugador as jugador").list();
			tx.commit();
			session.close();
			return result;
		} catch (HibernateException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	
	
	
	
}
