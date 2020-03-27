package queries.parts;

import basic.Pair;

import java.util.ArrayList;

public class Sort {
  boolean real = false;
  ArrayList<Pair<String, Boolean>> clauses = new ArrayList<>();

  public ArrayList<Pair<String, Boolean>> getClauses() {
    return clauses;
  }

  public boolean isReal() {
    return real;
  }

  public void pushClause(String field, boolean isDesc) {
    this.real = true;
    clauses.add(new Pair<>(field, isDesc));
  }
}
