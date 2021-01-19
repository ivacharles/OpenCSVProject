package iva.MODELS;

import javafx.beans.property.SimpleStringProperty;

public class Player {
    //properties
    private final double REBOUND = 1.2;
    private final double ASSIST = 1.5;
    private final double STEAL = 3;
    private final double BLOCK = 3;
    private final double TURNOVER = 1;

    //private String POS; //position
    private int gameID; // gameId for the game
    private SimpleStringProperty teamName; // team player played for
    private SimpleStringProperty playerName;
    private SimpleStringProperty MIN; // minute played
    private int FGM; // field goal made
    private int FGA; // field goal attended
    private double FGPercent; // field goal percentage
    private int ThreePM; // 3 point made
    private int ThreePA; // 3 point attended
    private double ThreePercent; // 3 point percentage
    private int FTM; // FREE throws point made
    private int FTA; // FREE throws point attended
    private double FTPercent; // FREE throws percentage
    private int OREB; // offensive rebounds
    private int DREB; // defensive rebounds
    private int REBTotal = 0; // totals rebounds
    private int AST = 0; // assist
    private int STL = 0; // steals
    private int BLK = 0; // block
    private int TO = 0; // turn over
    private int PF; // personal foul
    private int PTS = 0; // podoubles
    private int plusMinus; // plusMinus (+/-)
    private double FAN; // fantasy podouble

    public Player(){}
    public Player(String playerName, double fan, String min, int pts, int rebTotal, int ast, int stl, int blk, int to, int pf) {
        this.playerName = new SimpleStringProperty(playerName);
        this.MIN = new SimpleStringProperty(min);
        this.REBTotal = rebTotal;
        this.AST = ast;
        this.STL = stl;
        this.BLK = blk;
        this.TO = to;
        this.PF = pf;
        this.PTS = pts;
        this.FAN = fan;
        setTotalFantasyPoints();
    }


    //Methods
    public void setTotalFantasyPoints(){
        FAN = PTS + (REBTotal*REBOUND)+ (AST *ASSIST)+(STL*STEAL)+(BLK*BLOCK)-(TO*TURNOVER);
    }

    //getters and setters


    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getTeamName() {
        return teamName.get();
    }

    public SimpleStringProperty teamNameProperty() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = new SimpleStringProperty(teamName);
    }

    public String getPlayerName() {
        return playerName.get();
    }

    public SimpleStringProperty playerNameProperty() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = new SimpleStringProperty(playerName);
    }

    public String getMIN() {
        return MIN.get();
    }

    public SimpleStringProperty MINProperty() {
        return MIN;
    }

    public void setMIN(String MIN) {
        this.MIN = new SimpleStringProperty(MIN);
    }

    public int getFGM() {
        return FGM;
    }

    public void setFGM(int FGM) {
        this.FGM = FGM;
    }

    public int getFGA() {
        return FGA;
    }

    public void setFGA(int FGA) {
        this.FGA = FGA;
    }

    public double getFGPercent() {
        return FGPercent;
    }

    public void setFGPercent(double FGPercent) {
        this.FGPercent = FGPercent;
    }

    public int getThreePM() {
        return ThreePM;
    }

    public void setThreePM(int threePM) {
        ThreePM = threePM;
    }

    public int getThreePA() {
        return ThreePA;
    }

    public void setThreePA(int threePA) {
        ThreePA = threePA;
    }

    public double getThreePercent() {
        return ThreePercent;
    }

    public void setThreePercent(double threePercent) {
        ThreePercent = threePercent;
    }

    public int getFTM() {
        return FTM;
    }

    public void setFTM(int FTM) {
        this.FTM = FTM;
    }

    public int getFTA() {
        return FTA;
    }

    public void setFTA(int FTA) {
        this.FTA = FTA;
    }

    public double getFTPercent() {
        return FTPercent;
    }

    public void setFTPercent(double FTPercent) {
        this.FTPercent = FTPercent;
    }

    public int getOREB() {
        return OREB;
    }

    public void setOREB(int OREB) {
        this.OREB = OREB;
    }

    public int getDREB() {
        return DREB;
    }

    public void setDREB(int DREB) {
        this.DREB = DREB;
    }

    public int getREBTotal() {
        return REBTotal;
    }

    public void setREBTotal(int REBTotal) {
        this.REBTotal = REBTotal;
    }

    public int getAST() {
        return AST;
    }

    public void setAST(int AST) {
        this.AST = AST;
    }

    public int getSTL() {
        return STL;
    }

    public void setSTL(int STL) {
        this.STL = STL;
    }

    public int getBLK() {
        return BLK;
    }

    public void setBLK(int BLK) {
        this.BLK = BLK;
    }

    public int getTO() {
        return TO;
    }

    public void setTO(int TO) {
        this.TO = TO;
    }

    public int getPF() {
        return PF;
    }

    public void setPF(int PF) {
        this.PF = PF;
    }

    public int getPTS() {
        return PTS;
    }

    public void setPTS(int PTS) {
        this.PTS = PTS;
    }

    public int getPlusMinus() {
        return plusMinus;
    }

    public void setPlusMinus(int plusMinus) {
        this.plusMinus = plusMinus;
    }

    public double getFAN() {
        return FAN;
    }

    public void setFAN(double FAN) {
        this.FAN = FAN;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName=" + playerName +
                ", MIN=" + MIN +
                ", REBTotal=" + REBTotal +
                ", AST=" + AST +
                ", STL=" + STL +
                ", BLK=" + BLK +
                ", TO=" + TO +
                ", PF=" + PF +
                ", PTS=" + PTS +
                ", FAN=" + FAN +
                '}';
    }
}
