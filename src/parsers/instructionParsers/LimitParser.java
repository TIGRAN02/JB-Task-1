package parsers.instructionParsers;

import exceptions.InstructionException;
import exceptions.ValueException;
import queries.parts.NumberField;

public class LimitParser extends InstructionParser {
  private NumberField limit = new NumberField();

  public LimitParser(String[] sql) {
    super(sql);
  }

  public NumberField getLimit() {
    return limit;
  }

  @Override
  public int parse(int beginInd) throws ValueException, NumberFormatException {
    if (!this.parts[beginInd].toUpperCase().equals("LIMIT")) {
      return beginInd;
    }

    int currInd = beginInd;

    if (++currInd == parts.length) {
      throw new ValueException("Limit value expected");
    }

    try {
      this.limit = new NumberField(Integer.parseInt(parts[currInd++]));
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Numerical limit expected");
    }

    return currInd;
  }
}
