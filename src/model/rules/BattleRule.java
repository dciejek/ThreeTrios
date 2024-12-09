package model.rules;

import model.Card;
import model.CardinalDirection;

/**
 * Battle Rules are applied when comparing two cards to see if one beats another. Used in the
 * original comparison as well as the combo step for Three Trios.
 */
public interface BattleRule {

  /**
   * Determines if curr card beats the opposing card under this rule.
   * @param curr      the card to compare
   * @param opposing  the card being compared to
   * @param dir       the direction to compare in
   * @return          true if curr wins, false if not
   * @throws IllegalArgumentException   if either cards are null
   */
  boolean beatsCard(Card curr, Card opposing, CardinalDirection dir);
}
