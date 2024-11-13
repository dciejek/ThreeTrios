package view;

import java.awt.geom.Path2D;

import model.PlayingCard;

public class TTCard extends Path2D.Double {
  private final PlayingCard card;
  private final int size;

  public TTCard(PlayingCard card, int size) {
    this.card = card;
    this.size = size;
  }



}
