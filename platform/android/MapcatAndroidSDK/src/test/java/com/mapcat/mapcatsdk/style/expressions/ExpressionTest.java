package com.mapcat.mapcatsdk.style.expressions;

import android.graphics.Color;

import com.mapcat.mapcatsdk.style.layers.PropertyFactory;

import org.junit.Test;

import java.util.Arrays;

import static com.mapcat.mapcatsdk.style.expressions.Expression.acos;
import static com.mapcat.mapcatsdk.style.expressions.Expression.asin;
import static com.mapcat.mapcatsdk.style.expressions.Expression.at;
import static com.mapcat.mapcatsdk.style.expressions.Expression.atan;
import static com.mapcat.mapcatsdk.style.expressions.Expression.concat;
import static com.mapcat.mapcatsdk.style.expressions.Expression.cos;
import static com.mapcat.mapcatsdk.style.expressions.Expression.cubicBezier;
import static com.mapcat.mapcatsdk.style.expressions.Expression.division;
import static com.mapcat.mapcatsdk.style.expressions.Expression.downcase;
import static com.mapcat.mapcatsdk.style.expressions.Expression.eq;
import static com.mapcat.mapcatsdk.style.expressions.Expression.exponential;
import static com.mapcat.mapcatsdk.style.expressions.Expression.get;
import static com.mapcat.mapcatsdk.style.expressions.Expression.gt;
import static com.mapcat.mapcatsdk.style.expressions.Expression.gte;
import static com.mapcat.mapcatsdk.style.expressions.Expression.has;
import static com.mapcat.mapcatsdk.style.expressions.Expression.interpolate;
import static com.mapcat.mapcatsdk.style.expressions.Expression.length;
import static com.mapcat.mapcatsdk.style.expressions.Expression.literal;
import static com.mapcat.mapcatsdk.style.expressions.Expression.ln;
import static com.mapcat.mapcatsdk.style.expressions.Expression.log10;
import static com.mapcat.mapcatsdk.style.expressions.Expression.log2;
import static com.mapcat.mapcatsdk.style.expressions.Expression.lt;
import static com.mapcat.mapcatsdk.style.expressions.Expression.lte;
import static com.mapcat.mapcatsdk.style.expressions.Expression.match;
import static com.mapcat.mapcatsdk.style.expressions.Expression.max;
import static com.mapcat.mapcatsdk.style.expressions.Expression.min;
import static com.mapcat.mapcatsdk.style.expressions.Expression.mod;
import static com.mapcat.mapcatsdk.style.expressions.Expression.neq;
import static com.mapcat.mapcatsdk.style.expressions.Expression.not;
import static com.mapcat.mapcatsdk.style.expressions.Expression.pow;
import static com.mapcat.mapcatsdk.style.expressions.Expression.product;
import static com.mapcat.mapcatsdk.style.expressions.Expression.rgb;
import static com.mapcat.mapcatsdk.style.expressions.Expression.rgba;
import static com.mapcat.mapcatsdk.style.expressions.Expression.sin;
import static com.mapcat.mapcatsdk.style.expressions.Expression.sqrt;
import static com.mapcat.mapcatsdk.style.expressions.Expression.step;
import static com.mapcat.mapcatsdk.style.expressions.Expression.subtract;
import static com.mapcat.mapcatsdk.style.expressions.Expression.sum;
import static com.mapcat.mapcatsdk.style.expressions.Expression.tan;
import static com.mapcat.mapcatsdk.style.expressions.Expression.upcase;
import static com.mapcat.mapcatsdk.style.expressions.Expression.var;
import static junit.framework.Assert.assertTrue;

/**
 * Expression unit tests that validate the expression output with the expected Object[]array representation.
 */
public class ExpressionTest {

  @Test
  public void testRgb() throws Exception {
    Object[] expected = new Object[] {"rgb", 0, 0, 0};
    Object[] actual = Expression.rgb(Expression.literal(0), Expression.literal(0), Expression.literal(0)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testRgbLiteral() throws Exception {
    Object[] expected = new Object[] {"rgb", 0, 0, 0};
    Object[] actual = Expression.rgb(0, 0, 0).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testRgba() throws Exception {
    Object[] expected = new Object[] {"rgba", 0, 0, 0, 1};
    Object[] actual = Expression.rgba(Expression.literal(0), Expression.literal(0), Expression.literal(0), Expression.literal(1)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testRgbaLiteral() throws Exception {
    Object[] expected = new Object[] {"rgba", 0, 0, 0, 1};
    Object[] actual = Expression.rgba(0, 0, 0, 1).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testToRgba() throws Exception {
    Object[] expected = new Object[] {"to-rgba", PropertyFactory.colorToRgbaString(Color.RED)};
    Object[] actual = Expression.toRgba(Expression.color(Color.RED)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testEq() throws Exception {
    Object[] expected = new Object[] {"==", 1, 1};
    Object[] actual = Expression.eq(Expression.literal(1), Expression.literal(1)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testEqLiteral() throws Exception {
    Object[] expected = new Object[] {"==", 1, 1};
    Object[] actual = Expression.eq(1, 1).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testNeq() throws Exception {
    Object[] expected = new Object[] {"!=", 0, 1};
    Object[] actual = Expression.neq(Expression.literal(0), Expression.literal(1)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testNeqLiteral() throws Exception {
    Object[] expected = new Object[] {"!=", 0, 1};
    Object[] actual = Expression.neq(0, 1).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testGt() throws Exception {
    Object[] expected = new Object[] {">", 0, 1};
    Object[] actual = Expression.gt(Expression.literal(0), Expression.literal(1)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testGtLiteral() throws Exception {
    Object[] expected = new Object[] {">", 0, 1};
    Object[] actual = Expression.gt(0, 1).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLt() throws Exception {
    Object[] expected = new Object[] {"<", 1, 0};
    Object[] actual = Expression.lt(Expression.literal(1), Expression.literal(0)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLtLiteral() throws Exception {
    Object[] expected = new Object[] {"<", 1, 0};
    Object[] actual = Expression.lt(1, 0).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testGte() throws Exception {
    Object[] expected = new Object[] {">=", 1, 1};
    Object[] actual = Expression.gte(Expression.literal(1), Expression.literal(1)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testGteLiteral() throws Exception {
    Object[] expected = new Object[] {">=", 1, 1};
    Object[] actual = Expression.gte(1, 1).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLte() throws Exception {
    Object[] expected = new Object[] {"<=", 1, 1};
    Object[] actual = Expression.lte(Expression.literal(1), Expression.literal(1)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLteLiteral() throws Exception {
    Object[] expected = new Object[] {"<=", 1, 1};
    Object[] actual = Expression.lte(1, 1).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testAll() throws Exception {
    Object[] expected = new Object[] {"all", true, true, true};
    Object[] actual = Expression.all(Expression.literal(true), Expression.literal(true), Expression.literal(true)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testAny() throws Exception {
    Object[] expected = new Object[] {"any", true, false, false};
    Object[] actual = Expression.any(Expression.literal(true), Expression.literal(false), Expression.literal(false)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testNot() throws Exception {
    Object[] expected = new Object[] {"!", false};
    Object[] actual = Expression.not(Expression.literal(false)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testNotLiteral() throws Exception {
    Object[] expected = new Object[] {"!", false};
    Object[] actual = Expression.not(false).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testSwitchCase() throws Exception {
    Object[] expectedCaseOneGet = new Object[] {"get", "key1"};
    Object[] expectedCaseOne = new Object[] {"==", expectedCaseOneGet, "value1"};
    Object[] expectedCaseTwoGet = new Object[] {"get", "key2"};
    Object[] expectedCaseTwo = new Object[] {"!=", expectedCaseTwoGet, "value2"};
    Object[] expected = new Object[] {"case", expectedCaseOne, expectedCaseTwo};

    Object[] actual = Expression.switchCase(
      Expression.eq(Expression.get(Expression.literal("key1")), Expression.literal("value1")),
      Expression.neq(Expression.get(Expression.literal("key2")), Expression.literal("value2"))
    ).toArray();

    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testSwitchCaseLiteral() throws Exception {
    Object[] expectedCaseOneGet = new Object[] {"get", "key1"};
    Object[] expectedCaseOne = new Object[] {"==", expectedCaseOneGet, "value1"};
    Object[] expectedCaseTwoGet = new Object[] {"get", "key2"};
    Object[] expectedCaseTwo = new Object[] {"!=", expectedCaseTwoGet, "value2"};
    Object[] expected = new Object[] {"case", expectedCaseOne, expectedCaseTwo};

    Object[] actual = Expression.switchCase(
      Expression.eq(Expression.get("key1"), Expression.literal("value1")),
      Expression.neq(Expression.get("key2"), Expression.literal("value2"))
    ).toArray();

    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testMatch() throws Exception {
    Object[] labelZero = new Object[] {"a", "output"};
    Object[] labelOne = new Object[] {"b", "output2"};
    Object[] labelTwo = new Object[] {"c", "output3"};

    Object[] expected = new Object[] {"match", labelZero, labelOne, labelTwo};
    Object[] actual = Expression.match(Expression.literal(labelZero), Expression.literal(labelOne), Expression.literal(labelTwo)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testCoalesce() throws Exception {
    Object[] expectedGetOne = new Object[] {"get", "invalidKey"};
    Object[] expectedGetTwo = new Object[] {"get", "validKey"};
    Object[] expected = new Object[] {"coalesce", expectedGetOne, expectedGetTwo};

    Object[] actual = Expression.coalesce(
      Expression.get(Expression.literal("invalidKey")),
      Expression.get(Expression.literal("validKey"))
    ).toArray();

    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testCoalesceLiteral() throws Exception {
    Object[] expectedGetOne = new Object[] {"get", "invalidKey"};
    Object[] expectedGetTwo = new Object[] {"get", "validKey"};
    Object[] expected = new Object[] {"coalesce", expectedGetOne, expectedGetTwo};

    Object[] actual = Expression.coalesce(
      Expression.get("invalidKey"),
      Expression.get("validKey")
    ).toArray();

    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testProperties() throws Exception {
    Object[] expected = new Object[] {"properties"};
    Object[] actual = Expression.properties().toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testGeometryType() throws Exception {
    Object[] expected = new Object[] {"geometry-type"};
    Object[] actual = Expression.geometryType().toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testId() throws Exception {
    Object[] expected = new Object[] {"id"};
    Object[] actual = Expression.id().toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testHeatmapDensity() throws Exception {
    Object[] expected = new Object[] {"heatmap-density"};
    Object[] actual = Expression.heatmapDensity().toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testAt() throws Exception {
    Object[] expected = new Object[] {"at", 3, new Object[] {"one", "two"}};
    Object[] actual = Expression.at(Expression.literal(3), Expression.literal(new Object[] {"one", "two"})).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testAtLiteral() throws Exception {
    Object[] expected = new Object[] {"at", 3, new Object[] {"one", "two"}};
    Object[] actual = Expression.at(3, Expression.literal(new Object[] {"one", "two"})).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testAtExpression() throws Exception {
    Object[] expected = new Object[] {"at", 3, new Object[] {"properties"}};
    Object[] actual = Expression.at(Expression.literal(3), Expression.properties()).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testGet() throws Exception {
    Object[] expected = new Object[] {"get", "key"};
    Object[] actual = Expression.get(Expression.literal("key")).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testGetLiteral() throws Exception {
    Object[] expected = new Object[] {"get", "key"};
    Object[] actual = Expression.get("key").toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testGetObject() throws Exception {
    Object[] expected = new Object[] {"get", "key", new Object[] {"properties"}};
    Object[] actual = Expression.get(Expression.literal("key"), Expression.properties()).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testGetObjectLiteral() throws Exception {
    Object[] expected = new Object[] {"get", "key", new Object[] {"properties"}};
    Object[] actual = Expression.get("key", Expression.properties()).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testHas() throws Exception {
    Object[] expected = new Object[] {"has", "key"};
    Object[] actual = Expression.has(Expression.literal("key")).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testHasLiteral() throws Exception {
    Object[] expected = new Object[] {"has", "key"};
    Object[] actual = Expression.has("key").toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testHasObject() throws Exception {
    Object[] expected = new Object[] {"has", "key", new Object[] {"properties"}};
    Object[] actual = Expression.has(Expression.literal("key"), Expression.properties()).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testHasObjectLiteral() throws Exception {
    Object[] expected = new Object[] {"has", "key", new Object[] {"properties"}};
    Object[] actual = Expression.has("key", Expression.properties()).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testHasExpression() throws Exception {
    Object[] expected = new Object[] {"has", new Object[] {"get", "key"}, new Object[] {"properties"}};
    Object[] actual = Expression.has(Expression.get(Expression.literal("key")), Expression.properties()).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testHasExpressionLiteral() throws Exception {
    Object[] expected = new Object[] {"has", new Object[] {"get", "key"}, new Object[] {"properties"}};
    Object[] actual = Expression.has(Expression.get("key"), Expression.properties()).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLength() throws Exception {
    Object[] expected = new Object[] {"length", "key"};
    Object[] actual = Expression.length(Expression.literal("key")).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLengthLiteral() throws Exception {
    Object[] expected = new Object[] {"length", "key"};
    Object[] actual = Expression.length("key").toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLengthExpression() throws Exception {
    Object[] expected = new Object[] {"length", new Object[] {"get", "key"}};
    Object[] actual = Expression.length(Expression.get(Expression.literal("key"))).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLn2() throws Exception {
    Object[] expected = new Object[] {"ln2"};
    Object[] actual = Expression.ln2().toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testPi() throws Exception {
    Object[] expected = new Object[] {"pi"};
    Object[] actual = Expression.pi().toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testE() throws Exception {
    Object[] expected = new Object[] {"e"};
    Object[] actual = Expression.e().toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testSum() throws Exception {
    Object[] expected = new Object[] {"+", 1, 2};
    Object[] actual = Expression.sum(Expression.literal(1), Expression.literal(2)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testSumLiteral() throws Exception {
    Object[] expected = new Object[] {"+", 1, 2};
    Object[] actual = Expression.sum(1, 2).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testProduct() throws Exception {
    Object[] expected = new Object[] {"*", 1, 2};
    Object[] actual = Expression.product(Expression.literal(1), Expression.literal(2)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testProductLiteral() throws Exception {
    Object[] expected = new Object[] {"*", 1, 2};
    Object[] actual = Expression.product(1, 2).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testSubtract() throws Exception {
    Object[] expected = new Object[] {"-", 2, 1};
    Object[] actual = Expression.subtract(Expression.literal(2), Expression.literal(1)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testSubtractLiteral() throws Exception {
    Object[] expected = new Object[] {"-", 2, 1};
    Object[] actual = Expression.subtract(2, 1).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testDivision() throws Exception {
    Object[] expected = new Object[] {"/", 2, 1};
    Object[] actual = Expression.division(Expression.literal(2), Expression.literal(1)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testDivisionLiteral() throws Exception {
    Object[] expected = new Object[] {"/", 2, 1};
    Object[] actual = Expression.division(2, 1).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testDivisionWithNestedGet() throws Exception {
    Object nestedGet = new Object[] {"get", "key"};
    Object[] expected = new Object[] {"/", 2, nestedGet};
    Object[] actual = Expression.division(Expression.literal(2), Expression.get(Expression.literal("key"))).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testMod() throws Exception {
    Object[] expected = new Object[] {"%", 1, 3};
    Object[] actual = Expression.mod(Expression.literal(1), Expression.literal(3)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testModLiteral() throws Exception {
    Object[] expected = new Object[] {"%", 1, 3};
    Object[] actual = Expression.mod(1, 3).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testPow() throws Exception {
    Object[] expected = new Object[] {"^", 2, 3};
    Object[] actual = Expression.pow(Expression.literal(2), Expression.literal(3)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testPowLiteral() throws Exception {
    Object[] expected = new Object[] {"^", 2, 3};
    Object[] actual = Expression.pow(2, 3).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testSqrt() throws Exception {
    Object[] expected = new Object[] {"sqrt", 4};
    Object[] actual = Expression.sqrt(Expression.literal(4)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testSqrtLiteral() throws Exception {
    Object[] expected = new Object[] {"sqrt", 4};
    Object[] actual = Expression.sqrt(4).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLog10() throws Exception {
    Object[] expected = new Object[] {"log10", 10};
    Object[] actual = Expression.log10(Expression.literal(10)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLog10Literal() throws Exception {
    Object[] expected = new Object[] {"log10", 10};
    Object[] actual = Expression.log10(10).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLn() throws Exception {
    Object[] expected = new Object[] {"ln", 2};
    Object[] actual = Expression.ln(Expression.literal(2)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLnLiteral() throws Exception {
    Object[] expected = new Object[] {"ln", 2};
    Object[] actual = Expression.ln(2).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLog2() throws Exception {
    Object[] expected = new Object[] {"log2", 16};
    Object[] actual = Expression.log2(Expression.literal(16)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLog2Literal() throws Exception {
    Object[] expected = new Object[] {"log2", 16};
    Object[] actual = Expression.log2(16).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testSin() throws Exception {
    Object[] expected = new Object[] {"sin", 45};
    Object[] actual = Expression.sin(Expression.literal(45)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testSinLiteral() throws Exception {
    Object[] expected = new Object[] {"sin", 45};
    Object[] actual = Expression.sin(45).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testCos() throws Exception {
    Object[] expected = new Object[] {"cos", 45};
    Object[] actual = Expression.cos(Expression.literal(45)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testCosLiteral() throws Exception {
    Object[] expected = new Object[] {"cos", 45};
    Object[] actual = Expression.cos(45).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testTan() throws Exception {
    Object[] expected = new Object[] {"tan", 45};
    Object[] actual = Expression.tan(Expression.literal(45)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testTanLiteral() throws Exception {
    Object[] expected = new Object[] {"tan", 45};
    Object[] actual = Expression.tan(45).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testAsin() throws Exception {
    Object[] expected = new Object[] {"asin", 45};
    Object[] actual = Expression.asin(Expression.literal(45)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testAsinLiteral() throws Exception {
    Object[] expected = new Object[] {"asin", 45};
    Object[] actual = Expression.asin(45).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testAcos() throws Exception {
    Object[] expected = new Object[] {"acos", 45};
    Object[] actual = Expression.acos(Expression.literal(45)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testAcosLiteral() throws Exception {
    Object[] expected = new Object[] {"acos", 45};
    Object[] actual = Expression.acos(45).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testAtan() throws Exception {
    Object[] expected = new Object[] {"atan", 45};
    Object[] actual = Expression.atan(Expression.literal(45)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testAtanLiteral() throws Exception {
    Object[] expected = new Object[] {"atan", 45};
    Object[] actual = Expression.atan(45).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testMin() throws Exception {
    Object[] expected = new Object[] {"min", 0, 1, 2, 3};
    Object[] actual = Expression.min(Expression.literal(0), Expression.literal(1), Expression.literal(2), Expression.literal(3)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testMinLiteral() throws Exception {
    Object[] expected = new Object[] {"min", 0, 1, 2, 3};
    Object[] actual = Expression.min(0, 1, 2, 3).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testMax() throws Exception {
    Object[] expected = new Object[] {"max", 0, 1, 2, 3};
    Object[] actual = Expression.max(Expression.literal(0), Expression.literal(1), Expression.literal(2), Expression.literal(3)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testMaxLiteral() throws Exception {
    Object[] expected = new Object[] {"max", 0, 1, 2, 3};
    Object[] actual = Expression.max(0, 1, 2, 3).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testUpcase() throws Exception {
    Object[] expected = new Object[] {"upcase", "string"};
    Object[] actual = Expression.upcase(Expression.literal("string")).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testUpcaseLiteral() throws Exception {
    Object[] expected = new Object[] {"upcase", "string"};
    Object[] actual = Expression.upcase("string").toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testDowncase() throws Exception {
    Object[] expected = new Object[] {"downcase", "string"};
    Object[] actual = Expression.downcase(Expression.literal("string")).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testDowncaseLiteral() throws Exception {
    Object[] expected = new Object[] {"downcase", "string"};
    Object[] actual = Expression.downcase("string").toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testConcat() throws Exception {
    Object[] expected = new Object[] {"concat", "foo", "bar"};
    Object[] actual = Expression.concat(Expression.literal("foo"), Expression.literal("bar")).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testConcatLiteral() throws Exception {
    Object[] expected = new Object[] {"concat", "foo", "bar"};
    Object[] actual = Expression.concat("foo", "bar").toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testArray() throws Exception {
    Object[] get = new Object[] {"get", "keyToArray"};
    Object[] expected = new Object[] {"array", get};
    Object[] actual = Expression.array(Expression.get(Expression.literal("keyToArray"))).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testArrayLiteral() throws Exception {
    Object[] get = new Object[] {"get", "keyToArray"};
    Object[] expected = new Object[] {"array", get};
    Object[] actual = Expression.array(Expression.get("keyToArray")).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testTypeOf() throws Exception {
    Object[] expected = new Object[] {"typeof", "value"};
    Object[] actual = Expression.typeOf(Expression.literal("value")).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testString() throws Exception {
    Object[] expected = new Object[] {"string", "value"};
    Object[] actual = Expression.string(Expression.literal("value")).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testNumber() throws Exception {
    Object[] expected = new Object[] {"number", 1};
    Object[] actual = Expression.number(Expression.literal(1)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testBool() throws Exception {
    Object[] expected = new Object[] {"boolean", true};
    Object[] actual = Expression.bool(Expression.literal(true)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testObject() throws Exception {
    Object object = new Object();
    Object[] expected = new Object[] {"object", object};
    Object[] actual = Expression.object(Expression.literal(object)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testToString() throws Exception {
    Object[] expected = new Object[] {"to-string", 3};
    Object[] actual = Expression.toString(Expression.literal(3)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testToNumber() throws Exception {
    Object[] expected = new Object[] {"to-number", "3"};
    Object[] actual = Expression.toNumber(Expression.literal("3")).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testToBool() throws Exception {
    Object[] expected = new Object[] {"to-boolean", "true"};
    Object[] actual = Expression.toBool(Expression.literal("true")).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testToColor() throws Exception {
    Object[] expected = new Object[] {"to-color", "value"};
    Object[] actual = Expression.toColor(Expression.literal("value")).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLet() throws Exception {
    Object[] expected = new Object[] {"let", "letName", "value"};
    Object[] actual = Expression.let(Expression.literal("letName"), Expression.literal("value")).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testVar() throws Exception {
    Object[] expected = new Object[] {"var", "letName"};
    Object[] actual = Expression.var(Expression.literal("letName")).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testVarLiteral() throws Exception {
    Object[] expected = new Object[] {"var", "letName"};
    Object[] actual = Expression.var("letName").toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testVarExpression() throws Exception {
    Object[] expected = new Object[] {"var", new Object[] {"get", "letName"}};
    Object[] actual = Expression.var(Expression.get(Expression.literal("letName"))).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testVarExpressionLiteral() throws Exception {
    Object[] expected = new Object[] {"var", new Object[] {"get", "letName"}};
    Object[] actual = Expression.var(Expression.get("letName")).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testZoom() throws Exception {
    Object[] expected = new Object[] {"zoom"};
    Object[] actual = Expression.zoom().toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testStepBasic() throws Exception {
    Object[] expected = new Object[] {"step", 12, 11, 0, 111, 1, 1111};
    Object[] actual = Expression.step(Expression.literal(12), Expression.literal(11), Expression.literal(0), Expression.literal(111), Expression.literal(1), Expression.literal(1111)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testStepBasicLiteral() throws Exception {
    Object[] expected = new Object[] {"step", new Object[] {"get", "line-width"}, 11, 0, 111, 1, 1111};
    Object[] actual = Expression.step(Expression.get("line-width"), Expression.literal(11), Expression.stop(0, 111), Expression.stop(1, 1111)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testStepExpression() throws Exception {
    Object[] input = new Object[] {"get", "key"};
    Object[] number = new Object[] {"to-number", input};
    Object[] expected = new Object[] {"step", number, 11, 0, 111, 1, 1111};
    Object[] actual = Expression.step(Expression.toNumber(Expression.get(Expression.literal("key"))),
      Expression.literal(11), Expression.literal(0), Expression.literal(111), Expression.literal(1), Expression.literal(1111)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testStepExpressionLiteral() throws Exception {
    Object[] input = new Object[] {"get", "key"};
    Object[] number = new Object[] {"to-number", input};
    Object[] expected = new Object[] {"step", number, 11, 0, 111, 1, 1111};
    Object[] actual = Expression.step(Expression.toNumber(Expression.get("key")), Expression.literal(11), Expression.stop(0, 111), Expression.stop(1, 1111)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLinear() throws Exception {
    Object[] stopZero = new Object[] {0, 1};
    Object[] stopOne = new Object[] {1, 2};
    Object[] stopTwo = new Object[] {2, 3};
    Object[] expected = new Object[] {"interpolate", new Object[] {"linear"}, 12, stopZero, stopOne, stopTwo};
    Object[] actual = Expression.interpolate(Expression.linear(), Expression.literal(12),
      Expression.literal(stopZero), Expression.literal(stopOne), Expression.literal(stopTwo)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testLinearStops() throws Exception {
    Object[] expected = new Object[] {"interpolate", new Object[] {"linear"}, 12, 0, 1, 1, 2, 2, 3};
    Object[] actual = Expression.interpolate(Expression.linear(), Expression.literal(12), Expression.stop(0, 1), Expression.stop(1, 2), Expression.stop(2, 3)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testExponential() throws Exception {
    Object[] exponential = new Object[] {"exponential", 12};
    Object[] get = new Object[] {"get", "x"};
    Object[] expected = new Object[] {"interpolate", exponential, get, 0, 100, 200};
    Object[] actual = Expression.interpolate(Expression.exponential(Expression.literal(12)),
      Expression.get(Expression.literal("x")), Expression.literal(0), Expression.literal(100), Expression.literal(200)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testExponentialLiteral() throws Exception {
    Object[] exponential = new Object[] {"exponential", 12};
    Object[] get = new Object[] {"get", "x"};
    Object[] expected = new Object[] {"interpolate", exponential, get, 0, 100, 100, 200};
    Object[] actual = Expression.interpolate(Expression.exponential(12), Expression.get("x"), Expression.stop(0, 100), Expression.stop(100, 200)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testExponentialExpressionLiteral() throws Exception {
    Object[] getX = new Object[] {"get", "x"};
    Object[] exponential = new Object[] {"exponential", getX};
    Object[] getY = new Object[] {"get", "y"};
    Object[] expected = new Object[] {"interpolate", exponential, getY, 0, 100, 100, 200};
    Object[] actual = Expression.interpolate(Expression.exponential(Expression.get("x")), Expression.get("y"), Expression.stop(0, 100), Expression.stop(100, 200)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testCubicBezier() throws Exception {
    Object[] cubicBezier = new Object[] {"cubic-bezier", 1, 1, 1, 1};
    Object[] get = new Object[] {"get", "x"};
    Object[] expected = new Object[] {"interpolate", cubicBezier, get, 0, 100, 100, 200};
    Object[] actual = Expression.interpolate(Expression.cubicBezier(Expression.literal(1), Expression.literal(1), Expression.literal(1), Expression.literal(1)),
      Expression.get(Expression.literal("x")), Expression.literal(0), Expression.literal(100), Expression.literal(100), Expression.literal(200)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testCubicBezierLiteral() throws Exception {
    Object[] cubicBezier = new Object[] {"cubic-bezier", 1, 1, 1, 1};
    Object[] get = new Object[] {"get", "x"};
    Object[] expected = new Object[] {"interpolate", cubicBezier, get, 0, 100, 100, 200};
    Object[] actual = Expression.interpolate(Expression.cubicBezier(1, 1, 1, 1), Expression.get("x"), Expression.stop(0, 100), Expression.stop(100, 200)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testCubicBezierExpression() throws Exception {
    Object[] getX = new Object[] {"get", "x"};
    Object[] getY = new Object[] {"get", "y"};
    Object[] getZ = new Object[] {"get", "z"};
    Object[] cubicBezier = new Object[] {"cubic-bezier", getZ, 1, getY, 1};
    Object[] expected = new Object[] {"interpolate", cubicBezier, getX, 0, 100, 200};
    Object[] actual = Expression.interpolate(Expression.cubicBezier(Expression.get(Expression.literal("z")), Expression.literal(1),
      Expression.get(Expression.literal("y")), Expression.literal(1)), Expression.get(Expression.literal("x")), Expression.literal(0), Expression.literal(100), Expression.literal(200)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }

  @Test
  public void testCubicBezierExpressionLiteral() throws Exception {
    Object[] getX = new Object[] {"get", "x"};
    Object[] getY = new Object[] {"get", "y"};
    Object[] getZ = new Object[] {"get", "z"};
    Object[] cubicBezier = new Object[] {"cubic-bezier", getZ, 1, getY, 1};
    Object[] expected = new Object[] {"interpolate", cubicBezier, getX, 0, 100, 100, 200};
    Object[] actual = Expression.interpolate(Expression.cubicBezier(Expression.get("z"), Expression.literal(1), Expression.get("y"),
      Expression.literal(1)), Expression.get("x"), Expression.stop(0, 100), Expression.stop(100, 200)).toArray();
    assertTrue("expression should match", Arrays.deepEquals(expected, actual));
  }
}