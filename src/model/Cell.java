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
   * @param updatedCard the new card
   * @return the card in the cell after update
   */
  public Card updateCell(Card updatedCard);
}
