package proyectoPadelUp.daos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import proyectoPadelUp.pojos.Usuario;

public class UsuariosDao {

	public void altaUsuario(Usuario usuario) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();
		Session sesion = sf.openSession();

		sesion.beginTransaction();
		sesion.save(usuario);
		sesion.getTransaction().commit();
		sesion.close();
		sf.close();
	}

	public void modificarUsuario(Usuario usuario) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();
		Session sesion = sf.openSession();

		sesion.beginTransaction();
		sesion.update(usuario);
		sesion.getTransaction().commit();
		sesion.close();
		sf.close();
	}

	public boolean existeUsuario(String username, String contraseñaUsuario) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();
		Session sesion = sf.openSession();

		boolean existeUsuario = false;

		Query query = sesion.createQuery("from Usuario where nombreUsuario='" + username + "' and contraseñaUsuario='"
				+ contraseñaUsuario + "'");

		List<Usuario> usuarios = query.getResultList();

		if (usuarios.size() != 0) {
			existeUsuario = true;
		}
		sesion.close();
		sf.close();
		return existeUsuario;
	}

	public boolean existeUsuarioPorNombre(String username) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();
		Session sesion = sf.openSession();

		boolean existeUsuario = false;

		Query query = sesion.createQuery("from Usuario where nombreUsuario='" + username + "'");

		List<Usuario> usuarios = query.getResultList();

		if (usuarios.size() != 0) {
			existeUsuario = true;
		}
		sesion.close();
		sf.close();
		return existeUsuario;
	}

	public Usuario buscarUsuarioPorNombre(String username) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();
		Session sesion = sf.openSession();

		Usuario usuario = new Usuario();
		Query query = sesion.createQuery("from Usuario where nombreUsuario='" + username + "'");

		List<Usuario> usuarios = query.getResultList();

		for (Usuario usuario2 : usuarios) {

			usuario.setIdUsuario(usuario2.getIdUsuario());
			usuario.setNombre(usuario2.getNombre());
			usuario.setPrimerApellido(usuario2.getPrimerApellido());
			usuario.setNombreUsuario(usuario2.getNombreUsuario());
			usuario.setEmail(usuario2.getEmail());
			usuario.setContraseñaUsuario(usuario2.getContraseñaUsuario());
			usuario.setContraseñaEmail(usuario2.getContraseñaEmail());
		}
		sesion.close();
		sf.close();
		return usuario;
	}

	public Usuario buscarUsuarioPorId(int idUsuario) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();
		Session sesion = sf.openSession();

		Usuario usuario = new Usuario();
		Query query = sesion.createQuery("from Usuario where idUsuario='" + idUsuario + "'");

		List<Usuario> usuarios = query.getResultList();

		for (Usuario usuario2 : usuarios) {

			usuario.setIdUsuario(usuario2.getIdUsuario());
			usuario.setNombre(usuario2.getNombre());
			usuario.setPrimerApellido(usuario2.getPrimerApellido());
			usuario.setNombreUsuario(usuario2.getNombreUsuario());
			usuario.setEmail(usuario2.getEmail());
			usuario.setContraseñaUsuario(usuario2.getContraseñaUsuario());
			usuario.setContraseñaEmail(usuario2.getContraseñaEmail());
		}
		sesion.close();
		sf.close();
		return usuario;
	}
}
