package view;

import java.awt.geom.Point2D;

import javax.swing.JPanel;

import controller.ThreeTriosController;

/**
 * The display frame interface for a game of Three Trios. Able to be updated
 * via click events and refreshed to update the visual state.
 */
public interface ThreeTriosFrame {
  /**
   * Set up to handle click events in this view.
   * @param listener the controller
   */
  void addClickListener(ThreeTriosController listener);

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();

  /**
   * Make the view visible to start the game session.
   */
  void makeVisible();

  /**
   * Gets the highlighted card from the panel associated with the view.
   */
  Point2D getHighlightedCard();

  /**
   * Sets the highlighted card from the panel that is associated with the view.
   */
  void setHighlightedCard(Point2D point);

  /**
   * Gets the panel component that makes up the view.
   */
  JPanel getPanel();
}
