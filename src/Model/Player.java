package Model;

import java.time.Period;

public class Player {
    public String playerName;
    public PlayingPiece playingPiece;
    public Player(String name,PlayingPiece pieceType){
        this.playerName=name;
        this.playingPiece=pieceType;
    }


    public  String getPlayerName(){
        return  playerName;
    }
    public  void setPlayerName(String name){
        this.playerName=name;
    }
    public PlayingPiece getPlayingPiece(){
        return playingPiece;
    }
    public void setPlayingPiece(PlayingPiece pieceType){
        this.playingPiece=pieceType;
    }

}
