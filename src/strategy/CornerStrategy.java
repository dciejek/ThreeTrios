package strategy;

import java.util.ArrayList;
import java.util.List;

import model.Player;
import model.ThreeTriosModel;

public class CornerStrategy implements TTStrategy {
  @Override
  public Play playToPoint(ThreeTriosModel model, Player player) {
    List<Play> plays = new ArrayList<Play>();
    for (int row = 0; row < model.getGrid().size(); row+= model.getGrid().size() - 1) {
      for (int col = 0; col < model.getRow(0).size(); col+= model.getRow(0).size() - 1) {
        if (model.getCellAt(row, col).canPlayHere()) {
          plays.addAll(getBestMove(model, player, row, col));
        }
      }
    }
  }

  private List<Play> getBestMove(ThreeTriosModel model, Player player, int row, int col) {

  }


}
