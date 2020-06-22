package proyectoPadelUp.controladores;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proyectoPadelUp.Email;
import proyectoPadelUp.daos.EquiposDao;
import proyectoPadelUp.daos.JornadasDao;
import proyectoPadelUp.daos.JugadoresDao;
import proyectoPadelUp.daos.PartidosDao;
import proyectoPadelUp.daos.TemporadasDao;
import proyectoPadelUp.daos.TemporadasEquiposDao;
import proyectoPadelUp.pojos.Equipo;
import proyectoPadelUp.pojos.Jornada;
import proyectoPadelUp.pojos.Jugador;
import proyectoPadelUp.pojos.Partido;
import proyectoPadelUp.pojos.Temporada;

public class ControladorJornadaEnJuego implements Initializable {

	@FXML
	private Text titulo, numeroJornada, fecha, equipoLocal, equipoVisitante, equipoLocalGrande, equipoVisitanteGrande,
			jugador1, jugador2, jugador3, jugador4, jugador5, jugador6, jugador7, jugador8, jugador9, jugador10,
			juegosGanadosLocal, juegosGanadosVisitante, setsGanadosLocal, setsGanadosVisitante, partidosGanadosLocal,
			partidosGanadosVisitante;

	@FXML
	private TextField pareja1Set1A, pareja1Set1B, pareja1Set2A, pareja1Set2B, pareja1Set3A, pareja1Set3B, pareja2Set1A,
			pareja2Set1B, pareja2Set2A, pareja2Set2B, pareja2Set3A, pareja2Set3B, pareja3Set1A, pareja3Set1B,
			pareja3Set2A, pareja3Set2B, pareja3Set3A, pareja3Set3B, pareja4Set1A, pareja4Set1B, pareja4Set2A,
			pareja4Set2B, pareja4Set3A, pareja4Set3B, pareja5Set1A, pareja5Set1B, pareja5Set2A, pareja5Set2B,
			pareja5Set3A, pareja5Set3B;

	@FXML
	private CheckBox pareja1CheckG, pareja1CheckP, pareja2CheckG, pareja2CheckP, pareja3CheckG, pareja3CheckP,
			pareja4CheckG, pareja4CheckP, pareja5CheckG, pareja5CheckP;

	@FXML
	private Button alineacionManual, comunicar, imprimir, resultado, borrar, guardarJornada, volver;

	private Equipo equipoActivo = new Equipo();
	private Equipo equipoLocalNombre = new Equipo();
	private Equipo equipoVisitanteNombre = new Equipo();
	private Temporada temporada = new Temporada();
	private Temporada cerrarTemporada = new Temporada();
	private static Jornada jornada = new Jornada();
	private Jornada cerrarJornada = new Jornada();
	private Partido partido1 = new Partido();
	private Partido partido2 = new Partido();
	private Partido partido3 = new Partido();
	private Partido partido4 = new Partido();
	private Partido partido5 = new Partido();

	private TemporadasEquiposDao temporadasEquiposDao = new TemporadasEquiposDao();
	private JornadasDao jornadasDao = new JornadasDao();
	private PartidosDao partidosDao = new PartidosDao();
	private EquiposDao equiposDao = new EquiposDao();
	private JugadoresDao jugadoresDao = new JugadoresDao();
	private TemporadasDao temporadasDao = new TemporadasDao();

	private String pareja1ASet1 = "0";
	private String pareja1ASet2 = "0";
	private String pareja1ASet3 = "0";
	private String pareja2ASet1 = "0";
	private String pareja2ASet2 = "0";
	private String pareja2ASet3 = "0";
	private String pareja3ASet1 = "0";
	private String pareja3ASet2 = "0";
	private String pareja3ASet3 = "0";
	private String pareja4ASet1 = "0";
	private String pareja4ASet2 = "0";
	private String pareja4ASet3 = "0";
	private String pareja5ASet1 = "0";
	private String pareja5ASet2 = "0";
	private String pareja5ASet3 = "0";
	private String pareja1BSet1 = "0";
	private String pareja1BSet2 = "0";
	private String pareja1BSet3 = "0";
	private String pareja2BSet1 = "0";
	private String pareja2BSet2 = "0";
	private String pareja2BSet3 = "0";
	private String pareja3BSet1 = "0";
	private String pareja3BSet2 = "0";
	private String pareja3BSet3 = "0";
	private String pareja4BSet1 = "0";
	private String pareja4BSet2 = "0";
	private String pareja4BSet3 = "0";
	private String pareja5BSet1 = "0";
	private String pareja5BSet2 = "0";
	private String pareja5BSet3 = "0";

	private static String cuerpoEmail;
	private String regex = "7|[0-7]";

	private int juegosParejaLocal1, juegosParejaLocal2, juegosParejaLocal3, juegosParejaLocal4, juegosParejaLocal5,
			juegosParejaVisitante1, juegosParejaVisitante2, juegosParejaVisitante3, juegosParejaVisitante4,
			juegosParejaVisitante5, juegosTotalesLocal, juegosTotalesVisitante;

	private int setsPareja1Local, setsPareja2Local, setsPareja3Local, setsPareja4Local, setsPareja5Local,
			setsPareja1Visitante, setsPareja2Visitante, setsPareja3Visitante, setsPareja4Visitante,
			setsPareja5Visitante, totalSetsLocal, totalSetsVisitante, numeroSetsPareja1Local, numeroSetsPareja2Local,
			numeroSetsPareja3Local, numeroSetsPareja4Local, numeroSetsPareja5Local, numeroSetsPareja1Visitante,
			numeroSetsPareja2Visitante, numeroSetsPareja3Visitante, numeroSetsPareja4Visitante,
			numeroSetsPareja5Visitante;

	private int partidosGanados, partidosPerdidos;
	private List<Jugador> listaJugadores;
	private List<Partido> listaPartidos = new ArrayList<>();

	private int totalPuntosPareja1;
	private int totalPuntosPareja2;
	private int totalPuntosPareja3;
	private int totalPuntosPareja4;
	private int totalPuntosPareja5;

	private Stage stage;

	public void setPrimaryStage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		boolean jornadaEnJuego = jornadasDao.equipoConJornadaEnJuego(ControladorEquipos.equipoActivo.getIdEquipo());
		if (jornadaEnJuego) {
			temporada = temporadasEquiposDao.consultarTemporadaPorEquipo(ControladorEquipos.equipoActivo.getIdEquipo());
			jornada = (Jornada) jornadasDao.consultarJornadaEnJuegoPorTemporada(temporada.getIdTemporada());
			equipoLocalNombre = equiposDao.consultarEquipoPorId(jornada.getEquipoLocal().getIdEquipo());
			equipoVisitanteNombre = equiposDao.consultarEquipoPorId(jornada.getEquipoVisitante().getIdEquipo());
			listaJugadores = jugadoresDao.consultarTodosPorEquipo(ControladorEquipos.equipoActivo.getIdEquipo());
			listaPartidos = partidosDao.consultarPartidosPorJornada(jornada.getIdJornada());
			titulo.setText("Division " + temporada.getDivision() + " - Grupo " + temporada.getGrupo());
			numeroJornada.setText(jornada.getNombre().substring(7));
			Date date = jornada.getFecha();
			fecha.setText(new SimpleDateFormat("dd-MM-yyyy").format(date));

			cuerpoEmail = "Has sido convocado para el partido " + equipoLocalNombre.getNombre() + " Vs "
					+ equipoVisitanteNombre.getNombre() + " que se jugará el día "
					+ new SimpleDateFormat("dd-MM-yyyy").format(date) + ".";

			juegosFormateados();

			equipoLocal.setText(equipoLocalNombre.getNombre());
			equipoVisitante.setText(equipoVisitanteNombre.getNombre());
			equipoLocalGrande.setText(equipoLocalNombre.getNombre());
			equipoVisitanteGrande.setText(equipoVisitanteNombre.getNombre());

			totalPuntosPareja1 = listaPartidos.get(0).getJugador1().getPuntos()
					+ listaPartidos.get(0).getJugador2().getPuntos();
			totalPuntosPareja2 = listaPartidos.get(1).getJugador1().getPuntos()
					+ listaPartidos.get(1).getJugador2().getPuntos();
			totalPuntosPareja3 = listaPartidos.get(2).getJugador1().getPuntos()
					+ listaPartidos.get(2).getJugador2().getPuntos();
			totalPuntosPareja4 = listaPartidos.get(3).getJugador1().getPuntos()
					+ listaPartidos.get(3).getJugador2().getPuntos();
			totalPuntosPareja5 = listaPartidos.get(4).getJugador1().getPuntos()
					+ listaPartidos.get(4).getJugador2().getPuntos();

			pintarListaJugadores();
		}
	}

	private void pintarListaJugadores() {
		jugador1.setText(listaPartidos.get(0).getJugador1().getNombre() + " "
				+ listaPartidos.get(0).getJugador1().getPrimerApellido());
		jugador2.setText(listaPartidos.get(0).getJugador2().getNombre() + " "
				+ listaPartidos.get(0).getJugador2().getPrimerApellido());
		jugador3.setText(listaPartidos.get(1).getJugador1().getNombre() + " "
				+ listaPartidos.get(1).getJugador1().getPrimerApellido());
		jugador4.setText(listaPartidos.get(1).getJugador2().getNombre() + " "
				+ listaPartidos.get(1).getJugador2().getPrimerApellido());
		jugador5.setText(listaPartidos.get(2).getJugador1().getNombre() + " "
				+ listaPartidos.get(2).getJugador1().getPrimerApellido());
		jugador6.setText(listaPartidos.get(2).getJugador2().getNombre() + " "
				+ listaPartidos.get(2).getJugador2().getPrimerApellido());
		jugador7.setText(listaPartidos.get(3).getJugador1().getNombre() + " "
				+ listaPartidos.get(3).getJugador1().getPrimerApellido());
		jugador8.setText(listaPartidos.get(3).getJugador2().getNombre() + " "
				+ listaPartidos.get(3).getJugador2().getPrimerApellido());
		jugador9.setText(listaPartidos.get(4).getJugador1().getNombre() + " "
				+ listaPartidos.get(4).getJugador1().getPrimerApellido());
		jugador10.setText(listaPartidos.get(4).getJugador2().getNombre() + " "
				+ listaPartidos.get(4).getJugador2().getPrimerApellido());
	}

	@FXML
	public void alineacionManual(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(
				getClass().getResource("/proyectoPadelUp/alineacionManual.fxml"));
		Parent contenedor = fxmlLoader.load();
		Stage stage = new Stage();
		int idEquipo = equipoActivo.getIdEquipo();
		stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
		stage.setTitle("Alineación Manual");
		ControladorAlineacionManual controladorAlineacionManual = fxmlLoader
				.<ControladorAlineacionManual>getController();

		controladorAlineacionManual.setIdEquipo(idEquipo);
		Scene escena = new Scene(contenedor, 734, 537);
		escena.getStylesheets().addAll(getClass().getResource("/estilos/Estilos.css").toExternalForm());
		stage.setResizable(false);
		stage.setScene(escena);
		stage.initModality(Modality.APPLICATION_MODAL);

		stage.showAndWait();
		listaPartidos = partidosDao.consultarPartidosPorJornada(jornada.getIdJornada());
		pintarListaJugadores();
	}

	@FXML
	public void comunicar(ActionEvent event) throws Exception {

		Alert enviarMail = new Alert(AlertType.CONFIRMATION);

		enviarMail.setTitle("Comunicar convocatoria");
		enviarMail.setHeaderText("¿Desea comunicar esta convocatoria?");
		DialogPane dialogo3 = enviarMail.getDialogPane();
		dialogo3.getStylesheets().add(getClass().getResource("/estilos/Estilos.css").toExternalForm());
		dialogo3.getStyleClass().add("myDialog");
		Image image = new Image("@../../imagenes/logoAzul.PNG");
		ImageView imageView = new ImageView(image);
		enviarMail.setGraphic(imageView);
		Stage stage = new Stage();
		stage = (Stage) enviarMail.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));

		Optional<ButtonType> resultado = enviarMail.showAndWait();
		if (resultado.get() == ButtonType.OK) {
			List<String> emails = new ArrayList<String>();
			emails.add(listaPartidos.get(0).getJugador1().getEmail());
			emails.add(listaPartidos.get(0).getJugador2().getEmail());
			emails.add(listaPartidos.get(1).getJugador1().getEmail());
			emails.add(listaPartidos.get(1).getJugador2().getEmail());
			emails.add(listaPartidos.get(2).getJugador1().getEmail());
			emails.add(listaPartidos.get(2).getJugador2().getEmail());
			emails.add(listaPartidos.get(3).getJugador1().getEmail());
			emails.add(listaPartidos.get(3).getJugador2().getEmail());
			emails.add(listaPartidos.get(4).getJugador1().getEmail());
			emails.add(listaPartidos.get(4).getJugador2().getEmail());
			
			Email email = new Email(emails);
	
			Thread t = new Thread(email);
			t.start();
		}
	}

	@FXML
	public void crearActa(ActionEvent event) {

		if (equipoLocalNombre.getIdEquipo() == ControladorEquipos.equipoActivo.getIdEquipo()) {
			crearActaLocal();
		} else {
			crearActaVisitante();
		}

	}

	@FXML
	public void generarResultado(ActionEvent event) {

		calcularJuegosLocal();
		calcularJuegosVisitante();
		calcularSets();
		calcularPartidos();

		juegosGanadosLocal.setText(String.valueOf(juegosTotalesLocal));
		juegosGanadosVisitante.setText(String.valueOf(juegosTotalesVisitante));
		setsGanadosLocal.setText(String.valueOf(totalSetsLocal));
		setsGanadosVisitante.setText(String.valueOf(totalSetsVisitante));
		if (equipoActivo.getIdEquipo() == equipoLocalNombre.getIdEquipo()) {
			partidosGanadosLocal.setText(String.valueOf(partidosGanados));
			partidosGanadosVisitante.setText(String.valueOf(partidosPerdidos));
		} else {
			partidosGanadosVisitante.setText(String.valueOf(partidosGanados));
			partidosGanadosLocal.setText(String.valueOf(partidosPerdidos));
		}
	}

	private void calcularPartidos() {

		partidosGanados = 0;
		partidosPerdidos = 0;

		if (equipoActivo.getIdEquipo() == equipoLocalNombre.getIdEquipo() && setsPareja1Local > setsPareja1Visitante) {
			pareja1CheckG.setSelected(true);
			pareja1CheckP.setSelected(false);
		} else if (equipoActivo.getIdEquipo() == equipoLocalNombre.getIdEquipo()
				&& setsPareja1Local < setsPareja1Visitante) {
			pareja1CheckP.setSelected(true);
			pareja1CheckG.setSelected(false);
		}
		if (equipoActivo.getIdEquipo() != equipoLocalNombre.getIdEquipo() && setsPareja1Local > setsPareja1Visitante) {
			pareja1CheckG.setSelected(false);
			pareja1CheckP.setSelected(true);
		} else if (equipoActivo.getIdEquipo() != equipoLocalNombre.getIdEquipo()
				&& setsPareja1Local < setsPareja1Visitante) {
			pareja1CheckP.setSelected(false);
			pareja1CheckG.setSelected(true);
		}

		if (equipoActivo.getIdEquipo() == equipoLocalNombre.getIdEquipo() && setsPareja2Local > setsPareja2Visitante) {
			pareja2CheckG.setSelected(true);
			pareja2CheckP.setSelected(false);
		} else if (equipoActivo.getIdEquipo() == equipoLocalNombre.getIdEquipo()
				&& setsPareja2Local < setsPareja2Visitante) {
			pareja2CheckP.setSelected(true);
			pareja2CheckG.setSelected(false);
		}
		if (equipoActivo.getIdEquipo() != equipoLocalNombre.getIdEquipo() && setsPareja2Local > setsPareja2Visitante) {
			pareja2CheckG.setSelected(false);
			pareja2CheckP.setSelected(true);
		} else if (equipoActivo.getIdEquipo() != equipoLocalNombre.getIdEquipo()
				&& setsPareja2Local < setsPareja2Visitante) {
			pareja2CheckP.setSelected(false);
			pareja2CheckG.setSelected(true);
		}

		if (equipoActivo.getIdEquipo() == equipoLocalNombre.getIdEquipo() && setsPareja3Local > setsPareja3Visitante) {
			pareja3CheckG.setSelected(true);
			pareja3CheckP.setSelected(false);
		} else if (equipoActivo.getIdEquipo() == equipoLocalNombre.getIdEquipo()
				&& setsPareja3Local < setsPareja3Visitante) {
			pareja3CheckP.setSelected(true);
			pareja3CheckG.setSelected(false);
		}
		if (equipoActivo.getIdEquipo() != equipoLocalNombre.getIdEquipo() && setsPareja3Local > setsPareja3Visitante) {
			pareja3CheckG.setSelected(false);
			pareja3CheckP.setSelected(true);
		} else if (equipoActivo.getIdEquipo() != equipoLocalNombre.getIdEquipo()
				&& setsPareja3Local < setsPareja3Visitante) {
			pareja3CheckP.setSelected(false);
			pareja3CheckG.setSelected(true);
		}

		if (equipoActivo.getIdEquipo() == equipoLocalNombre.getIdEquipo() && setsPareja4Local > setsPareja4Visitante) {
			pareja4CheckG.setSelected(true);
			pareja4CheckP.setSelected(false);
		} else if (equipoActivo.getIdEquipo() == equipoLocalNombre.getIdEquipo()
				&& setsPareja4Local < setsPareja4Visitante) {
			pareja4CheckP.setSelected(true);
			pareja4CheckG.setSelected(false);
		}
		if (equipoActivo.getIdEquipo() != equipoLocalNombre.getIdEquipo() && setsPareja4Local > setsPareja4Visitante) {
			pareja4CheckG.setSelected(false);
			pareja4CheckP.setSelected(true);
		} else if (equipoActivo.getIdEquipo() != equipoLocalNombre.getIdEquipo()
				&& setsPareja4Local < setsPareja4Visitante) {
			pareja4CheckP.setSelected(false);
			pareja4CheckG.setSelected(true);
		}

		if (equipoActivo.getIdEquipo() == equipoLocalNombre.getIdEquipo() && setsPareja5Local > setsPareja5Visitante) {
			pareja5CheckG.setSelected(true);
			pareja5CheckP.setSelected(false);
		} else if (equipoActivo.getIdEquipo() == equipoLocalNombre.getIdEquipo()
				&& setsPareja5Local < setsPareja5Visitante) {
			pareja5CheckP.setSelected(true);
			pareja5CheckG.setSelected(false);
		}
		if (equipoActivo.getIdEquipo() != equipoLocalNombre.getIdEquipo() && setsPareja5Local > setsPareja5Visitante) {
			pareja5CheckG.setSelected(false);
			pareja5CheckP.setSelected(true);
		} else if (equipoActivo.getIdEquipo() != equipoLocalNombre.getIdEquipo()
				&& setsPareja5Local < setsPareja5Visitante) {
			pareja5CheckP.setSelected(false);
			pareja5CheckG.setSelected(true);
		}

		if (pareja1CheckG.isSelected()) {
			partidosGanados++;
		} else if (pareja1CheckP.isSelected()) {
			partidosPerdidos++;
		}
		if (pareja2CheckG.isSelected()) {
			partidosGanados++;
		} else if (pareja2CheckP.isSelected()) {
			partidosPerdidos++;
		}
		if (pareja3CheckG.isSelected()) {
			partidosGanados++;
		} else if (pareja3CheckP.isSelected()) {
			partidosPerdidos++;
		}
		if (pareja4CheckG.isSelected()) {
			partidosGanados++;
		} else if (pareja4CheckP.isSelected()) {
			partidosPerdidos++;
		}
		if (pareja5CheckG.isSelected()) {
			partidosGanados++;
		} else if (pareja5CheckP.isSelected()) {
			partidosPerdidos++;
		}
	}

	private void calcularSets() {

		setsPareja1Local = 0;
		setsPareja1Visitante = 0;
		setsPareja2Local = 0;
		setsPareja2Visitante = 0;
		setsPareja3Local = 0;
		setsPareja3Visitante = 0;
		setsPareja4Local = 0;
		setsPareja4Visitante = 0;
		setsPareja5Local = 0;
		setsPareja5Visitante = 0;

		if (Integer.parseInt(pareja1ASet1) > Integer.parseInt(pareja1BSet1)) {
			setsPareja1Local++;
		}
		if (Integer.parseInt(pareja1ASet1) < Integer.parseInt(pareja1BSet1)) {
			setsPareja1Visitante++;
		}
		if (Integer.parseInt(pareja1ASet2) > Integer.parseInt(pareja1BSet2)) {
			setsPareja1Local++;
		}
		if (Integer.parseInt(pareja1ASet2) < Integer.parseInt(pareja1BSet2)) {
			setsPareja1Visitante++;
		}
		if (setsPareja1Local == 2 || setsPareja1Visitante == 2) {
			pareja1Set3A.clear();
			pareja1Set3B.clear();
		}
		if (Integer.parseInt(pareja1ASet3) > Integer.parseInt(pareja1BSet3)
				&& setsPareja1Local == setsPareja1Visitante) {
			setsPareja1Local++;
		}
		if (Integer.parseInt(pareja1ASet3) < Integer.parseInt(pareja1BSet3)
				&& setsPareja1Local == setsPareja1Visitante) {
			setsPareja1Visitante++;
		}
		if (Integer.parseInt(pareja1ASet3) == Integer.parseInt(pareja1BSet3)) {
			setsPareja1Local = setsPareja1Local;
			setsPareja1Visitante = setsPareja1Visitante;
		}
		numeroSetsPareja1Local = setsPareja1Local;
		numeroSetsPareja1Visitante = setsPareja1Visitante;
		/*------------------------------------------------------------------------------*/
		if (Integer.parseInt(pareja2ASet1) > Integer.parseInt(pareja2BSet1)) {
			setsPareja2Local++;
		}
		if (Integer.parseInt(pareja2ASet1) < Integer.parseInt(pareja2BSet1)) {
			setsPareja2Visitante++;
		}
		if (Integer.parseInt(pareja2ASet2) > Integer.parseInt(pareja2BSet2)) {
			setsPareja2Local++;
		}
		if (Integer.parseInt(pareja2ASet2) < Integer.parseInt(pareja2BSet2)) {
			setsPareja2Visitante++;
		}
		if (setsPareja2Local == 2 || setsPareja2Visitante == 2) {
			pareja2Set3A.clear();
			pareja2Set3B.clear();
		}
		if (Integer.parseInt(pareja2ASet3) > Integer.parseInt(pareja2BSet3)
				&& setsPareja2Local == setsPareja2Visitante) {
			setsPareja2Local++;
		}
		if (Integer.parseInt(pareja2ASet3) < Integer.parseInt(pareja2BSet3)
				&& setsPareja2Local == setsPareja2Visitante) {
			setsPareja2Visitante++;
		}
		if (Integer.parseInt(pareja2ASet3) == Integer.parseInt(pareja2BSet3)) {
			setsPareja2Local = setsPareja2Local;
			setsPareja2Visitante = setsPareja2Visitante;
		}
		numeroSetsPareja2Local = setsPareja2Local;
		numeroSetsPareja2Visitante = setsPareja2Visitante;
		/*------------------------------------------------------------------------------*/
		if (Integer.parseInt(pareja3ASet1) > Integer.parseInt(pareja3BSet1)) {
			setsPareja3Local++;
		}
		if (Integer.parseInt(pareja3ASet1) < Integer.parseInt(pareja3BSet1)) {
			setsPareja3Visitante++;
		}
		if (Integer.parseInt(pareja3ASet2) > Integer.parseInt(pareja3BSet2)) {
			setsPareja3Local++;
		}
		if (Integer.parseInt(pareja3ASet2) < Integer.parseInt(pareja3BSet2)) {
			setsPareja3Visitante++;
		}
		if (setsPareja3Local == 2 || setsPareja3Visitante == 2) {
			pareja3Set3A.clear();
			pareja3Set3B.clear();
		}
		if (Integer.parseInt(pareja3ASet3) > Integer.parseInt(pareja3BSet3)
				&& setsPareja3Local == setsPareja3Visitante) {
			setsPareja3Local++;
		}
		if (Integer.parseInt(pareja3ASet3) < Integer.parseInt(pareja3BSet3)
				&& setsPareja3Local == setsPareja3Visitante) {
			setsPareja3Visitante++;
		}
		if (Integer.parseInt(pareja3ASet3) == Integer.parseInt(pareja3BSet3)) {
			setsPareja3Local = setsPareja3Local;
			setsPareja3Visitante = setsPareja3Visitante;
		}
		numeroSetsPareja3Local = setsPareja3Local;
		numeroSetsPareja3Visitante = setsPareja3Visitante;
		/*------------------------------------------------------------------------------*/
		if (Integer.parseInt(pareja4ASet1) > Integer.parseInt(pareja4BSet1)) {
			setsPareja4Local++;
		}
		if (Integer.parseInt(pareja4ASet1) < Integer.parseInt(pareja4BSet1)) {
			setsPareja4Visitante++;
		}
		if (Integer.parseInt(pareja4ASet2) > Integer.parseInt(pareja4BSet2)) {
			setsPareja4Local++;
		}
		if (Integer.parseInt(pareja4ASet2) < Integer.parseInt(pareja4BSet2)) {
			setsPareja4Visitante++;
		}
		if (setsPareja4Local == 2 || setsPareja4Visitante == 2) {
			pareja4Set3A.clear();
			pareja4Set3B.clear();
		}
		if (Integer.parseInt(pareja4ASet3) > Integer.parseInt(pareja4BSet3)
				&& setsPareja4Local == setsPareja4Visitante) {
			setsPareja4Local++;
		}
		if (Integer.parseInt(pareja4ASet3) < Integer.parseInt(pareja4BSet3)
				&& setsPareja4Local == setsPareja4Visitante) {
			setsPareja4Visitante++;
		}
		if (Integer.parseInt(pareja4ASet3) == Integer.parseInt(pareja4BSet3)) {
			setsPareja4Local = setsPareja4Local;
			setsPareja4Visitante = setsPareja4Visitante;
		}
		numeroSetsPareja4Local = setsPareja4Local;
		numeroSetsPareja4Visitante = setsPareja4Visitante;
		/*------------------------------------------------------------------------------*/
		if (Integer.parseInt(pareja5ASet1) > Integer.parseInt(pareja5BSet1)) {
			setsPareja5Local++;
		}
		if (Integer.parseInt(pareja5ASet1) < Integer.parseInt(pareja5BSet1)) {
			setsPareja5Visitante++;
		}
		if (Integer.parseInt(pareja5ASet2) > Integer.parseInt(pareja5BSet2)) {
			setsPareja5Local++;
		}
		if (Integer.parseInt(pareja5ASet2) < Integer.parseInt(pareja5BSet2)) {
			setsPareja5Visitante++;
		}
		if (setsPareja5Local == 2 || setsPareja5Visitante == 2) {
			pareja5Set3A.clear();
			pareja5Set3B.clear();
		}
		if (Integer.parseInt(pareja5ASet3) > Integer.parseInt(pareja5BSet3)
				&& setsPareja5Local == setsPareja5Visitante) {
			setsPareja5Local++;
		}
		if (Integer.parseInt(pareja5ASet3) < Integer.parseInt(pareja5BSet3)
				&& setsPareja5Local == setsPareja5Visitante) {
			setsPareja5Visitante++;
		}
		if (Integer.parseInt(pareja5ASet3) == Integer.parseInt(pareja5BSet3)) {
			setsPareja5Local = setsPareja5Local;
			setsPareja5Visitante = setsPareja5Visitante;
		}
		numeroSetsPareja5Local = setsPareja5Local;
		numeroSetsPareja5Visitante = setsPareja5Visitante;
		/*------------------------------------------------------------------------------*/
		totalSetsLocal = numeroSetsPareja1Local + numeroSetsPareja2Local + numeroSetsPareja3Local
				+ numeroSetsPareja4Local + numeroSetsPareja5Local;
		totalSetsVisitante = numeroSetsPareja1Visitante + numeroSetsPareja2Visitante + numeroSetsPareja3Visitante
				+ numeroSetsPareja4Visitante + numeroSetsPareja5Visitante;

	}

	private void calcularJuegosVisitante() {

		if (pareja1Set1B.getText().length() == 0) {
			pareja1BSet1 = "0";
			pareja1BSet2 = "0";
			pareja1BSet3 = "0";
			pareja1Set2B.clear();
			pareja1Set3B.clear();
		} else if (pareja1Set1B.getText().length() != 0) {
			pareja1BSet1 = pareja1Set1B.getText();
			if (pareja1Set2B.getText().length() == 0) {
				pareja1BSet1 = "0";
				pareja1BSet2 = "0";
				pareja1BSet3 = "0";
				pareja1Set1B.clear();
				pareja1Set3B.clear();
			} else {
				pareja1BSet2 = pareja1Set2B.getText();
			}
			if (pareja1Set3B.getText().length() == 0) {
				pareja1BSet3 = "0";
			} else {
				pareja1BSet3 = pareja1Set3B.getText();
			}
		} else {
			pareja1BSet3 = pareja1Set3B.getText();
		}
		/*------------------------------------------------------------------------------*/
		if (pareja2Set1B.getText().length() == 0) {
			pareja2BSet1 = "0";
			pareja2BSet2 = "0";
			pareja2BSet3 = "0";
			pareja2Set2B.clear();
			pareja2Set3B.clear();
		} else if (pareja2Set1B.getText().length() != 0) {
			pareja2BSet1 = pareja2Set1B.getText();
			if (pareja2Set2B.getText().length() == 0) {
				pareja2BSet1 = "0";
				pareja2BSet2 = "0";
				pareja2BSet3 = "0";
				pareja2Set1B.clear();
				pareja2Set3B.clear();
			} else {
				pareja2BSet2 = pareja2Set2B.getText();
			}
			if (pareja2Set3B.getText().length() == 0) {
				pareja2BSet3 = "0";
			} else {
				pareja2BSet3 = pareja2Set3B.getText();
			}
		} else {
			pareja2BSet3 = pareja2Set3B.getText();
		}
		/*------------------------------------------------------------------------------*/
		if (pareja3Set1B.getText().length() == 0) {
			pareja3BSet1 = "0";
			pareja3BSet2 = "0";
			pareja3BSet3 = "0";
			pareja3Set2B.clear();
			pareja3Set3B.clear();
		} else if (pareja3Set1B.getText().length() != 0) {
			pareja3BSet1 = pareja3Set1B.getText();
			if (pareja3Set2B.getText().length() == 0) {
				pareja3BSet1 = "0";
				pareja3BSet2 = "0";
				pareja3BSet3 = "0";
				pareja3Set1B.clear();
				pareja3Set3B.clear();
			} else {
				pareja3BSet2 = pareja3Set2B.getText();
			}
			if (pareja3Set3B.getText().length() == 0) {
				pareja3BSet3 = "0";
			} else {
				pareja3BSet3 = pareja3Set3B.getText();
			}
		} else {
			pareja3BSet3 = pareja3Set3B.getText();
		}
		/*------------------------------------------------------------------------------*/
		if (pareja4Set1B.getText().length() == 0) {
			pareja4BSet1 = "0";
			pareja4BSet2 = "0";
			pareja4BSet3 = "0";
			pareja4Set2B.clear();
			pareja4Set3B.clear();
		} else if (pareja4Set1B.getText().length() != 0) {
			pareja4BSet1 = pareja4Set1B.getText();
			if (pareja4Set2B.getText().length() == 0) {
				pareja4BSet1 = "0";
				pareja4BSet2 = "0";
				pareja4BSet3 = "0";
				pareja4Set1B.clear();
				pareja4Set3B.clear();
			} else {
				pareja4BSet2 = pareja4Set2B.getText();
			}
			if (pareja4Set3B.getText().length() == 0) {
				pareja4BSet3 = "0";
			} else {
				pareja4BSet3 = pareja4Set3B.getText();
			}
		} else {
			pareja4BSet3 = pareja4Set3B.getText();
		}
		/*------------------------------------------------------------------------------*/
		if (pareja5Set1B.getText().length() == 0) {
			pareja5BSet1 = "0";
			pareja5BSet2 = "0";
			pareja5BSet3 = "0";
			pareja5Set2B.clear();
			pareja5Set3B.clear();
		} else if (pareja5Set1B.getText().length() != 0) {
			pareja5BSet1 = pareja5Set1B.getText();
			if (pareja5Set2B.getText().length() == 0) {
				pareja5BSet1 = "0";
				pareja5BSet2 = "0";
				pareja5BSet3 = "0";
				pareja5Set1B.clear();
				pareja5Set3B.clear();
			} else {
				pareja5BSet2 = pareja5Set2B.getText();
			}
			if (pareja5Set3B.getText().length() == 0) {
				pareja5BSet3 = "0";
			} else {
				pareja5BSet3 = pareja5Set3B.getText();
			}
		} else {
			pareja5BSet3 = pareja5Set3B.getText();
		}

		juegosParejaVisitante1 = Integer.parseInt(pareja1BSet1) + Integer.parseInt(pareja1BSet2)
				+ Integer.parseInt(pareja1BSet3);
		juegosParejaVisitante2 = Integer.parseInt(pareja2BSet1) + Integer.parseInt(pareja2BSet2)
				+ Integer.parseInt(pareja2BSet3);
		juegosParejaVisitante3 = Integer.parseInt(pareja3BSet1) + Integer.parseInt(pareja3BSet2)
				+ Integer.parseInt(pareja3BSet3);
		juegosParejaVisitante4 = Integer.parseInt(pareja4BSet1) + Integer.parseInt(pareja4BSet2)
				+ Integer.parseInt(pareja4BSet3);
		juegosParejaVisitante5 = Integer.parseInt(pareja5BSet1) + Integer.parseInt(pareja5BSet2)
				+ Integer.parseInt(pareja5BSet3);
		juegosTotalesVisitante = juegosParejaVisitante1 + juegosParejaVisitante2 + juegosParejaVisitante3
				+ juegosParejaVisitante4 + juegosParejaVisitante5;

	}

	private void calcularJuegosLocal() {

		if (pareja1Set1A.getText().length() == 0) {
			pareja1ASet1 = "0";
			pareja1ASet2 = "0";
			pareja1ASet3 = "0";
			pareja1Set2A.clear();
			pareja1Set3A.clear();
		} else if (pareja1Set1A.getText().length() != 0) {
			pareja1ASet1 = pareja1Set1A.getText();
			if (pareja1Set2A.getText().length() == 0) {
				pareja1ASet1 = "0";
				pareja1ASet2 = "0";
				pareja1ASet3 = "0";
				pareja1Set1A.clear();
				pareja1Set3A.clear();
			} else {
				pareja1ASet2 = pareja1Set2A.getText();
			}
			if (pareja1Set3A.getText().length() == 0) {
				pareja1ASet3 = "0";
			} else {
				pareja1ASet3 = pareja1Set3A.getText();
			}
		} else {
			pareja1ASet3 = pareja1Set3A.getText();
		}
		/*------------------------------------------------------------------------------*/
		if (pareja2Set1A.getText().length() == 0) {
			pareja2ASet1 = "0";
			pareja2ASet2 = "0";
			pareja2ASet3 = "0";
			pareja2Set2A.clear();
			pareja2Set3A.clear();
		} else if (pareja2Set1A.getText().length() != 0) {
			pareja2ASet1 = pareja2Set1A.getText();
			if (pareja2Set2A.getText().length() == 0) {
				pareja2ASet1 = "0";
				pareja2ASet2 = "0";
				pareja2ASet3 = "0";
				pareja2Set1A.clear();
				pareja2Set3A.clear();
			} else {
				pareja2ASet2 = pareja2Set2A.getText();
			}
			if (pareja2Set3A.getText().length() == 0) {
				pareja2ASet3 = "0";
			} else {
				pareja2ASet3 = pareja2Set3A.getText();
			}
		} else {
			pareja2ASet3 = pareja2Set3A.getText();
		}
		/*------------------------------------------------------------------------------*/
		if (pareja3Set1A.getText().length() == 0) {
			pareja3ASet1 = "0";
			pareja3ASet2 = "0";
			pareja3ASet3 = "0";
			pareja3Set2A.clear();
			pareja3Set3A.clear();
		} else if (pareja3Set1A.getText().length() != 0) {
			pareja3ASet1 = pareja3Set1A.getText();
			if (pareja3Set2A.getText().length() == 0) {
				pareja3ASet1 = "0";
				pareja3ASet2 = "0";
				pareja3ASet3 = "0";
				pareja3Set1A.clear();
				pareja3Set3A.clear();
			} else {
				pareja3ASet2 = pareja3Set2A.getText();
			}
			if (pareja3Set3A.getText().length() == 0) {
				pareja3ASet3 = "0";
			} else {
				pareja3ASet3 = pareja3Set3A.getText();
			}
		} else {
			pareja3ASet3 = pareja3Set3A.getText();
		}
		/*------------------------------------------------------------------------------*/
		if (pareja4Set1A.getText().length() == 0) {
			pareja4ASet1 = "0";
			pareja4ASet2 = "0";
			pareja4ASet3 = "0";
			pareja4Set2A.clear();
			pareja4Set3A.clear();
		} else if (pareja4Set1A.getText().length() != 0) {
			pareja4ASet1 = pareja4Set1A.getText();
			if (pareja4Set2A.getText().length() == 0) {
				pareja4ASet1 = "0";
				pareja4ASet2 = "0";
				pareja4ASet3 = "0";
				pareja4Set1A.clear();
				pareja4Set3A.clear();
			} else {
				pareja4ASet2 = pareja4Set2A.getText();
			}
			if (pareja4Set3A.getText().length() == 0) {
				pareja4ASet3 = "0";
			} else {
				pareja4ASet3 = pareja4Set3A.getText();
			}
		} else {
			pareja4ASet3 = pareja4Set3A.getText();
		}
		/*------------------------------------------------------------------------------*/
		if (pareja5Set1A.getText().length() == 0) {
			pareja5ASet1 = "0";
			pareja5ASet2 = "0";
			pareja5ASet3 = "0";
			pareja5Set2A.clear();
			pareja5Set3A.clear();
		} else if (pareja5Set1A.getText().length() != 0) {
			pareja5ASet1 = pareja5Set1A.getText();
			if (pareja5Set2A.getText().length() == 0) {
				pareja5ASet1 = "0";
				pareja5ASet2 = "0";
				pareja5ASet3 = "0";
				pareja5Set1A.clear();
				pareja5Set3A.clear();
			} else {
				pareja5ASet2 = pareja5Set2A.getText();
			}
			if (pareja5Set3A.getText().length() == 0) {
				pareja5ASet3 = "0";
			} else {
				pareja5ASet3 = pareja5Set3A.getText();
			}
		} else {
			pareja5ASet3 = pareja5Set3A.getText();
		}
		juegosParejaLocal1 = Integer.parseInt(pareja1ASet1) + Integer.parseInt(pareja1ASet2)
				+ Integer.parseInt(pareja1ASet3);
		juegosParejaLocal2 = Integer.parseInt(pareja2ASet1) + Integer.parseInt(pareja2ASet2)
				+ Integer.parseInt(pareja2ASet3);
		juegosParejaLocal3 = Integer.parseInt(pareja3ASet1) + Integer.parseInt(pareja3ASet2)
				+ Integer.parseInt(pareja3ASet3);
		juegosParejaLocal4 = Integer.parseInt(pareja4ASet1) + Integer.parseInt(pareja4ASet2)
				+ Integer.parseInt(pareja4ASet3);
		juegosParejaLocal5 = Integer.parseInt(pareja5ASet1) + Integer.parseInt(pareja5ASet2)
				+ Integer.parseInt(pareja5ASet3);
		juegosTotalesLocal = juegosParejaLocal1 + juegosParejaLocal2 + juegosParejaLocal3 + juegosParejaLocal4
				+ juegosParejaLocal5;
	}

	@FXML
	public void borrar(ActionEvent event) {
		pareja1CheckG.setSelected(false);
		pareja1CheckP.setSelected(false);
		pareja2CheckG.setSelected(false);
		pareja2CheckP.setSelected(false);
		pareja3CheckG.setSelected(false);
		pareja3CheckP.setSelected(false);
		pareja4CheckG.setSelected(false);
		pareja4CheckP.setSelected(false);
		pareja5CheckG.setSelected(false);
		pareja5CheckP.setSelected(false);

		pareja1Set1A.clear();
		pareja1Set1B.clear();
		pareja1Set2A.clear();
		pareja1Set2B.clear();
		pareja1Set3A.clear();
		pareja1Set3B.clear();
		pareja2Set1A.clear();
		pareja2Set1B.clear();
		pareja2Set2A.clear();
		pareja2Set2B.clear();
		pareja2Set3A.clear();
		pareja2Set3B.clear();
		pareja3Set1A.clear();
		pareja3Set1B.clear();
		pareja3Set2A.clear();
		pareja3Set2B.clear();
		pareja3Set3A.clear();
		pareja3Set3B.clear();
		pareja4Set1A.clear();
		pareja4Set1B.clear();
		pareja4Set2A.clear();
		pareja4Set2B.clear();
		pareja4Set3A.clear();
		pareja4Set3B.clear();
		pareja5Set1A.clear();
		pareja5Set1B.clear();
		pareja5Set2A.clear();
		pareja5Set2B.clear();
		pareja5Set3A.clear();
		pareja5Set3B.clear();

		juegosGanadosLocal.setText("");
		juegosGanadosVisitante.setText("");
		setsGanadosLocal.setText("");
		setsGanadosVisitante.setText("");
		partidosGanadosLocal.setText("");
		partidosGanadosVisitante.setText("");
	}

	@FXML
	public void guardarJornada(ActionEvent event) {

		Alert guardarJornada = new Alert(AlertType.CONFIRMATION);
		guardarJornada.setTitle("Finalizar jornada");
		guardarJornada.setHeaderText("¿Desea finalizar la jornada con estos datos?");
		DialogPane dialogo3 = guardarJornada.getDialogPane();
		dialogo3.getStylesheets().add(getClass().getResource("/estilos/Estilos.css").toExternalForm());
		dialogo3.getStyleClass().add("myDialog");
		Image image = new Image("@../../imagenes/logoAzul.PNG");
		ImageView imageView = new ImageView(image);
		guardarJornada.setGraphic(imageView);
		Stage stage = new Stage();
		stage = (Stage) guardarJornada.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));

		Optional<ButtonType> resultado = guardarJornada.showAndWait();
		if (resultado.get() == ButtonType.OK) {
			if ((pareja1CheckG.isSelected() || pareja1CheckP.isSelected())
					&& (pareja2CheckG.isSelected() || pareja2CheckP.isSelected())
					&& (pareja3CheckG.isSelected() || pareja3CheckP.isSelected())
					&& (pareja4CheckG.isSelected() || pareja4CheckP.isSelected())
					&& (pareja5CheckG.isSelected() || pareja5CheckP.isSelected())) {

				partido1.setIdPartido(listaPartidos.get(0).getIdPartido());
				partido1.setNombre(listaPartidos.get(0).getNombre());
				partido1.setEstado(false);
				partido1.setPrimerSetlocal(Integer.parseInt(pareja1ASet1));
				partido1.setPrimerSetVisitante(Integer.parseInt(pareja1BSet1));
				partido1.setSegundoSetlocal(Integer.parseInt(pareja1ASet2));
				partido1.setSegundoSetVisitante(Integer.parseInt(pareja1BSet2));
				partido1.setTercerSetlocal(Integer.parseInt(pareja1ASet3));
				partido1.setTercerSetVisitante(Integer.parseInt(pareja1BSet3));
				if ((setsPareja1Local > setsPareja1Visitante || pareja1CheckG.isSelected())
						&& equipoActivo.getIdEquipo() == equipoLocalNombre.getIdEquipo()) {
					partido1.setResultado("Ganado");
				} else if ((setsPareja1Local < setsPareja1Visitante || pareja1CheckP.isSelected())
						&& equipoActivo.getIdEquipo() == equipoLocalNombre.getIdEquipo()) {
					partido1.setResultado("Perdido");
				} else if ((setsPareja1Local > setsPareja1Visitante || pareja1CheckP.isSelected())
						&& equipoActivo.getIdEquipo() != equipoLocalNombre.getIdEquipo()) {
					partido1.setResultado("Perdido");
				} else if ((setsPareja1Local < setsPareja1Visitante || pareja1CheckG.isSelected())
						&& equipoActivo.getIdEquipo() != equipoLocalNombre.getIdEquipo()) {
					partido1.setResultado("Ganado");
				}
				partido1.setJornada(jornada);
				partido1.setJugador1(listaPartidos.get(0).getJugador1());
				partido1.setJugador2(listaPartidos.get(0).getJugador2());
				partidosDao.modificarPartido(partido1);
				/*------------------------------------------------------------------------------*/
				partido2.setIdPartido(listaPartidos.get(1).getIdPartido());
				partido2.setNombre(listaPartidos.get(1).getNombre());
				partido2.setEstado(false);
				partido2.setPrimerSetlocal(Integer.parseInt(pareja2ASet1));
				partido2.setPrimerSetVisitante(Integer.parseInt(pareja2BSet1));
				partido2.setSegundoSetlocal(Integer.parseInt(pareja2ASet2));
				partido2.setSegundoSetVisitante(Integer.parseInt(pareja2BSet2));
				partido2.setTercerSetlocal(Integer.parseInt(pareja2ASet3));
				partido2.setTercerSetVisitante(Integer.parseInt(pareja2BSet3));
				if ((setsPareja2Local > setsPareja2Visitante || pareja2CheckG.isSelected())
						&& equipoActivo.getIdEquipo() == equipoLocalNombre.getIdEquipo()) {
					partido2.setResultado("Ganado");
				} else if ((setsPareja2Local < setsPareja2Visitante || pareja2CheckP.isSelected())
						&& equipoActivo.getIdEquipo() == equipoLocalNombre.getIdEquipo()) {
					partido2.setResultado("Perdido");
				} else if ((setsPareja2Local > setsPareja2Visitante || pareja2CheckP.isSelected())
						&& equipoActivo.getIdEquipo() != equipoLocalNombre.getIdEquipo()) {
					partido2.setResultado("Perdido");
				} else if ((setsPareja2Local < setsPareja2Visitante || pareja2CheckG.isSelected())
						&& equipoActivo.getIdEquipo() != equipoLocalNombre.getIdEquipo()) {
					partido2.setResultado("Ganado");
				}
				partido2.setJornada(jornada);
				partido2.setJugador1(listaPartidos.get(1).getJugador1());
				partido2.setJugador2(listaPartidos.get(1).getJugador2());
				partidosDao.modificarPartido(partido2);
				/*------------------------------------------------------------------------------*/
				partido3.setIdPartido(listaPartidos.get(2).getIdPartido());
				partido3.setNombre(listaPartidos.get(2).getNombre());
				partido3.setEstado(false);
				partido3.setPrimerSetlocal(Integer.parseInt(pareja3ASet1));
				partido3.setPrimerSetVisitante(Integer.parseInt(pareja3BSet1));
				partido3.setSegundoSetlocal(Integer.parseInt(pareja3ASet2));
				partido3.setSegundoSetVisitante(Integer.parseInt(pareja3BSet2));
				partido3.setTercerSetlocal(Integer.parseInt(pareja3ASet3));
				partido3.setTercerSetVisitante(Integer.parseInt(pareja3BSet3));
				if ((setsPareja3Local > setsPareja3Visitante || pareja3CheckG.isSelected())
						&& equipoActivo.getIdEquipo() == equipoLocalNombre.getIdEquipo()) {
					partido3.setResultado("Ganado");
				} else if ((setsPareja3Local < setsPareja3Visitante || pareja3CheckP.isSelected())
						&& equipoActivo.getIdEquipo() == equipoLocalNombre.getIdEquipo()) {
					partido3.setResultado("Perdido");
				} else if ((setsPareja3Local > setsPareja3Visitante || pareja3CheckP.isSelected())
						&& equipoActivo.getIdEquipo() != equipoLocalNombre.getIdEquipo()) {
					partido3.setResultado("Perdido");
				} else if ((setsPareja3Local < setsPareja3Visitante || pareja3CheckG.isSelected())
						&& equipoActivo.getIdEquipo() != equipoLocalNombre.getIdEquipo()) {
					partido3.setResultado("Ganado");
				}
				partido3.setJornada(jornada);
				partido3.setJugador1(listaPartidos.get(2).getJugador1());
				partido3.setJugador2(listaPartidos.get(2).getJugador2());
				partidosDao.modificarPartido(partido3);
				/*------------------------------------------------------------------------------*/
				partido4.setIdPartido(listaPartidos.get(3).getIdPartido());
				partido4.setNombre(listaPartidos.get(3).getNombre());
				partido4.setEstado(false);
				partido4.setPrimerSetlocal(Integer.parseInt(pareja4ASet1));
				partido4.setPrimerSetVisitante(Integer.parseInt(pareja4BSet1));
				partido4.setSegundoSetlocal(Integer.parseInt(pareja4ASet2));
				partido4.setSegundoSetVisitante(Integer.parseInt(pareja4BSet2));
				partido4.setTercerSetlocal(Integer.parseInt(pareja4ASet3));
				partido4.setTercerSetVisitante(Integer.parseInt(pareja4BSet3));
				if ((setsPareja4Local > setsPareja4Visitante || pareja4CheckG.isSelected())
						&& equipoActivo.getIdEquipo() == equipoLocalNombre.getIdEquipo()) {
					partido4.setResultado("Ganado");
				} else if ((setsPareja4Local < setsPareja4Visitante || pareja4CheckP.isSelected())
						&& equipoActivo.getIdEquipo() == equipoLocalNombre.getIdEquipo()) {
					partido4.setResultado("Perdido");
				} else if ((setsPareja4Local > setsPareja4Visitante || pareja4CheckP.isSelected())
						&& equipoActivo.getIdEquipo() != equipoLocalNombre.getIdEquipo()) {
					partido4.setResultado("Perdido");
				} else if ((setsPareja4Local < setsPareja4Visitante || pareja4CheckG.isSelected())
						&& equipoActivo.getIdEquipo() != equipoLocalNombre.getIdEquipo()) {
					partido4.setResultado("Ganado");
				}
				partido4.setJornada(jornada);
				partido4.setJugador1(listaPartidos.get(3).getJugador1());
				partido4.setJugador2(listaPartidos.get(3).getJugador2());
				partidosDao.modificarPartido(partido4);
				/*------------------------------------------------------------------------------*/
				partido5.setIdPartido(listaPartidos.get(4).getIdPartido());
				partido5.setNombre(listaPartidos.get(4).getNombre());
				partido5.setEstado(false);
				partido5.setPrimerSetlocal(Integer.parseInt(pareja5ASet1));
				partido5.setPrimerSetVisitante(Integer.parseInt(pareja5BSet1));
				partido5.setSegundoSetlocal(Integer.parseInt(pareja5ASet2));
				partido5.setSegundoSetVisitante(Integer.parseInt(pareja5BSet2));
				partido5.setTercerSetlocal(Integer.parseInt(pareja5ASet3));
				partido5.setTercerSetVisitante(Integer.parseInt(pareja5BSet3));
				if ((setsPareja5Local > setsPareja5Visitante || pareja5CheckG.isSelected())
						&& equipoActivo.getIdEquipo() == equipoLocalNombre.getIdEquipo()) {
					partido5.setResultado("Ganado");
				} else if ((setsPareja5Local < setsPareja5Visitante || pareja5CheckP.isSelected())
						&& equipoActivo.getIdEquipo() == equipoLocalNombre.getIdEquipo()) {
					partido5.setResultado("Perdido");
				} else if ((setsPareja5Local > setsPareja5Visitante || pareja5CheckP.isSelected())
						&& equipoActivo.getIdEquipo() != equipoLocalNombre.getIdEquipo()) {
					partido5.setResultado("Perdido");
				} else if ((setsPareja5Local < setsPareja5Visitante || pareja5CheckG.isSelected())
						&& equipoActivo.getIdEquipo() != equipoLocalNombre.getIdEquipo()) {
					partido5.setResultado("Ganado");
				}
				partido5.setJornada(jornada);
				partido5.setJugador1(listaPartidos.get(4).getJugador1());
				partido5.setJugador2(listaPartidos.get(4).getJugador2());
				partidosDao.modificarPartido(partido5);
				/*------------------------------------------------------------------------------*/
				cerrarJornada.setIdJornada(jornada.getIdJornada());
				cerrarJornada.setNombre(jornada.getNombre());
				cerrarJornada.setEstado("Finalizada");
				cerrarJornada.setEquipoLocal(jornada.getEquipoLocal());
				cerrarJornada.setEquipoVisitante(jornada.getEquipoVisitante());
				cerrarJornada.setFecha(jornada.getFecha());
				cerrarTemporada.setIdTemporada(temporada.getIdTemporada());
				cerrarTemporada.setNombre(temporada.getNombre());
				cerrarTemporada.setCategoria(temporada.getCategoria());
				cerrarTemporada.setDivision(temporada.getDivision());
				cerrarTemporada.setGrupo(temporada.getGrupo());
				cerrarTemporada.setEstado(temporada.isEstado());
				cerrarTemporada.setNumJornadas(temporada.getNumJornadas());
				cerrarTemporada.setEquipos(temporada.getEquipos());
				cerrarJornada.setTemporada(cerrarTemporada);
				jornadasDao.registrarJornada(cerrarJornada);
				List<Jornada> jornadasPendientes = jornadasDao.consultarJornadasPorTemporada(temporada.getIdTemporada());
				if(jornadasPendientes.size() == 0) {
					cerrarTemporada.setEstado(false);
					temporadasDao.modificarTemporada(cerrarTemporada);
					Alert finTemporada = new Alert(AlertType.INFORMATION);
					finTemporada.setContentText("La Temporada " + cerrarTemporada.getNombre() + " a finalizado");
					DialogPane dialogo = finTemporada.getDialogPane();
					dialogo.getStylesheets().add(getClass().getResource("/estilos/Estilos.css").toExternalForm());
					dialogo.getStyleClass().add("myDialog");
					Image imagenError = new Image("@../../imagenes/logoAzul.png");
					ImageView imageViewError = new ImageView(imagenError);
					finTemporada.setGraphic(imageViewError);
					Stage stageErrorJornada = new Stage();
					stageErrorJornada = (Stage) finTemporada.getDialogPane().getScene().getWindow();
					stageErrorJornada.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
					finTemporada.showAndWait();
					
				}

				Node source = (Node) event.getSource();
				Stage stagePrincipal = (Stage) source.getScene().getWindow();
				stagePrincipal.close();
			} else {
				Alert errorCerrarJornada = new Alert(AlertType.ERROR);
				errorCerrarJornada.setContentText("Falta el resultado de algún partido");
				DialogPane dialogo = errorCerrarJornada.getDialogPane();
				dialogo.getStylesheets().add(getClass().getResource("/estilos/Estilos.css").toExternalForm());
				dialogo.getStyleClass().add("myDialog");
				Image imagenError = new Image("@../../imagenes/logoAzul.png");
				ImageView imageViewError = new ImageView(imagenError);
				errorCerrarJornada.setGraphic(imageViewError);
				Stage stageErrorJornada = new Stage();
				stageErrorJornada = (Stage) errorCerrarJornada.getDialogPane().getScene().getWindow();
				stageErrorJornada.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
				errorCerrarJornada.showAndWait();
			}

		}

	}

	@FXML
	public void volver(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();

	}

	private TextFormatter<String> formateadorJuegos() {
		TextFormatter<String> formatter = new TextFormatter<String>(change -> {
			String text = change.getControlNewText();
			if (!Pattern.matches(regex, text)) {
				change.setText("");
			}
			return change;
		});
		return formatter;
	}

	private void juegosFormateados() {

		pareja1Set1A.setTextFormatter(formateadorJuegos());
		pareja1Set2A.setTextFormatter(formateadorJuegos());
		pareja1Set3A.setTextFormatter(formateadorJuegos());
		pareja1Set1B.setTextFormatter(formateadorJuegos());
		pareja1Set2B.setTextFormatter(formateadorJuegos());
		pareja1Set3B.setTextFormatter(formateadorJuegos());

		pareja2Set1A.setTextFormatter(formateadorJuegos());
		pareja2Set2A.setTextFormatter(formateadorJuegos());
		pareja2Set3A.setTextFormatter(formateadorJuegos());
		pareja2Set1B.setTextFormatter(formateadorJuegos());
		pareja2Set2B.setTextFormatter(formateadorJuegos());
		pareja2Set3B.setTextFormatter(formateadorJuegos());

		pareja3Set1A.setTextFormatter(formateadorJuegos());
		pareja3Set2A.setTextFormatter(formateadorJuegos());
		pareja3Set3A.setTextFormatter(formateadorJuegos());
		pareja3Set1B.setTextFormatter(formateadorJuegos());
		pareja3Set2B.setTextFormatter(formateadorJuegos());
		pareja3Set3B.setTextFormatter(formateadorJuegos());

		pareja4Set1A.setTextFormatter(formateadorJuegos());
		pareja4Set2A.setTextFormatter(formateadorJuegos());
		pareja4Set3A.setTextFormatter(formateadorJuegos());
		pareja4Set1B.setTextFormatter(formateadorJuegos());
		pareja4Set2B.setTextFormatter(formateadorJuegos());
		pareja4Set3B.setTextFormatter(formateadorJuegos());

		pareja5Set1A.setTextFormatter(formateadorJuegos());
		pareja5Set2A.setTextFormatter(formateadorJuegos());
		pareja5Set3A.setTextFormatter(formateadorJuegos());
		pareja5Set1B.setTextFormatter(formateadorJuegos());
		pareja5Set2B.setTextFormatter(formateadorJuegos());
		pareja5Set3B.setTextFormatter(formateadorJuegos());
	}

	private void crearActaLocal() {

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			String path = ControladorJornadaEnJuego.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decodedPath = URLDecoder.decode(path, "UTF-8");
			Document doc = builder.parse(new File(decodedPath + "\\acta\\acta.xml"));

			NodeList items = doc.getElementsByTagName("w:t");
			for (int ix = 0; ix < items.getLength(); ix++) {
				Element element = (Element) items.item(ix);

				if (element.getAttribute("id").equalsIgnoreCase("guty1")) {
					element.setTextContent(equipoLocalNombre.getDivision() + " " + equipoLocalNombre.getGrupo());

				} else if (element.getAttribute("id").equalsIgnoreCase("guty2")) {
					element.setTextContent(equipoLocalNombre.getNombre());

				} else if (element.getAttribute("id").equalsIgnoreCase("guty3")) {
					element.setTextContent(equipoVisitanteNombre.getNombre());
					/*--------------------------------------------------------------------------------------------------*/
				} else if (element.getAttribute("id").equalsIgnoreCase("guty4")) {
					element.setTextContent(Integer.toString(listaPartidos.get(0).getJugador1().getLicencia()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty14")) {
					element.setTextContent(listaPartidos.get(0).getJugador1().getNombre() + " "
							+ listaPartidos.get(0).getJugador1().getPrimerApellido());

				} else if (element.getAttribute("id").equalsIgnoreCase("guty24")) {
					element.setTextContent(Integer.toString(listaPartidos.get(0).getJugador1().getPuntos()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty5")) {
					element.setTextContent(Integer.toString(listaPartidos.get(0).getJugador2().getLicencia()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty15")) {
					element.setTextContent(listaPartidos.get(0).getJugador2().getNombre() + " "
							+ listaPartidos.get(0).getJugador2().getPrimerApellido());

				} else if (element.getAttribute("id").equalsIgnoreCase("guty25")) {
					element.setTextContent(Integer.toString(listaPartidos.get(0).getJugador2().getPuntos()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty34")) {
					element.setTextContent(Integer.toString(totalPuntosPareja1));
					/*--------------------------------------------------------------------------------------------------*/
				} else if (element.getAttribute("id").equalsIgnoreCase("guty6")) {
					element.setTextContent(Integer.toString(listaPartidos.get(1).getJugador1().getLicencia()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty16")) {
					element.setTextContent(listaPartidos.get(1).getJugador1().getNombre() + " "
							+ listaPartidos.get(1).getJugador1().getPrimerApellido());

				} else if (element.getAttribute("id").equalsIgnoreCase("guty26")) {
					element.setTextContent(Integer.toString(listaPartidos.get(1).getJugador1().getPuntos()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty7")) {
					element.setTextContent(Integer.toString(listaPartidos.get(1).getJugador2().getLicencia()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty17")) {
					element.setTextContent(listaPartidos.get(1).getJugador2().getNombre() + " "
							+ listaPartidos.get(1).getJugador2().getPrimerApellido());

				} else if (element.getAttribute("id").equalsIgnoreCase("guty27")) {
					element.setTextContent(Integer.toString(listaPartidos.get(1).getJugador2().getPuntos()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty35")) {
					element.setTextContent(Integer.toString(totalPuntosPareja2));
					/*--------------------------------------------------------------------------------------------------*/
				} else if (element.getAttribute("id").equalsIgnoreCase("guty8")) {
					element.setTextContent(Integer.toString(listaPartidos.get(2).getJugador1().getLicencia()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty18")) {
					element.setTextContent(listaPartidos.get(2).getJugador1().getNombre() + " "
							+ listaPartidos.get(2).getJugador1().getPrimerApellido());

				} else if (element.getAttribute("id").equalsIgnoreCase("guty28")) {
					element.setTextContent(Integer.toString(listaPartidos.get(2).getJugador1().getPuntos()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty9")) {
					element.setTextContent(Integer.toString(listaPartidos.get(2).getJugador2().getLicencia()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty19")) {
					element.setTextContent(listaPartidos.get(2).getJugador2().getNombre() + " "
							+ listaPartidos.get(2).getJugador2().getPrimerApellido());

				} else if (element.getAttribute("id").equalsIgnoreCase("guty29")) {
					element.setTextContent(Integer.toString(listaPartidos.get(2).getJugador2().getPuntos()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty36")) {
					element.setTextContent(Integer.toString(totalPuntosPareja3));
					/*--------------------------------------------------------------------------------------------------*/
				} else if (element.getAttribute("id").equalsIgnoreCase("guty10")) {
					element.setTextContent(Integer.toString(listaPartidos.get(3).getJugador1().getLicencia()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty20")) {
					element.setTextContent(listaPartidos.get(3).getJugador1().getNombre() + " "
							+ listaPartidos.get(3).getJugador1().getPrimerApellido());

				} else if (element.getAttribute("id").equalsIgnoreCase("guty30")) {
					element.setTextContent(Integer.toString(listaPartidos.get(3).getJugador1().getPuntos()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty11")) {
					element.setTextContent(Integer.toString(listaPartidos.get(3).getJugador2().getLicencia()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty21")) {
					element.setTextContent(listaPartidos.get(3).getJugador2().getNombre() + " "
							+ listaPartidos.get(3).getJugador2().getPrimerApellido());

				} else if (element.getAttribute("id").equalsIgnoreCase("guty31")) {
					element.setTextContent(Integer.toString(listaPartidos.get(3).getJugador2().getPuntos()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty37")) {
					element.setTextContent(Integer.toString(totalPuntosPareja4));
					/*--------------------------------------------------------------------------------------------------*/
				} else if (element.getAttribute("id").equalsIgnoreCase("guty12")) {
					element.setTextContent(Integer.toString(listaPartidos.get(4).getJugador1().getLicencia()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty22")) {
					element.setTextContent(listaPartidos.get(4).getJugador1().getNombre() + " "
							+ listaPartidos.get(4).getJugador1().getPrimerApellido());

				} else if (element.getAttribute("id").equalsIgnoreCase("guty32")) {
					element.setTextContent(Integer.toString(listaPartidos.get(4).getJugador1().getPuntos()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty13")) {
					element.setTextContent(Integer.toString(listaPartidos.get(4).getJugador2().getLicencia()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty23")) {
					element.setTextContent(listaPartidos.get(4).getJugador2().getNombre() + " "
							+ listaPartidos.get(4).getJugador2().getPrimerApellido());

				} else if (element.getAttribute("id").equalsIgnoreCase("guty33")) {
					element.setTextContent(Integer.toString(listaPartidos.get(4).getJugador2().getPuntos()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty38")) {
					element.setTextContent(Integer.toString(totalPuntosPareja5));
					/*--------------------------------------------------------------------------------------*/
				} else if (element.getAttribute("id").equalsIgnoreCase("guty39")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty49")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty59")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty40")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty50")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty60")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty69")) {
					element.setTextContent("");
					/*--------------------------------------------------------------------------------------------------*/
				} else if (element.getAttribute("id").equalsIgnoreCase("guty41")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty51")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty61")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty42")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty52")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty62")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty70")) {
					element.setTextContent("");
					/*--------------------------------------------------------------------------------------------------*/
				} else if (element.getAttribute("id").equalsIgnoreCase("guty43")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty53")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty63")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty44")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty54")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty64")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty71")) {
					element.setTextContent("");
					/*--------------------------------------------------------------------------------------------------*/
				} else if (element.getAttribute("id").equalsIgnoreCase("guty45")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty55")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty65")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty46")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty56")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty66")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty72")) {
					element.setTextContent("");
					/*--------------------------------------------------------------------------------------------------*/
				} else if (element.getAttribute("id").equalsIgnoreCase("guty47")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty57")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty67")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty48")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty58")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty68")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty73")) {
					element.setTextContent("");
				}
			}

			DirectoryChooser fileChooser = new DirectoryChooser();

			String userDirectoryString = System.getProperty("user.home");
			File userDirectory = new File(userDirectoryString);
			if (!userDirectory.canRead()) {
				userDirectory = new File("c:/");
			}
			fileChooser.setInitialDirectory(userDirectory);

			File chosenFile = fileChooser.showDialog(this.stage);

			String pathToSave;
			if (chosenFile != null) {
				pathToSave = chosenFile.getPath();
			} else {

				pathToSave = null;
			}

			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			Result output = new StreamResult(new File(pathToSave + "\\" + jornada.getNombre() + ".xml"));
			Source input = new DOMSource(doc);
			transformer.transform(input, output);

			Alert actaOk = new Alert(AlertType.CONFIRMATION);
			actaOk.setContentText("Archivo generado correctamente");
			DialogPane dialogo = actaOk.getDialogPane();
			dialogo.getStylesheets().add(getClass().getResource("/estilos/Estilos.css").toExternalForm());
			dialogo.getStyleClass().add("myDialog");
			Image imagenError = new Image("@../../imagenes/logoAzul.png");
			ImageView imageViewError = new ImageView(imagenError);
			actaOk.setGraphic(imageViewError);
			Stage stage = new Stage();
			stage = (Stage) actaOk.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
			actaOk.showAndWait();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void crearActaVisitante() {

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			String path = ControladorJornadaEnJuego.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decodedPath = URLDecoder.decode(path, "UTF-8");
			Document doc = builder.parse(new File(decodedPath + "\\acta\\acta.xml"));

			NodeList items = doc.getElementsByTagName("w:t");
			for (int ix = 0; ix < items.getLength(); ix++) {
				Element element = (Element) items.item(ix);

				if (element.getAttribute("id").equalsIgnoreCase("guty1")) {
					element.setTextContent(
							equipoVisitanteNombre.getDivision() + " " + equipoVisitanteNombre.getGrupo());

				} else if (element.getAttribute("id").equalsIgnoreCase("guty2")) {
					element.setTextContent(equipoLocalNombre.getNombre());

				} else if (element.getAttribute("id").equalsIgnoreCase("guty3")) {
					element.setTextContent(equipoVisitanteNombre.getNombre());
					/*--------------------------------------------------------------------------------------------------*/
				} else if (element.getAttribute("id").equalsIgnoreCase("guty39")) {
					element.setTextContent(Integer.toString(listaPartidos.get(0).getJugador1().getLicencia()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty49")) {
					element.setTextContent(listaPartidos.get(0).getJugador1().getNombre() + " "
							+ listaPartidos.get(0).getJugador1().getPrimerApellido());

				} else if (element.getAttribute("id").equalsIgnoreCase("guty59")) {
					element.setTextContent(Integer.toString(listaPartidos.get(0).getJugador1().getPuntos()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty40")) {
					element.setTextContent(Integer.toString(listaPartidos.get(0).getJugador2().getLicencia()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty50")) {
					element.setTextContent(listaPartidos.get(0).getJugador2().getNombre() + " "
							+ listaPartidos.get(0).getJugador2().getPrimerApellido());

				} else if (element.getAttribute("id").equalsIgnoreCase("guty60")) {
					element.setTextContent(Integer.toString(listaPartidos.get(0).getJugador2().getPuntos()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty69")) {
					element.setTextContent(Integer.toString(totalPuntosPareja1));
					/*--------------------------------------------------------------------------------------------------*/
				} else if (element.getAttribute("id").equalsIgnoreCase("guty41")) {
					element.setTextContent(Integer.toString(listaPartidos.get(1).getJugador1().getLicencia()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty51")) {
					element.setTextContent(listaPartidos.get(1).getJugador1().getNombre() + " "
							+ listaPartidos.get(1).getJugador1().getPrimerApellido());

				} else if (element.getAttribute("id").equalsIgnoreCase("guty61")) {
					element.setTextContent(Integer.toString(listaPartidos.get(1).getJugador1().getPuntos()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty42")) {
					element.setTextContent(Integer.toString(listaPartidos.get(1).getJugador2().getLicencia()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty52")) {
					element.setTextContent(listaPartidos.get(1).getJugador2().getNombre() + " "
							+ listaPartidos.get(1).getJugador2().getPrimerApellido());

				} else if (element.getAttribute("id").equalsIgnoreCase("guty62")) {
					element.setTextContent(Integer.toString(listaPartidos.get(1).getJugador2().getPuntos()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty70")) {
					element.setTextContent(Integer.toString(totalPuntosPareja2));
					/*--------------------------------------------------------------------------------------------------*/
				} else if (element.getAttribute("id").equalsIgnoreCase("guty43")) {
					element.setTextContent(Integer.toString(listaPartidos.get(2).getJugador1().getLicencia()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty53")) {
					element.setTextContent(listaPartidos.get(2).getJugador1().getNombre() + " "
							+ listaPartidos.get(2).getJugador1().getPrimerApellido());

				} else if (element.getAttribute("id").equalsIgnoreCase("guty63")) {
					element.setTextContent(Integer.toString(listaPartidos.get(2).getJugador1().getPuntos()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty44")) {
					element.setTextContent(Integer.toString(listaPartidos.get(2).getJugador2().getLicencia()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty54")) {
					element.setTextContent(listaPartidos.get(2).getJugador2().getNombre() + " "
							+ listaPartidos.get(2).getJugador2().getPrimerApellido());

				} else if (element.getAttribute("id").equalsIgnoreCase("guty64")) {
					element.setTextContent(Integer.toString(listaPartidos.get(2).getJugador2().getPuntos()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty71")) {
					element.setTextContent(Integer.toString(totalPuntosPareja3));
					/*--------------------------------------------------------------------------------------------------*/
				} else if (element.getAttribute("id").equalsIgnoreCase("guty45")) {
					element.setTextContent(Integer.toString(listaPartidos.get(3).getJugador1().getLicencia()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty55")) {
					element.setTextContent(listaPartidos.get(3).getJugador1().getNombre() + " "
							+ listaPartidos.get(3).getJugador1().getPrimerApellido());

				} else if (element.getAttribute("id").equalsIgnoreCase("guty65")) {
					element.setTextContent(Integer.toString(listaPartidos.get(3).getJugador1().getPuntos()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty46")) {
					element.setTextContent(Integer.toString(listaPartidos.get(3).getJugador2().getLicencia()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty56")) {
					element.setTextContent(listaPartidos.get(3).getJugador2().getNombre() + " "
							+ listaPartidos.get(3).getJugador2().getPrimerApellido());

				} else if (element.getAttribute("id").equalsIgnoreCase("guty66")) {
					element.setTextContent(Integer.toString(listaPartidos.get(3).getJugador2().getPuntos()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty72")) {
					element.setTextContent(Integer.toString(totalPuntosPareja4));
					/*--------------------------------------------------------------------------------------------------*/
				} else if (element.getAttribute("id").equalsIgnoreCase("guty47")) {
					element.setTextContent(Integer.toString(listaPartidos.get(4).getJugador1().getLicencia()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty57")) {
					element.setTextContent(listaPartidos.get(4).getJugador1().getNombre() + " "
							+ listaPartidos.get(4).getJugador1().getPrimerApellido());

				} else if (element.getAttribute("id").equalsIgnoreCase("guty67")) {
					element.setTextContent(Integer.toString(listaPartidos.get(4).getJugador1().getPuntos()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty48")) {
					element.setTextContent(Integer.toString(listaPartidos.get(4).getJugador2().getLicencia()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty58")) {
					element.setTextContent(listaPartidos.get(4).getJugador2().getNombre() + " "
							+ listaPartidos.get(4).getJugador2().getPrimerApellido());

				} else if (element.getAttribute("id").equalsIgnoreCase("guty68")) {
					element.setTextContent(Integer.toString(listaPartidos.get(4).getJugador2().getPuntos()));

				} else if (element.getAttribute("id").equalsIgnoreCase("guty73")) {
					element.setTextContent(Integer.toString(totalPuntosPareja5));
					/*--------------------------------------------------------------------------------------------------*/
				} else if (element.getAttribute("id").equalsIgnoreCase("guty4")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty14")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty24")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty5")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty15")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty25")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty34")) {
					element.setTextContent("");
					/*--------------------------------------------------------------------------------------------------*/
				} else if (element.getAttribute("id").equalsIgnoreCase("guty6")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty16")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty26")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty7")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty17")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty27")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty35")) {
					element.setTextContent("");
					/*--------------------------------------------------------------------------------------------------*/
				} else if (element.getAttribute("id").equalsIgnoreCase("guty8")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty18")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty28")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty9")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty19")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty29")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty36")) {
					element.setTextContent("");
					/*--------------------------------------------------------------------------------------------------*/
				} else if (element.getAttribute("id").equalsIgnoreCase("guty10")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty20")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty30")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty11")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty21")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty31")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty37")) {
					element.setTextContent("");
					/*--------------------------------------------------------------------------------------------------*/
				} else if (element.getAttribute("id").equalsIgnoreCase("guty12")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty22")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty32")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty13")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty23")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty33")) {
					element.setTextContent("");

				} else if (element.getAttribute("id").equalsIgnoreCase("guty38")) {
					element.setTextContent("");
				}
			}
			DirectoryChooser fileChooser = new DirectoryChooser();

			String userDirectoryString = System.getProperty("user.home");
			File userDirectory = new File(userDirectoryString);
			if (!userDirectory.canRead()) {
				userDirectory = new File("c:/");
			}
			fileChooser.setInitialDirectory(userDirectory);

			File chosenFile = fileChooser.showDialog(this.stage);

			String pathToSave;
			if (chosenFile != null) {
				pathToSave = chosenFile.getPath();
			} else {

				pathToSave = null;
			}

			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			Result output = new StreamResult(new File(pathToSave + "\\" + jornada.getNombre() + ".xml"));
			Source input = new DOMSource(doc);
			transformer.transform(input, output);

			Alert actaOk = new Alert(AlertType.CONFIRMATION);
			actaOk.setContentText("Archivo generado correctamente");
			DialogPane dialogo = actaOk.getDialogPane();
			dialogo.getStylesheets().add(getClass().getResource("/estilos/Estilos.css").toExternalForm());
			dialogo.getStyleClass().add("myDialog");
			Image imagenError = new Image("@../../imagenes/logoAzul.png");
			ImageView imageViewError = new ImageView(imagenError);
			actaOk.setGraphic(imageViewError);
			Stage stage = new Stage();
			stage = (Stage) actaOk.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
			actaOk.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String getCuerpoEmail() {
		return cuerpoEmail;
	}

	public static void setCuerpoEmail(String cuerpoEmail) {
		ControladorJornadaEnJuego.cuerpoEmail = cuerpoEmail;
	}

	public Equipo getEquipoActivo() {
		return equipoActivo;
	}

	public void setEquipoActivo(Equipo equipoActivo) {
		this.equipoActivo = equipoActivo;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
