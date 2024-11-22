package view;

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
}
