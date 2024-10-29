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

}
