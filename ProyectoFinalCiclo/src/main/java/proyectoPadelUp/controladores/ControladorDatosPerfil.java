package proyectoPadelUp.controladores;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import proyectoPadelUp.AplicacionPadelUp;
import proyectoPadelUp.Email;
import proyectoPadelUp.daos.UsuariosDao;
import proyectoPadelUp.pojos.Usuario;

public class ControladorDatosPerfil implements Initializable{
	
	@FXML
	private AnchorPane editarUsuario;
	@FXML
	private TextField textFieldNombre;
	@FXML
	private TextField textFieldApellido1;
	@FXML
	private TextField textFieldNombreUsuario;
	@FXML
	private TextField textFieldEmail;
	@FXML
	private PasswordField contrase�a;	
	@FXML
	private PasswordField contrase�aEmail;
	@FXML
	private PasswordField repetirContrase�a;
	@FXML 
	private Button botonCancelar, botonAceptar;
	@FXML
	private Text error;
	private UsuariosDao usuariosDao = new UsuariosDao();
	private Email validacionMail = new Email();
	private String cadenaFinalEmail;
	
	private IntegerProperty index = new SimpleIntegerProperty(-1);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 index.addListener((ob,n,n1)->{
			 Usuario usuario = usuariosDao.buscarUsuarioPorId(index.get());
			 textFieldNombre.setText(usuario.getNombre());
			 textFieldApellido1.setText(usuario.getPrimerApellido());
			 textFieldNombreUsuario.setText(usuario.getNombreUsuario());
			 textFieldEmail.setText(usuario.getEmail());
			 contrase�a.setText(usuario.getContrase�aUsuario());
			 contrase�aEmail.setText(usuario.getContrase�aEmail());			 
		    });	
	}
	
	@FXML
	public void botonAceptar(ActionEvent event){
		String[] parts = textFieldEmail.getText().split(Pattern.quote("@"));  
		
		if (!Arrays.asList(parts).isEmpty()) { 
			cadenaFinalEmail = Arrays.asList(parts).get(Arrays.asList(parts).size()-1);		
		}
		
		Usuario usuario = new Usuario();
		usuario.setNombre(textFieldNombre.getText());
		usuario.setPrimerApellido(textFieldApellido1.getText());
		usuario.setNombreUsuario(textFieldNombreUsuario.getText());
		usuario.setEmail(textFieldEmail.getText());		
		usuario.setContrase�aUsuario(contrase�a.getText());
		usuario.setContrase�aEmail(contrase�aEmail.getText());
		usuario.setEstado(true);
		usuario.setIdUsuario(AplicacionPadelUp.getUserId());
		if(validacionMail.mailCorrecto(textFieldEmail.getText()) && cadenaFinalEmail.equals("gmail.com")) {	
			if(contrase�a.getText().equals(repetirContrase�a.getText())) {		
				usuariosDao.modificarUsuario(usuario);
				Node source = (Node) event.getSource();
			    Stage stage = (Stage) source.getScene().getWindow();
				stage.close();
			}else {
				error.setText("Verificaci�n de contrase�a de usuario err�nea");
			}
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

	public void setIndex(int index){
	    this.index.set(index);
	}
	public int getIndex(){
	    return index.get();
	}

	public AnchorPane getEditarUsuario() {
		return editarUsuario;
	}

	public void setEditarUsuario(AnchorPane editarUsuario) {
		this.editarUsuario = editarUsuario;
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

	public TextField getTextFieldNombreUsuario() {
		return textFieldNombreUsuario;
	}

	public void setTextFieldNombreUsuario(TextField textFieldNombreUsuario) {
		this.textFieldNombreUsuario = textFieldNombreUsuario;
	}

	public TextField getTextFieldEmail() {
		return textFieldEmail;
	}

	public void setTextFieldEmail(TextField textFieldEmail) {
		this.textFieldEmail = textFieldEmail;
	}

	public PasswordField getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(PasswordField contrase�a) {
		this.contrase�a = contrase�a;
	}

	public PasswordField getContrase�aEmail() {
		return contrase�aEmail;
	}

	public void setContrase�aEmail(PasswordField contrase�aEmail) {
		this.contrase�aEmail = contrase�aEmail;
	}

	public PasswordField getRepetirContrase�a() {
		return repetirContrase�a;
	}

	public void setRepetirContrase�a(PasswordField repetirContrase�a) {
		this.repetirContrase�a = repetirContrase�a;
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

	public UsuariosDao getUsuariosDao() {
		return usuariosDao;
	}

	public void setUsuariosDao(UsuariosDao usuariosDao) {
		this.usuariosDao = usuariosDao;
	}

	public Email getValidacionMail() {
		return validacionMail;
	}

	public void setValidacionMail(Email validacionMail) {
		this.validacionMail = validacionMail;
	}

	public String getCadenaFinalEmail() {
		return cadenaFinalEmail;
	}

	public void setCadenaFinalEmail(String cadenaFinalEmail) {
		this.cadenaFinalEmail = cadenaFinalEmail;
	}

	public void setIndex(IntegerProperty index) {
		this.index = index;
	}
	
	
}
