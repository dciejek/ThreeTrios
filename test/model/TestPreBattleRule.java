package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import model.rules.PlusPreRule;
import model.rules.PreBattleRule;
import model.rules.SamePreRule;

/**
 * Tests for PreBattleRules.
 */
public class TestPreBattleRule {
  PreBattleRule rule1;
  PreBattleRule rule2;
  PlayingCard pc1;
  PlayingCard pc2;
  PlayingCard pc3;
  PlayingCard pc4;

  @Before
  public void setUp() {
    rule1 = new PlusPreRule();
    rule2 = new SamePreRule();
    pc1 =  new PlayingCard("test2", CardValue.ONE, CardValue.ONE,
            CardValue.ONE, CardValue.ONE);
    pc2 = new PlayingCard("test", CardValue.TEN, CardValue.TEN,
            CardValue.TEN, CardValue.TEN);
    pc3 =  new PlayingCard("test2", CardValue.SIX, CardValue.SIX,
            CardValue.SIX, CardValue.SIX);
    pc4 = new PlayingCard("test", CardValue.FIVE, CardValue.FIVE,
            CardValue.FIVE, CardValue.FIVE);
  }

  @Test
  public void testSameRuleAddsWinners() {
    // add winners
    rule2.apply(pc2, pc2, CardinalDirection.SOUTH);
    rule2.apply(pc2, pc2, CardinalDirection.EAST);

    Assert.assertEquals(Set.of(CardinalDirection.SOUTH, CardinalDirection.EAST),
            rule2.getWinners());

    rule2.reset();

    Assert.assertEquals(Set.of(), rule2.getWinners());
  }

  @Test
  public void testSameRuleNoWinnersWhenOneEqual() {
    rule2.apply(pc2, pc1, CardinalDirection.SOUTH);
    rule2.apply(pc2, pc2, CardinalDirection.EAST);

    Assert.assertEquals(Set.of(), rule2.getWinners());
  }

  @Test
  public void testPlusRuleAddsWinners() {
    rule1.apply(pc2, pc2, CardinalDirection.SOUTH);
    rule1.apply(pc2, pc2, CardinalDirection.EAST);

    Assert.assertEquals(Set.of(CardinalDirection.SOUTH, CardinalDirection.EAST),
            rule1.getWinners());

    rule1.reset();

    Assert.assertEquals(Set.of(), rule1.getWinners());
  }

  @Test
  public void testPlusRuleNoWinnersWhenOnePasses() {
    // two different sums
    rule1.apply(pc1, pc2, CardinalDirection.SOUTH);
    rule1.apply(pc1, pc4, CardinalDirection.EAST);

    Assert.assertEquals(Set.of(), rule1.getWinners());
  }

}
