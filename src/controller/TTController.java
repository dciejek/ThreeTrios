package controller;

import model.PlayingCard;
import model.ThreeTriosModel;
import view.ThreeTriosFrame;

public class TTController implements ThreeTriosController {

  private final ThreeTriosFrame view;
  private ThreeTriosModel<PlayingCard> model;

  public TTController(ThreeTriosFrame view) {
    if (view == null) {
      throw new IllegalArgumentException("view cannot be null");
    }
    this.view = view;
  }

  @Override
  public void playGame(ThreeTriosModel<PlayingCard> model) {
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    this.model = model;
    //addClickListener can go in constructor or in this method
    view.addClickListener(this);
    view.makeVisible();
  }

  @Override
  public void handleCellClicked(int handIndex, int row, int col) {
    model.placeCard(handIndex, row, col);
    view.refresh();
  }
}
