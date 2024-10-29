package model;

public class HoleCell implements Cell {

  public HoleCell() {
  }

  @Override
  public Card getCard() {
    throw new UnsupportedOperationException("Hole cell does not have card.");
  }

  @Override
  public Card updateCell(Card updatedCard) {
    throw new UnsupportedOperationException("Hole cell cannot be updated.");
  }
}
