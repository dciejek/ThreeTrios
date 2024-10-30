package model;

/**
 * Represents a Cardinal Direction.
 */
public enum CardinalDirection {
  NORTH,
  SOUTH,
  EAST,
  WEST;

  public CardinalDirection oppositeDirection() {
    switch (this) {
      case NORTH:
        return SOUTH;
      case SOUTH:
        return NORTH;
      case EAST:
        return WEST;
      case WEST:
        return EAST;
      default:
        throw new IllegalArgumentException("Direction does not exist");
    }
  }

}

