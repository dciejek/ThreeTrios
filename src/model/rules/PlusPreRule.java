package model.rules;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.Card;
import model.CardinalDirection;

/**
 * This rule makes it so neighbors of the originally placed card can be flipped if the total
 * of adjacent values is equal on more than one side.
 */
public class PlusPreRule implements PreBattleRule {
  private final Set<Integer> totals;
  private final Map<Integer, CardinalDirection> valToDir;
  private final Set<CardinalDirection> winners;


  /**
   * Constructs a default PlusPreRule.
   */
  public PlusPreRule() {
    this.totals = new HashSet<>();
    this.valToDir = new HashMap<>();
    this.winners = new HashSet<>();
  }



  @Override
  public void apply(Card curr, Card opposing, CardinalDirection dir) {
    if (curr == null || opposing == null) {
      throw new IllegalArgumentException("Cards cannot be null");
    }
    if (!(totals.contains(curr.getDirection(dir) + opposing.getDirection(dir.oppositeDirection())))) {
      totals.add(curr.getDirection(dir) + opposing.getDirection(dir.oppositeDirection()));
      valToDir.put(curr.getDirection(dir) + opposing.getDirection(dir.oppositeDirection()), dir);
    } else {
      winners.add(dir);
      winners.add(valToDir.get(curr.getDirection(dir)
              + opposing.getDirection(dir.oppositeDirection())));
    }
  }

  @Override
  public void reset() {
    totals.clear();
    winners.clear();
    valToDir.clear();
  }

  @Override
  public Set<CardinalDirection> getWinners() {
    return new HashSet<>(winners);
  }
}
