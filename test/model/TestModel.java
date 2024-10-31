package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class TestModel {
  ThreeTriosModel<PlayingCard, TTPlayer> model;

  @Before
  public void setUp() {
    model = new TTModel();
  }

  @Test
  public void testStartGameExceptions() {
    setUp();
    Assert.assertThrows(IllegalArgumentException.class,
        () -> model.startGame(null, null));
    setUp();
    Assert.assertThrows(IllegalArgumentException.class,
        () -> model.startGame(new File("docs" + File.separator + "InvalidGridEvenCells"),
            new File("docs" + File.separator + "cards1")));
    setUp();
    Assert.assertThrows(IllegalArgumentException.class,
        () -> model.startGame(new File("docs" + File.separator + "InvalidGridBadRows"),
                    new File("docs" + File.separator + "cards1")));
    setUp();
    Assert.assertThrows(IllegalArgumentException.class,
        () -> model.startGame(new File("docs" + File.separator + "InvalidGridBadCols"),
                    new File("docs" + File.separator + "cards1")));
    setUp();
    Assert.assertThrows(IllegalArgumentException.class,
        () -> model.startGame(new File("docs" + File.separator + "grid1"),
                    new File("docs" + File.separator + "SmallDeckOfCards")));

    setUp();
    model.startGame(new File("docs" + File.separator + "grid1"),
                    new File("docs" + File.separator + "cards1"));

    Assert.assertThrows(IllegalStateException.class,
        () -> model.startGame(new File("docs" + File.separator + "grid1"),
                    new File("docs" + File.separator + "cards1")));

  }

  @Test
  public void testStartGame() {

  }

  @Test
  public void testPlaceCardExceptions() {
    setUp();
    model.startGame(new File("docs" + File.separator + "grid1"),
            new File("docs" + File.separator + "cards1"));

    Assert.assertThrows(IllegalArgumentException.class,
        () -> model.placeCard(null, 1, 1));


  }

  @Test
  public void testPlaceCard() {

  }


  @Test
  public void testGetCurrentPlayer() {

  }

  @Test
  public void testIsGameOver() {

  }

  @Test
  public void testGetWinner() {

  }

  @Test
  public void testGetGrid() {

  }

  @Test
  public void testGetRow() {

  }
}
