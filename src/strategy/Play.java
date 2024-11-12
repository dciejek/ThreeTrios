package strategy;

import model.Card;

/**
 * Represents a position and card to play to in a game of ThreeTrios.
 */
public final class Play {
  public final int row;
  public final int col;
  public final int handIdx;

  public Play(int row, int col, int handIdx) {
    this.row = row;
    this.col = col;
    this.handIdx = handIdx;
  }
}
