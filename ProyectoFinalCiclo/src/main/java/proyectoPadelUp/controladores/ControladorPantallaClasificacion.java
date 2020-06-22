package proyectoPadelUp.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

public class ControladorPantallaClasificacion implements Initializable {

	@FXML
	private WebView webView;

	String division;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		if (ControladorEquipos.equipoActivo.getGrupo() == null) {
			division = ControladorEquipos.equipoActivo.getDivision();
		} else {
			division = ControladorEquipos.equipoActivo.getDivision() + " " + ControladorEquipos.equipoActivo.getGrupo();
		}

		switch (division) {
		case "1ª Sur - categoria femenina":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-sur-612/2984/9719/1529");
			break;
		case "2ª Sur - categoria femenina Grupo 1":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-sur-612/2985/9720/1512");
			break;
		case "2ª Sur - categoria femenina Grupo 2":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-sur-612/2985/9720/1513");
			break;
		case "3ª Sur - categoria femenina Grupo 1":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-sur-612/2986/9723/1514");
			break;
		case "3ª Sur - categoria femenina Grupo 2":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-sur-612/2986/9723/1515");
			break;
		case "3ª Sur - categoria femenina Grupo 3":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-sur-612/2986/9723/1516");
			break;
		case "3ª Sur - categoria femenina Grupo 4":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-sur-612/2986/9723/1517");
			break;
		case "3ª Sur - categoria femenina Grupo 5":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-sur-612/2986/9723/1518");
			break;
		case "1ª Sur - categoria masculina":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-sur-612/2987/9729/1530");
			break;
		case "2ª Sur - categoria masculina Grupo 1":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-sur-612/2988/9725/1519");
			break;
		case "2ª Sur - categoria masculina Grupo 2":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-sur-612/2988/9725/1520");
			break;
		case "3ª Sur - categoria masculina Grupo 1":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-sur-612/2989/9730/1521");
			break;
		case "3ª Sur - categoria masculina Grupo 2":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-sur-612/2989/9730/1522");
			break;
		case "3ª Sur - categoria masculina Grupo 3":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-sur-612/2989/9730/1523");
			break;
		case "3ª Sur - categoria masculina Grupo 4":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-sur-612/2989/9730/1524");
			break;
		case "4ª Sur - categoria masculina Grupo 1":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-sur-612/2989/9730/1525");
			break;
		case "4ª Sur - categoria masculina Grupo 2":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-sur-612/2989/9730/1526");
			break;
		case "4ª Sur - categoria masculina Grupo 3":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-sur-612/2989/9730/1527");
			break;
		case "4ª Sur - categoria masculina Grupo 4":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-sur-612/2989/9730/1528");
			break;
		case "1ª Norte - categoria femenina":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-norte-611/2979/9683/1531");
			break;
		case "2ª Norte - categoria femenina Grupo 1":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-norte-611/2976/9690/1490");
			break;
		case "2ª Norte - categoria femenina Grupo 2":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-norte-611/2976/9690/1491");
			break;
		case "3ª Norte - categoria femenina Grupo 1":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-norte-611/2976/9690/1492");
			break;
		case "3ª Norte - categoria femenina Grupo 2":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-norte-611/2976/9690/1493");
			break;
		case "3ª Norte - categoria femenina Grupo 3":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-norte-611/2976/9690/1494");
			break;
		case "3ª Norte - categoria femenina Grupo 4":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-norte-611/2976/9690/1495");
			break;
		case "4ª Norte - categoria femenina Grupo 1":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-norte-611/2976/9690/1496");
			break;
		case "4ª Norte - categoria femenina Grupo 2":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-norte-611/2976/9690/1497");
			break;
		case "1ª Norte - categoria masculina":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-norte-611/2980/9695/1532");
			break;
		case "2ª Norte - categoria masculina Grupo 1":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-norte-611/2982/9737/1498");
			break;
		case "2ª Norte - categoria masculina Grupo 2":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-norte-611/2982/9737/1499");
			break;
		case "3ª Norte - categoria masculina Grupo 1":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-norte-611/2982/9737/1500");
			break;
		case "3ª Norte - categoria masculina Grupo 2":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-norte-611/2982/9737/1501");
			break;
		case "3ª Norte - categoria masculina Grupo 3":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-norte-611/2982/9737/1502");
			break;
		case "3ª Norte - categoria masculina Grupo 4":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-norte-611/2982/9737/1503");
			break;
		case "4ª Norte - categoria masculina Grupo 1":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-norte-611/2982/9737/1504");
			break;
		case "4ª Norte - categoria masculina Grupo 2":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-norte-611/2982/9737/1505");
			break;
		case "4ª Norte - categoria masculina Grupo 3":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-norte-611/2982/9737/1506");
			break;
		case "4ª Norte - categoria masculina Grupo 4":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-norte-611/2982/9737/1507");
			break;
		case "4ª Norte - categoria masculina Grupo 5":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-norte-611/2982/9737/1508");
			break;
		case "4ª Norte - categoria masculina Grupo 6":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-norte-611/2982/9737/1509");
			break;
		case "4ª Norte - categoria masculina Grupo 7":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-norte-611/2982/9737/1510");
			break;
		case "4ª Norte - categoria masculina Grupo 8":
			webView.getEngine().load(
					"http://www.sport2fit.com/liga/liga-gallega-por-equipos-de-clubes-liga-gallega-por-equipos-de-clubes-2020-zona-norte-611/2982/9737/1511");
			break;
		}
	}
}
