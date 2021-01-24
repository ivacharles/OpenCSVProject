package iva.MAIN;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class mainController {

    public void changeScreenOnButtonPushToShowAllTeamMenu(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("showAllTeamLayout.fxml"));
        Parent showAllTeamParent = loader.load(); //needed to create a scene

        Scene showAllTeamMenuScene = new Scene(showAllTeamParent,1400, 800);

        //get the stage from the actionEvent parameter
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(showAllTeamMenuScene);

        window.show();
    }
    public void changeScreenOnButtonPushToShowVSMatchUp(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("showMatchVSLayout.fxml"));
        Parent showMatchVSParent = loader.load(); //needed to create a scene

        Scene showMatchVScene = new Scene(showMatchVSParent,1400, 800);

        //get the stage from the actionEvent parameter
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(showMatchVScene);

        window.show();
    }
}
