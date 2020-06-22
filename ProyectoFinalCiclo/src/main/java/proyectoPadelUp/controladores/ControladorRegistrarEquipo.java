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
import javafx.stage.Stage;
import javafx.util.StringConverter;
import proyectoPadelUp.AplicacionPadelUp;
import proyectoPadelUp.daos.EquiposDao;
import proyectoPadelUp.pojos.Equipo;
import proyectoPadelUp.pojos.Usuario;

public class ControladorRegistrarEquipo implements Initializable{
	
	@FXML
	private  ComboBox <Equipo> comboNombre;
	@FXML
	private TextField categoria,club,direccion,telefono;;
	@FXML 
	private Button botonCancelar, botonAceptar;
	
	private Usuario usuario = new Usuario();
	private EquiposDao equipoDao = new EquiposDao();
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
		comboNombre.valueProperty().addListener(new ChangeListener<Equipo>() {
			@Override
			public void changed(ObservableValue<? extends Equipo> observable, Equipo oldValue, Equipo newValue) {
				if (newValue != null) {
					equipoActivo = newValue;
					categoria.setText(equipoActivo.getCategoria());
				}
			}
		});
		cargarEquipos();		
	}
	
	private void cargarEquipos() {
		ObservableList<Equipo> equipos = FXCollections.observableArrayList();
		List<Equipo> listaEquipos = equipoDao.consultarTodosEquiposActivos();
		equipos.addAll(listaEquipos);
		comboNombre.setItems(equipos);
		comboNombre.setConverter(new StringConverter<Equipo>() {

			@Override
			public String toString(Equipo object) {
				return object.getNombre();
			}

			@Override
			public Equipo fromString(String string) {
				return comboNombre.getItems().stream().filter(ap -> ap.getNombre().equals(string)).findFirst()
						.orElse(null);
			}
		});	
	}
	
	@FXML
	public void botonAceptar(ActionEvent event) {
		Equipo equipo = new Equipo();
		equipo.setIdEquipo(equipoActivo.getIdEquipo());
		equipo.setNombre(equipoActivo.getNombre());
		equipo.setClub(club.getText());
		equipo.setDireccion(direccion.getText());
		equipo.setTelefono(telefono.getText());
		equipo.setCategoria(equipoActivo.getCategoria());
		equipo.setDivision(equipoActivo.getDivision());
		equipo.setGrupo(equipoActivo.getGrupo());
		equipo.setEstado(true);
		usuario.setIdUsuario(AplicacionPadelUp.getUserId());
		equipo.setUsuario(usuario);
		equipoDao.modificarEquipo(equipo);
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}		
	
	@FXML
	public void botonCancelar(ActionEvent event){
		Node source = (Node) event.getSource();
	    Stage stage = (Stage) source.getScene().getWindow();
	    stage.close();
	}
	public TextField getTelefono() {
		return telefono;
	}
	public void setTelefono(TextField telefono) {
		this.telefono = telefono;
	}
	
	
}




