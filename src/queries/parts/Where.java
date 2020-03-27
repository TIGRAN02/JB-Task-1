package queries.parts;

import basic.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class Where {
  boolean real = false;
  boolean oneClause = false;
  private HashMap<String, ArrayList<Pair<Integer, String>>> currClause = new HashMap<>();
  private ArrayList<HashMap<String, ArrayList<Pair<Integer, String>>>> clauses = new ArrayList<>();

  public ArrayList<HashMap<String, ArrayList<Pair<Integer, String>>>> getClauses() {
    return clauses;
  }

  public boolean isReal() {
    return this.real;
  }

  public boolean hasOneClause() {
    return this.oneClause;
  }

  public void pushSingleClause(String field, int operation, String value) {
    if (this.currClause.containsKey(field)) {
      if (this.currClause.get(field).size() == 1 && this.currClause.get(field).get(0).getFirst() == 3) {
        return;
      }
      if (operation == 3) {
        ArrayList<Pair<Integer, String>> curr = new ArrayList<>();
        curr.add(new Pair<>(operation, value));
        this.currClause.replace(field, curr);
      } else {
        this.currClause.get(field).add(new Pair<>(operation, value));
      }
    } else {
      ArrayList<Pair<Integer, String>> curr = new ArrayList<>();
      curr.add(new Pair<>(operation, value));
      this.currClause.put(field, curr);
    }
  }

  public void pushClauseSet() {
    if (!this.currClause.isEmpty()) {
      this.clauses.add(new HashMap<>(this.currClause));
      this.currClause.clear();
      this.real = true;
      this.oneClause = this.clauses.size() == 1;
    }
  }
}
