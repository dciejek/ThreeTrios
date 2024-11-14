package strategy;

import model.Card;

/**
 * Represents a position and card to play to in a game of ThreeTrios.
 * All indexes are 0-based.
 */
public class Play {
  public final int row;
  public final int col;
  public final int handIdx;

  public Play(int row, int col, int handIdx) {
    this.row = row;
    this.col = col;
    this.handIdx = handIdx;
  }

  @Override
  public boolean equals(Object obj) {
    return this.hashCode() == obj.hashCode();
  }

  @Override
  public int hashCode() {
    return row * 84291 + col * 1243 + handIdx * 47234;
  }
}
