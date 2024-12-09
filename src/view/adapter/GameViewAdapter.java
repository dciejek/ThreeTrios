package view.adapter;

import java.awt.geom.Point2D;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.swing.JPanel;

import controller.ThreeTriosController;
import model.ReadOnlyThreeTriosModel;
import provider.model.Model;
import provider.model.Move;
import provider.view.GUIGridInteractive;
import provider.view.GUIHandBase;
import provider.view.GUIHandInteractive;
import provider.view.GUIPlayerInteractive;
import view.ThreeTriosFrame;

/**
 * Class Adapter that implements our view ThreeTriosFrame and extends the interactive provider
 * view GUIPlayerInteractive so that it can be directly utilized with our controller and updated
 * directly by the player.
 */
public class GameViewAdapter extends GUIPlayerInteractive implements ThreeTriosFrame {
  private Model model;
  private Point2D highlightedCard;
  protected Move move;
  private ThreeTriosController listener;

  /**
   * Constructs an interactive player view for player 2 using provider view.
   * @param redHand provider hand panel
   * @param blueHand provider interactive hand panel
   * @param grid provider interactive grid panel
   */
  public GameViewAdapter(GUIHandBase redHand, GUIHandInteractive blueHand,
                         GUIGridInteractive grid) {
    super(redHand, blueHand, grid);
    model = null;
    highlightedCard = null;
    listener = null;
  }

  @Override
  public void addClickListener(ThreeTriosController listener) {
    if (this.listener == null) {
      this.listener = listener;
    }
  }

  @Override
  public void refresh() {
    renderModel(model);
    configGlassPlane();
  }

  @Override
  public void makeVisible() {
    //This is already handled by their view implementation
  }

  @Override
  public Point2D getHighlightedCard() {
    return new Point2D.Double(highlightedCard.getX(), highlightedCard.getY());
  }

  @Override
  public void setHighlightedCard(Point2D point) {
    highlightedCard = point;
  }

  @Override
  public JPanel getPanel() {
    //This is used to display messages, all messages are handled by their view implementation
    return null;
  }

  @Override
  public ReadOnlyThreeTriosModel getModel() {
    return null;
  }

  @Override
  public void toggleHints() {
    //not implemented
  }

  @Override
  public void accept(Consumer<Move> moveConsumer, Supplier<Model> supplier) {
    super.accept(new ProviderClickListener(listener), supplier);
    if (model == null) {
      model = supplier.get();
    }
  }

  class ProviderClickListener implements Consumer<Move> {
    private final ThreeTriosController features;

    public ProviderClickListener(ThreeTriosController features) {
      this.features = features;
    }

    @Override
    public void accept(Move move) {
      //model.numCols() + 1 & move.col + 1
      //because controller uses 0 based index with both hands included
      //therefore 0 index is player one's hand
      features.cellClicked(move.handIdx, model.numCols() + 1);
      features.cellClicked(move.row, move.col + 1);
      refresh();
    }
  }
}
