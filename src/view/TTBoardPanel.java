package view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

import javax.swing.*;

import model.Card;
import model.Cell;
import model.Player;
import model.PlayingCard;
import model.ReadOnlyThreeTriosModel;

/**
 * The panel representation for the whole game board in a game of Three Trios.
 * Composed of each player's Hand, as well as the Grid itself,
 * which can be updated via the addClickListener() method.
 */
public class TTBoardPanel extends JPanel implements ThreeTriosPanel {
  ReadOnlyThreeTriosModel<PlayingCard> model;
  private static final int SIZE = 20;

  /**
   * Constructs the game board panel, stores a read only representation of the model
   * for future in displaying the game state visually.
   * @param model a read only model
   */
  public TTBoardPanel(ReadOnlyThreeTriosModel<PlayingCard> model) {
    this.model = model;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    g2d.transform(getLogicalToPhysicalTransform());

    drawBoard(g2d);

  }

  private void drawBoard(Graphics2D g2d) {
    int posX = 0;
    int posY = 0;
    g2d.setColor(Color.RED);
    for (Card card : model.getCurrentPlayer().getHand()) {
      Shape cardSquare = new Rectangle(posX, posY, SIZE, SIZE);
      g2d.fill(cardSquare);
      g2d.drawString(card.getNorth(), );
      posY += SIZE;
    }
    posX += SIZE;
    posY = 0;
    for (int i = 0; i < model.getGrid().size(); i++) {
      for (Cell cell : model.getGrid().get(i)) {

      }
    }
    //Draw Grid
    for (Card card : model.getCurrentPlayer().getHand()) {

    }
    //Draw blue hand
  }

  private Dimension getLocalDimension() {
    //10 * each row + 2 (for each hand) x 10 * each column,
    return new Dimension(model.getGrid().size() + 2 * SIZE,
            model.getGrid().get(0).size() * SIZE);
  }

  private AffineTransform getLogicalToPhysicalTransform() {
    AffineTransform transform = new AffineTransform();
    Dimension local = getLocalDimension();
    transform.scale(this.getWidth() / local.getWidth(),
            this.getHeight() / local.getHeight());
    return transform;
  }

  private AffineTransform getModelToLogicalTransform() {
    AffineTransform transform = new AffineTransform();
    Dimension local = getLocalDimension();
    transform.scale(local.getWidth() / model.getGrid().size() + 2,
            local.getHeight() / model.getGrid().get(0).size());
    return transform;
  }

  /**
   * Set up the view to handle click events (highlight cards), later implementations will
   * allow the controller to handle click events.
   */
  public void addClickListener() {
    this.addMouseListener(new TTClickListener());
  }

  class TTClickListener implements MouseListener {

    /**
     * Empty constructor, adjustable in later implementation to input a controller
     * so its features can be applied.
     */
    public TTClickListener() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      try{
        AffineTransform physicalToLogical = getLogicalToPhysicalTransform();
        physicalToLogical.invert();

        AffineTransform logicalToModel = getModelToLogicalTransform();
        logicalToModel.invert();

        System.err.println(e.getX() + ", " + e.getY());
        Point2D evtPt = e.getPoint();
        Point2D modelPt = physicalToLogical.transform(evtPt, null);

        logicalToModel.transform(modelPt, modelPt);
        System.err.println(e.getX() + ", " + e.getY());

        //Highlight card

        //controller on click action will go here
      } catch (NoninvertibleTransformException ex) {
        throw new RuntimeException(ex);
      }
    }

    @Override
    public void mousePressed(MouseEvent e) {
      //ignore
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      //ignore
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      //ignore
    }

    @Override
    public void mouseExited(MouseEvent e) {
      //ignore
    }
  }
}
