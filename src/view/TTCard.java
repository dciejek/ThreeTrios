package view;

import java.awt.*;
import java.awt.geom.Path2D;

import model.Card;
import model.CardValue;

public class TTCard extends Path2D.Double {
  private final Card card;
  private final int size;
  private boolean selected;

  public TTCard(Card card, int size) {
    this.card = card;
    this.size = size;
    selected = false;
  }

  public void drawCard(Graphics2D g2d, Color color, int x, int y) {
    g2d.setColor(color);
    Shape cardSquare = new Rectangle(x, y, size, size);
    g2d.fill(cardSquare);
    g2d.setColor(Color.BLACK);
    g2d.setFont(new Font("Arial", Font.BOLD, size / 4));
    g2d.drawString(CardValue.toStringValue(card.getNorth()),
             x + size / (float) 2, y + size / (float) 4);
    g2d.drawString(CardValue.toStringValue(card.getSouth()),
            x + size / (float) 2, y + 3 * size / (float) 4);
    g2d.drawString(CardValue.toStringValue(card.getEast()),
            x + 3 * size / (float) 4, y + size / (float) 2);
    g2d.drawString(CardValue.toStringValue(card.getWest()),
            x + size / (float) 4, y + size / (float) 2);
    if (selected) {
      g2d.setColor(Color.GREEN);
      Shape highlight = new Rectangle(x, y, size - 5, size - 5);
      g2d.draw(highlight);
    }
  }

  public void select() {
    selected = true;
  }

  public void deselect() {
    selected = false;
  }

}
