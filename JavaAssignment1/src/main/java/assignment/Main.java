package assignment;

import assignment.controller.InputOutput;
import assignment.model.Item;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  /**
   * Main method to start the item input and output process.
   *
   * @param args Command-line arguments (not used directly in this method).
   */
  public static void main(String[] args) {
    String isNextInput = "y";
    InputOutput inOut = new InputOutput();
    ArrayList<Item> items = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    do {
      Item item = inOut.input(args);
      if (item != null) {
        items.add(item);
      }
      System.out.println("Do you want to enter details of any other item (y/n):  ");
      isNextInput = sc.nextLine();
      if (isNextInput.equalsIgnoreCase("y")) {
        String input = sc.nextLine();
        args = input.split(" ");
      }
    } while (isNextInput.equalsIgnoreCase("y"));
    inOut.output(items);
  }
}
