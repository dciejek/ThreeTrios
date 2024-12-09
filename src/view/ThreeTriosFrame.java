package view;

import java.awt.event.KeyListener;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

import controller.ThreeTriosController;
import model.Card;
import model.ReadOnlyThreeTriosModel;

/**
 * The display frame interface for a game of Three Trios. Able to be updated
 * via click events and refreshed to update the visual state.
 */
public interface ThreeTriosFrame<C extends Card> {
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

  /**
   * Gets a Read-Only model of three trios.
   */
  ReadOnlyThreeTriosModel<C> getModel();

  /**
   * Set up to key events in this view.
   * @param listener the key events to be handled
   */
  void addKeyListener(KeyListener listener);
}
