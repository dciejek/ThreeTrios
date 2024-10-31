package model;

public class CardCell implements Cell {

  private Card card;
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
  public void updateCell(Card card, Player currentPlayer) {
    this.card = card;
    this.color = currentPlayer.getColor();
  }

  @Override
  public PlayerColor getPlayerColor() {
    return this.color;
  }

  public void setPlayerColor(PlayerColor color) {
    this.color = color;
  }

  @Override
  public Boolean hasCard() {
    return this.card != null;
  }

  @Override
  public String toString() {
    if (hasCard()) {
      return this.color.toString().substring(0, 1);
    }
    return "_";
  }
}
