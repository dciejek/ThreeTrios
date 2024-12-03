package model.adapter;

import java.util.Objects;

import model.Cell;
import model.PlayingCard;
import provider.model.Card;
import provider.model.CardinalDirection;
import provider.model.GridCellReadOnly;

public class GridCellReadOnlyAdapter implements GridCellReadOnly {
  private final Cell<PlayingCard> cell;

  public GridCellReadOnlyAdapter(Cell<PlayingCard> cell) {
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
  public Card getCard() {
    //Should return providers card, so make adapter work
    //return new CardAdapter(cell.getCard());
    return null;
  }

  @Override
  public boolean hasNeighborToThe(CardinalDirection direction) {
    //boolean hasNeighborToThe(CardinalDirection dir)
    //return GridCellAbstractAdapter.hasNeighborToThe(dir);
    return false;
  }

  @Override
  public GridCellReadOnly getNeighborToThe(CardinalDirection direction) {
    //GridCellReadOnly getCellToThe(CardinalDirection dir)
    //return GridCellAbstractAdapter.getCellToThe(dir)
    return null;
  }

}
