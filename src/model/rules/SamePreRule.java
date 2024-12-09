package model.rules;

import java.util.HashSet;
import java.util.Set;

import model.Card;
import model.CardinalDirection;

/**
 * This rule makes it so neighbors of the originally placed card can be flipped if the values of the
 * adjacent cards are equal to this cards facing value.
 */
public class SamePreRule implements PreBattleRule {
  private final Set<CardinalDirection> winners;

  /**
   * Constructs a default SamePreRule.
   */
  public SamePreRule() {
    this.winners = new HashSet<>();
  }

  @Override
  public void apply(Card curr, Card opposing, CardinalDirection dir) {
    if (curr.getDirection(dir) == opposing.getDirection(dir.oppositeDirection())) {
      winners.add(dir);
    }
  }

  @Override
  public void reset() {
    winners.clear();
  }

  @Override
  public Set<CardinalDirection> getWinners() {
    if (winners.size() > 1) {
      return new HashSet<>(winners);
    }
    else {
      return new HashSet<>();
    }
  }
}
