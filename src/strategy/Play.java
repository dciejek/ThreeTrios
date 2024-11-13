package strategy;


/**
 * Represents a possible play in ThreeTrios. Holds fields for the row
 * and column to play at, and the card index in the hand for the card to play.
 */
public class Play {
  public final int row;
  public final int col;
  public final int handIdx;

  /**
   * Constructor for a Play, takes in necessary information to play a card to the grid.
   * @param row       The row to play to
   * @param col       The column to play to
   * @param handIdx   The index of the card in a Player's hand
   */
  public Play(int row, int col, int handIdx) {
    this.row = row;
    this.col = col;
    this.handIdx = handIdx;
  }
}
