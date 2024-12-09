package model.rules;

import model.Card;
import model.CardinalDirection;

/**
 * Decorator that makes it so an Ace can beat a 1, otherwise the rest of the BattleRules apply.
 */
public class FallenAceRule implements BattleRule {
  BattleRule rules;

  /**
   * Constructs a default FallenAceRule.
   */
  public FallenAceRule(BattleRule rules) {
    this.rules = rules;
  }

  @Override
  public boolean beatsCard(Card curr, Card opposing, CardinalDirection dir) {
    if (curr == null || opposing == null) {
      throw new IllegalArgumentException("Cards cannot be null");
    }

    if (curr.getDirection(dir) == 1
      && opposing.getDirection(dir.oppositeDirection()) == 10) {
      return true;
    } else if (curr.getDirection(dir) == 10
      && opposing.getDirection(dir.oppositeDirection()) == 1) {
      return false;
    }
    else {
      return rules.beatsCard(curr, opposing, dir);
    }
  }
}