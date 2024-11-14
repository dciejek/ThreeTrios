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
import model.PlayerColor;
import model.PlayingCard;
import model.ReadOnlyThreeTriosModel;

/**
 * The panel representation for the whole game board in a game of Three Trios.
 * Composed of each player's Hand, as well as the Grid itself,
 * which can be updated via the addClickListener() method.
 */
public class TTBoardPanel extends JPanel implements ThreeTriosPanel {
  private final ReadOnlyThreeTriosModel<PlayingCard> model;
  private final int SIZE = 100;
  private final int maxHandSize;
  private Graphics2D g2d;
  private Point2D highlightedCard;

  /**
   * Constructs the game board panel, stores a read only representation of the model
   * for future in displaying the game state visually.
   * @param model a read only model
   */
  public TTBoardPanel(ReadOnlyThreeTriosModel<PlayingCard> model) {
    this.model = model;
    maxHandSize = model.getCurrentPlayer().getHand().size();
    highlightedCard = null;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;
    this.g2d = g2d;

    g2d.transform(getLogicalToPhysicalTransform());

    drawBoard(g2d);

    addClickListener();

  }

  private void drawBoard(Graphics2D g2d) {
    int posX = 0;
    int posY = 0;
    TTCard cardSquare;
    //PlayerOne Hand
    for (Card card : model.getPlayerOne().getHand()) {
      cardSquare = new TTCard(card, SIZE);
      cardSquare.drawCard(g2d, getColor(model.getPlayerOne().getColor()), posX, posY);
      posY += SIZE;
    }
    //Add first border
    g2d.setColor(Color.BLACK);
    Shape borderOne = new Rectangle(SIZE - 5, 0,
            5, Math.max(maxHandSize * SIZE, model.getGrid().get(0).size() * SIZE));
    g2d.fill(borderOne);
    posX = SIZE;
    posY = 0;
    //Grid
    for (int i = 0; i < model.getGrid().size(); i++) {
      for (Cell cell : model.getGrid().get(i)) {
        if (cell.toString().equals(" ")) {
          drawCell(g2d, cell, SIZE, posX, posY);
        } else if (cell.toString().equals("_")) {
          drawCell(g2d, cell, SIZE, posX, posY);
        } else {
          cardSquare = new TTCard(cell.getCard(), SIZE);
          cardSquare.drawCard(g2d, getColor(cell.getPlayerColor()), posX, posY);
        }
        posX += SIZE;
      }
      posX = SIZE;
      posY += SIZE;
    }
    posX = (1 + model.getGrid().size()) * SIZE;
    posY = 0;
    //PlayerTwo Hand
    for (Card card : model.getPlayerTwo().getHand()) {
      cardSquare = new TTCard(card, SIZE);
      cardSquare.drawCard(g2d, getColor(model.getPlayerTwo().getColor()), posX, posY);
      posY += SIZE;
    }
    //Add second border (after PlayerTwo hand so that it is not covered)
    g2d.setColor(Color.BLACK);
    Shape borderTwo = new Rectangle((1 + model.getGrid().size()) * SIZE, 0,
            5, Math.max(maxHandSize * SIZE, model.getGrid().get(0).size() * SIZE));
    g2d.fill(borderTwo);
    if (highlightedCard != null) {
      drawHighlightedCard((int) highlightedCard.getX(), (int) highlightedCard.getY());
    }
  }

  private void drawCell(Graphics2D g2d, Cell cell, int size, int x, int y) {
    Shape cellSquare;
    switch (cell.toString()) {
      case " ":
        g2d.setColor(Color.GRAY);
        cellSquare = new Rectangle(x, y, size, size);
        g2d.fill(cellSquare);
        break;
      case "_":
        g2d.setColor(Color.YELLOW);
        cellSquare = new Rectangle(x, y, size, size);
        g2d.fill(cellSquare);
        break;
    }
  }

  private Color getColor(PlayerColor player) {
    switch (player) {
      case BLUE:
        return Color.BLUE;
      case RED:
        return Color.RED;
      default:
        throw new IllegalArgumentException("Unsupported player color: " + player);
    }
  }

  private Dimension getLocalDimension() {
    // each row + 2 (for each hand) x  (max each column/hand size)
    return new Dimension((model.getGrid().size() + 2) * SIZE,
            (Math.max(maxHandSize,
                    model.getGrid().get(0).size())) * SIZE);
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
    transform.scale(local.getWidth() / (model.getGrid().size() + 2),
            local.getHeight() / model.getGrid().get(0).size());
    return transform;
  }

  private void drawHighlightedCard(int x, int y) {
    TTCard cardSquare = new TTCard(model.getCurrentPlayer().getHand().get(y), SIZE);
    cardSquare.select();
    cardSquare.drawCard(g2d, getColor(model.getCurrentPlayer().getColor()),
              x * SIZE, y * SIZE);
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
      try {
        AffineTransform physicalToLogical = getLogicalToPhysicalTransform();
        physicalToLogical.invert();

        AffineTransform logicalToModel = getModelToLogicalTransform();
        logicalToModel.invert();

        Point2D physicalPt = e.getPoint();
        Point2D logicalPt = physicalToLogical.transform(physicalPt, null);
        Point2D modelPt = logicalToModel.transform(logicalPt, null);

        printIndexIfGrid(modelPt);

        //Highlight card
        if (highlightInBounds(modelPt)) {
          int x = (int) modelPt.getX();
          int y = (int) modelPt.getY();

          if (highlightedCard != null) {
            if (highlightedCard.getX() == x && highlightedCard.getY() == y) {
              highlightedCard = null;
            }
          } else {
            highlightedCard = new Point(x, y);
            drawBoard(g2d);
          }

        }

        //controller on click action will go here
      } catch (NoninvertibleTransformException ex) {
        throw new RuntimeException(ex);
      }
    }

    private void printIndexIfGrid(Point2D modelPt) {
      if ((int) modelPt.getX() != 0
              && (int) modelPt.getX() != model.getGrid().get(0).size() + 1
              && (int) modelPt.getY() <= model.getGrid().size()) {
        int gridX = (int) modelPt.getX() - 1;
        int gridY = (int) modelPt.getY();
        System.out.println(gridX + ", " + gridY);
      }
    }

    private boolean highlightInBounds(Point2D modelPt) {
      int x = (int) modelPt.getX();
      int y = (int) modelPt.getY();

      if (x == 0 && model.getPlayerOne() == model.getCurrentPlayer()) {
        return y < model.getPlayerOne().getHand().size();
      } else if (x == model.getGrid().size() + 1
              && model.getPlayerTwo() == model.getCurrentPlayer()) {
        return y < model.getPlayerTwo().getHand().size();
      }
      return false;
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
