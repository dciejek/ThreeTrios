package model.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import controller.ThreeTriosController;
import model.Card;
import model.Cell;
import model.Player;
import model.PlayingCard;
import model.TTPlayer;
import model.ThreeTriosModel;
import provider.model.CardinalDirection;
import provider.model.CoachColor;
import provider.model.Grid;
import provider.model.Model;
import provider.model.Referee;

/**
 * Two-way adapter that works for both views, since our view and the provider's view are
 * hooked up to the same model.
 */
public class ModelAdapter implements Model, ThreeTriosModel<Card> {
  private final ThreeTriosModel<Card> model;
  private GridAdapter grid;

  public ModelAdapter(ThreeTriosModel<Card> model) {
    this.model = model;
  }

  @Override
  public void startGame(Grid grid, List<provider.model.Card> cards, Referee referee) {
    this.grid = new GridAdapter(grid);
    List<Card> deck = new ArrayList<>();
    int count = 0;
    for (provider.model.Card card : cards) {
      if (count % 2 == 0) {
        deck.add(new CardAdapter(card, model.getPlayerOne().getColor()));
      } else {
        deck.add(new CardAdapter(card, model.getPlayerTwo().getColor()));
      }
      count++;
    }
    model.startGame(this.grid, deck, grid.numRows(), grid.numCols());
  }

  @Override
  public void startGame(List grid, List cards, int rows, int cols) {
    model.startGame(grid, cards, rows, cols);
  }

  @Override
  public void placeCard(int idx, int row, int col) {
    model.placeCard(idx, row, col);
  }

  @Override
  public CoachColor curCoach() {
    return CoachColorAdapter.playerColorToCoachColor(model.getCurrentPlayer().getColor());
  }

  @Override
  public Map<CoachColor, List<provider.model.Card>> curCoachesHands() {
    CoachColor coachColorOne = CoachColorAdapter.playerColorToCoachColor(
            model.getPlayerOne().getColor());
    CoachColor coachColorTwo = CoachColorAdapter.playerColorToCoachColor(
            model.getPlayerTwo().getColor());
    Map<CoachColor, List<provider.model.Card>> ret =
            new HashMap<CoachColor, List<provider.model.Card>>();
    List<provider.model.Card> tempHand = new ArrayList<>();

    for (Card c : model.getPlayerOne().getHand()) {
      tempHand.add(new CardAdapter(c, model.getPlayerOne().getColor()));
    }
    ret.put(coachColorOne, new ArrayList<provider.model.Card>(tempHand));
    tempHand.clear();

    for (Card c : model.getPlayerTwo().getHand()) {
      tempHand.add(new CardAdapter(c, model.getPlayerTwo().getColor()));
    }
    ret.put(coachColorTwo, new ArrayList<provider.model.Card>(tempHand));

    return ret;
  }

  @Override
  public boolean isGameStarted() {
    return this.model.isGameStarted();
  }

  @Override
  public boolean isGameOver() {
    return this.model.isGameOver();
  }

  @Override
  public CoachColor winner() {
    return CoachColorAdapter.playerColorToCoachColor(this.model.getWinner().getColor());
  }

  @Override
  public Grid curGrid() {
    return this.grid;
  }

  @Override
  public int numRows() {
    return this.model.getGrid().size();
  }

  @Override
  public int numCols() {
    return this.model.getRow(0).size();
  }

  @Override
  public Optional<provider.model.Card> cardAt(int row, int col) {
    try {
      return Optional.of(new CardAdapter(model.getCellAt(row, col).getCard(),
              model.getCellAt(row, col).getPlayerColor()));
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public Optional<CoachColor> ownerAt(int row, int col) {
    try {
      return Optional.of(CoachColorAdapter.playerColorToCoachColor(
              model.getCellAt(row, col).getPlayerColor()));
    } catch (IllegalArgumentException e) {
      return Optional.empty();
    }
  }

  @Override
  public boolean canPlayAt(int row, int col) {
    return model.getCellAt(row, col).canPlayHere();
  }

  @Override
  public int numFlippedIfPlaced(provider.model.Card card, int row, int col) {
    return model.numFlipped(new PlayingCard("",
            AttackValueAdapter.attackValueToCardValue(card.getAttackValue(CardinalDirection.NORTH)),
            AttackValueAdapter.attackValueToCardValue(card.getAttackValue(CardinalDirection.SOUTH)),
            AttackValueAdapter.attackValueToCardValue(card.getAttackValue(CardinalDirection.EAST)),
            AttackValueAdapter.attackValueToCardValue(card.getAttackValue(CardinalDirection.WEST))),
    row, col);
  }

  @Override
  public int score(CoachColor coach) {
    // model can be null for set up purposes already, helpful to set up dummy player here
    return model.getScore(new TTPlayer(null, CoachColorAdapter.coachColorToPlayerColor(coach)));
  }

  @Override
  public Player getWinner() {
    return model.getWinner();
  }

  @Override
  public Player getPlayerOne() {
    return model.getPlayerOne();
  }

  @Override
  public Player getPlayerTwo() {
    return model.getPlayerTwo();
  }

  @Override
  public Player getCurrentPlayer() {
    return model.getCurrentPlayer();
  }

  @Override
  public List<List<Cell>> getGrid() {
    return model.getGrid();
  }

  @Override
  public List<Cell> getRow(int row) {
    return model.getRow(row);
  }

  @Override
  public Cell getCellAt(int row, int col) {
    return model.getCellAt(row, col);
  }

  @Override
  public int numFlipped(Card card, int row, int col) {
    return model.numFlipped(card, row, col);
  }

  @Override
  public void addTurnListener(ThreeTriosController listener) {
    model.addTurnListener(listener);
  }

  @Override
  public int getScore(Player player) {
    return model.getScore(player);
  }
}
