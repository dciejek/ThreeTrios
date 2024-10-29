package model;

public class CardCell implements Cell {

  private final Card card;

  public CardCell() {
    this.card = null;
  }

  @Override
  public Card getCard() {
    if (this.card == null) {
      throw new IllegalStateException("The card cell is empty.");
    }
    return card;
  }

  @Override
  public Card updateCell(Card updatedCard) {
    //should update card
    return card;
  }
}
