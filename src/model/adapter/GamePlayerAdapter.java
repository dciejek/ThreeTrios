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

public class GamePlayerAdapter implements GamePlayer, Player<Card> {
  private final Player<Card> adaptee;

  public GamePlayerAdapter(Player<Card> adaptee) {
    this.adaptee = adaptee;
  }

  @Override
  public PlayerColor getColor() {
    return adaptee.getColor();
  }

  @Override
  public List getHand() {
    return adaptee.getHand();
  }

  @Override
  public void removeFromHand(Card card) {
    adaptee.removeFromHand(card);
  }

  @Override
  public void addToHand(Card card) {
    adaptee.addToHand(card);
  }

  @Override
  public Play getPlay(ThreeTriosModel model) {
    return adaptee.getPlay(model);
  }

  @Override
  public void setModel(ThreeTriosModel model) {
    adaptee.setModel(model);
  }

  @Override
  public void accept(Consumer<Move> moveConsumer, Supplier<Model> supplier) {

  }
}
