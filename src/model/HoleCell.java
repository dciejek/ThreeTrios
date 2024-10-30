package model;

public class HoleCell implements Cell {

  private final PlayerColor color;

  public HoleCell() {
    this.color = null;
  }

  @Override
  public Card getCard() {
    throw new IllegalStateException("Hole cell does not have card.");
  }

  @Override
  public Card updateCell(Card updatedCard, Player currentPlayer) {
    throw new IllegalStateException("Hole cell cannot be updated.");
  }

  @Override
  public PlayerColor getPlayerColor() {
    return color;
  }

  @Override
  public Boolean hasCard() {
    return false;
  }
}
