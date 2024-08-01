package assignment.controller;

import assignment.exceptions.InvalidException;
import assignment.model.Item;
import assignment.model.ItemTypeImported;
import assignment.model.ItemTypeManufactured;
import assignment.model.ItemTypeRaw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InputOutput {

  /**
   * Processes the input arguments to create an Item object.
   *
   * @param arg an array of strings representing the command-line arguments.
   *            Expected arguments:
   *            -name "itemName" -type "itemType" -quantity "quantity" -price "price"
   *            The order of arguments should be as above.
   *            The -name and -type arguments are mandatory.
   * @return an {@link Item} object created based on the provided arguments.
   * @throws InvalidException if:
   *                          - The first argument is not -name.
   *                          - The number of arguments is odd, indicating incomplete arguments.
   *                          - There are duplicate fields in the arguments.
   *                          - An argument does not start with a '-'.
   *                          - The item type is not one of the expected types
   *                            (raw, manufactured, imported).
   *                          - The item type is missing.
   */
  public Item input(String[] arg) {

    Map<String, String> isPresent = new HashMap<>();


    Map<String, Item> itemMap = new HashMap<>();
    itemMap.put("raw", new ItemTypeRaw());
    itemMap.put("imported", new ItemTypeImported());
    itemMap.put("manufactured", new ItemTypeManufactured());

    Item item = null;
    try {
      System.out.println("Inside Try");
      if (!arg[0].equalsIgnoreCase("-name")) {
        throw new InvalidException("First Argument must be name");
      }
      int length = arg.length;
      if (length % 2 == 1) {
        throw new InvalidException("Incomplete argument exception");
      }
      for (int i = 0; i < length; i += 2) {
        if (isPresent.containsKey(arg[i])) {
          System.out.println(arg[i]);
          throw new InvalidException("Duplicate field not allowed");
        } else if (arg[i].charAt(0) != '-') {
          throw new InvalidException("Wrong field exception");
        }
        isPresent.put(arg[i], arg[i + 1]);
      }
      if (!isPresent.containsKey("-quantity")) {
        isPresent.put("-quantity", "0");
      }
      if (!isPresent.containsKey("-price")) {
        isPresent.put("-price", "0");
      }
      System.out.println("After for");
      if (isPresent.containsKey("-type")) {
        String name = isPresent.get("-name");
        String itemType = isPresent.get("-type");
        int quantity = Integer.parseInt(isPresent.get("-quantity"));
        int price = Integer.parseInt(isPresent.get("-price"));

        if (itemMap.containsKey(itemType)) {
          item = itemMap
              .get(itemType)
              .setName(name)
              .setType()
              .setPrice(price)
              .setQuantity(quantity)
              .setTax()
              .calculateFinalPrice();
        } else {
          throw new InvalidException("Item type is not as expected required");
        }
      } else {
        System.out.println("Inside");
        throw new InvalidException("Item type not Found");
      }
    } catch (InvalidException e) {
      System.out.println(e);
    } catch (Exception e) {
      System.out.println(e);
    }
    return item;
  }

  /**
   * Outputs the details of items in the provided ArrayList.
   *
   * @param items an ArrayList of {@link Item} objects containing the items to be displayed.
   *              Each item's details including name, price, type, quantity, tax, and final price
   *              will be printed.
   */
  public void output(ArrayList<Item> items) {
    System.out.println("Here are your items: ");
    for (Item item : items) {
      System.out.print("Name: " + item.getName() + "  ");
      System.out.print("Price: " + item.getPrice() + "  ");
      System.out.print("Type: " + item.getType() + "  ");
      System.out.print("Quantity: " + item.getQuantity() + "  ");
      System.out.print("Tax: " + item.getTax() + "  ");
      System.out.print("Final Price: " + item.getFinalPrice() + "  ");
      System.out.println();
    }
    System.out.println("Thank you for using our service");
  }
}

// Example usage:
// java Main -name ayush -price 100 -type raw -quantity 19
// -name ash -price 400 -type manufactured -quantity 191
// -name sh -price 10 -type imported -quantity 119
