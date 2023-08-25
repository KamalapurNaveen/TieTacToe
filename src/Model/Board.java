package Model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public int boardSize;
    public  PlayingPiece[][] board;
    public Board(int size){
        this.boardSize=size;
        this.board=new PlayingPiece[size][size];
    }
    public boolean addPiece(int row,int column,PlayingPiece playingPiece){
        if(board[row][column]!=null){
            return false;
        }
        board[row][column]=playingPiece;
        return true;
    }
    public List<Pair<Integer,Integer>> getFreeCells(){
        List<Pair<Integer,Integer>> freeCells=new ArrayList<>();
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                if(board[i][j]==null){
                    freeCells.add(new Pair<>(i,j));
                }
            }
        }
        return  freeCells;
    }
    public  void printBoard(){
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                if (board[i][j] != null) {
                    System.out.print(board[i][j].pieceType.name() + "   ");
                }
                else {
                    System.out.print("    ");
                }
                System.out.print(" | ");
            }
            System.out.println();
        }

    }
}
