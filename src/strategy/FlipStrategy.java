package strategy;

import java.util.ArrayList;
import java.util.List;

import model.Card;
import model.Cell;
import model.Player;
import model.ThreeTriosModel;

public class FlipStrategy implements TTStrategy {

  @Override
  public Play playToPoint(ThreeTriosModel model, Player player) {
    return playToPointHelper(model, player);
  }

  private Play playToPointHelper(ThreeTriosModel<Card> model, Player<Card> player) {
    List<Play> plays = new ArrayList<Play>();
    ArrayList<Card> hand = player.getHand();
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
