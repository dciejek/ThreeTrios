package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class TestModel {
  ThreeTriosModel<PlayingCard> model;

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
    Assert.assertThrows(IllegalStateException.class,
        () -> model.placeCard(new PlayingCard("",
                    CardValue.ONE,
                    CardValue.ONE,
                    CardValue.ONE,
                    CardValue.ONE), 1, 1));

    model.startGame(new File("docs" + File.separator + "grid1"),
            new File("docs" + File.separator + "cards1"));

    Assert.assertThrows(IllegalArgumentException.class,
        () -> model.placeCard(model.getCurrentPlayer().getHand().get(0), -1, 1));

    Assert.assertThrows(IllegalArgumentException.class,
        () -> model.placeCard(model.getCurrentPlayer().getHand().get(0), 1, -1));

    model.placeCard(model.getCurrentPlayer().getHand().get(0), 0, 1);

    Assert.assertThrows(IllegalArgumentException.class,
        () -> model.placeCard(model.getCurrentPlayer().getHand().get(0), 0, 1));

    setUp();

    model.startGame(new File("docs" + File.separator + "SmallGrid"),
            new File("docs" + File.separator + "cards1"));

    model.placeCard(model.getCurrentPlayer().getHand().get(0), 0, 0);

    Assert.assertTrue(model.isGameOver());

    Assert.assertThrows(IllegalStateException.class,
        () -> model.placeCard(model.getCurrentPlayer().getHand().get(0), 0, 0));
  }

  @Test
  public void testPlaceCard() {

  }

  @Test
  public void testGetCurrentPlayerExceptions() {
    setUp();
    Assert.assertThrows(IllegalStateException.class,
        () -> model.getCurrentPlayer());
  }

  @Test
  public void testGetCurrentPlayer() {

  }

  @Test
  public void testIsGameOverExceptions() {
    setUp();
    Assert.assertThrows(IllegalStateException.class,
            () -> model.isGameOver());

  }

  @Test
  public void testIsGameOver() {

  }

  @Test
  public void testGetWinnerExceptions() {
    setUp();
    model.startGame(new File("docs" + File.separator + "SmallGrid"),
            new File("docs" + File.separator + "cards1"));
    Assert.assertThrows(IllegalStateException.class,
        () -> model.getWinner());
  }

  @Test
  public void testGetWinner() {

  }

  @Test
  public void testGetGridExceptions() {
    setUp();
    Assert.assertThrows(IllegalStateException.class,
        () -> model.getGrid());
  }

  @Test
  public void testGetGrid() {

  }

  @Test
  public void testGetRowExceptions() {
    setUp();
    Assert.assertThrows(IllegalStateException.class,
        () -> model.getRow(1));

    model.startGame(new File("docs" + File.separator + "SmallGrid"),
            new File("docs" + File.separator + "cards1"));
    Assert.assertThrows(IllegalArgumentException.class,
        () -> model.getRow(-1));
  }

  @Test
  public void testGetRow() {
    setUp();

    model.startGame(new File("docs" + File.separator + "SmallGrid"),
            new File("docs" + File.separator + "cards1"));
    Assert.assertEquals(null, model.getRow(0).get(0).getCard());
    Assert.assertEquals(null, model.getRow(0).get(0).getPlayerColor());

    Card newCard = model.getCurrentPlayer().getHand().get(0);
    Player newPlayer = model.getCurrentPlayer();
    model.placeCard(model.getCurrentPlayer().getHand().get(0), 0, 0);
    Assert.assertEquals(newCard, model.getRow(0).get(0).getCard());
    Assert.assertEquals(newPlayer.getColor(), model.getRow(0).get(0).getPlayerColor());
  }
}
