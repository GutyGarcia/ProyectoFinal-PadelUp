package proyectoPadelUp.pojos;

public class ParejaPartido {

	private int idJugador1, idJugador2;
	private String nombreJugador1,nombreJugador2,nombrePareja;
	private String posicionJugador1, posicionJugador2;
	private int puntosJugador1, puntosJugador2;
	private int totalPuntos;

	public ParejaPartido(Jugador jugador1, Jugador jugador2) {
		this.idJugador1 = jugador1.getIdJugador();
		this.idJugador2 = jugador2.getIdJugador();
		this.nombreJugador1 = jugador1.getNombre();
		this.nombreJugador2 = jugador2.getNombre();
		this.posicionJugador1 = jugador1.getPosicion();
		this.posicionJugador2 = jugador2.getPosicion();
		this.puntosJugador1 = jugador1.getPuntos();
		this.puntosJugador2 = jugador2.getPuntos();		
		this.totalPuntos = puntosJugador1 + puntosJugador2;
		
	}

	public int getIdJugador1() {
		return idJugador1;
	}

	public void setIdJugador1(int idJugador1) {
		this.idJugador1 = idJugador1;
	}

	public int getIdJugador2() {
		return idJugador2;
	}

	public void setIdJugador2(int idJugador2) {
		this.idJugador2 = idJugador2;
	}

	public String getNombreJugador1() {
		return nombreJugador1;
	}

	public void setNombreJugador1(String nombreJugador1) {
		this.nombreJugador1 = nombreJugador1;
	}

	public String getNombreJugador2() {
		return nombreJugador2;
	}

	public void setNombreJugador2(String nombreJugador2) {
		this.nombreJugador2 = nombreJugador2;
	}

	public String getNombrePareja() {
		return nombrePareja;
	}

	public void setNombrePareja(String nombrePareja) {
		this.nombrePareja = nombrePareja;
	}

	public String getPosicionJugador1() {
		return posicionJugador1;
	}

	public void setPosicionJugador1(String posicionJugador1) {
		this.posicionJugador1 = posicionJugador1;
	}

	public String getPosicionJugador2() {
		return posicionJugador2;
	}

	public void setPosicionJugador2(String posicionJugador2) {
		this.posicionJugador2 = posicionJugador2;
	}

	public int getPuntosJugador1() {
		return puntosJugador1;
	}

	public void setPuntosJugador1(int puntosJugador1) {
		this.puntosJugador1 = puntosJugador1;
	}

	public int getPuntosJugador2() {
		return puntosJugador2;
	}

	public void setPuntosJugador2(int puntosJugador2) {
		this.puntosJugador2 = puntosJugador2;
	}

	public int getTotalPuntos() {
		return totalPuntos;
	}

	public void setTotalPuntos(int totalPuntos) {
		this.totalPuntos = totalPuntos;
	}
	
	
}
