package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.json.JSONException;
import sample.company.Controller.Geodecoding;
import sample.company.Point;

import java.io.IOException;
import java.net.URL;

public class MapeController {
    private WebEngine webEngine;
    @FXML
    private Button OK_Button;

    @FXML
    private Button Back_Button;

    @FXML
    private TextField AddressField;

    @FXML
    private TextField OrderField;

    @FXML
    private TextField TimeField;

    @FXML
    private WebView Map;
    @FXML
    private TextField Exception;
    @FXML
    void Back_Action(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Parent delivery_page_parent = loader.load();
            Scene delivery_page_scene = new Scene(delivery_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(delivery_page_scene);
            app_stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OK_CLICKED(ActionEvent event)
    {
       String addres=AddressField.getText().toString();
       try {
           Point place = Geodecoding.decode(addres);
       }
       catch (IOException e)
       {

       }
       catch (JSONException e)
       {

       }
//        webEngine.executeScript("" +
//                "window.lat = " + lat + ";" +
//                "window.lon = " + lon + ";" +
//                "document.goToLocation(window.lat, window.lon);");
    }
    @FXML
    void initialize() {
        Exception.setVisible(false);
        webEngine = Map.getEngine();
        final URL urlGoogleMaps = getClass().getResource("demo.html");
        webEngine.load(urlGoogleMaps.toExternalForm());

    }

}
