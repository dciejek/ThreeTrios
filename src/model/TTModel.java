package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a simple 2 player ThreeTriosModel
 */
public class TTModel implements ThreeTriosModel<PlayingCard, TTPlayer> {
  private final ArrayList<ArrayList<Cell<PlayingCard, TTPlayer>>> grid;
  private final TTPlayer playerOne, playerTwo;
  private TTPlayer activePlayer;
  private int playableCells;
  private boolean isStarted;

  /**
   * The default constructor for a model of Three Trios.
   */
  TTModel() {
    grid = new ArrayList<ArrayList<Cell<PlayingCard, TTPlayer>>>();
    playerOne = new TTPlayer(PlayerColor.BLUE);
    playerTwo = new TTPlayer(PlayerColor.RED);
    activePlayer = playerOne;
    playableCells = -1;
    isStarted = false;
  }

  @Override
  public void startGame(File gridFile, File cardFile) {
    int rows = parseRows(gridFile);
    int cols = parseCols(gridFile);
    initializeGrid(gridFile);
    ArrayList<PlayingCard> cards = initializeCards(cardFile);
    if (grid.size() != rows) {
      throw new IllegalArgumentException("Invalid number of rows");
    } else if (grid.get(0).size() != cols) {
      throw new IllegalArgumentException("Invalid number of columns");
    } else if (isStarted) {
      throw new IllegalStateException("Game already started");
    }
    if (playableCells % 2 == 0) {
      throw new IllegalArgumentException("Total # of card cells must be an odd number");
    }
    dealHands(cards, playableCells);
    isStarted = true;
  }

  /**
   * Gets the row int from the gridFile.
   * @param gridFile  the file get row data from
   * @return    the number of rows for the grid
   */
  private int parseRows(File gridFile) {
    try {
      Scanner scan = new Scanner(new FileReader(gridFile));

      String line = scan.nextLine();
      return Integer.parseInt(line.substring(0, line.indexOf(" ")));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Grid file not found");
    }
  }

  /**
   * Gets the column int from the gridFile.
   * @param gridFile  the file get column data from
   * @return    the number of columns for the grid
   */
  private int parseCols(File gridFile) {
    try {
      Scanner scan = new Scanner(new FileReader(gridFile));

      String[] line = scan.nextLine().split(" ");
      return Integer.parseInt(line[1]);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Grid file not found");
    }
  }

  /**
   * Initializes the cards from the cardFile as PlayingCards.
   * @param cardFile  file to draw data from. Cards should be written in the form of:
   *                  CARDNAME NORTH SOUTH EAST WEST
   * @return          the list of cards to play with
   */
  private ArrayList<PlayingCard> initializeCards(File cardFile) {
    ArrayList<PlayingCard> cards = new ArrayList<>();
    try {
      FileReader reader = new FileReader(cardFile);
      Scanner scan = new Scanner(reader);

      while (scan.hasNextLine()) {
        String[] line = scan.nextLine().split(" ");
        cards.add(new PlayingCard(line[0], CardValue.toCardValue(line[1]),
                CardValue.toCardValue(line[2]),
                CardValue.toCardValue(line[3]),
                CardValue.toCardValue(line[4])));
      }
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Cannot find or read card file");
    }
    return cards;
  }

  /**
   * Initializes the grid according to the gridFile, with
   * Xs representing holes and Cs representing CardCells.
   * @param gridFile  the file to get grid data from
   */
  private void initializeGrid(File gridFile) {
    List<List<String>> grid = new ArrayList<>();
    try {
      Scanner scan = new Scanner(new FileReader(gridFile));

      while (scan.hasNextLine()) {
        String[] line = scan.nextLine().split(" ");
        grid.add(new ArrayList<String>(List.of(line)));
      }
      createGrid(grid);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Grid file not found");
    }
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
    ArrayList<Cell<PlayingCard, TTPlayer>> cells = new ArrayList<>();
    for (List<String> row : grid) {
      cells.clear();
      for (String str : row) {
        addCellToList(str, cells);
      }
      this.grid.add(cells);
    }
  }

  @Override
  public void placeCard(PlayingCard card, int row, int col) {
    if (grid.get(row).get(col) != null) {
      throw new IllegalArgumentException("Already a card here: " + row + "," + col);
    } else if (!isStarted) {
      throw new IllegalStateException("Game has not started");
    } else if (isGameOver()) { //checks if there are no more cells that can be placed on
      throw new IllegalStateException("Game is over");
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
  private void battlePhase(int cardRow, int cardCol) {
    //Check North neighbor, then South neighbor
    Cell cell = grid.get(cardRow).get(cardCol);
    if (opposingCardInBounds(cardRow - 1, cardCol, CardinalDirection.NORTH)) {
      battleOpposingCell(cell, CardinalDirection.NORTH,
              cardRow - 1, cardCol);
    }
    if (opposingCardInBounds(cardRow + 1, cardCol, CardinalDirection.SOUTH)) {
      battleOpposingCell(cell, CardinalDirection.SOUTH,
              cardRow + 1, cardCol);
    }
    if (opposingCardInBounds(cardRow, cardCol + 1, CardinalDirection.EAST)) {
      battleOpposingCell(cell, CardinalDirection.EAST,
              cardRow, cardCol + 1);
    }
    if (opposingCardInBounds(cardRow - 1, cardCol, CardinalDirection.WEST)) {
      battleOpposingCell(cell, CardinalDirection.WEST,
              cardRow - 1, cardCol);
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
  private void battleOpposingCell(Cell cell, CardinalDirection dir,
                                  int cellRow, int cellCol) {
    Cell opposing = grid.get(cellRow).get(cellCol);
    if (opposing.hasCard()
            && cell.getPlayerColor() != opposing.getPlayerColor()
            && cell.getCard().isStrongerCard(opposing.getCard(), dir)) {
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
        return cardRow < grid.size();
      case EAST:
        return cardCol < grid.get(0).size();
      case WEST:
        return cardCol - 1 >= 0;
      default:
        throw new IllegalArgumentException("Invalid cardinal direction");
    }
  }

  @Override
  public TTPlayer getCurrentPlayer() {
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
  }

  /**
   * Adds the correct cell type to the list of cells based on the string input.
   * @param str "C" if a CardCell or "X" if a HoleCell
   * @param cells list of cells that make up the grid
   * @throws IllegalArgumentException if the String is null or not a valid type ("X" or "C")
   */
  private void addCellToList(String str, List<Cell<PlayingCard, TTPlayer>> cells) {
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
    if (!isStarted) {
      throw new IllegalStateException("Game hasn't started");
    }
    return playableCells == 0;
  }

  @Override
  public Player<PlayingCard> getWinner() {
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
  public List<List<Cell<PlayingCard, TTPlayer>>> getGrid() {
    List<List<Cell<PlayingCard, TTPlayer>>> copy = new ArrayList<>();
    for (List<Cell<PlayingCard, TTPlayer>> row : grid) {
      copy.add(new ArrayList<>(row));
    }
    return copy;
  }

  @Override
  public List<Cell> getRow(int row) {
    return new ArrayList<>(grid.get(row));
  }

  /**
   * The number of cells belonging to the player on the grid.
   * @param player the player
   * @return the # of cells belonging to the player currently on the grid
   */
  private int countCells(Player<PlayingCard> player) {
    int count = 0;
    for (ArrayList<Cell<PlayingCard, TTPlayer>> row : grid) {
      for (Cell cell : row) {
        if (cell.getPlayerColor() == player.getColor()) {
          count++;
        }
      }
    }
    return count;
  }
}
