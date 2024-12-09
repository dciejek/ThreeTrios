package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import controller.FileHandler;
import model.rules.BattleRule;
import model.rules.FallenAceRule;
import model.rules.NormalRules;
import model.rules.ReverseRule;

/**
 * Tests for BattleRules and combos.
 */
public class TestBattleRule {
  ThreeTriosModel<Card> model;
  BattleRule br1;
  BattleRule br2;
  Player<Card> p1;
  Player<Card> p2;
  File grid1File = new File("docs" + File.separator + "3x3Grid");
  File cardsFile = new File("docs" + File.separator + "cards1");


  @Before
  public void setUp() {

    ThreeTriosModel<Card> model = null;
    BattleRule br1 = null;
    BattleRule br2 = null;
  }

  @Test
  public void testReverse() {
    br1 = new ReverseRule(new NormalRules());
    PlayingCard pc1 =  new PlayingCard("test2", CardValue.ONE, CardValue.ONE,
            CardValue.ONE, CardValue.ONE);
    PlayingCard pc2 = new PlayingCard("test", CardValue.TEN, CardValue.TEN,
            CardValue.TEN, CardValue.TEN);

    Assert.assertTrue(br1.beatsCard(pc1, pc2, CardinalDirection.SOUTH));
    Assert.assertFalse(br1.beatsCard(pc2, pc1, CardinalDirection.SOUTH));
    Assert.assertFalse(br1.beatsCard(pc1, pc1, CardinalDirection.SOUTH));
  }

  @Test
  public void testReverseInGame() {
    br1 = new ReverseRule(new NormalRules());
    p1 = new TTPlayer(model, PlayerColor.RED);
    p2 = new TTPlayer(model, PlayerColor.BLUE);
    model = new TTModel(p1, p2, br1);

    model.startGame(FileHandler.readGrid(grid1File),
            FileHandler.readCards(cardsFile), 3 ,3);

    model.placeCard(3, 1, 1);
    Assert.assertEquals(PlayerColor.RED, model.getCellAt(1, 1).getPlayerColor());

    model.placeCard(1, 1, 0);
    Assert.assertEquals(PlayerColor.BLUE, model.getCellAt(1, 1).getPlayerColor());
  }

  @Test
  public void testReverseOnEqualInGame() {
    br1 = new ReverseRule(new NormalRules());
    p1 = new TTPlayer(model, PlayerColor.RED);
    p2 = new TTPlayer(model, PlayerColor.BLUE);
    model = new TTModel(p1, p2, br1);

    model.startGame(FileHandler.readGrid(grid1File),
            FileHandler.readCards(cardsFile), 3 ,3);

    model.placeCard(3, 1, 1);
    model.placeCard(3, 1, 2);
    Assert.assertEquals(PlayerColor.RED, model.getCellAt(1, 1).getPlayerColor());
  }

  @Test
  public void testReverseWorksWithCombo() {
    br1 = new ReverseRule(new NormalRules());
    p1 = new TTPlayer(model, PlayerColor.RED);
    p2 = new TTPlayer(model, PlayerColor.BLUE);
    model = new TTModel(p1, p2, br1);

    model.startGame(FileHandler.readGrid(grid1File),
            FileHandler.readCards(cardsFile), 3 ,3);

    model.placeCard(3, 1, 1);
    Assert.assertEquals(PlayerColor.RED, model.getCellAt(1, 1).getPlayerColor());

    model.placeCard(1, 1, 0);
    Assert.assertEquals(PlayerColor.BLUE, model.getCellAt(1, 1).getPlayerColor());
    model.placeCard(0, 2, 1);
    Assert.assertEquals(PlayerColor.RED, model.getCellAt(2, 1).getPlayerColor());
    Assert.assertEquals(PlayerColor.RED, model.getCellAt(1, 1).getPlayerColor());
    Assert.assertEquals(PlayerColor.BLUE, model.getCellAt(1, 0).getPlayerColor());
  }


  @Test
  public void testFallenAce() {
    br1 = new FallenAceRule(new NormalRules());
    PlayingCard pc1 =  new PlayingCard("test2", CardValue.ONE, CardValue.ONE,
            CardValue.ONE, CardValue.ONE);
    PlayingCard pc2 = new PlayingCard("test", CardValue.TEN, CardValue.TEN,
            CardValue.TEN, CardValue.TEN);

    Assert.assertTrue(br1.beatsCard(pc1, pc2, CardinalDirection.SOUTH));

    Assert.assertFalse(br1.beatsCard(pc2, pc1, CardinalDirection.SOUTH));
    Assert.assertFalse(br1.beatsCard(pc1, pc1, CardinalDirection.SOUTH));
  }

  @Test
  public void testFallenAceInGame() {
    br1 = new FallenAceRule(new NormalRules());
    p1 = new TTPlayer(model, PlayerColor.RED);
    p2 = new TTPlayer(model, PlayerColor.BLUE);
    model = new TTModel(p1, p2, br1);

    model.startGame(FileHandler.readGrid(grid1File),
            FileHandler.readCards(cardsFile), 3 ,3);


    model.placeCard(3, 1, 1);
    Assert.assertEquals(PlayerColor.RED, model.getCellAt(1, 1).getPlayerColor());

    model.placeCard(1, 1, 0);
    Assert.assertEquals(PlayerColor.RED, model.getCellAt(1, 1).getPlayerColor());
  }


  @Test
  public void testCombo() {
    br1 = new FallenAceRule(new ReverseRule(new NormalRules()));
    PlayingCard pc1 =  new PlayingCard("test2", CardValue.ONE, CardValue.ONE,
            CardValue.ONE, CardValue.ONE);
    PlayingCard pc2 = new PlayingCard("test", CardValue.TEN, CardValue.TEN,
            CardValue.TEN, CardValue.TEN);
    PlayingCard pc3 =  new PlayingCard("test2", CardValue.SIX, CardValue.SIX,
            CardValue.SIX, CardValue.SIX);
    PlayingCard pc4 = new PlayingCard("test", CardValue.FIVE, CardValue.FIVE,
            CardValue.FIVE, CardValue.FIVE);

    Assert.assertTrue(br1.beatsCard(pc1, pc2, CardinalDirection.SOUTH));
    Assert.assertFalse(br1.beatsCard(pc2, pc1, CardinalDirection.SOUTH));
    Assert.assertFalse(br1.beatsCard(pc1, pc1, CardinalDirection.SOUTH));
    Assert.assertTrue(br1.beatsCard(pc4, pc3, CardinalDirection.SOUTH));
  }
}
