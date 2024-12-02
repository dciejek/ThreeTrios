package provider.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * A Read only interface for a three trios model. the objects that inheritors return to
 * fulfill
 * these requirements need not be the exact reference. they only need to have the same
 * observations as the inheritor's private objects make at that point in the game
 * timeline. `
 *
 * <p>Example:
 *
 * <p>State 1 : Model is started
 *
 * <p>Grid g1 = model.getGrid()
 *
 * <p>State 2: Model has 5 moves played on it
 *
 * <p>Grid g2 = model.getGrid()
 *
 * <p>g1 != g2
 *
 * <p>g1.(some observation) =? g2.(some observation)   -- indeterminate
 */
public interface ModelReadOnly {

  /**
   * Gets the current coach whose turn it is.
   * @return the current coach
   */
  CoachColor curCoach();

  /**
   * Gets the hands of all coaches in the game.
   * @return a map of coaches to their respective hands
   */
  Map<CoachColor, List<Card>> curCoachesHands();

  /**
   * Checks if the game has started.
   * @return true if the game has started, false otherwise
   */
  boolean isGameStarted();

  /**
   * Checks if the game is over.
   * @return true if the game is over, false otherwise
   */
  boolean isGameOver();

  /**
   * Gets the winner of the game.
   * @return the winning coach, or null if it's a tie
   */
  CoachColor winner();

  /**
   * Returns the current grid state.
   * @return the grid
   */
  Grid curGrid();

  /**
   * Gets the number of rows in the grid.
   * @return the number of rows
   */
  int numRows();

  /**
   * Gets the number of columns in the grid.
   * @return the number of columns
   */
  int numCols();

  /**
   * Gets the card at the specified position on the grid, if one exists.
   * @param row the row index
   * @param col the column index
   * @return an Optional containing the card, or empty if no card is present
   */
  Optional<model.Card> cardAt(int row, int col);

  /**
   * Gets the coach who owns the card at the specified position, if one exists.
   * @param row the row index
   * @param col the column index
   * @return an Optional containing the coach, or empty if no card is present
   */
  Optional<CoachColor> ownerAt(int row, int col);

  /**
   * Determines if a card can be placed at the specified position on the grid.
   * @param row the row index
   * @param col the column index
   * @return true if a card can be placed, false otherwise
   */
  boolean canPlayAt(int row, int col);

  /**
   * Calculates the number of opponent cards that would be flipped if a card were placed
   * at the specified position.
   * @param card the card to be placed
   * @param row  the row index
   * @param col  the column index
   * @return the number of opponent cards that would be flipped
   */
  int numFlippedIfPlaced(Card card, int row, int col);

  /**
   * Gets the current score of the specified coach.
   * @param coach the coach whose score is to be calculated
   * @return the score of the coach
   */
  int score(CoachColor coach);

}
