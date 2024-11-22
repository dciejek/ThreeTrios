package view;

import java.awt.Dimension;

import javax.swing.JFrame;

import controller.ThreeTriosController;
import model.PlayingCard;
import model.ReadOnlyThreeTriosModel;

/**
 * A gui based view for a game of Three Trios.
 */
public class TTGuiView extends JFrame implements ThreeTriosFrame {
  private final ReadOnlyThreeTriosModel<PlayingCard> model;
  private final TTBoardPanel panel;
  private static final int SIZE = 100;

  /**
   * Constructs a GUI based view based off the given read only model of a game of Three Trios.
   * @param model a read only model
   * @throws IllegalArgumentException if the read only model is null
   */
  public TTGuiView(ReadOnlyThreeTriosModel<PlayingCard> model) {
    if (model == null) {
      throw new IllegalArgumentException("read only model cannot be null");
    }
    this.model = model;
    panel = new TTBoardPanel(model);
    this.setTitle("Current Player: " + model.getCurrentPlayer().getColor().toString());
    this.setSize(getPreferredSize().width, getPreferredSize().height);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.add(panel);
  }

  @Override
  public void addClickListener(ThreeTriosController listener) {
    this.panel.addClickListener(listener);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension((model.getRow(0).size() + 2) * SIZE,
            (Math.max(model.getCurrentPlayer().getHand().size(),
                    model.getGrid().size())) * SIZE);
  }

}
