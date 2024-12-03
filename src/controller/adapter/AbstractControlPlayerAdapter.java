package controller.adapter;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

import controller.ThreeTriosController;
import model.Card;
import provider.controller.AbstractControlPlayer;
import provider.model.CoachColor;
import provider.model.GamePlayer;
import provider.model.Model;
import provider.model.Move;
import provider.view.GameView;

public class AbstractControlPlayerAdapter extends AbstractControlPlayer
        implements ThreeTriosController<Card> {

  /**
   * Constructor.
   *
   * @param color  color of the player
   * @param view   view of the game
   * @param player player of the game
   */
  public AbstractControlPlayerAdapter(CoachColor color, GameView view, GamePlayer player) {
    super(color, view, player);
  }

  @Override
  public void playGame() {

  }

  @Override
  public void handleCellClicked(int row, int col) {
    //Their view handles cell clicked within itself
  }

  @Override
  public void refresh() {
    //Handled within their view classes
  }

  @Override
  public void accept(Consumer<Move> moveConsumer, Callable<Model> modelCallable) {

  }
}
