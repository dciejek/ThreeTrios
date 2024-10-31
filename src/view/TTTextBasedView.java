package view;

import model.PlayingCard;
import model.TTPlayer;
import model.ThreeTriosModel;

/**
 * A text based view for a game of three trios.
 */
public class TTTextBasedView implements ThreeTriosView<PlayingCard> {
  ThreeTriosModel<PlayingCard> model;

  /**
   * Constructor for a text based view.
   * @param model the model of a game of three trios
   */
  public TTTextBasedView(ThreeTriosModel<PlayingCard> model) {
    this.model = model;
  }

  /**
   * A toString for a text based view.
   * @return a string representation of a game of three trios
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Player: ").append(model.getCurrentPlayer().toString()).append("\n");
    for (int row = 0; row < model.getGrid().size(); row++) {
      for (int col = 0; col < model.getRow(0).size(); col++) {
        sb.append(model.getRow(row).get(col).toString());
      }
      sb.append("\n");
    }
    sb.append("Hand: ").append("\n");
    for (int idx = 0; idx < model.getCurrentPlayer().getHand().size(); idx++) {
      sb.append(model.getCurrentPlayer().getHand().get(idx).toString()).append("\n");
    }
    return sb.toString();
  }
}
