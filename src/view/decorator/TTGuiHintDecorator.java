package view.decorator;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;

import javax.swing.*;

import controller.ThreeTriosController;
import model.Card;
import model.ReadOnlyThreeTriosModel;
import view.ThreeTriosFrame;
import view.ThreeTriosPanel;

public class TTGuiHintDecorator extends JFrame implements ThreeTriosFrame<Card> {

  private final ThreeTriosFrame<Card> frame;
  private final ThreeTriosPanel hints;

  public TTGuiHintDecorator(ThreeTriosFrame<Card> frame) {
    this.frame = frame;
    hints = new DisplayHints((ThreeTriosPanel) frame.getPanel(),
            getModel());
    this.add(hints.getPanel());
    revalidate();
    this.setComponentZOrder(hints.getPanel(), 1);
  }

  @Override
  public void addClickListener(ThreeTriosController listener) {
    frame.addClickListener(listener);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void makeVisible() {
    frame.makeVisible();
  }

  @Override
  public Point2D getHighlightedCard() {
    return frame.getHighlightedCard();
  }

  @Override
  public void setHighlightedCard(Point2D point) {
    frame.setHighlightedCard(point);
  }

  @Override
  public JPanel getPanel() {
    return frame.getPanel();
  }

  @Override
  public ReadOnlyThreeTriosModel<Card> getModel() {
    return frame.getModel();
  }

  @Override
  public void addKeyListener(KeyListener listener) {
    frame.addKeyListener(listener);
  }
}
