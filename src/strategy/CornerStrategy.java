package strategy;

import java.util.ArrayList;
import java.util.List;

import model.Card;
import model.Player;
import model.ThreeTriosModel;

public class CornerStrategy implements TTStrategy<Card> {
  private int highest = Integer.MIN_VALUE;

  @Override
  public Play playToPoint(ThreeTriosModel<Card> model, Player<Card> player) {
    List<Play> plays = new ArrayList<Play>();
    for (int row = 0; row < model.getGrid().size(); row+= model.getGrid().size() - 1) {
      for (int col = 0; col < model.getRow(0).size(); col+= model.getRow(0).size() - 1) {
        if (model.getCellAt(row, col).canPlayHere()) {
          plays.add(getBestMove(model, player, row, col));
        }
      }
    }
    if (plays.isEmpty()) {
      return lastResort(model, player);
    }
    return plays.get(0);
  }

  private Play lastResort(ThreeTriosModel<Card> model, Player<Card> player) {
    for (int row = 0; row < model.getGrid().size(); row+= model.getGrid().size() - 1) {
      for (int col = 0; col < model.getRow(0).size(); col+= model.getRow(0).size() - 1) {
        if (model.getCellAt(row, col).canPlayHere()) {
          return new Play(row, col, 0);
        }
      }
    }
    return null;
  }

  private Play getBestMove(ThreeTriosModel<Card> model, Player<Card> player, int row, int col) {
    int count = 0;
    List<Card> hand = player.getHand();
    List<Play> best = new ArrayList<Play>();
    for (int idx = 0; idx < hand.size(); idx++) {
      Card card = hand.get(idx);
      count = 0;
      if (col == 0 && model.getRow(0).size() > 1) {
        if (model.getCellAt(row, col + 1).canPlayHere()) {
          count += card.getEast() - model.getCellAt(row, col + 1).getCard().getWest();
        } else if (!model.getCellAt(row, col - 1).canPlayHere()) {
          count += card.getEast();
        }
      }
      if (col == model.getRow(0).size() - 1 && model.getRow(0).size() > 1) {
        if (model.getCellAt(row, col - 1).hasCard()) {
          count += card.getWest() - model.getCellAt(row, col - 1).getCard().getEast();
        } else if (!model.getCellAt(row, col - 1).canPlayHere()) {
          count += card.getWest();
        }
      }
      if (row == 0 && model.getGrid().size() > 1) {
        if (model.getCellAt(row + 1, col).hasCard()) {
          count += card.getSouth() - model.getCellAt(row + 1, col).getCard().getNorth();
        } else if (!model.getCellAt(row + 1, col).canPlayHere()) {
          count += card.getSouth();
        }
      }
      if (row == model.getGrid().size() - 1 && model.getGrid().size() > 1) {
        if (model.getCellAt(row - 1, col).hasCard()) {
          count += card.getSouth() - model.getCellAt(row - 1, col).getCard().getNorth();
        } else if (!model.getCellAt(row - 1, col).canPlayHere()) {
          count += card.getNorth();
        }
      }
      if (count > highest) {
        best.clear();
        highest = count;
        best.add(new Play(row, col, idx));
      }
    }
    return best.get(0);
  }


}
