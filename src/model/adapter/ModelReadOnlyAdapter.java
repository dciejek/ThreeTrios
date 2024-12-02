package model.adapter;

public class ModelReadOnlyAdapter {

  //pass in model

  //curCoach()
  //return new CoachColor using currentPlayer().getColor()

  //Map<CoachColor, List<Card>> curCoachesHands()
  //in startGame build map using CoachColorAdapter from PlayerColor, then
  //model.getPlayerOne().getColor -> CoachColor and use hand for List<Card> adapting each card in
  //process

  //isGameStarted()
  //return model.isGameStarted

  //isGameOver()
  //return model.isGameOver

  //CoachColor winner();
  //winner logic, prob if gameOver return most cards or something

  //Grid curGrid();
  //use gridAdapter using model.getGrid()

  //int numRows()
  //model.getGrid().size()

  //int numCols()
  //model.getGrid().get(0).size();

  //Optional<Card> cardAt(int row, int col)
  //model.getCellAt() return empty if holecell/empty cardCell
  //otherwise CardAdapter model.getCellAt().getCard()

  //Optional<CoachColor> ownerAt(int row, int col)
  //model.getCellAt() return empty if holecell/empty cardCell
  //otherwise CoachColorAdapter model.getCellAt().getPlayerColor()

  //boolean canPlayAt(int row, int col)
  //return model.getCellAt().canPlayHere()

  //int numFlippedIfPlaced(Card card, int row, int col)
  //use strategy logic?

  //int score(CoachColor coach)
  //implement this, +1 for each cell belonging to color
  //for each through model.getGrid()
}
