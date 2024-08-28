package assignment.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuOption {
  private final InputOutput inout;
  public final Map<String,Runnable> mapOfOption;

  /**
   * Constructs a MenuOption object and initializes the options map.
   * The map contains options mapped to corresponding actions.
   */
  public MenuOption() {
    inout = new InputOutput();
    mapOfOption = new HashMap<>();
    mapOfOption.put("1", inout::findParents);
    mapOfOption.put("2", inout::findChildren);
    mapOfOption.put("3", inout::findAncestors);
    mapOfOption.put("4", inout::findDescendants);
    mapOfOption.put("5", inout::deleteDependency);
    mapOfOption.put("6", inout::deleteANode);
    mapOfOption.put("7", inout::addNewDependency);
    mapOfOption.put("8", inout::addNode);
  }

  /**
   * Displays the menu options to the user and executes the selected option.
   * If the user enters "0" or an invalid option, the program exits.
   */
  public void showMenu() {
    System.out.println("Menu");
    System.out.println("1. Get Immediate Parents");
    System.out.println("2. Get Immediate Children");
    System.out.println("3. Get Ancestors");
    System.out.println("4. Get Descendants");
    System.out.println("5. Delete Dependency");
    System.out.println("6. Delete Node");
    System.out.println("7. Add Dependency");
    System.out.println("8. Add Node");
    System.out.print("Enter the type of operation you want to perform : ");
    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();
    Runnable action = mapOfOption.get(input);
    if (action != null) {
      action.run();
    } else {
      System.exit(0);
    }
  }
}
