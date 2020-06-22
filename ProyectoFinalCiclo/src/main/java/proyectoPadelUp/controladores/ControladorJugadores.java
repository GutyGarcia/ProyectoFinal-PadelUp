package proyectoPadelUp.controladores;

import java.sql.Date;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import proyectoPadelUp.Email;
import proyectoPadelUp.daos.JugadoresDao;
import proyectoPadelUp.pojos.Equipo;
import proyectoPadelUp.pojos.Jugador;

public class ControladorJugadores{

	@FXML
	private AnchorPane altaJugador;
	@FXML
	private TextField textFieldNombre;
	@FXML
	private TextField textFieldApellido1;
	@FXML
	private TextField textFieldApellido2;
	@FXML
	private DatePicker fechaNacimiento;
	@FXML
	private TextField textFieldApodo;
	@FXML
	private TextField textFieldTelefono;
	@FXML
	private TextField textFieldEmail;
	@FXML
	private TextField textFieldLicencia;
	@FXML
	private TextField textFieldPuntos;	
	@FXML
	private ComboBox<String> comboBoxPosicion;
	@FXML 
	private Button botonCancelar, botonAceptar;
	@FXML
	private Text error;
	
	private int idEquipo;
	
	private Email validacionMail = new Email(); 
	private JugadoresDao jugadoresDao = new JugadoresDao();

	
	@FXML
	public void botonAceptar(ActionEvent event){
	
		if(validacionMail.mailCorrecto(textFieldEmail.getText())) {
			Jugador jugador = new Jugador();
			Equipo equipo = new Equipo();
			equipo.setIdEquipo(idEquipo);;
			jugador.setNombre(textFieldNombre.getText());
			jugador.setPrimerApellido(textFieldApellido1.getText());
			jugador.setSegundoApellido(textFieldApellido2.getText());
			jugador.setEstado(true);
			LocalDate localDate = fechaNacimiento.getValue();
			jugador.setFechaNacimiento(Date.valueOf(localDate));
			jugador.setApodo(textFieldApodo.getText());
			jugador.setTelefono(textFieldTelefono.getText());
			jugador.setEmail(textFieldEmail.getText());
			jugador.setPosicion(comboBoxPosicion.getValue());
			jugador.setLicencia(Integer.parseInt(textFieldLicencia.getText()));
			jugador.setPuntos(Integer.parseInt(textFieldPuntos.getText()));		
			jugador.setEquipo(equipo);
			jugadoresDao.altaJugador(jugador);
			Node source = (Node) event.getSource();
		    Stage stage = (Stage) source.getScene().getWindow();
			stage.close();
		}else {
			error.setText("El formato del Email no es correcto");
		}
	}	
	@FXML
	public void botonCancelar(ActionEvent event){
		Node source = (Node) event.getSource();
	    Stage stage = (Stage) source.getScene().getWindow();
	    stage.close();
	}
	public AnchorPane getAltaJugador() {
		return altaJugador;
	}
	public void setAltaJugador(AnchorPane altaJugador) {
		this.altaJugador = altaJugador;
	}
	public TextField getTextFieldNombre() {
		return textFieldNombre;
	}
	public void setTextFieldNombre(TextField textFieldNombre) {
		this.textFieldNombre = textFieldNombre;
	}
	public TextField getTextFieldApellido1() {
		return textFieldApellido1;
	}
	public void setTextFieldApellido1(TextField textFieldApellido1) {
		this.textFieldApellido1 = textFieldApellido1;
	}
	public TextField getTextFieldApellido2() {
		return textFieldApellido2;
	}
	public void setTextFieldApellido2(TextField textFieldApellido2) {
		this.textFieldApellido2 = textFieldApellido2;
	}
	public DatePicker getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(DatePicker fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public TextField getTextFieldApodo() {
		return textFieldApodo;
	}
	public void setTextFieldApodo(TextField textFieldApodo) {
		this.textFieldApodo = textFieldApodo;
	}
	public TextField getTextFieldTelefono() {
		return textFieldTelefono;
	}
	public void setTextFieldTelefono(TextField textFieldTelefono) {
		this.textFieldTelefono = textFieldTelefono;
	}
	public TextField getTextFieldEmail() {
		return textFieldEmail;
	}
	public void setTextFieldEmail(TextField textFieldEmail) {
		this.textFieldEmail = textFieldEmail;
	}
	public TextField getTextFieldLicencia() {
		return textFieldLicencia;
	}
	public void setTextFieldLicencia(TextField textFieldLicencia) {
		this.textFieldLicencia = textFieldLicencia;
	}
	public TextField getTextFieldPuntos() {
		return textFieldPuntos;
	}
	public void setTextFieldPuntos(TextField textFieldPuntos) {
		this.textFieldPuntos = textFieldPuntos;
	}
	public ComboBox<String> getComboBoxPosicion() {
		return comboBoxPosicion;
	}
	public void setComboBoxPosicion(ComboBox<String> comboBoxPosicion) {
		this.comboBoxPosicion = comboBoxPosicion;
	}
	public Button getBotonCancelar() {
		return botonCancelar;
	}
	public void setBotonCancelar(Button botonCancelar) {
		this.botonCancelar = botonCancelar;
	}
	public Button getBotonAceptar() {
		return botonAceptar;
	}
	public void setBotonAceptar(Button botonAceptar) {
		this.botonAceptar = botonAceptar;
	}
	public Text getError() {
		return error;
	}
	public void setError(Text error) {
		this.error = error;
	}
	public int getIdEquipo() {
		return idEquipo;
	}
	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}
	public Email getValidacionMail() {
		return validacionMail;
	}
	public void setValidacionMail(Email validacionMail) {
		this.validacionMail = validacionMail;
	}
	public JugadoresDao getJugadoresDao() {
		return jugadoresDao;
	}
	public void setJugadoresDao(JugadoresDao jugadoresDao) {
		this.jugadoresDao = jugadoresDao;
	}

	
}
