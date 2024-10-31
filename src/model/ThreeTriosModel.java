package model;

import java.util.ArrayList;
import java.util.List;

public interface ThreeTriosModel<C extends Card> {


  /**
   * Starts the game based on the given parameters.
   * @param rows the # of rows in the grid
   * @param cols the # of cols in the grid
   * @param grid the 2D array of strings from config file that fill the grid
   */
  public void startGame(int rows, int cols, List<List<String>> grid,
                        List<C> cards);

  /**
   * Places a given card onto the game grid, then battle initiate the battle phase, once finished
   * switch the turn to the next player.
   * @param card the card to be placed on the grid
   * @param row the row index to be placed in
   * @param col the column index to be placed in
   * @throws IllegalArgumentException if a card already exists at the given index
   * @throws IllegalStateException if the game is over or if game has not started
   */
  public void placeCard(C card, int row, int col);

  /**
   * Tells us if the game is over.
   * @return true if the # of playable cells equals 0
   * @throws IllegalStateException if the game has not started
   */
  public boolean isGameOver();

  /**
   * Tells us the winner of a game of three trios
   * @return the Player who won
   * @throws IllegalStateException if the game isn't over
   */
  public Player getWinner();

  /**
   * Gets the player whose turn it currently is.
   * @return the current active player
   * @throws IllegalStateException if the game hasn't started
   */
  public Player getCurrentPlayer();

  /**
   * Gets the game grid.
   * @return the current state of the grid
   */
  public List<List<Cell>> getGrid();

  /**
   * Returns the row of the game grid at the index of the given int.
   * @param row the index to get
   * @return the row as a List of cells
   * @throws IllegalStateException if the game has not started
   */
  List<Cell> getRow(int row);
}
