package view;

/**
 * The display frame interface for a game of Three Trios. Able to be updated
 * via click events and refreshed to update the visual state.
 */
public interface ThreeTriosFrame {
  /**
   * Set up to handle click events in this view.
   * In this current implementation only to highlight cards,
   * later implementations can incorporate the controller's features.
   */
  void addClickListener();

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();

  /**
   * Make the view visible to start the game session.
   */
  void makeVisible();
}
