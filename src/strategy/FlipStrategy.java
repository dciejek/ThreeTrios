package strategy;

import java.util.ArrayList;
import java.util.List;

import model.Card;
import model.Cell;
import model.Player;
import model.ThreeTriosModel;

/**
 * Strategy to play to the Cell where this player will flip the most cards. To break ties,
 * it picks the top left most option out of the best options.
 */
public class FlipStrategy implements TTStrategy<Card> {

  @Override
  public Play playToPoint(ThreeTriosModel<Card> model, Player<Card> player) {
    List<Play> plays = new ArrayList<Play>();
    List<Card> hand = player.getHand();
    Cell<Card> currCell;
    int currHighest = 0;
    for (int row = 0; row < model.getGrid().size(); row++) {
      for (int col = 0; col < model.getRow(0).size(); col++) {
        currCell = model.getCellAt(row, col);
        if (currCell.canPlayHere()) {
          for (int cardIdx = 0; cardIdx < hand.size(); cardIdx++) {
            if (model.numFlipped(hand.get(cardIdx), row, col) >= currHighest) {
              if (model.numFlipped(hand.get(cardIdx), row, col) != currHighest) {
                plays.clear();
                currHighest = model.numFlipped(hand.get(cardIdx), row, col);
              }
              plays.add(new Play(row, col, cardIdx));
            }
          }
        }
      }
    }
    return plays.get(0);
  }
}
