package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import controller.FileHandler;

/**
 * Tests for the model.
 */
public class TestModel {
  ThreeTriosModel<PlayingCard> model;
  File evenCells = new File("docs" + File.separator + "InvalidGridEvenCells");
  File badRows = new File("docs" + File.separator + "InvalidGridBadRows");
  File badCols = new File("docs" + File.separator + "InvalidGridBadCols");
  File grid1File = new File("docs" + File.separator + "grid1");

  @Before
  public void setUp() {
    model = new TTModel();
  }

  @Test
  public void testStartGameExceptions() {
    setUp();
    Assert.assertThrows(IllegalArgumentException.class,
        () -> model.startGame(null, null, 1, 1));
    setUp();
    Assert.assertThrows(IllegalArgumentException.class,
        () -> model.startGame(FileHandler.readGrid(evenCells),
            FileHandler.readCards(new File("docs" + File.separator + "cards1")),
                FileHandler.readRowNum(evenCells),
                FileHandler.readColNum(evenCells)));
    setUp();
    Assert.assertThrows(IllegalArgumentException.class,
        () -> model.startGame(FileHandler.readGrid(badRows),
                    FileHandler.readCards(new File("docs" + File.separator + "cards1")),
                FileHandler.readRowNum(badRows), FileHandler.readColNum(badRows)));
    setUp();
    Assert.assertThrows(IllegalArgumentException.class,
        () -> model.startGame(FileHandler.readGrid(badCols),
                    FileHandler.readCards(new File("docs" + File.separator + "cards1")),
                FileHandler.readRowNum(badCols), FileHandler.readColNum(badCols)));
    setUp();
    Assert.assertThrows(IllegalArgumentException.class,
        () -> model.startGame(FileHandler.readGrid(grid1File),
                    FileHandler.readCards(new File("docs" + File.separator + "SmallDeckOfCards")),
                FileHandler.readRowNum(grid1File), FileHandler.readColNum(grid1File)));

    setUp();
    model.startGame(FileHandler.readGrid(grid1File),
                    FileHandler.readCards(new File("docs" + File.separator + "cards1")),
            FileHandler.readRowNum(grid1File), FileHandler.readColNum(grid1File));

    Assert.assertThrows(IllegalStateException.class,
        () -> model.startGame(FileHandler.readGrid(grid1File),
                FileHandler.readCards(new File("docs" + File.separator + "cards1")),
                FileHandler.readRowNum(grid1File), FileHandler.readColNum(grid1File)));

  }

  @Test
  public void testStartGame() {
    File cardsFile = new File("docs" + File.separator + "cards1");
    File gridFile = new File("docs" + File.separator + "grid1");
    List<List<Cell<PlayingCard>>> grid = FileHandler.readGrid(gridFile);
    List<PlayingCard> cards = FileHandler.readCards(cardsFile);
    int rows = FileHandler.readRowNum(gridFile);
    int cols = FileHandler.readColNum(gridFile);

    model.startGame(grid, cards, rows, cols);
    Assert.assertEquals(5, model.getGrid().size());
    Assert.assertEquals(4, model.getCurrentPlayer().getHand().size());
  }

  @Test
  public void testPlaceCardExceptions() {
    setUp();
    Assert.assertThrows(IllegalStateException.class,
        () -> model.placeCard(1, 1, 1));

    model = FileHandler.makeGame(new File("docs" + File.separator + "grid1"),
            new File("docs" + File.separator + "cards1"));

    Assert.assertThrows(IllegalArgumentException.class,
        () -> model.placeCard(0, -1, 1));

    Assert.assertThrows(IllegalArgumentException.class,
        () -> model.placeCard(0, 1, -1));

    model.placeCard(0, 0, 1);

    Assert.assertThrows(IllegalArgumentException.class,
        () -> model.placeCard(0, 0, 1));

    setUp();

    model = FileHandler.makeGame(new File("docs" + File.separator + "SmallGrid"),
            new File("docs" + File.separator + "cards1"));

    model.placeCard(0, 0, 0);

    Assert.assertTrue(model.isGameOver());

    Assert.assertThrows(IllegalStateException.class,
        () -> model.placeCard(0, 0, 0));
  }

  @Test
  public void testPlaceCard() {
    //Test a placing a card places one
    setUp();
    model = FileHandler.makeGame(new File("docs" + File.separator + "SmallGrid"),
            new File("docs" + File.separator + "cards1"));
    model.placeCard(0, 0, 0);
    Card first = new PlayingCard("BlueMagic",
            CardValue.ONE,
            CardValue.TEN,
            CardValue.TWO,
            CardValue.ONE);
    Assert.assertEquals("BlueMagic", model.getGrid().get(0).get(0).getCard().getName());
    Assert.assertEquals(1, model.getGrid().get(0).get(0).getCard().getNorth());
    Assert.assertEquals(10, model.getGrid().get(0).get(0).getCard().getEast());
    Assert.assertEquals(2, model.getGrid().get(0).get(0).getCard().getSouth());
    Assert.assertEquals(1, model.getGrid().get(0).get(0).getCard().getWest());
  }

  @Test
  public void testBattleAndComboStep() {
    setUp();
    model = FileHandler.makeGame(new File("docs" + File.separator + "3x3Grid"),
            new File("docs" + File.separator + "cards1"));
    Assert.assertEquals(PlayerColor.BLUE, model.getCurrentPlayer().getColor());

    model.placeCard(0, 0, 0);
    model.placeCard(0, 2, 2);
    model.placeCard(0, 0, 1);
    //Cells belong to same player
    Assert.assertEquals(PlayerColor.BLUE, model.getGrid().get(0).get(0).getPlayerColor());
    Assert.assertEquals(PlayerColor.BLUE, model.getGrid().get(0).get(1).getPlayerColor());
    //Cell 0 0 would beat cell 0 1 if they did battle
    Assert.assertEquals(10, model.getGrid().get(0).get(0).getCard().getEast());
    Assert.assertEquals(9, model.getGrid().get(0).get(1).getCard().getWest());

    //It is playerTwo's turn (Red)
    Assert.assertEquals(PlayerColor.RED, model.getCurrentPlayer().getColor());

    //Cell 0 0 south would lose to playerTwo's second card in hand's north
    Assert.assertEquals(2, model.getGrid().get(0).get(0).getCard().getSouth());
    Assert.assertEquals(10, model.getCurrentPlayer().getHand().get(1).getNorth());

    //A card can be played below cell 0 0
    Assert.assertEquals(new CardCell().toString(), model.getGrid().get(1).get(0).toString());

    //A combo is called an Cell 0 0, and Cell 0 1 are now red cells
    model.placeCard(1, 1, 0);
    Assert.assertEquals(PlayerColor.RED, model.getGrid().get(0).get(0).getPlayerColor());
    Assert.assertEquals(PlayerColor.RED, model.getGrid().get(0).get(1).getPlayerColor());
  }

  @Test
  public void testGetCurrentPlayerExceptions() {
    setUp();
    Assert.assertThrows(IllegalStateException.class,
        () -> model.getCurrentPlayer());
  }

  @Test
  public void testGetCurrentPlayer() {
    model = FileHandler.makeGame(new File("docs" + File.separator + "grid1"),
            new File("docs" + File.separator + "cards1"));
    Player startingPlayer = new TTPlayer(model, PlayerColor.BLUE);
    Assert.assertEquals(startingPlayer.getColor(), model.getCurrentPlayer().getColor());
    model.placeCard(0, 0, 1);
    Player nextPlayer = new TTPlayer(model, PlayerColor.RED);
    Assert.assertEquals(nextPlayer.getColor(), model.getCurrentPlayer().getColor());
  }

  @Test
  public void testIsGameOverExceptions() {
    setUp();
    Assert.assertThrows(IllegalStateException.class,
        () -> model.isGameOver());
  }

  @Test
  public void testIsGameOver() {
    setUp();
    model = FileHandler.makeGame(new File("docs" + File.separator + "SmallGrid"),
            new File("docs" + File.separator + "cards1"));
    Assert.assertFalse(model.isGameOver());
    model.placeCard(0, 0, 0);
    Assert.assertTrue(model.isGameOver());
  }

  @Test
  public void testGetWinnerExceptions() {
    setUp();
    model = FileHandler.makeGame(new File("docs" + File.separator + "SmallGrid"),
            new File("docs" + File.separator + "cards1"));
    Assert.assertThrows(IllegalStateException.class,
        () -> model.getWinner());
  }

  @Test
  public void testGetWinner() {
    setUp();
    model = FileHandler.makeGame(new File("docs" + File.separator + "SmallGrid"),
            new File("docs" + File.separator + "cards1"));
    Player winner = model.getCurrentPlayer();
    model.placeCard(0, 0, 0);

    Assert.assertTrue(model.isGameOver());
    Assert.assertEquals(winner, model.getWinner());
  }

  @Test
  public void testGetGridExceptions() {
    setUp();
    Assert.assertThrows(IllegalStateException.class,
        () -> model.getGrid());
  }

  @Test
  public void testGetGrid() {
    setUp();
    model = FileHandler.makeGame(new File("docs" + File.separator + "SmallGrid"),
            new File("docs" + File.separator + "cards1"));

    Assert.assertEquals(1, model.getGrid().size());
    Assert.assertEquals(1, model.getGrid().get(0).size());
    model.getGrid().clear();
    Assert.assertEquals(1, model.getGrid().size());
    Assert.assertEquals(1, model.getGrid().get(0).size());
    model.getGrid().get(0).add(new HoleCell());
    Assert.assertEquals(1, model.getGrid().size());
    Assert.assertEquals(1, model.getGrid().get(0).size());
  }

  @Test
  public void testGetRowExceptions() {
    setUp();
    Assert.assertThrows(IllegalStateException.class,
        () -> model.getRow(1));

    model = FileHandler.makeGame(new File("docs" + File.separator + "SmallGrid"),
            new File("docs" + File.separator + "cards1"));
    Assert.assertThrows(IllegalArgumentException.class,
        () -> model.getRow(-1));
  }

  @Test
  public void testGetRow() {
    setUp();

    model = FileHandler.makeGame(new File("docs" + File.separator + "SmallGrid"),
            new File("docs" + File.separator + "cards1"));
    Assert.assertEquals(null, model.getRow(0).get(0).getCard());
    Assert.assertEquals(null, model.getRow(0).get(0).getPlayerColor());

    Card newCard = model.getCurrentPlayer().getHand().get(0);
    Player newPlayer = model.getCurrentPlayer();
    model.placeCard(0, 0, 0);
    Assert.assertEquals(newCard, model.getRow(0).get(0).getCard());
    Assert.assertEquals(newPlayer.getColor(), model.getRow(0).get(0).getPlayerColor());
  }

  @Test
  public void testNumFlipped() {
    model = FileHandler.makeGame(grid1File, new File("docs" + File.separator + "cards1"));
    Assert.assertEquals(0, model.numFlipped(model.getCurrentPlayer().getHand().get(1), 0, 0));
    model.placeCard(1, 1, 0);
    Assert.assertEquals(1, model.numFlipped(model.getCurrentPlayer().getHand().get(0), 0, 0));
  }
}
