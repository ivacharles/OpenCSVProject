package iva.MAIN;

import iva.DAO.TeamDAO;
import iva.DbUtility.DbConnection;
import iva.MODELS.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MatchVSController implements Initializable {
    private final String [] teamList = {"Hawks","Celtics","Nets","Hornets","Bulls","Cavaliers","Mavericks","Nuggets","Pistons","Warriors","Rockets","Pacers","Clippers","Lakers","Grizzlies","Heat","Bucks","Timberwolves","Pelicans","Knicks","Thunder","Magic","Sixers","Suns","Blazers","Kings","Spurs","Raptors","Jazz","Wizards"};

    @FXML
    Label label4SelectedTeamTextAreaConfirmation;
    //these are items for the listView and textare
    @FXML private ListView listView;
    @FXML private TextArea  selectedListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label4SelectedTeamTextAreaConfirmation.setText("");
        listView.getItems().addAll(teamList);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML private void listViewButtonPushed() {
        String textAreaString = "";

        ObservableList listOfSelectedListView = listView.getSelectionModel().getSelectedItems();

        for (Object selectedTeam : listOfSelectedListView){
            textAreaString += String.format("%s%n", (String) selectedTeam);
        }
        selectedListView.setText(textAreaString);
    }

    @FXML private void OnGOButtonPushed(ActionEvent event) throws IOException {
        TeamDAO teamDAO = DbConnection.getTeamDAO();
        ObservableList listOfSelectedListView = listView.getSelectionModel().getSelectedItems();
        ObservableList <Game> lisOfGames4SelectedTeam;
        ObservableList <ObservableList<Game>> lisOfGames4ALLSelectedTeams = FXCollections.observableArrayList();

        if (listOfSelectedListView.size() == 0 || listOfSelectedListView.size() > 4){
            label4SelectedTeamTextAreaConfirmation.setText("Please select at least one or up to 4 team (s)");
        }else {
            for (Object selectedTeam : listOfSelectedListView){
                lisOfGames4SelectedTeam = teamDAO.getAllGames4TheTeamWithOnlyPlayersFANPTS((String) selectedTeam);
                System.out.println("-------->>>>> lisOfGames4SelectedTeam size is "+ lisOfGames4SelectedTeam.size());

                lisOfGames4ALLSelectedTeams.add(lisOfGames4SelectedTeam);
            }

            //step1.1
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("showDataMatchUpLayout.fxml"));
            Parent showDataMatchUpParent = (Parent) loader.load(); //needed to create a scene

            ///get the stage from the actionEvent parameter
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene showSelectedTeamScene = new Scene(showDataMatchUpParent);

            //step3
            ShowDataMatchUpController controller = loader.getController();
            controller.initData(lisOfGames4ALLSelectedTeams);
            //step4
            window.setScene(showSelectedTeamScene);
            window.show();
        }



    }

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

}
