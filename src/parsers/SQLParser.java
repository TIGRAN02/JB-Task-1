package parsers;

import parsers.queryParsers.SelectParser;
import queries.SelectQuery;

public class SQLParser {
  private String sql;

  public SQLParser(String sql) {
    this.sql = sql;
  }

  public SelectQuery parseSelect() {
    SelectParser sp = new SelectParser(this.sql);
    sp.parse();
    return sp.getQuery();
  }
}
