package parsers.instructionParsers;

import exceptions.InstructionException;
import exceptions.ValueException;
import queries.parts.Sort;

public class OrderByParser extends InstructionParser {
  private Sort sorting = new Sort();

  public OrderByParser(String[] sql) {
    super(sql);
  }

  public Sort getSorting() {
    return sorting;
  }

  @Override
  public int parse(int beginInd) throws InstructionException, ValueException {
    if (!this.parts[beginInd].toUpperCase().equals("ORDER")) {
      return beginInd;
    }

    int currInd = beginInd;

    if (!parts[++currInd].toUpperCase().equals("BY")) {
      throw new InstructionException("Incorrect instruction");
    }

    boolean hasNext = true;

    while (hasNext) {
      currInd++;

      if (currInd == this.parts.length) {
        throw new ValueException("Sorting field expected");
      }

      String field = this.parts[currInd];

      if (field.charAt(field.length() - 1) == ',') {
        this.sorting.pushClause(field.substring(0, field.length() - 1), false);
      } else {
        if (currInd != this.parts.length - 1) {
          switch (parts[++currInd].toUpperCase()) {
            case "DESC": {
              hasNext = false;
              currInd++;
              this.sorting.pushClause(field, true);
              break;
            }

            case "DESC,": {
              this.sorting.pushClause(field, true);
              break;
            }

            case "ASC": {
              hasNext = false;
              currInd++;
              this.sorting.pushClause(field, false);
              break;
            }

            case "ASC,": {
              this.sorting.pushClause(field, false);
              break;
            }

            default: {
              this.sorting.pushClause(field, false);
              hasNext = false;
            }
          }
        } else {
          this.sorting.pushClause(field, false);
          hasNext = false;
        }
      }
    }

    return currInd;
  }
}
