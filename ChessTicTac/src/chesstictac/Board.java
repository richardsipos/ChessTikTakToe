package chesstictac;

import java.awt.Color;
import java.awt.Point;

public class Board {
    
    private boolean turn=true; //true => white's turn /// false => black's turn
    private boolean firstPressGood;
    private Point previous=null;
    private Field[][] board;
    private String winnerColor = "";
    
    private final int boardSize;
    
    /**
     * A tábla inicializálása. Ekkor kerülnek a sarkok a táblára.
     * @param boardSize Ekkora tábla kerül inicializálásra.
     */
    public Board(int boardSize) {
        this.firstPressGood = false;
        this.boardSize = boardSize;
        board = new Field[this.boardSize][this.boardSize];
        for (int i = 0; i < this.boardSize; ++i) {
            for (int j = 0; j < this.boardSize; ++j) {
                if(i==0 && j==0 ||  i==this.boardSize-1 && j==this.boardSize-1){
                    board[i][j] = new Field("white");
                    board[i][j].setColor(Color.WHITE);
                    //System.out.println(board[i][j].getColor());
                }else if(i==0 && j==this.boardSize-1 || i==this.boardSize-1 && j==0){
                    board[i][j] = new Field("black");
                    board[i][j].setColor(Color.BLACK);
                }else{
                    board[i][j] = new Field("");   
                }
                
            }
        }
    }
    /**
     * Ez a metódus dönti el, ha a játéknak vége van-e.
     * Ha a játéknak vége van, true val tér vissza, másként peddig false-al.
     * Ha a játéknak vége van, akkor a winnerColor változóba elmenti a nyertes változó színét, vagy hogy nem született nyertes.
     * 
     */
    public boolean isOver()
    {
        //vizszintes nyeres
        for (int i = 0; i < boardSize; i++) {
            int count=1;
            for (int j = 1; j < boardSize; j++) {
                
                if(board[i][j-1].getColor() == board[i][j].getColor() && board[i][j].getColor()!=Color.GRAY ){
                    count++;
                    if(count>=4){
                        if(board[i][j].getColor()==Color.BLACK){
                            winnerColor="Black";
                        }else{
                            winnerColor="White";
                        }  
                        return true;
                    }
                }else if(board[i][j].getColor()!=Color.GRAY){
                    count=1;
                }
                else{
                    count=0;
                }
                  

            }

        }
        //fuggoleges nyeres
        for (int j = 0; j < boardSize; j++) {
            int count=1;
            for (int i = 1; i < boardSize; i++) {
                
                if(board[i-1][j].getColor() == board[i][j].getColor() && board[i][j].getColor()!=Color.GRAY ){
                    count++;
                    if(count>=4){
                        if(board[i][j].getColor()==Color.BLACK){
                            winnerColor="Black";
                        }else{
                            winnerColor="White";
                        }  
                        return true;
                    }
                }else if(board[i][j].getColor()!=Color.GRAY){
                    count=1;
                }
                else{
                    count=0;
                }
                  

            }

        }
        //foatlo nyeres
        //fenti foatlo
        for(int i=0;i<boardSize-3;i++){
            int count = 0;
            if(board[0][i].getColor()==Color.BLACK || board[0][i].getColor()==Color.WHITE){
                count=1;
            }
            for(int j=1;j<boardSize-i;j++){ //ez biztos.
                if(board[j][j+i].getColor()==board[j-1][j+i-1].getColor() && board[j][j+i].getColor()!=Color.GRAY){
                    count++;
                    if(count>=4){
                        if(board[j][j+i].getColor()==Color.BLACK){
                            winnerColor="Black";
                        }else{
                            winnerColor="White";
                        }  
                        return true;
                    }
                }else if(board[j][j+i].getColor()!=Color.GRAY){
                    count=1;
                }else{
                    count=0;
                }
                
            }
        }
        //lenti foatlo
        for(int sorok=0;sorok<boardSize-3;sorok++){
            int count=0;
            if(board[sorok][0].getColor()==Color.BLACK || board[sorok][0].getColor()==Color.WHITE){
                count=1;
            }
            for(int i=1;i<boardSize-sorok;i++){
                if(board[i+sorok][i].getColor()==board[i+sorok-1][i-1].getColor() && board[i+sorok][i].getColor()!=Color.GRAY){
                    count++;
                    if(count>=4){
                        if(board[i+sorok][i].getColor()==Color.BLACK){
                            winnerColor="Black";
                        }else{
                            winnerColor="White";
                        }  
                        return true;
                    }
                }else if(board[i+sorok][i].getColor()!=Color.GRAY){
                    count=1;
                }else{
                    count=0;
                }
            }
        }
        //mellekatlo megoldasa
        //fenti mellekatlo
        for(int sorok=0;sorok<boardSize-3;sorok++){
            int count=0;
            if(board[0][boardSize-1-sorok].getColor()==Color.BLACK || board[0][boardSize-1-sorok].getColor()==Color.WHITE){
                count=1;
            }
            for(int j=boardSize-2;j>=sorok;j--){ 
                if(board[boardSize-j-1][j-sorok].getColor()==board[boardSize-j-2][j-sorok+1].getColor() && board[boardSize-j-1][j-sorok].getColor()!=Color.GRAY){
                    count++;
                    if(count>=4){
                        if(board[boardSize-j-1][j-sorok].getColor()==Color.BLACK){
                            winnerColor="Black";
                        }else{
                            winnerColor="White";
                        }  
                        return true;
                    }
                }else if(board[boardSize-j-1][j-sorok].getColor()!=Color.GRAY){
                    count=1;
                }else{
                    count=0;
                }
            }
        }
        
        //also mellekatlo
        for(int sorok=0;sorok<boardSize-3;sorok++){
            int count=0;
            if(board[sorok][boardSize-1].getColor()==Color.BLACK || board[sorok][boardSize-1].getColor()==Color.WHITE){
                count=1;
            }
            for(int j=boardSize-2;j>=sorok;j--){ 

                
                if(board[boardSize-j+sorok-2][j+1].getColor()==board[boardSize-j+sorok-1][j].getColor() && board[boardSize-j+sorok-1][j].getColor()!=Color.GRAY){
                    count++;
                    if(count>=4){
                        if(board[boardSize-j+sorok-1][j].getColor()==Color.BLACK){
                            winnerColor="Black";
                        }else{
                            winnerColor="White";
                        }  
                        return true;
                    } 
                }else if(board[boardSize-j+sorok-1][j].getColor()!=Color.GRAY){
                    count=1;
                }else{
                    count=0;
                }
            }
        }
        int countO=0;
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
               
                if(board[i][j].getColor()==Color.BLACK || board[i][j].getColor()==Color.WHITE){
                    countO++;
                }
            }
        }
        if(countO ==boardSize*boardSize){
            winnerColor="OffGame";
            return true;
        }
        
        return false;      
    }
        
    /**
     * Egy mezőt add vissza.
     * @param x A mező vizszintes koordinátája.
     * @param y A mező függőleges koordinátája
     * @return  Az x,y koordinátákkal rendelkező mező
     */
    public Field get(int x, int y) {
        return board[x][y];
    }
    /**
     * Egy mezőt add vissza.
     * @param point Egy 2 koordinátából álló pontot kap.
     * @return  Az x,y koordinátákkal rendelkező mező
     */
    public Field get(Point point) {
        int x = (int)point.getX();
        int y = (int)point.getY();
        return get(x, y);
    }
    /**
     * A pálya mérete
     * @return A pályának a mérete.
     */
    public int getBoardSize() {
        return boardSize;
    }
    /**
     * Eldönti kinek a sora van, ha a fehérnek akkor false, ha a feketének akkor true az értéke.
     * @return a játékos sorát
     */
    public boolean isTurn() {
        return turn;
    }
    /**
     * Az adott játékos sora.
     * @param turn megváltoztattja a játékos sorát.
     */
    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    /**
     * Meagadja, hogy melyik játékos sora van.
     * @return a játékos sora.
     */
    public boolean getTurn(){
        return this.turn;
    }
    /**
     * Ellenőrzi, ha előszőr egy olyan mezőre kattintunk, ahol van ló.
     * @return Visszadja ha van-e ló a mezőn.
     */
    public boolean isFirstPressGood() {
        return firstPressGood;
    }
    /** 
     * Beállítja az első lépés helyességét.
     * @param firstPressGood Helyes vagy helytelen volt a lépés.
     */
    public void setFirstPressGood(boolean firstPressGood) {
        this.firstPressGood = firstPressGood;
    }
    /**
     * Beállítja az ezelőtti pontot.
     * @param point egy pont.
     */
    public void setPoint(Point point){
        this.previous = point;
    }
    /**
     * Lekéri az ezelőtti pontot.
     */
    public Point getPoint(){
        return this.previous;
    }
    /**
     * Ellenőrzi, ha a ló lépés-e az adott lépés.
     * @param x vizszítes koordináta
     * @param y függőleges koordináta
     * @return a lépés hellyessége
     */
    public boolean isLmove(int x, int y){
        //tesztelesre idealis return true;
        if(previous.x == x-2 && previous.y==y-1 ||previous.x == x-2 && previous.y==y+1 || 
            previous.x == x-1 && previous.y==y-2 ||previous.x == x+1 && previous.y==y-2 ||
             previous.x == x-1 && previous.y==y+2 ||previous.x == x+1 && previous.y==y+2 ||
                previous.x == x+2 && previous.y==y-1 ||previous.x == x+2 && previous.y==y+1){
            return true;
        }
        return false;
    }
    /**
     * Nyerő szín visszadása.
     * @return nyerő színt adja vissza.
     */
    public String getWinnerColor(){
        return this.winnerColor;
    }
    
    
    
    
    
    

}