package model;

import java.util.ArrayList;
import java.util.List;

import strategy.Play;
import strategy.TTStrategy;

/**
 * A basic AI model that creates the best move set it can compute for a game
 * of three trios based on the game state. Orders the moves in a point based system.
 */
public class StrategyPlayer implements Player<PlayingCard> {


  private final PlayerColor color;
  private final ArrayList<PlayingCard> hand;
  private final TTStrategy strategy;
  /**
   * Constructs a player that has a PlayerColor for all of its cards, as well
   * as an empty Hand of PlayingCards.
   * @param color the color of the player's cards
   * @throws IllegalArgumentException if the PlayerColor is null
   */
  public StrategyPlayer(PlayerColor color, TTStrategy strategy) {
    if (color == null) {
      throw new IllegalArgumentException("color cannot be null");
    }
    this.color = color;
    this.hand = new ArrayList<>();
    this.strategy = strategy;
  }

  @Override
  public PlayerColor getColor() {
    return this.color;
  }

  @Override
  public List<PlayingCard> getHand() {
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
  public Play getPlay(ThreeTriosModel<PlayingCard> model) {
    return strategy.playToPoint(model, this);
  }

}
