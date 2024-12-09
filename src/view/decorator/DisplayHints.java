package view.decorator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

import controller.ThreeTriosController;
import model.Card;
import model.ReadOnlyThreeTriosModel;
import view.ThreeTriosPanel;
import view.Utils;

/**
 * A decorator panel that displays GUI hints on the grid above the current state of the game.
 */
public class DisplayHints extends JPanel implements ThreeTriosPanel {
  private final ThreeTriosPanel panel;
  private final ReadOnlyThreeTriosModel<Card> model;

  /**
   * Constructs the decorator panel for hints.
   * @param panel the base panel to build from
   * @param model the state of the model
   */
  public DisplayHints(ThreeTriosPanel panel, ReadOnlyThreeTriosModel<Card> model) {
    this.panel = panel;
    this.model = model;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    g2d.transform(getLogicalToPhysicalTransform());

    drawBoard(g2d);
  }

  @Override
  public void setHighlightedCard(Point2D point) {
    panel.setHighlightedCard(point);
  }

  @Override
  public Point2D getHighlightedCard() {
    return panel.getHighlightedCard();
  }

  @Override
  public void drawBoard(Graphics2D g2d) {
    panel.drawBoard(g2d);
    if (getHighlightedCard() != null) {
      int gridX = Utils.SIZE;
      int gridY = 0;
      for (int i = 0; i < model.getGrid().size(); i++) {
        for (int j = 0; j < model.getRow(i).size(); j++) {
          if (model.getCellAt(i, j).toString().equals(" ") ||
                  model.getCellAt(i, j).toString().equals("_")) {
            drawHint(g2d, i, j, Utils.SIZE, gridX, gridY);
          }
          gridX += Utils.SIZE;
        }
        gridX = Utils.SIZE;
        gridY += Utils.SIZE;
      }
    }
  }

  private void drawHint(Graphics2D g2d, int row, int col, int size, int x, int y) {
    if (model.getCellAt(row, col).toString().equals("_")) {
      g2d.setColor(Color.BLACK);
      g2d.setFont(new Font("Arial", Font.BOLD, size / 8));
      g2d.drawString(
              Integer.toString(model.numFlipped(cardHighlightedCardFromPlayer(), row, col)),
              x + (size / 8), y - 5 + size);
    }
  }

  private Card cardHighlightedCardFromPlayer() {
    if (getHighlightedCard().getX() == 0) {
      return model.getPlayerOne().getHand().get((int) getHighlightedCard().getY());
    } else if (getHighlightedCard().getX() == model.getRow(0).size() + 1) {
      return model.getPlayerTwo().getHand().get((int) getHighlightedCard().getY());
    } else {
      throw new IllegalArgumentException("Point is not in a player's hand");
    }
  }

  @Override
  public AffineTransform getLogicalToPhysicalTransform() {
    return panel.getLogicalToPhysicalTransform();
  }

  @Override
  public JPanel getPanel() {
    return panel.getPanel();
  }

  @Override
  public void addClickListener(ThreeTriosController listener) {
    panel.addClickListener(listener);
  }
}
