package compilers.parts;

import queries.parts.Columns;

public class ColumnsCompiler implements PartCompiler {
  private Columns columns;

  public ColumnsCompiler(Columns columns) {
    this.columns = columns;
  }

  @Override
  public String compile() {
    if (this.columns.isAll()) {
      return "";
    }

    StringBuilder cols = new StringBuilder("{");

    for (String field : this.columns.getColumns()) {
      cols.append(field).append(": 1, ");
    }

    cols.delete(cols.length() - 2, cols.length());
    cols.append("}");

    return cols.toString();
  }
}
