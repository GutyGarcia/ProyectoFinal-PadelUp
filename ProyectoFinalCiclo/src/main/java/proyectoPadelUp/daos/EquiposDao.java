package proyectoPadelUp.daos;

import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import proyectoPadelUp.*;
import proyectoPadelUp.pojos.Equipo;
import proyectoPadelUp.pojos.Usuario;

public class EquiposDao {

	public List<Equipo> consultarTodos() {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session sesion = sf.openSession();
		Equipo equipo = new Equipo();
		Query query = sesion.createQuery("from Equipo where estado = 1 order by nombre");

		List<Equipo> equipos = query.getResultList();

		for (Equipo equipos2 : equipos) {

			equipo.setIdEquipo(equipos2.getIdEquipo());
			equipo.setNombre(equipos2.getNombre());
			equipo.setCategoria(equipos2.getCategoria());
			equipo.setClub(equipos2.getClub());
			equipo.setDireccion(equipos2.getDireccion());
			equipo.setTelefono(equipos2.getTelefono());
			equipo.setDivision(equipos2.getDivision());
			equipo.setGrupo(equipos2.getGrupo());
			equipo.setEstado(equipos2.isEstado());
		}
		sesion.close();
		sf.close();

		return equipos;
	}

	public Equipo consultarEquipoPorId(int idEquipo) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session sesion = sf.openSession();
		Equipo equipo = new Equipo();
		Query query = sesion.createQuery("from Equipo where idEquipo = " + idEquipo);
		
		List<Equipo> equipos = query.getResultList();
		
		for(Equipo equipos2 : equipos) {
			
			equipo.setIdEquipo(equipos2.getIdEquipo());
			equipo.setNombre(equipos2.getNombre());
			equipo.setCategoria(equipos2.getCategoria());
			equipo.setClub(equipos2.getClub());
			equipo.setDireccion(equipos2.getDireccion());
			equipo.setTelefono(equipos2.getTelefono());
			equipo.setDivision(equipos2.getDivision());
			equipo.setGrupo(equipos2.getGrupo());
			equipo.setEstado(equipos2.isEstado());
	}		
		sesion.close();
		sf.close();
		
		return equipo;
	}

	public List<Equipo> consultarRivales(int idEquipo, String division, String grupo) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session sesion = sf.openSession();
		Equipo equipo = new Equipo();
		Query query = sesion
				.createQuery("from Equipo where idEquipo !=" + "'" + idEquipo + "'" + "and estado = 1 and division="
						+ "'" + division + "'" + "and grupo=" + "'" + grupo + "'" + " order by nombre");

		List<Equipo> equipos = query.getResultList();

		for (Equipo equipos2 : equipos) {

			equipo.setIdEquipo(equipos2.getIdEquipo());
			equipo.setNombre(equipos2.getNombre());
			equipo.setCategoria(equipos2.getCategoria());
			equipo.setClub(equipos2.getClub());
			equipo.setDireccion(equipos2.getDireccion());
			equipo.setTelefono(equipos2.getTelefono());
			equipo.setDivision(equipos2.getDivision());
			equipo.setGrupo(equipos2.getGrupo());
			equipo.setEstado(equipos2.isEstado());

		}
		sesion.close();
		sf.close();

		return equipos;

	}

	public List<Equipo> consultarTodosEquiposActivos() {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session sesion = sf.openSession();
		Equipo equipo = new Equipo();
		Query query = sesion.createQuery("from Equipo where idUsuario = 1 and estado = 1 order by nombre");

		List<Equipo> equipos = query.getResultList();

		for (Equipo equipos2 : equipos) {

			equipo.setIdEquipo(equipos2.getIdEquipo());
			equipo.setNombre(equipos2.getNombre());
			equipo.setCategoria(equipos2.getCategoria());
			equipo.setClub(equipos2.getClub());
			equipo.setDireccion(equipos2.getDireccion());
			equipo.setTelefono(equipos2.getTelefono());
			equipo.setDivision(equipos2.getDivision());
			equipo.setGrupo(equipos2.getGrupo());
			equipo.setEstado(equipos2.isEstado());

		}
		sesion.close();
		sf.close();

		return equipos;

	}

	private List<Equipo> consultarEquiposActivos() {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session sesion = sf.openSession();
		Session sesion2 = sf.openSession();
		Equipo equipo = new Equipo();
		Query query = sesion.createQuery("from Equipo where estado = 1");

		List<Equipo> equipos = query.getResultList();

		for (Equipo equipos2 : equipos) {

			equipo.setIdEquipo(equipos2.getIdEquipo());
			equipo.setNombre(equipos2.getNombre());
			equipo.setCategoria(equipos2.getCategoria());
			equipo.setClub(equipos2.getClub());
			equipo.setDireccion(equipos2.getDireccion());
			equipo.setTelefono(equipos2.getTelefono());
			equipo.setDivision(equipos2.getDivision());
			equipo.setGrupo(equipos2.getGrupo());
			equipo.setEstado(equipos2.isEstado());
			Query queryEquipo = sesion2
					.createQuery("from Usuario where  idUsuario =" + equipos2.getUsuario().getIdUsuario());
			Usuario usuario = new Usuario();
			List<Usuario> usuarios = queryEquipo.getResultList();
			for (Usuario usuario2 : usuarios) {

				usuario.setIdUsuario(usuario2.getIdUsuario());
				usuario.setNombre(usuario2.getNombre());
				usuario.setPrimerApellido(usuario2.getPrimerApellido());
				usuario.setNombreUsuario(usuario2.getNombreUsuario());
				usuario.setEmail(usuario2.getEmail());
				usuario.setContraseñaUsuario(usuario2.getContraseñaUsuario());
				usuario.setContraseñaEmail(usuario2.getContraseñaEmail());
				usuario.setEstado(usuario2.isEstado());
				equipo.setUsuario(usuario2);
			}
		}
		sesion.close();
		sesion2.close();
		sf.close();

		return equipos;

	}

	public List<Equipo> consultarEquipoPorUsuario(int IdUsuario) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session sesion = sf.openSession();
		Session sesion2 = sf.openSession();
		Equipo equipo = new Equipo();
		Query query = sesion.createQuery("from Equipo where estado = 1 and idUsuario=" + IdUsuario);

		List<Equipo> equipos = query.getResultList();

		for (Equipo equipos2 : equipos) {

			equipo.setIdEquipo(equipos2.getIdEquipo());
			equipo.setNombre(equipos2.getNombre());
			equipo.setCategoria(equipos2.getCategoria());
			equipo.setClub(equipos2.getClub());
			equipo.setDireccion(equipos2.getDireccion());
			equipo.setTelefono(equipos2.getTelefono());
			equipo.setDivision(equipos2.getDivision());
			equipo.setGrupo(equipos2.getGrupo());
			equipo.setEstado(equipos2.isEstado());

			equipo.setUsuario(equipos2.getUsuario());
			Query queryEquipo = sesion2
					.createQuery("from Usuario where  idUsuario =" + equipos2.getUsuario().getIdUsuario());
			Usuario usuario = new Usuario();
			List<Usuario> usuarios = queryEquipo.getResultList();
			for (Usuario usuario2 : usuarios) {

				usuario.setIdUsuario(usuario2.getIdUsuario());
				usuario.setNombre(usuario2.getNombre());
				usuario.setPrimerApellido(usuario2.getPrimerApellido());
				usuario.setNombreUsuario(usuario2.getNombreUsuario());
				usuario.setEmail(usuario2.getEmail());
				usuario.setContraseñaUsuario(usuario2.getContraseñaUsuario());
				usuario.setContraseñaEmail(usuario2.getContraseñaEmail());
				usuario.setEstado(usuario2.isEstado());
				equipo.setUsuario(usuario2);
			}
		}
		sesion.close();
		sesion2.close();
		sf.close();

		return equipos;

	}

	public void altaEquipo(Equipo equipo) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session sesion = sf.openSession();
		sesion.beginTransaction();
		sesion.save(equipo);
		sesion.getTransaction().commit();
		sesion.close();
		sf.close();
	}

	public void modificarEquipo(Equipo equipo) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session sesion = sf.openSession();
		sesion.beginTransaction();
		sesion.update(equipo);
		sesion.getTransaction().commit();
		sesion.close();
		sf.close();
	}

	public Equipo consultarEquipoActivoId(int idEquipo) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session sesion = sf.openSession();
		Session sesion2 = sf.openSession();
		Query query = sesion.createQuery("from Equipo where estado = 1 and idEquipo=" + idEquipo);
		List<Equipo> equipos = query.getResultList();
		Equipo equipo = new Equipo();
		for (Equipo equipo2 : equipos) {
			equipo.setIdEquipo(equipo2.getIdEquipo());
			equipo.setNombre(equipo2.getNombre());
			equipo.setCategoria(equipo2.getCategoria());
			equipo.setClub(equipo2.getClub());
			equipo.setDireccion(equipo2.getDireccion());
			equipo.setTelefono(equipo2.getTelefono());
			equipo.setDivision(equipo2.getDivision());
			equipo.setGrupo(equipo2.getGrupo());
			equipo.setEstado(equipo2.isEstado());

			equipo.setUsuario(equipo2.getUsuario());
			Query queryEquipo = sesion2
					.createQuery("from Usuario where  idUsuario =" + equipo2.getUsuario().getIdUsuario());
			Usuario usuario = new Usuario();
			List<Usuario> usuarios = queryEquipo.getResultList();
			for (Usuario usuario2 : usuarios) {

				usuario.setIdUsuario(usuario2.getIdUsuario());
				usuario.setNombre(usuario2.getNombre());
				usuario.setPrimerApellido(usuario2.getPrimerApellido());
				usuario.setNombreUsuario(usuario2.getNombreUsuario());
				usuario.setEmail(usuario2.getEmail());
				usuario.setContraseñaUsuario(usuario2.getContraseñaUsuario());
				usuario.setContraseñaEmail(usuario2.getContraseñaEmail());
				usuario.setEstado(usuario2.isEstado());
				equipo.setUsuario(usuario2);
			}

		}
		sesion.close();
		sf.close();

		return equipo;
	}

}
