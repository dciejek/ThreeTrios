package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public interface PlayerAction extends ActionListener {

  /**
   * Emulates the given action from the player through the controller.
   * @param e the event to be processed
   */
  void actionPerformed(ActionEvent e);
}
