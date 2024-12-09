package model;

import model.rules.BattleRule;
import model.rules.NormalRules;

public class TTModelRules extends TTModel {
  BattleRule rules;

  public TTModelRules() {
    super();
    this.rules = new NormalRules();
  }
}
