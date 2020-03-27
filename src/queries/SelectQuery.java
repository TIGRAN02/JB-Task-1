package queries;

import queries.parts.Columns;
import queries.parts.NumberField;
import queries.parts.Sort;
import queries.parts.Where;

public class SelectQuery {
  private String relation;
  private Columns columns = new Columns();
  private Where whereClauses = new Where();
  private NumberField limit = new NumberField();
  private NumberField skip = new NumberField();
  private Sort sorting = new Sort();



  public String getRelation() {
    return relation;
  }

  public void setRelation(String relation) {
    this.relation = relation;
  }



  public boolean allColumns() {
    return this.columns.isAll();
  }

  public Columns getColumns() {
    return this.columns;
  }

  public void setColumns(Columns columns) {
    this.columns = columns;
  }



  public Where getWhere() {
    return this.whereClauses;
  }

  public void setWhereClauses(Where whereClauses) {
    this.whereClauses = whereClauses;
  }



  public NumberField getLimit() {
    return this.limit;
  }

  public void setLimit(NumberField limit) {
    this.limit = limit;
  }



  public NumberField getSkip() {
    return this.skip;
  }

  public void setSkip(NumberField skip) {
    this.skip = skip;
  }



  public Sort getSorting() {
    return this.sorting;
  }

  public void setSorting(Sort sorting) {
    this.sorting = sorting;
  }
}
