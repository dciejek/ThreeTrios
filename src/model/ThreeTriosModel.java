package model;

import java.io.File;
import java.util.List;

/**
 * Represents a model for a ThreeTrios game.
 * @param <C>   The type of cards to use for the game
 */
public interface ThreeTriosModel<C extends Card> {


  /**
   * Starts the game based on the given parameters.
   * @param gridFile the file to extract the grid info from
   * @param cardFile the file to extract the card info from
   * @throws IllegalStateException if the game has already been started
   * @throws IllegalArgumentException if either File input is null
   * @throws IllegalArgumentException if there is less than playable cells + 1 cards
   * @throws IllegalArgumentException if row/col dimensions from file is larger than the grid given
   * @throws IllegalArgumentException if the grid does not have an odd # of playable cells
   */
  public void startGame(File gridFile, File cardFile);

  /**
   * Places a given card onto the game grid, then battle initiate the battle phase, once finished
   * switch the turn to the next player.
   * @param card the card to be placed on the grid
   * @param row the row index to be placed in
   * @param col the column index to be placed in
   * @throws IllegalArgumentException if the card is null
   * @throws IllegalArgumentException if the row/col index is invalid
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
   * Tells us the winner of a game of three trios.
   * @return the Player who won
   * @throws IllegalStateException if the game isn't over
   */
  public Player<C> getWinner();

  /**
   * Gets the player whose turn it currently is.
   * @return the current active player
   * @throws IllegalStateException if the game hasn't started
   */
  public Player<C> getCurrentPlayer();

  /**
   * Gets the game grid.
   * @return the current state of the grid
   * @throws IllegalStateException if the game hasn't started
   */
  public List<List<Cell>> getGrid();

  /**
   * Returns the row of the game grid at the index of the given int.
   * @param row the index to get
   * @return the row as a List of cells
   * @throws IllegalStateException if the game has not started
   * @throws IllegalArgumentException if the row index is invalid
   */
  List<Cell> getRow(int row);
}
