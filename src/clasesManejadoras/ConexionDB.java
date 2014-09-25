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


public class ConexionDB {
	
	private SessionFactory sessionFactory;
	
	public ConexionDB() {
		try {
			System.out.println("Inicializando Hibernate");
			
			
			Configuration configuration = new Configuration();
		    configuration.configure();
		    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		    System.out.println("terminado la inicializacion de Hibernate");
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
	
	public void agregaJugador(Jugador jugador) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			
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
