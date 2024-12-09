package model.rules;

import model.Card;
import model.CardinalDirection;

public class FallenAceRule implements BattleRule {
  BattleRule rules;

  FallenAceRule(BattleRule rules) {
    this.rules = rules;
  }

  @Override
  public boolean beatsCard(Card curr, Card opposing, CardinalDirection dir) {
    if (curr.getDirection(dir) == 1
    && opposing.getDirection(dir) == 10) {
      return true;
    } else {
      return rules.beatsCard(curr, opposing, dir);
    }
  }
}