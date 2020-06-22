package proyectoPadelUp.controladores;

import proyectoPadelUp.*;
import proyectoPadelUp.daos.*;
import proyectoPadelUp.pojos.Equipo;
import proyectoPadelUp.pojos.Usuario;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ControladorEditarEquipo implements Initializable{
	
	@FXML
	private TextField nombre,club,direccion,telefono,categoria,division,grupo;
	@FXML 
	private Button botonCancelar, botonAceptar;
	
	private ControladorEquipos controlEquipo;
	private EquiposDao equipoDao = new EquiposDao();
	private Equipo equipo = new Equipo();
	

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
		 	equipoDao = new EquiposDao();
			cargarDatos();
	    });	
	}
	@FXML
	public void botonAceptar(ActionEvent event) {
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(AplicacionPadelUp.getUserId());
		equipo.setNombre(nombre.getText());
		equipo.setIdEquipo(index.get());
		equipo.setClub(club.getText());
		equipo.setDireccion(direccion.getText());
		equipo.setTelefono(telefono.getText());
		equipo.setCategoria(categoria.getText());
		equipo.setDivision(division.getText());
		equipo.setGrupo(grupo.getText());
		equipo.setEstado(true);
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
	
	private void cargarDatos() {
		Equipo equipo = equipoDao.consultarEquipoActivoId(index.get());
		nombre.setText(equipo.getNombre());
		club.setText(equipo.getClub());
		direccion.setText(equipo.getDireccion());
		telefono.setText(equipo.getTelefono());
		categoria.setText(equipo.getCategoria());
		division.setText(equipo.getDivision());
		grupo.setText(equipo.getGrupo());
		
	}
	public TextField getNombre() {
		return nombre;
	}
	public void setNombre(TextField nombre) {
		this.nombre = nombre;
	}
	public TextField getClub() {
		return club;
	}
	public void setClub(TextField club) {
		this.club = club;
	}
	public TextField getDireccion() {
		return direccion;
	}
	public void setDireccion(TextField direccion) {
		this.direccion = direccion;
	}
	public TextField getTelefono() {
		return telefono;
	}
	public void setTelefono(TextField telefono) {
		this.telefono = telefono;
	}
	
	public TextField getCategoria() {
		return categoria;
	}
	public void setCategoria(TextField categoria) {
		this.categoria = categoria;
	}
	public Button getBotonCancelar() {
		return botonCancelar;
	}
	public void setBotonCancelar(Button botonCancelar) {
		this.botonCancelar = botonCancelar;
	}
	public Button getBotonAceptar() {
		return botonAceptar;
	}
	public void setBotonAceptar(Button botonAceptar) {
		this.botonAceptar = botonAceptar;
	}
	public ControladorEquipos getControlEquipo() {
		return controlEquipo;
	}
	public void setControlEquipo(ControladorEquipos controlEquipo) {
		this.controlEquipo = controlEquipo;
	}
	public EquiposDao getEquipoDao() {
		return equipoDao;
	}
	public void setEquipoDao(EquiposDao equipoDao) {
		this.equipoDao = equipoDao;
	}
	public Equipo getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	public void setIndex(IntegerProperty index) {
		this.index = index;
	}
	
	
}




