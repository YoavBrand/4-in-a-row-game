package Q1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FourInRowView extends JFrame {

	// Class final values
    private final int BUTTOM_PANEL_HEIGHT = 100;
    private final int COLUMNS_BUTTONS_HEIGHT = 50;
    private final String MAIN_FRAME_TITLE = "Four in a row game";
    private final int MAIN_FRAME_WIDTH = 500;
    private final int MAIN_FRAME_HEIGHT = 500;

    // Class Components
    private JButton[] columnButtons;
    private JButton clearButton = new JButton("Clear");
    private JButton closeButton = new JButton("Close Game");
    private JLabel playerTurnLabel = new JLabel();
    private JPanel gameBoardPanel = new JPanel();
    protected JFrame mainFrame = new JFrame();
    private FourInRowModel model;

    public FourInRowView(FourInRowModel model) {
    	
        this.model = model;
        PlayerColor[][] board = this.model.getBoard();
        this.columnButtons = new JButton[board[0].length];
        this.setplayerTurnLabel(model);
        
        //setting up the main frame
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setTitle(MAIN_FRAME_TITLE);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //setting up the game board panel
        gameBoardPanel.setLayout(new GridLayout(board.length, board[0].length));
        this.setGameBoard(board);

        //creaating panels for components
        JPanel columnsButtonsPanel = addButtonsToBoard(board);
        JPanel closeAndClearPanel = addcloseAndClearPanelToBoard();
        JPanel buttomPanel = new JPanel();
        
        //setting up the buttom panel with the game buttons
        buttomPanel.setLayout(new BorderLayout());
        buttomPanel.setPreferredSize(new Dimension(0,BUTTOM_PANEL_HEIGHT));
        buttomPanel.add(columnsButtonsPanel, BorderLayout.NORTH);
        buttomPanel.add(closeAndClearPanel, BorderLayout.CENTER);

        //adding all other configurations to main panel
        mainFrame.add(gameBoardPanel, BorderLayout.CENTER);
        mainFrame.add(buttomPanel, BorderLayout.SOUTH);
        mainFrame.add(playerTurnLabel, BorderLayout.NORTH);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.setSize(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
    }

   
    
    public JPanel addButtonsToBoard(PlayerColor[][] board) {
    	JPanel columnsButtonsPanel = new JPanel();
        columnsButtonsPanel.setLayout(new GridLayout(1, board[0].length));
        columnsButtonsPanel.setPreferredSize(new Dimension(0,COLUMNS_BUTTONS_HEIGHT));
        for (int i = 0; i < board[0].length; i++) {
            JButton columnButton = new JButton(String.valueOf(i + 1));
            columnButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            columnButtons[i] = columnButton;
            columnsButtonsPanel.add(columnButton);
        }
    	return columnsButtonsPanel;
    }
    
    public JPanel addcloseAndClearPanelToBoard() {
        JPanel closeAndClearPanel = new JPanel();
        closeAndClearPanel.setLayout(new FlowLayout());
        closeAndClearPanel.add(this.clearButton);
        closeAndClearPanel.add(this.closeButton);
        return closeAndClearPanel;
    }

    public void setplayerTurnLabel(FourInRowModel model) {
    	this.playerTurnLabel.setText("Current Turn: " + this.model.getTurn());
        this.playerTurnLabel.setBackground(this.model.getTurn().equals(PlayerColor.BLUE) ? Color.BLUE : Color.RED);
        this.playerTurnLabel.setOpaque(true);
    }

    public void setGameBoard(PlayerColor[][] board) {
        gameBoardPanel.removeAll();

        for (int i = board.length - 1; i >= 0; i--) {
            for (int j = 0; j < board[i].length; j++) {
               
                if (board[i][j] != null) {
                    Cell cell = new Cell();
                    if (board[i][j] == PlayerColor.RED) {
                        cell.setColor(Color.RED);
                    } 
                    if (board[i][j] == PlayerColor.BLUE) {
                        cell.setColor(Color.BLUE);
                    }
                    cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    gameBoardPanel.add(cell);
                } else {
                    JPanel cell = new JPanel();
                    cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    gameBoardPanel.add(cell);
                }
            }
        }
        gameBoardPanel.validate();
    }

    public void errMessage(String err) {
        JOptionPane.showMessageDialog(this, err);
    }

    public void winMessage(PlayerColor playerColor) {
        JOptionPane.showMessageDialog(this, String.format("The Winner is:\n%s wins the game!", playerColor));
    }

    public void makeOneColumnUnAvailable(int columnIndex) {
        this.columnButtons[columnIndex].setEnabled(false);
        columnButtons[columnIndex].setBackground(Color.darkGray);
		columnButtons[columnIndex].setOpaque(true);
    }

    public void makeAllColumnsUnAvailable() {
    	for(int i = 0; i < columnButtons.length; i++) {
    		columnButtons[i].setEnabled(false);
    		columnButtons[i].setBackground(Color.darkGray);
    		columnButtons[i].setOpaque(true);
    	}
    }

    public void makeColumsAvailable() {
     	for(int i = 0; i < columnButtons.length; i++) {
    		columnButtons[i].setEnabled(true);
    		columnButtons[i].setBackground(null);
     	}
     }
    
    //listeners
    public void addClearListener(ActionListener clear) {
        this.clearButton.addActionListener(clear);
    }
    
    public void addColumnListener(ActionListener columnListner) {
    	for(int i = 0; i < columnButtons.length; i++) {
    		columnButtons[i].addActionListener(columnListner);
    	}
    }
    public void addCloseButtonListener(ActionListener closeListner) {
    	closeButton.addActionListener(closeListner);
    }
}
