package model;

import java.util.ArrayList;
import java.util.List;

import controller.ThreeTriosController;
import model.rules.BattleRule;
import model.rules.NormalRules;

/**
 * Represents a simple 2 player ThreeTriosModel.
 */
public class TTModel implements ThreeTriosModel<Card> {
  protected final List<List<Cell<Card>>> grid;
  private final Player<Card> playerOne;
  private final Player<Card> playerTwo;
  protected Player<Card> activePlayer;
  private int playableCells;
  private boolean isStarted;
  private final List<TTTurnListener> listeners;
  private final BattleRule battleRules;


  /**
   * The default constructor for a model of Three Trios.
   */
  public TTModel() {
    grid = new ArrayList<>();
    playerOne = new TTPlayer(this, PlayerColor.RED);
    playerTwo = new TTPlayer(this, PlayerColor.BLUE);
    activePlayer = playerOne;
    playableCells = -1;
    isStarted = false;
    listeners = new ArrayList<>();
    battleRules = new NormalRules();
  }

  /**
   * A constructor for the model for a 2-player game of Three Trios.
   * @param p1 player one
   * @param p2 player two
   */
  public TTModel(Player<Card> p1, Player<Card> p2) {
    grid = new ArrayList<>();
    playerOne = p1;
    playerTwo = p2;
    activePlayer = playerOne;
    playableCells = -1;
    isStarted = false;
    listeners = new ArrayList<>();
    battleRules = new NormalRules();
  }

  public TTModel(Player<Card> p1, Player<Card> p2, BattleRule rules) {
    grid = new ArrayList<>();
    playerOne = p1;
    playerTwo = p2;
    activePlayer = playerOne;
    playableCells = -1;
    isStarted = false;
    listeners = new ArrayList<>();
    this.battleRules = rules;
  }


  @Override
  public void startGame(List<List<Cell<Card>>> grid, List<Card> cards,
                        int rows, int cols) {
    if (isStarted) {
      throw new IllegalStateException("Game already started");
    } else if (grid == null || cards == null) {
      throw new IllegalArgumentException("Grid or Cards cannot be null");
    }
    this.grid.addAll(grid);
    playableCells = countPlayableCells(this.grid);
    if (cards.size() < playableCells + 1) {
      throw new IllegalArgumentException("There must be at least playable cells + 1 cards");
    } else if (grid.size() != rows) {
      throw new IllegalArgumentException("Invalid number of rows");
    } else if (grid.get(0).size() != cols) {
      throw new IllegalArgumentException("Invalid number of columns");
    }
    if (playableCells % 2 == 0) {
      throw new IllegalArgumentException("Total # of card cells must be an odd number");
    }

    dealHands(cards, playableCells);
    isStarted = true;
    notifyNewTurn();
  }


  /**
   * Deals (n+1)/2 cards to each player where n is the number of card cells on the grid.
   * @param cards list of every card as given from the config
   * @param playableCells the number of card cells on the grid
   */
  private void dealHands(List<Card> cards, int playableCells) {
    int counter = 0;
    while (!cards.isEmpty()
            && playerOne.getHand().size() <= ((playableCells + 1) / 2)
            && playerTwo.getHand().size() < ((playableCells + 1) / 2)) {
      if (counter % 2 == 0) {
        playerOne.addToHand(cards.remove(0));
      } else {
        playerTwo.addToHand(cards.remove(0));
      }
      counter++;
    }
  }

  @Override
  public void placeCard(int cardIdx, int row, int col) {
    if (cardIdx > getCurrentPlayer().getHand().size()) {
      throw new IllegalArgumentException("Invalid card index");
    }
    Card card = this.getCurrentPlayer().getHand().get(cardIdx);
    if (!isStarted) {
      throw new IllegalStateException("Game has not started");
    } else if (isGameOver()) { //checks if there are no more cells that can be placed on
      throw new IllegalStateException("Game is over");
    } else if (row < 0 || row >= grid.size() || col < 0 || col >= grid.get(row).size()) {
      throw new IllegalArgumentException("Invalid row or column index");
    } else if (grid.get(row).get(col).getCard() != null) {
      throw new IllegalArgumentException("Already a card here: " + row + "," + col);
    }
    //Will throw an error if the cell is a hole cell
    grid.get(row).get(col).updateCell(card, getCurrentPlayer());
    getCurrentPlayer().removeFromHand(card);
    playableCells--;

    battlePhase(row, col);
    switchPlayer();
  }

  /**
   * In order N, S, E, W, battles the opposing card in the given direction.
   * @param cardRow the row of the initial card doing battle
   * @param cardCol the column of the initial card doing battle
   */
  protected void battlePhase(int cardRow, int cardCol) {
    //Check North neighbor, then South neighbor
    Cell<Card> cell = grid.get(cardRow).get(cardCol);
    if (opposingCardInBounds(cardRow, cardCol, CardinalDirection.NORTH)) {
      battleOpposingCell(cell, CardinalDirection.NORTH,
              cardRow - 1, cardCol);
    }
    if (opposingCardInBounds(cardRow, cardCol, CardinalDirection.SOUTH)) {
      battleOpposingCell(cell, CardinalDirection.SOUTH,
              cardRow + 1, cardCol);
    }
    if (opposingCardInBounds(cardRow, cardCol, CardinalDirection.EAST)) {
      battleOpposingCell(cell, CardinalDirection.EAST,
              cardRow, cardCol + 1);
    }
    if (opposingCardInBounds(cardRow, cardCol, CardinalDirection.WEST)) {
      battleOpposingCell(cell, CardinalDirection.WEST,
              cardRow, cardCol - 1);
    }
  }

  /**
   * Does battle on the cell challenged using the direction value given, so long as:
   * there is a card in the index given & it belongs to the opposing player.
   * Then if the initial card is stronger swap the color of the challenged card, and recursively
   * call battlePhase on said card.
   * @param cell the cell doing battle
   * @param dir the direction used to compare the cell doing battle's value
   * @param cellRow the row index of the cell being challenged
   * @param cellCol the column index of the cell being challenged
   */
  protected void battleOpposingCell(Cell<Card> cell, CardinalDirection dir,
                                  int cellRow, int cellCol) {
    Cell<Card> opposing = grid.get(cellRow).get(cellCol);
    if (opposing.hasCard()
            && cell.getPlayerColor() != opposing.getPlayerColor()
            && battleRules.beatsCard(cell.getCard(), opposing.getCard(), dir)) {
      opposing.setPlayerColor(activePlayer.getColor());
      battlePhase(cellRow, cellCol);
    }
  }

  /**
   * Shows if the opposing index exists on the games grid.
   * @param cardRow the row index
   * @param cardCol the column index
   * @param dir the direction which the opposing card should exist
   * @return true if the index given exists on the grid
   */
  private boolean opposingCardInBounds(int cardRow, int cardCol, CardinalDirection dir) {
    switch (dir) {
      case NORTH:
        return cardRow - 1 >= 0;
      case SOUTH:
        return cardRow + 1 < grid.size();
      case EAST:
        return cardCol + 1 < grid.get(0).size();
      case WEST:
        return cardCol - 1 >= 0;
      default:
        throw new IllegalArgumentException("Invalid cardinal direction");
    }
  }

  @Override
  public Player<Card> getCurrentPlayer() {
    if (!isStarted) {
      throw new IllegalStateException("Game has not started");
    }
    return activePlayer;
  }

  /**
   * Changes the active player to the other non-active player.
   */
  private void switchPlayer() {
    if (activePlayer.equals(playerOne)) {
      activePlayer = playerTwo;
    } else if (activePlayer.equals(playerTwo)) {
      activePlayer = playerOne;
    }
    notifyNewTurn();
  }

  /**
   * Counts the # of playable cells in the grid.
   * @return the # playable cells
   */
  private int countPlayableCells(List<List<Cell<Card>>> grid) {
    int playable = 0;
    for (int i = 0; i < grid.size(); i++) {
      for (int j = 0; j < grid.get(i).size(); j++) {
        if (grid.get(i).get(j).canPlayHere()) {
          playable++;
        }
      }
    }
    return playable;
  }

  @Override
  public boolean isGameOver() {
    if (!isStarted) {
      throw new IllegalStateException("Game hasn't started");
    }
    return playableCells == 0;
  }

  @Override
  public Player<Card> getWinner() {
    if (!isGameOver()) {
      throw new IllegalStateException("Game is not over");
    }
    int p1Count = countCells(playerOne);
    int p2Count = countCells(playerTwo);
    if (p1Count > p2Count) {
      return playerOne;
    } else {
      return playerTwo;
    }
  }

  @Override
  public Player<Card> getPlayerOne() {
    return playerOne;
  }

  @Override
  public Player<Card> getPlayerTwo() {
    return playerTwo;
  }

  @Override
  public List<List<Cell>> getGrid() {
    if (!isStarted) {
      throw new IllegalStateException("Game hasn't started");
    }
    List<List<Cell>> copy = new ArrayList<>();
    for (List<Cell<Card>> row : grid) {
      copy.add(new ArrayList<>(row));
    }
    return copy;
  }

  @Override
  public List<Cell> getRow(int row) {
    if (!isStarted) {
      throw new IllegalStateException("Game hasn't started");
    } else if (row < 0 || row >= grid.size()) {
      throw new IllegalArgumentException("Invalid row index, given: " + row);
    }
    return new ArrayList<>(grid.get(row));
  }

  @Override
  public Cell getCellAt(int row, int col) {
    return this.grid.get(row).get(col);
  }

  @Override
  public int numFlipped(Card card, int cardRow, int cardCol) {
    int result = 0;
    List<Cell<Card>> counted = new ArrayList<>();
    counted.add(this.grid.get(cardRow).get(cardCol));
    if (opposingCardInBounds(cardRow, cardCol, CardinalDirection.NORTH)
            && (getCellAt(cardRow - 1, cardCol).getPlayerColor() != null)
            && !(getCellAt(cardRow - 1, cardCol).getPlayerColor().equals(this.activePlayer.getColor()))) {
      result += flipCountNext(card, cardRow - 1, cardCol, CardinalDirection.NORTH, counted);
    }
    if (opposingCardInBounds(cardRow, cardCol, CardinalDirection.SOUTH)
            && (getCellAt(cardRow + 1, cardCol).getPlayerColor() != null)
            && !(getCellAt(cardRow + 1, cardCol).getPlayerColor().equals(this.activePlayer.getColor()))) {
      result += flipCountNext(card, cardRow + 1, cardCol, CardinalDirection.SOUTH, counted);
    }
    if (opposingCardInBounds(cardRow, cardCol, CardinalDirection.EAST)
            && (getCellAt(cardRow, cardCol + 1).getPlayerColor() != null)
            && !(getCellAt(cardRow, cardCol + 1).getPlayerColor().equals(this.activePlayer.getColor()))) {
      result += flipCountNext(card, cardRow, cardCol + 1, CardinalDirection.EAST, counted);
    }
    if (opposingCardInBounds(cardRow, cardCol, CardinalDirection.WEST)
            && (getCellAt(cardRow, cardCol - 1).getPlayerColor() != null)
            && !(getCellAt(cardRow, cardCol - 1).getPlayerColor().equals(this.activePlayer.getColor()))) {
      result += flipCountNext(card, cardRow, cardCol - 1, CardinalDirection.WEST, counted);
    }
    return result;
  }

  private int flipCountNext(Card card, int cardRow, int cardCol, CardinalDirection dir,
                            List<Cell<Card>> counted) {
    if (cardRow < 0 || cardCol < 0 || !this.grid.get(cardRow).get(cardCol).hasCard()) {
      return 0;
    }
    

    int result = 0;
    Card otherCard = this.grid.get(cardRow).get(cardCol).getCard();

    if (card.isStrongerCard(otherCard, dir, battleRules) && !counted.contains(this.grid.get(cardRow)
            .get(cardCol))) {
      counted.add(this.grid.get(cardRow).get(cardCol));
      if (opposingCardInBounds(cardRow, cardCol, CardinalDirection.NORTH)
              && (getCellAt(cardRow - 1, cardCol).getPlayerColor() != null)
              && !(getCellAt(cardRow - 1, cardCol).getPlayerColor().equals(this.activePlayer.getColor()))) {
        result += flipCountNext(otherCard, cardRow - 1, cardCol, CardinalDirection.NORTH, counted);
      }
      if (opposingCardInBounds(cardRow, cardCol, CardinalDirection.SOUTH)
              && (getCellAt(cardRow + 1, cardCol).getPlayerColor() != null)
              && !(getCellAt(cardRow + 1, cardCol).getPlayerColor().equals(this.activePlayer.getColor()))) {
        result += flipCountNext(otherCard, cardRow + 1, cardCol, CardinalDirection.SOUTH, counted);
      }
      if (opposingCardInBounds(cardRow, cardCol, CardinalDirection.EAST)
              && (getCellAt(cardRow, cardCol + 1).getPlayerColor() != null)
              && !(getCellAt(cardRow, cardCol + 1).getPlayerColor().equals(this.activePlayer.getColor()))) {
        result += flipCountNext(otherCard, cardRow, cardCol + 1, CardinalDirection.EAST, counted);
      }
      if (opposingCardInBounds(cardRow, cardCol, CardinalDirection.WEST)
              && (getCellAt(cardRow, cardCol - 1).getPlayerColor() != null)
              && !(getCellAt(cardRow, cardCol - 1).getPlayerColor().equals(this.activePlayer.getColor()))) {
        result += flipCountNext(otherCard, cardRow, cardCol - 1, CardinalDirection.WEST, counted);
      }
      return result + 1;
    }
    return 0;
  }

  /**
   * The number of cells belonging to the player on the grid.
   * @param player the player
   * @return the # of cells belonging to the player currently on the grid
   */
  private int countCells(Player<Card> player) {
    int count = 0;
    for (List<Cell<Card>> row : grid) {
      for (Cell<Card> cell : row) {
        if (cell.getPlayerColor() == player.getColor()) {
          count++;
        }
      }
    }
    return count;
  }

  @Override
  public void addTurnListener(ThreeTriosController listener) {
    listeners.add(new TTTurnListener(listener));
  }
  
  private void notifyNewTurn() {
    for (TTTurnListener listener : listeners) {
      listener.newTurn();
    }
  }

  @Override
  public boolean isGameStarted() {
    return isStarted;
  }

  @Override
  public int getScore(Player player) {
    int score = 0;
    for (List<Cell> row : getGrid()) {
      for (Cell cell : row) {
        if (cell.getPlayerColor() == player.getColor()) {
          score++;
        }
      }
    }
    return score;
  }

  /**
   * An event listener for a three trios game. Uses the controller to handle new turn events.
   */
  class TTTurnListener implements ModelStatus {
    private final ThreeTriosController features;
    
    public TTTurnListener(ThreeTriosController features) {
      if (features == null) {
        throw new IllegalArgumentException("Features cannot be null");
      }
      this.features = features;
    }
    
    @Override
    public void newTurn() {
      features.refresh();
    }
  }
}
