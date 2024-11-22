package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for Cell.
 */
public class TestCell {
  Cell holeCell;
  Cell cardCell;
  Cell playerOneCell;
  Card random;
  Player player;
  ThreeTriosModel model;

  @Before
  public void setUp() {
    holeCell = new HoleCell();
    cardCell = new CardCell();
    playerOneCell = new CardCell();
    model = new TTModel();
    random = new PlayingCard("random",
            CardValue.ONE, CardValue.FIVE, CardValue.THREE, CardValue.NINE);
    player = new TTPlayer(model, PlayerColor.BLUE);
  }

  @Test
  public void testHoleCellExceptions() {

    Assert.assertThrows(IllegalStateException.class,
        () -> holeCell.updateCell(random, player));
    Assert.assertThrows(IllegalStateException.class,
        () -> holeCell.getCard());
    Assert.assertThrows(IllegalStateException.class,
        () -> holeCell.setPlayerColor(player.getColor()));
    Assert.assertThrows(IllegalStateException.class,
        () -> holeCell.getPlayerColor());
  }

  @Test
  public void testToString() {
    Assert.assertEquals(" ", holeCell.toString());
    Assert.assertEquals("_", cardCell.toString());
    playerOneCell.updateCell(random, player);
    Assert.assertEquals("B", playerOneCell.toString());
  }

  @Test
  public void testCardCellMethods() {
    Assert.assertFalse(playerOneCell.hasCard());
    playerOneCell.updateCell(random, player);

    Assert.assertEquals(random, playerOneCell.getCard());
    Assert.assertEquals(PlayerColor.BLUE, playerOneCell.getPlayerColor());
    Assert.assertTrue(playerOneCell.hasCard());
  }
}
