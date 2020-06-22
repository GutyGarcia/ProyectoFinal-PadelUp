package proyectoPadelUp.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Type;

import com.sun.istack.NotNull;

@Entity
public class Temporada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@NotNull
	@Column(name = "idTemporada")
	private int idTemporada;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private String division;
	@Column(nullable = false)
	private String grupo;
	@Column(nullable = false)
	private String categoria;
	@Column(nullable = false)
	@Type(type = "boolean")
	private boolean estado;
	@Column(nullable = false)
	private int numJornadas;
	@Column(name = "idEquipo")
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "temporada_equipo", joinColumns = { @JoinColumn(name = "idTemporada") }, inverseJoinColumns = {
			@JoinColumn(name = "idEquipo") })
	private List<Equipo> equipos;

	public Temporada() {
		super();
	}

	public int getIdTemporada() {
		return idTemporada;
	}

	public void setIdTemporada(int idTemporada) {
		this.idTemporada = idTemporada;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public int getNumJornadas() {
		return numJornadas;
	}

	public void setNumJornadas(int numJornadas) {
		this.numJornadas = numJornadas;
	}

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public void anhadirEquipo(Equipo equipo) {
		equipos = new ArrayList<Equipo>();
		equipos.add(equipo);
	}

}
