package model;

/**
 * Represents a Cell that no card can be played to.
 */
public class HoleCell implements Cell<PlayingCard> {

  /**
   * Constructs a HoleCell.
   */
  public HoleCell() {
    //All behaviors are handled by methods
  }

  @Override
  public PlayingCard getCard() {
    throw new IllegalStateException("Hole cell does not have card.");
  }

  @Override
  public void updateCell(PlayingCard card, Player<PlayingCard> currentPlayer) {
    throw new IllegalStateException("Hole cell cannot be updated.");
  }

  @Override
  public PlayerColor getPlayerColor() {
    throw new IllegalStateException("Hole cell cannot have a player color.");
  }

  @Override
  public boolean hasCard() {
    return false;
  }

  @Override
  public boolean canPlayHere() {
    return false;
  }

  @Override
  public void setPlayerColor(PlayerColor color) {
    throw new IllegalStateException("Hole cell cannot be updated.");
  }

  @Override
  public String toString() {
    return " ";
  }
}
