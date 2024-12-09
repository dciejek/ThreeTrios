package model.adapter;


import java.util.Objects;

import model.Card;
import model.CardinalDirection;
import model.PlayerColor;
import model.PlayingCard;
import model.rules.BattleRule;
import provider.model.AttackValue;
import provider.model.CoachColor;

/**
 * Two-way adapter to mask as both our Card and the provider's Card for the model.
 * Two-way because some of their methods took in the provider's Card type, and we needed to
 * convert from that, and vice versa is also true.
 */
public class CardAdapter implements Card, provider.model.Card {
  private final Card card;
  private final PlayerColor player;

  /**
   * Constructs a card from our implementation.
   * @param card our card implementation
   * @param player our player implementation
   */
  public CardAdapter(Card card, PlayerColor player) {
    this.card = Objects.requireNonNull(card);
    this.player = Objects.requireNonNull(player);
  }

  /**
   * Constructs a card from provider implementation.
   * @param card provider card
   * @param player provider player
   */
  public CardAdapter(provider.model.Card card, PlayerColor player) {
    this.card = cardConverter(card);
    this.player = player;
  }

  /**
   * Converts to our card implementation from provider.
   * @param card provider card
   * @return our card implementation
   */
  public static PlayingCard cardConverter(provider.model.Card card) {
    return new PlayingCard(card.getName(),
            AttackValueAdapter.attackValueToCardValue(
                    card.getAttackValue(provider.model.CardinalDirection.NORTH)),
            AttackValueAdapter.attackValueToCardValue(
                    card.getAttackValue(provider.model.CardinalDirection.EAST)),
            AttackValueAdapter.attackValueToCardValue(
                    card.getAttackValue(provider.model.CardinalDirection.SOUTH)),
            AttackValueAdapter.attackValueToCardValue(
                    card.getAttackValue(provider.model.CardinalDirection.WEST)));
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
  public boolean isStrongerCard(Card opposing, CardinalDirection dir, BattleRule rule) {
    return rule.beatsCard(this, opposing, dir);
  }
}
