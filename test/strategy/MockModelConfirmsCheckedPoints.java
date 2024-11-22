package strategy;

import java.io.IOException;
import java.util.List;

import controller.ThreeTriosController;
import model.Card;
import model.Cell;
import model.Player;
import model.PlayingCard;
import model.TTModel;
import model.ThreeTriosModel;

/**
 * Mock to confirm what points are checked by strategies.
 */
public class MockModelConfirmsCheckedPoints implements ThreeTriosModel<PlayingCard> {
  private final Appendable transcript;
  private final ThreeTriosModel<PlayingCard> base;

  public MockModelConfirmsCheckedPoints(Appendable transcript) {
    this.transcript = transcript;
    this.base = new TTModel();
  }

  @Override
  public void startGame(List<List<Cell<PlayingCard>>> grid,
                        List<PlayingCard> cards, int rows, int cols) {
    base.startGame(grid, cards, rows, cols);
  }

  @Override
  public void placeCard(int cardIdx, int row, int col) {
    //Is a mock
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public Player<PlayingCard> getWinner() {
    return null;
  }

  @Override
  public Player<PlayingCard> getPlayerOne() {
    return null;
  }

  @Override
  public Player<PlayingCard> getPlayerTwo() {
    return null;
  }

  @Override
  public Player<PlayingCard> getCurrentPlayer() {
    return base.getCurrentPlayer();
  }

  @Override
  public List<List<Cell>> getGrid() {
    return base.getGrid();
  }

  @Override
  public List<Cell> getRow(int row) {
    return base.getRow(row);
  }

  @Override
  public Cell getCellAt(int row, int col) {
    try {
      transcript.append(String.valueOf(row)).append(", ").append(String.valueOf(col))
              .append("\n");
    } catch (IOException ignored) {

    }
    return base.getCellAt(row, col);
  }

  @Override
  public int numFlipped(Card card, int row, int col) {
    return base.numFlipped(card, row, col);
  }

  @Override
  public void addTurnListener(ThreeTriosController listener) {
    //is a mock
  }
}
