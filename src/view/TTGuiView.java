package view;

import java.awt.*;
import java.awt.geom.Point2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.ThreeTriosController;
import model.Card;
import model.ReadOnlyThreeTriosModel;
import view.decorator.DisplayHints;

/**
 * A gui based view for a game of Three Trios.
 */
public class TTGuiView extends JFrame implements ThreeTriosFrame<Card> {
  private final ReadOnlyThreeTriosModel<Card> model;
  private final TTBoardPanel<Card> panel;
  private DisplayHints hints;
  private static final int SIZE = Utils.SIZE;
  private boolean hintsEnabled;

  /**
   * Constructs a GUI based view based off the given read only model of a game of Three Trios.
   * @param model a read only model
   * @throws IllegalArgumentException if the read only model is null
   */
  public TTGuiView(ReadOnlyThreeTriosModel<Card> model) {
    if (model == null) {
      throw new IllegalArgumentException("read only model cannot be null");
    }
    this.model = model;
    panel = new TTBoardPanel<>(model);
    this.setTitle("Current Player: " + model.getCurrentPlayer().getColor().toString());
    this.setSize(getPreferredSize().width, getPreferredSize().height);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.add(panel);
    hintsEnabled = false;
  }

  @Override
  public void addClickListener(ThreeTriosController listener) {
    this.panel.addClickListener(listener);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public Point2D getHighlightedCard() {
    return panel.getHighlightedCard();
  }

  @Override
  public void setHighlightedCard(Point2D point) {
    panel.setHighlightedCard(point);
  }

  @Override
  public JPanel getPanel() {
    return panel;
  }

  @Override
  public ReadOnlyThreeTriosModel<Card> getModel() {
    return model;
  }

  @Override
  public void toggleHints() {
    if (!hintsEnabled) {
      hints = new DisplayHints(panel, model, panel.getGraphics());
      this.add(hints);
      hintsEnabled = true;
    } else {
      this.remove(hints);
      refresh();
      hintsEnabled = false;
    }
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension((model.getRow(0).size() + 2) * SIZE,
            (Math.max(model.getCurrentPlayer().getHand().size(),
                    model.getGrid().size())) * SIZE);
  }
}
