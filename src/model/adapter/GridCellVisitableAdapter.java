package model.adapter;

import java.util.Objects;

import model.Cell;
import model.PlayingCard;
import provider.model.Card;
import provider.model.CardinalDirection;
import provider.model.GridCellReadOnly;
import provider.model.GridCellVisitable;
import provider.model.Referee;

public class GridCellVisitableAdapter implements GridCellVisitable {
  private final Cell<PlayingCard> cell;

  public GridCellVisitableAdapter(Cell<PlayingCard> cell) {
    this.cell = Objects.requireNonNull(cell);
  }

  @Override
  public void acceptBattlePhase(Referee ref) {
    //Is handled separately by our model through our public placeCard and private helper
    // battlePhase methods.
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
    return new CardAdapter(cell.getCard(), cell.getPlayerColor());
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
