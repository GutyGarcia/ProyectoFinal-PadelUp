package proyectoPadelUp.pojos;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

@Entity
public class Jornada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@NotNull
	private int idJornada;
	@Column(nullable = false)
	private String nombre;
	@Column
	private Date fecha;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idEquipoLocal")
	private Equipo equipoLocal;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idEquipoVisitante")
	private Equipo equipoVisitante;
	@Column(nullable = false)
	private String estado;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "idTemporada")
	private Temporada temporada;

	public int getIdJornada() {
		return idJornada;
	}

	public void setIdJornada(int idJornada) {
		this.idJornada = idJornada;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Equipo getEquipoLocal() {
		return equipoLocal;
	}

	public void setEquipoLocal(Equipo equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public Equipo getEquipoVisitante() {
		return equipoVisitante;
	}

	public void setEquipoVisitante(Equipo equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Temporada getTemporada() {
		return temporada;
	}

	public void setTemporada(Temporada temporada) {
		this.temporada = temporada;
	}

}
