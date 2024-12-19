package tic.tac.toe;

import java.util.Scanner;

import static tic.tac.toe.GameStatus.ONGOING;


public class Game {
    public static void main(String[] args) {

        Board board = new Board(3,3);
        Player player1 = new Player('X');
        Player player2 = new Player('0');
        GameStatus status = ONGOING;
        int count = 0;
        Scanner sc = new Scanner(System.in);
        playGame(sc,player1,player2,board,count,status);
    }

    public static void playGame(Scanner sc,Player player1,Player player2,Board board,int count,GameStatus status) {
        do{
            System.out.println("player1-"+player1.marker+" input board row and column less than "+board.b.length+" and greater than equals to zero");
            int r = sc.nextInt();
            int c = sc.nextInt();
            board.makeMove(board,r,c,player1.marker);
            count++;
            status = board.gameStatus(board,count);

            if(status== ONGOING){
                System.out.println("player2-"+player2.marker+" input board row and column");
                r = sc.nextInt();
                c = sc.nextInt();
                board.makeMove(board,r,c,player2.marker);
                count++;
            }



        }while(status== ONGOING && count<9);

        board.displayGameBoard(board);

        if(status==status.DRAW) System.out.println("The game is "+status);
        else System.out.println("The game is won By"+status);

    }




}




class Board{
    char[][] b;
    Board(int m,int n){
        b = new char[m][n];
    }
    GameStatus status;

    //Make move function
    //board is full
    //reset board
    //display board
    public void displayGameBoard(Board board){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(board.b[i][j]+" ");
            }
            System.out.println();
        }
    }

    public void makeMove(Board board,int r,int c,char symbol){
        board.b[r][c] = symbol;
    }

    public GameStatus gameStatus(Board board,int count){

        char res = rowCheck(board);
        res = res=='N'?colCheck(board):res;
        res = res=='N'?diagonalCheck(board):res;

        if(res!='N'){
            return res=='X'?status.X_WINS:status.O_WINS;
        }

        if(count==9) return status.DRAW;

        return ONGOING;

    }

    public char rowCheck(Board board){
        int p1=0, p2=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board.b[i][j]=='X')p1++;
                else if(board.b[i][j]=='0')p2++;

            }
            if(p1==3)return 'X';
            if(p2==3)return '0';
            p1=0;
            p2=0;
        }
        return 'N';
    }

    public char colCheck(Board board){
        int p1=0,p2=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board.b[j][i]=='X')p1++;
                else if(board.b[j][i]=='0')p2++;
            }
            if(p1==3)return 'X';
            if(p2==3)return '0';
            p1=0;
            p2=0;
        }
        return 'N';
    }

    public char diagonalCheck(Board board){
        int p1=0,p2=0;
        for(int i=0;i<3;i++){
            if(board.b[i][i]=='X')p1++;
            else if(board.b[i][i]=='0')p2++;
            if(p1==3)return 'X';
            if(p2==3)return '0';
        }
        p1=0;
        p2=0;
        for(int i=2;i>=0;i--){
            if(board.b[2-i][i]=='X')p1++;
            else if(board.b[i][i]=='0')p2++;
            if(p1==3)return 'X';
            if(p2==3)return '0';
        }
        return 'N';
    }
}

class Player{
    Character marker;

    // Name variable
    Player(Character m){
        this.marker = m;
    }
}



