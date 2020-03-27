package translators;

import compilers.MongoCompiler;
import parsers.SQLParser;

public class SQLMongoTranslator {
  public static String translateSelect(String sql) {
    SQLParser parser = new SQLParser(sql);
    MongoCompiler compiler = new MongoCompiler(parser.parseSelect());
    return compiler.compileSelect();
  }
}
