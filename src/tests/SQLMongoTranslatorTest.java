package tests;

import org.junit.Assert;
import org.junit.After;
import org.junit.Test;
import translators.SQLMongoTranslator;

import java.util.HashMap;
import java.util.Map;

public class SQLMongoTranslatorTest {
  HashMap<String, String> testCases = new HashMap<>();

  @After
  public void clearTestCases() {
    this.testCases.clear();
  }

  @Test
  public void translateSelect() {
    this.testCases.put(
      "SELECT * FROM user",
      "db.user.find({})"
    );
    this.testCases.put(
      "SELECT name, surname FROM collection",
      "db.collection.find({}, {name: 1, surname: 1})"
    );
    this.testCases.put(
      "SELECT id, location FROM user",
      "db.user.find({}, {id: 1, location: 1})"
    );
    this.testCases.put(
      "select id, location from user",
      "db.user.find({}, {id: 1, location: 1})"
    );
    this.testCases.put(
      "SELECT id   ,  location\n\n   FROM   user",
      "db.user.find({}, {id: 1, location: 1})"
    );
    this.testCases.put(
      "SELECT id, location\nFROM user",
      "db.user.find({}, {id: 1, location: 1})"
    );
    this.testCases.put(
      "SELECT user_id, status\nFROM people\nWHERE status = \"A\"",
      "db.people.find({status: \"A\"}, {user_id: 1, status: 1})"
    );
    this.testCases.put(
      "SELECT age, salary " +
        "FROM customers " +
        "WHERE age > 22 and " +
        "age < 33 and " +
        "age=54 and " +
        "salary < 5000 " +
        "order by salary " +
        "limit 10 " +
        "skip 50",
      "db.customers.find({salary: {$lt: 5000}, age: 54}, {age: 1, salary: 1}).sort({salary: 1}).skip(50).limit(10)");

    for (Map.Entry<String, String> testCase : this.testCases.entrySet()) {
      Assert.assertEquals(SQLMongoTranslator.translateSelect(testCase.getKey()), testCase.getValue());
    }
  }

  @Test
  public void translateSelect_WHERE() {
    this.testCases.put(
      "SELECT * FROM user WHERE age > 22",
      "db.user.find({age: {$gt: 22}})"
    );
    this.testCases.put(
      "SELECT *\n FROM people\n WHERE status = \"A\"",
      "db.people.find({status: \"A\"})"
    );
    this.testCases.put(
      "SELECT * FROM user WHERE age < 22",
      "db.user.find({age: {$lt: 22}})"
    );
    this.testCases.put(
      "SELECT * FROM user WHERE age <> 22",
      "db.user.find({age: {$ne: 22}})"
    );
    this.testCases.put(
      "SELECT * FROM user WHERE age = 22",
      "db.user.find({age: 22})"
    );
    this.testCases.put(
      "SELECT * FROM user WHERE age > 22 AND age <> 30",
      "db.user.find({age: {$gt: 22, $ne: 30}})"
    );
    this.testCases.put(
      "SELECT * FROM people WHERE status = \"A\" AND age = 50",
      "db.people.find({age: 50, status: \"A\"})"
    );
    this.testCases.put(
      "SELECT * FROM people WHERE status = \"A\" OR age = 50",
      "db.people.find({$or: [{status: \"A\"}, {age: 50}]})"
    );
    this.testCases.put(
      "select     * \nfrom user \nwhere age<22\n or age   >  30",
      "db.user.find({$or: [{age: {$lt: 22}}, {age: {$gt: 30}}]})"
    );
    this.testCases.put(
      "select     * \nFROM user \nwhere age   = 22\n or age   >  30",
      "db.user.find({$or: [{age: 22}, {age: {$gt: 30}}]})"
    );


    for (Map.Entry<String, String> testCase : this.testCases.entrySet()) {
      Assert.assertEquals(SQLMongoTranslator.translateSelect(testCase.getKey()), testCase.getValue());
    }
  }

  @Test
  public void translateSelect_LIMIT_SKIP() {
    this.testCases.put(
      "SELECT * FROM sales LIMIT 10",
      "db.sales.find({}).limit(10)"
    );
    this.testCases.put(
      "SELECT * FROM collection SKIP 5 LIMIT 10",
      "db.collection.find({}).skip(5).limit(10)"
    );
    this.testCases.put(
      "select * FROM collection SKIP 500",
      "db.collection.find({}).skip(500)"
    );
    this.testCases.put(
      "SELECT number from collection limit 500",
      "db.collection.find({}, {number: 1}).limit(500)"
    );

    for (Map.Entry<String, String> testCase : this.testCases.entrySet()) {
      Assert.assertEquals(SQLMongoTranslator.translateSelect(testCase.getKey()), testCase.getValue());
    }
  }

  @Test
  public void translateSelect_ORDERBY() {
    this.testCases.put(
      "SELECT * FROM collection ORDER BY number",
      "db.collection.find({}).sort({number: 1})"
    );
    this.testCases.put(
      "SELECT * FROM collection ORDER BY number desc",
      "db.collection.find({}).sort({number: -1})"
    );
    this.testCases.put(
      "SELECT * FROM collection\n\n ORDER\n    by\n number, value",
      "db.collection.find({}).sort({number: 1, value: 1})"
    );
    this.testCases.put(
      "SELECT * FROM collection order BY number desc, value",
      "db.collection.find({}).sort({number: -1, value: 1})"
    );
    this.testCases.put(
      "SELECT * from collection ORDER BY number asc, value DESC",
      "db.collection.find({}).sort({number: 1, value: -1})"
    );
    this.testCases.put(
      "SELECT * FROM collection ORDER BY number DESC, value asc",
      "db.collection.find({}).sort({number: -1, value: 1})"
    );

    for (Map.Entry<String, String> testCase : this.testCases.entrySet()) {
      Assert.assertEquals(SQLMongoTranslator.translateSelect(testCase.getKey()), testCase.getValue());
    }
  }
}
