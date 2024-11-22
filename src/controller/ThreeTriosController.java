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
   */
  void playGame();

  /**
   * Handle an action on a single cell of the board, such as playing a card.
   * 0 0 on the board is the first card in Player Ones hand, as both hands are counted
   * @param row row of cell clicked, 0 based index
   * @param col column of cell clicked, 0 based index
   */
  void handleCellClicked(int row, int col);
}
