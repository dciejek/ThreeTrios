package model;

public class HoleCell implements Cell<PlayingCard> {

  public HoleCell() {
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
  public Boolean hasCard() {
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
