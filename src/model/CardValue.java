package model;

/**
 * Represents a value for a Card. Values are represented in hexadecimal form
 * in the two string method so that all values are exactly one character.
 */
public enum CardValue {

  ONE("1"),
  TWO("2"),
  THREE("3"),
  FOUR("4"),
  FIVE("5"),
  SIX("6"),
  SEVEN("7"),
  EIGHT("8"),
  NINE("9"),
  TEN("A");

  private final String val;

  /**
   * Constructor for a CardValue, uses a value param for the toString method.
   * @param val   Hexadecimal value for the CardValue.
   */
  CardValue(String val) {
    this.val = val;
  }

  /**
   * Compares this CardValue to the given CardValue. Returns a positive
   * integer if this is greater than the other value, zero if they are the same,
   * and negative if the other is greater.
   * @param other   The CardValue to compare this one to
   * @return        An integer comparison
   */
  public int compareCardVal(CardValue other) {
    return this.ordinal() - other.ordinal();
  }

  @Override
  public String toString() {
    return this.val;
  }

  public static CardValue toCardValue(int val) {
    switch(val){
      case 1: return ONE;
      case 2: return TWO;
      case 3: return THREE;
      case 4: return FOUR;
      case 5: return FIVE;
      case 6: return SIX;
      case 7: return SEVEN;
      case 8: return EIGHT;
      case 9: return NINE;
      case 10: return TEN;
    }
  }
}
