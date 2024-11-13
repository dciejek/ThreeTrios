package view;

import java.awt.*;

import javax.swing.*;

import model.Player;
import model.PlayingCard;

public class TTHandPanel extends JPanel implements ThreeTriosPanel {
  private final Player<PlayingCard> player;
  private static final int SIZE = 20;

  public TTHandPanel(Player<PlayingCard> player) {
    this.player = player;
  }

  @Override
  public void paintComponent(Graphics g) {

  }

  private void drawCard(Graphics2D g2d) {

  }

  @Override
  public Dimension getMinimumSize() {
    return new Dimension(SIZE / 2, player.getHand().size() * SIZE / 2);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(SIZE, player.getHand().size() * SIZE);
  }

  @Override
  public Dimension getMaximumSize() {
    return new Dimension(SIZE * 2, player.getHand().size() * SIZE * 2);
  }

}
