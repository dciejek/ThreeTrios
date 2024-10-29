package model;

import java.util.ArrayList;

public interface ThreeTriosModel {


  /**
   * Starts the game based on the given parameters.
   * @param rows the # of rows in the grid
   * @param cols the # of cols in the grid
   * @param grid the 2D array of strings from config file that fill the grid
   */
  public void startGame(int rows, int cols, ArrayList<ArrayList<String>> grid);

  public void placePhase();
  //PlacePhase
  public void battlePhase();
  //BattlePhase
      //Helper: ComboStep
  public boolean isGameOver();
  //isGameOver
  //
  public Player getWinner();
}
