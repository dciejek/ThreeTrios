package model;

public class CardCell implements Cell {

  private final Card card;
  private PlayerColor color;

  public CardCell() {
    this.card = null;
    this.color = null;
  }

  @Override
  public Card getCard() {
    return card;
  }

  @Override
  public Card updateCell(Card updatedCard, Player currentPlayer) {
    //should update card
    return card;
  }

  @Override
  public PlayerColor getPlayerColor() {
    return this.color;
  }

  @Override
  public Boolean hasCard() {
    return this.card != null;
  }
}
