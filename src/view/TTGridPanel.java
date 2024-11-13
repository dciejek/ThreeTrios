package view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import model.Cell;
import model.PlayingCard;

public class TTGridPanel extends JPanel implements ThreeTriosPanel {
  private final List<List<Cell>> grid;
  private static final int SIZE = 20;

  public TTGridPanel(List<List<Cell>> grid) {
    this.grid = grid;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;


  }

  private void drawCell(Graphics2D g2d) {

  }

  @Override
  public Dimension getMinimumSize() {
    return new Dimension(grid.size() * SIZE / 2, grid.get(0).size() * SIZE / 2);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(grid.size() * SIZE, grid.get(0).size() * SIZE);
  }

  @Override
  public Dimension getMaximumSize() {
    return new Dimension(grid.size() * SIZE * 2, grid.get(0).size() * SIZE * 2);
  }

}
