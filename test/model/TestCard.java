package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for Card.
 */
public class TestCard {
  Card weakCard;
  Card strongCard;
  Card playingCard;

  @Test
  public void testCardConstruction() {
    Assert.assertThrows(IllegalArgumentException.class,
        () -> new PlayingCard(null,
                    CardValue.TEN,
                    CardValue.TEN,
                    CardValue.TEN,
                    CardValue.TEN));
    Card test = new PlayingCard("Test",
            CardValue.TEN,
            CardValue.TEN,
            CardValue.TEN,
            CardValue.TEN);
    Assert.assertEquals("Test", test.getName());
    Assert.assertEquals(10, test.getNorth());
    Assert.assertEquals(10, test.getEast());
    Assert.assertEquals(10, test.getSouth());
    Assert.assertEquals(10, test.getWest());
  }

  @Before
  public void setUp() {
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
  public void testGetDirection() {
    Assert.assertEquals(1, playingCard.getDirection(CardinalDirection.NORTH));
    Assert.assertEquals(2, playingCard.getDirection(CardinalDirection.EAST));
    Assert.assertEquals(3, playingCard.getDirection(CardinalDirection.SOUTH));
    Assert.assertEquals(4, playingCard.getDirection(CardinalDirection.WEST));
  }

  @Test
  public void testIsStrongerCard() {
    Assert.assertTrue(strongCard.isStrongerCard(weakCard, CardinalDirection.NORTH));
    Assert.assertFalse(weakCard.isStrongerCard(strongCard, CardinalDirection.SOUTH));
  }

  @Test
  public void testToString() {
    Assert.assertEquals("Strong A A A A", strongCard.toString());
    Assert.assertEquals("Weak 1 1 1 1", weakCard.toString());
  }
}
