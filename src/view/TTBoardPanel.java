package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

import controller.ThreeTriosController;
import model.Card;
import model.Cell;
import model.Player;
import model.PlayerColor;
import model.ReadOnlyThreeTriosModel;

/**
 * The panel representation for the whole game board in a game of Three Trios.
 * Composed of each player's Hand, as well as the Grid itself,
 * which can be updated via the addClickListener() method.
 */
public class TTBoardPanel<C extends Card> extends JPanel implements ThreeTriosPanel {
  private final ReadOnlyThreeTriosModel<C> model;
  private final int SIZE = Utils.SIZE;
  private final int maxHandSize;
  private Point2D highlightedCard;

  /**
   * Constructs the game board panel, stores a read only representation of the model
   * for future in displaying the game state visually.
   * @param model a read only model
   */
  public TTBoardPanel(ReadOnlyThreeTriosModel<C> model) {
    this.model = model;
    maxHandSize = model.getPlayerOne().getHand().size();
    highlightedCard = null;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    g2d.transform(getLogicalToPhysicalTransform());

    drawBoard(g2d);
  }

  @Override
  public void drawBoard(Graphics2D g2d) {
    int posX = 0;
    int posY = 0;
    TTCard cardSquare;
    //PlayerOne Hand
    drawHand(g2d, model.getPlayerOne(), posX, posY);
    //Add first border
    g2d.setColor(Color.BLACK);
    Shape borderOne = new Rectangle(SIZE - 5, 0,
            5, Math.max(maxHandSize * SIZE, model.getGrid().size() * SIZE));
    g2d.fill(borderOne);
    posX = SIZE;
    posY = 0;
    //Grid
    for (int i = 0; i < model.getGrid().size(); i++) {
      for (Cell cell : model.getRow(i)) {
        if (cell.toString().equals(" ") || cell.toString().equals("_")) {
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
    posX = (1 + model.getRow(0).size()) * SIZE;
    posY = 0;
    //PlayerTwo Hand
    drawHand(g2d, model.getPlayerTwo(), posX, posY);
    //Add second border (after PlayerTwo hand so that it is not covered)
    g2d.setColor(Color.BLACK);
    Shape borderTwo = new Rectangle((1 + model.getRow(0).size()) * SIZE, 0,
            5, Math.max(maxHandSize * SIZE, model.getGrid().size() * SIZE));
    g2d.fill(borderTwo);
    if (highlightedCard != null) {
      drawHighlightedCard(g2d, (int) highlightedCard.getX(), (int) highlightedCard.getY());
    }
  }

  private void drawHand(Graphics2D g2d, Player<C> player, int x, int y) {
    int posY = y;
    TTCard cardSquare;
    for (Card card : player.getHand()) {
      cardSquare = new TTCard(card, SIZE);
      cardSquare.drawCard(g2d, getColor(player.getColor()), x, posY);
      posY += SIZE;
    }
  }

  private void drawCell(Graphics2D g2d, Cell cell, int size, int x, int y) {
    Shape cellSquare;
    if (cell.toString().equals(" ")) {
      g2d.setColor(Color.GRAY);
      cellSquare = new Rectangle(x, y, size, size);
      g2d.fill(cellSquare);
    } else if (cell.toString().equals("_")) {
      g2d.setColor(Color.YELLOW);
      cellSquare = new Rectangle(x, y, size, size);
      g2d.fill(cellSquare);
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
    return new Dimension((model.getRow(0).size() + 2) * SIZE,
            (Math.max(maxHandSize,
                    model.getGrid().size())) * SIZE);
  }

  @Override
  public AffineTransform getLogicalToPhysicalTransform() {
    AffineTransform transform = new AffineTransform();
    Dimension local = getLocalDimension();
    transform.scale(this.getWidth() / local.getWidth(),
            this.getHeight() / local.getHeight());
    return transform;
  }

  @Override
  public JPanel getPanel() {
    return this;
  }

  private AffineTransform getModelToLogicalTransform() {
    AffineTransform transform = new AffineTransform();
    Dimension local = getLocalDimension();
    transform.scale(local.getWidth() / (model.getRow(0).size() + 2),
            local.getHeight() / (Math.max(maxHandSize, model.getGrid().size())));
    return transform;
  }

  @Override
  public void setHighlightedCard(Point2D point) {
    if (point == null) {
      highlightedCard = null;
      return;
    }
    int x = (int) point.getX();
    int y = (int) point.getY();
    highlightCard(x, y);
  }

  private void highlightCard(int handIndex, int cardIndex) {
    if (highlightedCard != null && cardIndex == highlightedCard.getY()) {
      highlightedCard = null;
    } else {
      highlightedCard = new Point2D.Double(handIndex, cardIndex);
    }
  }

  @Override
  public Point2D getHighlightedCard() {
    if (highlightedCard == null) {
      return null;
    } else {
      return new Point2D.Double(highlightedCard.getX(), highlightedCard.getY());
    }
  }

  private void drawHighlightedCard(Graphics2D g2d, int x, int y) {
    TTCard cardSquare = new TTCard(model.getCurrentPlayer().getHand().get(y), SIZE);
    cardSquare.select();
    cardSquare.drawCard(g2d, getColor(model.getCurrentPlayer().getColor()),
            x * SIZE, y * SIZE);
  }

  /**
   * Adds a click listener for the controller to handle click events.
   */
  public void addClickListener(ThreeTriosController listener) {
    this.addMouseListener(new TTClickListener(listener));
  }

  /**
   * A click listener for a three trios game. Uses the controller to handle events.
   */
  class TTClickListener implements MouseListener {
    ThreeTriosController features;

    /**
     * Constructor takes in a controller so that its features can handle
     * specified click events.
     */
    public TTClickListener(ThreeTriosController features) {
      this.features = features;
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
        //Rounds to int values so index's are valid
        int x = (int) modelPt.getX();
        int y = (int) modelPt.getY();
        modelPt.setLocation(x, y);
        printIndexIfGrid(modelPt);

        features.cellClicked(y, x);
      } catch (NoninvertibleTransformException ex) {
        throw new RuntimeException(ex);
      }
    }

    private void printIndexIfGrid(Point2D modelPt) {
      if ((int) modelPt.getX() != 0
              && (int) modelPt.getX() != model.getGrid().get(0).size() + 1
              && (int) modelPt.getY() < model.getGrid().size()) {
        int gridX = (int) modelPt.getX() - 1;
        int gridY = (int) modelPt.getY();
        System.out.println(gridX + ", " + gridY);
      } else {
        if ((int) modelPt.getX() == 0) {
          System.out.println(
                  model.getPlayerOne().getColor().toString() + " " + (int) modelPt.getY());
        } else if ((int) modelPt.getX() == model.getGrid().size() + 1) {
          System.out.println(
                  model.getPlayerTwo().getColor().toString() + " " + (int) modelPt.getY());
        }
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
