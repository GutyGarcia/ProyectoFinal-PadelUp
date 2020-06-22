package proyectoPadelUp.controladores;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import proyectoPadelUp.daos.EquiposDao;
import proyectoPadelUp.daos.JornadasDao;
import proyectoPadelUp.daos.TemporadasDao;
import proyectoPadelUp.daos.TemporadasEquiposDao;
import proyectoPadelUp.pojos.Equipo;
import proyectoPadelUp.pojos.Jornada;
import proyectoPadelUp.pojos.Temporada;

public class ControladorAltaTemporada implements Initializable{

	@FXML
	private AnchorPane altaTemporada;
	@FXML
	private TextField nombre, jornadas, categoria, grupo;
	@FXML
	private Button  botonAceptar, botonCancelar;
	@FXML
	private ComboBox<Equipo> comboEquipos;
	@FXML
	private Text error;
	
	private EquiposDao equiposDao = new EquiposDao();;
	private TemporadasDao temporadaDao = new TemporadasDao();
	private Temporada temporada = new Temporada();
	private Equipo equipo = new Equipo();
	private TemporadasEquiposDao temporadasEquiposDao = new TemporadasEquiposDao();
	private Jornada jornada = new Jornada();
	private JornadasDao jornadaDao = new JornadasDao();
	private int numJornadas;
	private int valorNombre = 1;
	public static Equipo equipoActivo;

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
			 
			 cargarEquipos();
		    });	
		 comboEquipos.valueProperty().addListener(new ChangeListener<Equipo>() {
				@Override
				public void changed(ObservableValue<? extends Equipo> observable, Equipo oldValue, Equipo newValue) {
					if (newValue != null) {
						equipoActivo = newValue;
						categoria.setText(equipoActivo.getDivision());
						grupo.setText(equipoActivo.getGrupo());
					}
				}
			});	
		}
	
	@FXML
	public void botonAceptar(ActionEvent event) {	

		if(!comboEquipos.getItems().isEmpty()) {
			int idEquipo = comboEquipos.getValue().getIdEquipo();
			equipo = equiposDao.consultarEquipoActivoId(idEquipo);
			boolean temporadaActiva = temporadasEquiposDao.equipoEnTemporadaActiva(idEquipo);				
			if(!temporadaActiva) {
				temporada.setNombre(nombre.getText());
				temporada.setNumJornadas(Integer.parseInt(jornadas.getText()));
				numJornadas = Integer.parseInt(jornadas.getText());
				temporada.setDivision(categoria.getText());
				temporada.setGrupo(grupo.getText());
				temporada.setEstado(true);
				temporada.setCategoria(equipo.getCategoria());
				
					temporada.anhadirEquipo(equipo);
					temporadaDao.altaTemporada(temporada);
					while(numJornadas > 0) {
						jornada.setNombre("Jornada " + valorNombre);
						jornada.setEstado("pendiente");
						jornada.setTemporada(temporada);
						jornadaDao.altaJornada(jornada);
						valorNombre++;
						numJornadas--;
					}
					Node source = (Node) event.getSource();
					Stage stage = (Stage) source.getScene().getWindow();
				    stage.close();			
			}else {
				error.setText("El equipo ya tiene una temporada activa");
				
			}
		}else {
			error.setText("Debe de registrar un equipo");
		}
	}
	
	@FXML
	public void botonCancelar(ActionEvent event) {
		Node source = (Node) event.getSource();
	    Stage stage = (Stage) source.getScene().getWindow();
	    stage.close();
	}

	private void cargarEquipos() {
		ObservableList<Equipo> equipos = FXCollections.observableArrayList();
		List<Equipo> listaEquipos = equiposDao.consultarEquipoPorUsuario(index.get());
		equipos.addAll(listaEquipos);
		comboEquipos.setItems(equipos);
		comboEquipos.setConverter(new StringConverter<Equipo>() {
			
			@Override
			public String toString(Equipo object) {
				return object.getNombre();
			}			
			@Override
			public Equipo fromString(String string) {
				return comboEquipos.getItems().stream().filter(ap -> 
	            ap.getNombre().equals(string)).findFirst().orElse(null);
			}
		});
	}
	public TextField getJornadas() {
		return jornadas;
	}
	public void setJornadas(TextField jornadas) {
		this.jornadas = jornadas;
	}
	
	
	
}
