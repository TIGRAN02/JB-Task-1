package queries.parts;

public class NumberField {
  boolean real;
  int value;

  public NumberField() {
    this.real = false;
    this.value = -1;
  }

  public NumberField(int value) {
    this.real = true;
    this.value = value;
  }

  public boolean isReal() {
    return real;
  }

  public int getValue() {
    return value;
  }
}
