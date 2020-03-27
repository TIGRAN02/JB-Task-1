package compilers.parts;

import basic.Pair;
import queries.parts.Sort;

public class SortingCompiler implements PartCompiler {
  private Sort sort;

  public SortingCompiler(Sort sort) {
    this.sort = sort;
  }

  @Override
  public String compile() {
    if (!this.sort.isReal()) {
      return "";
    }

    StringBuilder sorting = new StringBuilder(".sort({");

    for (Pair<String, Boolean> currSort : this.sort.getClauses()) {
      sorting.append(currSort.getFirst()).append(": ");
      if (!currSort.getSecond()) {
        sorting.append("1, ");
      } else {
        sorting.append("-1, ");
      }
    }

    sorting.delete(sorting.length() - 2, sorting.length()).append("})");

    return sorting.toString();
  }
}
