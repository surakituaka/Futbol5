package clasesManejadoras;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.Session;

import clasesDeNegocio.Calificacion;
import clasesDeNegocio.IEstadoEquipo;
import clasesDeNegocio.Jugador;
import clasesDeNegocio.Partido;


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
	
	public void guardar(Object objeto) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			
			session.saveOrUpdate(objeto);
			tx.commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	@SuppressWarnings("unchecked")
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
	
	@SuppressWarnings("unchecked")
	public List<Partido> listaPartidos() {
		
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			List<Partido> result = session.createQuery("from Partido as Partido").list();
			tx.commit();
			session.close();
			return result;
		} catch (HibernateException e) {
			throw new RuntimeException(e.getMessage());
		}		
	}

	@SuppressWarnings("unchecked")
	public List<Calificacion> listaCalificaciones() {
		
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			List<Calificacion> result = (List<Calificacion>) session.createQuery("from Partido as Partido").list();
			tx.commit();
			session.close();
			return result;
		} catch (HibernateException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<IEstadoEquipo> listaEstados() {
		
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			List<IEstadoEquipo> result = (List<IEstadoEquipo>) session.createQuery("from IEstadoEquipo as IEstadoEquipo").list();
			tx.commit();
			session.close();
			return result;
		} catch (HibernateException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
}
