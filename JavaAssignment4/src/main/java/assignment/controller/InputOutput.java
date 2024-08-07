package assignment.controller;

import assignment.MyDataBase;
import assignment.exceptions.InvalidException;
import assignment.model.Item;
import assignment.model.ItemTypeImported;
import assignment.model.ItemTypeManufactured;
import assignment.model.ItemTypeRaw;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InputOutput {

  final MyDataBase myDataBase = new MyDataBase();

  /**
  * Takes user input for item details.
  * Prompts the user to enter item details,
  * processes the input, and asks if the user wants to enter more items.
  *
  * @return A string indicating whether the user wants to enter more items
  */
  public String takeInput() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter Items Detail : ");
    String input = sc.nextLine();
    String[] args = input.split(" ");
    if (args != null && args.length > 0) {
      convertInputIntoItem(args);
    }
    System.out.print("Do you want to enter details of any other item (y/n) : ");
    return sc.nextLine();
  }

  /**
   * Converts the input arguments into an Item object and inserts it into the database.
   */
  public void convertInputIntoItem(String[] arg) {

    Map<String, String> isPresent = new HashMap<>();


    Map<String, Item> itemMap = new HashMap<>();
    itemMap.put("raw", new ItemTypeRaw());
    itemMap.put("imported", new ItemTypeImported());
    itemMap.put("manufactured", new ItemTypeManufactured());

    Item item = null;
    try {
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
              .setQuantity(quantity);
        } else {
          throw new InvalidException("Item type is not as expected required");
        }
      } else {
        throw new InvalidException("Item type not Found");
      }
    } catch (InvalidException e) {
      System.out.println(e);
    } catch (Exception e) {
      System.out.println(e);
    }
    myDataBase.insertInDb(item);
  }

  /**
   * Outputs the details of items stored in the database.
   * This method triggers the producer and consumer threads to process and display item details.
   */
  public void output() {
    myDataBase.getOutput();
  }
}

// Example usage:
// java Main -name mango -price 40 -type raw -quantity 19
// -name apple -price 80 -type manufactured -quantity 191
// -name orange -price 90 -type imported -quantity 119
