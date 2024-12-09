package model.rules;

import model.Card;
import model.Cell;
import model.ReadOnlyThreeTriosModel;

public interface PreBattleRule {

  /**
   *
   * @param row
   * @param col
   * @param model
   */
  void apply(int row, int col, ReadOnlyThreeTriosModel model);
}
