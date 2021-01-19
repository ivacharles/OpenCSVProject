package iva.DAO;

import iva.DbUtility.DbConnection;
import iva.MODELS.Game;
import iva.MODELS.Player;
import javafx.collections.ObservableList;

import java.sql.*;

public class TeamDAOImplementation implements TeamDAO{

    Connection connection = null;	// Our connection to the database
    PreparedStatement stmt = null;	// We use prepared statements to help protect against SQL injection
    ResultSet rs = null;
    /*------------------------------------------------------------------------------------------------*/



    @Override
    public ObservableList<Game> getAllGames4TheTeam(String teamName) {
        return null;
    }

    @Override
    public boolean addGame(Game gameStats) {
        try {
            connection = DbConnection.establishConnection(); // establish a connection
            connection.setAutoCommit(false);

            String gameSQL = "insert into game values (?,?,?,?,?,?);";
            stmt = connection.prepareStatement(gameSQL, Statement.RETURN_GENERATED_KEYS);

            //set all ? before we execute
            stmt.setString(1,gameStats.getTeamName());
            stmt.setObject(2, gameStats.getDateOfTheGame());
            stmt.setString(3,gameStats.getOpponentName());
            stmt.setString(4,gameStats.getTeamWinOrLoose());
            stmt.setInt(5,gameStats.getTeamScore());
            stmt.setInt(6,gameStats.getOpponentScore());

            int rowAffected = stmt.executeUpdate();

            //get generated key bak
            rs = stmt.getGeneratedKeys();
            int gameId = 0;
            if(rs.next())
                gameId = rs.getInt(1);

            //store list of players
            if(rowAffected == 1){
                addPlayersStats(gameStats.getPlayerListStats(),gameId, gameStats.getTeamName());
                connection.commit();
            }else {
                connection.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if(connection != null)
                    connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return false;
        } finally {
            DbConnection.closeResources(connection,stmt);
            try {
                if(rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private void addPlayersStats(ObservableList<Player> playerListStats, int gameID, String teamName) {
        for (int i=0; i<playerListStats.size(); i++){
            playerListStats.get(i).setGameID(gameID);
            playerListStats.get(i).setTeamName(teamName);
            playerListStats.get(i).setTotalFantasyPoints();
            addPlayerStat(playerListStats.get(i));
        }
    }

    private boolean addPlayerStat(Player player){

        try {
            connection = DbConnection.establishConnection(); // establish a connection

            String playerSQL = "insert into player values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            stmt = connection.prepareStatement(playerSQL);

            //set all ? before we execute
            stmt.setInt(1,player.getGameID());
            stmt.setString(2,player.getTeamName());
            stmt.setString(3,player.getPlayerName());
            stmt.setString(4,player.getMIN());
            stmt.setInt(5,player.getFGM());
            stmt.setInt(6,player.getFGA());
            stmt.setDouble(7,player.getFGPercent());
            stmt.setInt(8,player.getThreePM());
            stmt.setInt(9,player.getThreePA());
            stmt.setDouble(10,player.getThreePercent());
            stmt.setInt(11,player.getFTM());
            stmt.setInt(12,player.getFTA());
            stmt.setDouble(13,player.getFTPercent());
            stmt.setInt(14,player.getOREB());
            stmt.setInt(15,player.getDREB());
            stmt.setDouble(16,player.getREBTotal());
            stmt.setInt(17,player.getAST());
            stmt.setInt(18,player.getSTL());
            stmt.setInt(19,player.getBLK());
            stmt.setInt(20,player.getTO());
            stmt.setInt(21,player.getPF());
            stmt.setInt(22,player.getPTS());
            stmt.setInt(23,player.getPlusMinus());
            stmt.setDouble(24,player.getFAN());

            if (stmt.executeUpdate() != 0)
                return true;
            else
                return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DbConnection.closeResources(connection,stmt);
        }
    }
}
