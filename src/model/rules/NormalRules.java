package model.rules;

import model.Card;
import model.CardinalDirection;

public class NormalRules implements BattleRule {

  @Override
  public boolean beatsCard(Card curr, Card opposing, CardinalDirection dir) {
    return curr.getDirection(dir) - opposing.getDirection(dir) > 0;
  }
}
