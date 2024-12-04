package strategy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import controller.FileHandler;
import model.Card;
import model.ThreeTriosModel;

/**
 * Tests for CornerStrategy.
 */
public class TestCornerStrategy {
  File evenCells = new File("docs" + File.separator + "InvalidGridEvenCells");
  File badRows = new File("docs" + File.separator + "InvalidGridBadRows");
  File badCols = new File("docs" + File.separator + "InvalidGridBadCols");
  File grid1File = new File("docs" + File.separator + "grid1");
  File grid2File = new File("docs" + File.separator + "3x3Grid");
  File cards1 = new File("docs" + File.separator + "cards1");
  File sameCards = new File("docs" + File.separator + "AllSameCard");

  ThreeTriosModel<Card> model1;
  ThreeTriosModel<Card> model2;
  TTStrategy<Card> cornerStrategy;
  StringBuilder sb = new StringBuilder();

  @Before
  public void setUp() {
    model1 = FileHandler.makeGame(grid2File, cards1);
    cornerStrategy = new CornerStrategy();
    model2 = (ThreeTriosModel) new MockModelConfirmsCheckedPoints(sb);
  }

  @Test
  public void testPlayToPoint() {
    Play p = cornerStrategy.playToPoint(model1,
            model1.getCurrentPlayer());
    Assert.assertEquals(new Play(0, 0, 4), cornerStrategy.playToPoint(model1,
            model1.getCurrentPlayer()));
    model1.placeCard(0, 1, 1);
  }

  @Test
  public void testConfirmPoints() {
    model2.startGame(FileHandler.readGrid(grid2File), FileHandler.readCards(cards1),
            FileHandler.readRowNum(grid2File), FileHandler.readColNum(grid2File));
    // simulate play
    Play p = cornerStrategy.playToPoint(model2,
            model2.getCurrentPlayer());
    Assert.assertEquals("0, 0\n" +
            "0, 1\n" +
            "0, 1\n" +
            "1, 0\n" +
            "1, 0\n" +
            "0, 1\n" +
            "0, 1\n" +
            "1, 0\n" +
            "1, 0\n" +
            "0, 1\n" +
            "0, 1\n" +
            "1, 0\n" +
            "1, 0\n" +
            "0, 1\n" +
            "0, 1\n" +
            "1, 0\n" +
            "1, 0\n" +
            "0, 1\n" +
            "0, 1\n" +
            "1, 0\n" +
            "1, 0\n" +
            "0, 2\n" +
            "0, 1\n" +
            "0, 1\n" +
            "1, 2\n" +
            "1, 2\n" +
            "0, 1\n" +
            "0, 1\n" +
            "1, 2\n" +
            "1, 2\n" +
            "0, 1\n" +
            "0, 1\n" +
            "1, 2\n" +
            "1, 2\n" +
            "0, 1\n" +
            "0, 1\n" +
            "1, 2\n" +
            "1, 2\n" +
            "0, 1\n" +
            "0, 1\n" +
            "1, 2\n" +
            "1, 2\n" +
            "2, 0\n" +
            "2, 1\n" +
            "2, 1\n" +
            "1, 0\n" +
            "1, 0\n" +
            "2, 1\n" +
            "2, 1\n" +
            "1, 0\n" +
            "1, 0\n" +
            "2, 1\n" +
            "2, 1\n" +
            "1, 0\n" +
            "1, 0\n" +
            "2, 1\n" +
            "2, 1\n" +
            "1, 0\n" +
            "1, 0\n" +
            "2, 1\n" +
            "2, 1\n" +
            "1, 0\n" +
            "1, 0\n" +
            "2, 2\n" +
            "2, 1\n" +
            "2, 1\n" +
            "1, 2\n" +
            "1, 2\n" +
            "2, 1\n" +
            "2, 1\n" +
            "1, 2\n" +
            "1, 2\n" +
            "2, 1\n" +
            "2, 1\n" +
            "1, 2\n" +
            "1, 2\n" +
            "2, 1\n" +
            "2, 1\n" +
            "1, 2\n" +
            "1, 2\n" +
            "2, 1\n" +
            "2, 1\n" +
            "1, 2\n" +
            "1, 2\n", sb.toString());
  }
}
