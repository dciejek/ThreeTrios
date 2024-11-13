package view;

import java.awt.*;
import java.awt.geom.Path2D;

import model.Card;
import model.CardValue;

public class TTCard extends Path2D.Double {
  private final Card card;
  private final int size;

  public TTCard(Card card, int size) {
    this.card = card;
    this.size = size;
  }

  public void drawCard(Graphics2D g2d, Color color, int x, int y) {
    g2d.setColor(color);
    Shape cardSquare = new Rectangle(x, y, size, size);
    g2d.fill(cardSquare);
    g2d.setColor(Color.BLACK);
    g2d.setFont(new Font("Arial", Font.BOLD, size / 4));
    g2d.drawString(CardValue.toStringValue(card.getNorth()),
            x + size / 2, y + size / 4);
    g2d.drawString(CardValue.toStringValue(card.getSouth()),
            x + size / 2, y + 3 * size / 4);
    g2d.drawString(CardValue.toStringValue(card.getEast()),
            x + 3 * size / 4, y + size / 2);
    g2d.drawString(CardValue.toStringValue(card.getWest()),
            x + size / 4, y + size / 2);
  }

}
