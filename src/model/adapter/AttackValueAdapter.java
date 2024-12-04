package model.adapter;

import model.CardValue;
import provider.model.AttackValue;

/**
 * A class housing static conversion methods that allow for respective
 *     AttackValue <-> CardValue enumeration conversions.
 */
public class AttackValueAdapter {

  /**
   * Converts attack values -> card values.
   * @param av an attack value
   * @return the respective card value
   */
  public static CardValue attackValueToCardValue(AttackValue av) {
    switch (av) {
      case A:
        return CardValue.TEN;
      case NINE:
        return CardValue.NINE;
      case EIGHT:
        return CardValue.EIGHT;
      case SEVEN:
        return CardValue.SEVEN;
      case SIX:
        return CardValue.SIX;
      case FIVE:
        return CardValue.FIVE;
      case FOUR:
        return CardValue.FOUR;
      case THREE:
        return CardValue.THREE;
      case TWO:
        return CardValue.TWO;
      case ONE:
        return CardValue.ONE;
      default:
        throw new IllegalArgumentException("Not a valid AttackValue");
    }
  }

  /**
   * Converts card values -> attack values.
   * @param cv a card value
   * @return the respective attack value
   */
  public static AttackValue cardValueToAttackValue(CardValue cv) {
    switch (cv) {
      case TEN:
        return AttackValue.A;
      case NINE:
        return AttackValue.NINE;
      case EIGHT:
        return AttackValue.EIGHT;
      case SEVEN:
        return AttackValue.SEVEN;
      case SIX:
        return AttackValue.SIX;
      case FIVE:
        return AttackValue.FIVE;
      case FOUR:
        return AttackValue.FOUR;
      case THREE:
        return AttackValue.THREE;
      case TWO:
        return AttackValue.TWO;
      case ONE:
        return AttackValue.ONE;
      default:
        throw new IllegalArgumentException("Not a valid CardValue");
    }
  }

}
