package model.rules;

import model.Card;
import model.CardinalDirection;

public interface BattleRule {

  /**
   * Determines if the cell at this card
   * @param curr
   * @param opposing
   * @param dir
   * @return
   */
  boolean beatsCard(Card curr, Card opposing, CardinalDirection dir);
}
