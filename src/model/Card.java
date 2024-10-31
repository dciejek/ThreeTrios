package model;

/**
 * Card behaviors in a ThreeTrios game.
 */
public interface Card {


  /**
   * Gets the name of the card.
   * @return The string name of the card
   */
  public String getName();

  /**
   * Gets the north value of the card.
   * @return    The north card's integer value.
   */
  public int getNorth();

  /**
   * Gets the south value of the card.
   * @return    The south card's integer value.
   */
  public int getSouth();

  /**
   * Gets the east value of the card.
   * @return    The east card's integer value.
   */
  public int getEast();

  /**
   * Gets the west value of the card.
   * @return    The west card's integer value.
   */
  public int getWest();

  /**
   * Returns the value of the given direction.
   * @param dir   A CardinalDirection
   * @return      An integer representation of the CardValue in the given direction
   */
  public int getDirection(CardinalDirection dir);

  /**
   * Returns true if this cards specific directional value is greater than the opposing
   * @param opposing the opposing card.
   * @param dir the direction for this cards value to be compared
   * @return true if this card's value is greater than the opposing
   */
  public boolean isStrongerCard(Card opposing, CardinalDirection dir);
}
