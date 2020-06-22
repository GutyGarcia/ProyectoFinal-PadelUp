package proyectoPadelUp.controladores;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import proyectoPadelUp.daos.EquiposDao;
import proyectoPadelUp.daos.JugadoresDao;
import proyectoPadelUp.pojos.Equipo;
import proyectoPadelUp.pojos.Jugador;

public class ControladorRivales implements Initializable {

	@FXML
	private Text encabezado;
	@FXML
	private TextField nombre, club, categoria, direccion, telefono;
	@FXML
	private Button botonAceptar, botonCancelar;
	@FXML
	private TableView<Jugador> jugadores;
	@FXML
	private TableView<Equipo> rivales;

	private EquiposDao equiposDao = new EquiposDao();
	private JugadoresDao jugadoresDao = new JugadoresDao();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		encabezado.setText(ControladorEquipos.equipoActivo.getNombre());
		consultarRivales();

	}

	@FXML
	protected void consultar() {
		jugadores.getItems().clear();
		Equipo equipoRival = rivales.getSelectionModel().getSelectedItem();
		if (equipoRival == null) {
			Alert error = new Alert(AlertType.ERROR);
			error.setContentText("Debe de elegir un equipo para realizar la consulta");
			DialogPane dialogo = error.getDialogPane();
			dialogo.getStylesheets().add(getClass().getResource("/estilos/Estilos.css").toExternalForm());
			dialogo.getStyleClass().add("myDialog");
			Image imagenError = new Image("@../../imagenes/logoAzul.png");
			ImageView imageViewError = new ImageView(imagenError);
			error.setGraphic(imageViewError);
			Stage stage = new Stage();
			stage = (Stage) error.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
			error.showAndWait();
		} else {
			ObservableList<Jugador> listaJugadores = FXCollections.observableArrayList();
			List<Jugador> jugadoresEncontrados = jugadoresDao.consultarTodosPorEquipo(equipoRival.getIdEquipo());
			nombre.setText(equipoRival.getNombre());
			club.setText(equipoRival.getClub());
			categoria.setText(equipoRival.getCategoria());
			direccion.setText(equipoRival.getDireccion());
			telefono.setText(equipoRival.getTelefono());
			if (jugadoresEncontrados.size() != 0) {
				listaJugadores.addAll(jugadoresEncontrados);
				jugadores.setItems(listaJugadores);
			} else {
				Alert errorBajaEquipo = new Alert(AlertType.WARNING);
				errorBajaEquipo
						.setContentText("El equipo " + equipoRival.getNombre() + " no tiene ningún jugador registrado");
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

	}

	public void consultarRivales() {
		ObservableList<Equipo> listaEquipos = FXCollections.observableArrayList();
		List<Equipo> equiposEncontrados = equiposDao.consultarRivales(ControladorEquipos.equipoActivo.getIdEquipo(),
				ControladorEquipos.equipoActivo.getDivision(), ControladorEquipos.equipoActivo.getGrupo());
		listaEquipos.addAll(equiposEncontrados);
		rivales.setItems(listaEquipos);
	}

	@FXML
	public void salir(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

}
