package iva.MODELS;

import javafx.collections.ObservableList;


public class Team {
    //properties
    private String name;
    private ObservableList<Game> listOfGames;

    //constructor

    //getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObservableList<Game> getListOfGames() {
        return listOfGames;
    }

    public void setListOfGames(ObservableList<Game> listOfGames) {
        this.listOfGames = listOfGames;
    }
    //number of games this team has
    public int getNumberOfGames4Team(){
        return listOfGames.size();
    }
}
