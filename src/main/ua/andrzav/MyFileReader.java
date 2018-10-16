package main.ua.andrzav;

import java.io.*;

public class MyFileReader implements AutoCloseable {
  private String[] lines;
  private StringBuilder line = new StringBuilder();

  public void readLine(String fileName) {
    try (InputStreamReader isr = new InputStreamReader(new FileInputStream(new File(fileName)))) {
      int c = 0;
      while ((c = isr.read()) != -1) {
        line.append((char)c);
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public void splitLines(String delimiter) {
    lines = getLine().split(delimiter);
  }

  public String[] getLines() {
    return lines;
  }

  public String getLine() {
    return line.toString();
  }

  @Override
  public void close() throws Exception {
    System.out.println("Resource is closed");
  }
}
