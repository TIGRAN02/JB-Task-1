package parsers.instructionParsers;

import exceptions.ValueException;
import queries.parts.NumberField;

public class SkipParser extends InstructionParser {
  private NumberField skip = new NumberField();

  public SkipParser(String[] sql) {
    super(sql);
  }

  public NumberField getSkip() {
    return skip;
  }

  @Override
  public int parse(int beginInd) throws ValueException, NumberFormatException {
    if (!this.parts[beginInd].toUpperCase().equals("SKIP")) {
      return beginInd;
    }

    int currInd = beginInd;

    if (++currInd == parts.length) {
      throw new ValueException("Skip value expected");
    }

    try {
      this.skip = new NumberField(Integer.parseInt(parts[currInd++]));
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Numerical skip expected");
    }

    return currInd;
  }
}
