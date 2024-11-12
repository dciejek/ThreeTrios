package strategy;

import model.Player;
import model.ThreeTriosModel;

public class CornerStrategy implements TTStrategy {
  @Override
  public Play playToPoint(ThreeTriosModel model, Player player) {
    for (int row = 0; row < model.getGrid().size(); row+= model.getGrid().size() - 1) {
      for (int col = 0; col < model.getRow(0).size(); col+= model.getRow(0).size() - 1) {
        if (model.getCellAt(row, col).canPlayHere()) {
          getBestMoves(model, player, row, col);
        }
      }
    }
  }
}
