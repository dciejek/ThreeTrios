package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestModel {
  ThreeTriosModel<PlayingCard, TTPlayer> model;

  @Test
  public void testModelConstruction() {
    model = new TTModel();
    Assert.assertTrue(model.getGrid().isEmpty());
  }

  @Before
  public void setUp() {

  }
}
