package parsers.instructionParsers;

import exceptions.InstructionException;
import queries.parts.Columns;

public class ColumnsParser extends InstructionParser {
  private Columns columns = new Columns();

  public ColumnsParser(String[] sql) {
    super(sql);
  }

  public Columns getColumns() {
    return columns;
  }

  @Override
  public int parse(int beginInd) throws InstructionException {
    int currInd = beginInd;

    if (this.parts[currInd].equals("*")) {
      currInd++;
    } else {
      boolean endedWithComma = true;

      for (currInd = 1; currInd < this.parts.length && !this.parts[currInd].toUpperCase().equals("FROM"); currInd++) {
        if (!endedWithComma) {
          throw new InstructionException("FROM expected");
        }

        if (this.parts[currInd].endsWith(",")) {
          this.columns.pushColumn(this.parts[currInd].substring(0, this.parts[currInd].length() - 1));
        } else {
          endedWithComma = false;
          this.columns.pushColumn(this.parts[currInd]);
        }
      }
    }

    return currInd;
  }
}
