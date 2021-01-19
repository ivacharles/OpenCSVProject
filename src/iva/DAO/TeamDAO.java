package iva.DAO;

import iva.MODELS.Game;
import javafx.collections.ObservableList;

public interface TeamDAO {

    ObservableList<Game> getAllGames4TheTeam(String teamName);

    boolean addGame(Game game);
}
