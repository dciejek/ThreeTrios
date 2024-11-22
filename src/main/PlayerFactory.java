package main;

import model.Player;
import model.PlayerColor;
import model.PlayingCard;
import model.StrategyPlayer;
import model.TTPlayer;
import model.ThreeTriosModel;
import strategy.CornerStrategy;
import strategy.FlipStrategy;

/**
 * Factory class for Players. Can construct
 * Players with specified strategies and human players.
 */
public class PlayerFactory {
  private int count;

  /**
   * No argument constructor, initializes the count.
   */
  public PlayerFactory() {
    count = 0;
  }

  /**
   * Creates a Player based off of the given String.
   * @param playerName  The type of Player to create
   * @return            The specified type of Player
   */
  public Player<PlayingCard> stringToPlayer(ThreeTriosModel model, String playerName) {
    switch (playerName) {
      case "person":
        return new TTPlayer(model, countToColor());
      case "corner":
        return new StrategyPlayer(model, countToColor(), new CornerStrategy());
      case "flip":
        return new StrategyPlayer(model, countToColor(), new FlipStrategy());
    }
    throw new IllegalArgumentException("Player type not accepted");
  }

  /**
   * Gives the color for the player being initialized.
   * @return    BLUE for the first player, RED for the second
   */
  public PlayerColor countToColor() {
    if (count == 0) {
      count++;
      return PlayerColor.BLUE;
    }
    if (count == 1) {
      count++;
      return PlayerColor.RED;
    }

    throw new IllegalStateException("Already used two players");
  }
}
