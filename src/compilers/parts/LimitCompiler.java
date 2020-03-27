package compilers.parts;

import queries.parts.NumberField;

public class LimitCompiler implements PartCompiler {
  private NumberField limit;

  public LimitCompiler(NumberField skip) {
    this.limit = skip;
  }

  @Override
  public String compile() {
    if (!this.limit.isReal()) {
      return "";
    }

    return ".limit(" + this.limit.getValue() + ")";
  }
}
