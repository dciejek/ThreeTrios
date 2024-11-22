package strategy;

import java.util.ArrayList;
import java.util.List;

import model.Card;
import model.Cell;
import model.Player;
import model.ReadOnlyThreeTriosModel;
import model.ThreeTriosModel;

/**
 * Strategy to pick the spot where the most cards will be flipped as a result. Defaults
 * to the top left-most move if there is a tie, and uses the same logic and the card at
 * index 0 in the player's hand if there is no best move.
 */
public class FlipStrategy implements TTStrategy<Card> {

  @Override
  public Play playToPoint(ReadOnlyThreeTriosModel<Card> model, Player<Card> player) {
    List<Play> plays = new ArrayList<Play>();
    Cell currCell;
    int currHighest = 0;
    for (int row = 0; row < model.getGrid().size(); row++) {
      for (int col = 0; col < model.getRow(0).size(); col++) {
        currCell = model.getCellAt(row, col);
        if (currCell.canPlayHere()) {
          for (int cardIdx = 0; cardIdx < player.getHand().size(); cardIdx++) {
            if (model.numFlipped(player.getHand().get(cardIdx), row, col) >= currHighest) {
              if (model.numFlipped(player.getHand().get(cardIdx), row, col) != currHighest) {
                plays.clear();
                currHighest = model.numFlipped(player.getHand().get(cardIdx), row, col);
              }
              plays.add(new Play(row, col, currHighest));
            }
          }
        }
      }
    }
    return plays.get(0);
  }
}
