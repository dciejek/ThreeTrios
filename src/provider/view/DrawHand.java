package provider.view;


import provider.model.Card;
import provider.model.CardinalDirection;
import provider.model.CoachColor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * To draw a hand of cards onto a buffered image and provide information about where
 * this class would have drawn a specific card or what card specifically does some
 * pixel point correspond to.
 */
public class DrawHand {

  private static final Color VISIBLE_RED = new Color(246, 150, 151);
  private static final Color VISIBLE_BLUE = new Color(173, 216, 230);
  private static final Color VISIBLE_TEXT = Color.BLACK;
  private static final Font VISIBLE_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 10);
  private static final Color VISIBLE_BORDER = Color.BLACK;

  /**
   * Renders the representation of a hand of cards onto handImage.
   * @param hand      the hand of cards
   * @param handImage the render context
   */
  public void render(List<Card> hand, BufferedImage handImage) {
    if (hand.isEmpty()) {
      return;
    } else {
      Graphics artist = handImage.createGraphics();
      artist.setColor(Color.WHITE);
      for (Card card : hand) {
        renderCard(card, artist, cardWidth(handImage), cardHeight(hand, handImage));
        artist.translate(0, cardHeight(hand, handImage));
      }
    }

  }

  /**
   * To render the card to the artist's context and translate the artist after.
   * @param card       the card to render
   * @param artist     the artist who manages rendering to correct context
   * @param cardWidth  the width of the rendered card
   * @param cardHeight the height of the rendered card
   */
  protected void renderCard(Card card, Graphics artist, int cardWidth, int cardHeight) {
    setColor(artist, card.getCoach());
    artist.fillRect(0, 0, cardWidth, cardHeight);
    artist.setColor(VISIBLE_BORDER);
    artist.drawRect(0, 0, cardWidth, cardHeight);
    Point north = new Point(7 * cardWidth / 16, cardHeight / 4);
    Point south = new Point(7 * cardWidth / 16, 7 * cardHeight / 8);
    Point east = new Point(cardWidth / 16, cardHeight / 2);
    Point west = new Point(7 * cardWidth / 8, cardHeight / 2);
    artist.setColor(VISIBLE_TEXT);
    artist.setFont(VISIBLE_FONT);
    artist.drawString(card.getAttackValue(CardinalDirection.NORTH).toString(), north.x,
                      north.y);
    artist.drawString(card.getAttackValue(CardinalDirection.SOUTH).toString(), south.x,
                      south.y);
    artist.drawString(card.getAttackValue(CardinalDirection.EAST).toString(), east.x,
                      east.y);
    artist.drawString(card.getAttackValue(CardinalDirection.WEST).toString(), west.x,
                      west.y);
  }

  /**
   * To produce the width each card should have in pixels from an image context.
   * @param handImage the image context
   * @return the width each card should have in pixels
   */
  private int cardWidth(BufferedImage handImage) {
    return handImage.getWidth();
  }

  /**
   * To produce the height each card should have in pixels from an image context.
   * @param handImage the image context
   * @return the height each card should have in pixels
   */
  private int cardHeight(List<Card> hand, BufferedImage handImage) {
    return handImage.getHeight() / hand.size();
  }

  /**
   * To update the color of artist to match the visual representation of the Coach's
   * color.
   * @param artist the artist to update
   * @param coach  the coach to match
   */
  private void setColor(Graphics artist, CoachColor coach) {
    artist.setColor(coach == CoachColor.RED ? VISIBLE_RED : VISIBLE_BLUE);
  }

  /**
   * The index of a hand the card a pixel on an image would refer to.
   * @param point the point that is considered
   * @param hand the hand to add to the context of the point
   * @param handImage the image to add to the context of the point
   * @return the index in the hand the point corresponds to
   */
  public int idxOfHandAt(Point point, List<Card> hand, BufferedImage handImage) {
    return point.y / cardHeight(hand, handImage);
  }

  /**
   * The bounding box that an index in a given hand would correspond to on an image.
   *
   * @param idx the index of the hand to consider
   * @param hand the hand to add to context
   * @param handImage the image to add to context
   * @return the bounding box on the image the card at idx lies
   */
  public Rectangle idxToBoundingBox(int idx, List<Card> hand, BufferedImage handImage) {
    int left = 0;
    int top = idx * cardHeight(hand, handImage);
    int width = handImage.getWidth();
    int height = handImage.getHeight() / hand.size();
    return new Rectangle(left, top, width, height);
  }


}
