package chesstictac;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;


public class BoardGUI {

    private JButton[][] buttons;
    private Board board;
    private JPanel boardPanel;

    private int boardSize;
    
    private ImageIcon iconBlackKnight = new ImageIcon("blackKnight.png");
    private ImageIcon iconWhiteKnight = new ImageIcon("whiteKnight.png");
    /**
     * AZ osztály konstruktora, amely meghívja a játéktábla inicializálását.
     * @param boardSizeGet A tábla mérete
     */
    public BoardGUI(int boardSizeGet) {
        boardSize=boardSizeGet;
        initialise(boardSize);  //initialise ujrakezdese
    }
    /**
     * A játék inicializálása.
     * @param boardSize A játéktábla mérete.
     */
    public void initialise(int boardSize){

        board = new Board(boardSize);
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(board.getBoardSize(), board.getBoardSize()));
        buttons = new JButton[board.getBoardSize()][board.getBoardSize()];
        System.out.println(board.get(0,0).getColor());
        
        for (int i = 0; i < board.getBoardSize(); ++i) {
            for (int j = 0; j < board.getBoardSize(); ++j) {
                JButton button = new JButton();
                
                if(i==0 && j==0 || i==board.getBoardSize()-1 && j==board.getBoardSize()-1){
                    button.setBackground(Color.WHITE);
                    button.setIcon(iconWhiteKnight);
                    //button.setText("♘");
                }
                else if(i==board.getBoardSize()-1 && j==0 || i==0 && j==board.getBoardSize()-1){
                    button.setBackground(Color.BLACK);
                    button.setIcon(iconBlackKnight);
                    //button.setText("♞");
                }else{
                    button.setBackground(Color.GRAY);
                    board.get(i,j).setColor(Color.gray);
                }
                
                
                button.addActionListener(new ButtonListener(i, j));
                button.setPreferredSize(new Dimension(40, 40));
                
                buttons[i][j] = button;
               
                boardPanel.add(button);
            }
        }
    }
    

    public void refresh(int x, int y) {
        JButton button = buttons[x][y];
        Field field = board.get(x, y);
        if (field.getColor() == Color.WHITE) {
            if(field.getIcon().equals("white")){
                //button.setText("♘");
                button.setBackground(Color.WHITE);

            }
        }else if (field.getColor() == Color.BLACK){
            if(field.getIcon().equals("black")){
                //button.setText("♞");
                button.setBackground(Color.BLACK);
            }
        }
    }
    /**
     * A megjelenített ablak paneljének a visszadása.
     * @return 
     */
    public JPanel getBoardPanel() {
        return boardPanel;
    }
    /**
     * A kattintásra figyelő Listener. Ha kattintást érzékel, lefut.
     */
    class ButtonListener implements ActionListener {

        private int x, y;

        public ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Color white = Color.WHITE;
            Color black = Color.BLACK;
  
           if((board.get(x, y).getIcon().equals("white")) && (board.getTurn()==false) || (board.get(x, y).getIcon().equals("black")) && (board.getTurn()==true)){
               System.out.println("Illegal square selected");
           }else
           {
               if (board.get(x, y).getIcon().equals("white")  
                     && board.getTurn()==true && board.isFirstPressGood()==false){
                
                System.out.println("Feher kivalaszt");

                board.setPoint(new Point(x,y));
                
                
                board.get(x, y).setIcon("");
                board.setFirstPressGood(true); 
                buttons[x][y].setIcon(null);
                }
                else if ((!(board.get(x, y).getIcon() == "white") || (!(board.get(x, y).getIcon() == "black"))  )&&
                        board.getTurn()==true && board.isFirstPressGood()==true && board.isLmove(x, y)) 
                {
                    System.out.println("Feheret lerak");
                    
                    
                    
                    board.get(x, y).setColor(white);
                    board.get(x, y).setIcon("white");
                    board.setFirstPressGood(false);
                    board.setTurn(false);
                    board.setPoint(null);
                    buttons[x][y].setIcon(iconWhiteKnight);
                }else if(board.get(x, y).getIcon().equals("black")  && board.getTurn()==false && board.isFirstPressGood()==false){
                    System.out.println("Fekete kivalaszt");
                    

                    board.setPoint(new Point(x,y));
                    board.get(x, y).setIcon("");
                    board.setFirstPressGood(true);
                    buttons[x][y].setIcon(null);
                }else if((!(board.get(x, y).getIcon() == "white") || (!(board.get(x, y).getIcon() == "black"))  ) && board.getTurn()==false && board.isFirstPressGood()==true && board.isLmove(x, y)){
                    System.out.println("Fekete lerak");
                    
                    
                    
                    
                    board.get(x, y).setColor(black);
                    board.get(x, y).setIcon("black");
                    board.setPoint(null);
                    board.setFirstPressGood(false);
                    board.setTurn(true);
                    buttons[x][y].setIcon(iconBlackKnight);
                }
           }
             
            
            refresh(x, y);
            if (board.isOver()) {
                if(board.getWinnerColor()=="OffGame"){
                    JOptionPane.showMessageDialog(boardPanel, "The game is over! Nobody has won.", "GAMEOVER!",
                        JOptionPane.PLAIN_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(boardPanel, "The "+board.getWinnerColor()+" color has won the game. ", "Congrats!",
                        JOptionPane.PLAIN_MESSAGE);
                }
                
                resetGame();

                             
            }
        }
    }
    /**
     * A játék ujrakezdéséért felelős metódus.
     */
    public void resetGame(){
        ChessTicTacGUI.reset(6);
        initialise(boardSize);
    }
        
}