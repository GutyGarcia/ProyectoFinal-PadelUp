package proyectoPadelUp.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proyectoPadelUp.AplicacionPadelUp;
import proyectoPadelUp.daos.EquiposDao;
import proyectoPadelUp.pojos.Equipo;
import proyectoPadelUp.pojos.Usuario;

public class ControladorEditarEquipoAdmin implements Initializable{
	
	@FXML
	private TextField nombre,club,direccion,telefono, nombreCapitan;
	@FXML
	private ComboBox<String> categoria,division,grupo;
	@FXML 
	private Button botonCancelar, botonAceptar, capitan;
	@FXML
	private Text error;
	
	private ControladorEquipos controlEquipo;
	private EquiposDao equipoDao = new EquiposDao();
	private Equipo equipo = new Equipo();
	private Usuario usuario = new Usuario();
	private String cadenaFinal;
	

	private IntegerProperty index = new SimpleIntegerProperty(-1);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		index.addListener((ob,n,n1)->{
			cargarDatos();
	    });	
	}
	@FXML
	public void botonAceptar(ActionEvent event) {		
		String[] parts = division.getValue().split(Pattern.quote("categoria"));

		if (!Arrays.asList(parts).isEmpty()) {
			cadenaFinal = Arrays.asList(parts).get(Arrays.asList(parts).size() - 1);
		}	
		usuario.setIdUsuario(AplicacionPadelUp.getUserId());
		equipo.setNombre(nombre.getText());
		equipo.setIdEquipo(index.get());
		equipo.setClub(club.getText());
		equipo.setDireccion(direccion.getText());
		equipo.setTelefono(telefono.getText());
		equipo.setCategoria(categoria.getValue());
		equipo.setDivision(division.getValue());
		equipo.setGrupo(grupo.getValue());
		equipo.setEstado(true);
		equipo.setUsuario(usuario);
		if(equipo.getCategoria().trim().equals(cadenaFinal.trim())) {
			equipoDao.modificarEquipo(equipo);
			Node source = (Node) event.getSource();
			Stage stage = (Stage) source.getScene().getWindow();
		    stage.close();
		}else {
			error.setText("División errónea para la categoría del equipo");
		}
		
	}	
	
	@FXML
	public void botonCancelar(ActionEvent event){
		Node source = (Node) event.getSource();
	    Stage stage = (Stage) source.getScene().getWindow();
	    stage.close();
	}
	
	private void cargarDatos() {
		Equipo equipo = equipoDao.consultarEquipoActivoId(index.get());
		nombre.setText(equipo.getNombre());
		club.setText(equipo.getClub());
		direccion.setText(equipo.getDireccion());
		telefono.setText(equipo.getTelefono());
		categoria.setValue(equipo.getCategoria());
		division.setValue(equipo.getDivision());
		grupo.setValue(equipo.getGrupo());
		if(equipo.getUsuario().getIdUsuario() > 1) {
			nombreCapitan.setText(equipo.getUsuario().getNombre());
		}else {
			capitan.setDisable(true);
		}		
	}
	
	@FXML
	public void consultarCapitan(ActionEvent event) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/proyectoPadelUp/editarCapitan.fxml"));
		Parent contenedor = fxmlLoader.load();
		Stage stage = new Stage();
		int idCapitan = controlEquipo.equipoActivo.getUsuario().getIdUsuario();
		stage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
		stage.setTitle("Registrar jugador");
		ControladorEditarCapitan controladorEditarCapitan = fxmlLoader.<ControladorEditarCapitan>getController();
		
		controladorEditarCapitan.setIndex(idCapitan);
		Scene escena = new Scene(contenedor, 379, 307 );
		escena.getStylesheets().addAll(getClass().getResource("/estilos/Estilos.css").toExternalForm());
		stage.setResizable(false);
		stage.setScene(escena);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
		nombreCapitan.clear();
		cargarDatos();
	}
	public void setIndex(int index){
	    this.index.set(index);
	}
	public int getIndex(){
	    return index.get();
	}
	public TextField getTelefono() {
		return telefono;
	}
	public void setTelefono(TextField telefono) {
		this.telefono = telefono;
	}
	
	
}




