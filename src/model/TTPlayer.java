package model;

import java.awt.*;
import java.util.ArrayList;

/**
 * A player in a game of Three Trios.
 */
public class TTPlayer implements Player {

  private final Color color;
  private final ArrayList<PlayingCard> deck;

  /**
   * Constructs a player.
   * @param color the color of the player's cards
   */
  public TTPlayer(Color color) {
    this.color = color;
    this.deck = new ArrayList<>();
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public ArrayList<PlayingCard> getHand() {
    return new ArrayList<>(this.deck);
  }

  @Override
  public void removeFromHand(PlayingCard card) {
    this.deck.remove(card);
  }

  @Override
  public void addToHand(PlayingCard card) {
    this.deck.add(card);
  }

}
