package view;

import java.awt.geom.Point2D;

/**
 * A panel displayed in the GUI view of a game of three trios. Able to update the highlighted card.
 */
public interface ThreeTriosPanel {
  /**
   * Updates the highlighted card based on a given 2D point.
   * @throws IllegalArgumentException if the point is null.
   */
  void setHighlightedCard(Point2D point);

  /**
   * Returns a copy of the 2D point representing the highlighted card.
   * Using a board based (includes both hands) 0 based index position.
   * Returns null if no card is highlighted.
   */
  Point2D getHighlightedCard();
}
