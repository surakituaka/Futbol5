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


public final class ConexionDB {
	
    private static SessionFactory sessionFactory;

    static {
    	Boolean todoOK=false;
    	int errores=0;
    	while(!todoOK)
        try {
			System.out.println("Inicializando Hibernate");
			Configuration configuration = new Configuration();
		    configuration.configure();
		    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		    System.out.println("Terminado la inicializacion de Hibernate");
		    todoOK=true;
		} catch (HibernateException e) {
			errores++;
			if(errores >3) 		//HACE 3 INTENTOS PORQUE AL MENOS EN MI DB A VECES NO ARRANCABA AL PRIMER INTENTO.
			{
				e.printStackTrace();
				System.out.println("Superada la cantidad de reintentos. Ejecucion finalizada.");
				System.exit(0); 
			}
			System.out.println("Error al inicar conexion con DB, reintentando..  " + "(" + e.getLocalizedMessage() + ")");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}        
		
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }
	
	public static void guardar(Object objeto) {
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
	public static List<Jugador> listaJugadores() {
		
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
	public static List<Partido> listaPartidos() {
		
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			List<Partido> result = (List<Partido>) session.createQuery("from Partido as Partido").list();
			tx.commit();
			session.close();
			return result;
		} catch (HibernateException e) {
			throw new RuntimeException(e.getMessage());
		}		
	}

	@SuppressWarnings("unchecked")
	public static List<Calificacion> listaCalificaciones() {
		
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
	public static List<IEstadoEquipo> listaEstados() {
		
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
