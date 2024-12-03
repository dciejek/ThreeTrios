package main;

import model.Card;
import model.Player;
import model.PlayerColor;
import model.PlayingCard;
import model.ReadOnlyThreeTriosModel;
import model.StrategyPlayer;
import model.TTPlayer;
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
  public Player<Card> stringToPlayer(ReadOnlyThreeTriosModel<Card> model,
                                            String playerName) {
    switch (playerName) {
      case "person":
        return new TTPlayer(model, countToColor());
      case "corner":
        return new StrategyPlayer(model, countToColor(), new CornerStrategy());
      case "flip":
        return new StrategyPlayer(model, countToColor(), new FlipStrategy());
      default:
        throw new IllegalArgumentException("Player type not accepted");
    }
  }

  /**
   * Gives the color for the player being initialized.
   * @return    RED for the first player, BLUE for the second
   */
  public PlayerColor countToColor() {
    if (count == 0) {
      count++;
      return PlayerColor.RED;
    }
    if (count == 1) {
      count++;
      return PlayerColor.BLUE;
    }

    throw new IllegalStateException("Already used two players");
  }
}
