package controller;

/**
 * Tracks the status of the model. Specifically if a new turn occurs.
 */
public interface ModelStatus {

  /**
   * When a new turns will notify a controller listener, which updates the view through
   * its features.
   */
  void newTurn();
}
