package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a cell that can hold a card. Cells can have a PlayingCard inside them.
 * If a playing card is in a cell then the cell will also have a PlayerColor value.
 * Cells can be updated using
 * <Code>updateCell(PlayingCard card, Player currentPlayer)</Code>
 * method.
 */
public class CardCell implements Cell<Card> {

  private Card card;
  private PlayerColor color;

  /**
   * Creates a card cell, upon creation no playing card/player color is assigned.
   * Therefore, both values are set to null, for later update using updateCell().
   */
  public CardCell() {
    this.card = null;
    this.color = null;
  }

  @Override
  public Card getCard() {
    return card;
  }

  @Override
  public void updateCell(Card card, Player<Card> currentPlayer) {
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
  public boolean hasCard() {
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
