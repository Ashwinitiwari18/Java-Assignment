package assignment.controller;

import assignment.config.ItemType;
import assignment.model.Item;
import assignment.model.ItemTypeImported;
import assignment.model.ItemTypeRaw;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InputOutputTest {

  private InputOutput inputOutput;
  private ByteArrayOutputStream outContent;

  @BeforeEach
  void setUp() {
    inputOutput = new InputOutput();
    outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
  }

  @Test
  void testValidInput() {
    String[] args = {"-name", "Item1", "-type", "raw", "-quantity", "10", "-price", "20"};
    Item item = inputOutput.input(args);
    assertNotNull(item);
    assertEquals("Item1", item.getName());
    assertEquals(ItemType.RAW, item.getType());
    assertEquals(10, item.getQuantity());
    assertEquals(20, item.getPrice());
  }

  @Test
  void testInvalidFirstArgument() {
    String[] args = {"-type", "raw", "-name", "Item1", "-quantity", "10", "-price", "20"};
    Item item = inputOutput.input(args);
    assertNull(item);
    assertTrue(outContent.toString().contains("First Argument must be name"));
  }

  @Test
  void testIncompleteArguments() {
    String[] args = {"-name", "Item1", "-type", "raw", "-quantity"};
    Item item = inputOutput.input(args);
    assertNull(item);
    assertTrue(outContent.toString().contains("Incomplete argument exception"));
  }

  @Test
  void testDuplicateFields() {
    String[] args = {"-name", "Item1", "-type", "raw", "-type", "imported", "-quantity", "10", "-price", "20"};
    Item item = inputOutput.input(args);
    assertNull(item);
    assertTrue(outContent.toString().contains("Duplicate field not allowed"));
  }

  @Test
  void testInvalidFieldFormat() {
    String[] args = {"-name", "Item1", "type", "raw", "-quantity", "10", "-price", "20"};
    Item item = inputOutput.input(args);
    assertNull(item);
    assertTrue(outContent.toString().contains("Wrong field exception"));
  }

  @Test
  void testInvalidItemType() {
    String[] args = {"-name", "Item1", "-type", "unknown", "-quantity", "10", "-price", "20"};
    Item item = inputOutput.input(args);
    assertNull(item);
    assertTrue(outContent.toString().contains("Item type is not as expected required"));
  }

  @Test
  void testMissingItemType() {
    String[] args = {"-name", "Item1", "-quantity", "10", "-price", "20"};
    Item item = inputOutput.input(args);
    assertNull(item);
    assertTrue(outContent.toString().contains("Item type not Found"));
  }

  @Test
  void testOutput() {
    ArrayList<Item> items = new ArrayList<>();
    Item item1 = new ItemTypeRaw().setName("Item1").setType().setQuantity(10).setPrice(20).setTax().calculateFinalPrice();
    Item item2 = new ItemTypeImported().setName("Item2").setType().setQuantity(5).setPrice(50).setTax().calculateFinalPrice();
    items.add(item1);
    items.add(item2);

    inputOutput.output(items);

    String expectedOutput = "Here are your items: \r\n" +
        "Name: Item1  Price: 20  Type: RAW  Quantity: 10  Tax: 0.125  Final Price: 22.5  \r\n" +
        "Name: Item2  Price: 50  Type: IMPORTED  Quantity: 5  Tax: 0.1  Final Price: 60.0  \r\n" +
        "Thank you for using our service\r\n";

    assertEquals(expectedOutput, outContent.toString());
  }
}
