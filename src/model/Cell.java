package model;

/**
 * Represents a Cell for a card grid.
 * @param <C> The type of Card to use
 */
public interface Cell<C extends Card> {

  /**
   * Gets the card in the cell.
   * @return the card in the given cell
   * @throws IllegalStateException if the cell is empty
   */
  public C getCard();

  /**
   * Updates the cell with the new card & player associated with it.
   * @param card the card now in the cell.
   * @param currentPlayer the player to which the cell now belongs to
   */
  public void updateCell(C card, Player<C> currentPlayer);

  /**
   * Returns the color of this player.
   * @return    A PlayerColor of the color
   */
  public PlayerColor getPlayerColor();

  /**
   * Returns true if the given cell has a card in it.
   * @return true if there is a card, or false if its empty/a hole cell
   */
  public Boolean hasCard();

  /**
   * Updates the PlayerColor associated with the cell.
   * @param color   The color to update the cell to
   */
  public void setPlayerColor(PlayerColor color);

  /**
   * The toString method for a cell.
   * @return the cell's respected to string
   */
  public String toString();
}
