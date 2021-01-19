package iva.MAIN;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import iva.MODELS.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class getDataController implements Initializable {


    @FXML private void OnButtonPressedGetData(ActionEvent actionEvent) throws IOException {

        File submittedFilePath = getFileAbsPathFromStage(actionEvent); // get file path
        CSVParser csvParser = new CSVParserBuilder().withSeparator(',').build(); // create delimiter

        CSVReader reader = new CSVReaderBuilder(new FileReader(submittedFilePath)).withCSVParser(csvParser).build();

        ColumnPositionMappingStrategy<Player> playerStrategy = new ColumnPositionMappingStrategy<>();
        playerStrategy.setType(Player.class);
        playerStrategy.setColumnMapping(new String [] {"playerName","MIN","FGM","FGA","FGPercent", "threePM", "threePA","threePercent","FTM","FTA","FTPercent","OREB","DREB","REBTotal","AST","STL","BLK","TO","PF","PTS","plusMinus"});

        CsvToBean<Player> csvToBean = new CsvToBean<Player>();

        List<Player> playerArrayList = csvToBean.parse(playerStrategy,reader);

        System.out.println("here inside of OnButtonPressedGetData METHOD size is "+playerArrayList.size());
        for (Player p : playerArrayList){
            p.setTotalFantasyPoints();
            System.out.println(p.getPlayerName() +" " + p.getFAN());
        }


    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML private File getFileAbsPathFromStage(javafx.event.ActionEvent actionEvent){
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


}
