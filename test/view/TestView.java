package view;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import model.PlayingCard;
import model.TTModel;
import model.TTPlayer;
import model.ThreeTriosModel;

public class TestView {

  ThreeTriosModel<PlayingCard, TTPlayer> model;
  File cards;
  File grid;
  ThreeTriosView<PlayingCard> view;

  @Before
  public void setUp() {
    model = new TTModel();
    cards = new File("docs" + File.separator + "cards1");
    grid = new File("docs" + File.separator + "grid1");
    view = new TTTextBasedView(model);
  }

  @Test
  public void testToString() {
    model.startGame(grid, cards);
    view.toString();
  }
}
