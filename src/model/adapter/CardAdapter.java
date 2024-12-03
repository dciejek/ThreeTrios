package model.adapter;


import java.util.Objects;

import model.Card;
import model.CardinalDirection;
import model.Player;
import model.PlayerColor;
import provider.model.AttackValue;
import provider.model.CoachColor;

public class CardAdapter implements Card, provider.model.Card {
  private final Card card;
  private final PlayerColor player;

  public CardAdapter(Card card, PlayerColor player) {
    this.card = Objects.requireNonNull(card);
    this.player = Objects.requireNonNull(player);
  }

  @Override
  public String getName() {
    return card.getName();
  }

  @Override
  public CoachColor getCoach() {
    return CoachColorAdapter.playerColorToCoachColor(player);
  }

  @Override
  public boolean beats(provider.model.Card other, provider.model.CardinalDirection direction) {
    return this.getAttackValue(direction).fromAttackValue()
            - other.getAttackValue(direction.opposite()).fromAttackValue()
            > 0;
  }

  @Override
  public AttackValue getAttackValue(provider.model.CardinalDirection direction) {
    switch (direction) {
      case NORTH:
        return AttackValue.fromString(valueToString(card.getNorth()));
      case SOUTH:
        return AttackValue.fromString(valueToString(card.getSouth()));
      case EAST:
        return AttackValue.fromString(valueToString(card.getEast()));
      case WEST:
        return AttackValue.fromString(valueToString(card.getWest()));
      default:
        throw new IllegalArgumentException("Not a cardinal direction");
    }
  }

  private String valueToString(int value) {
    if (value == 10) {
      return "A";
    } else {
      return Integer.toString(value);
    }
  }

  @Override
  public int getNorth() {
    return card.getNorth();
  }

  @Override
  public int getSouth() {
    return card.getSouth();
  }

  @Override
  public int getEast() {
    return card.getEast();
  }

  @Override
  public int getWest() {
    return card.getWest();
  }

  @Override
  public int getDirection(CardinalDirection dir) {
    return card.getDirection(dir);
  }

  @Override
  public boolean isStrongerCard(Card opposing, CardinalDirection dir) {
    return card.isStrongerCard(opposing, dir);
  }
}
