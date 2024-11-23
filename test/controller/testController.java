package controller;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.StringWriter;
import java.util.List;
import java.util.Scanner;

import main.PlayerFactory;
import model.Cell;
import model.Player;
import model.PlayingCard;
import model.TTModel;
import model.ThreeTriosModel;
import view.TTGuiView;
import view.ThreeTriosFrame;

/**
 * Test class for the controller.
 */
public class TestController {

  @Test
  public void testMockModelConfirmsInputs() {
    Appendable ap = new StringBuilder();
    PlayerFactory factory = new PlayerFactory();
    Scanner sc = new Scanner(System.in);
    ThreeTriosModel model = new MockModelConfirmsInputs(ap);
    Player<PlayingCard> p1 = factory.stringToPlayer(model, sc.next());
    Player<PlayingCard> p2 = factory.stringToPlayer(model, sc.next());

    p1.setModel(model);
    p2.setModel(model);

    File cardsFile = new File("docs" + File.separator + "cards1");
    File gridFile = new File("docs" + File.separator + "3x3Grid");
    List<List<Cell<PlayingCard>>> grid = FileHandler.readGrid(gridFile);
    List<PlayingCard> cards = FileHandler.readCards(cardsFile);
    int rows = FileHandler.readRowNum(gridFile);
    int cols = FileHandler.readColNum(gridFile);

    model.startGame(grid, cards, rows, cols);

    ThreeTriosFrame view = new TTGuiView(model);
    ThreeTriosFrame view2 = new TTGuiView(model);

    ThreeTriosController controller = new TTController(model, p1, view);
    ThreeTriosController controller2 = new TTController(model, p2, view2);

    controller.playGame();
    controller2.playGame();

    view.setHighlightedCard(new Point(0, 1));
    controller.handleCellClicked(1, 2);

    Assert.assertEquals("1, 1, 2", ap.toString());
  }
}
