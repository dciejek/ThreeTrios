package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Path2D;

import model.Card;
import model.CardValue;

/**
 * A swing based GUI for a given card in a game of three trios. The card can be highlighted.
 */
public class TTCard extends Path2D.Double {
  private final Card card;
  private final int size;
  private boolean selected;

  /**
   * The constructor for a GUI representation of a card.
   * @param card the card to be displayed
   * @param size the logical size of the display
   */
  public TTCard(Card card, int size) {
    this.card = card;
    this.size = size;
    selected = false;
  }

  /**
   * Creates the visual representation of the card.
   * @param g2d the 2D graphic tool used
   * @param color the color of the card
   * @param x the logical x position
   * @param y the logical y position
   */
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

  /**
   * Highlights a card.
   */
  public void select() {
    selected = true;
  }

  /**
   * DeHighlights a card.
   */
  public void deselect() {
    selected = false;
  }

}
