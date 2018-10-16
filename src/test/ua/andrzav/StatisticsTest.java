package test.ua.andrzav;

import main.ua.andrzav.Statistics;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StatisticsTest {
  private static final String testString = "123 3456 78910";
  private static final int length = testString.length();

  Statistics statistics;

  @Before
  public void setUp() throws Exception {
    statistics = new Statistics();
  }

  @Test
  public void calcLongestWord() throws Exception {
    assertEquals("78910", statistics.calcLongestWord(testString));
    assertNotNull(statistics.calcLongestWord(testString));
  }

  @Test
  public void calcShortestWord() throws Exception {
    assertEquals("123", statistics.calcShortestWord(testString));
  }

  @Test
  public void calcLineLength() throws Exception {
    assertEquals(length, statistics.calcLineLength(testString));
  }

  @Test
  public void calcAverWordsLengths() throws Exception {
    assertEquals(4, statistics.calcAverWordsLengths(testString));
  }
}