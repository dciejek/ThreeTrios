package model.adapter;

import java.util.List;
import java.util.Objects;

import model.Cell;
import model.PlayingCard;
import model.ReadOnlyThreeTriosModel;
import provider.model.Grid;
import provider.model.GridCellReadOnly;

public class GridAdapter implements Grid {
  private final ReadOnlyThreeTriosModel<PlayingCard> model;

  public GridAdapter(ReadOnlyThreeTriosModel<PlayingCard> model) {
    this.model = Objects.requireNonNull(model);
  }

  @Override
  public boolean isFull() {
    //Checks if the number of playable cells is 0 on a given grid
    //aka if the grid is full.
    return model.isGameOver();
  }

  @Override
  public int getNumHoles() {
    int numHoles = 0;
    for (List<Cell> row : model.getGrid()) {
      for (Cell cell : row) {
        try {
          cell.getCard();
        } catch (IllegalStateException e) {
          numHoles++;
        }
      }
    }
    return numHoles;
  }

  @Override
  public GridCellReadOnly[][] readOnlyArray2D() {
    GridCellReadOnly[][] array2D =
            new GridCellReadOnly[model.getGrid().size()][model.getRow(0).size()];
    for (int i = 0; i < model.getGrid().size(); i++) {
      for (int j = 0; j < model.getRow(0).size(); j++) {
        array2D[i][j] = new GridCellReadOnlyAdapter(model.getGrid().get(i).get(j));
      }
    }
    return array2D;
  }

  @Override
  public int numRows() {
    return model.getGrid().size();
  }

  @Override
  public int numCols() {
    return model.getRow(0).size();
  }
}
