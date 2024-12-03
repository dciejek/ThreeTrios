package model.adapter;

import model.CardinalDirection;

public class CardinalDirectionAdapter {

  public static CardinalDirection
  providerDirectionToCardinalDirection(provider.model.CardinalDirection cardinalDirection) {
    switch (cardinalDirection) {
      case NORTH:
        return CardinalDirection.NORTH;
      case SOUTH:
        return CardinalDirection.SOUTH;
      case EAST:
        return CardinalDirection.EAST;
      case WEST:
        return CardinalDirection.WEST;
      default:
        throw new IllegalArgumentException("Not a valid CardinalDirection");
    }
  }

  public static provider.model.CardinalDirection
  cardinalDirectionToProviderDirection(CardinalDirection cardinalDirection) {
    switch (cardinalDirection) {
      case NORTH:
        return provider.model.CardinalDirection.NORTH;
      case SOUTH:
        return provider.model.CardinalDirection.SOUTH;
      case EAST:
        return provider.model.CardinalDirection.EAST;
      case WEST:
        return provider.model.CardinalDirection.WEST;
      default:
        throw new IllegalArgumentException("Not a valid CardinalDirection");
    }
  }
}
