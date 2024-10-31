package view;

import org.junit.Before;
import org.junit.Test;

import model.PlayingCard;
import model.TTModel;
import model.TTPlayer;
import model.ThreeTriosModel;

public class TestView {

  @Before
  public void setUp() {
    ThreeTriosModel<PlayingCard, TTPlayer> model = new TTModel();

  }

  @Test
  public void testToString() {

  }
}
