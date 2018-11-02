package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.json.JSONException;
import sample.company.*;
import sample.company.Controller.Geodecoding;
import sample.company.Model.Company;

import java.io.IOException;
import java.rmi.server.ExportException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.company.Model.Configuration;
import sample.company.Model.Transport;
import sample.company.Model.TypeOfTransport;

public class SettingController {

    @FXML
    private Button Back_Button;
    @FXML
    private Button Accept_Button;

    @FXML
    private TextField CompanyAddress;

    @FXML
    private TextField CarsAmount;

    @FXML
    private TextField MenAmount;

    @FXML
    private TextField QuadroAmount;
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
    void Accept_Clicked(ActionEvent event)
    {
        Company my = new Company();
        my.address=CompanyAddress.getText().trim();
        try {
            my.place= Geodecoding.decode(my.address);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

for (int i = 0; i < Integer.parseInt(CarsAmount.getText().trim()); i++)
    {
    Transport tmp=new Transport();
    tmp.name= TypeOfTransport.Type.driving;
    my.worker.add(tmp);
    }

        for (int i = 0; i < Integer.parseInt(QuadroAmount.getText().trim()); i++)
        {
            Transport tmp=new Transport();
            tmp.name= TypeOfTransport.Type.quadro;
            my.worker.add(tmp);
        }
        for (int i = 0; i < Integer.parseInt(MenAmount.getText().trim()); i++)
        {
            Transport tmp=new Transport();
            tmp.name= TypeOfTransport.Type.walking;
            my.worker.add(tmp);
        }
        try {
            Configuration.saveconf(my);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("map.fxml"));
            Parent delivery_page_parent = loader.load();
            Scene delivery_page_scene = new Scene (delivery_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(delivery_page_scene);
            app_stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
