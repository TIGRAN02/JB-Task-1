package parsers.queryParsers;

import exceptions.ComparatorException;
import exceptions.InstructionException;
import exceptions.ValueException;

public abstract class QueryParser {
  protected String[] parts;

  protected QueryParser(String sql) {
    this.parts = sql.replaceAll("\\s+,", ",")
      .replaceAll(">", " > ")
      .replaceAll("<", " < ")
      .replaceAll("=", " = ")
      .replaceAll("<\\s+>", " <> ")
      .split("\\s+");
  }

  protected abstract void parse() throws ComparatorException, InstructionException, ValueException, NumberFormatException;
}
