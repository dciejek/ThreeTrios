package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of a ThreeTrios player. Each player has a PlayerColor that represents
 * the color of its cards (in Hand and on Grid), as well as a Hand of three trios Cards.
 */
public interface Player<C extends Card> {
  /**
   * Gets the color of the player.
   * @return the Color of the player
   */
  PlayerColor getColor();

  /**
   * Gets the deck of the player's cards.
   * @return the ArrayList of cards that represents the player's hand.
   */
  List<C> getHand();

  /**
   * Removes the given PlayingCard from the player's hand.
   *
   * @param card removes the specified card from the player's hand
   */
  void removeFromHand(C card);

  /**
   * Adds the given PlayingCard to the player's hand.
   * @param card adds the specified card to the player's hand.
   */
  void addToHand(C card);

}
