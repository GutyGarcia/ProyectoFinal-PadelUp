package proyectoPadelUp.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

public class ControladorPantallaFederacion extends ControladorConNavegabilidad implements Initializable {
	
	@FXML
	private WebView webView;
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		webView.getEngine().load("http://fgpadel.com/");	
		
	}		
}


