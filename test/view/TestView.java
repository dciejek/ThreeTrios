package view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import controller.FileHandler;
import model.PlayingCard;
import model.TTModel;
import model.ThreeTriosModel;

/**
 * Tests for the text based view.
 */
public class TestView {

  ThreeTriosModel<PlayingCard> model;
  File cards;
  File grid;
  ThreeTriosView<PlayingCard> view;

  @Before
  public void setUp() {
    cards = new File("docs" + File.separator + "cards1");
    grid = new File("docs" + File.separator + "grid1");
    model = FileHandler.makeGame(grid, cards);
    view = new TTTextBasedView(model);
  }

  @Test
  public void testToString() {
    Assert.assertEquals("Player: B\n" +
            " _ \n" +
            "_  \n" +
            " _ \n" +
            "__ \n" +
            "   \n" +
            "Hand: \n" +
            "BlueMagic 1 2 A 1\n" +
            "FireDancer 2 2 3 9\n" +
            "ReeceDiGiacomo 3 7 1 8", view.toString());

    model.placeCard(0, 0, 1);

    Assert.assertEquals("Player: R\n" +
            " B \n" +
            "_  \n" +
            " _ \n" +
            "__ \n" +
            "   \n" +
            "Hand: \n" +
            "BigDragon 3 4 9 4\n" +
            "BurningMan 3 1 8 2\n" +
            "Cedar A A A A", view.toString());

  }
}
