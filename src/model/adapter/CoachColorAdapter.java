package model.adapter;

import model.PlayerColor;
import provider.model.CoachColor;

public class CoachColorAdapter {

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
