package test.ua.andrzav;

import main.ua.andrzav.MyFileReader;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyFileReaderTest {
  private MyFileReader fileReader = new MyFileReader();
  private String expectedLine;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Before
  public void setUp() throws Exception {
    expectedLine = "Hello world!\r\n" +
       "My name is Andrew.\r\n" +
       "I am software developer.";
  }

  @Test
  public void readLineSuccess() throws FileNotFoundException {
    fileReader.readLine("test/test.txt");
    assertEquals(expectedLine, fileReader.getLine());
  }

  @Test
  public void splitLines() throws Exception {
    fileReader.splitLines(" ");
    assertNotNull(fileReader.getLines());
    assertTrue(fileReader.getLines().length >= 1);
  }
}