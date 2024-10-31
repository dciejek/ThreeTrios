package model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Representation of a ThreeTrios player.
 */
public interface Player<C extends Card> {
  /**
   * Gets the color of the player.
   * @return the Color of the player
   */
  public PlayerColor getColor();

  /**
   * Gets the deck of the player's cards.
   * @return the ArrayList of cards that represents the player's hand.
   */
  public ArrayList<C> getHand();

  /**
   * Removes the given PlayingCard from the player's hand.
   *
   * @param card removes the specified card from the player's hand
   */
  public void removeFromHand(C card);

  /**
   * Adds the given PlayingCard to the player's hand.
   * @param card adds the specified card to the player's hand.
   */
  public void addToHand(C card);


}
