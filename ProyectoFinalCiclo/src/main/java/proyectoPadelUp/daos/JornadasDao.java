package proyectoPadelUp.daos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import proyectoPadelUp.*;
import proyectoPadelUp.pojos.Jornada;
import proyectoPadelUp.pojos.Temporada;

public class JornadasDao {

	public void altaJornada(Jornada jornada) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session sesion = sf.openSession();
		sesion.beginTransaction();
		sesion.save(jornada);
		sesion.getTransaction().commit();
		sesion.close();
		sf.close();
	}

	public void registrarJornada(Jornada jornada) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session sesion = sf.openSession();
		sesion.beginTransaction();
		sesion.update(jornada);
		sesion.getTransaction().commit();
		sesion.close();
		sf.close();
	}

	public List<Jornada> consultarJornadasPorTemporada(int idTemporada) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session sesion = sf.openSession();
		Session sesion2 = sf.openSession();

		Jornada jornada = new Jornada();
		Query query = sesion.createQuery("from Jornada where estado = 'pendiente' and idTemporada=" + idTemporada);

		List<Jornada> jornadas = query.getResultList();

		for (Jornada jornada2 : jornadas) {

			jornada.setIdJornada(jornada2.getIdJornada());
			jornada.setNombre(jornada2.getNombre());
			jornada.setFecha(jornada2.getFecha());
			jornada.setEquipoLocal(jornada2.getEquipoLocal());
			jornada.setEquipoVisitante(jornada2.getEquipoVisitante());
			jornada.setEstado(jornada2.getEstado());

			Query queryTemporada = sesion2.createQuery(
					"from Temporada where  estado = 1 and idTemporada =" + jornada2.getTemporada().getIdTemporada());
			Temporada temporada = new Temporada();
			List<Temporada> temporadas = queryTemporada.getResultList();
			for (Temporada temporada2 : temporadas) {

				temporada.setIdTemporada(temporada2.getIdTemporada());
				temporada.setNombre(temporada2.getNombre());
				temporada.setNumJornadas(temporada2.getNumJornadas());
				temporada.setDivision(temporada2.getDivision());
				temporada.setCategoria(temporada2.getCategoria());
				temporada.setGrupo(temporada2.getGrupo());
				temporada.setEstado(temporada2.isEstado());
				jornada.setTemporada(temporada2);
			}

		}
		sesion.close();
		sesion2.close();
		sf.close();

		return jornadas;
	}

	public Jornada consultarJornadaEnJuegoPorTemporada(int idTemporada) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session sesion = sf.openSession();
		Session sesion2 = sf.openSession();
		Jornada jornada = new Jornada();
		Query query = sesion.createQuery("from Jornada where estado = 'enJuego' and idTemporada=" + idTemporada);

		List<Jornada> jornadas = query.getResultList();

		for (Jornada jornada2 : jornadas) {

			jornada.setIdJornada(jornada2.getIdJornada());
			jornada.setNombre(jornada2.getNombre());
			jornada.setFecha(jornada2.getFecha());
			jornada.setEquipoLocal(jornada2.getEquipoLocal());
			jornada.setEquipoVisitante(jornada2.getEquipoVisitante());
			jornada.setEstado(jornada2.getEstado());

		}
		sesion.close();
		sesion2.close();
		sf.close();

		return jornada;
	}

	public boolean equipoConJornadaEnJuego(int idEquipo) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();
		Session sesion = sf.openSession();

		boolean jornadaEnJuego = false;

		Query query = sesion.createQuery("from Jornada j where j.estado= 'enJuego' and (idEquipoLocal =" + idEquipo
				+ "or idEquipoVisitante= " + idEquipo + ")");

		List<Jornada> jornadas = query.getResultList();

		if (jornadas.size() != 0) {
			jornadaEnJuego = true;
		}
		sesion.close();
		sf.close();
		return jornadaEnJuego;
	}

}
