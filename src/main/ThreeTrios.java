package main;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import controller.FileHandler;
import controller.TTController;
import controller.ThreeTriosController;
import model.Cell;
import model.Player;
import model.PlayingCard;
import model.TTModel;
import model.ThreeTriosModel;
import view.TTGuiView;
import view.ThreeTriosFrame;

/**
 * The placeholder main class for our project, as described by HW6 displays
 * the view as a moment in time as described by how the model is set up.
 */
public final class ThreeTrios {
  /**
   * Main method to test GUI based view.
   * @param args arguments
   */
  public static void main(String[] args) {
    PlayerFactory factory = new PlayerFactory();
    Scanner sc = new Scanner(System.in);
    ThreeTriosModel<PlayingCard> model = new TTModel();
    Player<PlayingCard> p1 = factory.stringToPlayer(model, sc.next());
    Player<PlayingCard> p2 = factory.stringToPlayer(model, sc.next());
    
    model = new TTModel(p1, p2);
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
  }
}
