package proyectoPadelUp.daos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import proyectoPadelUp.*;
import proyectoPadelUp.pojos.Temporada;

public class TemporadasDao {
	
	public void altaTemporada(Temporada temporada) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();   	
	 	SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();
		
	 	Session sesion = sf.openSession();
		sesion.beginTransaction();
		sesion.save(temporada);
		sesion.getTransaction().commit();	
		sesion.close();
	 	sf.close();
	}
	

	public List<Temporada> consultarTemporadas(){
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();   	
	 	SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();
		
	 	Session sesion = sf.openSession();
		Temporada temporada = new Temporada();
		Query query = sesion.createQuery("from Temporada order by idTemporada");
		
		List<Temporada> temporadas = query.getResultList();
		
		for(Temporada temporada2 : temporadas) {
			
			temporada.setIdTemporada(temporada2.getIdTemporada());
			temporada.setNombre(temporada2.getNombre());
			temporada.setNumJornadas(temporada2.getNumJornadas());
			temporada.setDivision(temporada2.getDivision());
			temporada.setCategoria(temporada2.getCategoria());
			temporada.setGrupo(temporada2.getGrupo());
	}		
		sesion.close();
	 	sf.close();
		return temporadas;
	}
	
	
	public void modificarTemporada(Temporada temporada) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();   	
	 	SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();
		
	 	Session sesion = sf.openSession();
		sesion.beginTransaction();
		sesion.update(temporada);
		sesion.getTransaction().commit();	
		sesion.close();
	 	sf.close();
	}
}
