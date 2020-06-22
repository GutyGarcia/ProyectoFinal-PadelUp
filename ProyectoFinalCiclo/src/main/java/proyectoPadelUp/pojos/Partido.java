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
public class Partido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@NotNull
	private int idPartido;
	@Column(nullable=false) 
	private String nombre;
	@Column(nullable=false) 
	private int primerSetlocal;
	@Column(nullable=false) 
	private int primerSetVisitante;
	@Column(nullable=false) 
	private int segundoSetlocal;
	@Column(nullable=false) 
	private int segundoSetVisitante;
	@Column
	private int tercerSetlocal;
	@Column
	private String resultado;
	@Column
	private int tercerSetVisitante;
	@Column(nullable = false)
	@Type(type = "boolean")
	private boolean estado;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable=false, name = "idJugador1")
	private Jugador jugador1;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable=false, name = "idJugador2")
	private Jugador jugador2;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable=false, name = "idJornada")
	private Jornada jornada;
	
	public Partido() {
		super();
	}

	public int getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrimerSetlocal() {
		return primerSetlocal;
	}

	public void setPrimerSetlocal(int primerSetlocal) {
		this.primerSetlocal = primerSetlocal;
	}

	public int getPrimerSetVisitante() {
		return primerSetVisitante;
	}

	public void setPrimerSetVisitante(int primerSetVisitante) {
		this.primerSetVisitante = primerSetVisitante;
	}

	public int getSegundoSetlocal() {
		return segundoSetlocal;
	}

	public void setSegundoSetlocal(int segundoSetlocal) {
		this.segundoSetlocal = segundoSetlocal;
	}

	public int getSegundoSetVisitante() {
		return segundoSetVisitante;
	}

	public void setSegundoSetVisitante(int segundoSetVisitante) {
		this.segundoSetVisitante = segundoSetVisitante;
	}

	public int getTercerSetlocal() {
		return tercerSetlocal;
	}

	public void setTercerSetlocal(int tercerSetlocal) {
		this.tercerSetlocal = tercerSetlocal;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public int getTercerSetVisitante() {
		return tercerSetVisitante;
	}

	public void setTercerSetVisitante(int tercerSetVisitante) {
		this.tercerSetVisitante = tercerSetVisitante;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Jugador getJugador1() {
		return jugador1;
	}

	public void setJugador1(Jugador jugador1) {
		this.jugador1 = jugador1;
	}

	public Jugador getJugador2() {
		return jugador2;
	}

	public void setJugador2(Jugador jugador2) {
		this.jugador2 = jugador2;
	}

	public Jornada getJornada() {
		return jornada;
	}

	public void setJornada(Jornada jornada) {
		this.jornada = jornada;
	}
	
	
	
	
	
	
}