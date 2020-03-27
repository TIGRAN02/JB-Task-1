package compilers;

import compilers.parts.*;
import queries.SelectQuery;

public class MongoCompiler {
  private SelectQuery query;

  public MongoCompiler(SelectQuery query) {
    this.query = query;
  }

  public String compileSelect() {
    StringBuilder mongoQuery = new StringBuilder("db.");

    mongoQuery.append(this.query.getRelation()).append(".find(");

    PredicateCompiler predicateCompiler = new PredicateCompiler(this.query.getWhere());
    mongoQuery.append(predicateCompiler.compile());

    if (!this.query.allColumns()) {
      mongoQuery.append(", ");
    }
    ColumnsCompiler columnsCompiler = new ColumnsCompiler(this.query.getColumns());
    mongoQuery.append(columnsCompiler.compile());

    mongoQuery.append(")");

    SortingCompiler sortingCompiler = new SortingCompiler(this.query.getSorting());
    mongoQuery.append(sortingCompiler.compile());

    SkipCompiler skipCompiler = new SkipCompiler(this.query.getSkip());
    mongoQuery.append(skipCompiler.compile());

    LimitCompiler limitCompiler = new LimitCompiler(this.query.getLimit());
    mongoQuery.append(limitCompiler.compile());

    return mongoQuery.toString();
  }
}
