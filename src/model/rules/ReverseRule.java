package model.rules;

import model.Card;
import model.CardinalDirection;

public class ReverseRule implements BattleRule {

  /**
   * Constructs a default PlusPreRule.
   */
  public ReverseRule() {

  }

  @Override
  public boolean beatsCard(Card curr, Card opposing, CardinalDirection dir) {
    return curr.getDirection(dir) - opposing.getDirection(dir.oppositeDirection()) < 0;
  }
}