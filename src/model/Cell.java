package model;

import java.util.List;

/**
 * A Cell belonging to the Grid in a game of Three Trios.
 * Cell's can hold a Card along with the current Player the card belongs to.
 * Additionally, a cell can only be played to once (only one card can be played on it)
 * but it can have its state updated internally.
 * @param <C> The type of Card to use
 */
public interface Cell<C extends Card> {

  /**
   * Gets the card in the cell.
   * @return the card in the given cell
   * @throws IllegalStateException if the cell is empty
   */
  C getCard();

  /**
   * Updates the cell with the new card & player associated with it.
   * @param card the card now in the cell.
   * @param currentPlayer the player to which the cell now belongs to
   */
  void updateCell(C card, Player<C> currentPlayer);

  /**
   * Returns the color of this player.
   * @return    A PlayerColor of the color
   */
  PlayerColor getPlayerColor();

  /**
   * Returns true if the given cell has a card in it.
   * @return true if there is a card, or false if its empty/a hole cell
   */
  boolean hasCard();

  /**
   * Returns true if this card can contain a card.
   * @return  false if this cell cannot take a card, or is full, true otherwise.
   */
  boolean canPlayHere();

  /**
   * Updates the PlayerColor associated with the cell.
   * @param color   The color to update the cell to
   */
  void setPlayerColor(PlayerColor color);

  /**
   * The toString method for a cell.
   * @return the cell's respected to string
   */
  String toString();
}
