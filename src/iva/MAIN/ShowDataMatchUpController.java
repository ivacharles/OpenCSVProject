package iva.MAIN;

import iva.MODELS.Game;
import iva.MODELS.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ShowDataMatchUpController implements Initializable {
    @FXML private Label teamNameLabel;
    private Label gameInfoLabel;
    private TableView<Player> playerTableView;
    @FXML private FlowPane flowPane;

    public void initData(ObservableList<ObservableList<Game>> lisOfGames4ALLSelectedTeams) throws IOException {
        teamNameLabel.setText(lisOfGames4ALLSelectedTeams.get(0).get(0).getTeamName());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d");
        System.out.println("Hello here from INITDATA, the list' size is "+lisOfGames4ALLSelectedTeams.size());
        if(lisOfGames4ALLSelectedTeams.size() > 0)
            for(int i=0; i<lisOfGames4ALLSelectedTeams.size(); i++){
                ObservableList <Game> games = lisOfGames4ALLSelectedTeams.get(i);
                for (int j=0; j<games.size(); j++){
                    playerTableView = new TableView<>();
                    String gameInfos = games.get(j).getDateOfTheGame().format(formatter) + "\t vs " + games.get(j).getOpponentName()+"\t"+games.get(j).getTeamWinOrLoose()+" "+games.get(j).getTeamScore()+"-"+games.get(j).getOpponentScore();
                    System.out.println(gameInfos);
                    gameInfoLabel = new Label();
                    gameInfoLabel.setMaxWidth(250);
                    gameInfoLabel.setMinWidth(250);
                    gameInfoLabel.setMinHeight(10);
                    gameInfoLabel.setStyle("-fx-background-color: #707070");;
                    gameInfoLabel.setTextFill(Color.WHITE);
                    gameInfoLabel.setText(gameInfos);

                    //Associate the properties with the table columns
                    TableColumn<Player, String> playerNameColumn = new TableColumn<>("Player name");
                    playerNameColumn.setMaxWidth(112);
                    playerNameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("playerName"));

                    TableColumn<Player, String> minPlayedColumn = new TableColumn<>("Minutes");
                    minPlayedColumn.setMaxWidth(80);
                    minPlayedColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("MIN"));

                    TableColumn<Player, Double> fantasyPointColumn = new TableColumn<>("FAN PTS");
//                    fantasyPointColumn.setMaxWidth(200);
                    fantasyPointColumn.setCellValueFactory(new PropertyValueFactory<Player, Double>("FAN"));
                    playerTableView.getColumns().addAll(playerNameColumn,minPlayedColumn,fantasyPointColumn);
                    playerTableView.setItems(games.get(j).getPlayerListStats());
                    VBox newVBox = new VBox();
                    newVBox.getChildren().addAll(gameInfoLabel,playerTableView);
                    flowPane.getChildren().add(newVBox);
                }
                System.out.println();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            teamNameLabel.setText("");
    }
}

