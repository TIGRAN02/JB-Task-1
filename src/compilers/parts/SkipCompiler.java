package compilers.parts;

import queries.parts.NumberField;

public class SkipCompiler implements PartCompiler {
  private NumberField skip;

  public SkipCompiler(NumberField skip) {
    this.skip = skip;
  }

  @Override
  public String compile() {
    if (!this.skip.isReal()) {
      return "";
    }

    return ".skip(" + this.skip.getValue() + ")";
  }
}
