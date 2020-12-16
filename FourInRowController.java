package Q1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FourInRowController {
    private FourInRowModel model;
    private FourInRowView view;

    public FourInRowController(FourInRowModel model, FourInRowView view) {
    	
    	// Setting up the components
    	this.model = model;
        this.view = view;

    	// add listeners to the view listeners methods
        view.addClearListener(new ClearButtonListener());
        view.addColumnListener(new ColumnButtonsListener());
        view.addCloseButtonListener(new CloseButtonListener());
    }

    //Listeners
    class ColumnButtonsListener implements ActionListener {
    	@Override
        public void actionPerformed(ActionEvent e) {
        	
    		//getting the column index that is being selected with minus 1 to fit the array numbering
            int index = Integer.parseInt(e.getActionCommand()) - 1;
            
            //updating the players turn and the board state
			model.putCellInGameBoard(model.getTurn(), index);
			view.setGameBoard(model.getBoard());
			view.setplayerTurnLabel(model);
			
			//if there is a wiiner, game is ended
			boolean isWin = model.checkWin(model.getTurn());
			if (isWin == true) {
			    view.winMessage(model.getTurn());
			    view.makeAllColumnsUnAvailable();
			   
			    //No winner, game continues 
			} else {
			    model.changeTurns();
			    view.setplayerTurnLabel(model);

			    //check if the column is full
			    boolean checkColumnFull = model.isColumnFull(index);
			    if (checkColumnFull == true) {
			        view.makeOneColumnUnAvailable(index);
			    }
			}
        }
    }
    
    class ClearButtonListener implements ActionListener {
    	@Override
        public void actionPerformed(ActionEvent e) {
    		
    		//reset the model board
            model.reset();
            
            //views the "new game" with a blank board
            view.setGameBoard(model.getBoard());
            view.setplayerTurnLabel(model);
            view.makeColumsAvailable();
        }
    }
    
    class CloseButtonListener implements ActionListener {

    	@Override
        public void actionPerformed(ActionEvent e) {
    		
    		// dispose the main frame and "closes" the game
           view.mainFrame.dispose();
        }
    }
}
