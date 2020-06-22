package proyectoPadelUp.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import proyectoPadelUp.TablaAltaJugador;
import proyectoPadelUp.pojos.Equipo;
import proyectoPadelUp.pojos.Jugador;

public class JugadoresDao {

	private String imagen = "✔";

	public void altaJugador(Jugador jugador) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session sesion = sf.openSession();
		sesion.beginTransaction();
		sesion.save(jugador);
		sesion.getTransaction().commit();
		sesion.close();
		sf.close();

	}

	public void modificarJugador(Jugador jugador) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session sesion = sf.openSession();
		sesion.beginTransaction();
		sesion.update(jugador);
		sesion.getTransaction().commit();
		sesion.close();
		sf.close();
	}

	public List<Jugador> consultarTodosPorEquipo(int IdEquipo) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session sesion = sf.openSession();
		Query query = sesion
				.createQuery("from Jugador where estado = 1 and idEquipo=" + IdEquipo + "order by idEquipo");

		List<Jugador> jugadores = query.getResultList();

		sesion.close();
		sf.close();

		return jugadores;
	}

	public List<TablaAltaJugador> consultarTodosPorEquipoTabla(int IdEquipo) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session sesion = sf.openSession();

		Query query = sesion.createQuery("from Jugador where estado = 1 and idEquipo=" + IdEquipo + "order by nombre");

		List<Jugador> jugadores = query.getResultList();
		List<TablaAltaJugador> jugadoresTabla = new ArrayList<TablaAltaJugador>();

		for (Jugador jugador2 : jugadores) {

			TablaAltaJugador jugador = new TablaAltaJugador();

			jugador.setIdJugador(jugador2.getIdJugador());
			jugador.setApodo(jugador2.getApodo());
			jugador.setNombreJugador(jugador2.getNombre());
			switch (jugador2.getPosicion()) {
			case "derecha":
				jugador.setDerecha(imagen);
				break;
			case "reves":
				jugador.setReves(imagen);

				break;
			case "ambas":
				jugador.setDerecha(imagen);
				jugador.setReves(imagen);
				break;

			default:
				break;
			}
			jugadoresTabla.add(jugador);

		}
		sesion.close();
		sf.close();

		return jugadoresTabla;
	}

	public Jugador consultarJugadorPorId(int idJugador) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session sesion = sf.openSession();
		Session sesion2 = sf.openSession();
		Jugador jugador = new Jugador();
		Query query = sesion.createQuery("from Jugador where estado = 1 and idJugador=" + idJugador);
		List<Jugador> jugadores = query.getResultList();

		for (Jugador jugador2 : jugadores) {

			jugador.setIdJugador(jugador2.getIdJugador());
			jugador.setNombre(jugador2.getNombre());
			jugador.setPrimerApellido(jugador2.getPrimerApellido());
			jugador.setSegundoApellido(jugador2.getSegundoApellido());
			jugador.setApodo(jugador2.getApodo());
			jugador.setFechaNacimiento(jugador2.getFechaNacimiento());
			jugador.setApodo(jugador2.getApodo());
			jugador.setEstado(jugador2.isEstado());
			jugador.setTelefono(jugador2.getTelefono());
			jugador.setLicencia(jugador2.getLicencia());
			jugador.setEmail(jugador2.getEmail());
			jugador.setPosicion(jugador2.getPosicion());
			jugador.setPuntos(jugador2.getPuntos());
			Query queryEquipo = sesion2
					.createQuery("from Equipo where  estado = 1 and idEquipo =" + jugador2.getEquipo().getIdEquipo());
			Equipo equipo = new Equipo();
			List<Equipo> equipos = queryEquipo.getResultList();
			for (Equipo equipo2 : equipos) {
				equipo.setIdEquipo(equipo2.getIdEquipo());
				equipo.setNombre(equipo2.getNombre());
				jugador.setEquipo(equipo2);
			}

		}
		sesion.close();
		sesion2.close();
		sf.close();

		return jugador;
	}
}
