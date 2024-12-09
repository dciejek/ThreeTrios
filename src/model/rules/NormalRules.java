package model.rules;

import model.Card;
import model.CardinalDirection;

/**
 * Normal Rules is simply if this card's value is greater than the opposing one with the given
 * direction.
 */
public class NormalRules implements BattleRule {

  /**
   * Constructs a default NormalRules.
   */
  public NormalRules() {

  }

  @Override
  public boolean beatsCard(Card curr, Card opposing, CardinalDirection dir) {
    return curr.getDirection(dir) - opposing.getDirection(dir.oppositeDirection()) > 0;
  }
}
