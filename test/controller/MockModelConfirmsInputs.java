package controller;

import java.io.IOException;
import java.util.List;

import model.Card;
import model.Cell;
import model.Player;
import model.PlayerColor;
import model.TTModel;
import model.TTPlayer;
import model.ThreeTriosModel;

/**
 * Mock for confirming inputs from a controller.
 */
public class MockModelConfirmsInputs implements ThreeTriosModel<Card> {
  private final Appendable transcript;

  public MockModelConfirmsInputs(Appendable transcript) {
    this.transcript = transcript;
    ThreeTriosModel<Card> base = new TTModel();
  }

  @Override
  public void startGame(List grid, List list, int rows, int cols) {
    // is mock
  }

  @Override
  public void placeCard(int cardIdx, int row, int col) {
    try {
      transcript.append(cardIdx + ", " + row + ", " + col + "\n");
    } catch (IOException ignored) {
      // mock
    }
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public Player getWinner() {
    return null;
  }

  @Override
  public Player getPlayerOne() {
    return new TTPlayer(this, PlayerColor.RED);
  }

  @Override
  public Player getPlayerTwo() {
    return null;
  }

  @Override
  public Player getCurrentPlayer() {
    return new TTPlayer(this, PlayerColor.RED);
  }

  @Override
  public List<List<Cell>> getGrid() {
    return List.of();
  }

  @Override
  public List<Cell> getRow(int row) {
    return List.of();
  }

  @Override
  public Cell getCellAt(int row, int col) {
    return null;
  }

  @Override
  public int numFlipped(Card card, int row, int col) {
    return 0;
  }

  @Override
  public void addTurnListener(ThreeTriosController listener) {
    // mock
  }

  @Override
  public boolean isGameStarted() {
    return false;
  }

  @Override
  public int getScore(Player player) {
    return 0;
  }
}
