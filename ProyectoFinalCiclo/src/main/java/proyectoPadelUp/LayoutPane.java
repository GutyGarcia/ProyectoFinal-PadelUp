package proyectoPadelUp;

import proyectoPadelUp.controladores.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LayoutPane extends BorderPane {

	private Stage stage;

	private Map<String, Node> pantallasDeLaAplicacion;

	public LayoutPane() {
		this.pantallasDeLaAplicacion = new HashMap<>();
	}

	public void cargarPantalla(String nombreDeLaPantalla, URL urlArchivoFxml) throws IOException {
		FXMLLoader cargadorPantallas = new FXMLLoader(urlArchivoFxml);
		Parent pantalla = cargadorPantallas.load();

		ControladorConNavegabilidad controladorConNavegabilidad = cargadorPantallas.getController();
		controladorConNavegabilidad.setLayout(this);

		pantallasDeLaAplicacion.put(nombreDeLaPantalla, pantalla);
	}

	public void mostrarComoPantallaActual(String nombreDeLaPantalla) {
		this.setCenter(pantallasDeLaAplicacion.get(nombreDeLaPantalla));
	}

	public void mostrarComoPantallaActual(String nombreDeLaPantalla, Stage stage) {
		this.setCenter(pantallasDeLaAplicacion.get(nombreDeLaPantalla));
		this.stage = stage;
	}

	public void cargarBarraDeMenuEnLaPartePosterior() {
		this.setTop(pantallasDeLaAplicacion.get("Menu"));

	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
