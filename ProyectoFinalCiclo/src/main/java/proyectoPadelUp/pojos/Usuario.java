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
	private String contraseñaEmail;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String contraseñaUsuario;
	@Column(nullable = false)
	@Type(type = "boolean")
	private boolean estado;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idRol")
	private Rol rol;

	public Usuario() {
		super();
	}
	
	

	public Usuario(int idUsuario, String nombre, String primerApellido, String nombreUsuario, String contraseñaEmail,
			String email, String contraseñaUsuario, boolean estado, Rol rol) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.nombreUsuario = nombreUsuario;
		this.contraseñaEmail = contraseñaEmail;
		this.email = email;
		this.contraseñaUsuario = contraseñaUsuario;
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

	public String getContraseñaEmail() {
		return contraseñaEmail;
	}

	public void setContraseñaEmail(String contraseñaEmail) {
		this.contraseñaEmail = contraseñaEmail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContraseñaUsuario() {
		return contraseñaUsuario;
	}

	public void setContraseñaUsuario(String contraseñaUsuario) {
		this.contraseñaUsuario = contraseñaUsuario;
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
