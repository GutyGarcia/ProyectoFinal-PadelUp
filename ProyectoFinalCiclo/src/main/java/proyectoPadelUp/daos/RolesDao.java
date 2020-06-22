package proyectoPadelUp.daos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import proyectoPadelUp.pojos.Rol;
import proyectoPadelUp.pojos.Usuario;

public class RolesDao {
	
	public void altaRol(Rol rol) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session sesion = sf.openSession();
		sesion.beginTransaction();
		sesion.save(rol);
		sesion.getTransaction().commit();
		sesion.close();
		sf.close();

	}

	public Rol consultarRolPorId(int idRol) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session sesion = sf.openSession();
		Rol rol = new Rol();
		Query query = sesion.createQuery("from Rol where idRol = " + idRol);

		List<Rol> roles = query.getResultList();

		for (Rol rol2 : roles) {
			rol.setIdRol(rol2.getIdRol());
			rol.setNombre(rol2.getNombre());
		}
		sesion.close();
		sf.close();
		return rol;
	}

	public Rol consultarRolPorIdUsuario(int idUsuario) {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();

		Session sesion = sf.openSession();
		Rol rol = new Rol();
		Query query = sesion.createQuery("from Rol where idRol = " + idUsuario);

		List<Rol> roles = query.getResultList();

		for (Rol rol2 : roles) {
			rol.setIdRol(rol2.getIdRol());
			rol.setNombre(rol2.getNombre());
		}
		sesion.close();
		sf.close();
		return rol;
	}
	
	public boolean existeRol() {
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();
		Session sesion = sf.openSession();

		boolean existeRol = false;

		Query query = sesion.createQuery("from Rol");
		
		List<Rol> roles = query.getResultList();

		if (roles.size() != 0) {
			existeRol = true;
		}
		sesion.close();
		sf.close();
		return existeRol;
	}

}
