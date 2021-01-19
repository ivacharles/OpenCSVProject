package iva.MODELS;

import javafx.collections.ObservableList;

import java.time.LocalDate;

public class Game {
    //properties
    private String teamName;
    private LocalDate dateOfTheGame;
    private String opponentName;
    private String teamWinOrLoose;
    private int teamScore;
    private int opponentScore;
    private ObservableList<Player> playerListStats;

    //constructor
    public Game(String teamName, LocalDate dateOfTheGame, String opponentName, String teamWinOrLoose, int teamScore, int opponentScore, ObservableList<Player> playerListStats) {
        this.teamName = teamName;
        this.dateOfTheGame = dateOfTheGame;
        this.opponentName = opponentName;
        this.teamWinOrLoose = teamWinOrLoose;
        this.teamScore = teamScore;
        this.opponentScore = opponentScore;
        this.playerListStats = playerListStats;
    }

    //getter and setters
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public LocalDate getDateOfTheGame() {
        return dateOfTheGame;
    }

    public void setDateOfTheGame(LocalDate dateOfTheGame) {
        this.dateOfTheGame = dateOfTheGame;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public String getTeamWinOrLoose() {
        return teamWinOrLoose;
    }

    public void setTeamWinOrLoose(String teamWinOrLoose) {
        this.teamWinOrLoose = teamWinOrLoose;
    }

    public int getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(int teamScore) {
        this.teamScore = teamScore;
    }

    public int getOpponentScore() {
        return opponentScore;
    }

    public void setOpponentScore(int opponentScore) {
        this.opponentScore = opponentScore;
    }

    public ObservableList<Player> getPlayerListStats() {
        return playerListStats;
    }

    public void setPlayerListStats(ObservableList<Player> playerListStats) {
        this.playerListStats = playerListStats;
    }
}
