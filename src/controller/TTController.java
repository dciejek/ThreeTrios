package controller;

import java.awt.geom.Point2D;

import javax.swing.JOptionPane;

import model.Player;
import model.PlayingCard;
import model.ThreeTriosModel;
import view.ThreeTriosFrame;
import strategy.Play;

/**
 * Controller for a game of ThreeTrios. Takes in a player, a model
 * and a view.
 */
public class TTController implements ThreeTriosController {

  private final ThreeTriosFrame view;
  private final Player<PlayingCard> player;
  private final ThreeTriosModel<PlayingCard> model;

  /**
   * Constructs a ThreeTrios constructor with a model, player and view.
   * @param model   model to play with
   * @param player  player for this controller
   * @param view    view to play with
   */
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
    model.addTurnListener(this);
    view.addClickListener(this);
    view.makeVisible();
  }

  @Override
  public void handleCellClicked(int col, int row) {
    view.refresh();
    if (model.isGameOver()) {
      gameOverMessage();
      return;
    }
    //check that it is the players turn
    if (notPlayersTurn()) {
      return;
    }
    if (player.getPlay(model) != null && this.player.equals(model.getCurrentPlayer())) {
      Play play = player.getPlay(model);
      model.placeCard(play.handIdx, play.row, play.col);
      view.refresh();
      return;
    }

    //Check that it isnt the opponents hand
    else if (isCellInOpponentHand(row, col)) {
      return;
    }
    //If the cell selected is in the players hand update highlight
    else if (cardSelectedIsInHand(row, col)) {
      view.setHighlightedCard(new Point2D.Double(col, row));
      view.refresh();
      return;
    }
    //Check that if the cell is on the grid a card is selected
    //No card selected not yet implemented
    else if (noCardSelected() && gridClicked(row, col)) {
      return;
    }
    //Last case (card selected & grid clicked (playCard))
    try {
      model.placeCard((int) view.getHighlightedCard().getY(), row, col - 1);
      view.setHighlightedCard(null);
    } catch (NullPointerException e) {
      JOptionPane.showMessageDialog(view.getPanel(),
              "Please select a card from your hand first");
    } catch (IllegalArgumentException | IllegalStateException e) {
      if (e.getMessage().contains("over")) {
        gameOverMessage();
      } else {
        JOptionPane.showMessageDialog(view.getPanel(),
                "Selected card must be played to an empty card cell on the grid");
      }
    }
    view.refresh();
  }

  private void gameOverMessage() {
    JOptionPane.showMessageDialog(view.getPanel(), "Game over.\n" +
            "Winner: " + model.getWinner().getColor().toString());
  }

  @Override
  public void refresh() {
    view.refresh();
  }

  private boolean cardSelectedIsInHand(int row, int col) {
    if (playerHandIsLeftHand() && col == 0
        && row < player.getHand().size()) {
      return true;
    } else {
      return !playerHandIsLeftHand() && col == model.getRow(0).size() + 1
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
    //JOptionPane showMessage
    return !model.getCurrentPlayer().equals(player);
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
