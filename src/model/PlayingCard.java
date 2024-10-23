package model;

/**
 * Represents a playable card for ThreeTrios. Has 4 values for each
 * cardinal direction.
 */
public class PlayingCard implements Card {


  private final String name;
  CardValue north;
  CardValue east;
  CardValue south;
  CardValue west;
  Player owner;



  public PlayingCard(String name, CardValue northVal, CardValue eastVal, CardValue southVal, CardValue westVal) {
    this.name = name;
    north = northVal;
    east = eastVal;
    south = southVal;
    west = westVal;
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

    return owner.toString();
  }


}
