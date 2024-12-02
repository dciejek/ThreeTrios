package provider.model;

import java.util.EnumMap;
import java.util.Map;

import model.Card;
import model.CardinalDirection;

/**
 * To represent a cell on a grid on a game of three trios.
 */
abstract class GridCellAbstract implements GridCellVisitable {

  private final Map<model.CardinalDirection, GridCellAbstract> neighbors;

  /**
   * Constructs an AGridCell.
   */
  public GridCellAbstract() {
    this.neighbors = new EnumMap<>(model.CardinalDirection.class);
  }

  /**
   * To link [this] board cell to [other] in direction [dir]. EFFECT: also links [other]
   * to [this] in the opposite direction of [dir].
   * @param other - the other board cell
   * @param dir   - the direction [this] links to [other]
   */
  protected final void link(GridCellAbstract other, model.CardinalDirection dir) {
    this.neighbors.put(dir, other);
    other.neighbors.put(dir.opposite(), this);
  }

  /**
   * To evaluate if this cell can have a card.
   * @return - if this cell can have a card
   */
  public boolean canHaveCard() {
    return false;
  }

  /**
   * Whether this cell has a card.
   * @return - whether this cell has a card
   */
  public boolean hasCard() {
    return false;
  }

  /**
   * To return the card held in this cell.
   * @return - the card held in this cell.
   */
  public model.Card getCard() {
    throw new IllegalStateException("Can't get card from this cell");
  }

  /**
   * To indicate whether a neighbor exists in a given cardinal direction.
   * @param direction - the direction to consider
   * @return
   */
  public boolean hasNeighborToThe(model.CardinalDirection direction) {
    return this.neighbors.get(direction) != null;
  }

  /**
   * To get the neighbor to the [N/S/E/W].
   * @param direction - the cardinal direction to consider
   * @return - the neighbor (if any) in [direction]
   */
  public GridCellAbstract getNeighborToThe(CardinalDirection direction) {
    return this.neighbors.get(direction);
  }

  /**
   * To place the card on this cell.
   * @param card - the card to place
   * @throws IllegalStateException if canHaveCard() is false
   */
  protected void placeCard(Card card) {
    throw new IllegalStateException("Can't place card on this cell");
  }

  /**
   * To accept a battle phase officiated by [ref].
   * @param ref - the ref that controls the rules and what happens during this battle
   *            phase
   */
  public abstract void acceptBattlePhase(Referee ref);

  protected String renderTextConstructor() {
    throw new IllegalStateException("not implemented");
  }

}
