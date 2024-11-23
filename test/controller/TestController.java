package controller;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Scanner;

import main.PlayerFactory;
import model.Cell;
import model.Player;
import model.PlayerColor;
import model.PlayingCard;
import model.TTPlayer;
import model.ThreeTriosModel;
import view.TTGuiView;
import view.ThreeTriosFrame;

public class TestController {

  @Test
  public void testMockModelConfirmsInputs() {
    Appendable ap = new StringBuilder();
    PlayerFactory factory = new PlayerFactory();
    Scanner sc = new Scanner(System.in);
    ThreeTriosModel model = new MockModelConfirmsInputs(ap);


    File cardsFile = new File("docs" + File.separator + "cards1");
    File gridFile = new File("docs" + File.separator + "3x3Grid");
    List<List<Cell<PlayingCard>>> grid = FileHandler.readGrid(gridFile);
    List<PlayingCard> cards = FileHandler.readCards(cardsFile);
    int rows = FileHandler.readRowNum(gridFile);
    int cols = FileHandler.readColNum(gridFile);

    Player p1 = new TTPlayer(model, PlayerColor.RED);
    Player p2 = new TTPlayer(model, PlayerColor.BLUE);

    model.startGame(grid, cards, rows, cols);

    ThreeTriosFrame view = new TTGuiView(model);
    ThreeTriosFrame view2 = new TTGuiView(model);

    ThreeTriosController controller = new TTController(model, p1, view);
    ThreeTriosController controller2 = new TTController(model, p2, view2);

    controller.playGame();
    controller2.playGame();

    view.setHighlightedCard(new Point(0, 1));
    controller.handleCellClicked(1, 2);

    Assert.assertEquals("1, 2, 0\n", ap.toString());
  }


@Test
  public void testPlayGame() {

  }

  @Test
  public void testClickIgnoredNotOnTurn() {
    //nothing happens
    //Message sent
  }

  @Test
  public void testHighlightCard() {
    //Card is highlighted
  }

  @Test
  public void testUnhighlightCard() {
    //When a new card is highlighted old is un

    //When same card is clicked on again
  }

  @Test
  public void testGridClickedWithNoCardSelected() {
    //nothing happens
    //message sent
  }

  @Test
  public void testGridClickedWithCardSelected() {
    //card is played to the grid
  }

  @Test
  public void testPlayerClicks() {
    //A player clicks on a card
    //A player clicks on grid
  }

  @Test
  public void testStrategyClicks() {
    //A strategy works as expected
  }
}
