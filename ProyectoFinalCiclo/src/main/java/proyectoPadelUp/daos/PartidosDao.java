package proyectoPadelUp.daos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import proyectoPadelUp.pojos.Jornada;
import proyectoPadelUp.pojos.Jugador;
import proyectoPadelUp.pojos.Partido;

public class PartidosDao {

	public void altaPartido(Partido partido) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session sesion = sf.openSession();
		sesion.beginTransaction();
		sesion.save(partido);
		sesion.getTransaction().commit();
		sesion.close();
		sf.close();

	}

	public void modificarPartido(Partido partido) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session sesion = sf.openSession();
		sesion.beginTransaction();
		sesion.update(partido);
		sesion.getTransaction().commit();
		sesion.close();
		sf.close();
	}

	public List<Partido> consultarPartidosPorJornada(int idJornada) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session sesion = sf.openSession();
		Session sesion2 = sf.openSession();
		Session sesion3 = sf.openSession();

		Query query = sesion.createQuery("from Partido  where estado = 1 and idJornada = " + idJornada);

		List<Partido> partidos = query.getResultList();
		for (Partido partido2 : partidos) {

			Query queryJornada = sesion2.createQuery(
					"from Jornada where  estado = 'enJuego' and idJornada =" + partido2.getJornada().getIdJornada());
			Jornada jornada = new Jornada();
			List<Jornada> jornadas = queryJornada.getResultList();
			for (Jornada jornada2 : jornadas) {
				jornada.setIdJornada(jornada2.getIdJornada());
				jornada.setNombre(jornada2.getNombre());
				partido2.setJornada(jornada2);

			}
			Query queryJugadores = sesion3.createQuery("from Jugador where  estado = 1 and idJugador ="
					+ partido2.getJugador1().getIdJugador() + "or idJugador=" + partido2.getJugador2().getIdJugador());

			Jugador jugador1 = new Jugador();
			Jugador jugador2 = new Jugador();
			List<Jugador> jugadores = queryJugadores.getResultList();
			for (Jugador jugadorEspecial : jugadores) {
				if (partido2.getJugador1().getIdJugador() == jugadorEspecial.getIdJugador()) {
					jugador1.setIdJugador(jugadorEspecial.getIdJugador());
					jugador1.setNombre(jugadorEspecial.getNombre());
					jugador1.setPrimerApellido(jugadorEspecial.getPrimerApellido());
					jugador1.setSegundoApellido(jugadorEspecial.getSegundoApellido());
					jugador1.setApodo(jugadorEspecial.getApodo());
					jugador1.setEmail(jugadorEspecial.getEmail());
					jugador1.setEquipo(jugadorEspecial.getEquipo());
					jugador1.setEstado(jugadorEspecial.isEstado());
					jugador1.setFechaNacimiento(jugadorEspecial.getFechaNacimiento());
					jugador1.setLicencia(jugadorEspecial.getLicencia());
					jugador1.setPosicion(jugadorEspecial.getPosicion());
					jugador1.setPuntos(jugadorEspecial.getPuntos());
					jugador1.setTelefono(jugadorEspecial.getTelefono());

					partido2.setJugador1(jugadorEspecial);

				} else if (partido2.getJugador2().getIdJugador() == jugadorEspecial.getIdJugador()) {
					jugador2.setIdJugador(jugadorEspecial.getIdJugador());
					jugador2.setNombre(jugadorEspecial.getNombre());
					jugador2.setPrimerApellido(jugadorEspecial.getPrimerApellido());
					jugador2.setSegundoApellido(jugadorEspecial.getSegundoApellido());
					jugador2.setApodo(jugadorEspecial.getApodo());
					jugador2.setEmail(jugadorEspecial.getEmail());
					jugador2.setEquipo(jugadorEspecial.getEquipo());
					jugador2.setEstado(jugadorEspecial.isEstado());
					jugador2.setFechaNacimiento(jugadorEspecial.getFechaNacimiento());
					jugador2.setLicencia(jugadorEspecial.getLicencia());
					jugador2.setPosicion(jugadorEspecial.getPosicion());
					jugador2.setPuntos(jugadorEspecial.getPuntos());
					jugador2.setTelefono(jugadorEspecial.getTelefono());

					partido2.setJugador2(jugadorEspecial);

				}

			}

		}
		sesion.close();
		sesion2.close();
		sesion3.close();
		sf.close();

		return partidos;
	}

}
