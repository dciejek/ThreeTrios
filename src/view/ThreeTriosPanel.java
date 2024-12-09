package view;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

import controller.ThreeTriosController;


/**
 * A panel displayed in the GUI view of a game of three trios. Able to update the highlighted card.
 */
public interface ThreeTriosPanel {
  /**
   * Updates the highlighted card based on a given 2D point. 
   * Can be null to signify no highlighted card.
   * @param point the 0 based coordinate for the highlighted card (includes both hands in index)
   */
  void setHighlightedCard(Point2D point);

  /**
   * Returns a copy of the 2D point representing the highlighted card.
   * Using a board based (includes both hands) 0 based index position.
   * Returns null if no card is highlighted.
   */
  Point2D getHighlightedCard();

  /**
   * Draws the entirety of the board of a game of three trios, both players hands,
   * and the state of the grid.
   * @param g2d the graphics
   */
  void drawBoard(Graphics2D g2d);

  /**
   * Translates from the dimensions of the panel to the physical dimensions displayed
   * on the user's screen.
   * @return panel -> user screen transform
   */
  AffineTransform getLogicalToPhysicalTransform();

  /**
   * Returns this panel implementation.
   * @return this panel
   */
  JPanel getPanel();

  /**
   * Adds a click listener for the controller to handle click events.
   * @param listener the controller to listen
   */
  void addClickListener(ThreeTriosController listener);
}
