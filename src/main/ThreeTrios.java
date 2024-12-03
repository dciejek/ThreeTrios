package main;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import controller.FileHandler;
import controller.TTController;
import controller.ThreeTriosController;
import controller.adapter.AbstractControlPlayerAdapter;
import model.Card;
import model.Cell;
import model.Player;
import model.PlayingCard;
import model.ReadOnlyThreeTriosModel;
import model.TTModel;
import model.ThreeTriosModel;
import model.adapter.CoachColorAdapter;
import provider.model.CoachColor;
import provider.model.GamePlayer;
import provider.view.DrawGrid;
import provider.view.DrawHand;
import provider.view.GUIGridBase;
import provider.view.GUIGridInteractive;
import provider.view.GUIHandBase;
import provider.view.GUIHandInteractive;
import provider.view.GUIPlayerDelegate;
import provider.view.GUIPlayerInteractive;
import provider.view.GameView;
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
    ThreeTriosModel<Card> model = new TTModel();
    Player<Card> p1 = factory.stringToPlayer(model, sc.next());
    Player<Card> p2 = factory.stringToPlayer(model, sc.next());
    
    model = new TTModel(p1, p2);
    p1.setModel(model);
    p2.setModel(model);

    File cardsFile = new File("docs" + File.separator + "cards1");
    File gridFile = new File("docs" + File.separator + "3x3Grid");
    List<List<Cell<Card>>> grid = FileHandler.readGrid(gridFile);
    List<Card> cards = FileHandler.readCards(cardsFile);
    int rows = FileHandler.readRowNum(gridFile);
    int cols = FileHandler.readColNum(gridFile);

    model.startGame(grid, cards, rows, cols);
    
    ThreeTriosFrame view = new TTGuiView(model);

    CoachColor player2Color = CoachColorAdapter.playerColorToCoachColor(p2.getColor());
    GameView view2 = buildProviderView(player2Color);
    
    ThreeTriosController<Card> controller = new TTController(model, p1, view);
    ThreeTriosController<Card> controller2 = new AbstractControlPlayerAdapter(player2Color,
            view2, buildProviderPlayer());
    
    controller.playGame();
    controller2.playGame();
  }

  private static GameView buildProviderView(CoachColor coachColor) {
    GUIHandBase redHand = new GUIHandBase(new DrawHand());
    GUIHandBase blueHand = new GUIHandBase(new DrawHand());
    GUIGridBase grid = new GUIGridBase(new DrawGrid());

    return new GUIPlayerDelegate(redHand, blueHand, grid, coachColor);
  }

  private static GamePlayer buildProviderPlayer() {
    GUIHandBase redHand = new GUIHandBase(new DrawHand());
    GUIHandInteractive blueHand = new GUIHandInteractive(new DrawHand());
    GUIGridInteractive grid = new GUIGridInteractive(new DrawGrid());

    return new GUIPlayerInteractive(redHand, blueHand, grid);
  }


}


//helper method to build providers view implement in controllerAdapter
//Building GUIPlayerInteractive
  //GUIHandBase
    //DrawHand
      //List<Card>, BufferedImage
  //GUIHandInteraction
    //DrawHand
     //List<Card>, BufferedImage
  //GUIGridInteraction
   //DrawGrid
    //Grid, BufferedImage


