drop database if exists fantasy;
create database fantasy;
use fantasy;


create table team (
    teamName varchar(25),
    constraint pk_team primary key(teamName)
);

create table game(
--  homeTeam == teamName
    gameID int auto_increment,
    homeTeam varchar(25),
    dateOfTheGame date,
    opponentName varchar(50),
    teamWinOrLoose varchar(3),
    teamScore int,
    opponentScore int,
    constraint pk_game primary key (gameID),
    constraint fk_game foreign key (homeTeam) references team(teamName)
);

create table player (
    gameID int,
    homeTeam varchar(25),
    playerName varchar(100),
    MINI varchar(100),
    FGM int,
    FGA int,
    FGPercent decimal(10,2),
    ThreePM int,
    ThreePA int,
    ThreePercent decimal(10,2),
    FTM int,
    FTA int,
    FTPercent decimal(10,2),
    OREB int,
    DREB int,
    REBTotal int,
    AST int,
    STL int,
    BLK int,
    TurnO int,
    PF int,
    PTS int,
    plusMinus int,
    FAN decimal(10,2),
    constraint fk_player foreign key (gameID) references game(gameID),
    constraint fk_player1 foreign key (homeTeam) references game(homeTeam)

);

# insert into player values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);
# select G.homeTeam, G.dateOfTheGame, G.opponentName , G.teamWinOrLoose ,G.teamScore , G.opponentScore, P.playerName, P.MINI,
#        P.FGM, P.FGA, P.FGPercent, P.ThreePM, P.ThreePA, P.ThreePercent, P.FTM, P.FTA, P.FTPercent, P.OREB , P.DREB , P.REBTotal,
#        P.AST, P.STL, P.BLK, P.TurnO, P.PF, P.PTS, P.plusMinus, P.FAN from game G join player P on G.homeTeam = P.homeTeam;
#
#select G.dateOfTheGame, G.opponentName , G.teamWinOrLoose ,G.teamScore , G.opponentScore, P.playerName, P.FAN, P.MINI, P.PTS, P.REBTotal,P.AST, P.STL, P.BLK, P.TurnO, P.PF  from game G join player P on G.homeTeam = P.homeTeam and G.gameID = P.gameID;
select P.playerName, P.FAN, P.MINI, P.PTS, P.REBTotal,P.AST, P.STL, P.BLK, P.TurnO, P.PF  from player P where P.gameID = ?;
#select * from game where homeTeam = 'Hawks';
#"select G.dateOfTheGame, G.opponentName , G.teamWinOrLoose ,G.teamScore , G.opponentScore, P.playerName, P.FAN, P.MINI, P.PTS, P.REBTotal,P.AST, P.STL, P.BLK, P.TurnO, P.PF  from game G join player P on G.homeTeam = P.homeTeam where G.homeTeam = ?;"
# # "Hawks","Celtics","Nets","Hornets","Bulls","Cavaliers","Mavericks","Nuggets","Pistons","Warriors","Rockets","Pacers","Clippers","Lakers","Grizzlies","Heat",
# "Bucks","Timberwolves","Pelicans","Knicks","Thunder","Magic","Sixers","Suns","Blazers","Kings","Spurs","Raptors","Jazz","Wizards"
#    private final String [] teamList = {"Hawks","Celtics","Nets","Hornets","Bulls","Cavaliers","Mavericks","Nuggets","Pistons","Warriors","Rockets","Pacers","Clippers","Lakers","Grizzlies","Heat","Buks","Timberwolves","Pelicans","Knicks","Thunder","Magic","Sixers","Suns","Blazers","Kings","Spurs","Raptors","Jazz","Wizards"};

insert into team value ('Wizards');

select * from game;
select * from team;
select * from player;
delete from game where gameID ='Hawks - G2';
drop table game;

insert into player values ('Nets - G26','Nets','ÔJoe¬†HarrisF','26:12','3','6','50','2','4','50','1','2','50','0','1','1','0','0','0','0','4','9','23');
