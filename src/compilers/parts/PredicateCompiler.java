package compilers.parts;

import basic.Pair;
import queries.parts.Where;

import java.util.ArrayList;
import java.util.Map;

public class PredicateCompiler implements PartCompiler {
  private Where where;

  public PredicateCompiler(Where where) {
    this.where = where;
  }

  @Override
  public String compile() {
    if (!this.where.isReal()) {
      return "{}";
    }

    StringBuilder predicate = new StringBuilder("{");

    for (Map.Entry<String, ArrayList<Pair<Integer, String>>> clause : this.where.getClauses().entrySet()) {
      predicate.append(clause.getKey()).append(": ");

      if (clause.getValue().get(0).getFirst() == 3) {
        predicate.append(clause.getValue().get(0).getSecond()).append(", ");
        continue;
      }

      predicate.append("{");

      for (Pair<Integer, String> singleClause : clause.getValue()) {
        switch (singleClause.getFirst()) {
          case 0: {
            predicate.append("$gt: ");
            break;
          }

          case 1: {
            predicate.append("$lt: ");
            break;
          }

          case 2: {
            predicate.append("$ne: ");
            break;
          }
        }

        predicate.append(singleClause.getSecond()).append(", ");
      }

      predicate.delete(predicate.length() - 2, predicate.length()).append("}, ");
    }

    predicate.delete(predicate.length() - 2, predicate.length()).append("}");

    return predicate.toString();
  }
}
