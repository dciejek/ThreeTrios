package main;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import controller.FileHandler;
import controller.TTController;
import controller.ThreeTriosController;
import model.Card;
import model.Cell;
import model.Player;
import model.TTModel;
import model.ThreeTriosModel;
import view.TTGuiView;
import view.ThreeTriosFrame;

public class ThreeTriosTest {

  /**
   * Main method to test GUI based view.
   * @param args arguments
   */
  public static void main(String[] args) {
    PlayerFactory factory = new PlayerFactory();
    Scanner sc = new Scanner(System.in);
    ThreeTriosModel<Card> model = new TTModel();
    Player<Card> p1 = factory.stringToPlayer(model, sc.next());
    Player<Card> p2 = factory.stringToPlayer(model, sc.next());

    model = new TTModel(p1, p2);

    File cardsFile = new File("docs" + File.separator + "cards1");
    File gridFile = new File("docs" + File.separator + "3x3Grid");
    List<List<Cell<Card>>> grid = FileHandler.readGrid(gridFile);
    List<Card> cards = FileHandler.readCards(cardsFile);

    model.startGame(grid, cards, grid.size(), grid.get(0).size());

    ThreeTriosFrame<Card> view = new TTGuiView(model);
    ThreeTriosFrame<Card> view2 = new TTGuiView(model);

    ThreeTriosController<Card> controller = new TTController(model, p1, view);
    ThreeTriosController<Card> controller2 = new TTController(model, p2, view2);

    controller.playGame();
    controller2.playGame();
  }
}
