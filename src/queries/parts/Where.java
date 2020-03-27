package queries.parts;

import basic.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class Where {
  boolean real = false;
  private HashMap<String, ArrayList<Pair<Integer, String>>> clauses = new HashMap<>();

  public HashMap<String, ArrayList<Pair<Integer, String>>> getClauses() {
    return clauses;
  }

  public boolean isReal() {
    return real;
  }

  public void pushClause(String field, int operation, String value) {
    this.real = true;
    if (this.clauses.containsKey(field)) {
      if (this.clauses.get(field).size() == 1 && this.clauses.get(field).get(0).getFirst() == 3) {
        return;
      }
      if (operation == 3) {
        ArrayList<Pair<Integer, String>> curr = new ArrayList<>();
        curr.add(new Pair<>(operation, value));
        this.clauses.replace(field, curr);
      } else {
        this.clauses.get(field).add(new Pair<>(operation, value));
      }
    } else {
      ArrayList<Pair<Integer, String>> curr = new ArrayList<>();
      curr.add(new Pair<>(operation, value));
      this.clauses.put(field, curr);
    }
  }
}
