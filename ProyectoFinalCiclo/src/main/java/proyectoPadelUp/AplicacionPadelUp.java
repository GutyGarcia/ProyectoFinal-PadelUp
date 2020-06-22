package proyectoPadelUp;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import proyectoPadelUp.controladores.*;
import proyectoPadelUp.daos.RolesDao;
import proyectoPadelUp.daos.UsuariosDao;
import proyectoPadelUp.pojos.Rol;
import proyectoPadelUp.pojos.Usuario;

public class AplicacionPadelUp extends Application {

	private static int userId;
	private static LayoutPane layoutPane;
	private static final Logger LOGGER = Logger.getLogger( AplicacionPadelUp.class.getName() );
	@Override
	public void start(Stage primaryStage) {
		try {
			setupLogger();               
			altaRoles();
			layoutPane = new LayoutPane();
			layoutPane.cargarPantalla("Principal",
					ControladorEquipos.class.getResource("/proyectoPadelUp/principal.fxml"));
			layoutPane.cargarPantalla("Federacion",
					ControladorPantallaFederacion.class.getResource("/proyectoPadelUp/webFederacion.fxml"));
			layoutPane.cargarPantalla("Documentos", ControladorPantallaDocumentosFederacion.class
					.getResource("/proyectoPadelUp/webDocumentosFederacion.fxml"));
			layoutPane.cargarPantalla("Alta usuario",
					ControladorAltaUsuario.class.getResource("/proyectoPadelUp/altaUsuario.fxml"));
			layoutPane.cargarPantalla("Menu", ControladorMenu.class.getResource("/proyectoPadelUp/menu.fxml"));
			layoutPane.cargarPantalla("Login",
					ControladorPantallaLogin.class.getResource("/proyectoPadelUp/login.fxml"));

			layoutPane.mostrarComoPantallaActual("Login");
			Scene escena = new Scene(layoutPane);

			escena.getStylesheets().add("@../../estilos/estilos.css");
			primaryStage.getIcons().add(new Image("@../../imagenes/logoGeneralMediano.png"));
			primaryStage.setScene(escena);
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.show();
		} catch (Exception e) {
			LOGGER.log( Level.SEVERE, e.toString(), e );
		}
		
	}

	private void altaRoles() {
		RolesDao rolesDao = new RolesDao();
		RolesDao rolesDao2 = new RolesDao();
		UsuariosDao usuariosDao = new UsuariosDao();
		boolean existeRol = rolesDao2.existeRol();
		if(!existeRol) {
			Rol rolAdmin = new Rol(1, "admin");
			Rol rolUser = new Rol(2, "user");
			rolesDao.altaRol(rolAdmin);
			rolesDao.altaRol(rolUser);
			Usuario usuarioAdmin = new Usuario(1, "admin", "admin", "admin", "1234", "prueba@gmail.com", "admin", true, rolAdmin);
			usuariosDao.altaUsuario(usuarioAdmin);
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void setUserId(int userId) {
		AplicacionPadelUp.userId = userId;
		try {
			layoutPane.cargarPantalla("Principal",
					ControladorEquipos.class.getResource("/proyectoPadelUp/principal.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public static int getUserId() {
		return AplicacionPadelUp.userId;
	}
	
	private static void setupLogger() {
        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.ALL);
        
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        LOGGER.addHandler(ch);

        try {
            FileHandler fh = new FileHandler("myLogger.log", true);
            fh.setLevel(Level.FINE);
            LOGGER.addHandler(fh);
        } catch (java.io.IOException e) {            
          LOGGER.log(Level.SEVERE, "File logger not working.", e);
        }         
    }
}
