package model;

public interface Cell {

  /**
   * Gets the card in the cell.
   * @return the card in the given cell
   * @throws IllegalStateException if the cell is empty
   */
  public Card getCard();

  /**
   * Updates the cell with the new card.
   *
   * @param updatedCard   the new card
   * @param currentPlayer
   * @return the card in the cell after update
   */
  public Card updateCell(Card updatedCard, Player currentPlayer);

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
}
