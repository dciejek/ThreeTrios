package model.adapter;

import java.util.Objects;

import model.Card;
import model.Cell;
import provider.model.CardinalDirection;
import provider.model.GridCellReadOnly;

/**
 * Object Adapter used to convert from Cell to the provider's GridCellReadOnly.
 */
public class GridCellReadOnlyAdapter implements GridCellReadOnly {
  private final Cell<Card> cell;

  public GridCellReadOnlyAdapter(Cell<Card> cell) {
    this.cell = Objects.requireNonNull(cell);
  }

  @Override
  public boolean canHaveCard() {
    try {
      cell.getCard();
      return true;
    } catch (IllegalStateException e) {
      return false;
    }
  }

  @Override
  public boolean hasCard() {
    return cell.hasCard();
  }

  @Override
  public provider.model.Card getCard() {
    return new CardAdapter(cell.getCard(), cell.getPlayerColor());
  }

  @Override
  public boolean hasNeighborToThe(CardinalDirection direction) {
    //Not necessary in implementation as neighbors are accessed in a private helper method
    //within our model when needed (during the battle/combo step after placeCard() is called)
    return false;
  }

  @Override
  public GridCellReadOnly getNeighborToThe(CardinalDirection direction) {
    //Not necessary in implementation as neighbors are accessed in a private helper method
    //within our model when needed (during the battle/combo step after placeCard() is called)
    return null;
  }

}
