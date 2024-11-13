package model;

/**
 * Represents a playable card for ThreeTrios. Has 4 values for each
 * cardinal direction, as well as a name.
 */
public class PlayingCard implements Card {


  private final String name;
  private final CardValue north;
  private final CardValue east;
  private final CardValue south;
  private final CardValue west;

  /**
   * Constructs a PlayingCard.
   * @param name    Name of the card
   * @param north   The north CardValue
   * @param east    The east CardValue
   * @param south   The south CardValue
   * @param west    The west CardValue
   */
  public PlayingCard(String name, CardValue north, CardValue east, CardValue south,
                     CardValue west) {
    if (name == null || north == null || east == null || south == null || west == null) {
      throw new IllegalArgumentException("Null is not a valid argument");
    }
    this.name = name;
    this.north = north;
    this.east = east;
    this.south = south;
    this.west = west;
  }


  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getNorth() {
    return north.ordinal() + 1;
  }

  @Override
  public int getSouth() {
    return south.ordinal() + 1;
  }

  @Override
  public int getEast() {
    return east.ordinal() + 1;
  }

  @Override
  public int getWest() {
    return west.ordinal() + 1;
  }

  @Override
  public int getDirection(CardinalDirection dir) {
    switch (dir) {
      case NORTH:
        return getNorth();
      case SOUTH:
        return getSouth();
      case EAST:
        return getEast();
      case WEST:
        return getWest();
      default:
        throw new IllegalArgumentException("Not a cardinal direction");
    }
  }

  @Override
  public boolean isStrongerCard(Card opposing, CardinalDirection dir) {
    return this.getDirection(dir)
            - opposing.getDirection(dir.oppositeDirection())
            > 0;
  }


  @Override
  public String toString() {
    return name
            + " " + north
            + " " + south
            + " " + east
            + " " + west;
  }
}
