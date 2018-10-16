package main.ua.andrzav;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class DbStore {
  private final DateFormat dateTimeMySqlFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

  private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  private static final String DB_URL = "jdbc:mysql://localhost:3308/file_statistics";

  //  Database credentials
  private static final String USER = "root";
  private static final String PASS = "root";

  private Connection connection = null;
  private Statement statement = null;

  public void save(String fileName, List<String> longestWords, List<String> shortestWords,
    List<Integer> lineLengths, List<Integer> averWordsLengths) throws SQLException {
    try {
      Class.forName("com.mysql.jdbc.Driver");

      connection = DriverManager.getConnection(DB_URL,USER,PASS);
      statement = connection.createStatement();
      String sql;

      // insert into files table
      sql = String.format("INSERT INTO files (dateTime, fileName) "
              + " VALUES('%s', '%s');",
              dateTimeNow(),
              fileName
              );
      statement.execute(sql);
      ResultSet keys = statement.getGeneratedKeys();
      keys.next();
      int file_id = keys.getInt(1);

      for (int i = 0; i < longestWords.size(); i++) {
        sql = String.format("INSERT INTO statistics (longest_word, shortest_word, line_length, aver_words_length, file_id) "
          + " VALUES('%s', '%s', %d, %d, %d);",
          longestWords.get(i),
          shortestWords.get(i),
          lineLengths.get(i),
          averWordsLengths.get(i),
          file_id
        );
        statement.execute(sql);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      if (connection != null && !connection.isClosed()) {
        connection.close();
      }
    }
  }

  private String convertTimestampToSqlFormat(Timestamp dateTime) {
    return dateTimeMySqlFormat.format(dateTime);
  }

  private Timestamp dateTimeNow() {
    Instant time = Instant.ofEpochMilli(new Date().getTime());
    return Timestamp.from(time);
  }


}
