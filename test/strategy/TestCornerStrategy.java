package strategy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import controller.FileHandler;
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

  ThreeTriosModel model1;
  ThreeTriosModel model2;
  ThreeTriosModel model3;
  TTStrategy cornerStrategy;

  @Before
  public void setUp() {
    model1 = FileHandler.makeGame(grid2File, cards1);
    cornerStrategy = new CornerStrategy();
  }

  @Test
  public void testPlayToPoint() {
    Assert.assertEquals(null, cornerStrategy.playToPoint(model1,
            model1.getCurrentPlayer()));
  }
}
