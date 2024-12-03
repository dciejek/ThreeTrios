package model.adapter;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import model.Card;
import model.Player;
import model.PlayerColor;
import model.ThreeTriosModel;
import provider.model.GamePlayer;
import provider.model.Model;
import provider.model.Move;
import strategy.Play;

public final class GamePlayerAdapter implements GamePlayer, Player<Card> {
  private final Player<Card> adaptee;

  public GamePlayerAdapter(Player<Card> adaptee) {
    this.adaptee = adaptee;
  }

  @Override
  public PlayerColor getColor() {
    return null;
  }

  @Override
  public List getHand() {
    return List.of();
  }

  @Override
  public void removeFromHand(Card card) {

  }

  @Override
  public void addToHand(Card card) {

  }

  @Override
  public Play getPlay(ThreeTriosModel model) {
    return null;
  }

  @Override
  public void setModel(ThreeTriosModel model) {

  }

  @Override
  public void accept(Consumer<Move> moveConsumer, Supplier<Model> supplier) {

  }
}
