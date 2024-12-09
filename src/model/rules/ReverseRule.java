package model.rules;

import model.Card;
import model.CardinalDirection;

/**
 * Reverses the effects of following BattleRules.
 */
public class ReverseRule implements BattleRule {
  private final BattleRule rules;

  /**
   * Constructs a default PlusPreRule.
   */
  public ReverseRule(BattleRule rules) {
    this.rules = rules;
  }

  @Override
  public boolean beatsCard(Card curr, Card opposing, CardinalDirection dir) {
    if (curr == null || opposing == null) {
      throw new IllegalArgumentException("Cards cannot be null");
    }

    return !rules.beatsCard(curr, opposing, dir)
            && curr.getDirection(dir) != opposing.getDirection(dir.oppositeDirection());
  }
}