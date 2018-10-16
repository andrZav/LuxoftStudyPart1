package main;

import main.ua.andrzav.ConcurrentFileParser;

public class Main {
  public static void main(String[] args) {

    String fileName0 = "testFile0.txt";
    String fileName1 = "testFile1.txt";
    String fileName2 = "testFile2.txt";

    // file 1
    Thread t1 = new Thread(() -> {
        try {
          System.out.println("Thread 1");
          new ConcurrentFileParser().parseFile(fileName0);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
    });
    t1.start();

    // file 2
    Thread t2 = new Thread(() -> {
        try {
          System.out.println("Thread 2");
          new ConcurrentFileParser().parseFile(fileName1);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
    });
    t2.start();

    // file 3
    Thread t3 = new Thread(()-> {
        try {
          System.out.println("Thread 3");
          new ConcurrentFileParser().parseFile(fileName2);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
    });
    t3.start();
  }
}
