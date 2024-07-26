package assignment.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class NodeTest {
  Node node;

  @BeforeEach
  public void setUp(){
    node = new Node(1,"one");
  }

  @Test
  public void testAddChild(){
    node.addChild(new Node());
    assertEquals(1,node.getChildren().size());
  }

  @Test
  public void testAddParents(){
    node.addParent(new Node());
    assertEquals(1,node.getParents().size());
  }

  @Test
  public void testRemoveChild(){
    Node child = new Node();
    node.addChild(child);
    node.removeChild(child);
    assertEquals(0,node.getChildren().size());
  }

  @Test
  public void testRemoveParents(){
    Node newNode = new Node();
    node.addParent(newNode);
    node.removeParent(newNode);
    assertEquals(0,node.getParents().size());
  }

  @Test
  public void testPrintIdAndName(){
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outputStream));

    node.printNameId(false);

    String actual = outputStream.toString().trim();
    String expected = "Name of node one (1) :";
    assertEquals(expected,actual);
    System.setOut(originalOut);
  }
}
