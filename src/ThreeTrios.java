import java.awt.event.MouseListener;
import java.io.File;
import java.util.List;

import controller.FileHandler;
import model.Cell;
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
  public static void main(String[] args) {
    ThreeTriosModel<PlayingCard> model = new TTModel();
    File cardsFile = new File("docs" + File.separator + "cards1");
    File gridFile = new File("docs" + File.separator + "XGrid");
    List<List<Cell<PlayingCard>>> grid = FileHandler.readGrid(gridFile);
    List<PlayingCard> cards = FileHandler.readCards(cardsFile);
    int rows = FileHandler.readRowNum(gridFile);
    int cols = FileHandler.readColNum(gridFile);

    model.startGame(grid, cards, rows, cols);
    ThreeTriosFrame view = new TTGuiView(model);
    view.makeVisible();
    view.addClickListener();
  }
}
