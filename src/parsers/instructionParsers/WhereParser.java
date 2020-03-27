package parsers.instructionParsers;

import exceptions.ComparatorException;
import queries.parts.Where;

public class WhereParser extends InstructionParser {
  private Where clauses = new Where();

  public WhereParser(String[] sql) {
    super(sql);
  }

  public Where getClauses() {
    return clauses;
  }

  @Override
  public int parse(int beginInd) throws ComparatorException {
    if (!this.parts[beginInd].toUpperCase().equals("WHERE")) {
      return beginInd;
    }

    int currInd = beginInd;

    boolean hasClause = true;

    while (hasClause) {
      currInd++;

      String field = parts[currInd++];
      int operation;

      switch (parts[currInd]) {
        case ">": {
          operation = 0;
          break;
        }

        case "<": {
          operation = 1;
          break;
        }

        case "<>": {
          operation = 2;
          break;
        }

        case "=": {
          operation = 3;
          break;
        }

        default: {
          throw new ComparatorException("Unhandled comparator");
        }
      }

      String value = parts[++currInd];

      this.clauses.pushClause(field, operation, value);

      // TODO: добавить обработку OR
      currInd++;
      if (currInd == parts.length || !parts[currInd].toUpperCase().equals("AND")) {
        hasClause = false;
      }
    }

    return currInd;
  }
}
