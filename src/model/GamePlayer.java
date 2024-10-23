package model;

import java.awt.*;

public class GamePlayer implements Player {
  Color color;

  GamePlayer(Color color) {
    this.color = color;
  }

  @Override
  public String toString() {
    return color.toString().substring(0, 1).toUpperCase();
  }
}
