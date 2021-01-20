package iva.MAIN;

import iva.MODELS.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowAllTeamLayoutController implements Initializable {


    //this control the home button. it goes to the main layout scene
    public void changeScreenOnButtonPushToMainLayout(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("mainLayout.fxml"));
        Parent showAllTeamParent = loader.load(); //needed to create a scene

        Scene showAllTeamMenuScene = new Scene(showAllTeamParent);

        //get the stage from the actionEvent parameter
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(showAllTeamMenuScene);

        window.show();
    }

    //this control the team selected button
    @FXML
    private void changeScreenAndSendDataOnButtonPushToShowSelectedTeamStats(ActionEvent event) throws IOException {
        //step 2.1 (get the selected button id )
        String setTeamName = (((Control) event.getSource()).getId());
        //step1.1
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("getDataLayout.fxml"));
        Parent showSelectedTeamParent = (Parent) loader.load(); //needed to create a scene

        ///get the stage from the actionEvent parameter
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();


        //step3
        getDataController controller = loader.getController();
        controller.setSelectedTeamName(setTeamName);
        controller.initData(setTeamName);
        //step4
        Scene showSelectedTeamScene = new Scene(showSelectedTeamParent);
        window.setScene(showSelectedTeamScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
