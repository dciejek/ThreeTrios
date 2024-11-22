package controller;

import java.awt.geom.Point2D;

import model.Player;
import model.PlayingCard;
import model.ThreeTriosModel;
import view.ThreeTriosFrame;

public class TTController implements ThreeTriosController {

  private final ThreeTriosFrame view;
  private final Player<PlayingCard> player;
  private final ThreeTriosModel<PlayingCard> model;

  public TTController(ThreeTriosModel<PlayingCard> model,
                      Player<PlayingCard> player,
                      ThreeTriosFrame view) {
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    } else if (player == null) {
      throw new IllegalArgumentException("player cannot be null");
    } else if (view == null) {
      throw new IllegalArgumentException("view cannot be null");
    }
    this.model = model;
    this.player = player;
    this.view = view;
  }

  @Override
  public void playGame() {
    view.addClickListener(this);
    view.makeVisible();
  }

  @Override
  public void handleCellClicked(int row, int col) {
    //check that it is the players turn
    if (notPlayersTurn()) {
      return;
    }
    //Check that it isnt the opponents hand
    else if (isCellInOpponentHand(row, col)) {
      return;
    }
    //If the cell selected is in the players hand update highlight
    else if (cardSelectedIsInHand(row, col)) {
      view.setHighlightedCard(new Point2D.Double(row, col));
      view.refresh();
      return;
    }
    //Check that if the cell is on the grid a card is selected
    //No card selected not yet implemented
    else if (noCardSelected() && gridClicked(row, col)) {
      return;
    }
    //Last case (card selected & grid clicked (playCard))
    model.placeCard((int) view.getHighlightedCard().getY(), row, col);
    view.refresh();
  }

  private boolean cardSelectedIsInHand(int row, int col) {
    if (playerHandIsLeftHand() && col == 0
        && row < player.getHand().size()) {
      return true;
    } else {
      return !playerHandIsLeftHand() && col == model.getRow(0).size() + 2
              && row < player.getHand().size();
    }
  }

  private boolean gridClicked(int row, int col) {
    return row > 0 && row <= model.getGrid().size() && col >= 1 && col <= model.getRow(0).size();
  }

  private boolean noCardSelected() {
    return view.getHighlightedCard() == null;
  }

  private boolean notPlayersTurn() {
    if (!model.getCurrentPlayer().equals(player)) {
      //JOptionPane showMessage
      return true;
    }
    return false;
  }


  private boolean isCellInOpponentHand(int row, int col) {
    //We don't care if there is/isn't a card in that row since it's already the wrong hand.
    if (playerHandIsLeftHand() && col == model.getRow(0).size() + 2) {
      //This aint yo hand twin
      return true;
    } else if (!playerHandIsLeftHand() && col == 0) {
      //this aint yo hand twin
      return true;
    }
    return false;
  }

  private boolean playerHandIsLeftHand() {
    return player.equals(model.getPlayerOne());
  }
}
