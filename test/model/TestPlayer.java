package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for Player.
 */
public class TestPlayer {
  Player playerOne;
  Player playerTwo;
  Card weakCard;
  Card strongCard;
  Card playingCard;

  @Test
  public void testPlayerConstruction() {
    Player test;
    Assert.assertThrows(IllegalArgumentException.class,
        () -> new TTPlayer(null, null));
    test = new TTPlayer(new TTModel(), PlayerColor.BLUE);
    Assert.assertEquals(PlayerColor.BLUE, test.getColor());
    Assert.assertTrue(test.getHand().isEmpty());
  }

  @Before
  public void setUp() {
    ThreeTriosModel<Card> model = new TTModel();
    playerOne = new TTPlayer(model, PlayerColor.BLUE);
    playerTwo = new TTPlayer(model, PlayerColor.RED);

    weakCard = new PlayingCard("Weak",
            CardValue.ONE,
            CardValue.ONE,
            CardValue.ONE,
            CardValue.ONE);
    strongCard = new PlayingCard("Strong",
            CardValue.TEN,
            CardValue.TEN,
            CardValue.TEN,
            CardValue.TEN);
    playingCard = new PlayingCard("Card",
            CardValue.ONE,
            CardValue.TWO,
            CardValue.THREE,
            CardValue.FOUR);
  }

  @Test
  public void testPlayerMethods() {
    Assert.assertEquals(0,  playerOne.getHand().size());
    playerOne.addToHand(weakCard);
    Assert.assertEquals(1,  playerOne.getHand().size());

    Assert.assertEquals(PlayerColor.BLUE, playerOne.getColor());

    playerOne.removeFromHand(strongCard);
    Assert.assertEquals(1,  playerOne.getHand().size());
    playerOne.removeFromHand(weakCard);
    Assert.assertEquals(0,  playerOne.getHand().size());
  }
}
