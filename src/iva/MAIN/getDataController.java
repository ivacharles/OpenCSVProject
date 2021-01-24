package iva.MAIN;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import iva.DAO.TeamDAO;
import iva.DbUtility.DbConnection;
import iva.MODELS.Game;
import iva.MODELS.Player;
import iva.MODELS.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class getDataController implements Initializable {

    private final String [] teamList = {"Hawks","Celtics","Nets","Hornets","Bulls","Cavaliers","Mavericks","Nuggets","Pistons","Warriors","Rockets","Pacers","Clippers","Lakers","Grizzlies","Heat","Bucks","Timberwolves","Pelicans","Knicks","Thunder","Magic","Sixers","Suns","Blazers","Kings","Spurs","Raptors","Jazz","Wizards"};

    private String SelectedTeamName ;

    @FXML private Pagination pagination;

    @FXML private Label teamNameLabel;
    @FXML private DatePicker dateOfTheGamePicker;
    @FXML private ChoiceBox<String> opponentTeamNameChoice;
    @FXML private ChoiceBox<String> teamWinOrLoseChoice;
    @FXML private TextField currentTeamFinalScore;
    @FXML private TextField opponentTeamFinalScore;
    @FXML private Label wasGameStatsSavedProperly;

    //table view
    @FXML private Label gameInfo;

    @FXML private TableView<Player> teamTableView;
    @FXML private TableColumn<Player,String> playerNameColumn;
    @FXML private TableColumn<Player,Double> fantasyPointColumn;
    @FXML private TableColumn<Player,String> playerMinPlayedColumn;
    @FXML private TableColumn<Player,Integer> playerNumberPointColumn;
    @FXML private TableColumn<Player,Integer> playerNumberRebColumn;
    @FXML private TableColumn<Player,Integer> playerNumberAstColumn;
    @FXML private TableColumn<Player,Integer> playerNumberStlColumn;
    @FXML private TableColumn<Player,Integer> playerNumberBlkColumn;
    @FXML private TableColumn<Player,Integer> playerNumberTurnOColumn;
    @FXML private TableColumn<Player,Integer> playerNumberPersonalFColumn;

    public void initData(String teamName){
        System.out.println("TEAM NAME HERE IS "+ SelectedTeamName);

        teamNameLabel.setText(teamName);
        setUpTheColumnTable();
        populateChoiceBox(teamList);
        teamWinOrLoseChoice.getItems().addAll("W","L");

        pagination.setPageCount(85);
        pagination.setCurrentPageIndex(0);
        pagination.setMaxPageIndicatorCount(5);
        pagination.setPageFactory(this::createPage);
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
        wasGameStatsSavedProperly.setText("");
    }

    private Node createPage(Integer pageIndex) {
        TeamDAO teamDAO = DbConnection.getTeamDAO();
        ObservableList<Game> game = teamDAO.getAllGames4TheTeam(SelectedTeamName);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d");

        String gameInfos = game.get(pageIndex).getDateOfTheGame().format(formatter) + "\t\t vs " + game.get(pageIndex).getOpponentName()+"\t\t"+game.get(pageIndex).getTeamWinOrLoose()+" "+game.get(pageIndex).getTeamScore()+"-"+game.get(pageIndex).getOpponentScore();
        gameInfo.setText(gameInfos);
        teamTableView.setItems(game.get(pageIndex).getPlayerListStats());
        return teamTableView;
    }

    @FXML private void OnButtonPressedGetData(ActionEvent actionEvent) throws IOException {
        TeamDAO teamDAO = DbConnection.getTeamDAO();
        Game game = new Game();
        File submittedFilePath = getFileAbsPathFromStage(actionEvent); // get file path

        ObservableList<Player> playersStatsList = getPlayerStats4GameFromFilePath(submittedFilePath);

        String teamName = SelectedTeamName;
        System.out.println("Hello here from OnButtonPressedGetData METHOD SelectedTeamName => "+SelectedTeamName);
        game.setTeamName(teamName);
        game.setDateOfTheGame(dateOfTheGamePicker.getValue());
        game.setOpponentName(opponentTeamNameChoice.getValue());
        game.setTeamWinOrLoose(teamWinOrLoseChoice.getValue()); // W/L
        game.setTeamScore(Integer.parseInt(currentTeamFinalScore.getText()));
        game.setOpponentScore(Integer.parseInt(opponentTeamFinalScore.getText()));
        game.setPlayerListStats(playersStatsList);

        if(teamDAO.addGame(game)){
            wasGameStatsSavedProperly.setText("Game' Stats was saved.");
        }else{
            wasGameStatsSavedProperly.setText("Something went wrong. Try again");
        }
    }

    public void setSelectedTeamName(String selectedTeamName) {
        SelectedTeamName = selectedTeamName;
    }

    private File getFileAbsPathFromStage(javafx.event.ActionEvent actionEvent){
        //get the stage from the actionEvent parameter
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        File filePath = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add( new FileChooser.ExtensionFilter("Excel File", "*.csv"));
        File selectFile = fileChooser.showOpenDialog(window);
        if(selectFile != null){
            filePath = selectFile.getAbsoluteFile();
            System.out.println("Hello here form getFileAbsPathFromStage METHOD, the file name is "+filePath);
        }
        return filePath;
    }


    private void populateChoiceBox(String [] teamList){
        for(int i=0; i< teamList.length; i++){
            if(!SelectedTeamName.contains(teamList[i])){
                opponentTeamNameChoice.getItems().add(teamList[i]);
            }
        }
    }

    private ObservableList<Player> getPlayerStats4GameFromFilePath(File submitedFile) throws IOException{
        ObservableList <Player> playerList = null;
        CSVParser csvParser = new CSVParserBuilder().withSeparator(',').build(); // create delimiter

        CSVReader reader = new CSVReaderBuilder(new FileReader(submitedFile)).withCSVParser(csvParser).build();

        ColumnPositionMappingStrategy<Player> playerStrategy = new ColumnPositionMappingStrategy<>();
        playerStrategy.setType(Player.class);
        playerStrategy.setColumnMapping(new String [] {"playerName","MIN","FGM","FGA","FGPercent", "threePM", "threePA","threePercent","FTM","FTA","FTPercent","OREB","DREB","REBTotal","AST","STL","BLK","TO","PF","PTS","plusMinus"});

        CsvToBean<Player> csvToBean = new CsvToBean<Player>();

        List<Player> list = csvToBean.parse(playerStrategy,reader);

        playerList = FXCollections.observableList(list);

        return playerList;
    }

    private void setUpTheColumnTable(){
        playerNameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("playerName"));
        fantasyPointColumn.setCellValueFactory(new PropertyValueFactory<Player, Double>("FAN"));
        playerMinPlayedColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("MIN"));
        playerNumberPointColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("PTS"));
        playerNumberRebColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("REBTotal"));
        playerNumberAstColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("AST"));
        playerNumberStlColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("STL"));
        playerNumberBlkColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("BLK"));
        playerNumberTurnOColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("TO"));
        playerNumberPersonalFColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("PF"));
    }

}
