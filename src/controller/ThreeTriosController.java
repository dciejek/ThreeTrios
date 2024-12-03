package controller;

import model.Card;

/**
 * Represents a Controller for a game of Three Trios, handle a players
 * moves by executing them to the model.
 */
public interface ThreeTriosController<C extends Card> {

  /**
   * Execute a single game of Three Trios, when the game is over
   * the playGame method ends.
   */
  void playGame();

  /**
   * Handle an action on a single cell of the board, such as playing a card.
   * For both players and machines.
   * Both players hands and the grid itself are incorporated into the 0 based index.
   * @param row row of cell clicked, 0 based index
   * @param col column of cell clicked, 0 based index
   */
  void handleCellClicked(int row, int col);

  /**
   * Refreshes the controller's view field.
   */
  void refresh();
}
