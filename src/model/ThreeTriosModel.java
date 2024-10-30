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

  public void placeCard(C card, int row, int col);
  //PlacePhase
  public boolean isGameOver();
  //isGameOver
  //
  public Player getWinner();

  /**
   * Gets the player whose turn it currently is.
   * @return the current active player
   * @throws IllegalStateException if the game is over or if the game hasn't started
   */
  public Player getCurrentPlayer();

  public List<List<Cell>> getGrid();


}
