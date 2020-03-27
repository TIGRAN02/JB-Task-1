package parsers.instructionParsers;

import exceptions.ComparatorException;
import exceptions.InstructionException;
import exceptions.ValueException;

public abstract class InstructionParser {
  protected String[] parts;

  protected InstructionParser(String[] sql) {
    this.parts = sql;
  }

  protected abstract int parse(int beginInd) throws ComparatorException, InstructionException, ValueException, NumberFormatException;
}
