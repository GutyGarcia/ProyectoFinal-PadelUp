package proyectoPadelUp;

import javafx.scene.control.CheckBox;


public class TablaAltaJugador {

	private CheckBox convocar = new CheckBox();
	private String nombreJugador, derecha, reves, ambas, apodo;
	private int idJugador;
	
	public TablaAltaJugador() {
		super();
	}

	public CheckBox getConvocar() {
		return convocar;
	}

	public void setConvocar(CheckBox convocar) {
		this.convocar = convocar;
	}

	public String getNombreJugador() {
		return nombreJugador;
	}

	public void setNombreJugador(String nombreJugador) {
		this.nombreJugador = nombreJugador;
	}

	public String getDerecha() {
		return derecha;
	}

	public void setDerecha(String derecha) {
		this.derecha = derecha;
	}

	public String getReves() {
		return reves;
	}

	public void setReves(String reves) {
		this.reves = reves;
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public String getAmbas() {
		return ambas;
	}

	public void setAmbas(String ambas) {
		this.ambas = ambas;
	}
	
	

}
