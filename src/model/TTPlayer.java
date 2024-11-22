package model;

import java.util.ArrayList;

import strategy.Play;


/**
 * A player in a game of Three Trios.
 */
public class TTPlayer implements Player<PlayingCard> {

  private ReadOnlyThreeTriosModel<PlayingCard> model;
  private final PlayerColor color;
  private final ArrayList<PlayingCard> hand;

  /**
   * Constructs a player that has a PlayerColor for all of its cards, as well
   * as an empty Hand of PlayingCards.
   * @param model the model this player is using
   * @param color the color of the player's cards
   * @throws IllegalArgumentException if the PlayerColor is null
   */
  public TTPlayer(ReadOnlyThreeTriosModel<PlayingCard> model, PlayerColor color) {
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
  public ArrayList<PlayingCard> getHand() {
    return new ArrayList<>(this.hand);
  }

  @Override
  public void removeFromHand(PlayingCard card) {
    this.hand.remove(card);
  }

  @Override
  public void addToHand(PlayingCard card) {
    this.hand.add(card);
  }

  @Override
  public Play getPlay() {
    return null;
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
