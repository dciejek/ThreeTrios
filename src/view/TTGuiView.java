package view;

import java.awt.*;

import javax.swing.*;

import model.PlayingCard;
import model.ReadOnlyThreeTriosModel;

/**
 * A gui based view for a game of Three Trios.
 */
public class TTGuiView extends JFrame implements ThreeTriosFrame {
  private final ReadOnlyThreeTriosModel<PlayingCard> model;
  private final TTBoardPanel panel;
  private static final int SIZE = 20;

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
    this.panel = new TTBoardPanel(model);
    this.setTitle("Current Player: " + model.getCurrentPlayer().getColor().toString());
    this.setSize((model.getGrid().size() + 2) * SIZE,
            (model.getGrid().get(0).size() + 2) * SIZE);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.add(panel);
  }

  @Override
  public void addClickListener() {
    //ignore, use in later implementation
  }

  @Override
  public void refresh() {
    //ignore, use in later implementation
  }

  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public Dimension getMinimumSize() {
    return new Dimension((model.getGrid().size() + 2) * SIZE / 2,
            (model.getGrid().get(0).size() + 2) * SIZE / 2);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension((model.getGrid().size() + 2) * SIZE,
            (model.getGrid().get(0).size() + 2) * SIZE);
  }

  @Override
  public Dimension getMaximumSize() {
    return new Dimension((model.getGrid().size() + 2) * SIZE * 2,
            (model.getGrid().get(0).size() + 2) * SIZE * 2);
  }

}
