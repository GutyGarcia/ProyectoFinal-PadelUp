package proyectoPadelUp.controladores;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import proyectoPadelUp.AplicacionPadelUp;
import proyectoPadelUp.daos.EquiposDao;
import proyectoPadelUp.pojos.Equipo;
import proyectoPadelUp.pojos.Usuario;


public class ControladorAltaEquipo implements Initializable{

	@FXML
	private TextField nombre,club,direccion,telefono;
	@FXML
	private ComboBox<String> categoria,division,grupo;
	@FXML 
	private Button botonCancelar, botonAceptar;
	@FXML
	private Text error;

	private EquiposDao equipoDao = new EquiposDao();
	private Equipo equipo = new Equipo();
	private Usuario usuario = new Usuario();
	private String cadenaFinal;
	
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
		equipo.setClub(club.getText());
		equipo.setDireccion(direccion.getText());
		equipo.setTelefono(telefono.getText());
		equipo.setCategoria(categoria.getValue());
		equipo.setDivision(division.getValue());
		equipo.setGrupo(grupo.getValue());
		equipo.setEstado(true);
		equipo.setUsuario(usuario);		
		if(equipo.getCategoria().trim().equals(cadenaFinal.trim())) {
			equipoDao.altaEquipo(equipo);
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
	public TextField getTelefono() {
		return telefono;
	}
	public void setTelefono(TextField telefono) {
		this.telefono = telefono;
	}
	
	
	
}
