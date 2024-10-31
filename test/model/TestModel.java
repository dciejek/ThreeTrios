package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestModel {
  ThreeTriosModel<PlayingCard, TTPlayer> model;

  @Test
  public void testModelConstruction() {
    model = new TTModel();
    Assert.assertTrue(model.getGrid().isEmpty());
  }

  @Before
  public void setUp() {

  }

  @Test
  public void testParseRows() {

  }

  @Test
  public void testParseColumns() {

  }

  @Test
  public void testInitializeCards() {

  }

  @Test
  public void testInitializeGrid() {

  }

  @Test
  public void testCreateGrid() {

  }

  @Test
  public void testAddCellToList() {

  }

  @Test
  public void testDealHands() {

  }

  @Test
  public void testStartGame() {

  }

  @Test
  public void testOpposingCardInBounds() {

  }

  @Test
  public void testBattleOpposingCell() {

  }

  @Test
  public void testBattlePhase() {

  }

  @Test
  public void testSwitchPlayer() {

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
  public void testGameWinner() {

  }

  @Test
  public void testGetGrid() {

  }

  @Test
  public void testGetRow() {

  }

  @Test
  public void testCountCells() {

  }
}
