package model.rules;

import java.util.Set;

import model.Card;
import model.CardinalDirection;

/**
 * Pre Battle rules are applied before the combo phase in a Three Trios game begins and after the
 * card is placed. They change how the neighbors of the original card can flip.
 */
public interface PreBattleRule {


  /**
   * Applies this rule to the curr Card and sees if the opposing card will meet the criteria.
   * @param curr      the current card being compared
   * @param opposing  the card to compare to
   * @param dir       the direction to compare in
   * @throws IllegalArgumentException   if either cards are null
   */
  void apply(Card curr, Card opposing, CardinalDirection dir);

  /**
   * Resets all fields to prepare for next battle phase.
   */
  void reset();

  /**
   * Gets the winners of the criteria after applying to all neighbors.
   * @return  a set of all winning CardinalDirections
   */
  Set<CardinalDirection> getWinners();
}
