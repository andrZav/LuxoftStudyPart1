package main.ua.andrzav;

import java.util.Arrays;
import java.util.Comparator;

public class Statistics {
  public Statistics() {}

  public String calcLongestWord(String line) {
    String[] subLines = line.split(" ");
    return Arrays.stream(subLines)
            .max(Comparator.comparingInt(String::length)).get();
  }

  public String calcShortestWord(String line) {
    String[] subLines = line.split(" ");
    return Arrays.stream(subLines)
            .min(Comparator.comparingInt(String::length)).get();
  }

  public int calcLineLength(String line) {
    return line.length();
  }

  public int calcAverWordsLengths(String line) {
    String[] subLines = line.split(" ");

    int totalLength = 0;
    for (String word : subLines) {
      totalLength += word.length();
    }
    return totalLength / subLines.length;
  }
}
