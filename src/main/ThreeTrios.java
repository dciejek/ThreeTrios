package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.FileHandler;
import controller.TTController;
import controller.ThreeTriosController;
import model.Card;
import model.Player;
import model.TTModel;
import model.ThreeTriosModel;
import model.adapter.CardAdapter;
import model.adapter.GridAdapter;
import model.adapter.ModelAdapter;
import provider.model.Grid;
import provider.view.DrawGrid;
import provider.view.DrawHand;
import provider.view.GUIGridInteractive;
import provider.view.GUIHandBase;
import provider.view.GUIHandInteractive;
import view.TTGuiView;
import view.ThreeTriosFrame;
import view.adapter.GameViewAdapter;

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
    ModelAdapter modelAdapter = new ModelAdapter(model);
    p1.setModel(modelAdapter);
    p2.setModel(modelAdapter);

    File cardsFile = new File("docs" + File.separator + "cards1");
    File gridFile = new File("docs" + File.separator + "3x3Grid");
    Grid grid = new GridAdapter(FileHandler.readGrid(gridFile));
    List<Card> cards = FileHandler.readCards(cardsFile);
    List<provider.model.Card> deck = new ArrayList<>();

    cardToProviderConversion(cards, deck, p1, p2);

    modelAdapter.startGame(grid, deck, null);


    ThreeTriosFrame<Card> view = new TTGuiView(modelAdapter);
    GameViewAdapter providerView = buildProviderView();

    ThreeTriosController controller = new TTController(modelAdapter, p1, view);
    ThreeTriosController controller2 =
            new TTController(modelAdapter, p2, providerView);

    controller.playGame();
    controller2.playGame();

    providerView.accept(move -> { }, () -> modelAdapter);
  }

  private static void cardToProviderConversion(List<Card> cards,
                                               List<provider.model.Card> deck,
                                               Player<Card> p1, Player<Card> p2) {
    int count = 0;
    for (Card card : cards) {
      if (count % 2 == 0) {
        deck.add(new CardAdapter(card, p1.getColor()));
      } else {
        deck.add(new CardAdapter(card, p2.getColor()));
      }
      count++;
    }
  }

  private static GameViewAdapter buildProviderView() {
    GUIHandBase redHand = new GUIHandBase(new DrawHand());
    GUIHandInteractive blueHand = new GUIHandInteractive(new DrawHand());
    GUIGridInteractive grid = new GUIGridInteractive(new DrawGrid());

    return new GameViewAdapter(redHand, blueHand, grid);
  }

}

