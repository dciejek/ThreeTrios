package model;

import model.rules.BattleRule;

/**
 * A card interface for three trios. Each card will have a name represented with a string,
 * 4 CardValue's belonging to each cardinal direction, which can be compared to another
 * card's respective CardValue.
 */
public interface Card {


  /**
   * Gets the name of the card.
   * @return The string name of the card
   */
  String getName();

  /**
   * Gets the north value of the card.
   * @return    The north card's integer value.
   */
  int getNorth();

  /**
   * Gets the south value of the card.
   * @return    The south card's integer value.
   */
  int getSouth();

  /**
   * Gets the east value of the card.
   * @return    The east card's integer value.
   */
  int getEast();

  /**
   * Gets the west value of the card.
   * @return    The west card's integer value.
   */
  int getWest();

  /**
   * Returns the value of the given direction.
   * @param dir   A CardinalDirection
   * @return      An integer representation of the CardValue in the given direction
   */
  int getDirection(CardinalDirection dir);

  /**
   * Returns true if this cards specific directional value is greater than the opposing.
   * @param opposing the opposing card.
   * @param dir the direction for this cards value to be compared
   * @return true if this card's value is greater than the opposing
   */
  boolean isStrongerCard(Card opposing, CardinalDirection dir, BattleRule rule);
}
