package proyectoPadelUp.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proyectoPadelUp.daos.EquiposDao;
import proyectoPadelUp.daos.UsuariosDao;
import proyectoPadelUp.pojos.Equipo;
import proyectoPadelUp.pojos.Usuario;

public class ControladorEditarCapitan implements Initializable{
	
	@FXML 
	private TextField nombre, apellido1, email, nombreUsuario;
	@FXML
	private Button botonAceptar, botonCancelar, eliminarCapitan;
	
	private UsuariosDao usuarioDao = new UsuariosDao();
	private EquiposDao equipoDao = new EquiposDao();
	private Usuario usuario = new Usuario();
	private Equipo equipo = new Equipo();
	private ControladorEquipos controladorEquipos = new ControladorEquipos();
	
	
	private IntegerProperty index = new SimpleIntegerProperty(-1);

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		index.addListener((ob,n,n1)->{
			cargarDatos();
	    });			
	}
	
	@FXML
	public void botonAceptar(ActionEvent event) {
		Usuario usuario2 = new Usuario();
		usuario2.setIdUsuario(usuario.getIdUsuario());
		usuario2.setNombre(nombre.getText());
		usuario2.setPrimerApellido(apellido1.getText());
		usuario2.setEmail(email.getText());
		usuario2.setNombreUsuario(nombreUsuario.getText());
		usuario2.setContraseñaEmail(usuario.getContraseñaEmail());
		usuario2.setContraseñaUsuario(usuario.getContraseñaUsuario());
		usuario2.setEstado(true);
		usuarioDao.modificarUsuario(usuario2);
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

	@FXML
	public void eliminarCapitan(ActionEvent event){
		equipo = controladorEquipos.equipoActivo;
		usuario.setIdUsuario(1);
		equipo.setUsuario(usuario);	
		equipoDao.modificarEquipo(equipo);
		Node source = (Node) event.getSource();
	    Stage stage = (Stage) source.getScene().getWindow();
	    stage.close();
	}
	
	private void cargarDatos() {
		usuario = usuarioDao.buscarUsuarioPorId(index.get());
		nombre.setText(usuario.getNombre());
		apellido1.setText(usuario.getPrimerApellido());
		email.setText(usuario.getEmail());
		nombreUsuario.setText(usuario.getNombreUsuario());		
	}

	public void setIndex(int index){
	    this.index.set(index);
	}
	public int getIndex(){
	    return index.get();
	}
}
