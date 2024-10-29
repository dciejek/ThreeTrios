package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a simple 2 player ThreeTriosModel
 */
public class TTModel implements ThreeTriosModel {
  //Fields:
  //2D ArrayList<Card> representing the game grid
  //ArrayList<Player>
  private final ArrayList<ArrayList<Cell>> grid;
  private final Player playerOne, playerTwo;

  TTModel() {
    grid = new ArrayList<ArrayList<Cell>>();
    playerOne = new TTPlayer(Color.BLUE);
    playerTwo = new TTPlayer(Color.RED);
  }

  @Override
  public void startGame(int rows, int cols, ArrayList<ArrayList<String>> grid) {
    //Make grid
    ArrayList<Cell> cells = new ArrayList<>();
    for (ArrayList<String> row : grid) {
      cells.clear();
      for (String str : row) {
        addCellToList(str, cells);
      }
      this.grid.add(cells);
    }
    //Draw cards

  }

  private void addCellToList(String str, List<Cell> cells) {
    if (str == null) {
      throw new IllegalArgumentException("String cannot be null");
    } else if (str.equals("C")) {
      cells.add(new CardCell());
    } else if (str.equals("X")) {
      cells.add(new HoleCell());
    } else {
      throw new IllegalArgumentException("Invalid cell type, given: " + str);
    }
  }

  @Override
  public void placePhase() {

  }

  @Override
  public void battlePhase() {

  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public Player getWinner() {

  }

  //Methods:
  //Constructor
}
