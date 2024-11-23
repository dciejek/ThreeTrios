package model;

import java.util.List;

import controller.ThreeTriosController;

/**
 * A ReadOnly interface for a game of three trios.
 * @param <C> The type of cards to use for the game
 */
public interface ReadOnlyThreeTriosModel<C extends Card> {

  /**
   * Tells us if the game is over.
   * @return true if the # of playable cells equals 0
   * @throws IllegalStateException if the game has not started
   */
  boolean isGameOver();

  /**
   * Tells us the winner of a game of three trios.
   * @return the Player who won
   * @throws IllegalStateException if the game isn't over
   */
  Player<C> getWinner();

  /**
   * Gets the first player for the game.
   * @return  The first player for the game
   */
  Player<C> getPlayerOne();

  /**
   * Gets the second player for the game.
   * @return  The second player for the game
   */
  Player<C> getPlayerTwo();

  /**
   * Gets the player whose turn it currently is.
   * @return the current active player
   * @throws IllegalStateException if the game hasn't started
   */
  Player<C> getCurrentPlayer();

  /**
   * Gets the game grid.
   * @return the current state of the grid
   * @throws IllegalStateException if the game hasn't started
   */
  List<List<Cell>> getGrid();

  /**
   * Returns the row of the game grid at the index of the given int.
   * @param row the index to get
   * @return the row as a List of cells
   * @throws IllegalStateException if the game has not started
   * @throws IllegalArgumentException if the row index is invalid
   */
  List<Cell> getRow(int row);

  /**
   * Returns the cell at the given coordinate.
   * @param row The row of the cell
   * @param col The column of the cell
   * @return  the cell at the coordinate
   */
  Cell getCellAt(int row, int col);

  /**
   * Returns the number of cards that can be flipped by playing the given card
   * to the given position.
   * @param card  the card to play
   * @param row   the row to play to
   * @param col   the column to play to
   * @return number of cards that can be flipped playing card to position
   */
  int numFlipped(Card card, int row, int col);

  /**
   * Set up to handle new turn events in this model.
   * @param listener the controller.
   */
  void addTurnListener(ThreeTriosController listener);

  boolean isGameStarted();
}
