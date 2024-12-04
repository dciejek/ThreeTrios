package view.adapter;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.swing.*;

import controller.ThreeTriosController;
import provider.model.Model;
import provider.model.Move;
import provider.view.GUIGridInteractive;
import provider.view.GUIHandBase;
import provider.view.GUIHandInteractive;
import provider.view.GUIPlayerInteractive;
import view.ThreeTriosFrame;

public class GameViewAdapter extends GUIPlayerInteractive implements ThreeTriosFrame {
  private Model model;
  private Point2D highlightedCard;
  protected Move move;
  private ThreeTriosController listener;

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
      features.handleCellClicked(move.handIdx, model.numCols() + 1);
      features.handleCellClicked(move.row, move.col + 1);
      refresh();
    }
  }
}
