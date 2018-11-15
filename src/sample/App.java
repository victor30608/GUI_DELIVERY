package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.web.WebView;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import java.net.URL;

public class App {

    double lat;
    double lon;
    private WebEngine webEngine;
    @FXML
    private TextField latitude;

    @FXML
    private TextField longitude;
    @FXML
    private WebView mapView =  new WebView();

    @FXML
    private Button Update_Button;

    @FXML
    void UpdateAction(ActionEvent event) {
//        webEngine = mapView.getEngine();
//        final URL urlGoogleMaps = getClass().getResource("demo.html");
//        webEngine.load(urlGoogleMaps.toExternalForm());
        lat = Double.parseDouble(latitude.getText());
        lon = Double.parseDouble(longitude.getText());

        System.out.printf("%.2f %.2f%n", lat, lon);

        webEngine.executeScript("" +
                "window.lat = " + lat + ";" +
                "window.lon = " + lon + ";" +
                "document.goToLocation(window.lat, window.lon);");
    }

    @FXML
    void initialize() {
        webEngine = mapView.getEngine();
        final URL urlGoogleMaps = getClass().getResource("demo.html");
        webEngine.load(urlGoogleMaps.toExternalForm());

    }
}