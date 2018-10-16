package main.ua.andrzav;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ConcurrentFileParser {
  public void parseFile(String fileName) throws SQLException, InterruptedException {
    // read file content
    MyFileReader fileReader = new MyFileReader();

    fileReader.readLine(fileName);
    fileReader.splitLines("\r\n");
    String[] lines = fileReader.getLines();

    List<String> results = Arrays.stream(lines)
            .filter(l -> !l.isEmpty())
            .collect(Collectors.toList());

    // get statistics for line of the text
    Statistics statistics = new Statistics();

    List<String> longestWords = new LinkedList<>();
    List<String> shortestWords = new LinkedList<>();
    List<Integer> lineLengths = new LinkedList<>();
    List<Integer> averWordsLengths = new LinkedList<>();

    for (String line : results) {
      longestWords.add(statistics.calcLongestWord(line));
      shortestWords.add(statistics.calcShortestWord(line));
      lineLengths.add(statistics.calcLineLength(line));
      averWordsLengths.add(statistics.calcAverWordsLengths(line));
    }

    DbStore dbStore = new DbStore();
    dbStore.save(fileName, longestWords, shortestWords, lineLengths, averWordsLengths);
    System.out.println("Saved file`s statistics with name: " + fileName);
  }
}
