package strategy;

import model.Card;
import model.Player;
import model.ThreeTriosModel;

/**
 * Represents a strategy for a CPU player of Three Trios to follow.
 */
public interface TTStrategy<C extends Card> {
  /**
   * Returns the best point to play to for the given strategy, returns the top left play
   * if there is a tie for best play or there is no best play for the given strategy.
   * @param model   The model being played to
   * @param player  The active player
   * @return        The best play possible, or the leftmost and upmost play
   * @throws IllegalArgumentException If either argument is null
   * @throws IllegalStateException    If the player given is not the active player in the model
   */
  public Play playToPoint(ThreeTriosModel<C> model, Player<C> player);
}
