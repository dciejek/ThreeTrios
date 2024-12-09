package model;

import java.util.ArrayList;
import java.util.List;

import strategy.Play;


/**
 * A player in a game of Three Trios. Possesses a PlayerColor and a hand of Cards.
 * This hand can be added to, removed from, and accessed to receive the specific card's information.
 */
public class TTPlayer implements Player<Card> {

  private ReadOnlyThreeTriosModel<Card> model;
  private final PlayerColor color;
  private final List<Card> hand;

  /**
   * Constructs a player that has a PlayerColor for all of its cards, as well
   * as an empty Hand of cards.
   * @param model the model this player is using
   * @param color the color of the player's cards
   * @throws IllegalArgumentException if the PlayerColor is null
   */
  public TTPlayer(ReadOnlyThreeTriosModel<Card> model, PlayerColor color) {
    this.model = model;
    if (color == null) {
      throw new IllegalArgumentException("color cannot be null");
    }
    this.color = color;
    this.hand = new ArrayList<>();
  }

  @Override
  public PlayerColor getColor() {
    return this.color;
  }

  @Override
  public ArrayList<Card> getHand() {
    return new ArrayList<>(this.hand);
  }

  @Override
  public void removeFromHand(Card card) {
    this.hand.remove(card);
  }

  @Override
  public void addToHand(Card card) {
    this.hand.add(card);
  }

  @Override
  public Play getPlay(ReadOnlyThreeTriosModel<Card> model) {
    return null;
  }

  @Override
  public void setModel(ReadOnlyThreeTriosModel<Card> model) {
    this.model = model;
  }


  @Override
  public boolean equals(Object other) {
    if (!(other instanceof TTPlayer)) {
      return false;
    }
    return this.hashCode() == other.hashCode();
  }

  @Override
  public int hashCode() {
    return this.color.hashCode() + this.hand.hashCode();
  }


}
