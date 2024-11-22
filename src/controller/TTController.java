package controller;

import javax.swing.*;

import model.Player;
import model.PlayingCard;
import model.ThreeTriosModel;
import view.ThreeTriosFrame;

public class TTController implements ThreeTriosController {

  private final ThreeTriosFrame view;
  private final Player<PlayingCard> player;
  private final ThreeTriosModel<PlayingCard> model;

  public TTController(ThreeTriosModel<PlayingCard> model,
                      Player<PlayingCard> player,
                      ThreeTriosFrame view) {
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    } else if (player == null) {
      throw new IllegalArgumentException("player cannot be null");
    } else if (view == null) {
      throw new IllegalArgumentException("view cannot be null");
    }
    this.model = model;
    this.player = player;
    this.view = view;
  }

  @Override
  public void playGame() {
    //addClickListener can go in constructor or in this method
    view.addClickListener(this);
    view.makeVisible();
  }

  @Override
  public void handleCellClicked(int row, int col) {
    if (!model.getCurrentPlayer().equals(player)) {
      //JOptionPane showMessage
    }
    //check that its the players turn
    //check that card selected is from own hand
    //
    view.refresh();
  }
}
