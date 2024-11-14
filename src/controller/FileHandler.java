package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.CardCell;
import model.CardValue;
import model.Cell;
import model.HoleCell;
import model.PlayingCard;
import model.TTModel;
import model.ThreeTriosModel;

/**
 * Contains static methods to determine fields for a ThreeTriosModel by parsing
 * the contents of files.
 */
public class FileHandler {

  /**
   * Reads a file as given from docs and translate the information to the model.
   * @param gridFile the given file.
   * @return A list of cells that represent the grid.
   */
  public static List<List<Cell<PlayingCard>>> readGrid(File gridFile) {
    List<List<String>> tempGrid = new ArrayList<>();
    try {
      Scanner scan = new Scanner(new FileReader(gridFile));
      while (scan.hasNextLine()) {
        tempGrid.add(new ArrayList<>(
                List.of(scan.nextLine().split(""))));
      }
      tempGrid.remove(0);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Grid file not found");
    }
    List<List<Cell<PlayingCard>>> ret = new ArrayList<>();
    List<Cell<PlayingCard>> cells = new ArrayList<>();
    for (List<String> row : tempGrid) {
      cells.clear();
      for (String s : row) {
        cells.add(cellFactory(s));
      }
      ret.add(new ArrayList<Cell<PlayingCard>>(cells));
    }
    return ret;
  }

  /**
   * Adds the correct cell type to the list of cells based on the string input.
   *
   * @param str "C" if a CardCell or "X" if a HoleCell
   * @throws IllegalArgumentException if the String is null or not a valid type ("X" or "C")
   */
  private static Cell<PlayingCard> cellFactory(String str) {
    if (str == null) {
      throw new IllegalArgumentException("String cannot be null");
    } else if (str.equals("C")) {
      return new CardCell();
    } else if (str.equals("X")) {
      return new HoleCell();
    } else {
      throw new IllegalArgumentException("Invalid cell type, given: " + str);
    }
  }

  /**
   * Reads the cards as given from the card file and translates it for the model.
   * @param cardFile the card file
   * @return a list of playing cards to be used in the model
   */
  public static List<PlayingCard> readCards(File cardFile) {
    List<PlayingCard> cards = new ArrayList<>();
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
   * Gets the row number from a given doc file for the model.
   * @param rowFile the doc file
   * @return the row number for the model to use
   */
  public static int readRowNum(File rowFile) {
    try {
      Scanner scan = new Scanner(new FileReader(rowFile));

      String line = scan.nextLine();
      return Integer.parseInt(line.substring(0, line.indexOf(" ")));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Grid file not found");
    }
  }

  /**
   * Gets the col number from a given doc file for the model.
   * @param colFile the doc file
   * @return the col number for the model to use
   */
  public static int readColNum(File colFile) {
    try {
      Scanner scan = new Scanner(new FileReader(colFile));

      String[] line = scan.nextLine().split(" ");
      return Integer.parseInt(line[1]);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Grid file not found");
    }
  }

  /**
   * Starts a game based off of the given inputs from the doc files, which translated
   * by the appropriate methods.
   * @return a game of Three trios
   */
  public static ThreeTriosModel makeGame(File gridFile, File cardFile) {
    ThreeTriosModel game = new TTModel();
    game.startGame(readGrid(gridFile), readCards(cardFile),
            readRowNum(gridFile), readColNum(gridFile));
    return game;
  }
}
