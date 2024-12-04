package view.adapter;

import java.awt.geom.Point2D;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.swing.*;

import controller.ThreeTriosController;
import provider.model.GamePlayer;
import provider.model.Model;
import provider.model.Move;
import provider.view.GUIGridInteractive;
import provider.view.GUIHandBase;
import provider.view.GUIHandInteractive;
import provider.view.GUIPlayerInteractive;
import view.ThreeTriosFrame;

public class GameViewAdapter extends GUIPlayerInteractive implements ThreeTriosFrame {
  private Model model;

  public GameViewAdapter(GUIHandBase redHand, GUIHandInteractive blueHand,
                         GUIGridInteractive grid) {
    super(redHand, blueHand, grid);
    model = null;
  }

  @Override
  public void addClickListener(ThreeTriosController listener) {

  }

  @Override
  public void refresh() {
    renderModel(model);
  }

  @Override
  public void makeVisible() {

  }

  @Override
  public Point2D getHighlightedCard() {
    return null;
  }

  @Override
  public void setHighlightedCard(Point2D point) {

  }

  @Override
  public JPanel getPanel() {
    return null;
  }

  @Override
  public void accept(Consumer<Move> moveConsumer, Supplier<Model> supplier) {
    super.accept(moveConsumer, supplier);
    if (model == null) {
      model = supplier.get();
    }
  }
}
