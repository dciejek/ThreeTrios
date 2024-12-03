package model.adapter;

import java.util.Objects;

import model.Cell;
import model.PlayingCard;
import model.ReadOnlyThreeTriosModel;
import model.ThreeTriosModel;
import provider.model.Card;
import provider.model.CardinalDirection;
import provider.model.GridCellReadOnly;
import provider.model.GridCellVisitable;
import provider.model.Referee;

public class GridCellVisitableAdapter implements GridCellVisitable {
  private final Cell<PlayingCard> cell;
  private final ReadOnlyThreeTriosModel<PlayingCard> model;

  public GridCellVisitableAdapter(Cell<PlayingCard> cell,
                                  ReadOnlyThreeTriosModel<PlayingCard> model) {
    this.cell = Objects.requireNonNull(cell);
    this.model = Objects.requireNonNull(model);
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
