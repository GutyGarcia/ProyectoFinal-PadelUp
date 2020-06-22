package proyectoPadelUp.controladores;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import proyectoPadelUp.Email;
import proyectoPadelUp.daos.RolesDao;
import proyectoPadelUp.daos.UsuariosDao;
import proyectoPadelUp.pojos.Rol;
import proyectoPadelUp.pojos.Usuario;

public class ControladorAltaUsuario extends ControladorConNavegabilidad implements Initializable {

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
	private Rol rol = new Rol();
	private RolesDao rolDao = new RolesDao();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		textFieldEmail.setPromptText("@gmail.com");

		this.getTextFieldNombre().textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\sa-zA-Z*")) {
				this.getTextFieldNombre().setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
			}
		});
		this.getTextFieldNombre().textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\sa-zA-Z*")) {
				this.getTextFieldNombre().setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
			}
		});

		this.getTextFieldApellido1().textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\sa-zA-Z*")) {
				this.getTextFieldApellido1().setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
			}
		});

		BooleanBinding isTextFieldEmpty = Bindings.isEmpty(this.getTextFieldNombre().textProperty())
				.or(Bindings.isEmpty(this.getTextFieldApellido1().textProperty()))
				.or(Bindings.isEmpty(this.getTextFieldEmail().textProperty()))
				.or(Bindings.isEmpty(this.getContrase�a().textProperty()))
				.or(Bindings.isEmpty(this.getRepetirContrase�a().textProperty()))
				.or(Bindings.isEmpty(this.getTextFieldNombreUsuario().textProperty()))
				.or(Bindings.isEmpty(this.getContrase�aEmail().textProperty()))
				.or(Bindings.isEmpty(this.getTextFieldEmail().textProperty()));
		this.getBotonAceptar().disableProperty().bind(isTextFieldEmpty);

	}

	@FXML
	public void botonAceptar(ActionEvent event) {

		String[] parts = textFieldEmail.getText().split(Pattern.quote("@"));

		if (!Arrays.asList(parts).isEmpty()) {
			cadenaFinalEmail = Arrays.asList(parts).get(Arrays.asList(parts).size() - 1);
		}

		Usuario usuario = new Usuario();
		usuario.setNombre(textFieldNombre.getText());
		usuario.setPrimerApellido(textFieldApellido1.getText());
		usuario.setNombreUsuario(textFieldNombreUsuario.getText());
		usuario.setEmail(textFieldEmail.getText());
		usuario.setContrase�aUsuario(contrase�a.getText());
		usuario.setContrase�aEmail(contrase�aEmail.getText());
		usuario.setEstado(true);
		rol = rolDao.consultarRolPorId(2);
		usuario.setRol(rol);
		if (validacionMail.mailCorrecto(textFieldEmail.getText()) && cadenaFinalEmail.equals("gmail.com")) {
			if (usuariosDao.existeUsuarioPorNombre(textFieldNombreUsuario.getText())) {
				error.setText("El nombre de usuario ya existe");
			} else if (contrase�a.getText().equals(repetirContrase�a.getText())) {
				usuariosDao.altaUsuario(usuario);
				textFieldNombre.clear();
				textFieldApellido1.clear();
				textFieldEmail.clear();
				textFieldNombreUsuario.clear();
				contrase�a.clear();
				contrase�aEmail.clear();
				repetirContrase�a.clear();
				error.setText("");
				mostrarLogin();
			} else {
				error.setText("Verificaci�n de contrase�a de usuario err�nea");
			}
		} else {
			error.setText("El formato del Email no es correcto");
		}
	}

	@FXML
	public void botonCancelar(ActionEvent event) {
		mostrarLogin();
		textFieldNombre.clear();
		textFieldApellido1.clear();
		textFieldNombreUsuario.clear();
		textFieldEmail.clear();
		contrase�a.clear();
		repetirContrase�a.clear();
		repetirContrase�a.clear();
		error.setText("");

	}

	private void mostrarLogin() {
		this.layout.mostrarComoPantallaActual("Login");
		Stage stage = (Stage) layout.getScene().getWindow();
		stage.sizeToScene();

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

	public Email getValidacionMail() {
		return validacionMail;
	}

	public void setValidacionMail(Email validacionMail) {
		this.validacionMail = validacionMail;
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

	public String getCadenaFinalEmail() {
		return cadenaFinalEmail;
	}

	public void setCadenaFinalEmail(String cadenaFinalEmail) {
		this.cadenaFinalEmail = cadenaFinalEmail;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public RolesDao getRolDao() {
		return rolDao;
	}

	public void setRolDao(RolesDao rolDao) {
		this.rolDao = rolDao;
	}

}
