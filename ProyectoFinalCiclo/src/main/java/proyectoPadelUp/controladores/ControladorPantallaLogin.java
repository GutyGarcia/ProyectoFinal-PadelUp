package proyectoPadelUp.controladores;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import proyectoPadelUp.AplicacionPadelUp;
import proyectoPadelUp.daos.UsuariosDao;
import proyectoPadelUp.pojos.Usuario;

public class ControladorPantallaLogin extends ControladorConNavegabilidad {

	@FXML
	private TextField nombreUsuario;
	@FXML
	private PasswordField password;
	@FXML
	private Text error;

	private UsuariosDao usuarioDao;

	public ControladorPantallaLogin() {
		usuarioDao = new UsuariosDao();
	}

	public void mostrarPantallaAltaUsuario() {
		this.layout.mostrarComoPantallaActual("Alta usuario");
		Stage stage = (Stage) layout.getScene().getWindow();
		stage.sizeToScene();

	}

	public void login() {

		boolean loginOk = usuarioDao.existeUsuario(nombreUsuario.getText(), password.getText());
		boolean existeUsuario = usuarioDao.existeUsuarioPorNombre(nombreUsuario.getText());
		if (existeUsuario) {
			if (loginOk) {
				Usuario usuario = usuarioDao.buscarUsuarioPorNombre(nombreUsuario.getText());
				AplicacionPadelUp.setUserId(usuario.getIdUsuario());
				Stage stage = (Stage) layout.getScene().getWindow();
				stage.close();
				stage = new Stage();
				stage.hide();
				stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
				stage.setTitle("PadelUp");
				this.layout.cargarBarraDeMenuEnLaPartePosterior();
				this.layout.mostrarComoPantallaActual("Principal", stage);
				stage.setResizable(false);
				stage.setScene(layout.getScene());
				stage.show();
			} else {
				error.setText("Contraseña incorrecta.Vuelve a intentarlo");
			}
		} else {
			error.setText("Usuario no registrado");
		}
		nombreUsuario.clear();
		password.clear();
	}

	public void cancelar() {
		Platform.exit();
	}
}
