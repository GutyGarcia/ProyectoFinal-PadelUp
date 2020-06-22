package proyectoPadelUp.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import proyectoPadelUp.daos.JornadasDao;
import proyectoPadelUp.daos.JugadoresDao;
import proyectoPadelUp.daos.PartidosDao;
import proyectoPadelUp.daos.TemporadasEquiposDao;
import proyectoPadelUp.pojos.Jornada;
import proyectoPadelUp.pojos.Jugador;
import proyectoPadelUp.pojos.Partido;
import proyectoPadelUp.pojos.Temporada;

public class ControladorAlineacionManual implements Initializable {

	@FXML
	private Text jugador1A, jugador1B, jugador2A, jugador2B, jugador3A, jugador3B, jugador4A, jugador4B, jugador5A,
			jugador5B, nombrePareja, puntos1, puntos2, puntos3, puntos4, puntos5;

	@FXML
	private Button elegir1, elegir2, elegir3, elegir4, elegir5, aceptarPareja, cancelarPareja, validar, salir;

	@FXML
	private ComboBox<Jugador> comboJugadorA, comboJugadorB;

	private Jornada jornadaManual = new Jornada();
	private Jornada jornada = new Jornada();
	private Jugador jugadorAPareja1 = new Jugador();
	private Jugador jugadorBPareja1 = new Jugador();
	private Jugador jugadorAPareja2 = new Jugador();
	private Jugador jugadorBPareja2 = new Jugador();
	private Jugador jugadorAPareja3 = new Jugador();
	private Jugador jugadorBPareja3 = new Jugador();
	private Jugador jugadorAPareja4 = new Jugador();
	private Jugador jugadorBPareja4 = new Jugador();
	private Jugador jugadorAPareja5 = new Jugador();
	private Jugador jugadorBPareja5 = new Jugador();
	private Partido partido1 = new Partido();
	private Partido partido2 = new Partido();
	private Partido partido3 = new Partido();
	private Partido partido4 = new Partido();
	private Partido partido5 = new Partido();
	private int idEquipo;
	private boolean elejir1Activo = false;
	private boolean elejir2Activo = false;
	private boolean elejir3Activo = false;
	private boolean elejir4Activo = false;
	private boolean elejir5Activo = false;
	private boolean pareja1Activa = false;
	private boolean pareja2Activa = false;
	private boolean pareja3Activa = false;
	private boolean pareja4Activa = false;
	private boolean pareja5Activa = false;
	
	private Temporada temporada = new Temporada();
	private JornadasDao jornadasDao = new JornadasDao();
	private JugadoresDao jugadoresDao = new JugadoresDao();
	private PartidosDao partidosDao = new PartidosDao();
	private TemporadasEquiposDao temporadasEquiposDao = new TemporadasEquiposDao();
	private List<Partido> listaPartidosManual = new ArrayList<>();
	private List<Jugador> listaJugadoresmanual = new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		temporada = temporadasEquiposDao.consultarTemporadaPorEquipo(ControladorEquipos.equipoActivo.getIdEquipo());
		jornada = (Jornada) jornadasDao.consultarJornadaEnJuegoPorTemporada(temporada.getIdTemporada());
		listaPartidosManual = partidosDao.consultarPartidosPorJornada(jornada.getIdJornada());
	}

	private void cargarJugadoresComboA() {
		ObservableList<Jugador> jugadores = FXCollections.observableArrayList();
		List<Jugador> listaJugadores = jugadoresDao.consultarTodosPorEquipo(idEquipo);
		jugadores.addAll(listaJugadores);
		comboJugadorA.setItems(jugadores);
		comboJugadorA.setConverter(new StringConverter<Jugador>() {

			@Override
			public String toString(Jugador object) {
				return object.getNombre() + " " + object.getPrimerApellido() + " " + "- puntos: " + object.getPuntos();
			}

			@Override
			public Jugador fromString(String string) {
				return comboJugadorA.getItems().stream().filter(ap -> ap.getNombre().equals(string)).findFirst()
						.orElse(null);
			}
		});
	}

	private void cargarJugadoresComboB() {
		ObservableList<Jugador> jugadores = FXCollections.observableArrayList();
		List<Jugador> listaJugadores = jugadoresDao.consultarTodosPorEquipo(idEquipo);
		jugadores.addAll(listaJugadores);
		comboJugadorB.setItems(jugadores);
		comboJugadorB.setConverter(new StringConverter<Jugador>() {

			@Override
			public String toString(Jugador object) {
				return object.getNombre() + " " + object.getPrimerApellido() + " " + "- puntos: " + object.getPuntos();
			}

			@Override
			public Jugador fromString(String string) {
				return comboJugadorB.getItems().stream().filter(ap -> ap.getNombre().equals(string)).findFirst()
						.orElse(null);
			}
		});
	}

	@FXML
	public void elegir1(ActionEvent event) throws IOException {
		cargarJugadoresComboA();
		cargarJugadoresComboB();
		elejir1Activo = true;
		nombrePareja.setText("Pareja 1");
		aceptarPareja.setDisable(false);
		cancelarPareja.setDisable(false);
	}

	@FXML
	public void elegir2(ActionEvent event) throws IOException {
		cargarJugadoresComboA();
		cargarJugadoresComboB();
		elejir2Activo = true;
		nombrePareja.setText("Pareja 2");
		aceptarPareja.setDisable(false);
		cancelarPareja.setDisable(false);
	}

	@FXML
	public void elegir3(ActionEvent event) throws IOException {
		cargarJugadoresComboA();
		cargarJugadoresComboB();
		elejir3Activo = true;
		nombrePareja.setText("Pareja 3");
		aceptarPareja.setDisable(false);
		cancelarPareja.setDisable(false);
	}

	@FXML
	public void elegir4(ActionEvent event) throws IOException {
		cargarJugadoresComboA();
		cargarJugadoresComboB();
		elejir4Activo = true;
		nombrePareja.setText("Pareja 4");
		aceptarPareja.setDisable(false);
		cancelarPareja.setDisable(false);
	}

	@FXML
	public void elegir5(ActionEvent event) throws IOException {
		cargarJugadoresComboA();
		cargarJugadoresComboB();
		elejir5Activo = true;
		nombrePareja.setText("Pareja 5");
		aceptarPareja.setDisable(false);
		cancelarPareja.setDisable(false);
	}

	@FXML
	public void salir(ActionEvent event) throws IOException {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void validarAlineacion(ActionEvent event) throws IOException {
		boolean comprobarDuplicados = false;
		boolean comprobarPuntos = true;

		listaJugadoresmanual.add(jugadorAPareja1);
		listaJugadoresmanual.add(jugadorBPareja1);
		listaJugadoresmanual.add(jugadorAPareja2);
		listaJugadoresmanual.add(jugadorBPareja2);
		listaJugadoresmanual.add(jugadorAPareja3);
		listaJugadoresmanual.add(jugadorBPareja3);
		listaJugadoresmanual.add(jugadorAPareja4);
		listaJugadoresmanual.add(jugadorBPareja4);
		listaJugadoresmanual.add(jugadorAPareja5);
		listaJugadoresmanual.add(jugadorBPareja5);
		
		int totalPuntosPareja1 = jugadorAPareja1.getPuntos() + jugadorBPareja1.getPuntos();
		int totalPuntosPareja2 = jugadorAPareja2.getPuntos() + jugadorBPareja2.getPuntos();
		int totalPuntosPareja3 = jugadorAPareja3.getPuntos() + jugadorBPareja3.getPuntos();
		int totalPuntosPareja4 = jugadorAPareja4.getPuntos() + jugadorBPareja4.getPuntos();
		int totalPuntosPareja5 = jugadorAPareja5.getPuntos() + jugadorBPareja5.getPuntos();

		int[] puntos = new int[5];
		puntos[0] = totalPuntosPareja1;
		puntos[1] = totalPuntosPareja2;
		puntos[2] = totalPuntosPareja3;
		puntos[3] = totalPuntosPareja4;
		puntos[4] = totalPuntosPareja5;

		for (int i = 0; i < puntos.length; i++) {
			if (comprobarPuntos == true) {
				for (int j = i + 1; j < puntos.length; j++) {
					if (puntos[i] > puntos[j]) {
						comprobarPuntos = true;
					} else {
						comprobarPuntos = false;
						break;
					}
				}
			}
		}

		for (int i = 0; i < listaJugadoresmanual.size(); i++) {
			for (int j = i + 1; j < listaJugadoresmanual.size(); j++) {
				if (listaJugadoresmanual.get(i).getIdJugador() == listaJugadoresmanual.get(j).getIdJugador()) {
					comprobarDuplicados = true;
				}
			}
		}

		if (comprobarDuplicados == false) {
			if (comprobarPuntos == true) {
				Alert guardarConvocatoriaManual = new Alert(AlertType.CONFIRMATION);

				guardarConvocatoriaManual.setTitle("Confirmar cambio de alineación");
				guardarConvocatoriaManual.setHeaderText("¿Desea cambiar la alineación?");
				guardarConvocatoriaManual.setContentText("No será posible  recuperar la alineación activa");
				DialogPane dialogo3 = guardarConvocatoriaManual.getDialogPane();
				dialogo3.getStylesheets().add(getClass().getResource("/estilos/Estilos.css").toExternalForm());
				dialogo3.getStyleClass().add("myDialog");
				Image image = new Image("@../../imagenes/logoAzul.PNG");
				ImageView imageView = new ImageView(image);
				guardarConvocatoriaManual.setGraphic(imageView);
				Stage stage = new Stage();
				stage = (Stage) guardarConvocatoriaManual.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));

				Optional<ButtonType> resultado = guardarConvocatoriaManual.showAndWait();
				if (resultado.get() == ButtonType.OK) {
					jornadaManual.setIdJornada(jornada.getIdJornada());
					jornadaManual.setEquipoLocal(jornada.getEquipoLocal());
					jornadaManual.setEquipoVisitante(jornada.getEquipoVisitante());
					jornadaManual.setEstado("enJuego");

					partido1.setJornada(jornadaManual);
					partido1.setEstado(true);
					partido1.setIdPartido(listaPartidosManual.get(0).getIdPartido());
					partido1.setJugador1(jugadorAPareja1);
					partido1.setJugador2(jugadorBPareja1);
					partido1.setNombre("Partido 1");
					partidosDao.modificarPartido(partido1);

					partido2.setJornada(jornadaManual);
					partido2.setEstado(true);
					partido2.setIdPartido(listaPartidosManual.get(1).getIdPartido());
					partido2.setJugador1(jugadorAPareja2);
					partido2.setJugador2(jugadorBPareja2);
					partido2.setNombre("Partido 2");
					partidosDao.modificarPartido(partido2);

					partido3.setJornada(jornadaManual);
					partido3.setEstado(true);
					partido3.setIdPartido(listaPartidosManual.get(2).getIdPartido());
					partido3.setJugador1(jugadorAPareja3);
					partido3.setJugador2(jugadorBPareja3);
					partido3.setNombre("Partido 3");
					partidosDao.modificarPartido(partido3);

					partido4.setJornada(jornadaManual);
					partido4.setEstado(true);
					partido4.setIdPartido(listaPartidosManual.get(3).getIdPartido());
					partido4.setJugador1(jugadorAPareja4);
					partido4.setJugador2(jugadorBPareja4);
					partido4.setNombre("Partido 4");
					partidosDao.modificarPartido(partido4);

					partido5.setJornada(jornadaManual);
					partido5.setEstado(true);
					partido5.setIdPartido(listaPartidosManual.get(4).getIdPartido());
					partido5.setJugador1(jugadorAPareja5);
					partido5.setJugador2(jugadorBPareja5);
					partido5.setNombre("Partido 5");
					partidosDao.modificarPartido(partido5);
				}
				Node source = (Node) event.getSource();
				Stage stage2 = (Stage) source.getScene().getWindow();
				stage2.close();
			} else {
				Alert comprobacionAlineacion = new Alert(AlertType.WARNING);
				comprobacionAlineacion.setHeaderText("Por favor revise la alineación");
				comprobacionAlineacion.setContentText("Las parejas no estan correctamente ordenadas por puntos");
				DialogPane dialogo4 = comprobacionAlineacion.getDialogPane();
				dialogo4.getStylesheets().add(getClass().getResource("/estilos/Estilos.css").toExternalForm());
				dialogo4.getStyleClass().add("myDialog");
				Image image = new Image("@../../imagenes/logoAzul.PNG");
				ImageView imageView = new ImageView(image);
				comprobacionAlineacion.setGraphic(imageView);
				Stage stage = new Stage();
				stage = (Stage) comprobacionAlineacion.getDialogPane().getScene().getWindow();
				stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
				comprobacionAlineacion.showAndWait();
			}
		} else {
			Alert comprobacionAlineacion = new Alert(AlertType.WARNING);
			comprobacionAlineacion.setHeaderText("Por favor revise la alineación");
			comprobacionAlineacion.setContentText("Faltan jugadores o/y están repetidos");
			DialogPane dialogo4 = comprobacionAlineacion.getDialogPane();
			dialogo4.getStylesheets().add(getClass().getResource("/estilos/Estilos.css").toExternalForm());
			dialogo4.getStyleClass().add("myDialog");
			Image image = new Image("@../../imagenes/logoAzul.PNG");
			ImageView imageView = new ImageView(image);
			comprobacionAlineacion.setGraphic(imageView);
			Stage stage = new Stage();
			stage = (Stage) comprobacionAlineacion.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
			comprobacionAlineacion.showAndWait();
			listaJugadoresmanual.clear();
		}
	}

	@FXML
	public void aceptarPareja(ActionEvent event) throws IOException {
		if (elejir1Activo) {
			jugadorAPareja1 = comboJugadorA.getValue();
			jugadorBPareja1 = comboJugadorB.getValue();
			String nombreJugadorA = comboJugadorA.getValue().getNombre() + " "
					+ comboJugadorA.getValue().getPrimerApellido();
			int puntosJugadorA = comboJugadorA.getValue().getPuntos();
			jugador1A.setText(nombreJugadorA);
			String nombreJugadorB = comboJugadorB.getValue().getNombre() + " "
					+ comboJugadorB.getValue().getPrimerApellido();
			int puntosJugadorB = comboJugadorB.getValue().getPuntos();
			jugador1B.setText(nombreJugadorB);
			int puntosTotalesPareja = puntosJugadorA + puntosJugadorB;
			puntos1.setText(String.valueOf(puntosTotalesPareja));
			aceptarPareja.setDisable(true);
			cancelarPareja.setDisable(true);
			comboJugadorA.getItems().clear();
			comboJugadorB.getItems().clear();
			elejir1Activo = false;
			pareja1Activa = true;


		}
		if (elejir2Activo) {
			jugadorAPareja2 = comboJugadorA.getValue();
			jugadorBPareja2 = comboJugadorB.getValue();
			String nombreJugadorA = comboJugadorA.getValue().getNombre() + " "
					+ comboJugadorA.getValue().getPrimerApellido();
			int puntosJugadorA = comboJugadorA.getValue().getPuntos();
			jugador2A.setText(nombreJugadorA);
			String nombreJugadorB = comboJugadorB.getValue().getNombre() + " "
					+ comboJugadorB.getValue().getPrimerApellido();
			int puntosJugadorB = comboJugadorB.getValue().getPuntos();
			jugador2B.setText(nombreJugadorB);
			int puntosTotalesPareja = puntosJugadorA + puntosJugadorB;
			puntos2.setText(String.valueOf(puntosTotalesPareja));
			aceptarPareja.setDisable(true);
			cancelarPareja.setDisable(true);
			comboJugadorA.getItems().clear();
			comboJugadorB.getItems().clear();
			elejir2Activo = false;
			pareja2Activa = true;


		}
		if (elejir3Activo) {
			jugadorAPareja3 = comboJugadorA.getValue();
			jugadorBPareja3 = comboJugadorB.getValue();
			String nombreJugadorA = comboJugadorA.getValue().getNombre() + " "
					+ comboJugadorA.getValue().getPrimerApellido();
			int puntosJugadorA = comboJugadorA.getValue().getPuntos();
			jugador3A.setText(nombreJugadorA);
			String nombreJugadorB = comboJugadorB.getValue().getNombre() + " "
					+ comboJugadorB.getValue().getPrimerApellido();
			int puntosJugadorB = comboJugadorB.getValue().getPuntos();
			jugador3B.setText(nombreJugadorB);
			int puntosTotalesPareja = puntosJugadorA + puntosJugadorB;
			puntos3.setText(String.valueOf(puntosTotalesPareja));
			aceptarPareja.setDisable(true);
			cancelarPareja.setDisable(true);
			comboJugadorA.getItems().clear();
			comboJugadorB.getItems().clear();
			elejir3Activo = false;
			pareja3Activa = true;


		}
		if (elejir4Activo) {
			jugadorAPareja4 = comboJugadorA.getValue();
			jugadorBPareja4 = comboJugadorB.getValue();
			String nombreJugadorA = comboJugadorA.getValue().getNombre() + " "
					+ comboJugadorA.getValue().getPrimerApellido();
			int puntosJugadorA = comboJugadorA.getValue().getPuntos();
			jugador4A.setText(nombreJugadorA);
			String nombreJugadorB = comboJugadorB.getValue().getNombre() + " "
					+ comboJugadorB.getValue().getPrimerApellido();
			int puntosJugadorB = comboJugadorB.getValue().getPuntos();
			jugador4B.setText(nombreJugadorB);
			int puntosTotalesPareja = puntosJugadorA + puntosJugadorB;
			puntos4.setText(String.valueOf(puntosTotalesPareja));
			aceptarPareja.setDisable(true);
			cancelarPareja.setDisable(true);
			comboJugadorA.getItems().clear();
			comboJugadorB.getItems().clear();
			elejir4Activo = false;
			pareja4Activa = true;

		}
		if (elejir5Activo) {
			jugadorAPareja5 = comboJugadorA.getValue();
			jugadorBPareja5 = comboJugadorB.getValue();
			String nombreJugadorA = comboJugadorA.getValue().getNombre() + " "
					+ comboJugadorA.getValue().getPrimerApellido();
			int puntosJugadorA = comboJugadorA.getValue().getPuntos();
			jugador5A.setText(nombreJugadorA);
			String nombreJugadorB = comboJugadorB.getValue().getNombre() + " "
					+ comboJugadorB.getValue().getPrimerApellido();
			int puntosJugadorB = comboJugadorB.getValue().getPuntos();
			jugador5B.setText(nombreJugadorB);
			int puntosTotalesPareja = puntosJugadorA + puntosJugadorB;
			puntos5.setText(String.valueOf(puntosTotalesPareja));
			aceptarPareja.setDisable(true);
			cancelarPareja.setDisable(true);
			comboJugadorA.getItems().clear();
			comboJugadorB.getItems().clear();
			elejir5Activo = false;
			pareja5Activa = true;
			
		}
		nombrePareja.setText("Nombre Pareja");
		if( pareja1Activa && pareja2Activa && pareja3Activa && pareja4Activa && pareja5Activa) {
			validar.setDisable(false);
		}

	}

	@FXML
	public void cancelarPareja(ActionEvent event) throws IOException {
		elejir1Activo = false;
		elejir2Activo = false;
		elejir3Activo = false;
		elejir4Activo = false;
		elejir5Activo = false;
		aceptarPareja.setDisable(true);
		cancelarPareja.setDisable(true);
		comboJugadorA.getItems().clear();
		comboJugadorB.getItems().clear();
		nombrePareja.setText("Nombre Pareja");
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

}
