package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import controller.FileHandler;
import model.rules.BattleRule;
import model.rules.FallenAceRule;
import model.rules.NormalRules;
import model.rules.PlusPreRule;
import model.rules.PreBattleRule;

/**
 * Tests for combinations of BattleRules and PreBattleRules.
 */
public class TestCombos {
  ThreeTriosModel<Card> model;
  BattleRule br1;
  BattleRule br2;
  Player<Card> p1;
  Player<Card> p2;
  File grid1File = new File("docs" + File.separator + "3x3Grid");
  File cardsFile = new File("docs" + File.separator + "cards1");
  PreBattleRule rule1;
  PreBattleRule rule2;

  @Before
  public void setUp() {
    br1 = null;
    model = null;
    p1 = null;
    p2 = null;
    rule1 = null;
  }

  @Test
  public void testFallenAceAndPlusRule() {
    br1 = new FallenAceRule(new NormalRules());
    rule1 = new PlusPreRule();
    p1 = new TTPlayer(model, PlayerColor.RED);
    p2 = new TTPlayer(model, PlayerColor.BLUE);
    model = new TTModel(p1, p2, br1);

    model.startGame(FileHandler.readGrid(grid1File),
            FileHandler.readCards(cardsFile), 3 ,3);

    model.placeCard(1, 1, 1);
    model.placeCard(2, 2, 2);
    model.placeCard(3, 2, 0);

    model.placeCard(3, 2, 1);

    Assert.assertEquals(3, model.getScore(p2));
  }

  @Test
  public void testReverseAndSameRule() {
    br1 = new FallenAceRule(new NormalRules());
    rule1 = new PlusPreRule();
    p1 = new TTPlayer(model, PlayerColor.RED);
    p2 = new TTPlayer(model, PlayerColor.BLUE);
    model = new TTModel(p1, p2, br1);

    model.startGame(FileHandler.readGrid(grid1File),
            FileHandler.readCards(cardsFile), 3 ,3);

    model.placeCard(1, 1, 1);
    model.placeCard(2, 2, 2);
    model.placeCard(3, 2, 0);

    model.placeCard(0, 2, 1);

    Assert.assertEquals(3, model.getScore(p2));
  }
}
