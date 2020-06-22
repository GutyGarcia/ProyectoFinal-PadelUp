package proyectoPadelUp.controladores;

import proyectoPadelUp.*;
import proyectoPadelUp.daos.*;
import proyectoPadelUp.pojos.Equipo;
import proyectoPadelUp.pojos.Jugador;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ControladorEditarJugador implements Initializable{
	
	@FXML
	AnchorPane altaJugador;
	@FXML
	private TextField textFieldNombre;
	@FXML
	private TextField textFieldApellido1;
	@FXML
	private TextField textFieldApellido2;
	@FXML
	private TextField textFieldApodo;
	@FXML
	private TextField textFieldTelefono;
	@FXML
	private TextField textFieldEmail;
	@FXML
	private TextField textFieldPuntos;	
	@FXML
	private TextField textFieldLicencia;
	@FXML
	ComboBox<String> comboBoxPosicion;
	@FXML 
	private Button botonCancelar, botonAceptar;
	@FXML
	DatePicker fechaNacimiento;
	@FXML
	Text error;
	
	private int idEquipo;
	
	Email validacionMail = new Email(); 
	JugadoresDao jugadoresDao = new JugadoresDao();

	private IntegerProperty index = new SimpleIntegerProperty(-1);

	public void setIndex(int index){
	    this.index.set(index);
	}
	public int getIndex(){
	    return index.get();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 index.addListener((ob,n,n1)->{
			 	jugadoresDao = new JugadoresDao();
				cargarDatos();
		    });		
	}
	
	private void cargarDatos() {
		Jugador jugador = jugadoresDao.consultarJugadorPorId(index.get());
		textFieldNombre.setText(jugador.getNombre());
		textFieldApellido1.setText(jugador.getPrimerApellido());
		textFieldApellido2.setText(jugador.getSegundoApellido());
		Date date = jugador.getFechaNacimiento();
		fechaNacimiento.setValue(date.toLocalDate());
		textFieldApodo.setText(jugador.getApodo());
		textFieldTelefono.setText(jugador.getTelefono());
		textFieldLicencia.setText(String.valueOf(jugador.getLicencia()));
		textFieldEmail.setText(jugador.getEmail());
		textFieldPuntos.setText(String.valueOf(jugador.getPuntos()));
		comboBoxPosicion.setValue(jugador.getPosicion());
		idEquipo = jugador.getEquipo().getIdEquipo();
	}

	@FXML
	public void botonAceptar(ActionEvent event){
		
		if(validacionMail.mailCorrecto(textFieldEmail.getText())) {
			Jugador jugador = new Jugador();
			Equipo equipo = new Equipo();
			equipo.setIdEquipo(idEquipo);
			jugador.setIdJugador(index.get());
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
			jugador.setPuntos(Integer.parseInt(textFieldPuntos.getText()));	
			jugador.setLicencia(Integer.parseInt(textFieldLicencia.getText()));
			jugador.setEquipo(equipo);
			jugadoresDao.modificarJugador(jugador);
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

	public ComboBox<String> getComboBoxPosicion() {
		return comboBoxPosicion;
	}
	public void setComboBoxPosicion(ComboBox<String> comboBoxPosicion) {
		this.comboBoxPosicion = comboBoxPosicion;
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
	public TextField getTextFieldPuntos() {
		return textFieldPuntos;
	}
	public void setTextFieldPuntos(TextField textFieldPuntos) {
		this.textFieldPuntos = textFieldPuntos;
	}
	public Button getBotonAceptar() {
		return botonAceptar;
	}
	public void setBotonAceptar(Button botonAceptar) {
		this.botonAceptar = botonAceptar;
	}
	public int getIdEquipo() {
		return idEquipo;
	}
	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}
	public TextField getTextFieldApellido2() {
		return textFieldApellido2;
	}
	public void setTextFieldApellido2(TextField textFieldApellido2) {
		this.textFieldApellido2 = textFieldApellido2;
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
	public DatePicker getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(DatePicker fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public TextField getTextFieldLicencia() {
		return textFieldLicencia;
	}
	public void setTextFieldLicencia(TextField textFieldLicencia) {
		this.textFieldLicencia = textFieldLicencia;
	}
	

}
	
	


