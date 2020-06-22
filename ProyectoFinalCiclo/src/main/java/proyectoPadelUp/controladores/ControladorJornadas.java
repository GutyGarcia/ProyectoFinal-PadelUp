package proyectoPadelUp.controladores;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import proyectoPadelUp.TablaAltaJugador;
import proyectoPadelUp.daos.EquiposDao;
import proyectoPadelUp.daos.JornadasDao;
import proyectoPadelUp.daos.JugadoresDao;
import proyectoPadelUp.daos.PartidosDao;
import proyectoPadelUp.daos.TemporadasEquiposDao;
import proyectoPadelUp.pojos.Equipo;
import proyectoPadelUp.pojos.Jornada;
import proyectoPadelUp.pojos.Jugador;
import proyectoPadelUp.pojos.ParejaPartido;
import proyectoPadelUp.pojos.Partido;
import proyectoPadelUp.pojos.Temporada;


public class ControladorJornadas implements Initializable {

	@FXML
	private TableView<TablaAltaJugador> tabla;
	@FXML
	private TableView<ParejaPartido> parejaPartido;
	@FXML
	private ComboBox<Equipo> comboLocal, comboVisitante;
	@FXML
	private ComboBox<Jornada> comboJornada;
	@FXML
	private CheckBox checkBoxLocal, checkBoxVisitante;
	@FXML
	private Button convocar, generar, validar, opcionDerecha, opcionIzquierda, actualizar, volver;
	@FXML
	private Text nombreTemporada, opciones;
	private List<List<ParejaPartido>> listaEmparejamientos;
	@FXML
	private DatePicker fechaJornada;

	private Equipo equipoActivo = new Equipo();
	private JornadasDao jornadasDao = new JornadasDao();
	private EquiposDao equiposDao = new EquiposDao();
	private JugadoresDao jugadoresDao = new JugadoresDao();
	private Jornada jornada = new Jornada();
	private Temporada temporada = new Temporada();
	private TemporadasEquiposDao temporadasEquiposDao = new TemporadasEquiposDao();
	private PartidosDao partidoDao = new PartidosDao();
	private List<TablaAltaJugador> jugadoresCargados;
	private List<Jugador> listaJugadores, opcionElegida;
	private int incrementoPareja = 0;
	private static Jornada jornadaActiva;
	private static Equipo equipoLocal, equipoVisitante;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comboJornada.valueProperty().addListener(new ChangeListener<Jornada>() {
			@Override
			public void changed(ObservableValue<? extends Jornada> observable, Jornada oldValue, Jornada newValue) {
				if (newValue != null) {
					jornadaActiva = newValue;
				}
			}
		});
		comboLocal.valueProperty().addListener(new ChangeListener<Equipo>() {
			@Override
			public void changed(ObservableValue<? extends Equipo> observable, Equipo oldValue, Equipo newValue) {
				if (newValue != null) {
					equipoLocal = newValue;
				}
			}
		});
		comboVisitante.valueProperty().addListener(new ChangeListener<Equipo>() {
			@Override
			public void changed(ObservableValue<? extends Equipo> observable, Equipo oldValue, Equipo newValue) {
				if (newValue != null) {
					equipoVisitante = newValue;
				}
			}
		});
			temporada = temporadasEquiposDao.consultarTemporadaPorEquipo(ControladorEquipos.equipoActivo.getIdEquipo());
			nombreTemporada.setText("Federación Gallega de Pádel - Temporada " + temporada.getNombre());
			cargarJornadas();
			prepararComboLocal();
			prepararComboVisitante();
		
		
	}

	public void CheckBoxes(ActionEvent event) {
		equiposDao = new EquiposDao();
		if (checkBoxLocal.isSelected()) {
			comboVisitante.valueProperty().set(null);
			comboLocal.setValue(equipoActivo);
			cargarRivalVisitante();
		} else if (checkBoxVisitante.isSelected()) {
			comboLocal.valueProperty().set(null);
			comboVisitante.setValue(equipoActivo);
			cargarRivalLocal();
		}
	}

	protected void cargarRivalLocal() {
		ObservableList<Equipo> equipos = FXCollections.observableArrayList();
		List<Equipo> listaEquipos = equiposDao.consultarRivales(equipoActivo.getIdEquipo(), equipoActivo.getDivision(), equipoActivo.getGrupo());
		equipos.addAll(listaEquipos);
		comboLocal.setItems(equipos);
	}

	protected void cargarRivalVisitante() {
		ObservableList<Equipo> equipos = FXCollections.observableArrayList();
		List<Equipo> listaEquipos = equiposDao.consultarRivales(equipoActivo.getIdEquipo(), equipoActivo.getDivision(), equipoActivo.getGrupo());
		equipos.addAll(listaEquipos);
		comboVisitante.setItems(equipos);

	}

	protected void prepararComboLocal() {
		comboLocal.setConverter(new StringConverter<Equipo>() {

			@Override
			public String toString(Equipo object) {
				return object.getNombre();
			}

			@Override
			public Equipo fromString(String string) {
				return comboLocal.getItems().stream().filter(ap -> ap.getNombre().equals(string)).findFirst()
						.orElse(null);
			}
		});
	}

	protected void prepararComboVisitante() {
		comboVisitante.setConverter(new StringConverter<Equipo>() {

			@Override
			public String toString(Equipo object) {
				return object.getNombre();
			}

			@Override
			public Equipo fromString(String string) {
				return comboVisitante.getItems().stream().filter(ap -> ap.getNombre().equals(string)).findFirst()
						.orElse(null);
			}
		});
	}

	@FXML
	protected void consultarJugadoresTabla() {

		ObservableList<TablaAltaJugador> jugadores = FXCollections.observableArrayList();

		jugadoresCargados = jugadoresDao.consultarTodosPorEquipoTabla(ControladorEquipos.equipoActivo.getIdEquipo());
		jugadores.addAll(jugadoresCargados);
		tabla.setItems(jugadores);

	}

	private void generarOpcionEquipos(List<Jugador> jugadores) {
		listaEmparejamientos = new ArrayList();
		final int numeroOpcionesAlineacion = 6;
		final int minJugadoresConvocatoria = 10;
		for (int i = 0; i < numeroOpcionesAlineacion; i++) {
			List<Jugador> listaUnUso = new ArrayList<Jugador>();
			List<Jugador> listaBuena = new ArrayList<Jugador>();
			listaUnUso.addAll(jugadores);
			for (int j = 0 ; j < minJugadoresConvocatoria; j++) {
				int aleatorio = (int) (Math.random() * listaUnUso.size()) + 0;
				listaBuena.add(listaUnUso.get(aleatorio));
				listaUnUso.remove(aleatorio);
			}
			List<ParejaPartido> emparejamiento = new ArrayList<ParejaPartido>();
			List<Integer> puntuaciones = new ArrayList<Integer>();
			for (int k = 0; k < listaBuena.size(); k = k + 2) {
				if (listaBuena.get(k + 1) != null) {
					ParejaPartido parejaPartido = new ParejaPartido(listaBuena.get(k), listaBuena.get(k + 1));
					emparejamiento.add(parejaPartido);
					puntuaciones.add(parejaPartido.getTotalPuntos());
				}
			}
					
			
			emparejamiento.sort(Comparator.comparingInt(ParejaPartido::getTotalPuntos)
                    .reversed());
			
			for (int l = 0; l < emparejamiento.size(); l++) {
				emparejamiento.get(l).setNombrePareja("Pareja" + (l + 1) );
			}	
			
			listaEmparejamientos.add(emparejamiento);
		}
	}

	protected void cargarJornadas() {
		ObservableList<Jornada> jornadas = FXCollections.observableArrayList();
		List<Jornada> listaJornadas = jornadasDao.consultarJornadasPorTemporada(temporada.getIdTemporada());
		jornadas.addAll(listaJornadas);
		comboJornada.setItems(jornadas);
		comboJornada.setConverter(new StringConverter<Jornada>() {

			@Override
			public String toString(Jornada object) {
				return object.getNombre();
			}

			@Override
			public Jornada fromString(String string) {
				return comboJornada.getItems().stream().filter(ap -> ap.getNombre().equals(string)).findFirst()
						.orElse(null);
			}
		});
	}

	@FXML
	public void pasarOpcionDerecha(ActionEvent event) {

		if (incrementoPareja >= 0 && incrementoPareja < 5) {
			incrementoPareja++;
			parejaPartido.setItems(FXCollections.observableArrayList(listaEmparejamientos.get(incrementoPareja)));
			opciones.setText("Opción " + incrementoPareja);
		}
	}

	@FXML
	public void pasarOpcionIzquierda(ActionEvent event) {
		if (incrementoPareja > 1) {
			incrementoPareja--;
			parejaPartido.setItems(FXCollections.observableArrayList(listaEmparejamientos.get(incrementoPareja)));
			opciones.setText("Opción " + incrementoPareja);
		}
	}

	@FXML
	public void consultarJugadores(ActionEvent event) {
		consultarJugadoresTabla();
	}

	@FXML
	public void generarConvocatoria(ActionEvent event) {
		incrementoPareja = 0;
		parejaPartido.getItems().clear();

		listaJugadores = jugadoresDao.consultarTodosPorEquipo(ControladorEquipos.equipoActivo.getIdEquipo());
		List<TablaAltaJugador> jugadoresConvocados = tabla.getItems();
		List<Jugador> listaVacia = new ArrayList<>();
		for (TablaAltaJugador jugadorConvocado : jugadoresConvocados) {
			if (jugadorConvocado.getConvocar().isSelected()) {
				for (Jugador jugador2 : listaJugadores) {
					if (jugadorConvocado.getIdJugador() == jugador2.getIdJugador()) {
						listaVacia.add(jugador2);
						break;
					}
				}
			}
		}
		if (listaVacia.size() >= 10 && listaVacia.size() % 2 == 0) {
			generarOpcionEquipos(listaVacia);
			pasarOpcionDerecha(event);

		} else {
			Alert errorConvocatoria = new Alert(AlertType.WARNING);
			errorConvocatoria.setContentText("Tiene que convocar un mínimo de 10 jugadores y el número tiene que ser par");
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
	@FXML
	public void validarConvocatoria(ActionEvent event) {
		try {
			if(!jornadaActiva.getEstado().equals("enJuego")) {
			jornada.setIdJornada(jornadaActiva.getIdJornada());
			jornada.setTemporada(jornadaActiva.getTemporada());
			jornada.setNombre(jornadaActiva.getNombre());
			LocalDate localDate = fechaJornada.getValue();
			jornada.setFecha(Date.valueOf(localDate));
			jornada.setEquipoLocal(equipoLocal);
			jornada.setEquipoVisitante(equipoVisitante);
			jornada.setEstado("enJuego");
			jornadasDao.registrarJornada(jornada);
			opcionElegidaTabla();

			Partido partido1 = new Partido();
			partido1.setJornada(jornada);
			partido1.setEstado(true);
			partido1.setJugador1(opcionElegida.get(0));
			partido1.setJugador2(opcionElegida.get(5));
			partido1.setNombre("Partido 1");
			partidoDao.altaPartido(partido1);

			Partido partido2 = new Partido();
			partido2.setJornada(jornada);
			partido2.setEstado(true);
			partido2.setJugador1(opcionElegida.get(1));
			partido2.setJugador2(opcionElegida.get(6));
			partido2.setNombre("Partido 2");
			partidoDao.altaPartido(partido2);

			Partido partido3 = new Partido();
			partido3.setJornada(jornada);
			partido3.setEstado(true);
			partido3.setJugador1(opcionElegida.get(2));
			partido3.setJugador2(opcionElegida.get(7));
			partido3.setNombre("Partido 3");
			partidoDao.altaPartido(partido3);

			Partido partido4 = new Partido();
			partido4.setJornada(jornada);
			partido4.setEstado(true);
			partido4.setJugador1(opcionElegida.get(3));
			partido4.setJugador2(opcionElegida.get(8));
			partido4.setNombre("Partido 4");
			partidoDao.altaPartido(partido4);

			Partido partido5 = new Partido();
			partido5.setJornada(jornada);
			partido5.setEstado(true);
			partido5.setJugador1(opcionElegida.get(4));
			partido5.setJugador2(opcionElegida.get(9));
			partido5.setNombre("Partido 5");
			partidoDao.altaPartido(partido5);

			Alert verificacion = new Alert(AlertType.INFORMATION);
			verificacion.setContentText("Convocatoria insertada correctamente");
			DialogPane dialogo = verificacion.getDialogPane();
			dialogo.getStylesheets().add(getClass().getResource("/estilos/Estilos.css").toExternalForm());
			dialogo.getStyleClass().add("myDialog");
			Image imagenError = new Image("@../../imagenes/logoAzul.png");
			ImageView imageViewError = new ImageView(imagenError);
			verificacion.setGraphic(imageViewError);
			Stage stage2 = new Stage();
			stage2 = (Stage) verificacion.getDialogPane().getScene().getWindow();
			stage2.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
			verificacion.showAndWait();
			
			jornadaActiva.setEstado("enJuego");
			Node source = (Node) event.getSource();
		    Stage stage = (Stage) source.getScene().getWindow();
			stage.close();
			
			}else {
				Alert verificacion = new Alert(AlertType.WARNING);
				verificacion.setContentText("La jornada " + jornadaActiva.getNombre() + " ya esta registrada y en juego");
				DialogPane dialogo = verificacion.getDialogPane();
				dialogo.getStylesheets().add(getClass().getResource("/estilos/Estilos.css").toExternalForm());
				dialogo.getStyleClass().add("myDialog");
				Image imagenError = new Image("@../../imagenes/logoAzul.png");
				ImageView imageViewError = new ImageView(imagenError);
				verificacion.setGraphic(imageViewError);
				Stage stage2 = new Stage();
				stage2 = (Stage) verificacion.getDialogPane().getScene().getWindow();
				stage2.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
				verificacion.showAndWait();
			}
		} catch (Exception e) {
			Alert errorConvocatoria = new Alert(AlertType.WARNING);
			errorConvocatoria.setContentText("No es posible insertar la convocatoria, revise los datos");
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

	@FXML
	public void actualizarPantalla(ActionEvent event) {
		parejaPartido.getItems().clear();
		tabla.getItems().clear();
		comboJornada.getItems().clear();
		comboLocal.getItems().clear();
		comboVisitante.getItems().clear();
		fechaJornada.getEditor().clear();
		checkBoxLocal.setSelected(false);
		checkBoxVisitante.setSelected(false);
		cargarJornadas();
		prepararComboLocal();
		prepararComboVisitante();
	}
	
	@FXML
	public void volverAtras(ActionEvent event) {
		Node source = (Node) event.getSource();
	    Stage stage = (Stage) source.getScene().getWindow();
	    stage.close();
	}

	public List<Jugador> opcionElegidaTabla() {
		List<ParejaPartido> jugadoresAlineados = parejaPartido.getItems();
		opcionElegida = new ArrayList<>();
		for (ParejaPartido jugadorAlineado : jugadoresAlineados) {
			for (Jugador jugador2 : listaJugadores) {
				if (jugadorAlineado.getIdJugador1() == jugador2.getIdJugador()) {
					opcionElegida.add(jugador2);
				}
			}
		}
		for (ParejaPartido jugadorAlineado2 : jugadoresAlineados) {
			for (Jugador jugador3 : listaJugadores) {
				if (jugadorAlineado2.getIdJugador2() == jugador3.getIdJugador()) {
					opcionElegida.add(jugador3);
				}
			}
		}
		return opcionElegida;
	}

	public Equipo getEquipoActivo() {
		return equipoActivo;
	}

	public void setEquipoActivo(Equipo equipoActivo) {
		this.equipoActivo = equipoActivo;
	}
	
	

}
