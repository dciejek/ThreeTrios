package controller;

import model.PlayingCard;
import model.ThreeTriosModel;

/**
 * Represents a Controller for a game of Three Trios, handle a players
 * moves by executing them to the model.
 */
public interface ThreeTriosController {

  /**
   * Execute a single game of Three Trios, when the game is over
   * the playGame method ends.
   * @param model a non-null Three Trios model
   */
  void playGame(ThreeTriosModel<PlayingCard> model);

  /**
   * Handle an action on a single cell of the grid, such as playing a card.
   * @param handIndex the index of the card in a hand, 0 based index
   * @param row row of cell clicked, 0 based index
   * @param col column of cell clicked, 0 based index
   */
  void handleCellClicked(int handIndex, int row, int col);
}
