package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a simple 2 player ThreeTriosModel
 */
public class TTModel implements ThreeTriosModel<PlayingCard> {
  //Fields:
  //2D ArrayList<Card> representing the game grid
  //ArrayList<Player>
  private final ArrayList<ArrayList<Cell>> grid;
  private final Player playerOne, playerTwo;
  private Player activePlayer;
  private int playableCells;

  /**
   * The default constructor for a model of Three Trios.
   */
  TTModel() {
    grid = new ArrayList<ArrayList<Cell>>();
    playerOne = new TTPlayer(PlayerColor.BLUE);
    playerTwo = new TTPlayer(PlayerColor.RED);
    activePlayer = playerOne;
    playableCells = 0;
  }

  /**
   * Starts a game of three trios.
   * @param rows the # of rows in the grid
   * @param cols the # of cols in the grid
   * @param grid the 2D array of strings from config file that fill the grid
   * @param cards the list of every card from config file
   * @throws IllegalArgumentException if the given grid # of rows/cols do not match the value given
   * @throws IllegalArgumentException if the total # of card cells is even
   */
  @Override
  public void startGame(int rows, int cols, List<List<String>> grid, List<PlayingCard> cards) {
    if (grid.size() != rows) {
      throw new IllegalArgumentException("Invalid number of rows");
    } else if (grid.get(0).size() != cols) {
      throw new IllegalArgumentException("Invalid number of columns");
    }
    createGrid(grid);
    if (playableCells % 2 == 0) {
      throw new IllegalArgumentException("Total # of card cells must be an odd number");
    }
    dealHands(cards, playableCells);
  }

  /**
   * Deals (n+1)/2 cards to each player where n is the number of card cells on the grid.
   * @param cards list of every card as given from the config
   * @param playableCells the number of card cells on the grid
   */
  private void dealHands(List<PlayingCard> cards, int playableCells) {
    int counter = 0;
    while (!cards.isEmpty()
            && playerOne.getHand().size() <= ((playableCells + 1) / 2)
            && playerTwo.getHand().size() <= ((playableCells + 1) / 2)) {
      if (counter % 2 == 0) {
        playerOne.addToHand(cards.remove(0));
      } else {
        playerTwo.addToHand(cards.remove(0));
      }
      counter++;
    }
  }

  /**
   * Initializes the grid with the given matrix of hole cells and card cells.
   * @param grid the matrix of hole and card cells as given by the config
   */
  private void createGrid(List<List<String>> grid) {
    playableCells = 0;
    ArrayList<Cell> cells = new ArrayList<>();
    for (List<String> row : grid) {
      cells.clear();
      for (String str : row) {
        addCellToList(str, cells);
      }
      this.grid.add(cells);
    }
  }

  /**
   * Places a given card onto the game grid, then battle initiate the battle phase, once finished
   * switch the turn to the next player.
   * @param card the card to be placed on the grid
   * @param row the row index to be placed in
   * @param col the column index to be placed in
   * @throws IllegalArgumentException if a card already exists at the given index
   * @throws IllegalStateException if the game is over.
   */
  @Override
  public void placeCard(PlayingCard card, int row, int col) {
    if (grid.get(row).get(col) != null) {
      throw new IllegalArgumentException("Already a card here: " + row + "," + col);
    }
    if (isGameOver()) {
      throw new IllegalStateException("Game is over");
    }
    grid.get(row).get(col).updateCell(card, getCurrentPlayer());
    battlePhase(row, col);
    switchPlayer();
  }

  private void battlePhase(int row, int col) {
    //Check to make sure cell is valid
    //Not empty, not hole


    //Call isStrongerCard
    //which checks the card in cell's value against each neighbor
  }


  private void checkNeighbors(int cardRow, int cardCol) {
    //Check North neighbor, then South neighbor
    compareOpposingCard(cardRow, cardCol, CardinalDirection.NORTH);
    compareOpposingCard(cardRow, cardCol, CardinalDirection.SOUTH);
    compareOpposingCard(cardRow, cardCol, CardinalDirection.EAST);
    compareOpposingCard(cardRow, cardCol, CardinalDirection.WEST);
  }

  private void compareOpposingCard(int cardRow, int cardCol, CardinalDirection dir) {
    if (opposingCardInBounds(cardRow, cardCol, dir)) {

    }
  }

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

  private void compareOpposingCardHorizontal(int cardRow, int cardCol, int col, CardinalDirection dir) {
    switch (col) {
      case 1:
        if(isStrongerCard(grid.get(cardRow).get(cardCol).getCard().getDirection(dir),
                grid.get(cardRow).get(cardCol + col).getCard().getDirection())) {
          battlePhase(cardRow, cardCol + col);
        }
      case -1:
        if(isStrongerCard(grid.get(cardRow).get(cardCol).getCard().getWest(),
                grid.get(cardRow).get(cardCol + col).getCard().getEast())) {
          battlePhase(cardRow, cardCol + col);
        }
    }
  }

  private void compareOpposingCardVertical(int cardRow, int cardCol, int row) {
    switch (row) {
      case 1:
        if(isStrongerCard(grid.get(cardRow).get(cardCol).getCard().getSouth(),
                grid.get(cardRow + row).get(cardCol).getCard().getNorth())) {
          battlePhase(cardRow + row, cardCol);
        }
      case -1:
        if(isStrongerCard(grid.get(cardRow).get(cardCol).getCard().getNorth(),
                grid.get(cardRow + row).get(cardCol).getCard().getSouth())) {
          battlePhase(cardRow + row, cardCol);
        }
    }
  }

  private boolean isStrongerCard(int card, int card2) {
    //Have a way to get direction that we are comparing
    
    //Call battlePhase on second card if it loses


    return false;
  }

  /**
   * Gets the player whose turn it currently is.
   * @return the current active player
   * @throws IllegalStateException if the game is over or if the game hasn't started
   */
  public Player getCurrentPlayer() {
    return activePlayer;
  }

  /**
   * Changes the active player to the other player.
   */
  private void switchPlayer() {
    if (activePlayer.equals(playerOne)) {
      activePlayer = playerTwo;
    } else if (activePlayer.equals(playerTwo)) {
      activePlayer = playerOne;
    }
  }

  /**
   * Adds the correct cell type to the list of cells based on the string input.
   * @param str "C" if a CardCell or "X" if a HoleCell
   * @param cells list of cells that make up the grid
   * @throws IllegalArgumentException if the String is null or not a valid type ("X" or "C")
   */
  private void addCellToList(String str, List<Cell> cells) {
    if (str == null) {
      throw new IllegalArgumentException("String cannot be null");
    } else if (str.equals("C")) {
      cells.add(new CardCell());
      playableCells++;
    } else if (str.equals("X")) {
      cells.add(new HoleCell());
    } else {
      throw new IllegalArgumentException("Invalid cell type, given: " + str);
    }
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public Player getWinner() {
    int p1Count = countTiles(playerOne);
    int p2Count = countTiles(playerTwo);
  }

  @Override
  public List<List<Cell>> getGrid() {
    return List.of();
  }

  private int countTiles(Player player) {
    int count = 0;
    for (ArrayList<Cell> row : grid) {
      for (Cell cell : row) {
        if (cell.)
      }
    }
  }

  //Methods:
  //Constructor
}
