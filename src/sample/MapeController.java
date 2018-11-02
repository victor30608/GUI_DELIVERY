package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MapeController {

    @FXML
    private ImageView mapImage;

    @FXML
    private Button OK_Button;

    @FXML
    private TextField AddressField;

    @FXML
    private TextField OrderField;

    @FXML
    private TextField TimeField;

    @FXML
    private Button BackToSample;

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

    }

}
