package proyectoPadelUp.controladores;

import java.io.IOException;
import java.util.List;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proyectoPadelUp.AplicacionPadelUp;
import proyectoPadelUp.daos.JornadasDao;
import proyectoPadelUp.daos.JugadoresDao;
import proyectoPadelUp.daos.TemporadasEquiposDao;
import proyectoPadelUp.pojos.Jugador;

public class ControladorMenu extends ControladorConNavegabilidad {

	private JornadasDao jornadasDao = new JornadasDao();
	private TemporadasEquiposDao temporadasEquiposDao = new TemporadasEquiposDao();
	private JugadoresDao jugadoresDao = new JugadoresDao();
	@FXML
	private WebView webView;

	public void mostrarPantallaPrincipal() {
		this.layout.mostrarComoPantallaActual("Principal");
		Stage stage = (Stage) layout.getScene().getWindow();
		stage.sizeToScene();
		this.layout.getStage().setResizable(false);
		this.layout.getStage().setMaximized(false);
	}

	public void salir() {
		Platform.exit();
	}

	public void mostrarPantallaClasificacion() throws IOException {
		if (ControladorEquipos.equipoActivo == null) {
			Alert errorConvocatoria = new Alert(AlertType.WARNING);
			errorConvocatoria.setContentText("Debe de escojer un equipo para poder ver la clasificación");
			DialogPane dialogo = errorConvocatoria.getDialogPane();
			dialogo.getStylesheets().add(getClass().getResource("/estilos/Estilos.css").toExternalForm());
			dialogo.getStyleClass().add("myDialog");
			Image imagenError = new Image("@../../imagenes/logoAzul.png");
			ImageView imageViewError = new ImageView(imagenError);
			errorConvocatoria.setGraphic(imageViewError);
			Stage stage2 = new Stage();
			stage2 = (Stage) errorConvocatoria.getDialogPane().getScene().getWindow();
			stage2.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
			errorConvocatoria.showAndWait();
		} else {
			FXMLLoader fxmlLoader = new FXMLLoader(
					getClass().getResource("/proyectoPadelUp/webClasificacion.fxml"));
			Parent contenedor = fxmlLoader.load();
			Stage stage = new Stage();

			Scene escena = new Scene(contenedor, 800, 700);
			stage.setScene(escena);
			stage.setResizable(true);
			stage.setMaximized(true);
			stage.initModality(Modality.WINDOW_MODAL);
			escena.getStylesheets().add("@../../estilos/estilos.css");
			stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
			stage.setTitle("Clasificación");

			stage.showAndWait();

		}

	}

	public void mostrarPantallaRivales() throws IOException {
		if (ControladorEquipos.equipoActivo == null) {
			Alert errorConvocatoria = new Alert(AlertType.WARNING);
			errorConvocatoria.setContentText("Debe de escojer un equipo para consultar sus rivales");
			DialogPane dialogo = errorConvocatoria.getDialogPane();
			dialogo.getStylesheets().add(getClass().getResource("/estilos/Estilos.css").toExternalForm());
			dialogo.getStyleClass().add("myDialog");
			Image imagenError = new Image("@../../imagenes/logoAzul.png");
			ImageView imageViewError = new ImageView(imagenError);
			errorConvocatoria.setGraphic(imageViewError);
			Stage stage2 = new Stage();
			stage2 = (Stage) errorConvocatoria.getDialogPane().getScene().getWindow();
			stage2.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
			errorConvocatoria.showAndWait();
		} else {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/proyectoPadelUp/rivales.fxml"));
			Parent contenedor = fxmlLoader.load();
			Stage stage = new Stage();
			Scene escena = new Scene(contenedor, 800, 650);
			stage.setScene(escena);
			stage.setResizable(false);
			stage.setMaximized(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			escena.getStylesheets().add("@../../estilos/estilos.css");
			stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
			stage.setTitle("Consultar rivales");

			stage.showAndWait();
		}
	}

	public void mostrarPantallaJornadaEnJuego() throws IOException {
		if (ControladorEquipos.equipoActivo == null) {
			Alert errorConvocatoria = new Alert(AlertType.WARNING);
			errorConvocatoria.setContentText("Debe de escojer un equipo para consultar la jornada en juego");
			DialogPane dialogo = errorConvocatoria.getDialogPane();
			dialogo.getStylesheets().add(getClass().getResource("/estilos/Estilos.css").toExternalForm());
			dialogo.getStyleClass().add("myDialog");
			Image imagenError = new Image("@../../imagenes/logoAzul.png");
			ImageView imageViewError = new ImageView(imagenError);
			errorConvocatoria.setGraphic(imageViewError);
			Stage stage2 = new Stage();
			stage2 = (Stage) errorConvocatoria.getDialogPane().getScene().getWindow();
			stage2.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
			errorConvocatoria.showAndWait();
		} else {
			FXMLLoader fxmlLoader = new FXMLLoader(
					getClass().getResource("/proyectoPadelUp/jornadaEnJuego.fxml"));
			Parent contenedor = fxmlLoader.load();
			Stage stage = new Stage();
			ControladorJornadaEnJuego controladorJornadaEnJuego = fxmlLoader.<ControladorJornadaEnJuego>getController();
			controladorJornadaEnJuego.setStage(stage);
			controladorJornadaEnJuego.setEquipoActivo(ControladorEquipos.equipoActivo);
			boolean jornadaEnJuego = jornadasDao.equipoConJornadaEnJuego(ControladorEquipos.equipoActivo.getIdEquipo());
			if (jornadaEnJuego) {
				Scene escena = new Scene(contenedor, 894, 681);
				stage.setScene(escena);
				stage.setResizable(false);
				stage.setMaximized(false);
				stage.initModality(Modality.APPLICATION_MODAL);
				escena.getStylesheets().add("@../../estilos/estilos.css");
				stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
				stage.setTitle("Jornada en juego");

				stage.showAndWait();
			} else {
				Alert errorConvocatoria = new Alert(AlertType.WARNING);
				errorConvocatoria.setContentText("El Equipo " + ControladorEquipos.equipoActivo.getNombre()
						+ " no tiene ninguna jornada en juego");
				DialogPane dialogo = errorConvocatoria.getDialogPane();
				dialogo.getStylesheets().add(getClass().getResource("/estilos/Estilos.css").toExternalForm());
				dialogo.getStyleClass().add("myDialog");
				Image imagenError = new Image("@../../imagenes/logoAzul.png");
				ImageView imageViewError = new ImageView(imagenError);
				errorConvocatoria.setGraphic(imageViewError);
				Stage stage2 = new Stage();
				stage2 = (Stage) errorConvocatoria.getDialogPane().getScene().getWindow();
				stage2.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
				errorConvocatoria.showAndWait();
			}
		}

	}

	public void mostrarPantallaAltaJornadas() throws IOException {
		if (ControladorEquipos.equipoActivo == null) {
			Alert errorConvocatoria = new Alert(AlertType.WARNING);
			errorConvocatoria.setContentText("Debe de escojer un equipo para dar de alta una jornada");
			DialogPane dialogo = errorConvocatoria.getDialogPane();
			dialogo.getStylesheets().add(getClass().getResource("/estilos/Estilos.css").toExternalForm());
			dialogo.getStyleClass().add("myDialog");
			Image imagenError = new Image("@../../imagenes/logoAzul.png");
			ImageView imageViewError = new ImageView(imagenError);
			errorConvocatoria.setGraphic(imageViewError);
			Stage stage2 = new Stage();
			stage2 = (Stage) errorConvocatoria.getDialogPane().getScene().getWindow();
			stage2.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
			errorConvocatoria.showAndWait();
		} else {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/proyectoPadelUp/jornadas.fxml"));
			Parent contenedor = fxmlLoader.load();
			Stage stage = new Stage();
			ControladorJornadas controladorJornadas = fxmlLoader.<ControladorJornadas>getController();
			controladorJornadas.setEquipoActivo(ControladorEquipos.equipoActivo);
			boolean temporadaActiva = temporadasEquiposDao
					.equipoEnTemporadaActiva(ControladorEquipos.equipoActivo.getIdEquipo());
			boolean jornadaEnJuego = jornadasDao.equipoConJornadaEnJuego(ControladorEquipos.equipoActivo.getIdEquipo());
			if (temporadaActiva) {
				if (jornadaEnJuego == false) {
					List<Jugador> jugadoresEncontrados = jugadoresDao
							.consultarTodosPorEquipo(ControladorEquipos.equipoActivo.getIdEquipo());
					if (jugadoresEncontrados.size() >= 10) {
						Scene escena = new Scene(contenedor, 894, 681);
						stage.setScene(escena);
						stage.setResizable(false);
						stage.setMaximized(false);
						stage.initModality(Modality.APPLICATION_MODAL);
						escena.getStylesheets().add("@../../estilos/estilos.css");
						stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
						stage.setTitle("Alta Jornada");
						stage.showAndWait();
					} else {
						Alert errorConvocatoria = new Alert(AlertType.WARNING);
						errorConvocatoria.setContentText("El Equipo " + ControladorEquipos.equipoActivo.getNombre()
								+ " no tiene el mínimo de 10 jugadores para la jornada");
						DialogPane dialogo = errorConvocatoria.getDialogPane();
						dialogo.getStylesheets().add(getClass().getResource("/estilos/Estilos.css").toExternalForm());
						dialogo.getStyleClass().add("myDialog");
						Image imagenError = new Image("@../../imagenes/logoAzul.png");
						ImageView imageViewError = new ImageView(imagenError);
						errorConvocatoria.setGraphic(imageViewError);
						Stage stage2 = new Stage();
						stage2 = (Stage) errorConvocatoria.getDialogPane().getScene().getWindow();
						stage2.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
						errorConvocatoria.showAndWait();
					}
				} else {
					Alert errorConvocatoria = new Alert(AlertType.WARNING);
					errorConvocatoria.setContentText("El Equipo " + ControladorEquipos.equipoActivo.getNombre()
							+ " ya tiene una jornada en juego. Debe de cerrar la actual jornada para registrar otra");
					DialogPane dialogo = errorConvocatoria.getDialogPane();
					dialogo.getStylesheets().add(getClass().getResource("/estilos/Estilos.css").toExternalForm());
					dialogo.getStyleClass().add("myDialog");
					Image imagenError = new Image("@../../imagenes/logoAzul.png");
					ImageView imageViewError = new ImageView(imagenError);
					errorConvocatoria.setGraphic(imageViewError);
					Stage stage2 = new Stage();
					stage2 = (Stage) errorConvocatoria.getDialogPane().getScene().getWindow();
					stage2.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
					errorConvocatoria.showAndWait();
				}
			} else {
				Alert errorConvocatoria = new Alert(AlertType.WARNING);
				errorConvocatoria.setContentText("El Equipo " + ControladorEquipos.equipoActivo.getNombre()
						+ " no tiene ninguna temporada activa");
				DialogPane dialogo = errorConvocatoria.getDialogPane();
				dialogo.getStylesheets().add(getClass().getResource("/estilos/Estilos.css").toExternalForm());
				dialogo.getStyleClass().add("myDialog");
				Image imagenError = new Image("@../../imagenes/logoAzul.png");
				ImageView imageViewError = new ImageView(imagenError);
				errorConvocatoria.setGraphic(imageViewError);
				Stage stage2 = new Stage();
				stage2 = (Stage) errorConvocatoria.getDialogPane().getScene().getWindow();
				stage2.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
				errorConvocatoria.showAndWait();
			}
		}
	}

	public void mostrarPantallaFederacion() {
		this.layout.mostrarComoPantallaActual("Federacion");
		this.layout.getStage().setResizable(true);
		this.layout.getStage().setMaximized(true);
	}

	public void mostrarPantallaDocumentos() {
		this.layout.mostrarComoPantallaActual("Documentos");
		this.layout.getStage().setResizable(true);
		this.layout.getStage().setMaximized(true);
	}

	public void mostrarPerfil() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/proyectoPadelUp/editarUsuario.fxml"));
		Parent contenedor = fxmlLoader.load();
		Stage stage = new Stage();
		ControladorDatosPerfil controladorDatosPerfil = fxmlLoader.<ControladorDatosPerfil>getController();
		controladorDatosPerfil.setIndex(AplicacionPadelUp.getUserId());
		Scene escena = new Scene(contenedor, 392, 448);
		stage.setScene(escena);
		stage.setResizable(false);
		stage.setMaximized(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		escena.getStylesheets().add("@../../estilos/estilos.css");
		stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
		stage.setTitle("Modificar datos del usuario");

		controladorDatosPerfil.getTextFieldNombre().textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\sa-zA-Z*")) {
				controladorDatosPerfil.getTextFieldNombre().setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
			}
		});

		controladorDatosPerfil.getTextFieldApellido1().textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\sa-zA-Z*")) {
				controladorDatosPerfil.getTextFieldApellido1().setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
			}
		});

		BooleanBinding isTextFieldEmpty = Bindings.isEmpty(controladorDatosPerfil.getTextFieldNombre().textProperty())
				.or(Bindings.isEmpty(controladorDatosPerfil.getTextFieldApellido1().textProperty()))
				.or(Bindings.isEmpty(controladorDatosPerfil.getTextFieldEmail().textProperty()))
				.or(Bindings.isEmpty(controladorDatosPerfil.getContraseña().textProperty()))
				.or(Bindings.isEmpty(controladorDatosPerfil.getRepetirContraseña().textProperty()))
				.or(Bindings.isEmpty(controladorDatosPerfil.getTextFieldEmail().textProperty()));
		controladorDatosPerfil.getBotonAceptar().disableProperty().bind(isTextFieldEmpty);
		stage.showAndWait();

	}

	public void crearTemporada() throws IOException {
		if (ControladorEquipos.equipoActivo == null) {
			Alert errorConvocatoria = new Alert(AlertType.WARNING);
			errorConvocatoria.setContentText("Debe de escojer un equipo para dar de alta una temporada");
			DialogPane dialogo = errorConvocatoria.getDialogPane();
			dialogo.getStylesheets().add(getClass().getResource("/estilos/Estilos.css").toExternalForm());
			dialogo.getStyleClass().add("myDialog");
			Image imagenError = new Image("@../../imagenes/logoAzul.png");
			ImageView imageViewError = new ImageView(imagenError);
			errorConvocatoria.setGraphic(imageViewError);
			Stage stage2 = new Stage();
			stage2 = (Stage) errorConvocatoria.getDialogPane().getScene().getWindow();
			stage2.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
			errorConvocatoria.showAndWait();
		} else {
			FXMLLoader fxmlLoader = new FXMLLoader(
					getClass().getResource("/proyectoPadelUp/altaTemporada.fxml"));
			Parent contenedor = fxmlLoader.load();
			Stage stage = new Stage();
			ControladorAltaTemporada controladorAltaTemporada = fxmlLoader.<ControladorAltaTemporada>getController();
			controladorAltaTemporada.setIndex(AplicacionPadelUp.getUserId());
			Scene escena = new Scene(contenedor, 436, 328);
			stage.setScene(escena);
			stage.setResizable(false);
			stage.setMaximized(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			escena.getStylesheets().add("@../../estilos/estilos.css");
			stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
			stage.setTitle("Alta temporada");

			controladorAltaTemporada.getJornadas().textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*")) {
						controladorAltaTemporada.getJornadas().setText(newValue.replaceAll("[^\\d]", ""));
					}
				}
			});

			stage.showAndWait();
		}

	}

	public void logOut() {
		this.layout.mostrarComoPantallaActual("Login");
		Stage stage = (Stage) layout.getScene().getWindow();
		stage.sizeToScene();
		this.layout.getStage().setResizable(false);
		this.layout.getStage().setMaximized(false);

	}
}
