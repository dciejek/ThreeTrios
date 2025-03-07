package model;

import java.util.List;

/**
 * A game where two players battle upon a Grid of Cells with a variety of
 * custom Cards. Each player has their own color, red or blue, along with an
 * equal amount of cards dealt to their Hand to which they do battle with.
 * @param <C>   The type of cards to use for the game
 */
public interface ThreeTriosModel<C extends Card> extends ReadOnlyThreeTriosModel<C> {

  /**
   * Starts the game based on the given parameters.
   * @param grid    the board to play on, uses Cells to hold cards
   * @param cards   the list of cards to play with, are shuffled into hands
   * @param rows    the number of rows in the grid
   * @param cols    the number of columns in the grid
   * @throws IllegalStateException if the game has already been started
   * @throws IllegalArgumentException if either File input is null
   * @throws IllegalArgumentException if there is less than playable cells + 1 cards
   * @throws IllegalArgumentException if row/col dimensions from file is larger than the grid given
   * @throws IllegalArgumentException if the grid does not have an odd # of playable cells
   */
  void startGame(List<List<Cell<C>>> grid, List<C> cards,
                 int rows, int cols);

  /**
   * Places a given card onto the game grid, then battle initiate the battle phase, once finished
   * switch the turn to the next player.
   * @param cardIdx the index of card in the active player's hand to be placed on the grid
   * @param row the row index to be placed in
   * @param col the column index to be placed in
   * @throws IllegalArgumentException if the card is null
   * @throws IllegalArgumentException if the row/col index is invalid
   * @throws IllegalArgumentException if a card already exists at the given index
   * @throws IllegalStateException if the game is over or if game has not started
   */
  void placeCard(int cardIdx, int row, int col);

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

}
