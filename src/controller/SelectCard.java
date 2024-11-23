package controller;

import java.awt.event.ActionEvent;

public class SelectCard implements PlayerAction {

  private final ThreeTriosController features;

  public SelectCard(ThreeTriosController features) {
    this.features = features;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
   // features.handleCellClicked();
  }
}
