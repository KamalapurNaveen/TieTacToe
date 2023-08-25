import Model.*;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    Deque<Player> players;
    Board gameBoard;
    TicTacToeGame(){
        initializeGame();
    }
    public void initializeGame(){
        //JUST FOR THIS PURPOSE I AM CREATING TWO PLAYERS
        players=new LinkedList<>();
        PlayingPieceX crossPiece=new PlayingPieceX();
        Player player1=new Player("Player1",crossPiece);
        PlayingPieceO zeroPiece=new PlayingPieceO();
        Player player2=new Player("Player2",zeroPiece);
        players.add(player1);
        players.add(player2);
        gameBoard=new Board(3);
    }
    public  String startGame(){
        boolean noWinner=true;
        while(noWinner){
            Player currentPlayer=players.removeFirst();
            gameBoard.printBoard();
            List<Pair<Integer,Integer>> freeCells=gameBoard.getFreeCells();
            if(freeCells.size()==0){
                noWinner=false;
                continue;
            }
            System.out.print("Player: " + currentPlayer.playerName + " Enter row,column :" );
            Scanner sc=new Scanner(System.in);

            String s=sc.nextLine();
            String [] values;
            int inputRow;
            int inputColumn;
            try{
                values=s.split(",");
                inputRow=Integer.valueOf(values[0]);
                inputColumn=Integer.valueOf(values[1]);
            }
            catch (NumberFormatException exception){
                System.out.println("Input format mismatch");
                players.addFirst(currentPlayer);
                continue;
            }
            boolean pieceAddedSuccessfully= gameBoard.addPiece(inputRow,inputColumn,currentPlayer.playingPiece);
            if(!pieceAddedSuccessfully){
                System.out.println("Provided input cell is already filled ,try again :)");
                players.addFirst(currentPlayer);
                continue;
            }
            players.addLast(currentPlayer);
            boolean winner=isThereWinner(inputRow,inputColumn,currentPlayer.playingPiece.pieceType);
            if(winner){
                gameBoard.printBoard();
                return currentPlayer.playerName;
            }
        }
        return "Game Tied";
    }
    public  boolean isThereWinner(int row,int col,PieceType pieceType){
            boolean rowMatch=true;
            boolean colMatch=true;
            boolean diagonalMatch=true;
            boolean antiDiagonalMatch=true;
            // checking row wise
            for(int i=0;i< gameBoard.boardSize;i++){
                if(gameBoard.board[row][i]==null || gameBoard.board[row][i].pieceType!=pieceType){
                    rowMatch=false;
                    break;
                }
            }
            // col check
            for(int i=0;i< gameBoard.boardSize;i++){
                if(gameBoard.board[i][col]==null || gameBoard.board[i][col].pieceType!=pieceType){
                    colMatch=false;
                    break;
                }
            }
            for(int i=0,j=0;i<gameBoard.boardSize;i++,j++){
                if(gameBoard.board[i][j]==null || gameBoard.board[i][j].pieceType!=pieceType){
                    diagonalMatch=false;
                    break;
                }
            }
            for(int i=0,j=gameBoard.boardSize-1;i<gameBoard.boardSize;i++,j--){
                if(gameBoard.board[i][j]==null || gameBoard.board[i][j].pieceType!=pieceType){
                    antiDiagonalMatch=false;
                    break;
                }
            }

            return rowMatch || colMatch || diagonalMatch || antiDiagonalMatch;
    }
}
