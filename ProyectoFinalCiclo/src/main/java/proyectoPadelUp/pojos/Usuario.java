package proyectoPadelUp.pojos;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import com.sun.istack.NotNull;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@NotNull
	private int idUsuario;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private String primerApellido;
	@Column(nullable = false)
	private String nombreUsuario;
	@Column(nullable = false)
	private String contrase�aEmail;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String contrase�aUsuario;
	@Column(nullable = false)
	@Type(type = "boolean")
	private boolean estado;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idRol")
	private Rol rol;

	public Usuario() {
		super();
	}
	
	

	public Usuario(int idUsuario, String nombre, String primerApellido, String nombreUsuario, String contrase�aEmail,
			String email, String contrase�aUsuario, boolean estado, Rol rol) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.nombreUsuario = nombreUsuario;
		this.contrase�aEmail = contrase�aEmail;
		this.email = email;
		this.contrase�aUsuario = contrase�aUsuario;
		this.estado = estado;
		this.rol = rol;
	}



	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrase�aEmail() {
		return contrase�aEmail;
	}

	public void setContrase�aEmail(String contrase�aEmail) {
		this.contrase�aEmail = contrase�aEmail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrase�aUsuario() {
		return contrase�aUsuario;
	}

	public void setContrase�aUsuario(String contrase�aUsuario) {
		this.contrase�aUsuario = contrase�aUsuario;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}
