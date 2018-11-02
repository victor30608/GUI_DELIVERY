package sample;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;



import static javafx.fxml.FXMLLoader.load;

public class Controller {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button UserAccaunt;

    @FXML
    private Button DevAcc;

    @FXML
    void DevAction(ActionEvent event) {
        try {
//            Stage stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
////            Stage stage = ((Node) event.getSource()).getScene().getWindow();
//            stage.close();
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("settings.fxml"));
//
//            Parent root = fxmlLoader.load();
//            stage.setTitle("Delivery");
//
//            stage.setScene(new Scene(root, 600, 400));
//            stage.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("settings.fxml"));
            Parent delivery_page_parent = loader.load();
            Scene delivery_page_scene = new Scene (delivery_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(delivery_page_scene);
            app_stage.show();
        } catch (IOException e)
        {
            e.getMessage();
        }
    }

    @FXML
    void UserAction(ActionEvent event) {
        try {
//            Stage stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.close();
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("map.fxml"));
//
//            Parent root = fxmlLoader.load();
//            stage.setTitle("Delivery");
//
//            stage.setScene(new Scene(root, 600, 400));
//            stage.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("map.fxml"));
            Parent delivery_page_parent = loader.load();
            Scene delivery_page_scene = new Scene (delivery_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(delivery_page_scene);
            app_stage.show();
        } catch (IOException e)
        {
            e.getMessage();
        }

    }
   @FXML
    void initialize()
    {
//        UserAccaunt.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
//        {
//            @Override
//            public void handle(MouseEvent mouseEvent)
//            {
//
//
//            }
//        });
    }

   /* @FXML
    private void handleButtonAction(ActionEvent event) throws IOException
    {
        System.out.println("You clicked me!");
        //Close current
        Stage stage = (Stage) UserAccaunt.getScene().getWindow();
        // do what you have to do
       // stage.close();


        Object page = load(getClass().getResource("map.fxml"));
        Scene scene = new Scene((Parent) page);
        stage.setScene(scene);
        stage.show();





       // Parent root1 = (Parent) fxmlLoader.load();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Find your way");
       // stage.setScene(new Scene(root1));
       //stage.show();
    }*/


}
