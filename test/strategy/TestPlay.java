package strategy;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for Play.
 */
public class TestPlay {

  @Test
  public void testPlay() {
    Play play1 = new Play(1, 1, 0);
    Play play2 = new Play(2, 1, 0);

    Assert.assertEquals(1, play1.col);
    Assert.assertEquals(2, play2.row);
    Assert.assertEquals(0, play2.col);
  }
}
