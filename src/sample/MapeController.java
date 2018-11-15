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
import sample.company.Controller.Calculation;
import sample.company.Controller.Geodecoding;
import sample.company.Controller.Product;
import sample.company.Model.Company;
import sample.company.Model.Configuration;
import sample.company.Point;
import sample.DialogFX.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;

public class MapeController {
    private WebEngine webEngine;
    private Company my= new Company();
    boolean badset=false;
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
    void OK_CLICKED(ActionEvent event) {
        String ans="";
        if (!badset) {
            Exception.setVisible(false);
            String addres = AddressField.getText().toString();
            Point place = new Point();
            boolean bad = false;
            int time = 0;
            String order = "";
            try {
                place = Geodecoding.decode(addres);
                time = Integer.parseInt(TimeField.getText());
                order = OrderField.getText();
                Product it = new Product(order, time, place, LocalDateTime.now());
              ans= Calculation.singecalc(my,it);
            } catch (IOException e) {
                Exception.setText("Bad address! Try again!");
                Exception.setVisible(true);
                bad = true;
            } catch (JSONException e) {
                Exception.setText("Bad address! Try again!");
                Exception.setVisible(true);
                bad = true;
            }
            if(!ans.equals("Sorry, but all couriers are busy"))
            {
                DialogFX dialog = new DialogFX();
                dialog.setTitleText("Notice");
                dialog.setMessage("Congratulations, your order will be delivered to "+ans);
                dialog.showDialog();
            }
            else
            {
                DialogFX dialog = new DialogFX();
                dialog.setTitleText("Notice");
                dialog.setMessage(ans+".");
                dialog.showDialog();
            }
            if (!bad)
                webEngine.executeScript("" +
                        "window.lat = " + place.Getlat() + ";" +
                        "window.lon = " + place.Getlng() + ";" +
                        "document.goToLocation(window.lat, window.lon);");
        } else
        {
            Exception.setText("Action cannot be done");
            Exception.setVisible(true);
        }

    }
    @FXML
    void initialize() {

        Exception.setVisible(false);
        webEngine = Map.getEngine();
        final URL urlGoogleMaps = getClass().getResource("demo.html");
        webEngine.load(urlGoogleMaps.toExternalForm());
        badset =false;
        try {
            Configuration.loadconf(my);
        }
        catch (Exception e)
        {
            Exception.setText(" Please check the settings");
            Exception.setVisible(true);
            badset=true;
        }


    }

}
