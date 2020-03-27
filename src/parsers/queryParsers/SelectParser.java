package parsers.queryParsers;

import exceptions.ComparatorException;
import exceptions.InstructionException;
import exceptions.ValueException;
import parsers.instructionParsers.*;
import queries.SelectQuery;

public class SelectParser extends QueryParser {
  SelectQuery query = new SelectQuery();

  public SelectParser(String sql) {
    super(sql);
  }

  public SelectQuery getQuery() {
    return query;
  }

  @Override
  public void parse() throws ComparatorException, InstructionException, ValueException, NumberFormatException {
    int currInd = 0;

    if (!this.parts[currInd++].toUpperCase().equals("SELECT")) {
      return;
    }

    ColumnsParser cp = new ColumnsParser(parts);
    currInd = cp.parse(currInd);
    this.query.setColumns(cp.getColumns());

    if (currInd++ == this.parts.length) {
      throw new InstructionException("FROM expected");
    }

    this.query.setRelation(this.parts[currInd++]);

    if (currInd == this.parts.length) {
      return;
    }

    if (this.parts[currInd].toUpperCase().equals("WHERE")) {
      WhereParser wh = new WhereParser(this.parts);
      currInd = wh.parse(currInd);
      this.query.setWhereClauses(wh.getClauses());

      if (currInd == this.parts.length) {
        return;
      }
    }

    if (this.parts[currInd].toUpperCase().equals("ORDER")) {
      OrderByParser ord = new OrderByParser(this.parts);
      currInd = ord.parse(currInd);
      this.query.setSorting(ord.getSorting());

      if (currInd == this.parts.length) {
        return;
      }
    }

    if (this.parts[currInd].toUpperCase().equals("LIMIT")) {
      LimitParser lim = new LimitParser(this.parts);
      currInd = lim.parse(currInd);
      this.query.setLimit(lim.getLimit());

      if (currInd == this.parts.length) {
        return;
      }
    }

    if (this.parts[currInd].toUpperCase().equals("SKIP")) {
      SkipParser skip = new SkipParser(this.parts);
      currInd = skip.parse(currInd);
      this.query.setSkip(skip.getSkip());

      if (currInd == this.parts.length) {
        return;
      }
    }

    if (this.parts[currInd].toUpperCase().equals("LIMIT")) {
      LimitParser lim = new LimitParser(this.parts);
      currInd = lim.parse(currInd);
      this.query.setLimit(lim.getLimit());

      if (currInd != this.parts.length) {
        throw new InstructionException("Expected end of this.query");
      }
    }
  }
}
