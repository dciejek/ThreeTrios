package model.rules;

import java.util.HashSet;
import java.util.Set;

import model.Card;
import model.Cell;
import model.ReadOnlyThreeTriosModel;
import model.CardinalDirection;

public class SamePreRule implements PreBattleRule {

  @Override
  public void apply(int row, int col, ReadOnlyThreeTriosModel model) {
    Set<Integer> neighborVals = new HashSet<>();
    int count = 0;


  }


  /**
   * Shows if the opposing index exists on the games grid.
   * @param cardRow the row index
   * @param cardCol the column index
   * @param dir the direction which the opposing card should exist
   * @return true if the index given exists on the grid
   */
  private boolean opposingCardInBounds(int cardRow, int cardCol, CardinalDirection dir,
                                       ReadOnlyThreeTriosModel model) {
    switch (dir) {
      case NORTH:
        return cardRow - 1 >= 0;
      case SOUTH:
        return cardRow + 1 < model.getGrid().size();
      case EAST:
        return cardCol + 1 < model.getRow(0).size();
      case WEST:
        return cardCol - 1 >= 0;
      default:
        throw new IllegalArgumentException("Invalid cardinal direction");
    }
  }
}
