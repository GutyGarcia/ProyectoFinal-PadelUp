package proyectoPadelUp.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import proyectoPadelUp.AplicacionPadelUp;
import proyectoPadelUp.daos.EquiposDao;
import proyectoPadelUp.daos.JugadoresDao;
import proyectoPadelUp.daos.RolesDao;
import proyectoPadelUp.pojos.Equipo;
import proyectoPadelUp.pojos.Jugador;
import proyectoPadelUp.pojos.Rol;
import proyectoPadelUp.pojos.Usuario;

public class ControladorEquipos extends ControladorConNavegabilidad implements Initializable {
	@FXML
	private TableView<Jugador> tablaJugadores;
	@FXML
	private ComboBox<Equipo> comboEquipos;
	@FXML
	private Button altaJugador, bajaJugador, bajaEquipo, consultarJugadores, editarJugador, editarEquipo,
			consultarEquipos;

	private EquiposDao equiposDao = new EquiposDao();
	private JugadoresDao jugadoresDao;

	private RolesDao rolesDao = new RolesDao();

	public static Equipo equipoActivo;
	public static Rol rolUsuario;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comboEquipos.valueProperty().addListener(new ChangeListener<Equipo>() {
			@Override
			public void changed(ObservableValue<? extends Equipo> observable, Equipo oldValue, Equipo newValue) {
				if (newValue != null) {
					equipoActivo = newValue;
				}
			}
		});
		equiposDao = new EquiposDao();
		jugadoresDao = new JugadoresDao();
		altaJugador.disableProperty().bind(comboEquipos.valueProperty().isNull());
		bajaEquipo.disableProperty().bind(comboEquipos.valueProperty().isNull());
		editarEquipo.disableProperty().bind(comboEquipos.valueProperty().isNull());
		consultarJugadores.disableProperty().bind(comboEquipos.valueProperty().isNull());
		editarJugador.disableProperty().bind(Bindings.isEmpty(tablaJugadores.getSelectionModel().getSelectedItems()));
		bajaJugador.disableProperty().bind(Bindings.isEmpty(tablaJugadores.getSelectionModel().getSelectedItems()));
		comboEquipos.getItems().clear();
		configurarTamanioColumnas();

	}

	protected void configurarTamanioColumnas() {
		tablaJugadores.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		ObservableList<TableColumn<Jugador, ?>> columnas = tablaJugadores.getColumns();
		columnas.get(0).setMaxWidth(1f * Integer.MAX_VALUE * 25);
		columnas.get(1).setMaxWidth(1f * Integer.MAX_VALUE * 25);
		columnas.get(2).setMaxWidth(1f * Integer.MAX_VALUE * 20);
		columnas.get(3).setMaxWidth(1f * Integer.MAX_VALUE * 15);
		columnas.get(4).setMaxWidth(1f * Integer.MAX_VALUE * 15);
	}

	@FXML
	private void pantallaAltaJugador(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/proyectoPadelUp/altaJugador.fxml"));
		Parent contenedor = fxmlLoader.load();
		Stage stage = new Stage();
		int idEquipo = equipoActivo.getIdEquipo();
		stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
		stage.setTitle("Registrar jugador");
		ControladorJugadores controladorJugadores = fxmlLoader.<ControladorJugadores>getController();

		controladorJugadores.getTextFieldNombre().textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\sa-zA-Z*")) {
				controladorJugadores.getTextFieldNombre().setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
			}
		});

		controladorJugadores.getTextFieldApellido1().textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\sa-zA-Z*")) {
				controladorJugadores.getTextFieldApellido1().setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
			}
		});

		controladorJugadores.getTextFieldApellido2().textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\sa-zA-Z*")) {
				controladorJugadores.getTextFieldApellido2().setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
			}
		});

		controladorJugadores.getTextFieldTelefono().textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					controladorJugadores.getTextFieldTelefono().setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		controladorJugadores.getTextFieldPuntos().textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					controladorJugadores.getTextFieldPuntos().setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		controladorJugadores.getTextFieldLicencia().textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					controladorJugadores.getTextFieldLicencia().setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		controladorJugadores.setIdEquipo(idEquipo);
		Scene escena = new Scene(contenedor, 517, 594);
		escena.getStylesheets().addAll(getClass().getResource("/estilos/Estilos.css").toExternalForm());
		stage.setResizable(false);
		stage.setScene(escena);
		stage.initModality(Modality.APPLICATION_MODAL);
		BooleanBinding isTextFieldEmpty = Bindings.isEmpty(controladorJugadores.getTextFieldNombre().textProperty())
				.or(Bindings.isEmpty(controladorJugadores.getTextFieldApellido1().textProperty()))
				.or(Bindings.isEmpty(controladorJugadores.getTextFieldPuntos().textProperty()))
				.or(Bindings.isEmpty(controladorJugadores.getTextFieldLicencia().textProperty()))
				.or(controladorJugadores.getComboBoxPosicion().valueProperty().isNull())
				.or(controladorJugadores.getFechaNacimiento().valueProperty().isNull())
				.or(Bindings.isEmpty(controladorJugadores.getTextFieldEmail().textProperty()));
		controladorJugadores.getBotonAceptar().disableProperty().bind(isTextFieldEmpty);
		stage.showAndWait();

		consultarJugadores();
	}

	@FXML
	private void pantallaAltaEquipo(ActionEvent event) throws IOException {
		rolUsuario = rolesDao.consultarRolPorIdUsuario(AplicacionPadelUp.getUserId());
		if (rolUsuario.getIdRol() == 1) {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/proyectoPadelUp/altaEquipo.fxml"));
			Parent contenedor = fxmlLoader.load();
			Stage stage = new Stage();

			ControladorAltaEquipo controladorAltaEquipo = fxmlLoader.<ControladorAltaEquipo>getController();
			controladorAltaEquipo.setIndex(AplicacionPadelUp.getUserId());

			controladorAltaEquipo.getTelefono().textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*")) {
						controladorAltaEquipo.getTelefono().setText(newValue.replaceAll("[^\\d]", ""));
					}
				}
			});

			stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
			stage.setTitle("Registrar equipo nuevo");
			Scene escena = new Scene(contenedor, 390, 433);
			escena.getStylesheets().addAll(getClass().getResource("/estilos/Estilos.css").toExternalForm());
			stage.setResizable(false);
			stage.setScene(escena);
			stage.initModality(Modality.APPLICATION_MODAL);

			stage.showAndWait();
		} else {
			FXMLLoader fxmlLoader = new FXMLLoader(
					getClass().getResource("/proyectoPadelUp/registrarEquipo.fxml"));
			Parent contenedor = fxmlLoader.load();
			Stage stage = new Stage();

			ControladorRegistrarEquipo controladorAltaEquipo = fxmlLoader.<ControladorRegistrarEquipo>getController();
			controladorAltaEquipo.setIndex(AplicacionPadelUp.getUserId());

			controladorAltaEquipo.getTelefono().textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*")) {
						controladorAltaEquipo.getTelefono().setText(newValue.replaceAll("[^\\d]", ""));
					}
				}
			});

			stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
			stage.setTitle("Registrar equipo nuevo");
			Scene escena = new Scene(contenedor, 340, 366);
			escena.getStylesheets().addAll(getClass().getResource("/estilos/Estilos.css").toExternalForm());
			stage.setResizable(false);
			stage.setScene(escena);
			stage.initModality(Modality.APPLICATION_MODAL);

			stage.showAndWait();
		}
		cargarEquipos();
		ObservableList<Jugador> jugadores = FXCollections.observableArrayList();
		tablaJugadores.setItems(jugadores);
	}

	@FXML
	private void editarEquipo(ActionEvent event) throws IOException {

		if (AplicacionPadelUp.getUserId() == 1) {
			FXMLLoader fxmlLoader = new FXMLLoader(
					getClass().getResource("/proyectoPadelUp/editarEquipoAdmin.fxml"));
			Parent contenedor = fxmlLoader.load();
			Stage stage = new Stage();
			ControladorEditarEquipoAdmin controladorEditarEquipoAdmin = fxmlLoader
					.<ControladorEditarEquipoAdmin>getController();

			controladorEditarEquipoAdmin.getTelefono().textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (newValue != null && !newValue.matches("\\d*")) {
						controladorEditarEquipoAdmin.getTelefono().setText(newValue.replaceAll("[^\\d]", ""));
					}
				}
			});

			controladorEditarEquipoAdmin.setIndex(comboEquipos.getValue().getIdEquipo());
			stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
			stage.setTitle("Modificar equipo");
			Scene escena = new Scene(contenedor, 390, 474);
			escena.getStylesheets().addAll(getClass().getResource("/estilos/Estilos.css").toExternalForm());
			stage.setResizable(false);
			stage.setScene(escena);
			stage.initModality(Modality.APPLICATION_MODAL);

			stage.showAndWait();
			cargarEquipos();
			ObservableList<Jugador> jugadores = FXCollections.observableArrayList();
			tablaJugadores.setItems(jugadores);

		} else {
			FXMLLoader fxmlLoader = new FXMLLoader(
					getClass().getResource("/proyectoPadelUp/editarEquipo.fxml"));
			Parent contenedor = fxmlLoader.load();
			Stage stage = new Stage();
			ControladorEditarEquipo controladorEditarEquipo = fxmlLoader.<ControladorEditarEquipo>getController();

			controladorEditarEquipo.getTelefono().textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*")) {
						controladorEditarEquipo.getTelefono().setText(newValue.replaceAll("[^\\d]", ""));
					}
				}
			});

			controladorEditarEquipo.setIndex(comboEquipos.getValue().getIdEquipo());
			stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
			stage.setTitle("Modificar equipo");
			Scene escena = new Scene(contenedor, 390, 430);
			escena.getStylesheets().addAll(getClass().getResource("/estilos/Estilos.css").toExternalForm());
			stage.setResizable(false);
			stage.setScene(escena);
			stage.initModality(Modality.APPLICATION_MODAL);

			stage.showAndWait();
			cargarEquipos();
			ObservableList<Jugador> jugadores = FXCollections.observableArrayList();
			tablaJugadores.setItems(jugadores);
		}
	}

	@FXML
	protected void consultarEquipos(ActionEvent event) {
		rolUsuario = rolesDao.consultarRolPorIdUsuario(AplicacionPadelUp.getUserId());

		ObservableList<Equipo> equipos = FXCollections.observableArrayList();
		List<Equipo> listaEquipos;
		if (rolUsuario.getIdRol() == 1) {
			listaEquipos = equiposDao.consultarTodos();
		} else {
			listaEquipos = equiposDao.consultarEquipoPorUsuario(AplicacionPadelUp.getUserId());
		}
		if (listaEquipos.size() != 0) {
			equipos.addAll(listaEquipos);
			comboEquipos.setItems(equipos);
			comboEquipos.setConverter(new StringConverter<Equipo>() {

				@Override
				public String toString(Equipo object) {
					return object.getNombre();
				}

				@Override
				public Equipo fromString(String string) {
					return comboEquipos.getItems().stream().filter(ap -> ap.getNombre().equals(string)).findFirst()
							.orElse(null);
				}
			});
		} else {
			Alert errorBajaEquipo = new Alert(AlertType.WARNING);
			errorBajaEquipo.setContentText("El usuario no tiene ningún equipo activo en estos momentos");
			DialogPane dialogo2 = errorBajaEquipo.getDialogPane();
			dialogo2.getStylesheets().add(getClass().getResource("/estilos/Estilos.css").toExternalForm());
			dialogo2.getStyleClass().add("myDialog");
			Image imagenError = new Image("@../../imagenes/logoAzul.png");
			ImageView imageViewError = new ImageView(imagenError);
			errorBajaEquipo.setGraphic(imageViewError);
			Stage stage2 = new Stage();
			stage2 = (Stage) errorBajaEquipo.getDialogPane().getScene().getWindow();
			stage2.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
			errorBajaEquipo.showAndWait();
		}
	}

	protected void cargarEquipos() {
		ObservableList<Equipo> equipos = FXCollections.observableArrayList();
		List<Equipo> listaEquipos;
		if (rolUsuario.getIdRol() == 1) {
			listaEquipos = equiposDao.consultarTodos();
		} else {
			listaEquipos = equiposDao.consultarEquipoPorUsuario(AplicacionPadelUp.getUserId());
		}
		equipos.addAll(listaEquipos);
		comboEquipos.setItems(equipos);
		comboEquipos.setConverter(new StringConverter<Equipo>() {

			@Override
			public String toString(Equipo object) {
				return object.getNombre();
			}

			@Override
			public Equipo fromString(String string) {
				return comboEquipos.getItems().stream().filter(ap -> ap.getNombre().equals(string)).findFirst()
						.orElse(null);
			}
		});
	}

	@FXML
	protected void consultarJugadores() {
		tablaJugadores.getItems().clear();
		ObservableList<Jugador> jugadores = FXCollections.observableArrayList();
		List<Jugador> jugadoresEncontrados = jugadoresDao
				.consultarTodosPorEquipo(comboEquipos.getSelectionModel().getSelectedItem().getIdEquipo());
		if (jugadoresEncontrados.size() != 0) {
			jugadores.addAll(jugadoresEncontrados);
			tablaJugadores.setItems(jugadores);
		} else {
			Alert errorBajaEquipo = new Alert(AlertType.WARNING);
			errorBajaEquipo.setContentText("El equipo " + comboEquipos.getSelectionModel().getSelectedItem().getNombre()
					+ " no tiene ningún jugador registrado");
			DialogPane dialogo2 = errorBajaEquipo.getDialogPane();
			dialogo2.getStylesheets().add(getClass().getResource("/estilos/Estilos.css").toExternalForm());
			dialogo2.getStyleClass().add("myDialog");
			Image imagenError = new Image("@../../imagenes/logoAzul.png");
			ImageView imageViewError = new ImageView(imagenError);
			errorBajaEquipo.setGraphic(imageViewError);
			Stage stage2 = new Stage();
			stage2 = (Stage) errorBajaEquipo.getDialogPane().getScene().getWindow();
			stage2.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
			errorBajaEquipo.showAndWait();
		}
	}

	@FXML
	private void editarJugador(ActionEvent even) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/proyectoPadelUp/editarJugador.fxml"));
		Parent contenedor = fxmlLoader.load();
		Stage stage = new Stage();

		int idJugador = tablaJugadores.getSelectionModel().getSelectedItem().getIdJugador();
		ControladorEditarJugador controladorEditarJugador = fxmlLoader.<ControladorEditarJugador>getController();
		controladorEditarJugador.setIndex(idJugador);

		stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
		stage.setTitle("Modificar datos del jugador");
		Scene escena = new Scene(contenedor, 517, 594);
		escena.getStylesheets().addAll(getClass().getResource("/estilos/Estilos.css").toExternalForm());
		stage.setResizable(false);
		stage.setScene(escena);
		stage.initModality(Modality.APPLICATION_MODAL);

		controladorEditarJugador.getTextFieldNombre().textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\sa-zA-Z*")) {
				controladorEditarJugador.getTextFieldNombre().setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
			}
		});

		controladorEditarJugador.getTextFieldApellido1().textProperty()
				.addListener((observable, oldValue, newValue) -> {
					if (!newValue.matches("\\sa-zA-Z*")) {
						controladorEditarJugador.getTextFieldApellido1()
								.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
					}
				});

		controladorEditarJugador.getTextFieldPuntos().textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					controladorEditarJugador.getTextFieldPuntos().setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		BooleanBinding isTextFieldEmpty = Bindings.isEmpty(controladorEditarJugador.getTextFieldNombre().textProperty())
				.or(Bindings.isEmpty(controladorEditarJugador.getTextFieldApellido1().textProperty()))
				.or(Bindings.isEmpty(controladorEditarJugador.getTextFieldPuntos().textProperty()))
				.or(Bindings.isEmpty(controladorEditarJugador.getTextFieldLicencia().textProperty()))
				.or(controladorEditarJugador.getComboBoxPosicion().valueProperty().isNull())
				.or(controladorEditarJugador.getFechaNacimiento().valueProperty().isNull())
				.or(Bindings.isEmpty(controladorEditarJugador.getTextFieldEmail().textProperty()));
		controladorEditarJugador.getBotonAceptar().disableProperty().bind(isTextFieldEmpty);
		stage.showAndWait();

		consultarJugadores();
	}

	public void bajaEquipo() {
		Equipo equipo = comboEquipos.getSelectionModel().getSelectedItem();
		Alert avisoBajaEquipo = new Alert(AlertType.CONFIRMATION);

		if (rolUsuario.getIdRol() == 1) {
			avisoBajaEquipo.setTitle("Confirmar la baja");
			avisoBajaEquipo.setHeaderText("Desea dar de baja al equipo ");
			avisoBajaEquipo
					.setContentText("Va usted a borrar al equipo " + equipo.getNombre() + ". ¿Está usted seguro?");

			DialogPane dialogo1 = avisoBajaEquipo.getDialogPane();
			dialogo1.getStylesheets().add(getClass().getResource("/estilos/Estilos.css").toExternalForm());
			dialogo1.getStyleClass().add("myDialog");

			Image image = new Image("@../../imagenes/logoAzul.png");
			ImageView imageView = new ImageView(image);
			avisoBajaEquipo.setGraphic(imageView);
			Stage stage = new Stage();
			stage = (Stage) avisoBajaEquipo.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));

			Optional<ButtonType> resultado = avisoBajaEquipo.showAndWait();
			if (resultado.get() == ButtonType.OK) {
				if (jugadoresDao.consultarTodosPorEquipo(equipo.getIdEquipo()).isEmpty()) {
					Usuario usuarioAdmin = new Usuario();
					if (rolUsuario.getIdRol() == 1) {
						equipo.setEstado(false);
						usuarioAdmin.setIdUsuario(1);
						equipo.setUsuario(usuarioAdmin);
						equiposDao.modificarEquipo(equipo);
						cargarEquipos();
					} else {
						usuarioAdmin.setIdUsuario(1);
						equipo.setUsuario(usuarioAdmin);
						equiposDao.modificarEquipo(equipo);
						cargarEquipos();
					}

				} else {
					Alert errorBajaEquipo = new Alert(AlertType.WARNING);
					errorBajaEquipo
							.setContentText("No es posible la baja del equipo mientras tenga jugadores en activo");
					DialogPane dialogo2 = errorBajaEquipo.getDialogPane();
					dialogo2.getStylesheets().add(getClass().getResource("/estilos/Estilos.css").toExternalForm());
					dialogo2.getStyleClass().add("myDialog");
					Image imagenError = new Image("@../../imagenes/logoAzul.png");
					ImageView imageViewError = new ImageView(imagenError);
					errorBajaEquipo.setGraphic(imageViewError);
					Stage stage2 = new Stage();
					stage2 = (Stage) errorBajaEquipo.getDialogPane().getScene().getWindow();
					stage2.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
					errorBajaEquipo.showAndWait();
				}
			}
		} else {
			Alert errorBajaEquipo = new Alert(AlertType.WARNING);
			errorBajaEquipo.setContentText(
					"El usuario no tiene permisos para la acción, pongase en contacto con el administrador");
			DialogPane dialogo2 = errorBajaEquipo.getDialogPane();
			dialogo2.getStylesheets().add(getClass().getResource("/estilos/Estilos.css").toExternalForm());
			dialogo2.getStyleClass().add("myDialog");
			Image imagenError = new Image("@../../imagenes/logoAzul.png");
			ImageView imageViewError = new ImageView(imagenError);
			errorBajaEquipo.setGraphic(imageViewError);
			Stage stage2 = new Stage();
			stage2 = (Stage) errorBajaEquipo.getDialogPane().getScene().getWindow();
			stage2.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
			errorBajaEquipo.showAndWait();
		}
	}

	@FXML
	private void bajaJugador(ActionEvent event) {
		Jugador jugador = tablaJugadores.getSelectionModel().getSelectedItem();
		Alert avisoBajaJugador = new Alert(AlertType.CONFIRMATION);

		avisoBajaJugador.setTitle("Confirmar la baja");
		avisoBajaJugador.setHeaderText("Desea dar de baja al jugador ");
		avisoBajaJugador.setContentText("Va usted a borrar al jugador " + jugador.getNombre() + " "
				+ jugador.getPrimerApellido() + ". ¿Esta usted seguro?");
		DialogPane dialogo3 = avisoBajaJugador.getDialogPane();
		dialogo3.getStylesheets().add(getClass().getResource("/estilos/Estilos.css").toExternalForm());
		dialogo3.getStyleClass().add("myDialog");
		Image image = new Image("@../../imagenes/logoAzul.PNG");
		ImageView imageView = new ImageView(image);
		avisoBajaJugador.setGraphic(imageView);
		Stage stage = new Stage();
		stage = (Stage) avisoBajaJugador.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));

		Optional<ButtonType> resultado = avisoBajaJugador.showAndWait();
		if (resultado.get() == ButtonType.OK) {
			jugador.setEstado(false);
			jugadoresDao.modificarJugador(jugador);
			consultarJugadores();
		}
	}

	public ComboBox<Equipo> getComboEquipos() {
		return comboEquipos;
	}

	public void setComboEquipos(ComboBox<Equipo> comboEquipos) {
		this.comboEquipos = comboEquipos;
	}

	public static Equipo getEquipoActivo() {
		return equipoActivo;
	}

	public static void setEquipoActivo(Equipo equipoActivo) {
		ControladorEquipos.equipoActivo = equipoActivo;
	}

}