package provider.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import model.CardinalDirection;

/**
 * to represent a card in three trios.
 */
public final class Card {

  private final String name;
  private final Map<model.CardinalDirection, AttackValue> attackValues;
  private CoachColor coachColor;

  /**
   * to construct a card with [name] and [attackValues].
   * @param name         - name of this card
   * @param attackValues - attack value in each direction
   */
  public Card(String name, Map<model.CardinalDirection, AttackValue> attackValues) {
    if (name == null) {
      throw new IllegalArgumentException("card name cannot be null");
    }
    if (attackValues == null) {
      throw new IllegalArgumentException("attack values cannot be null");
    }
    if (name.isEmpty()) {
      throw new IllegalArgumentException("card name cannot be empty");
    }
    this.name = name;
    this.attackValues = new HashMap<>(attackValues);
  }


  /**
   * to return the name of the card.
   * @return - the name of the card.
   */
  public String getName() {
    return name;
  }

  /**
   * to return the coach of this card.
   * @return - the current coach of this card.
   */
  public CoachColor getCoach() {
    return this.coachColor;
  }

  /**
   * to update the coach to [newCoach].
   * @param newCoach - the new coach of this
   */
  void setCoach(CoachColor newCoach) {
    this.coachColor = newCoach;
  }

  /**
   * to evaluate if this card beats [other] in [direction].
   * @param other     - the card to compare with
   * @param direction - the direction where this battles other
   * @return the result of the battle.
   */
  public boolean beats(Card other, model.CardinalDirection direction) {
    return getAttackValue(direction).beats(other.getAttackValue(direction.opposite()));
  }

  /**
   * to return the attack value of this card in [direction].
   * @param direction - the direction to check
   * @return - the attack value in [direction]
   */
  public AttackValue getAttackValue(model.CardinalDirection direction) {
    return attackValues.get(direction);
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Card) {
      Card other = (Card) obj;
      return this.toString().equals(other.toString())
          && this.coachColor == other.coachColor;
    } else {
      return false;
    }
  }

  // package private to prevent excess side-effect

  @Override
  public String toString() {
    String s = name;
    for (model.CardinalDirection c : model.CardinalDirection.values()) {
      s += " " + attackValues.get(c).toString();
    }
    return s;
  }

  /**
   * to set the attack value in [direction] to [av].
   * @param av        - the attack value to add
   * @param direction - the direction to update
   */
  void setAttackValueInDirection(AttackValue av, CardinalDirection direction) {
    attackValues.put(direction, av);
  }

  Card copy() {
    Card card = new Card(name, Collections.unmodifiableMap(attackValues));
    card.setCoach(this.coachColor);
    return card;
  }

}
