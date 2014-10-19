package Managers;

import java.util.List; 
import java.util.Date;
import java.util.Iterator; 
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import clasesDeNegocio.Admin;
import clasesManejadoras.ConexionDB;;

public final class ManagerAdmin {
	public static void addAdmin(String name, String pass){
		Admin admin = new Admin();
        admin.setUsuario(name);
        admin.setPassword(pass);
        ConexionDB.guardar(admin);            	     
	   }	
	
	
	
}