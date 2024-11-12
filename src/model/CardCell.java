package model;

/**
 * Represents a cell that can hold a card.
 */
public class CardCell implements Cell<PlayingCard> {

  private PlayingCard card;
  private PlayerColor color;

  public CardCell() {
    this.card = null;
    this.color = null;
  }

  @Override
  public PlayingCard getCard() {
    return card;
  }

  @Override
  public void updateCell(PlayingCard card, Player<PlayingCard> currentPlayer) {
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
  public boolean canPlayHere() {
    return this.card == null;
  }

  @Override
  public String toString() {
    if (hasCard()) {
      return this.color.toString().substring(0, 1);
    }
    return "_";
  }
}
