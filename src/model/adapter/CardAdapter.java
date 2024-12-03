package model.adapter;


import model.Card;
import model.CardinalDirection;

public class CardAdapter extends provider.model.Card implements Card {
  @Override
  public int getNorth() {
    return 0;
  }

  @Override
  public int getSouth() {
    return 0;
  }

  @Override
  public int getEast() {
    return 0;
  }

  @Override
  public int getWest() {
    return 0;
  }

  @Override
  public int getDirection(CardinalDirection dir) {
    return 0;
  }

  @Override
  public boolean isStrongerCard(Card opposing, CardinalDirection dir) {
    return false;
  }

  //Take in card

  //Constructor card.getName(), new HashMap.put(North, card.getNorth) etc.

  //Rest of it can stay the same

  //A few public methods are missing Exceptions for null
}
