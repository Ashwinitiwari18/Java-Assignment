package assignment.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class GraphOperationsTest {

  GraphOperations operations;
  ByteArrayOutputStream outputStream;
  PrintStream originalOut;
  Node node;
  Node parent;
  Node child;

  @BeforeEach
  public void setUp(){
    outputStream = new ByteArrayOutputStream();
    originalOut = System.out;
    System.setOut(new PrintStream(outputStream));
    node = new Node(1,"one");
    parent = new Node(2,"two");
    child = new Node(3,"three");
    parent.addChild(node);
    child.addParent(node);
    node.addParent(parent);
    node.addChild(child);
    operations = new GraphOperations();
  }

  @AfterEach
  public void setDown(){
    System.setOut(originalOut);
  }

  @Test
  public void TestGetRelationParent(){
    operations.getRelations(node,Node::getParents,"Parents");
    String actual = outputStream.toString().trim();
    String expected = "Parents of node one (1) :\r\nName of node two (2) :";
    assertEquals(expected,actual);

  }

  @Test
  public void TestGetRelationChild(){
    operations.getRelations(node,Node::getChildren,"Children");
    String actual = outputStream.toString().trim();
    String expected = "Children of node one (1) :\r\nName of node three (3) :";
    assertEquals(expected,actual);
  }

  @Test
  public void TestGetAncestors(){

    operations.getAncestorsOrDescendants(node,Node::getParents);
    String actual = outputStream.toString().trim();
    String expected = "Name of node two (2) :";
    assertEquals(expected,actual);
  }

  @Test
  public void TestGetDescendants(){

    operations.getAncestorsOrDescendants(node,Node::getChildren);
    String actual = outputStream.toString().trim();
    String expected = "Name of node three (3) :";
    assertEquals(expected,actual);
  }

  @Test
  public void TestDeleteDependency(){
    operations.deleteDependency(parent,node);
    operations.deleteDependency(parent,child);
    String actual = outputStream.toString().trim();
    String expected = "Parent child relationship between these two node has been deleted successfully\r\nThere is no parent-child relationship between these two node";
    assertEquals(expected,actual);
  }

  @Test
  public void TestDeleteNode(){
    operations.deleteANode(node);
    assertEquals(0,node.getParents().size());
    assertEquals(0,node.getChildren().size());
  }

  @Test
  public void TestAddNewDependency(){
    operations.addNewDependency(child,parent);
    assertEquals("If we add this edge then cycle will form.",outputStream.toString().trim());
  }
}
