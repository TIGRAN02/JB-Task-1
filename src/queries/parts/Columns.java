package queries.parts;

import java.util.ArrayList;

public class Columns {
  private boolean all = true;
  private ArrayList<String> columns = new ArrayList<>();

  public ArrayList<String> getColumns() {
    return this.columns;
  }

  public boolean isAll() {
    return this.all;
  }

  public void pushColumn(String column) {
    this.all = false;
    this.columns.add(column);
  }
}
