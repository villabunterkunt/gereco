package controllers;

import application.Main;
import application.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Institution;

import java.io.IOException;
import java.net.URL;

public class HomeController {
    public Label lblInstitutionInfo;
    public ScrollPane scrollHome;
    public StackPane stackPaneMain;

    static StackPane staticStackPaneMain;
    private URL eventListURL;

    @FXML
    public void initialize() {
        Institution institutionLogged = Session.getInstance().getInstitution();
        lblInstitutionInfo.setText(institutionLogged.getName() + " |  " + institutionLogged.getEmail());
        eventListURL = getClass().getResource("/views/home/event-list.fxml");

        staticStackPaneMain = stackPaneMain;
        loadView(eventListURL);
    }

    @FXML
    protected void openEventListView(){
        loadView(eventListURL);
    }

    @FXML
    protected void openConfigView(){
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/views/external-forms/institution-config.fxml")));
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void logout(){
        Session.getInstance().setInstitution(null);
        Main.mainStage.close();
        Main.mainStage = new Stage();

        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/views/initial.fxml")));

            Main.mainStage .setScene(scene);
            Main.mainStage .setResizable(false);
            Main.mainStage .centerOnScreen();
            Main.mainStage .show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void loadView(URL viewURL){
        try{
            staticStackPaneMain.getChildren().clear();
            staticStackPaneMain.getChildren().add(FXMLLoader.load(viewURL));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}