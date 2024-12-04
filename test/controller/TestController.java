package controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.Point;
import java.io.File;
import java.util.List;

import model.Card;
import model.Cell;
import model.Player;
import model.PlayerColor;
import model.StrategyPlayer;
import model.TTPlayer;
import model.ThreeTriosModel;
import strategy.CornerStrategy;
import view.TTGuiView;
import view.ThreeTriosFrame;

/**
 * Tests for the controller.
 */
public class TestController {

  int rows;
  int cols;
  Appendable ap;
  ThreeTriosModel model;
  File cardsFile = new File("docs" + File.separator + "cards1");
  File gridFile = new File("docs" + File.separator + "3x3Grid");
  List<List<Cell<Card>>> grid = FileHandler.readGrid(gridFile);
  List<Card> cards = FileHandler.readCards(cardsFile);
  ThreeTriosFrame view;
  ThreeTriosFrame view2;
  Player p1;
  Player p2;
  ThreeTriosController controller;
  ThreeTriosController controller2;

  @Before
  public void init() {
    ap = new StringBuilder();
    model = new MockModelConfirmsInputs(ap);
    rows = FileHandler.readRowNum(gridFile);
    cols = FileHandler.readColNum(gridFile);

    p1 = new TTPlayer(model, PlayerColor.RED);
    p2 = new TTPlayer(model, PlayerColor.BLUE);

    model.startGame(grid, cards, rows, cols);

    view = new TTGuiView(model);
    view2 = new TTGuiView(model);

    controller = new TTController(model, p1, view);
    controller2 = new TTController(model, p2, view2);

    controller.playGame();
    controller2.playGame();
  }


  @Test
  public void testMockModelConfirmsInputs() {

    controller.playGame();
    controller2.playGame();

    view.setHighlightedCard(new Point(0, 1));
    controller.handleCellClicked(1, 2);

    Assert.assertEquals("1, 2, 0\n", ap.toString());
  }

  @Test
  public void testClickIgnoredNotOnTurn() {
    controller.playGame();
    controller2.playGame();

    view2.setHighlightedCard(new Point(0, 1));
    controller2.handleCellClicked(1, 2);

    Assert.assertEquals("", ap.toString());
  }

  @Test
  public void testGridClickedWithNoCardSelected() {

    controller.playGame();
    controller2.playGame();

    controller.handleCellClicked(1, 2);

    Assert.assertEquals("", ap.toString());

  }

  @Test
  public void testStrategyClicks() {
    ap = new StringBuilder();
    model = new MockModelConfirmsInputs(ap);
    int rows = FileHandler.readRowNum(gridFile);
    int cols = FileHandler.readColNum(gridFile);

    p1 = new StrategyPlayer(model, PlayerColor.RED, new CornerStrategy());
    p2 = new TTPlayer(model, PlayerColor.BLUE);

    model.startGame(grid, cards, rows, cols);

    view = new TTGuiView(model);
    view2 = new TTGuiView(model);

    controller = new TTController(model, p1, view);
    controller2 = new TTController(model, p2, view2);

    controller.playGame();
    controller2.playGame();

    controller.handleCellClicked(1, 2);
    Assert.assertEquals("", ap.toString());
  }
}