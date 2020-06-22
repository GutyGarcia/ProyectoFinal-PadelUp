package proyectoPadelUp.daos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import proyectoPadelUp.pojos.Equipo;
import proyectoPadelUp.pojos.Jugador;
import proyectoPadelUp.pojos.Temporada;
import proyectoPadelUp.pojos.Usuario;

public class TemporadasEquiposDao {

	public boolean equipoEnTemporadaActiva(int idEquipo) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();
		Session sesion = sf.openSession();

		boolean temporadaActiva = false;

		Query query = sesion.createQuery("from Temporada t where t.estado=1");

		List<Temporada> temporadas = query.getResultList();

		for (Temporada temporada : temporadas) {
			for (Equipo equipo : temporada.getEquipos()) {
				if (equipo.getIdEquipo() == idEquipo) {
					temporadaActiva = true;
					break;
				}
				if (temporadaActiva) {
					break;
				}
			}

		}

		sesion.close();
		sf.close();
		return temporadaActiva;
	}

	public Temporada consultarTemporadaPorEquipo(int idEquipo) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();
		Session sesion = sf.openSession();

		Temporada temporada = new Temporada();
		Query query = sesion.createQuery(
				"select t from Temporada t inner join t.equipos e where t.estado=1 and e.idEquipo = " + idEquipo);

		List<Temporada> temporadas = (List<Temporada>) query.getResultList();
		for (Temporada temporada2 : temporadas) {
			temporada = temporada2;
		}
		sesion.close();
		sf.close();
		return temporada;
	}
}
