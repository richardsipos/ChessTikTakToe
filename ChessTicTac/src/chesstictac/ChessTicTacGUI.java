package chesstictac;




import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ChessTicTacGUI {

    private static JFrame frame;
    private static BoardGUI boardGUI;
    private static int[] boardSizes;
    private static final int INITIAL_BOARD_SIZE = 6;

    /**
     * A teljes játékablak inicializálása.
     */
    public ChessTicTacGUI() {
        frame = new JFrame("Chess Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardGUI = new BoardGUI(INITIAL_BOARD_SIZE);
        frame.getContentPane().add(boardGUI.getBoardPanel(), BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);
        JMenu newMenu = new JMenu("New");
        gameMenu.add(newMenu);
        int[] boardSizes = new int[]{4,6,8};
        for (int boardSize : boardSizes) {
            JMenuItem sizeMenuItem = new JMenuItem(boardSize + "x" + boardSize);
            newMenu.add(sizeMenuItem);
            sizeMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    reset(boardSize);
                }
            });
        }
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        gameMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        frame.pack();
        frame.setVisible(true);
    }
    /**
     * A játékot megjelenítő ablak reinicializálása.
     * @param boardSize a játéktábla mérete
     */
    public static void reset(int boardSize){
        frame.getContentPane().remove(boardGUI.getBoardPanel());
        boardGUI = new BoardGUI(boardSize);
        frame.getContentPane().add(boardGUI.getBoardPanel(),
                BorderLayout.CENTER);
        frame.pack();
    }
    
}
