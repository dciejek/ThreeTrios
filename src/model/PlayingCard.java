package model;

/**
 * Represents a playable card for ThreeTrios. Has 4 values for each
 * cardinal direction.
 */
public class PlayingCard implements Card {


  private final String name;
  private final CardValue north;
  private final CardValue east;
  private final CardValue south;
  private final CardValue west;

  public PlayingCard(String name, CardValue north, CardValue east, CardValue south, CardValue west) {
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
    return north.ordinal();
  }

  @Override
  public int getSouth() {
    return south.ordinal();
  }

  @Override
  public int getEast() {
    return east.ordinal();
  }

  @Override
  public int getWest() {
    return west.ordinal();
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
