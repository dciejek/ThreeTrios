package model.adapter;

import model.PlayerColor;
import provider.model.CoachColor;

/**
 * A class housing two static conversion methods that allow for respective
 * CoachColor <-> PlayerColor enumeration conversions.
 */
public class CoachColorAdapter {

  /**
   * Converts to provider coach color from player color.
   * @param playerColor our player's color
   * @return respective coach color
   */
  public static CoachColor playerColorToCoachColor(PlayerColor playerColor) {
    switch (playerColor) {
      case BLUE:
        return CoachColor.BLUE;
      case RED:
        return CoachColor.RED;
      default:
        throw new IllegalArgumentException("Invalid player color");
    }
  }

  /**
   * Converts to our player color from provider coach color.
   * @param coachColor provider coach color
   * @return respective player color
   */
  public static PlayerColor coachColorToPlayerColor(CoachColor coachColor) {
    switch (coachColor) {
      case BLUE:
        return PlayerColor.BLUE;
      case RED:
        return PlayerColor.RED;
      default:
        throw new IllegalArgumentException("Invalid coach color");
    }
  }
}
