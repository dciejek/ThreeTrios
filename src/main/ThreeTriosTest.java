package main;

import java.io.File;
import java.util.Arrays;
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
import model.rules.BattleRule;
import model.rules.FallenAceRule;
import model.rules.NormalRules;
import model.rules.PlusPreRule;
import model.rules.PreBattleRule;
import model.rules.ReverseRule;
import model.rules.SamePreRule;
import view.TTGuiView;
import view.ThreeTriosFrame;

/**
 * A main method that tests our view implementation.
 */
public class ThreeTriosTest {

  /**
   * Main method to test GUI based view.
   * @param args arguments
   */
  public static void main(String[] args) {
    PlayerFactory factory = new PlayerFactory();
    Scanner sc = new Scanner(System.in);
    ThreeTriosModel<Card> model = new TTModel();
    System.out.println("Game Rules (press enter for normal):");
    BattleRule gameRules = setGameRules(sc.nextLine());

    System.out.println("PreBattle Rule (press enter if none):");
    PreBattleRule preRules = setPreRules(sc.nextLine());

    System.out.println("Player Types:");
    Player<Card> p1 = factory.stringToPlayer(model, sc.next());
    Player<Card> p2 = factory.stringToPlayer(model, sc.next());

    model = new TTModel(p1, p2, gameRules, preRules);

    File cardsFile = new File("docs" + File.separator + "cards1");
    File gridFile = new File("docs" + File.separator + "3x3Grid");
    List<List<Cell<Card>>> grid = FileHandler.readGrid(gridFile);
    List<Card> cards = FileHandler.readCards(cardsFile);

    model.startGame(grid, cards, grid.size(), grid.get(0).size());

    ThreeTriosFrame<Card> view = new TTGuiView(model);
    ThreeTriosFrame<Card> view2 = new TTGuiView(model);

    ThreeTriosController controller = new TTController(model, p1, view);
    ThreeTriosController controller2 = new TTController(model, p2, view2);

    controller.playGame();
    controller2.playGame();
  }

  // Initializes PreBattleRules
  private static PreBattleRule setPreRules(String s) {

    if (s.equalsIgnoreCase("same")) {
      return new SamePreRule();
    } else if (s.equalsIgnoreCase("plus")) {
      return new PlusPreRule();
    }

    return null;
  }

  //initializes BattleRules
  private static BattleRule setGameRules(String s) {
    List<String> strs = Arrays.asList(s.split(" "));
    BattleRule ret = new NormalRules();

    if (strs.contains("reverse")) {
      ret = new ReverseRule(ret);
    }

    if (strs.contains("fallenAce")) {
      ret = new FallenAceRule(ret);
    }

    return ret;
  }
}
